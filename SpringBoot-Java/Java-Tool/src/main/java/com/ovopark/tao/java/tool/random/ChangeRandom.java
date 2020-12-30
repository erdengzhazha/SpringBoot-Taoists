package com.ovopark.tao.java.tool.random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 洗牌算法
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2020/12/24 7:08 下午
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
public class ChangeRandom {

  public static void main(String[] args) {
    int len = 30;
    int[] arr = new int[len];
    for(int i = 0;i<len;i++){
      arr[i] = i+1;
    }
    print(arr);
    System.out.println();
    // 交换替换者
    int k;
    for(int i = len - 1; i >= 0 ; i -- ) {
      // 随机
      int i1 = new Random().nextInt(len);
      // 交换
      k = arr[i];
      arr[i] = arr[i1%len];
      arr[i1%len] = k;
    }
    System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss sss").format(new Date()));
    print(arr);
  }
  public  static void print(int[] a){
    for(int i = 0;i<a.length;i++){
      System.out.print(a[i]+",");
    }
  }
}
