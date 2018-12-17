package com.run.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	/**
	 * 判断手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean validateMobile(String mobile) {
		String rexgx = "^[1][34578]\\d{9}$";
		Pattern p = Pattern.compile(rexgx);
		Matcher m = p.matcher(mobile);
		return m.matches();
	}



	/**
	 * 判断是否为数字
	 * 
	 * @param number
	 * @return
	 */
	public static boolean validateNumber(String number) {
		String rexgx = "^\\d+$";
		Pattern p = Pattern.compile(rexgx);
		Matcher m = p.matcher(number);
		return m.matches();
	}



	/**
	 * 邮箱验证
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		String rexEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

		Pattern p = Pattern.compile(rexEmail);
		Matcher m = p.matcher(email);
		return m.matches();
	}



	/**
	 * 登录名验证
	 * 
	 * @param loginAccount
	 * @return
	 */
	public static boolean validateLoginAccount(String loginAccount) {
		String rexEmail = "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$";
		Pattern p = Pattern.compile(rexEmail);
		Matcher m = p.matcher(loginAccount);
		return m.matches();
	}
}
