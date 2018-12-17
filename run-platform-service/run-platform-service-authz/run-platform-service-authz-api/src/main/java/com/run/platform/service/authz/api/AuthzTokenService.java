package com.run.platform.service.authz.api;

import com.run.service.common.entity.Result;

public interface AuthzTokenService {
	/**
	 * 
	 * 验证token
	 *
	 * @param authInfo
	 *            验证参数，包括token字符串（token）、用户唯一编号或者接入方编码（userId）
	 * @param
	 * @return
	 */
	public Result authenticate(String authInfo);



	/**
	 * 
	 * 生成token
	 *
	 * @param userInfo
	 *            验证参数，包括token字符串（token）、用户唯一编号或者接入方编码（userId）
	 * @param
	 * @return
	 */
	public Result createToken(String userInfo);



	/**
	 * 
	 * 移除token
	 *
	 * @param userInfo
	 *            验证参数，包括token字符串（token）、用户唯一编号或者接入方编码（userId）
	 * @param
	 * @return
	 */
	public Result removeToken(String userInfo);



	/**
	 * 
	 * 根据token获取userId
	 *
	 * @param tokenInfo
	 *            验证参数，包括token字符串（token））
	 * @param
	 * @return
	 */
	public Result getUserId(String tokenInfo);
}
