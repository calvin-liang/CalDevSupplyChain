package com.caldevsupplychain.order.service;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.repository.OrderRepository;
import com.caldevsupplychain.order.vo.OrderBean;
import com.google.common.base.Preconditions;

public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderMapper orderMapper;


	@Override
	public OrderBean createOrder(OrderBean orderBean) {

		Preconditions.checkState(orderBean != null, "OrderBean is null");




	}
}
