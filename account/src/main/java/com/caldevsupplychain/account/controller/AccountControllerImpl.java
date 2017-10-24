package com.caldevsupplychain.account.controller;

import com.caldevsupplychain.account.service.AccountService;
import com.caldevsupplychain.account.util.JwtMapper;
import com.caldevsupplychain.account.util.UserMapper;
import com.caldevsupplychain.account.validator.EditUserValidator;
import com.caldevsupplychain.account.validator.SignupValidator;
import com.caldevsupplychain.account.vo.UserBean;
import com.caldevsupplychain.common.annotation.RequiresJwtAuthentication;
import com.caldevsupplychain.common.exception.ApiErrorsExceptionHandler;
import com.caldevsupplychain.common.jwt.service.JwtService;
import com.caldevsupplychain.common.jwt.token.JWTAuthenticationToken;
import com.caldevsupplychain.common.jwt.vo.JwtBean;
import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.common.ws.account.ApiErrorsWS;
import com.caldevsupplychain.common.ws.account.ErrorWS;
import com.caldevsupplychain.common.ws.account.UserWS;
import com.caldevsupplychain.notification.mail.service.EmailService;
import com.caldevsupplychain.notification.mail.type.EmailType;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value="/api/account/v1", description = "Account API")
@RequestMapping("/api/account/v1")
public class AccountControllerImpl implements AccountController {

	private AccountService accountService;
	private EmailService emailService;
	private JwtService jwtService;

	private UserMapper userMapper;
	private JwtMapper jwtMapper;

	private SignupValidator signupValidator;
	private EditUserValidator editUserValidator;

	/* exception handler */
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
			emailService.sendVerificationTokenEmail(user.getEmailAddress(), user.getToken(), EmailType.ACTIVATING.name());
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

