package com.example.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	private Logger logger = Logger.getLogger(CRMLoggingAspect.class.getName());
	
	@Pointcut("execution(* com.example.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}

	@Pointcut("execution(* com.example.springdemo.service.*.*(..))")
	private void forServicePackage() {}

	@Pointcut("execution(* com.example.springdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n ===> In @Before: calling method: " + method);
		
		Object[] args = joinPoint.getArgs();
		
		for(Object arg : args) {
			logger.info("\n ===> argument: " + arg);
		}
	}
	
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n ===> In @AfterReturning: calling method: " + method);	
		
		logger.info("\n ===> result: " + result);
	}
}
