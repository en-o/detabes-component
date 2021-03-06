package com.detabes.jap.core.util.criteria;

import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * jpa 查询工具之构建sql  具体实现
 * @author tn
 */
public class Restrictions {

    /**
     * 等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.EQ);
    }

    /**
     * 不等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression ne(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.NE);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression like(String fieldName, String value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)||"null".equals(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.LIKE);
    }


    /**
     * 大于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression gt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.GT);
    }

    /**
     * 小于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression lt(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.LT);
    }

    /**
     * 大于等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression lte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.GTE);
    }

    /**
     * 小于等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    public static SimpleExpression gte(String fieldName, Object value, boolean ignoreNull) {
        if (ignoreNull&&StringUtils.isEmpty(value)) {
            return null;
        }
        return new SimpleExpression(fieldName, value, ExpandCriterion.Operator.LTE);
    }

    /**
     * 并且
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression and(ExpandCriterion... criterions) {
        return new LogicalExpression(criterions, ExpandCriterion.Operator.AND);
    }

    /**
     * 或者
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression or(ExpandCriterion... criterions) {
        return new LogicalExpression(criterions, ExpandCriterion.Operator.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull true 空值不做查询，false 不忽略空
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, ExpandCriterion.Operator.EQ);
            i++;
        }
        return new LogicalExpression(ses, ExpandCriterion.Operator.OR);
    }
}