		// TODO: need to tell FE to redirect to /login page after activate signup token
		return new ResponseEntity<>(userMapper.toWS(user.get()), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody UserWS userWS) {

		Optional<UserBean> user = accountService.findByEmailAddress(userWS.getEmailAddress());

		if (!user.isPresent()) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.LOGIN_INVALID.name(), "Invalid Login"), HttpStatus.UNAUTHORIZED);
		}

		Subject subject = SecurityUtils.getSubject();

		// Apache Shiro authentication check for 1st login
		if (!subject.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(userWS.getEmailAddress(), userWS.getPassword());

			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				log.error("Error in login. User fail to login. user={}", user.toString());
				return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.LOGIN_INVALID.name(), e.getMessage()), HttpStatus.UNAUTHORIZED);
			}
		}

		log.info("Success in user login. user={}", user.toString());

		UserBean userBean = user.get();
		JwtBean jwtBean = jwtMapper.toBean(userBean);
		JWTAuthenticationToken jwtObj = jwtService.createJwtToken(jwtBean);

		log.info("Success create jwt token. jwtToken={}", jwtObj.getToken());

		return new ResponseEntity<>(userMapper.toWS(userBean), jwtService.createJwtHeader(jwtObj.getToken()), HttpStatus.OK);
	}


	@GetMapping("/logout")
	@RequiresAuthentication
	public ResponseEntity<?> logout() {
		Subject subject = SecurityUtils.getSubject();

		Optional<UserBean> user = accountService.findByUuid(subject.getPrincipal().toString());

		if (!user.isPresent()) {
			log.error("Error in logout. User fail to logout. user={}", user.toString());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.LOGOUT_INVALID.name(), "Logout Invalid"), HttpStatus.BAD_REQUEST);
		}

		log.info("Success in user logout. user={}", user.toString());

		subject.logout();

		return new ResponseEntity<>(userMapper.toWS(user.get()), HttpStatus.OK);
	}

	@PostMapping("/users/{uuid}/forgot_password")
	public ResponseEntity<?> forgotPassword(@PathVariable String uuid, @Validated @RequestBody UserWS userWS) {

		Optional<UserBean> user = accountService.findByUuid(uuid);

		if (!user.isPresent()) {
			log.error("Error in user forgot password. Fail in finding user's uuid={}", uuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user account."), HttpStatus.NOT_FOUND);
		}

		if(!userWS.getEmailAddress().equals(user.get().getEmailAddress())){
			log.error("Error in user forgot password. Fail in finding user's email={}", userWS.getEmailAddress());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user email address."), HttpStatus.NOT_FOUND);
		}

		UserBean userBean = user.get();
		userBean = accountService.setPassCode(userBean);

		try {
			emailService.sendVerificationTokenEmail(userBean.getEmailAddress(), userBean.getToken(), EmailType.FORGOT_PASSWORD.name());
		} catch (MessagingException e) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.EMAIL_MESSAGING_EXCEPTION.name(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(userMapper.toWS(userBean), HttpStatus.OK);
	}

	@PostMapping("/users/{uuid}/reset_password")
	public ResponseEntity<?> resetPassword(@PathVariable String uuid, @Validated @RequestBody UserWS userWS) {
		Optional<UserBean> user = accountService.findByUuid(uuid);

		if (!user.isPresent()) {
			log.error("Error in user reset password. Fail in finding user's uuid={}", uuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user account."), HttpStatus.NOT_FOUND);
		}

		if(!userWS.getEmailAddress().equals(user.get().getEmailAddress())){
			log.error("Error in user reset password. Fail in finding user's email={}", userWS.getEmailAddress());
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user email address."), HttpStatus.NOT_FOUND);
		}

		if(StringUtils.isEmpty(userWS.getPassword())){
			log.error("Error in user reset password. The user password field cannot be empty.");
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.PASSWORD_EMPTY.name(), "Password field cannot empty."), HttpStatus.BAD_REQUEST);
		}

		UserBean userBean = userMapper.toBean(userWS);

		UserBean updatedUser = accountService.updateUser(userBean);

		return new ResponseEntity<>(userMapper.toWS(updatedUser), HttpStatus.OK);
	}


	@GetMapping("/users")
	@RequiresAuthentication
	@RequiresJwtAuthentication
	@RequiresPermissions("account:admin")
	public ResponseEntity<?> getUsers() {
		// TODO: may need to custommize logic to pass in page search limit
		Optional<List<UserBean>> userBeans = accountService.getAllUsers();
		return new ResponseEntity<Object>(userMapper.toWss(userBeans.get()), HttpStatus.OK);
	}

	@PutMapping("/users/{uuid}")
	@RequiresAuthentication
	@RequiresJwtAuthentication
	@RequiresPermissions("account:update")
	public ResponseEntity<?> updateUser(@PathVariable("uuid") String uuid, @Validated @RequestBody UserWS userWS) {
		BindException errors = new BindException(userWS, "UserWS");

		log.warn("update user account");

		editUserValidator.validate(userWS, errors);

		if (errors.hasErrors()) {
			log.error("Error in update user. Fail in edit user validation fields. userWS={}", userWS.toString());
			List<ErrorWS> errorWSList = apiErrorsExceptionHandler.generateErrorWSList(errors);
			return new ResponseEntity<>(new ApiErrorsWS(errorWSList), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Optional<UserBean> user = accountService.findByUuid(uuid);
		if (!user.isPresent()) {
			log.error("Error in update user. Fail in finding user's uuid={}", uuid);
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.ACCOUNT_NOT_EXIST.name(), "Cannot find user account."), HttpStatus.NOT_FOUND);
		}

		Subject subject = SecurityUtils.getSubject();
		if(!user.get().isAdmin() || !subject.getPrincipal().toString().equals(uuid)) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.PERMISSION_DENIED_ON_USER_UPDATE.name(), "Cannot update user information"), HttpStatus.BAD_REQUEST);
		}

		if(!StringUtils.isNotBlank(userWS.getEmailAddress())) {
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.PERMISSION_DENIED_ON_EMPTY_EMAIL_UPDATE.name(), "User cannot update email address"), HttpStatus.BAD_REQUEST);
		}

		UserBean userBean = userMapper.toBean(userWS);

		UserBean updatedUser = accountService.updateUser(userBean);

		log.info("Success in update user={}", updatedUser.toString());

		return new ResponseEntity<>(userMapper.toWS(updatedUser), HttpStatus.OK);
	}
}
