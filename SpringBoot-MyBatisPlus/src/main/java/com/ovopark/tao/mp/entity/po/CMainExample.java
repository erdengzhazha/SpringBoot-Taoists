package com.ovopark.tao.mp.entity.po;

import java.util.ArrayList;
import java.util.List;

public class CMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CMainExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andStart1IsNull() {
            addCriterion("start1 is null");
            return (Criteria) this;
        }

        public Criteria andStart1IsNotNull() {
            addCriterion("start1 is not null");
            return (Criteria) this;
        }

        public Criteria andStart1EqualTo(Double value) {
            addCriterion("start1 =", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1NotEqualTo(Double value) {
            addCriterion("start1 <>", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1GreaterThan(Double value) {
            addCriterion("start1 >", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1GreaterThanOrEqualTo(Double value) {
            addCriterion("start1 >=", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1LessThan(Double value) {
            addCriterion("start1 <", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1LessThanOrEqualTo(Double value) {
            addCriterion("start1 <=", value, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1In(List<Double> values) {
            addCriterion("start1 in", values, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1NotIn(List<Double> values) {
            addCriterion("start1 not in", values, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1Between(Double value1, Double value2) {
            addCriterion("start1 between", value1, value2, "start1");
            return (Criteria) this;
        }

        public Criteria andStart1NotBetween(Double value1, Double value2) {
            addCriterion("start1 not between", value1, value2, "start1");
            return (Criteria) this;
        }

        public Criteria andStart2IsNull() {
            addCriterion("start2 is null");
            return (Criteria) this;
        }

        public Criteria andStart2IsNotNull() {
            addCriterion("start2 is not null");
            return (Criteria) this;
        }

        public Criteria andStart2EqualTo(Double value) {
            addCriterion("start2 =", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2NotEqualTo(Double value) {
            addCriterion("start2 <>", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2GreaterThan(Double value) {
            addCriterion("start2 >", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2GreaterThanOrEqualTo(Double value) {
            addCriterion("start2 >=", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2LessThan(Double value) {
            addCriterion("start2 <", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2LessThanOrEqualTo(Double value) {
            addCriterion("start2 <=", value, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2In(List<Double> values) {
            addCriterion("start2 in", values, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2NotIn(List<Double> values) {
            addCriterion("start2 not in", values, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2Between(Double value1, Double value2) {
            addCriterion("start2 between", value1, value2, "start2");
            return (Criteria) this;
        }

        public Criteria andStart2NotBetween(Double value1, Double value2) {
            addCriterion("start2 not between", value1, value2, "start2");
            return (Criteria) this;
        }

        public Criteria andStart3IsNull() {
            addCriterion("start3 is null");
            return (Criteria) this;
        }

        public Criteria andStart3IsNotNull() {
            addCriterion("start3 is not null");
            return (Criteria) this;
        }

        public Criteria andStart3EqualTo(Double value) {
            addCriterion("start3 =", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3NotEqualTo(Double value) {
            addCriterion("start3 <>", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3GreaterThan(Double value) {
            addCriterion("start3 >", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3GreaterThanOrEqualTo(Double value) {
            addCriterion("start3 >=", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3LessThan(Double value) {
            addCriterion("start3 <", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3LessThanOrEqualTo(Double value) {
            addCriterion("start3 <=", value, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3In(List<Double> values) {
            addCriterion("start3 in", values, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3NotIn(List<Double> values) {
            addCriterion("start3 not in", values, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3Between(Double value1, Double value2) {
            addCriterion("start3 between", value1, value2, "start3");
            return (Criteria) this;
        }

        public Criteria andStart3NotBetween(Double value1, Double value2) {
            addCriterion("start3 not between", value1, value2, "start3");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNull() {
            addCriterion("total_num is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumIsNotNull() {
            addCriterion("total_num is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumEqualTo(Integer value) {
            addCriterion("total_num =", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotEqualTo(Integer value) {
            addCriterion("total_num <>", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThan(Integer value) {
            addCriterion("total_num >", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_num >=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThan(Integer value) {
            addCriterion("total_num <", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumLessThanOrEqualTo(Integer value) {
            addCriterion("total_num <=", value, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumIn(List<Integer> values) {
            addCriterion("total_num in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotIn(List<Integer> values) {
            addCriterion("total_num not in", values, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumBetween(Integer value1, Integer value2) {
            addCriterion("total_num between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andTotalNumNotBetween(Integer value1, Integer value2) {
            addCriterion("total_num not between", value1, value2, "totalNum");
            return (Criteria) this;
        }

        public Criteria andMainWinIsNull() {
            addCriterion("main_win is null");
            return (Criteria) this;
        }

        public Criteria andMainWinIsNotNull() {
            addCriterion("main_win is not null");
            return (Criteria) this;
        }

        public Criteria andMainWinEqualTo(Integer value) {
            addCriterion("main_win =", value, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinNotEqualTo(Integer value) {
            addCriterion("main_win <>", value, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinGreaterThan(Integer value) {
            addCriterion("main_win >", value, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinGreaterThanOrEqualTo(Integer value) {
            addCriterion("main_win >=", value, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinLessThan(Integer value) {
            addCriterion("main_win <", value, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinLessThanOrEqualTo(Integer value) {
            addCriterion("main_win <=", value, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinIn(List<Integer> values) {
            addCriterion("main_win in", values, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinNotIn(List<Integer> values) {
            addCriterion("main_win not in", values, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinBetween(Integer value1, Integer value2) {
            addCriterion("main_win between", value1, value2, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainWinNotBetween(Integer value1, Integer value2) {
            addCriterion("main_win not between", value1, value2, "mainWin");
            return (Criteria) this;
        }

        public Criteria andMainFlatIsNull() {
            addCriterion("main_flat is null");
            return (Criteria) this;
        }

        public Criteria andMainFlatIsNotNull() {
            addCriterion("main_flat is not null");
            return (Criteria) this;
        }

        public Criteria andMainFlatEqualTo(Integer value) {
            addCriterion("main_flat =", value, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatNotEqualTo(Integer value) {
            addCriterion("main_flat <>", value, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatGreaterThan(Integer value) {
            addCriterion("main_flat >", value, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatGreaterThanOrEqualTo(Integer value) {
            addCriterion("main_flat >=", value, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatLessThan(Integer value) {
            addCriterion("main_flat <", value, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatLessThanOrEqualTo(Integer value) {
            addCriterion("main_flat <=", value, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatIn(List<Integer> values) {
            addCriterion("main_flat in", values, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatNotIn(List<Integer> values) {
            addCriterion("main_flat not in", values, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatBetween(Integer value1, Integer value2) {
            addCriterion("main_flat between", value1, value2, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainFlatNotBetween(Integer value1, Integer value2) {
            addCriterion("main_flat not between", value1, value2, "mainFlat");
            return (Criteria) this;
        }

        public Criteria andMainLoseIsNull() {
            addCriterion("main_lose is null");
            return (Criteria) this;
        }

        public Criteria andMainLoseIsNotNull() {
            addCriterion("main_lose is not null");
            return (Criteria) this;
        }

        public Criteria andMainLoseEqualTo(Integer value) {
            addCriterion("main_lose =", value, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseNotEqualTo(Integer value) {
            addCriterion("main_lose <>", value, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseGreaterThan(Integer value) {
            addCriterion("main_lose >", value, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseGreaterThanOrEqualTo(Integer value) {
            addCriterion("main_lose >=", value, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseLessThan(Integer value) {
            addCriterion("main_lose <", value, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseLessThanOrEqualTo(Integer value) {
            addCriterion("main_lose <=", value, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseIn(List<Integer> values) {
            addCriterion("main_lose in", values, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseNotIn(List<Integer> values) {
            addCriterion("main_lose not in", values, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseBetween(Integer value1, Integer value2) {
            addCriterion("main_lose between", value1, value2, "mainLose");
            return (Criteria) this;
        }

        public Criteria andMainLoseNotBetween(Integer value1, Integer value2) {
            addCriterion("main_lose not between", value1, value2, "mainLose");
            return (Criteria) this;
        }

        public Criteria andWinRateIsNull() {
            addCriterion("win_rate is null");
            return (Criteria) this;
        }

        public Criteria andWinRateIsNotNull() {
            addCriterion("win_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWinRateEqualTo(Double value) {
            addCriterion("win_rate =", value, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateNotEqualTo(Double value) {
            addCriterion("win_rate <>", value, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateGreaterThan(Double value) {
            addCriterion("win_rate >", value, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateGreaterThanOrEqualTo(Double value) {
            addCriterion("win_rate >=", value, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateLessThan(Double value) {
            addCriterion("win_rate <", value, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateLessThanOrEqualTo(Double value) {
            addCriterion("win_rate <=", value, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateIn(List<Double> values) {
            addCriterion("win_rate in", values, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateNotIn(List<Double> values) {
            addCriterion("win_rate not in", values, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateBetween(Double value1, Double value2) {
            addCriterion("win_rate between", value1, value2, "winRate");
            return (Criteria) this;
        }

        public Criteria andWinRateNotBetween(Double value1, Double value2) {
            addCriterion("win_rate not between", value1, value2, "winRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateIsNull() {
            addCriterion("flat_rate is null");
            return (Criteria) this;
        }

        public Criteria andFlatRateIsNotNull() {
            addCriterion("flat_rate is not null");
            return (Criteria) this;
        }

        public Criteria andFlatRateEqualTo(Double value) {
            addCriterion("flat_rate =", value, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateNotEqualTo(Double value) {
            addCriterion("flat_rate <>", value, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateGreaterThan(Double value) {
            addCriterion("flat_rate >", value, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateGreaterThanOrEqualTo(Double value) {
            addCriterion("flat_rate >=", value, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateLessThan(Double value) {
            addCriterion("flat_rate <", value, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateLessThanOrEqualTo(Double value) {
            addCriterion("flat_rate <=", value, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateIn(List<Double> values) {
            addCriterion("flat_rate in", values, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateNotIn(List<Double> values) {
            addCriterion("flat_rate not in", values, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateBetween(Double value1, Double value2) {
            addCriterion("flat_rate between", value1, value2, "flatRate");
            return (Criteria) this;
        }

        public Criteria andFlatRateNotBetween(Double value1, Double value2) {
            addCriterion("flat_rate not between", value1, value2, "flatRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateIsNull() {
            addCriterion("lose_rate is null");
            return (Criteria) this;
        }

        public Criteria andLoseRateIsNotNull() {
            addCriterion("lose_rate is not null");
            return (Criteria) this;
        }

        public Criteria andLoseRateEqualTo(Double value) {
            addCriterion("lose_rate =", value, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateNotEqualTo(Double value) {
            addCriterion("lose_rate <>", value, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateGreaterThan(Double value) {
            addCriterion("lose_rate >", value, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateGreaterThanOrEqualTo(Double value) {
            addCriterion("lose_rate >=", value, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateLessThan(Double value) {
            addCriterion("lose_rate <", value, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateLessThanOrEqualTo(Double value) {
            addCriterion("lose_rate <=", value, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateIn(List<Double> values) {
            addCriterion("lose_rate in", values, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateNotIn(List<Double> values) {
            addCriterion("lose_rate not in", values, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateBetween(Double value1, Double value2) {
            addCriterion("lose_rate between", value1, value2, "loseRate");
            return (Criteria) this;
        }

        public Criteria andLoseRateNotBetween(Double value1, Double value2) {
            addCriterion("lose_rate not between", value1, value2, "loseRate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}