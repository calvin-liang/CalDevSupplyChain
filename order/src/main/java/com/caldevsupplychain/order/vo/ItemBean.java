package com.caldevsupplychain.order.vo;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

import com.caldevsupplychain.order.model.Quantity;

@Data
@ToString(exclude = "order")
public class ItemBean {

	private String uuid;
	private String displayId;
	private OrderBean order;
	private String color;
	private String description;
	private String fabric;
	private Quantity quantity;
	private BigDecimal price;
	private String note;

}
