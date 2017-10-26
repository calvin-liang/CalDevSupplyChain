package com.caldevsupplychain.account.controller;

import com.caldevsupplychain.account.vo.UserWS;
import org.springframework.http.ResponseEntity;

public interface AccountController {

	ResponseEntity<?> signup(String role, UserWS userWS);

	ResponseEntity<?> activateAccount(String token);

	ResponseEntity<?> issueToken();

	ResponseEntity<?> forgotPassword(UserWS userWS);

	ResponseEntity<?> resetPassword(String uuid, UserWS userWS);

	ResponseEntity<?> updateUser(String uuid, UserWS userWS);

	ResponseEntity<?> getAllUsers();

}
