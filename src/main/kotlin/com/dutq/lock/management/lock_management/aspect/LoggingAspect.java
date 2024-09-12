package com.dutq.lock.management.lock_management.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.dutq.lock.management.lock_management.controllers..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Request to: {} with arguments: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }
}
