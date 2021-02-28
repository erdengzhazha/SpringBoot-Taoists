package com.ovopark.tao.java.tool.httpClient.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 异步http客户端工具
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/7 2:43 下午
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
public class AsynHttpClientUtil {

  private enum AsynHttpClientLoader{
    instance;
    private CloseableHttpAsyncClient httpAsyncClient;
    AsynHttpClientLoader(){
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
       * setDefaultMaxPerRoute()方法来设置每一个路由的最大连接数，这里的路由是指IP+PORT，
       * 例如连接池大小(MaxTotal)设置为300，路由连接数设置为200(DefaultMaxPerRoute)，
       * 对于www.a.com与www.b.com两个路由来说，发起服务的主机连接到每个路由的最大连接数（并发数）
       * 不能超过200，两个路由的总连接数不能超过300。
       **/
      connManager.setMaxTotal(2000);  //池子的大小
      connManager.setDefaultMaxPerRoute(200); //一个路由最大并发200
      CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
        .setConnectionManager(connManager)
        .setDefaultRequestConfig(requestConfig)
        .build();
      // 3.0启动
      httpclient.start();
      httpAsyncClient = httpclient;
    }

    private CloseableHttpAsyncClient init(){
      return httpAsyncClient;
    }
  }

  /**
   * 单利获取
   * @return
   */
  public static CloseableHttpAsyncClient getCloseableHttpAsyncClient(){
    return AsynHttpClientLoader.instance.init();
  }

  public static HttpPost getPostBody(String urls, String bodys, ContentType contentType) {
    HttpPost post = null;
    StringEntity entity = null;
    post = new HttpPost(urls);
    entity = new StringEntity(bodys, contentType);
    post.setEntity(entity);
    return post;
  }

}
