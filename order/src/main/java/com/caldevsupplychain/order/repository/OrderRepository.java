package com.caldevsupplychain.order.repository;

import com.caldevsupplychain.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
	Order findByUuid(String uuid);
	List<Order> findByDisplayId(String displayId, Pageable pageable);
	List<Order> findByUserId(String userId, Pageable pageable);
	List<Order> findByAgentId(String agentId, Pageable pageable);
}
