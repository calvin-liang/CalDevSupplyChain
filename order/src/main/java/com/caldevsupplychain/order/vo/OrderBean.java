package com.caldevsupplychain.order.vo;

import java.math.BigDecimal;
import java.util.List;

public class OrderBean {

	private String uuid;
	private Long orderId;
	private Long userId;
	private Long agentId;
	private String SKU;
	private List<ItemBean> items;
	private String currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;

}
