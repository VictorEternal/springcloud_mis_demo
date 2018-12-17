package com.run.apidoc.utils;

import java.util.Map;

public class MapUtils {
	@SuppressWarnings("rawtypes")
	public static Object getKeyByValue(Map map, Object value) {
		for (Object key : map.keySet()) {
			if (map.get(key).equals(value)) {
				return key;
			}
		}
		return null;
	}
}
