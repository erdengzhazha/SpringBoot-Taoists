package com.ovopark.tao.java.design.RulesPlatform.checker;

import com.ovopark.tao.java.design.RulesPlatform.base.RuleChecker;
import com.ovopark.tao.java.design.RulesPlatform.constant.Result;
import com.ovopark.tao.java.design.RulesPlatform.constant.ResultCodeEnum;
import com.ovopark.tao.java.design.RulesPlatform.domain.pojo.StartTimeRule;
import com.ovopark.tao.java.design.RulesPlatform.domain.request.RuleCheckRequest;
import com.ovopark.tao.java.design.system.util.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * StartTimeRuleChecker
 *
 * @author caisl
 * @since 2019-01-18
 */
@Component
public class StartTimeRuleChecker implements RuleChecker<StartTimeRule, RuleCheckRequest> {
    @Override
    public Result check(StartTimeRule rule) {
        if(rule.getRule() == null){
            return ResultUtil.failResult(ResultCodeEnum.RULE_PARAM_ERROR.getCode(), "活动开始时间为null");
        }
        return ResultUtil.successResult(null);
    }

    @Override
    public Result<String> validate(StartTimeRule rule, RuleCheckRequest request) {
        long l = System.currentTimeMillis();
        if(rule.getRule() > l){
            return ResultUtil.failResult("1111","你的时间错误的");
        }
        return ResultUtil.successResult("本规则, 朕 ,受纳了！");
    }
}
