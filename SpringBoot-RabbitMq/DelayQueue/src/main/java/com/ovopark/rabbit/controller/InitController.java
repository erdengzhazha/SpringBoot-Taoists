package com.ovopark.rabbit.controller;

import com.ovopark.rabbit.service.DeclareService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description RabbitMq初始化路由
 * @author  Xiu_Er 13813641925@163.com
 * @Date 2020年10月15号 下午7:56
 */
@RestController
@RequestMapping("/rabbit")
public class InitController {
  @Autowired
  private DeclareService declareService;

  /**
   * TODO (声明延迟队列套件)
   * @param projectName
   * @return
   */
  @RequestMapping("/declareDelayPackage")
  public String declareDelayPackage(@RequestParam("projectName")String projectName){
    String s = declareService.declareDelayPackage(projectName);
    return s;
  }

}
