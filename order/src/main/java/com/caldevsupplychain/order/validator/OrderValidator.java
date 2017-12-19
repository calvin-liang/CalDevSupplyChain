package com.caldevsupplychain.order.validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.caldevsupplychain.account.util.ContextUtil;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.vo.ItemWS;
import com.caldevsupplychain.order.vo.OrderWS;

@Slf4j
@Component
public class OrderValidator implements Validator {

	@Autowired
	private ItemValidator itemValidator;
	@Autowired
	private ContextUtil contextUtil;

	@Override
	public boolean supports(Class clazz) {
		return OrderWS.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		OrderWS orderWS = (OrderWS) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalPrice", ErrorCode.INVALID_PAYLOAD.name(), "Order total price cannot be empty.");

		contextUtil.currentUser().ifPresent(u -> {
			if (orderWS.getTotalPrice() != null && !orderWS.getTotalPrice().equals(BigDecimal.ZERO)) {
				errors.rejectValue("totalPrice", ErrorCode.INVALID_PAYLOAD.name(), "User cannot set order price.");
			}
		});

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

	public void validateCreateOrder(Object o, Errors errors) {
		this.validate(o, errors);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userUuid", ErrorCode.INVALID_PAYLOAD.name(), "Order user uuid cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sku", ErrorCode.INVALID_PAYLOAD.name(), "Order SKU cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency", ErrorCode.INVALID_PAYLOAD.name(), "Order currency cannot be empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderType", ErrorCode.INVALID_PAYLOAD.name(), "Order type cannot be empty.");

		OrderWS orderWS = (OrderWS) o;
		if (orderWS.getItems() == null || orderWS.getItems().isEmpty()) {
			errors.rejectValue("items", ErrorCode.ITEMS_EMPTY.name(), "Item cannot empty.");
		}
		contextUtil.currentUser().ifPresent(u -> {
			if (!u.getUuid().equals(orderWS.getUserUuid())) {
				errors.rejectValue("userUuid", ErrorCode.INVALID_PAYLOAD.name(), "Cannot create order for other users.");
			}
		});
	}

	public void validateUpdateOrder(Object o, Errors errors) {
		this.validate(o, errors);
		OrderWS orderWS = (OrderWS) o;
		if (!StringUtils.isEmpty(orderWS.getUserUuid())) {
			errors.rejectValue("userUuid", ErrorCode.INVALID_PAYLOAD.name(), "Cannot update userUuid.");
		}
		if (!StringUtils.isEmpty(orderWS.getAgentUuid()) && !contextUtil.currentUser().get().isAgent()) {
			errors.rejectValue("agentUuid", ErrorCode.INVALID_PAYLOAD.name(), "Cannot update agentUuid");
		}
	}
}
