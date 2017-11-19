package com.caldevsupplychain.account.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caldevsupplychain.account.annotation.RequiresJwtAuthentication;
import com.caldevsupplychain.account.jwt.service.JwtService;
import com.caldevsupplychain.account.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.util.ContextUtil;
import com.caldevsupplychain.account.util.UserMapper;
import com.caldevsupplychain.account.validator.EditUserValidator;
import com.caldevsupplychain.account.validator.SignupValidator;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.account.vo.UserWS;
import com.caldevsupplychain.common.exception.ApiErrorsExceptionHandler;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.common.ws.ApiErrorsWS;
import com.caldevsupplychain.common.ws.ErrorWS;
import com.caldevsupplychain.notification.mail.service.EmailService;
import com.caldevsupplychain.notification.mail.type.EmailType;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value = "/api/v1/account", description = "Account API")
@RequestMapping("/api/v1/account")
public class AccountControllerImpl implements AccountController {

	private ContextUtil contextUtil;

	private AccountService accountService;
	private EmailService emailService;
	private JwtService jwtService;

	private UserMapper userMapper;

	private SignupValidator signupValidator;
	private EditUserValidator editUserValidator;

	private ApiErrorsExceptionHandler apiErrorsExceptionHandler;

	/************************************************************************************************
	 |									Account API													|
	 ************************************************************************************************/
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestParam(required = false, defaultValue = "USER") String role, @Validated @RequestBody UserWS userWS) {
		BindException errors = new BindException(userWS, "UserWS");

		signupValidator.validate(userWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in signup user. Fail in signup validation fields. userWS={}", userWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		userWS.setRoles(Lists.newArrayList(role));

		if (accountService.userExist(userWS.getEmailAddress())) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_EXIST.name(), "Account already registered."), HttpStatus.CONFLICT);
		}

		UserBean userBean = userMapper.toBean(userWS);

		UserBean user = accountService.createUser(userBean);

		try {
			emailService.sendVerificationTokenEmail(user.getEmailAddress(), user.getToken(), EmailType.ACTIVATION.name());
		} catch (MessagingException e) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.EMAIL_MESSAGING_EXCEPTION.name(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(userMapper.toWS(user), HttpStatus.CREATED);
	}

	@GetMapping("/activate/{token}")
	public ResponseEntity<?> activateAccount(@PathVariable("token") String token) {
		Optional<UserBean> user = accountService.findByToken(token);

		if (!user.isPresent()) {
			return new ResponseEntity<Object>(new ApiErrorsWS(ErrorCode.INVALID_TOKEN.name(), "Invalid Token."), HttpStatus.BAD_REQUEST);
		}
		accountService.activateUser(user.get().getId());

		return new ResponseEntity<>(userMapper.toWS(user.get()), HttpStatus.OK);
	}

	@PostMapping("/issue-token")
	@RequiresAuthentication
	public ResponseEntity<?> issueToken() {

		Optional<UserBean> userBean = contextUtil.currentUser();

		if (!userBean.isPresent()) {
			log.error("Current user not found");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_NOT_FOUND.name(), "Cannot find current user"), HttpStatus.NOT_FOUND);
		}

		UserBean user = userBean.get();

		JWTAuthenticationToken jwtToken = jwtService.createJwtToken(user);

		log.debug("Success create jwt token. jwtToken={}", jwtToken.getToken());

		return new ResponseEntity<>(jwtService.createJwtHeader(jwtToken.getToken()), HttpStatus.OK);
	}

	@PostMapping("/users/forgot-password")
	public ResponseEntity<?> forgotPassword(@Validated @RequestBody UserWS userWS) {

		Optional<UserBean> user = accountService.findByEmailAddress(userWS.getEmailAddress());

		if (!user.isPresent()) {
			log.error("Error in user forgot password. Fail in finding user's email={}", userWS.getEmailAddress());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user email address."), HttpStatus.NOT_FOUND);
		}

		UserBean userBean = user.get();

		userBean.setToken(UUID.randomUUID().toString());

		UserBean updatedUser = accountService.updateUser(userBean);

		try {
			emailService.sendVerificationTokenEmail(updatedUser.getEmailAddress(), updatedUser.getToken(), EmailType.FORGOT_PASSWORD.name());
		} catch (MessagingException e) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.EMAIL_MESSAGING_EXCEPTION.name(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(userMapper.toWS(userBean), HttpStatus.OK);
	}

	@PutMapping("/users/{uuid}")
	@RequiresJwtAuthentication
	@RequiresPermissions("account:update")
	public ResponseEntity<?> updateUser(@PathVariable("uuid") String uuid, @Validated @RequestBody UserWS userWS) {
		BindException errors = new BindException(userWS, "UserWS");

		editUserValidator.validate(userWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in update user. Fail in edit user validation fields. userWS={}", userWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		if (!accountService.findByUuid(uuid).isPresent()) {
			log.error("Error in update user. Fail in finding user's uuid={}", uuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user account."), HttpStatus.NOT_FOUND);
		}

		Optional<UserBean> currentUser = contextUtil.currentUser();

		if (!currentUser.isPresent()) {
			log.error("Current user not found");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.USER_NOT_FOUND.name(), "Cannot find current user"), HttpStatus.NOT_FOUND);
		}

		if (!currentUser.get().isAdmin()) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.PERMISSION_DENIED_ON_USER_UPDATE.name(), "Cannot update user information"), HttpStatus.BAD_REQUEST);
		}

		if (!StringUtils.isNotBlank(userWS.getEmailAddress())) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.PERMISSION_DENIED_ON_EMPTY_EMAIL_UPDATE.name(), "User cannot update email address"), HttpStatus.BAD_REQUEST);
		}

		UserBean userBean = userMapper.toBean(userWS);

		UserBean updatedUser = accountService.updateUser(userBean);

		log.info("Success in update user={}", updatedUser.toString());

		return new ResponseEntity<>(userMapper.toWS(updatedUser), HttpStatus.OK);
	}

	@GetMapping("/users")
	@RequiresJwtAuthentication
	@RequiresPermissions("account:admin")
	public ResponseEntity<?> getAllUsers() {
		List<UserBean> userBeans = accountService.getAllUsers();
		return new ResponseEntity<Object>(userMapper.toWss(userBeans), HttpStatus.OK);
	}

}
