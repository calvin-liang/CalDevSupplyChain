package com.caldevsupplychain.order.vo;

import com.caldevsupplychain.order.model.Quantity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemBean {

	private String uuid;
	private OrderBean order;
	private String color;
	private String description;
	private String fabric;
	private Quantity quantity;
	private BigDecimal price;
	private String note;


}
