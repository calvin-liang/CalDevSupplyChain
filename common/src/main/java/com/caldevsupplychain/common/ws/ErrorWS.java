package com.caldevsupplychain.common.ws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorWS {
	private String field;
	private String code;
	private String message;

	public ErrorWS(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
