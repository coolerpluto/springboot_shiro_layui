package com.example.sys.myJava;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Aspect
@Component
public class AopTest {

    @Autowired
    Test1 test1;

    @Pointcut("execution(* com.example.sys.myJava.Test1.sout1())")
    public void method1(){}

    @Before("execution(* com.example.sys.myJava.Test1.sout1())")
    public void method2(JoinPoint joinPoint){
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));

    }



    public static void main(String[] args) {


    }
}
