package com.caldevsupplychain.common.exception;

import com.caldevsupplychain.common.type.ErrorCode;
import com.caldevsupplychain.common.ws.account.ApiErrorsWS;
import com.caldevsupplychain.common.ws.account.ErrorWS;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@ControllerAdvice
public class ApiErrorsExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({JwtException.class})
	public ResponseEntity<?> handleJwtAuthenticationException(JwtException e){
		log.error("Error Cause={}", e.getStackTrace());
		return new ResponseEntity<Object>(new ApiErrorsWS(ErrorCode.JWT_EXCEPTION.name(), e.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ShiroException.class})
	public ResponseEntity<?> handleAuthenticationException(ShiroException e){

		log.error("Error Cause={}", e.getStackTrace());

		if(e instanceof UnauthenticatedException){
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.UNAUTHENTICATION.name(), e.getMessage()), HttpStatus.UNAUTHORIZED);
		}
		else if(e instanceof UnauthorizedException){
			return new ResponseEntity<>(new ApiErrorsWS(ErrorCode.UNAUTHORIZATION.name(), e.getMessage()), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(new ApiErrorsWS(e.getClass().getSimpleName(), e.getMessage()), HttpStatus.UNAUTHORIZED);
	}

	public List<ErrorWS> generateErrorWSList(BindingResult errors) {
		return errors.getFieldErrors()
				.stream()
				.map(error -> new ErrorWS(
						error.getCode(),
						error.getDefaultMessage()
				))
				.collect(toList());
	}
}
