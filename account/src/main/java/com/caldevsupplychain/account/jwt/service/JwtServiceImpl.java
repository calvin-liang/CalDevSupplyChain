package com.caldevsupplychain.account.jwt.service;

import java.security.Key;
import java.util.Date;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.account.vo.UserBean;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Slf4j
@Service
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

	// generated pre-shared key
	Key key = MacProvider.generateKey();
	@Value("${jwt.header.issuer}")
	private String ISSUER;
	@Value("${jwt.header.token-prefix}")
	private String BEARER;
	@Value("${jwt.header.auth}")
	private String AUTH_HEADER;
	@Value("${jwt.header.expire-time-range}")
	private long EXPIRE_TIME_RANGE;

	public String getAuthHeader() {
		return AUTH_HEADER;
	}

	public JWTAuthenticationToken createJwtToken(UserBean userBean) {

		// @Reference: ref google auth api: https://developers.google.com/identity/protocols/OAuth2ServiceAccount
		String jwtToken = Jwts.builder()
				.setIssuer(ISSUER)
				.setSubject(userBean.getEmailAddress())
				.setIssuedAt(new Date())
				.setNotBefore(new Date())
				.setExpiration(new Date(new Date().getTime() + EXPIRE_TIME_RANGE))
				.setId(userBean.getUuid())
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();

		return new JWTAuthenticationToken(userBean.getUuid(), jwtToken);
	}

	public HttpHeaders createJwtHeader(String jwtToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTH_HEADER, BEARER + " " + jwtToken);
		headers.setAccessControlExposeHeaders(Lists.newArrayList(AUTH_HEADER));
		return headers;
	}

	public void verifyJwtToken(String uuid, String jwtTokenObj) throws JwtException {

		String jwtToken = jwtTokenObj.replace(BEARER, "");

		Claims claims = Jwts.parser()
				.requireId(uuid)
				.setSigningKey(key)
				.parseClaimsJws(jwtToken)
				.getBody();




		long currentTime = System.currentTimeMillis();
		boolean checkIntegrity = claims.getId().equals(uuid);
		boolean checkTimeRange = claims.getNotBefore().getTime() <= currentTime && currentTime < claims.getExpiration().getTime();

		if (!(checkIntegrity && checkTimeRange)) {
			throw new JwtException("JSON Web Token Authentication Fail.");
		}
	}
}
