package com.ovopark.tao.feign;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

@Headers({"Content-Type: application/json","Accept: application/json"})
public interface EsApi {

  @Body(value = "%7B\"index\": \"{index}\",\n" +
    "    \"type\": \"{type}\",\n" +
    "    \"id\": {id} ,\"doc\":\"{doc}\"%7D")
  @RequestLine("POST /es/add")
  String add(
    @Param(value="index") String index,
    @Param(value="type") String type,
    @Param(value="id") Object id,
    @Param(value="doc") String doc
  );


//  @RequestLine("POST /es/add")
//  EsBaseResultVO add(
//    Map<String,Object> map
//  );
}
