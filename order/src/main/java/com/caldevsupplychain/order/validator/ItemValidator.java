package com.caldevsupplychain.order.validator;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.model.Quantity;
import com.caldevsupplychain.order.vo.ItemWS;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class ItemValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return ItemWS.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ItemWS itemWS = (ItemWS) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", ErrorCode.COLOR_EMPTY.name(), "Item color cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", ErrorCode.ITEM_DESCRIPTION.name(), "Item description cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fabric", ErrorCode.FABRIC_EMPTY.name(), "Item fabric cannot empty");

		if(!Optional.ofNullable(itemWS.getQuantity()).isPresent()){
			errors.rejectValue("quantity", ErrorCode.QUANTITY_EMPTY.name(), "Item quantity cannot empty");
		}

		if(quantityAllZero(itemWS.getQuantity())){
			errors.rejectValue("quantity", ErrorCode.QUANTITY_SIZE_EMPTY.name(), "Item quantity size cannot empty");
		}

		if(!Optional.ofNullable(itemWS.getPrice()).isPresent()){
			errors.rejectValue("price", ErrorCode.ITEM_PRICE_EMPTY.name(), "Item price cannot empty");
		}

		if(itemWS.getPrice().compareTo(BigDecimal.ZERO) == 0){
			errors.rejectValue("price", ErrorCode.ITEM_PRICE_ZERO.name(), "Item price cannot be zero");
		}
	}

	public boolean quantityAllZero(Quantity quantity){
		boolean allZero = false;
		Integer XS = quantity.getXS();
		Integer S = quantity.getS();
		Integer M = quantity.getM();
		Integer L = quantity.getL();

		if(XS == 0 && S == 0 && M == 0 && L == 0) {
			allZero = true;
		}
		return allZero;
	}

}


