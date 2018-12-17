package com.run.shared.service.usc.check;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.run.service.common.entity.constants.MongoConstants;
import com.run.shared.usc.api.check.UserCheckQueryService;

public class UserRpcCheckQueryServiceImpl implements UserCheckQueryService {
	@Autowired
	private MongoTemplate ucsTemplate;



	@Override
	@SuppressWarnings("unchecked")
	public boolean checkLoginAccountIfExits(String loginAccount) {
		Query query = new Query();
		Criteria.where("loginAccount").is(loginAccount);
		List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) ucsTemplate.find(query,
				new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
		if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
			return true;
		}

		return false;
	}



	@Override
	@SuppressWarnings("unchecked")
	public boolean checkMobileIfExits(String mobile) {
		Query query = new Query();
		query.addCriteria(Criteria.where("mobile").is(mobile));
		List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) ucsTemplate.find(query,
				new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
		if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
			return true;
		}
		return false;
	}



	@Override
	@SuppressWarnings("unchecked")
	public boolean checkEmailIfExits(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) ucsTemplate.find(query,
				new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
		if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
			return true;
		}
		return false;
	}

}
