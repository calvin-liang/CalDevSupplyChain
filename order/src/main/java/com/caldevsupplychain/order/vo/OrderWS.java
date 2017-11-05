package com.caldevsupplychain.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderWS implements Serializable {

	private static final long serialVersionUID = -8744630767752497964L;

	private String uuid;
	private Long orderId;
	private Long ownerId;
	private Long userId;
	private Long agentId;
	private String SKU;
	private List<ItemWS> items;
	private Currency currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;

}
