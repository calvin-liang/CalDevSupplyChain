package com.caldevsupplychain.common.type;

public enum ErrorCode {

	/* Shiro exception error code */
	UNAUTHENTICATION,
	UNAUTHORIZATION,

	/* Jwt error code */
	JWT_EXCEPTION,

	/* Account errors */
	ACCOUNT_EXIST,
	ACCOUNT_NOT_EXIST,

	LOGIN_INVALID,
	LOGOUT_INVALID,

	USERNAME_EMPTY,
	USER_NOT_FOUND,

	PASSWORD_EMPTY,

	EMAIL_EMPTY,
	EMAIL_INVALID,
	EMAIL_MESSAGING_EXCEPTION,

	INVALID_TOKEN,

	PERMISSION_DENIED_ON_EMPTY_EMAIL_UPDATE,
	PERMISSION_DENIED_ON_USER_UPDATE,

	/* order errors */
	ORDER_ID_NOT_FOUND,
	USER_ID_NOT_FOUND,
	AGENT_ID_NOT_FOUND,
	USER_ID_EMPTY,
	AGENT_ID_EMPTY,
	SKU_EMPTY,
	CURRENCY_EMPTY,
	TOTAL_PRICE_EMPTY,
	TOTAL_PRICE_ZERO,
	ITEMS_EMPTY,
	COLOR_EMPTY,
	ITEM_DESCRIPTION,
	FABRIC_EMPTY,
	QUANTITY_EMPTY,
	QUANTITY_SIZE_EMPTY,
	ITEM_PRICE_EMPTY,
	ITEM_PRICE_ZERO,
}
