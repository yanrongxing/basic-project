package com.better.xing.web.common.aop;

import com.better.xing.web.common.annotations.NoLogin;
import com.better.xing.web.common.enums.ErrorEnum;
import com.better.xing.web.common.utils.MD5Utils;
import com.better.xing.web.common.utils.ResponseUtil;
import com.better.xing.web.jpa.model.User;
import com.better.xing.web.rest.base.RestBaseReq;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Aspect
@Log4j2
@Order(AspectOrders.AUTHCHECK_ORDER)
@Configuration
public class AuthCheckAspect {
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    @Pointcut("execution(* com.better.xing.web.rest.controller.*Controller.*(..))")
    public void excudeService() {
    }
    
    @Around("excudeService()")
    protected Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        Signature s = pjp.getSignature();
        MethodSignature ms = (MethodSignature)s;
        Method m = ms.getMethod();
        NoLogin annotation = m.getAnnotation(NoLogin.class);
        if(annotation == null){
            ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
            // 执行业务
            Object[] args = pjp.getArgs();
            if(args.length > 0){
                Object arg = args[0];
                if(arg instanceof RestBaseReq){
                    RestBaseReq arg1 = (RestBaseReq) arg;
                    String sessionId = arg1.getSessionId();
                    String secretKey = arg1.getSecretKey();
                    Object o = operations.get(sessionId);
                    if(o == null){//会话不存在
                        return ResponseEntity.ok(ResponseUtil.genResp(ErrorEnum.SESSION_FAILURE));
                    }
                    String s1 = objectMapper.writeValueAsString(arg1);
                    TreeMap treeMap = objectMapper.readValue(s1, TreeMap.class);
                    Object secret_key = treeMap.remove("secret_key");
                    Set<Map.Entry> set = treeMap.entrySet();
                    StringBuilder stringBuilder = new StringBuilder();
                    set.forEach(entry -> {
                        log.info(entry);
                        stringBuilder.append(entry.getKey())
                                .append("=")
                                .append(entry.getValue())
                                .append("&");
                    });
                    String argStr = stringBuilder.toString();
                    if(argStr.endsWith("&")){
                        argStr = argStr.substring(0, argStr.length() - 1);
                    }
                    int i = sessionId.length() / 2;
                    String substring = sessionId.substring(0, i);
                    String substring1 = sessionId.substring(i, sessionId.length());
                    String s2 = new StringBuilder().append(substring).append(argStr).append(substring1).toString();
                    if(!MD5Utils.isEqualsToMd5(s2, secretKey)){
                        result = ResponseEntity.ok(ResponseUtil.genError());
                    }else{
                        result = pjp.proceed();
                    }
                }
            }
        }else{
            result = pjp.proceed();
        }

        return result;
    }
}
