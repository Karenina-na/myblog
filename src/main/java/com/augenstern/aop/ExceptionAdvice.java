package com.augenstern.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 异常处理增强
 */
@Component
@Aspect
public class ExceptionAdvice {
    @Pointcut("execution(* com.augenstern.service.impl.*.*(..))")
    private void servicePt() {
    }

    @Pointcut("execution(* com.augenstern.controller.*.*(..))")
    private void controllerPt() {
    }

    /**
     * Service异常处理
     */
    @Around("servicePt()")
    public Object serviceException(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }

    /**
     * Dao异常处理
     */
    @Around("controllerPt()")
    public Object controllerException(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
