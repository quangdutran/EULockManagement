package com.dutq.lock.management.lock_management.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;

@Aspect
@Component
@Slf4j
public class RestTemplateLoggingAspect {
    @Pointcut("execution(* org.springframework.web.client.RestTemplate.*(..))")
    public void restTemplateMethods() {
        // Pointcut to capture all methods in RestTemplate
    }

    @Before("restTemplateMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("REST Request: Method = {}, Arguments = {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "restTemplateMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("REST Response: Method = {}, Response = {}", joinPoint.getSignature().toShortString(), result);
    }
}
