package com.caldevsupplychain.account.controller;

import org.springframework.http.ResponseEntity;

import com.caldevsupplychain.account.vo.UserWS;

public interface AccountController {

	ResponseEntity<?> signup(String role, UserWS userWS);

	ResponseEntity<?> activateAccount(String token);

	ResponseEntity<?> issueToken();

	ResponseEntity<?> forgotPassword(UserWS userWS);

	ResponseEntity<?> updateUser(String uuid, UserWS userWS);
}
