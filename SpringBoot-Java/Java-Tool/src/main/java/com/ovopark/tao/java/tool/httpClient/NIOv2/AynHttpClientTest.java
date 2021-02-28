package com.ovopark.tao.java.tool.httpClient.NIOv2;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/7 2:23 下午
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
public class AynHttpClientTest {
//  CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

  public static void main(String[] args) {
    int i=0;
    CloseableHttpAsyncClient httpClient = AsynHttpClient.getHttpClient();
    //        String url = "http://www.baidu.com/";
//        String url = "https://www.cnblogs.com/";
    String url = "https://study.163.com/";
    String stringBody = JSONObject.toJSONString("123456");
    HttpPost postBody = AsynHttpClient.getPostBody(url, stringBody, ContentType.APPLICATION_JSON);
    //回调
    FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>() {
      @Override
      public void completed(HttpResponse result) {
        System.out.println(result.getStatusLine() );
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
    //连接池执行

    httpClient.execute(postBody,callback);
  }

}
