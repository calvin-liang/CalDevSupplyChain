package com.caldevsupplychain.common.jwt.service;

import com.caldevsupplychain.common.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.common.jwt.vo.JwtBean;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;

import java.security.Key;

public interface JwtService {

	JWTAuthenticationToken createJwtToken(JwtBean jwtBean);

	void verifyJwtToken(String uuid, String jwtToken);

	HttpHeaders createJwtHeader(String jwtToken);

	String getAuthHeader();

}
