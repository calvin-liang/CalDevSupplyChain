package com.caldevsupplychain.order.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "XS", "S", "M", "L" })
public class Quantity {

	private Integer XS;
	private Integer S;
	private Integer M;
	private Integer L;

	@JsonProperty("XS")
	public void setXS(Integer XS) {
		this.XS = XS;
	}

	@JsonProperty("S")
	public void setS(Integer S) {
		this.S = S;
	}

	@JsonProperty("M")
	public void setM(Integer M) {
		this.M = M;
	}

	@JsonProperty("L")
	public void setL(Integer L) {
		this.L = L;
	}

}