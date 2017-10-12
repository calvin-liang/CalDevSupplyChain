package com.caldevsupplychain.account.vo;

public enum PermissionName {
	ACCOUNT_READ("account:read"),
	ACCOUNT_UPDATE("account:update"),
	ACCOUNT_ADMIN("account:admin");

	private final String name;

	PermissionName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
