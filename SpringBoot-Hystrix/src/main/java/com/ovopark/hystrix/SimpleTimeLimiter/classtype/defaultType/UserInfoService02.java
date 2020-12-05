package com.ovopark.hystrix.SimpleTimeLimiter.classtype.defaultType;

/*** * http://www.qiusunzuo.com/    
 *           _.._        ,------------.
 *        ,'      `.    ( 我最屌！听我的！)
 *       /  __) __` \    `-,----------'
 *      (  (`-`(-')  ) _.-'
 *      /)  \  = /  (
 *     /'    |--' .  \
 *    (  ,---|  `-.)__`
 *     )(  `-.,--'   _`-.
 *    '/,'          (  Uu",
 *     (_       ,    `/,-' )
 *     `.__,  : `-'/  /`--'  
 *       |     `--'  |
 *       `   `-._   /
 *        \        (
 *        /\ .      \.  
 *       / |` \     ,-\
 *      /  \| .)   /   \
 *     ( ,'|\    ,'     :
 *     | \,`.`--"/      }
 *     `,'    \  |,'    /
 *    / "-._   `-/      |   @Description 请填写接口注释
 *    "-.   "-.,'|     ;    @Auther Xiu_Er 13813641925@163.com
 *   /        _/["---'""]   @Date 2020/12/3 9:19 下午
 *  :        /  |"-     '   @Version 1.0.0   
 *  '           |      /
 *              `      |
 */
public interface UserInfoService02 {
  String getUserName(String info) throws InterruptedException;

  String getUserNameWithTimeout(String info) throws  InterruptedException;
}
