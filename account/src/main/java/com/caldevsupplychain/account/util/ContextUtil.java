package com.caldevsupplychain.account.util;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import com.caldevsupplychain.account.vo.UserBean;

@Component
public class ContextUtil {
	public Optional<UserBean> currentUser() {
		return Optional.ofNullable((UserBean)SecurityUtils.getSubject().getPrincipal());
	}
}
