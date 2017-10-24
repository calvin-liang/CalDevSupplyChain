package com.caldevsupplychain.common.annotation;

import com.caldevsupplychain.common.jwt.service.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Aspect
@Component
public class JwtAuthenticationAspect {

	@Autowired
	private JwtService jwtService;

	@Around("@annotation(RequiresJwtAuthentication)")
	public Object doJwtAuthenticationCheck(ProceedingJoinPoint proceedingJoinPoint) throws JwtException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		Object proceed = null;

		try {
			proceed = proceedingJoinPoint.proceed();
		} catch (Throwable e){
			log.error("Error message={} stack_trace={}", e.getMessage(), e.getStackTrace());
		} finally {
			Subject subject = SecurityUtils.getSubject();
			Optional<String> jwtTokenObj = Optional.ofNullable(request.getHeader(jwtService.getAuthHeader()));
			if(!jwtTokenObj.isPresent()){
				throw new JwtException("JSON Web Token empty");
			}
			jwtService.verifyJwtToken(subject.getPrincipal().toString(), jwtTokenObj.get());
		}
		return proceed;
	}
}
