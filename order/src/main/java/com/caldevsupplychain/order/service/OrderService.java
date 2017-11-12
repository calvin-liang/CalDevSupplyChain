package com.caldevsupplychain.order.service;

import java.util.List;
import java.util.Optional;

import com.caldevsupplychain.order.vo.OrderBean;

public interface OrderService {

	OrderBean createOrder(OrderBean orderBean);

	OrderBean updateOrder(OrderBean orderBean);

	void deleteOrder(OrderBean orderBean);

	Optional<OrderBean> findByUuid(String uuid);

	Optional<List<OrderBean>> findByUserUuid(String userUuid);

	Optional<List<OrderBean>> findByAgentUuid(String agentUuid);

}

