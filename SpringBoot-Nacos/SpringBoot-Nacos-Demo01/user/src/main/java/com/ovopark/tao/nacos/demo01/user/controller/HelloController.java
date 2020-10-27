package com.ovopark.tao.nacos.demo01.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class HelloController {

  @RequestMapping("/websocket/sendMessageToUser")
  public void hello(Integer userId){
    System.out.println(userId);
  }
}
