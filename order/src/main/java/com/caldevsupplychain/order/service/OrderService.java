package com.caldevsupplychain.order.service;

import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.vo.OrderBean;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {

	OrderBean createOrder(OrderBean orderBean);

	OrderBean updateOrder(OrderBean orderBean);

	OrderBean deleteOrder(OrderBean orderBean);

	Optional<OrderBean> findByUuid(String uuid);

	List<OrderBean> getAllOrders();

	Optional<List<OrderBean>> findByDisplayId(String displayId);

	Optional<List<OrderBean>> findByUserId(String userId);

	Optional<List<OrderBean>> findByAgentId(String agentId);

}
