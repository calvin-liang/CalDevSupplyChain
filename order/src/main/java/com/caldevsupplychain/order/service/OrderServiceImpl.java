package com.caldevsupplychain.order.service;

import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.repository.OrderRepository;
import com.caldevsupplychain.order.util.CycleAvoidingMappingContext;
import com.caldevsupplychain.order.util.OrderMapper;
import com.caldevsupplychain.order.vo.OrderBean;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderMapper orderMapper;

	@Override
	@Transactional
	public OrderBean createOrder(OrderBean orderBean) {

		Preconditions.checkState(orderBean != null, "OrderBean is null");

		Order order = orderMapper.toOrder(orderBean, new CycleAvoidingMappingContext());

		orderRepository.save(order);

		return orderMapper.toBean(order, new CycleAvoidingMappingContext());

	}

}
