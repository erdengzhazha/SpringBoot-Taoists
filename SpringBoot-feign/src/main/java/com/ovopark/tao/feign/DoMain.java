package com.ovopark.tao.feign;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;

import java.util.HashMap;
import java.util.Map;

public class DoMain {
  public static void main(String[] args) {
    EsApi elastic = Feign.builder()
      .decoder(new GsonDecoder())
      .decoder(new StringDecoder())
      .requestInterceptor(new BasicAuthRequestInterceptor("elastic", "Ovopark#2020"))
      .target(EsApi.class, "http://47.111.16.185:6200");

//    Map<String,Object> map = new HashMap<>(4);
//    map.put("index","new-article");
//    map.put("type","article");
//    map.put("id",1);
//    map.put("doc","{\\\"hits\\\": 0,\\\"catid\\\": 51,\\\"update_time\\\": 1464082029,\\\"create_time\\\": 1462498729,\\\"description\\\": \\\"这是一个测试\\\",\\\"listorder\\\": 0,\\\"title\\\": \\\"测试新增\\\"}");
    //String doc = "{\"id\":107,\"categoryId\":1,\"title\":\"文章\",\"author\":\"足者\",\"description\":\"dafdas\",\"documentType\":2,\"content\":\"fdaaaaaaaaa\",\"createUserId\":\"68543\",\"createTime\":\"2020-11-12 19:23:08\",\"publishUserId\":null,\"publishTime\":null,\"updateUserId\":null,\"updateTime\":null,\"readtimes\":null,\"isHot\":null,\"isDelete\":null,\"deleteUserId\":null,\"sortId\":null,\"isSynchEs\":null,\"newbieGuide\":null,\"title_img\":null,\"publishStatus\":null,\"version\":null,\"helpNum\":null,\"noHelpNum\":null,\"platformId\":null}";
    String doc = "{'id':107}";
    String article = elastic.add(
      "new-article",
      "article",
      1,
      doc
    );
    System.out.println(article.toString());
  }
}
