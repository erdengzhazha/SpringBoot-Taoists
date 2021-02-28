package com.ovopark.tao.kafka;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;


/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 测试类
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2020/12/30 6:49 下午
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
@SpringBootTest
public class KafkaApplication {

  @Autowired
  private KafkaTemplate<Integer, String> template;

  @Test
  public void testSimple() throws Exception {
//    template.send("topic1", 0, "{\"message\":\"雄起\",\"bussinesType\":\"push\",\"moduleKey\":\"device\",\"enterpriseId\":1084}");
//    template.flush();
//    System.out.println("发送成功");
//    Thread.sleep(1000000);
  }

}
