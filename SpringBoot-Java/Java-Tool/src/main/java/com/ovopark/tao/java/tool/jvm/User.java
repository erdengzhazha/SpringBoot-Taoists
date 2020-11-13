package com.ovopark.tao.java.tool.jvm;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy(value = true)
@Component
public class User {

  private String name;

  private String pass;


  public User() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }
}
