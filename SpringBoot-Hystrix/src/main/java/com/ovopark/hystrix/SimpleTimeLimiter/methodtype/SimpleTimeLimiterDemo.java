package com.ovopark.hystrix.SimpleTimeLimiter.methodtype;

import com.google.common.util.concurrent.SimpleTimeLimiter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2020/12/3 10:26 下午
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
public class SimpleTimeLimiterDemo {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    SimpleTimeLimiter simpleTimeLimiter =SimpleTimeLimiter.create(executorService);
    final AtomicBoolean isInterrupted = new AtomicBoolean();

    Callable<String> task = new Callable<String>() {
      @Override
      public String call() throws Exception {
        isInterrupted.set(true);
        Thread.sleep(200);
        isInterrupted.set(false);
        return "success";
      }
    };

    try {
      String s = simpleTimeLimiter.callWithTimeout(task, 201, TimeUnit.MILLISECONDS);
      System.out.println(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
