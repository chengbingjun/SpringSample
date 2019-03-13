package com.example.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @Before("execution(* com.example.aop.UserService.addUser(..))")
    public void myBefore (JoinPoint joinpoint){
        System.out.println("前置通知"+joinpoint.getSignature().getName());
    }
    @AfterReturning(value = "execution(* com.example.aop.*.*(..))",returning = "jet")
    public void myAfter(JoinPoint joinPoint,Object jet){
        System.out.println("后置通知"+joinPoint.getSignature().getName()+",-->"+jet);
    }
}
