package com.caldevsupplychain.common.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailValidator {

	private final static String EMAIL_REGEX = "[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

	public boolean matchEmailPattern(String emailAddress) {
		return emailPattern.matcher(emailAddress).matches();
	}
}
