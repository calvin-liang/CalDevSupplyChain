package com.caldevsupplychain.order.repository;

import com.caldevsupplychain.order.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Order, Long> {
	
}
