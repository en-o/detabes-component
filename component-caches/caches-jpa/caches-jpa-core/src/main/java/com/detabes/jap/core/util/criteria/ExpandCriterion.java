package com.detabes.jap.core.util.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * jpa 查询工具之构建sql
 * @author tnnn
 */
public interface ExpandCriterion {
	/**
	 *  sql 表达式连接词
	 */
	enum Operator {
		/**
		 *  等于
		 */
		EQ,
		/**
		 * 不相等
		 */
		NE,
		/**
		 * 模糊
		 */
		LIKE,
		/**
		 * 大于
		 */
		GT,
		/**
		 * 小于
		 */
		LT,
		/**
		 * 大于等于
		 */
		GTE,
		/**
		 * 小于等于
		 */
		LTE,
		/**
		 * 并且
		 */
		AND,
		/**
		 * 或者
		 */
		OR
	}

	/**
	 *   Predicate 语句
	 * @param root  root
	 * @param query query
	 * @param builder builder
	 * @return Predicate
	 */
	Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
						  CriteriaBuilder builder);
}