package com.caldevsupplychain.account.jwt.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
@AllArgsConstructor
public class JWTAuthenticationToken implements AuthenticationToken {

	private String uuid;
	private String token;

	@Override
	public Object getPrincipal() {
		return getUuid();
	}

	@Override
	public Object getCredentials() {
		return getToken();
	}
}
