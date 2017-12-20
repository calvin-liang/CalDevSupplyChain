package com.caldevsupplychain.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.caldevsupplychain.order.repository.ItemRepository;

@Component
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemRepository itemRepository;

	@Override
	@Transactional(readOnly = true)
	public boolean orderItemExists(String orderUuid, String itemUuid) {
		return itemRepository.findByOrderUuidAndUuid(orderUuid, itemUuid) != null;
	}
}
