package com.ovopark.tao.java.tool.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/***
 * 包层面的切面配置
 */
@Aspect
@Component
public class PkTypeAspectConfig {

    @Pointcut("within(com.ovopark.tao.java.tool.aop.service.UserService)")
    public void matchType(){
        System.out.println("我是在什么时候执行");
    }

    @Before("matchType()")
    public void before(){
        System.out.printf("");
        System.out.println("之前");
    }
}
