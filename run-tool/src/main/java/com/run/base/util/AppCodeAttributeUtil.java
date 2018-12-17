package com.run.base.util;

import com.alibaba.fastjson.JSONObject;
import com.run.service.common.entity.constants.ParamKeyConstants;

public class AppCodeAttributeUtil {
	/**
	 * 
	 * 属性过滤
	 *
	 * @param userTypeCode
	 * @param userInfo
	 * @return
	 */
	public static JSONObject filterAttribute(String userTypeCode, final JSONObject userInfo) {
		if (userInfo.isEmpty()) {
			return userInfo;
		}
		try {
			JSONObject parseObject = userInfo;
			// 编号不可更改
			if (parseObject.containsKey(ParamKeyConstants._ID)) {
				parseObject.remove(ParamKeyConstants._ID);
			}
			if (parseObject.containsKey(ParamKeyConstants.USER_ID)) {
				parseObject.remove(ParamKeyConstants.USER_ID);
			}
			if (parseObject.containsKey(ParamKeyConstants.PARTNER_ID)) {
				parseObject.remove(ParamKeyConstants.PARTNER_ID);
			}

			// 登陆账号 不可修改
			if (parseObject.containsKey(ParamKeyConstants.LOGIN_ACCOUNT)) {
				parseObject.remove(ParamKeyConstants.LOGIN_ACCOUNT);
			}

			// password不可修改
			if (parseObject.containsKey(ParamKeyConstants.PASSWORD)) {
				parseObject.remove(ParamKeyConstants.PASSWORD);
			}

			// 注册时间不可修改
			if (parseObject.containsKey(ParamKeyConstants.REGISTER_TIME)) {
				parseObject.remove(ParamKeyConstants.REGISTER_TIME);
			}

			return parseObject;
		} catch (Exception e) {
			return userInfo;
		}
	}
}
