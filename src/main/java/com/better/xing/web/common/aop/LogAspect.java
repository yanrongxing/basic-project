package com.better.xing.web.common.aop;

/**
 * Created by Administrator on 2017/2/15.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect   //定义一个切面
@Order(AspectOrders.LOG_ORDER)
@Configuration
@Log4j2
public class LogAspect {
    @Autowired
    private ObjectMapper objectMapper;
    // 定义切点Pointcut
    @Pointcut("execution(* com.better.xing.web.rest.controller.*Controller.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        log.info("请求开始 >>>> url: {}, method: {}, uri: {}, params: {}", url, method, uri, pjp.getArgs());
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        String resultStr = objectMapper.writeValueAsString(result);
        log.info("请求结束 <<<< controller的返回值是{}",resultStr);
        return result;
    }
}
