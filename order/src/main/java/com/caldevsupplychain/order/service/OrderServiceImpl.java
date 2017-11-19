package com.caldevsupplychain.order.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.model.Item;
import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.repository.ItemRepository;
import com.caldevsupplychain.order.repository.OrderRepository;
import com.caldevsupplychain.order.util.CycleAvoidingMappingContext;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.vo.OrderBean;
import com.caldevsupplychain.order.vo.OrderStatus;
import com.google.common.base.Preconditions;


@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private ItemRepository itemRepository;
	private OrderMapper orderMapper;


	@Override
	public boolean orderExists(String uuid) {
		return orderRepository.findByUuid(uuid) != null;
	}

	@Override
	@Transactional
	public OrderBean createOrder(OrderBean orderBean) {

		Preconditions.checkState(orderBean != null, "Must have orderBean in creating order");

		CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

		Order order = orderMapper.toOrder(orderBean, context);

		orderRepository.save(order);

		order.getItems().stream().forEach(item -> {
			item.setOrder(order);
		});

		itemRepository.save(order.getItems());

		return orderMapper.toBean(order, context);
	}

	@Override
	@Transactional
	public OrderBean updateOrder(String orderUuid, OrderBean orderBean) {

		Order order = orderRepository.findByUuid(orderUuid);

		Preconditions.checkState(order != null, ErrorCode.ORDER_NOT_FOUND);

		// update order part
		Optional.ofNullable(orderBean.getSku()).ifPresent(sku -> order.setSku(sku));

		log.warn("stuck at sku?");

		Optional.ofNullable(orderBean.getOrderType()).ifPresent(orderType -> order.setOrderType(orderType));

		Optional.ofNullable(orderBean.getOrderStatus()).ifPresent(orderStatus -> order.setOrderStatus(orderStatus));

		Optional.ofNullable(orderBean.getCurrency()).ifPresent(currency -> order.setCurrency(currency));

		Optional.ofNullable(orderBean.getTotalPrice()).ifPresent(totalPrice -> order.setTotalPrice(totalPrice));

		Optional.ofNullable(orderBean.getShippingInstruction()).ifPresent(shippingInstruction -> order.setShippingInstruction(shippingInstruction));

		Optional.ofNullable(orderBean.getOrderNote()).ifPresent(orderNote -> order.setOrderNote(orderNote));

		// update item list part
		orderBean.getItems().stream().forEach(itemBean -> {

			// must have item uuid
			Preconditions.checkState(itemBean.getUuid() != null, "Can't find itemBean with uuid = {" + itemBean.getUuid() + "}");

			Item item = itemRepository.findByUuid(itemBean.getUuid());

			Preconditions.checkState(item != null, ErrorCode.ITEM_NOT_FOUND.name());

			Optional.ofNullable(itemBean.getColor()).ifPresent(color -> item.setColor(color));

			Optional.ofNullable(itemBean.getDescription()).ifPresent(itemDescription -> item.setDescription(itemDescription));

			Optional.ofNullable(itemBean.getFabric()).ifPresent(fabric -> item.setFabric(fabric));

			Optional.ofNullable(itemBean.getQuantity()).ifPresent(quantity -> item.setQuantity(quantity));

			Optional.ofNullable(itemBean.getPrice()).ifPresent(itemPrice -> item.setPrice(itemPrice));

			Optional.ofNullable(itemBean.getNote()).ifPresent(itemNote -> item.setNote(itemNote));

			item.setOrder(order);

		});

		return orderMapper.toBean(order, new CycleAvoidingMappingContext());
	}

	@Override
	@Transactional
	public void deleteOrder(Long id) {

		Order order = orderRepository.findOne(id);

		Preconditions.checkState(order != null, ErrorCode.ORDER_NOT_FOUND);

		order.setOrderStatus(OrderStatus.DELETED);

	}

	public Optional<OrderBean> getOrder(String uuid) {

		Order order = orderRepository.findByUuidAndOrderStatusNotDeleted(uuid);

		if(order != null) {
			return Optional.of(orderMapper.toBean(order, new CycleAvoidingMappingContext()));
		}
		return Optional.empty();
	}

	@Override
	public Optional<OrderBean> findByUuid(String uuid) {

		Order order = orderRepository.findByUuid(uuid);

		if(order != null) {
			return Optional.of(orderMapper.toBean(order, new CycleAvoidingMappingContext()));
		}
		return Optional.empty();
	}

	@Override
	public List<OrderBean> findByUserUuid(String userUuid) {

		List<Order> orders = orderRepository.findByUserUuid(userUuid, new PageRequest(0, Integer.MAX_VALUE));

		return orderMapper.ordersToBeans(orders, new CycleAvoidingMappingContext());

	}

	@Override
	public List<OrderBean> findByAgentUuid(String agentUuid) {

		List<Order> orders = orderRepository.findByAgentUuid(agentUuid, new PageRequest(0, Integer.MAX_VALUE));

		return orderMapper.ordersToBeans(orders, new CycleAvoidingMappingContext());

	}

}