package com.caldevsupplychain.order.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.caldevsupplychain.order.model.Item;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
	Item findByUuid(String uuid);
}