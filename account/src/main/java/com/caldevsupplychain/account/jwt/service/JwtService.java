package com.caldevsupplychain.account.jwt.service;

import org.springframework.http.HttpHeaders;

import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.account.vo.UserBean;

public interface JwtService {

	JWTAuthenticationToken createJwtToken(UserBean userBean);

	void verifyJwtToken(String uuid, String jwtToken);

	HttpHeaders createJwtHeader(String jwtToken);

	String getAuthHeader();

}
