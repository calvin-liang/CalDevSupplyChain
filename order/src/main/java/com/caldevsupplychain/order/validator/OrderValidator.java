package com.caldevsupplychain.order.validator;

import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.vo.ItemWS;
import com.caldevsupplychain.order.vo.OrderWS;

@Slf4j
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userUuid", ErrorCode.USER_UUID_EMPTY.name(), "Order user uuid cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "agentUuid", ErrorCode.AGENT_UUID_EMPTY.name(), "Order agent uuid cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sku", ErrorCode.SKU_EMPTY.name(), "Order SKU cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency", ErrorCode.CURRENCY_EMPTY.name(), "Order currency cannot empty");

		if (orderWS.getTotalPrice() == null) {
			errors.rejectValue("totalPrice", ErrorCode.ORDER_TOTAL_PRICE_EMPTY.name(), "Order total price cannot be empty.");
		}

		if (orderWS.getItems() == null) {
			errors.rejectValue("items", ErrorCode.ITEMS_EMPTY.name(), "Item cannot empty");
		}

		List<ItemWS> itemWSList = orderWS.getItems();

		if (itemWSList != null) {
			IntStream.range(0, itemWSList.size())
					.forEach(idx -> {
						ItemWS itemWS = itemWSList.get(idx);
						try {
							errors.pushNestedPath("items[" + idx + "]");
							ValidationUtils.invokeValidator(itemValidator, itemWS, errors);
						} finally {
							errors.popNestedPath();

						}
					});
		}
	}

}