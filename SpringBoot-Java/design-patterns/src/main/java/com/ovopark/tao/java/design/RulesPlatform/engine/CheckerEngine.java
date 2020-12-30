package com.ovopark.tao.java.design.RulesPlatform.engine;
import com.ovopark.tao.java.design.RulesPlatform.base.Checker;
import com.ovopark.tao.java.design.RulesPlatform.base.Rule;
import com.ovopark.tao.java.design.RulesPlatform.constant.Result;
import com.ovopark.tao.java.design.system.helper.BeanHelper;
import com.ovopark.tao.java.design.system.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * CheckerEngine
 *
 * @author caisl
 * @since 2019-01-22
 */
public abstract class CheckerEngine<T extends Rule, C extends Checker>  {
    public static final String CHECKER = "Checker";

    @Resource
    protected BeanHelper beanHelper;


    /**
     * 检查规则配置格式
     *
     * @param _checkList
     * @return
     */
    public Result check(List<T> _checkList) {
        return (
          new CheckerProcess<T>() {
            public Result _process(T _check) {
                return CheckerEngine.this.getChecker(_check).check(_check);
            }
        }).process(_checkList);
    }

    /**
     * 获取对应的检查类
     *
     * @param rule
     * @return
     */
    protected C getChecker(T rule) {
        return (C) this.beanHelper.getBean(this.getName(rule.getClass()));
    }

    /**
     * 组装名称
     *
     * @param clazz
     * @return
     */
    private String getName(Class clazz) {
        String clazzName = clazz.getSimpleName() + CHECKER;
        return StringUtils.uncapitalize(clazzName);
    }

    /**
     * 内部执行类
     *
     * @param <T>
     */
    public abstract class CheckerProcess<T> {
        public CheckerProcess() {
        }
        /**
         * 执行方法
         *
         * @param checkList
         * @return
         */
        public Result<List<String>> process(List<T> checkList) {
            if (CollectionUtils.isEmpty(checkList)) {
                return ResultUtil.successResult(Collections.EMPTY_LIST);
            } else {
                Result codeDuplicate = checkCodeDuplicate(checkList);
                if (!ResultUtil.isResultSuccess(codeDuplicate)) {
                    return codeDuplicate;
                }
                List<String> msg = new ArrayList();
                Iterator<T> ruleChecker = checkList.iterator();
                while (ruleChecker.hasNext()) {
                    T checker = ruleChecker.next();
                    Result checkResult = this._process(checker);
                    if (!checkResult.isSuccess()) {
                        msg.add(checkResult.getMessage());
                    }
                }
                if (!CollectionUtils.isEmpty(msg)) {
                    return ResultUtil.successResult(msg);
                } else {
                    return ResultUtil.successResult(Collections.EMPTY_LIST);
                }
            }
        }

        /**
         * 执行方法
         *
         * @param checkList
         * @return
         */
        public Result<String> processSingleRule(List<T> checkList) {
            if (CollectionUtils.isEmpty(checkList)) {
                // 如果list集合为空
                return ResultUtil.successResult(Collections.EMPTY_LIST);
            } else {
                // list集合不为空
                //检查list中的code是否重复
                Result codeDuplicate = checkCodeDuplicate(checkList);
                //如果结果为fail
                if (!ResultUtil.isResultSuccess(codeDuplicate)) {
                    return codeDuplicate;
                }
                Iterator<T> ruleChecker = checkList.iterator();
                while (ruleChecker.hasNext()) {
                    T checker = ruleChecker.next();
                    Result checkResult = this._process(checker);
                    // 如果非 false
                    if (!checkResult.isSuccess()) {
                        return ResultUtil.failResult("",checkResult.getMessage());
                    }
                }
                // 如果成功
                return ResultUtil.successResult(StringUtils.EMPTY);
            }
        }

        /**
         * 检查code是否重复
         *
         * @param checkList
         * @return
         */
        private Result checkCodeDuplicate(List<T> checkList) {
            if (checkList.get(0) instanceof Rule) {
                Map ruleMap = new HashMap();
                Iterator checkerIterator = checkList.iterator();
                while (checkerIterator.hasNext()) {
                    Rule rule = (Rule) checkerIterator.next();
                    String code = rule.getCode();
                    if (ruleMap.get(code) != null) {
                        return ResultUtil.failResult("", "rule code重复:" + code);
                    }
                    ruleMap.put(code, rule);
                }
            }
            return ResultUtil.successResult(null);
        }

        /**
         * @param checker
         * @return
         */
        public abstract Result _process(T checker);

    }

}
