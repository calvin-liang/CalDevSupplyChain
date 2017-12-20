package com.caldevsupplychain.account.util;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.caldevsupplychain.account.vo.UserBean;

@Component
public class ContextUtil {
	/**
	 * For authenticated API call, current user will always present.
	 * For un-authenticated API call(pubic API calls), current user might not present (anonymous user).
	 * @return
	 */
	public Optional<UserBean> currentUser() {
		return Optional.ofNullable((UserBean)SecurityUtils.getSubject().getPrincipal());
	}
}
