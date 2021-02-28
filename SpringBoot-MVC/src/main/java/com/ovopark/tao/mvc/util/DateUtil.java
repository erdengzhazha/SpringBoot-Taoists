package com.ovopark.tao.mvc.util;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/8 5:25 下午
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author 时间工具类
 *
 */
public class DateUtil {
  //先定义时间转换的格式
  private static final SimpleDateFormat YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
  private static final Calendar cal = Calendar.getInstance();

  //测试方法
  public static void main(String[] args) throws Exception {
    Date date = new Date();
    System.out.println("时间转字符串，格式yyyyMMdd：" + DateUtil.formatYmdhms(date));
    System.out.println("字符串转时间，格式yyyyMMdd：" + DateUtil.parseYmdhms("20180606060606"));
    System.out.println("获取当前系统时间的年份：" + DateUtil.getYear());
    System.out.println("获取指定时间的年份：" + DateUtil.getYear(DateUtil.parseYmdhms("20180606060606")));
    System.out.println("获取当前系统时间的月份：" + DateUtil.getMonth());
    System.out.println("获取指定时间的月份：" + DateUtil.getMonth(DateUtil.parseYmdhms("20180606060606")));
    System.out.println("获取当前系统时间的日期：" + DateUtil.getDay());
    System.out.println("获取指定时间的日期：" + DateUtil.getDay(DateUtil.parseYmdhms("20180606060606")));
    System.out.println("获取当前系统时间的小时：" + DateUtil.getHour());
    System.out.println("获取指定时间的小时：" + DateUtil.getHour(DateUtil.parseYmdhms("20180606060606")));
    System.out.println("获取当前系统时间的分钟：" + DateUtil.getMinute());
    System.out.println("获取指定时间的分钟：" + DateUtil.getMinute(DateUtil.parseYmdhms("20180606060606")));
    System.out.println("获取当前系统时间的秒钟：" + DateUtil.getSecond());
    System.out.println("获取指定时间的秒钟：" + DateUtil.getSecond(DateUtil.parseYmdhms("20180606060606")));
  }


  /** 时间转字符串
   * @param rq
   * @return 格式：yyyyMMdd
   */
  public static final String formatYmdhms(Date rq) {
    if(rq == null){
      return "";
    }
    return YMDHMS.format(rq);
  }

  /** 字符串转时间
   * @param rq 格式：yyyyMMdd
   * @return
   * @throws Exception
   */
  public static final Date parseYmdhms(String rq) throws Exception {
    if(rq == null){
      return null;
    }
    try {
      return YMDHMS.parse(rq);
    } catch (ParseException e) {
      System.out.println("【字符转换时间异常】："+e.getMessage());
      throw new Exception( "字符转换时间异常", e);
    }
  }

  /**获取当前系统时间的年份
   * @return
   */
  public static int  getYear() {
    cal.setTime(new Date());
    return cal.get(Calendar.YEAR);
  }

  /**获取指定时间的年份
   * @param date //指定时间
   * @return
   */
  public static int  getYear( Date date) {
    cal.setTime(date);
    return cal.get(Calendar.YEAR);
  }


  /**获取当前系统时间的月份
   * @return
   */
  public static int  getMonth() {
    cal.setTime(new Date());
    return cal.get(Calendar.MONTH)+1;//不知道是系统的BUG还是怎样，获取的月份要比时间的少1，于是加个1
  }

  /**获取指定时间的月份
   * @param date //指定时间
   * @return
   */
  public static int  getMonth( Date date) {
    cal.setTime(date);
    return cal.get(Calendar.MONTH)+1;
  }

  /**获取当前系统时间的日期
   * @return
   */
  public static int  getDay() {
    cal.setTime(new Date());
    return cal.get(Calendar.DATE);
  }

  /**获取指定时间的日期
   * @param date //指定时间
   * @return
   */
  public static int  getDay( Date date) {
    cal.setTime(date);
    return cal.get(Calendar.DATE);
  }

  /**获取当前系统时间的小时
   * @return
   */
  public static int  getHour() {
    cal.setTime(new Date());
    return cal.get(Calendar.HOUR_OF_DAY);
  }

  /**获取指定时间的小时
   * @param date //指定时间
   * @return
   */
  public static int  getHour( Date date) {
    cal.setTime(date);
    return cal.get(Calendar.HOUR_OF_DAY);
  }

  /**获取当前系统时间的分钟
   * @return
   */
  public static int  getMinute() {
    cal.setTime(new Date());
    return cal.get(Calendar.MINUTE);
  }

  /**获取指定时间的分钟
   * @param date //指定时间
   * @return
   */
  public static int  getMinute( Date date) {
    cal.setTime(date);
    return cal.get(Calendar.MINUTE);
  }

  /**获取当前系统时间的秒钟
   * @return
   */
  public static int  getSecond() {
    cal.setTime(new Date());
    return cal.get(Calendar.SECOND);
  }

  /**获取指定时间的秒钟
   * @param date //指定时间
   * @return
   */
  public static int  getSecond( Date date) {
    cal.setTime(date);
    return cal.get(Calendar.SECOND);
  }

}
