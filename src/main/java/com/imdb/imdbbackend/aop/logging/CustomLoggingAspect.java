package com.imdb.imdbbackend.aop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomLoggingAspect extends LoggingAspect {


	/**
	 * Pointcut that matches all Spring beans in the application's main packages.
	 */
	@Pointcut("execution (* com.imdb.imdbbackend.controller..*.*(..))")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices.
	}

	/**
	 * Pointcut that matches error response.
	 */
	@Pointcut("within(@org.springframework.web.bind.annotation.ControllerAdvice *)")
	public void errorHandlerResponsePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices.
	}

	/**
	 * Pointcut that matches all repositories, services and Web REST endpoints.
	 */
	@Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
	public void springBeanPointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices.
	}

	/**
	 * Advice that logs methods throwing exceptions.
	 * @param joinPoint join point for advice
	 * @param e         exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
	public void logAfterThrowingError(JoinPoint joinPoint, Throwable e) {
		logAfterThrowing(joinPoint, e);
	}

	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@Around("applicationPackagePointcut() || springBeanPointcut() || errorHandlerResponsePointcut()")
	public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		return logAround(joinPoint);
	}

}
