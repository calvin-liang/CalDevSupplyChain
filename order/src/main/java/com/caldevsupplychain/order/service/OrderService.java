package com.caldevsupplychain.order.service;

import java.util.List;
import java.util.Optional;

import com.caldevsupplychain.order.vo.OrderBean;

public interface OrderService {

	boolean orderExists(String uuid);

	OrderBean createOrder(OrderBean orderBean);

	OrderBean updateOrder(String orderUuid, OrderBean orderBean);

	void deleteOrder(Long id);

	Optional<OrderBean> getOrder(String uuid);

	Optional<OrderBean> findByUuid(String uuid);

	List<OrderBean> findByUserUuid(String userUuid);

	List<OrderBean> findByAgentUuid(String agentUuid);

}

