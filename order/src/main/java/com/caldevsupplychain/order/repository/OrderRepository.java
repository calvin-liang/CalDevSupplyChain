package com.caldevsupplychain.order.repository;

import com.caldevsupplychain.order.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
	Order findByUuid(String uuid);

	List<> findBy

}
