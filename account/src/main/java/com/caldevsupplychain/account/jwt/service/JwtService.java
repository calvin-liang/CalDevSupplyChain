package com.caldevsupplychain.account.jwt.service;

import org.springframework.http.HttpHeaders;

import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.account.vo.UserBean;

public interface JwtService {

	JWTAuthenticationToken createJwtToken(UserBean userBean);

	boolean verifyJwtToken(String jwtToken);

	String getSubject(String jwtToken);

	HttpHeaders createJwtHeader(String jwtToken);
}
