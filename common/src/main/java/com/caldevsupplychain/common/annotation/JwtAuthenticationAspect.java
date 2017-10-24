package com.caldevsupplychain.common.annotation;

import com.caldevsupplychain.common.jwt.service.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class JwtAuthenticationAspect {

	@Autowired
	private JwtService jwtService;

	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void controller() {
	}

	@Pointcut("execution(* *.*(..))")
	protected void allMethod() {
	}


//	@Around("@annotation(RequiresJwtAuthentication) && controller() && allMethod() && args(..,headers)")
	//	public void doJwtAuthenticationCheck(ProceedingJoinPoint proceedingJoinPoint, HttpHeaders headers) throws Exception {
//	@Around("@annotation(RequiresJwtAuthentication) && args(.., headers)" )
//	public void doJwtAuthenticationCheck(ProceedingJoinPoint proceedingJoinPoint, HttpHeaders headers) throws Exception {
//	@AfterThrowing(throwing= "error")

	@Around("@annotation(RequiresJwtAuthentication)")
	public void doJwtAuthenticationCheck(ProceedingJoinPoint proceedingJoinPoint) throws JwtException {
//		Object proceedArgs = proceedingJoinPoint.getArgs();
//
//		Signature sign = proceedingJoinPoint.getSignature();;
//
//		log.warn("JointPoint args={}", proceedArgs.toString());
//		log.warn("Signature sign={}", sign.toString());

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		Subject subject = SecurityUtils.getSubject();

		Optional<String> token = Optional.ofNullable(request.getHeader(jwtService.getAuthHeader()));

		if(!token.isPresent()){
			throw new JwtException("JSON Web Token empty");
		}

		jwtService.verifyJwtToken(subject.getPrincipal().toString(), token.get());
	}



}
