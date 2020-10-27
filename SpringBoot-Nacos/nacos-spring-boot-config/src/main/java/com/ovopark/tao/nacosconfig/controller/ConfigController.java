package com.ovopark.tao.nacosconfig.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class ConfigController {

  @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
  private boolean useLocalCache;
//  @NacosValue(value = "${dataId}",autoRefreshed = true)
//  private boolean dataId;

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public String get() {
    return useLocalCache +"..dataId..";
  }
}
