package com.ovopark.tao.mp.mybatis;

import com.ovopark.tao.mp.MPApplication;
import com.ovopark.tao.mp.mygenerator.entity.MyUser;
import com.ovopark.tao.mp.mygenerator.mapper.MyUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/2/28 7:07 下午
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
@SpringBootTest(classes = MPApplication.class)
public class SelectTest {

  @Autowired
  private MyUserMapper myUserMapper;

  @Test
  public void select01(){
    List<MyUser> myUsers = myUserMapper.selectUserList();
    myUsers.stream().forEach(a -> System.out.println(a.toString()));

  }
}
