package com.caldevsupplychain.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({ "XS", "S", "M", "L" })
public class Quantity {

	@JsonProperty("XS")
	private BigDecimal XS;

	@JsonProperty("S")
	private BigDecimal S;

	@JsonProperty("M")
	private BigDecimal M;

	@JsonProperty("L")
	private BigDecimal L;

	@JsonProperty("XS")
	public void setXS(BigDecimal XS) {
		this.XS = convertToTwoDecimal(XS);
	}

	@JsonProperty("S")
	public void setS(BigDecimal S) {
		this.S = convertToTwoDecimal(S);
	}

	@JsonProperty("M")
	public void setM(BigDecimal M) {
		this.M = convertToTwoDecimal(M);
	}

	@JsonProperty("L")
	public void setL(BigDecimal L) {
		this.L = convertToTwoDecimal(L);
	}

	public BigDecimal convertToTwoDecimal(BigDecimal value){
		BigDecimal convertedVal = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		return convertedVal;
	}


//	public String toJSONObject(){
//		ObjectMapper mapper = new ObjectMapper();
//		ObjectNode jsonNode = mapper.createObjectNode();
//		jsonNode.put()
//	}

}
