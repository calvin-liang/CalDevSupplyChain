package com.caldevsupplychain.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderWS implements Serializable {

	private static final long serialVersionUID = -8744630767752497964L;

	private String uuid;
	private Long order_id;
	private Long user_id;
	private Long agent_id;
	private String SKU;
	private List<ItemWS> items;
	private String currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;

}
