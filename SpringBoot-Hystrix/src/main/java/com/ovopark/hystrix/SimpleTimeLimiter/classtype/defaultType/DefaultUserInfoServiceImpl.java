package com.ovopark.hystrix.SimpleTimeLimiter.classtype.defaultType;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.ovopark.hystrix.SimpleTimeLimiter.classtype.UserInfoService;
import com.ovopark.hystrix.SimpleTimeLimiter.classtype.UserInfoServiceImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2020/12/3 9:45 下午
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
public class DefaultUserInfoServiceImpl implements UserInfoService02 {


  // 设置为可配置
  private static final long TIME_OUT_DURATION = 600;

  private static UserInfoService02 userInfoServiceProxy ;

  static {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    SimpleTimeLimiter simpleTimeLimiter = SimpleTimeLimiter.create(executorService);
    UserInfoService02 userInfoService02 =new DefaultUserInfoServiceImpl();
    userInfoServiceProxy = simpleTimeLimiter.newProxy(userInfoService02, UserInfoService02.class, TIME_OUT_DURATION, TimeUnit.MILLISECONDS);
  }

  @Override
  public String getUserName(String info) throws InterruptedException {
    System.out.println("123");
    Thread.sleep(500);
    System.out.println("456");
    return info;
  }

  @Override
  public String getUserNameWithTimeout(String info) throws InterruptedException {
    return userInfoServiceProxy.getUserName(info);
  }

//  @Override
//  public String getSex(String txt) {
//    return "";
//  }
//
//  @Override
//  public String getSexWithTimeout(String txt) {
//    return userInfoServiceProxy.getSex(txt);
//  }


  public static void main(String[] args) throws InterruptedException {
    UserInfoService02 userInfoService = new DefaultUserInfoServiceImpl();
    String result = userInfoService.getUserNameWithTimeout("tim");
    System.out.println("result is:" + result);
  }

}
