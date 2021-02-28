package com.ovopark.tao.java.tool.httpClient.NIO;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpRequestFutureTask;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description Java非阻塞IO
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/7 1:23 下午
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
public class NioClient {

  // 1.创建线程池
  private static ExecutorService executorService = Executors.newFixedThreadPool(5);

  // 2.创建http回调函数
  private static final class OkidokiHandler implements ResponseHandler<String> {
    public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
      // 2.1处理响应结果
      return EntityUtils.toString(response.getEntity());
    }
  }

  /**
   * 创建CallBack
   */
  private static final class MyCallback implements FutureCallback<String> {

    public void failed(final Exception ex) {
      System.out.println(ex.getLocalizedMessage());
    }

    public void completed(final String result) {
      System.out.println(result);
    }

    public void cancelled() {
      System.out.println("cancelled");
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    // 3.创建httpclient对象
    CloseableHttpClient httpclient = HttpClients.createDefault();

    // 4.创建FutureRequestExecutionService实例
    FutureRequestExecutionService futureRequestExecutionService = new FutureRequestExecutionService(httpclient,
      executorService);

    // 5.发起调用
    try {
      // 5.1请求参数
      HttpGet httpget1 = new HttpGet("http://127.0.0.1:8080/test1");
      HttpGet httpget2 = new HttpGet("http://127.0.0.1:8080/test2");
      // 5.2发起请求，不阻塞，马上返回
//      HttpRequestFutureTask<String> task1 = futureRequestExecutionService.execute(httpget1,
//        HttpClientContext.create(), new OkidokiHandler());
//
//      HttpRequestFutureTask<String> task2 = futureRequestExecutionService.execute(httpget2,
//        HttpClientContext.create(), new OkidokiHandler());
      HttpRequestFutureTask<String> task1 = futureRequestExecutionService.execute(httpget1, HttpClientContext.create(), new OkidokiHandler(), new MyCallback());
      HttpRequestFutureTask<String> task2 = futureRequestExecutionService.execute(httpget2, HttpClientContext.create(), new OkidokiHandler(), new MyCallback());
      // 5.3 do somthing
      Thread.sleep(10000);
      // 5.4阻塞获取结果
//      String str1 = task1.get();
//      String str2 = task2.get();
//      System.out.println("response:" + str1 + " " + str2);
      System.out.println("等了10m了");
    } finally {
      httpclient.close();
    }
  }

}
