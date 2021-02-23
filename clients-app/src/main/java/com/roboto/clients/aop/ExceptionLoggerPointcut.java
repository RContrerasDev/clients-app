package com.roboto.clients.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/*@Configuration
@Aspect*/
public class ExceptionLoggerPointcut {
	
	/*
	 * @AfterThrowing(pointcut = "execution(* com.roboto.clients.*.*.*(..))",
	 * throwing = "ex") public void logError(Exception ex) {
	 * System.out.println("ROBOTO:: There was a horrible exception");
	 * System.out.println("ROBOTO:: Message: " + ex.getCause());
	 * System.out.println("ROBOTO:: LocalizedMessage: " + ex.getLocalizedMessage());
	 * ex.printStackTrace(); }
	 */

}
