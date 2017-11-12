package com.caldevsupplychain.order.service;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.repository.OrderRepository;
import com.caldevsupplychain.order.util.CycleAvoidingMappingContext;
import com.caldevsupplychain.order.util.ItemMapper;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.vo.OrderBean;
import com.caldevsupplychain.order.vo.OrderStatus;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderMapper orderMapper;
	private ItemMapper itemMapper;

	@Override
	@Transactional
	public OrderBean createOrder(OrderBean orderBean) {

		Preconditions.checkState(orderBean != null, "Must have orderBean in creating order");

		Order order = orderMapper.toOrder(orderBean, new CycleAvoidingMappingContext());

		orderRepository.save(order);

		return orderMapper.toBean(order, new CycleAvoidingMappingContext());
	}

	@Override
	@Transactional
	public OrderBean updateOrder(OrderBean orderBean) {

		Order order = orderRepository.findOne(orderBean.getId());

		Preconditions.checkState(orderBean != null, ErrorCode.ORDER_ID_NOT_FOUND);

		Optional.ofNullable(orderBean.getTotalPrice()).ifPresent(totalPrice -> order.setTotalPrice(totalPrice));

		Optional.ofNullable(orderBean.getOrderType()).ifPresent(orderType -> order.setOrderType(orderType));

		Optional.ofNullable(orderBean.getOrderStatus()).ifPresent(orderStatus -> order.setOrderStatus(orderStatus));

		Optional.ofNullable(orderBean.getItems()).ifPresent(itemBeans -> order.setItems(itemMapper.toItemList(itemBeans)));

		return orderMapper.toBean(order, new CycleAvoidingMappingContext());
	}

	@Override
	@Transactional
	public OrderBean deleteOrder(OrderBean orderBean) {

		Order order = orderRepository.findOne(orderBean.getId());

		Preconditions.checkState(orderBean != null, ErrorCode.ORDER_ID_NOT_FOUND);

		order.setOrderStatus(OrderStatus.DELETED);

		return orderMapper.toBean(order, new CycleAvoidingMappingContext());
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
	public Optional<List<OrderBean>> findByDisplayId(String displayId) {
		List<Order> orders = orderRepository.findByDisplayId(displayId, new PageRequest(0, Integer.MAX_VALUE));
		if(orders != null){
			return Optional.of(orderMapper.ordersToBeans(orders));
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<OrderBean>> findByUserId(String userId) {
		List<Order> orders = orderRepository.findByUserId(userId, new PageRequest(0, Integer.MAX_VALUE));

		if(orders != null){
			return Optional.of(orderMapper.ordersToBeans(orders));
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<OrderBean>> findByAgentId(String agentId) {
		List<Order> orders = orderRepository.findByAgentId(agentId, new PageRequest(0, Integer.MAX_VALUE));

		if(orders != null){
			return Optional.of(orderMapper.ordersToBeans(orders));
		}
		return Optional.empty();
	}

	@Override
	public List<OrderBean> getAllOrders() {
		Page<Order> orders = orderRepository.findAll(new PageRequest(0, Integer.MAX_VALUE));
		return orderMapper.ordersToBeans(orders.getContent());
	}

}
