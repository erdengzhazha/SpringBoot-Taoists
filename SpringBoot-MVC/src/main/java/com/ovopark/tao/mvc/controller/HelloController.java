package com.ovopark.tao.mvc.controller;

import com.ovopark.boot.kit.json.Jackson2Kit;
import com.ovopark.dc.apigetway.sdk.config.ApiConst;
import com.ovopark.dc.apigetway.sdk.utils.SignUtils;
import com.ovopark.tao.mvc.entity.ResultDto;
import com.ovopark.tao.mvc.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class HelloController {


  private  int i=0;
  @RequestMapping("/hello")
  public String toIndex(){
    i++;
    System.out.println(i);
    System.out.println("进入了");
    return "index.html";
  }

  Map<Integer,Long> mapJs = new HashMap<>();

  @RequestMapping("/v1/getMessage02")
  public void getMessage(@RequestHeader String sig,  @RequestBody Map<String,Object> map) throws InterruptedException {
    int minute = DateUtil.getMinute();
    if(mapJs.get(minute)==null){
      mapJs.put(minute,0L);
    }else{
      Long aLong = mapJs.get(minute);
      aLong = aLong+1;
      mapJs.put(minute,aLong);
    }
    String json = Jackson2Kit.objToJson(map);
    log.info("json : {}" ,json);
    log.info("sig: {}", sig);
    String sig2 = SignUtils.signJson(json,"c2e7b547b11bc4eda02e88b3648f9e72", ApiConst.sign_method_MD5);
    log.info("sig2: {}",sig2);
    if(!sig.equals(sig2)){
      log.error("签名验证失败!");
    }else{
      log.info("签名正确!");
    }
//    System.out.println(resultDto.toString());
  }

  @RequestMapping("/v1/getMap")
  public Long getMapd(Integer minit){
    return mapJs.get(minit);
  }

  public Map<Integer, Long> getMap() {
    return mapJs;
  }

  public void setMap(Map<Integer, Long> map) {
    this.mapJs = map;
  }

  //  public void getMessage
}
