package com.ovopark.tao.java.design.RulesPlatform.engine;
import com.ovopark.tao.java.design.RulesPlatform.base.Rule;
import com.ovopark.tao.java.design.RulesPlatform.base.RuleChecker;
import com.ovopark.tao.java.design.RulesPlatform.constant.Result;
import com.ovopark.tao.java.design.RulesPlatform.domain.request.ActivityPartRuleRequest;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ActivityRuleEngine
 *
 * @author caisl
 * @since 2019-01-22
 */
@Component
public class ActivityRuleEngine extends CheckerEngine<Rule, RuleChecker> {
    /**
     * 规则引擎执行方法
     *
     * @param request
     * @param rules
     * @return
     */
    public Result<String> validate(final ActivityPartRuleRequest request, List<Rule> rules) {
        return (
          new CheckerProcess<Rule>() {
            public Result _process(Rule rule) {
                // 根据Rule类型获取对应的检查引擎
                RuleChecker checker = ActivityRuleEngine.this.getChecker(rule);
                // 根据对应的引擎执行校验
                Result result = checker.validate(rule, request);
                //返回校验结果
                return result;
            }
        }).processSingleRule(rules);
    }

}
