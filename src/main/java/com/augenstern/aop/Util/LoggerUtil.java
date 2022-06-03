package com.augenstern.aop.Util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日志抽取工具类
 */
public class LoggerUtil {
    public static List<String> getMessage(JoinPoint pjp) {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String methodName = signature.getName();
        Map<String, Object> Arg = new HashMap<>();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            Arg.put("Param" + (i + 1), args[i]);
        }
        Class<?> returnType = methodSignature.getReturnType();
        List<String> message = new ArrayList<>();
        message.add(methodName);
        message.add(String.valueOf(Arg));
        message.add(String.valueOf(returnType));
        return message;
    }
}
