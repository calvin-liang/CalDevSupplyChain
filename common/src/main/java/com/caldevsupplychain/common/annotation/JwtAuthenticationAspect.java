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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Aspect
@Controller
@EnableAspectJAutoProxy
public class JwtAuthenticationAspect {

	@Autowired
	private JwtService jwtService;

	@Around("@annotation(RequiresJwtAuthentication)")
	public void doJwtAuthenticationCheck(ProceedingJoinPoint proceedingJoinPoint) throws JwtException {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Subject subject = SecurityUtils.getSubject();

		Optional<String> token = Optional.ofNullable(request.getHeader(jwtService.getAuthHeader()));

		if(!token.isPresent()){
			log.error("Token empty");
			throw new JwtException("JSON Web Token empty");
		}

		jwtService.verifyJwtToken(subject.getPrincipal().toString(), token.get());
	}



}
