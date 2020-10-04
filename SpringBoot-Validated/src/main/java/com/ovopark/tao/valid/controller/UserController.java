package com.ovopark.tao.valid.controller;

import com.ovopark.tao.valid.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

  @PostMapping("/user")
  public void add(@RequestBody @Validated User user){
    log.info("user ======{}",user);
  }
}
