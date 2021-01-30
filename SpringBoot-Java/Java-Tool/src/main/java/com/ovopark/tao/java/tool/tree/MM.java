package com.ovopark.tao.java.tool.tree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MM
 * @Description: TODO(记录结果集,继承HashMap)
 * 没有MM，你可以new一个啊
 * @author Remiel_Mercy xuefei_fly@126.com
 * @date 2017年8月4日 上午8:50:26
 */
public class MM extends HashMap<String,Object> implements Serializable {
    private static final long serialVersionUID = -202812801618427021L;

    public MM set(String column, Object value) {
        super.put(column, value);
        return this;
    }

    public MM setColumns(Map<String, Object> columns) {
        Map<String, Object> map = new HashMap<String, Object>();
        for(Entry<String, Object> entry : columns.entrySet()){
            map.put(entry.getKey(),  entry.getValue());
        }
        super.putAll(map);
        return this;
    }

    public MM setColumns(MM record) {
        super.putAll(record);
        return this;
    }

    public MM remove(String column) {
        super.remove(column);
        return this;
    }

    public MM remove(String... columns) {
        if (columns != null)
            for (String c : columns)
                super.remove(c);
        return this;
    }

    public void clear() {
        super.clear();
    }

    public String getStr(String column) {
        return (String)super.get(column);
    }

    public Integer getInt(String column) {
        return (Integer)super.get(column);
    }

    public Long getLong(String column) {
        return (Long)super.get(column);
    }

    public java.math.BigInteger getBigInteger(String column) {
        return (java.math.BigInteger)super.get(column);
    }

    public java.util.Date getDate(String column) {
        return (java.util.Date)super.get(column);
    }

    public java.sql.Time getTime(String column) {
        return (java.sql.Time)super.get(column);
    }

    public java.sql.Timestamp getTimestamp(String column) {
        return (java.sql.Timestamp)super.get(column);
    }

    public Double getDouble(String column) {
        return (Double)super.get(column);
    }

    public Float getFloat(String column) {
        return (Float)super.get(column);
    }

    public Boolean getBoolean(String column) {
        return (Boolean)super.get(column);
    }

    public java.math.BigDecimal getBigDecimal(String column) {
        return (java.math.BigDecimal)super.get(column);
    }
}
