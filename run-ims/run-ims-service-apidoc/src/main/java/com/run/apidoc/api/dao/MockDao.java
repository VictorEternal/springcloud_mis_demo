package com.run.apidoc.api.dao;

import com.run.apidoc.entity.Rule;

/**
 * 
 * Mock持久层
 * 
 * @author: lyc
 * @version: 1.0, 2017年2月13日
 */
public interface MockDao {
	/**
	 * get rule by action id
	 *
	 * @param actionId
	 * @return
	 */
	Rule getRule(int actionId);



	/**
	 * delete rule by action id
	 *
	 * @param actionId
	 * @return 0 success, -1 error
	 */
	int removeRule(int actionId);



	/**
	 * set rule, rule.actionId must be set
	 *
	 * @param rule
	 * @return
	 */
	int updateRule(Rule rule);



	/**
	 * add new rule for action
	 *
	 * @param rule
	 * @return
	 */
	int addRule(Rule rule);
}
