package com.caldevsupplychain.account.security;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.caldevsupplychain.account.jwt.service.JwtService;
import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.service.PermissionService;
import com.caldevsupplychain.account.vo.UserBean;

@Slf4j
public class JwtRealm extends AuthorizingRealm {
	@Autowired
	private AccountService accountService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private JwtService jwtService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token != null && token instanceof JWTAuthenticationToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		JWTAuthenticationToken token = (JWTAuthenticationToken) authcToken;
		Optional<UserBean> user = accountService.findByUuid(token.getUuid());

		if (user.isPresent() && jwtService.verifyJwtToken(token.getToken())) {
			SimpleAccount account = new SimpleAccount(user.get(), token.getToken(), getName());
			account.addRole(user.get().getRoles().stream().map(r -> r.name()).collect(Collectors.toList()));
			return account;
		}
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals.fromRealm(getName()).isEmpty()) {
			return null;
		}
		UserBean user = ((UserBean) principals.getPrimaryPrincipal());
		if (user != null) {
			Set<String> roles = user.getRoles().stream().map(r -> r.toString()).collect(Collectors.toSet());
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
			info.setStringPermissions(permissionService.getPermissions(roles));
			return info;
		}
		return null;
	}
}
