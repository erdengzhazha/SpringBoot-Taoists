package com.ovopark.tao.java.design.RulesPlatform;

import com.ovopark.tao.java.design.RulesPlatform.base.Rule;
import com.ovopark.tao.java.design.RulesPlatform.constant.Result;
import com.ovopark.tao.java.design.RulesPlatform.domain.pojo.StartTimeRule;
import com.ovopark.tao.java.design.RulesPlatform.domain.request.ActivityPartRuleRequest;
import com.ovopark.tao.java.design.RulesPlatform.engine.ActivityRuleEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 单个规则的测试
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2020/12/30 4:16 下午
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
public class SingleRule {
  // 引入活动规则引擎
  @Resource
  private ActivityRuleEngine activityRuleEngine;

  /**
   * 测试
   */
  @Test
  void t(){
    // 初始化请求bean
    ActivityPartRuleRequest request = new ActivityPartRuleRequest();

    // 初始化规则
    List<Rule> ruleList = new ArrayList();
    StartTimeRule startTimeRule = new StartTimeRule(System.currentTimeMillis());
    ruleList.add(startTimeRule);

    // 发动引擎获取结果
    Result<String> result = activityRuleEngine.validate(request, ruleList);

    // 断言
    Assert.isTrue(result.isSuccess());

    // 打印结果
    System.out.println(result.getModel());
  }
}
