package com.caldevsupplychain.account.vo;

public enum PermissionName {

	ACCOUNT_READ("account:read"),
	ACCOUNT_UPDATE("account:update"),
	ACCOUNT_ADMIN("account:admin"),

	ORDER_CREATE("order:create"),
	ORDER_UPDATE("order:update"),
	ORDER_READ("order:read");

	private final String name;

	PermissionName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
