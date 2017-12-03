package com.caldevsupplychain.account.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.caldevsupplychain.account.jwt.service.JwtService;
import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;

@Slf4j
public class JwtAuthenticationFilter extends AuthenticatingFilter {
	private JwtService jwtService;

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static String AUTH_SCHEMA = "Bearer";

	@Autowired
	public JwtAuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		return false;
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		String token = getAuthzHeader(request);
		if(token == null || !isJwtToken(token)) {
			return false;
		}
		String userUuid = jwtService.getSubject(token);
		if (userUuid == null) {
			return false;
		}
		JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(userUuid, token);
		SecurityUtils.getSubject().login(jwtAuthenticationToken);
		return true;
	}

	private String getAuthzHeader(ServletRequest request) {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		return httpRequest.getHeader(AUTHORIZATION_HEADER);
	}

	private boolean isJwtToken(String token) {
		return token.startsWith(AUTH_SCHEMA);
	}
}
