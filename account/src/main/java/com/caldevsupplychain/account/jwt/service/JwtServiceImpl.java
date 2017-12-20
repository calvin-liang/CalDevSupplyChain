package com.caldevsupplychain.account.jwt.service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

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
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Slf4j
@Service("jwtService")
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static String AUTH_SCHEMA = "Bearer";

	// generated pre-shared key
	private Key key = MacProvider.generateKey();

	@Value("${jwt.header.issuer}")
	private String ISSUER;
	@Value("${jwt.header.expire-time-range}")
	private long EXPIRE_TIME_RANGE;

	@Override
	public JWTAuthenticationToken createJwtToken(UserBean userBean) {

		// @Reference: ref google auth api: https://developers.google.com/identity/protocols/OAuth2ServiceAccount
		String jwtToken = Jwts.builder()
				.setIssuer(ISSUER)
				.setSubject(userBean.getUuid())
				.setIssuedAt(new Date())
				.setNotBefore(new Date())
				.setExpiration(new Date(new Date().getTime() + EXPIRE_TIME_RANGE))
				.setId(UUID.randomUUID().toString())
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();

		return new JWTAuthenticationToken(userBean.getUuid(), jwtToken);
	}

	@Override
	public HttpHeaders createJwtHeader(String jwtToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTHORIZATION_HEADER, AUTH_SCHEMA + " " + jwtToken);
		headers.setAccessControlExposeHeaders(Lists.newArrayList(AUTHORIZATION_HEADER));
		return headers;
	}

	@Override
	public boolean verifyJwtToken(String jwtTokenObj) {
		String jwtToken = jwtTokenObj.replace(AUTH_SCHEMA, "");

		Claims claims = null;
		try {
			claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwtToken)
				.getBody();
		} catch (Exception e) {
			return false;
		}

		long currentTime = System.currentTimeMillis();
		return claims.getNotBefore().getTime() <= currentTime && currentTime < claims.getExpiration().getTime();
	}

	/**
	 * Return user uuid as subject if this is a valida JWT token.
	 * @param token
	 * @return null if the jwt token is not valid
	 */
	@Override
	public String getSubject(String token) {
		token = token.replace(AUTH_SCHEMA, "");
		try {
			return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		} catch (RuntimeException e) {
			log.warn("Unable to parse JWT token: {}", e.getMessage());
			return null;
		}
	}
}
