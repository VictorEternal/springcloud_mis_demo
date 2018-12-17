package com.run.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Update;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class MongoUtils {
	/**
	 * json封装进mongodb的Update对象中
	 * 
	 * @param jsonString
	 * @return
	 */
	public static final Update JsonStringToUpdate(String jsonString) {
		Update update = new Update();
		try {
			if (StringUtils.isNotBlank(jsonString)) {
				JSONObject parseObject = JSON.parseObject(jsonString);
				for (String key : parseObject.keySet()) {
					if (parseObject.get(key) != null) {
						update.set(key, parseObject.get(key));
					}
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (SecurityException e) {
		}
		return update;
	}

}
