package com.ovopark.hystrix.SimpleTimeLimiter;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.ovopark.hystrix.SimpleTimeLimiter.classtype.UserInfoService;
import com.ovopark.hystrix.SimpleTimeLimiter.classtype.UserInfoServiceImpl;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2020/12/3 9:43 下午
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
public class SimpleDemo {

  /**
   *
   */
  @Test
  public  void simpleDemo() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    SimpleTimeLimiter simpleTimeLimiter = SimpleTimeLimiter.create(executorService);
    UserInfoService userInfoService = new UserInfoServiceImpl();
    UserInfoService userInfoService1 = simpleTimeLimiter.newProxy(userInfoService, UserInfoService.class, 50, TimeUnit.MILLISECONDS);
    try {
      String userName = userInfoService1.getUserName();
      System.out.println("userName:" + userName);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
