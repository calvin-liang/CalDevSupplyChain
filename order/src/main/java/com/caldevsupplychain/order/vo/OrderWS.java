package com.caldevsupplychain.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderWS implements Serializable {

	private static final long serialVersionUID = -8744630767752497964L;

	private String uuid;
	private String displayId;
	private String userUuid;
	private String agentUuid;
	private String sku;
	private OrderType orderType;
	private OrderStatus orderStatus;
	private List<ItemWS> items = Lists.newArrayList();
	private Currency currency;
	private BigDecimal totalPrice;
	private String shippingInstruction;
	private String orderNote;

}
