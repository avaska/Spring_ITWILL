package com.itwillbs.ex;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// MethodInterceptor
// => 스프링 프레임워크 중에서 로그를 찍는 기능을 메서드 안에서 수행 가능하도록 기능 구현된 객체

public class LoggingAdvice implements MethodInterceptor {
	//로그를 찍는 보조기능을 수행하는 객체
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		
		System.out.println(" 중요 동작 메서드 호출 전 : LoggingAdvice");
		System.out.println(mi.getMethod() + " 메서드 호출 전 !! ");
		
		// 중요기능 메서드 호출
		Object obj = mi.proceed();		
		
		System.out.println("중요 동작 메서드 호출 후 : LoggingAdvice");
		System.out.println(mi.getMethod() + " 메서드 호출 후 !! ");
		
		return obj;
	}
	
	
	
}
