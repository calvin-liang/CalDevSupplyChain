package com.caldevsupplychain.order.validator;

import java.math.BigDecimal;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.caldevsupplychain.account.util.ContextUtil;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.order.model.Quantity;
import com.caldevsupplychain.order.vo.ItemWS;

@Slf4j
@Component
public class ItemValidator implements Validator {

	@Autowired
	private ContextUtil contextUtil;

	@Override
	public boolean supports(Class clazz) {
		return ItemWS.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Optional<UserBean> userBean = contextUtil.currentUser();

		ItemWS itemWS = (ItemWS) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", ErrorCode.COLOR_EMPTY.name(), "Item color cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", ErrorCode.ITEM_DESCRIPTION.name(), "Item description cannot empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fabric", ErrorCode.FABRIC_EMPTY.name(), "Item fabric cannot empty");

		if (!Optional.ofNullable(itemWS.getQuantity()).isPresent()) {
			errors.rejectValue("quantity", ErrorCode.QUANTITY_EMPTY.name(), "Item quantity cannot empty");
		}

		if (itemWS.getPrice() == null) {
			errors.rejectValue("price", ErrorCode.ITEM_PRICE_EMPTY.name(), "Item price cannot be empty");
		}

		if (!contextUtil.currentUser().get().isAgent() && itemWS.getPrice() != null && itemWS.getPrice().compareTo(BigDecimal.ZERO) != 0) {
			errors.rejectValue("price", ErrorCode.PRICE_NOT_ZERO.name(), "User cannot set item price.");
		}

		if (quantityAllZero(itemWS.getQuantity())) {
			errors.rejectValue("quantity", ErrorCode.QUANTITY_SIZE_EMPTY.name(), "Need to have at least one item quantity size");
		}
	}

	public void validateCreateItem(Object o, Errors errors) {
		this.validate(o, errors);
	}

	private boolean quantityAllZero(Quantity quantity) {
		if (quantity == null) {
			return false;
		}
		Integer XS = quantity.getXS() == null ? 0 : quantity.getXS();
		Integer S = quantity.getS() == null ? 0 : quantity.getS();
		Integer M = quantity.getM() == null ? 0 : quantity.getM();
		Integer L = quantity.getL() == null ? 0 : quantity.getL();

		return XS == 0 && S == 0 && M == 0 && L == 0;
	}
}

