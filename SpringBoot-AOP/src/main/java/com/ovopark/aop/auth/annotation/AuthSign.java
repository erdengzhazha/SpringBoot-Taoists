package com.ovopark.aop.auth.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 鉴权注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthSign {

  /**
   * 加密方式
   * @return
   */
  String signMethod() default "md5";

  /**
   * 白名单检查？
   */
  boolean checkWhiteList() default true;
}
