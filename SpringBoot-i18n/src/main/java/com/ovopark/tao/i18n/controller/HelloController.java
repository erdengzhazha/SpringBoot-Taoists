package com.ovopark.tao.i18n.controller;

import com.ovopark.tao.i18n.utils.LocaleMessageSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class HelloController {

  @Autowired
  private LocaleMessageSourceUtil lmsUtil;

  @RequestMapping("hello")
  public String hello() throws UnsupportedEncodingException {
    String welcome = lmsUtil.getMessage("welcome");
    System.out.println(welcome);
    return welcome;
  }
}
