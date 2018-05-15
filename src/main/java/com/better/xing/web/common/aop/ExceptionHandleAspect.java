package com.better.xing.web.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Aspect
@Order(AspectOrders.EXCEPTION_HANDLE_ORDER)
@Configuration
public class ExceptionHandleAspect {


    @Pointcut("execution(* com.better.xing.web.rest.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @SuppressWarnings("unchecked")
    @Around("excudeService()")
	protected Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		return pjp.proceed();
	}

}
