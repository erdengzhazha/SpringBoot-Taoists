package com.ovopark.tao.java.design.observer.standard;

/**
 * 观察者接口
 */
public interface Observer {

  /**
   * 更新接口
   * @param subject (传入的目标对象， 方便获取目标的状态)
   */
  void update(Subject subject);
}
