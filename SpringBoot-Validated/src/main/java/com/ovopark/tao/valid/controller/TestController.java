package com.ovopark.tao.valid.controller;

import com.ovopark.tao.valid.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  /**
   * 表单请求
   *
   * @param form 请求参数
   * @return 返回参数
   */
  @PostMapping("/formRequest")
  public String formRequest(@Validated User form) {
    return form.toString();
  }
}
