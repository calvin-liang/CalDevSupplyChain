package com.caldevsupplychain.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderBean {

	private String uuid;
	private Long orderId;
	private Long ownerId;
	private Long userId;
	private Long agentId;
	private String SKU;
	private List<ItemBean> items;
	private Currency currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;

}