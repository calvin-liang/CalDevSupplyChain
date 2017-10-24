package com.caldevsupplychain.common.jwt.service;

import com.caldevsupplychain.common.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.common.jwt.vo.JwtBean;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Slf4j
@Service
@NoArgsConstructor
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.header.issuer}")
	private String ISSUER;
	@Value("${jwt.header.token-prefix}")
	private String BEARER;
	@Value("${jwt.header.auth}")
	private String AUTH_HEADER;
	@Value("${jwt.header.expire-time-range}")
	private long EXPIRE_TIME_RANGE;

	// generated pre-shared key
	Key key = MacProvider.generateKey();

	public String getAuthHeader(){
		return AUTH_HEADER;
	}

	public JWTAuthenticationToken createJwtToken(JwtBean jwtBean) {

		// @Reference: ref google auth api: https://developers.google.com/identity/protocols/OAuth2ServiceAccount
		String jwtToken =  Jwts.builder()
				.setIssuer(ISSUER)
				.setSubject(jwtBean.getSub())
				.setIssuedAt(new Date())
				.setNotBefore(new Date())
				.setExpiration(new Date(new Date().getTime() + EXPIRE_TIME_RANGE))
				.setId(jwtBean.getUuid())
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();

		return new JWTAuthenticationToken(jwtBean.getUuid(), jwtToken);
	}

	public HttpHeaders createJwtHeader(String jwtToken){
		HttpHeaders headers = new HttpHeaders();
		headers.set(AUTH_HEADER, BEARER + " " + jwtToken);
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

		if(!(checkIntegrity && checkTimeRange)) {
			throw new JwtException("JSON Web Token Authentication Fail.");
		}
	}
}