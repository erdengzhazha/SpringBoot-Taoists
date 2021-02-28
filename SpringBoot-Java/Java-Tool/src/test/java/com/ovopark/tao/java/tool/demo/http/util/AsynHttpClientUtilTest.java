package com.ovopark.tao.java.tool.demo.http.util;

import com.alibaba.fastjson.JSONObject;
import com.ovopark.tao.java.tool.httpClient.NIOv2.AsynHttpClient;
import com.ovopark.tao.java.tool.httpClient.util.AsynHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.junit.jupiter.api.Test;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/7 3:03 下午
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
@Slf4j
public class AsynHttpClientUtilTest {

  //回调
  FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>() {
    @Override
    public void completed(HttpResponse result) {
      System.out.println("----------------------------------------------------");
      System.out.println(result.getStatusLine() );
      System.out.println(result.getEntity().toString());
    }
    @Override
    public void failed(Exception e) {
      e.printStackTrace();
      System.err.println("失败：");
    }
    @Override
    public void cancelled() {
      System.err.println("cancelled");

    }
  };

  @Test
  public void t() throws InterruptedException {
    CloseableHttpAsyncClient closeableHttpAsyncClient = AsynHttpClientUtil.getCloseableHttpAsyncClient();

    // 3.1 get请求参数
//    HttpGet httpget1 = new HttpGet("http://127.0.0.1:8080/test1");
//    HttpGet httpget2 = new HttpGet("http://127.0.0.1:8080/test2");
    //3.2 post请求
    String url = "https://study.163.com/";
    String stringBody = JSONObject.toJSONString("123456");
    HttpPost postBody = AsynHttpClientUtil.getPostBody(url, stringBody, ContentType.APPLICATION_JSON);
    HttpPost postBody02 = AsynHttpClientUtil.getPostBody("http://127.0.0.1:8080/test2", stringBody, ContentType.APPLICATION_JSON);
    closeableHttpAsyncClient.execute(postBody,callback);
    closeableHttpAsyncClient.execute(postBody02,callback);

    Thread.sleep(10001);
    System.out.println("10秒过去了");

  }
}
