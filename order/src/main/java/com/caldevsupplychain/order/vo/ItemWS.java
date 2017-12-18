package com.caldevsupplychain.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

import com.caldevsupplychain.order.model.Quantity;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ItemWS implements Serializable {

	private static final long serialVersionUID = -1683616977939567855L;

	private String uuid;
	private String displayId;
	private String color;
	private String description;
	private String fabric;
	private Quantity quantity;
	private BigDecimal price;
	private String note;

}
