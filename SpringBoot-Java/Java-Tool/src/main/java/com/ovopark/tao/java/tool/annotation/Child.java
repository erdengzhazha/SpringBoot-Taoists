package com.ovopark.tao.java.tool.annotation;

public class Child extends Father{


  /**
   * @Override 注解信息
   *
   * @Target(ElementType.METHOD)
   * @Retention(RetentionPolicy.SOURCE)
   * public @interface Override {
   * }
   */




  @Override
  public void fatherFaction() {
    super.fatherFaction();
  }
}
