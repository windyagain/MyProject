package com.me.account.entity.sys;


import com.me.account.common.utils.TypeCaseHelper;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;


/**
 * 公用条件查询类<br>
 * 也可以用于MVC层之间的参数传递
 */
public class Criteria {
    /**
     * 存放条件查询值
     */
    private Map<String, Object> condition;

    /**
     * 是否相异
     */
    protected boolean distinct;

    /**
     * 排序字段
     */
    protected String orderByClause;

    private int currentPage;

    private int pageSize;

    private long startRow;
    private long endRow;

    protected Criteria(Criteria example) {
        this.orderByClause = example.orderByClause;
        this.condition = example.condition;
        this.distinct = example.distinct;
        this.currentPage = example.currentPage;
        this.pageSize = example.pageSize;
        this.startRow = example.startRow;
        this.endRow = example.endRow;
    }

    public Criteria() {
        condition = new HashMap<String, Object>();
        pageSize = 10;
        currentPage = 1;
        startRow = 0;
        endRow = 10;
    }

    public void clear() {
        this.condition.clear();
        this.orderByClause = null;
        this.distinct = false;
    }

    /**
     * @param condition
     *            查询的条件名称
     * @param value
     *            查询的值
     */
    public Criteria put(String condition, Object value) {
        this.condition.put(condition, value);
        return (Criteria) this;
    }

    /**
     * 得到键值，C层和S层的参数传递时取值所用<br>
     * 自行转换对象
     *
     * @param key
     *            键值
     * @return 返回指定键所映射的值
     */
    public Object get(String key) {
        return this.condition.get(key);
    }

    /**
     * @param orderByClause
     *            排序字段
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * @param distinct
     *            是否相异
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setEndRow(long endRow) {
        this.endRow = endRow;
    }

    /**
     * 以BigDecimal类型返回键值
     *
     * @param key
     *            键名
     * @return BigDecimal 键值
     */
    public BigDecimal getAsBigDecimal(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
        if (obj != null)
            return (BigDecimal) obj;
        else
            return null;
    }

    /**
     * 以Date类型返回键值
     *
     * @param key
     *            键名
     * @return Date 键值
     */
    public Date getAsDate(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
        if (obj != null)
            return (Date) obj;
        else
            return null;
    }

    /**
     * 以Integer类型返回键值
     *
     * @param key
     *            键名
     * @return Integer 键值
     */
    public Integer getAsInteger(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
        if (obj != null)
            return (Integer) obj;
        else
            return null;
    }

    /**
     * 以Long类型返回键值
     *
     * @param key
     *            键名
     * @return Long 键值
     */
    public Long getAsLong(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Long", null);
        if (obj != null)
            return (Long) obj;
        else
            return null;
    }

    /**
     * 以String类型返回键值
     *
     * @param key
     *            键名
     * @return String 键值
     */
    public String getAsString(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "String", null);
        if (obj != null)
            return (String) obj;
        else
            return "";
    }

    /**
     * 以Timestamp类型返回键值
     *
     * @param key
     *            键名
     * @return Timestamp 键值
     */
    public Timestamp getAsTimestamp(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
        if (obj != null)
            return (Timestamp) obj;
        else
            return null;
    }

    /**
     * 以Boolean类型返回键值
     *
     * @param key
     *            键名
     * @return Timestamp 键值
     */
    public Boolean getAsBoolean(String key) {
        Object obj = TypeCaseHelper.convert(get(key), "Boolean", null);
        if (obj != null)
            return (Boolean) obj;
        else
            return null;
    }

}