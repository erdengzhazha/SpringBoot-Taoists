package com.ovopark.tao.mvc.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloController {

  @RequestMapping("/hello")
  public String toIndex(){
    System.out.println("进入了");
    return "index.html";
  }

  @PostMapping(value = "/v1/listenHelpDocument" , produces = "application/json;charset=UTF-8")
  public void listenHelpDocument(@RequestBody Map<String,Object> map){
    System.out.println(JSON.toJSONString(map));
  }
}
