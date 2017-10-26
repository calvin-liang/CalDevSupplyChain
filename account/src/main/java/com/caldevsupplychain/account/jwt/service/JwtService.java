package com.caldevsupplychain.account.jwt.service;

import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.account.vo.UserBean;
import org.springframework.http.HttpHeaders;

public interface JwtService {

	JWTAuthenticationToken createJwtToken(UserBean userBean);

	void verifyJwtToken(String uuid, String jwtToken);

	HttpHeaders createJwtHeader(String jwtToken);

	String getAuthHeader();

}
