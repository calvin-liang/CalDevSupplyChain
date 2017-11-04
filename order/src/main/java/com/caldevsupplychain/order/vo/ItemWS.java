package com.caldevsupplychain.order.vo;

import com.caldevsupplychain.order.model.Quantity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ItemWS implements Serializable {

	private static final long serialVersionUID = -1683616977939567855L;

	private String uuid;
	private String color;
	private String description;
	private String fabric;
	private Quantity quantity;
	private BigDecimal price;
	private String priceNote;

}
