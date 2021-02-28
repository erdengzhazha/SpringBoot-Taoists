package com.ovopark.tao.java.tool.httpClient.NIOv2;

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
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/7 2:22 下午
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
public class AsynHttpClient {

  private static CloseableHttpAsyncClient client = null;


  public static CloseableHttpAsyncClient getHttpClient() {
    if (client == null) {
      synchronized (AsynHttpClient.class) {
        if (client == null) {
          RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(2000)//连接超时,连接建立时间,三次握手完成时间
            .setSocketTimeout(2000)//请求超时,数据传输过程中数据包之间间隔的最大时间
            .setConnectionRequestTimeout(20000)//使用连接池来管理连接,从连接池获取连接的超时时间
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
          connManager.setMaxTotal(5);//最大连接数设置1
          connManager.setDefaultMaxPerRoute(5);//per route最大连接数设置

          client = HttpAsyncClients.custom()
            .setConnectionManager(connManager)
            .setDefaultRequestConfig(requestConfig)
            .build();
          client.start();

        }
      }
    }
    return client;
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
