package com.ovopark.tao.java.design.observer.standard;

/**
 * 测试观察者模式
 */
public class RunObserver {

  public static void main(String[] args) {
    //定义观察者
    ConcreteObserver observer1 = new ConcreteObserver();
    //定义一个主题
    ConcreteSubject concreteSubject = new ConcreteSubject();
    //观察者订阅主题
    concreteSubject.Attach(observer1);
    //更新消息
    concreteSubject.setSubjectState("你好!");
  }
}
