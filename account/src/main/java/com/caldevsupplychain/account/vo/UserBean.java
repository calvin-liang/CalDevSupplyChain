package com.caldevsupplychain.account.vo;

import java.util.List;

import lombok.Data;

@Data
public class UserBean {
	private Long id;
	private String uuid;
	private String username;
	private String emailAddress;
	private String password;
	private String token;
	private String companyName;
	private List<RoleName> roles;

	public boolean isUser() {
		return roles.contains(RoleName.USER);
	}

	public boolean isAdmin() {
		return roles.contains(RoleName.ADMIN);
	}

	public boolean isAgent() {
		return roles.contains(RoleName.AGENT);
	}
}
