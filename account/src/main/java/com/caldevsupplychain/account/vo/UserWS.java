package com.caldevsupplychain.account.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class UserWS implements Serializable {

	private static final long serialVersionUID = 1110144298076663428L;

	private String uuid;
	private String username;
	private String emailAddress;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String companyName;
	private List<String> roles;
}

