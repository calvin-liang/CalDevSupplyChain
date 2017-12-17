package com.caldevsupplychain.common.ws;

import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

import org.springframework.validation.BindingResult;

import com.caldevsupplychain.common.type.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorsWS implements Serializable {

	private List<ErrorWS> errors;

	public ApiErrorsWS(List<ErrorWS> errors) {
		this.errors = errors;
	}

	public ApiErrorsWS(String errorCode, String message) {
		this.errors = Lists.newArrayList(new ErrorWS(errorCode, message));
	}

	public ApiErrorsWS(ErrorCode errorCode, String message) {
		this.errors = Lists.newArrayList(new ErrorWS(errorCode.name(), message));
	}

	public ApiErrorsWS(BindingResult errors) {
		this.errors = errors.getFieldErrors()
			.stream()
			.map(error -> new ErrorWS(
				error.getField(),
				error.getCode(),
				error.getDefaultMessage()
			))
			.collect(toList());
	}
}
