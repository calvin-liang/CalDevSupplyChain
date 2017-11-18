package com.caldevsupplychain.account.util;

import java.util.Optional;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.vo.UserBean;

@Component
public class ContextUtil {

	@Autowired
	AccountService accountService;

	public Optional<UserBean> currentUser() {
		Subject subject  = SecurityUtils.getSubject();
		return accountService.findByUuid((String)subject.getPrincipal());
	}

}
