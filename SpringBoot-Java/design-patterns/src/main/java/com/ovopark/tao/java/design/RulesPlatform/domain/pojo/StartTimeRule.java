package com.ovopark.tao.java.design.RulesPlatform.domain.pojo;


import com.ovopark.tao.java.design.RulesPlatform.base.AbstractRule;

/**
 * StartTimeRule
 *
 * @author caisl
 * @since 2019-01-22
 */
public class StartTimeRule extends AbstractRule<Long> {
    /**
     * 构造函数的rule
     *
     * @param rule
     */
    public StartTimeRule(Long rule) {
        super(rule);
    }

    public Long getStartTime() {
        return this.getRule();
    }

    @Override
    public String getCode() {
        return StartTimeRule.class.getSimpleName();
    }

    @Override
    public int getSort() {
        return 21;
    }

    @Override
    public String getDisplay() {
        return "活动开始时间";
    }
}
