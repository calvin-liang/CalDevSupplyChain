package com.caldevsupplychain.order.service;

public interface ItemService {
	boolean orderItemExists(String orderUuid, String itemUuid);
}
