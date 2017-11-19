package com.caldevsupplychain.order.repository;

import com.caldevsupplychain.order.model.Order;
import com.caldevsupplychain.order.vo.OrderStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	Order findByUuid(String uuid);

	@Query("select o from Order o where o.uuid = ?1 and o.orderStatus <> 'DELETED'")
	Order findByUuidAndOrderStatusNotDeleted(String uuid);

	@Query(value = "select * from orders where user_uuid = ?1 and order_status <> 'DELETED' order by ?#{#pageable}",
			countQuery = "select count(*) from orders where user_uuid = ?1 and order_status <> 'DELETED' order by ?#{#pageable}",
			nativeQuery = true
	)
	List<Order> findByUserUuid(String userUuid, Pageable pageable);

	@Query(value = "select * from orders where agent_uuid = ?1 and order_status <> 'DELETED' order by ?#{#pageable}",
			countQuery = "select count(*) from orders where agent_uuid = ?1 and order_status <> 'DELETED' order by ?#{#pageable}",
			nativeQuery = true
	)
	List<Order> findByAgentUuid(String agentUuid, Pageable pageable);

}