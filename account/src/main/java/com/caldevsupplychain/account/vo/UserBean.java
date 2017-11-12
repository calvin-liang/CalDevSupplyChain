package com.caldevsupplychain.account.vo;

import java.util.List;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class UserBean {
	private Long id;
	private String uuid;
	private String username;
	private String emailAddress;
	private String password;
	private String token;
	private List<RoleName> roles;

	public boolean isAdmin() {
		return roles.contains(RoleName.ADMIN);
	}

	public boolean isAgent() {
		return roles.contains(RoleName.AGENT);
	}
}
