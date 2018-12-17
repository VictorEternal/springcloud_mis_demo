package com.run.common.util;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 接口参数检查工具
 * 
 * @author: lyc
 * @version: 1.0, 2017年1月11日
 */
public class ParamChecker {
	/**
	 * 
	 * 检测入参是否非空
	 *
	 * @param param
	 * @return
	 */
	public static boolean isBlank(String param) {
		if (StringUtils.isBlank(param)) {
			return true;
		}
		return false;
	}



	/**
	 * 
	 * 检测入参是否为标准的JSON字符串
	 * 
	 *
	 * @param param
	 * @return
	 */
	public static boolean isNotMatchJson(String param) {
		try {
			JSON.parseObject(param);
		} catch (Exception e) {
			return true;
		}
		return false;
	}



	/**
	 * 
	 * 检查json对象的Key的值是否为空
	 *
	 * @param param
	 *            Json对象
	 * @param key
	 *            键值
	 * @return 如果为空，返回false，不为空则为true
	 */
	public static boolean jsonContainsKeyAndValueIsBlank(JSONObject param, String key) {
		if (param.isEmpty()) {
			return false;
		}
		if (StringUtils.isBlank(key)) {
			return false;
		}
		if (!param.containsKey(key)) {
			return false;
		}
		if (param.containsKey(key)) {
			String str = param.getString(key);
			if (StringUtils.isNotBlank(str)) {
				return true;
			}
		}
		return false;
	}

}
