package com.run.base.util;

import java.security.SecureRandom;
import java.util.Random;

import com.run.common.util.MDCoder;

/**
 * 
 * <strong>Title : com.sgcc.mall.uc.util.VerifyCodeGenerator.java </strong>.
 * <br>
 * <strong>Description : 随机验证码生产工具</strong> <br>
 * <strong>Create on : 2014年11月13日 下午2:18:16 </strong>. <br>
 * <p>
 * <strong>Copyright (C) sgcc Software Co.,Ltd.</strong> <br>
 * </p>
 * 
 * @author lijun <br>
 * @version <strong></strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class VerifyCodeUtil {

	private final static char[]			CHARS			= new char[] { '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'0' };
	private final static int			SIZE			= CHARS.length;

	private final static SecureRandom	SECURERANDOM	= new SecureRandom();



	public static String getRandomString(int length) {
		StringBuffer rs = new StringBuffer();
		for (int i = 0; i < length; i++) {
			rs.append(CHARS[SECURERANDOM.nextInt(SIZE)]);
		}
		return rs.toString();
	}



	/**
	 * 
	 * /** 得到六位随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomSixNum(int length) {
		return ((int) (new SecureRandom().nextDouble() * 900000 + 100000)) + "";
	}



	/**
	 * 得到GWSC+六位 随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomSixNumGWSC() {
		return "GWSC" + ((int) (new SecureRandom().nextDouble() * 900000 + 100000)) + "";
	}



	/**
	 * 随机获取字母和数字组合 生成用户邀请码
	 * 
	 * @param length
	 *            字符串长度
	 * @return 随机字符串
	 */
	public static String getCharAndNumr(int length) {
		StringBuffer val = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 字符串,取得大写字母还是小写字母
				int choice = 65;
				val.append((char) (choice + random.nextInt(26)));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				// 数字
				val.append(String.valueOf(random.nextInt(10)));
			}
		}
		return val.toString();
	}



	/**
	 * 根据接入方code自动生成用户名 规则：接入方简拼大写+userId MD5_16位加密小写
	 * 
	 * @param appCode
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static String getRandomLoginAcount(String userId) throws Exception {
		String loginAcount = "RUN";
		userId = MDCoder.encodeMD5_16Hex(userId).toLowerCase();
		return loginAcount + userId;
	}
}
