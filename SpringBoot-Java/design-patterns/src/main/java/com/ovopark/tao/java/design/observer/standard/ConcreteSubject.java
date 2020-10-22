package com.ovopark.tao.java.design.observer.standard;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题
 */
public class ConcreteSubject extends Subject{

  /** 主题的状态*/
  private String subjectState;

  public String getSubjectState() {
    return subjectState;
  }

  public void setSubjectState(String subjectState){
    this.subjectState = subjectState;
    this.myNotify();
  }
}
