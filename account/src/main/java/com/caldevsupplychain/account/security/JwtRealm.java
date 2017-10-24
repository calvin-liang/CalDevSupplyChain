package com.caldevsupplychain.account.security;

import com.caldevsupplychain.account.model.User;
import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.util.UserMapper;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.jwt.service.JwtService;
import com.caldevsupplychain.common.jwt.token.JWTAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtRealm extends AuthorizingRealm  {


	@Autowired
	private AccountService accountService;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserMapper userMapper;

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		JWTAuthenticationToken jwtToken = (JWTAuthenticationToken) token;

		UserBean userBean = accountService.findByUuid(jwtToken.getUuid()).orElse(null);
		User user = userMapper.toUser(userBean);

		// TODO: if this not work change back verifyJwtToken return boolean type
//		if (user != null && jwtService.verifyJwtToken(user.getUuid(), jwtToken.getToken())) {
//			return new SimpleAuthenticationInfo(user.getUuid(), jwtToken.getToken() , getName());
//		}

		return null;
	}

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String uuid = (String) principals.fromRealm(getName()).iterator().next();
		UserBean userBean = accountService.findByUuid(uuid).orElse(null);

		User user = userMapper.toUser(userBean);

		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			user.getRoles().stream().distinct().forEach(role -> {
				info.addRole(role.getName().toString());
				info.addStringPermissions(
								role.getPermissions()
								.stream()
								.distinct()
								.map(p -> p.getName().toString())
								.collect(Collectors.toList())
				);

			});
			return info;
		}
		return null;
	}
}
