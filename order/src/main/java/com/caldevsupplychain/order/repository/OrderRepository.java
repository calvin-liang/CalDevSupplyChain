package com.caldevsupplychain.order.repository;

import com.caldevsupplychain.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	Order findByUuid(String uuid);

	List<Order> findByUserUuid(String userUuid, Pageable pageable);

	List<Order> findByAgentUuid(String agentUuid, Pageable pageable);

}