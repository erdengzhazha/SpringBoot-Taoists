package com.ovopark.tao.java.tool.demo.OtherDemo;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/6 3:35 下午
 *           '::::::::::::'     @Version 1.0.0    
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 */
public class ElasticSearchTest {

  @Test
  public void t(){
    HttpRequest post = HttpUtil.createPost("http://47.111.16.185:9090/es/getByCondition");
    String body = post.body("{\n" +
      "      \"index\":\"open_help_document\",\n" +
      "      \"type\":\"_doc\",\n" +
      "      \"matchFields\":\"_author=饕餮&platformId=1\",\n" +
      "      \"sortField\":\"helpNum\",\n" +
      "      \"sortType\":\"desc\",\n" +
      "      \"start\":0,\n" +
      "      \"size\":10\n" +
      "    }").execute().body();
    System.out.println(body);


  }

  @Test
  public void d(){
    String level = "0,1";
    String[] fathers = level.split(",");
    System.out.println(fathers.length);
  }

  @Test
  public void c(){
    List<Long> list = new ArrayList<>();
    list.add(1L);
    System.out.println(JSON.toJSON(list));
  }
}
