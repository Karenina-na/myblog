package com.augenstern.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.augenstern.aop.Util.LoggerUtil.getMessage;

/**
 * 日志增强通知
 */
@Component
@Aspect
public class LoggerAdvice {

    @Resource
    private HttpServletRequest request;

    @Pointcut("execution(* com.augenstern.service.impl.*.*(..))")
    private void servicePt() {
    }

    @Pointcut("execution(* com.augenstern.dao.*.*(..))")
    private void daoPt() {
    }

    @Pointcut("execution(* com.augenstern.controller.*.*(..))")
    private void controllerPt() {
    }

    /**
     * 增强Controller
     */
    @Before("controllerPt()")
    public void controllerLogger() {
        final Logger LOGGER = LoggerFactory.getLogger("Controller-");
        LOGGER.info(request.getRemoteAddr() + "-" + request.getRequestURI());
    }

    /**
     * 增强Service
     */
    @After("servicePt()")
    public void serviceLogger(JoinPoint jp) {
        List<String> message = getMessage(jp);
        String methodName = message.get(0);
        String Arg = message.get(1);
        String returnType = message.get(2);

        final Logger LOGGER = LoggerFactory.getLogger(request.getRemoteAddr()
                + "-Service-" + methodName);
        LOGGER.info("Args-" + Arg + " Return-" + (returnType));
    }

    /**
     * 增强Dao
     */
    @After("daoPt()")
    public void daoLogger(JoinPoint jp) {
        List<String> message = getMessage(jp);
        String methodName = message.get(0);
        String Arg = message.get(1);
        String returnType = message.get(2);

        final Logger LOGGER = LoggerFactory.getLogger(request.getRemoteAddr()
                + "-Dao-" + methodName);
        LOGGER.info("Args-" + Arg + " Return-" + (returnType));
    }

}
