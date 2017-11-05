package com.caldevsupplychain.order.validator;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.vo.OrderWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class OrderValidator implements Validator {

	@Autowired
	private ItemValidator itemValidator;

	@Override
	public boolean supports(Class clazz) {
		return OrderWS.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		OrderWS orderWS = (OrderWS) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_id", ErrorCode.USER_ID_EMPTY.name(), "Order user id cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agent_id", ErrorCode.AGENT_ID_EMPTY.name(), "Order agent id cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "SKU", ErrorCode.SKU_EMPTY.name(), "Order SKU cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency", ErrorCode.CURRENCY_EMPTY.name(), "Order currency cannot empty");

		if(!Optional.ofNullable(orderWS.getTotalPrice()).isPresent()) {
			errors.rejectValue("totalPrice", ErrorCode.TOTAL_PRICE_EMPTY.name(), "Order total price cannot empty");
		}

		if(orderWS.getTotalPrice().compareTo(BigDecimal.ZERO) == 0) {
			errors.rejectValue("totalPrice", ErrorCode.TOTAL_PRICE_ZERO.name(), "Order total price cannot be zero");
		}

		/* item check */
		if(orderWS.getItems().isEmpty()){
			errors.rejectValue("items", ErrorCode.ITEMS_EMPTY.name(), "Item cannot empty");
		}
		orderWS.getItems().stream().forEach(item -> itemValidator.validate(item, errors));
	}
}


