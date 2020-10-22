package com.ovopark.tao.java.design.observer.standard;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 具体观察员
 */
public class ConcreteObserver implements Observer{

  /** 观察者状态*/
  private String observerState;


  @Override
  public void update(Subject subject) {
    Class c = subject.getClass();

    //获取对象的属性，直接获取值 （前提：属性为public类型）
//    try {
//      Field subjectState = c.getDeclaredField("subjectState");
//      try {
//        String o = (String)subjectState.get(subject);
//        System.out.println(o);
//      } catch (IllegalAccessException e) {
//        e.printStackTrace();
//      }
//    } catch (NoSuchFieldException e) {
//      e.printStackTrace();
//    }

    //获取方法
    try {
      Method getSubjectState = c.getMethod("getSubjectState", null);
      Object invoke = getSubjectState.invoke(subject, null);
      System.out.println(invoke.toString());
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

  }
}
