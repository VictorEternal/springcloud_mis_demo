package com.run.apidoc.impl.dao;

import com.run.apidoc.api.dao.MockDao;
import com.run.apidoc.entity.Rule;

/**
 * 
 * Mock持久层实现类
 * 
 * @author: Administrator
 * @version: 1.0, 2017年2月13日
 */
public class MockDaoImpl implements MockDao {

	public Rule getRule(int actionId) {
		Rule rule = null;
		try {
			// To do
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rule;
	}



	public int removeRule(int actionId) {
		return 0;
	}



	public int updateRule(Rule rule) {
		return 0;
	}



	public int addRule(Rule rule) {
		return 0;
	}
}
