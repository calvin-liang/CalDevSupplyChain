package com.caldevsupplychain.common.ws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorWS {
	private String field;
	private String code;
	private String message;

	public ErrorWS(String code, String message) {
		this.code = code;
		this.message = code;
	}
}
