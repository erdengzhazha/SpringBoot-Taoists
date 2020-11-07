package com.ovopark.aop.auth.controller;

import com.ovopark.aop.auth.annotation.AuthSign;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>后台控制器类<p/>
 * @author Xiu_Er 13813641925@163.com
 * @Date 2020年11月7号 下午 12:57
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

  @AuthSign
  @RequestMapping("/v1/login")
  public String login(String name){
    System.out.println("恭喜登录-成功!"+name);
    return "恭喜登录成功!";
  }
}
