package com.caldevsupplychain.order.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderBean {

	private String uuid;
	private String displayId;
	private Long id;
	private String userUuid;
	private String agentUuid;
	private String sku;
	private OrderType orderType;
	private OrderStatus orderStatus;
	private List<ItemBean> items;
	private Currency currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;

}
