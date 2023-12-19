package com.igaming.watergastracker.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

    private long startTime;

    @Before("execution(* com.igaming.watergastracker.controller.MeasurementController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.log(Level.INFO, "Executing method: " + methodName);
    }

    @After("execution(* com.igaming.watergastracker.controller.MeasurementController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        long endTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        LOGGER.log(Level.INFO, "Method " + methodName + " executed in " + (endTime - startTime) + " ms");
    }
}
