package com.ovopark.tao.java.tool.httpClient.NIO;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description HttpAsyncClient-真正的异步
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/7 1:55 下午
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
public class RealAsyncClient {

  // 1.创建CallBack
  private static final class MyCallback implements FutureCallback<HttpResponse> {

    public void failed(final Exception ex) {
      System.out.println(ex.getLocalizedMessage());
    }

    public void completed(final HttpResponse response) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (ParseException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    public void cancelled() {
      System.out.println("cancelled");
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
    // 2.创建异步httpclient对象
    RequestConfig requestConfig = RequestConfig.custom()
      .setConnectTimeout(1000)//连接超时,连接建立时间,三次握手完成时间
      .setSocketTimeout(1000)//请求超时,数据传输过程中数据包之间间隔的最大时间
      .setConnectionRequestTimeout(10000)//使用连接池来管理连接,从连接池获取连接的超时时间
      .build();
    //配置io线程
    IOReactorConfig ioReactorConfig = IOReactorConfig.custom().
      setIoThreadCount(Runtime.getRuntime().availableProcessors())
      .setSoKeepAlive(true)
      .build();
    //设置连接池大小
    ConnectingIOReactor ioReactor = null;
    try {
      ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
    } catch (IOReactorException e) {
      e.printStackTrace();
    }
    PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
    /**
     * setMaxTotal()方法用来设置连接池的最大连接数，即整个池子的大小；
     * setDefaultMaxPerRoute()方法来设置每一个路由的最大连接数，这里的路由是指IP+PORT，例如连接池大小(MaxTotal)设置为300，路由连接数设置为200(DefaultMaxPerRoute)，对于www.a.com与www.b.com两个路由来说，发起服务的主机连接到每个路由的最大连接数（并发数）不能超过200，两个路由的总连接数不能超过300。
     **/
    connManager.setMaxTotal(2000);  //池子的大小
    connManager.setDefaultMaxPerRoute(200); //一个路由最大并发200
    CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
      .setConnectionManager(connManager)
      .setDefaultRequestConfig(requestConfig)
      .build();
    // 3.0启动
    httpclient.start();
    // 3.发起调用
    try {


      // 3.1请求参数
      HttpGet httpget1 = new HttpGet("http://127.0.0.1:8080/test1");
      HttpGet httpget2 = new HttpGet("http://127.0.0.1:8080/test2");
      // 3.2发起请求，不阻塞，马上返回
      httpclient.execute(httpget1, new MyCallback());
      httpclient.execute(httpget2, new MyCallback());

      // 3.3休眠10s,避免请求执行完成前，关闭了链接
      Thread.sleep(10000);
    } finally {
      httpclient.close();
    }
  }

}
