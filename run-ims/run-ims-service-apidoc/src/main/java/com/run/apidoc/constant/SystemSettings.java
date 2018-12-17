package com.run.apidoc.constant;

public class SystemSettings {
	public static final String	projectContext			= "";
	public static final int		MOCK_SERVICE_TIMEOUT	= 1000;



	public static String GET_DEFAULT_USER_SETTINGS(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}

		if (key.equals("inform")) {
			return "";
		}

		return null;

	}

	;
}
