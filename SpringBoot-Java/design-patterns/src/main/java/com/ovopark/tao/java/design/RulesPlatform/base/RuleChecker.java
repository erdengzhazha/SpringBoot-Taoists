package com.ovopark.tao.java.design.RulesPlatform.base;

import com.ovopark.tao.java.design.RulesPlatform.constant.Result;
import com.ovopark.tao.java.design.RulesPlatform.domain.request.RuleCheckRequest;

/**
 * RuleChecker
 *
 * @author caisl
 * @since 2019-01-21
 */
public interface RuleChecker<T extends Rule, REQ extends RuleCheckRequest> extends Checker<T> {
    /**
     * 规则检验
     *
     * @param rule
     * @param request
     * @return
     */
    Result validate(T rule, REQ request);
}
