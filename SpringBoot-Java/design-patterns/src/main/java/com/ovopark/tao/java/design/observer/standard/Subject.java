package com.ovopark.tao.java.design.observer.standard;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题
 */
public class Subject {

  /** 观察者列表*/
  List<Observer> observers = new ArrayList<>();

  /**
   * TODO (附加观察者)
   * @param observer
   */
  public void Attach(Observer observer){
    observers.add(observer);
  }

  /**
   * TODO (分离观察者)
   * @param observer
   */
  public void Detach(Observer observer){
    observers.remove(observer);
  }

  /**
   * 通知观察者,更新
   */
  public void myNotify() {
    for(Observer o: observers){
      o.update(this);
    }
  }
}
