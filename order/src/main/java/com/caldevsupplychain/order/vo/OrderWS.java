package com.caldevsupplychain.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderWS implements Serializable {

	private static final long serialVersionUID = -8744630767752497964L;

	private String uuid;
	private String displayId;
	private Long id;
	private Long userId;
	private Long agentId;
	private String SKU;
	private OrderType orderType;
	private OrderStatus orderStatus;
	private List<ItemWS> items;
	private Currency currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;
}
