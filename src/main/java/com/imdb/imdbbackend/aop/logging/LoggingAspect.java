package com.imdb.imdbbackend.aop.logging;

import com.imdb.imdbbackend.util.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
 * Logging aspect class
 */
@Aspect
@Component
public abstract class LoggingAspect {

	private static final String NULL = "NULL";

	/**
	 * Advice that logs methods throwing exceptions.
	 * @param joinPoint join point for advice
	 * @param exception exception
	 */
	protected void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		List<Object> objects = new ArrayList<>();
		objects.add(joinPoint.getSignature().getName());
		objects.add(exception.getMessage());
		objects.add(exception);
		Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
		log.error("Exception: {}() with Exception Message = '{}'", objects.toArray());
	}

	/**
	 * Advice that logs when a method is entered and exited.
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	protected Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());

		if (log.isDebugEnabled()) {
			List<Object> objects = new ArrayList<>();
			objects.add(joinPoint.getSignature().getName());
			objects.add(toArgumentsString(joinPoint.getArgs()));
			log.debug("Enter: {}() with Argument[s] = {}", objects.toArray());
		}

		Object result = joinPoint.proceed();

		if (log.isDebugEnabled()) {
			List<Object> objects = new ArrayList<>();
			objects.add(joinPoint.getSignature().getName());
			if (joinPoint.getSignature().toLongString().contains(" void ")) {
				objects.add("'No Result'");
			} else {
				objects.add(toResultString(result));
			}
			log.debug("Exit: {}() with Result = {}", objects.toArray());
		}
		return result;
	}

	/**
	 * To result string
	 * @param result the result object
	 * @return return result in string object
	 */
	private String toResultString(Object result) {
		if (ObjectUtils.isNull(result)) {
			return NULL;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n'\nResult: ");
		appendToStringBuilder(stringBuilder, result);
		stringBuilder.append("\n'");
		return stringBuilder.toString();
	}

	/**
	 * To arguments string method
	 * @param args arguments
	 * @return in string object
	 */
	private String toArgumentsString(Object args) {
		if (ObjectUtils.isNull(args)) {
			return "'No Argument[s]'";
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n'\n");
		if (!args.getClass().isArray()) {
			appendToStringBuilder(stringBuilder, args);
			stringBuilder.append("\n'");
			return stringBuilder.toString();
		}
		Object[] argsArray = (Object[]) args;
		if (argsArray.length == 0) {
			return "'No Argument[s]'";
		}
		for (int i = 0; i < argsArray.length; i++) {
			stringBuilder.append("arguments[");
			stringBuilder.append(i);
			stringBuilder.append("]: ");
			appendToStringBuilder(stringBuilder, argsArray[i]);
			stringBuilder.append("\n");
		}
		stringBuilder.append("'");
		return stringBuilder.toString();
	}

	/**
	 * To append to the string builder
	 * @param stringBuilder the string building
	 * @param arg           the argument
	 */
	private void appendToStringBuilder(StringBuilder stringBuilder, Object arg) {
		if (ObjectUtils.isNull(arg)) {
			stringBuilder.append(NULL);
		} else {
			stringBuilder.append(arg);
		}
	}

}
