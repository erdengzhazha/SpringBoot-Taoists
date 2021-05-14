package com.ovopark.tao.nacosconfig.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
// ⭐⭐⭐⭐⭐动态刷新注解必须,  如果使用Spring自带的@Value 必须加⭐️⭐⭐⭐⭐
@RefreshScope
public class ConfigController {

  @Value(value = "${testStr}")
  public String useLocalCache;

//  @NacosValue(value = "${testStr}" , autoRefreshed = true)
//  public String useLocalCache;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public String get() {
    return useLocalCache +"..dataId..";
  }
}
