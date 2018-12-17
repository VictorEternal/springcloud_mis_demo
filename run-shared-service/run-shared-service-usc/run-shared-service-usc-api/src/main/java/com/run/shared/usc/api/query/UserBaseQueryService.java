package com.run.shared.usc.api.query;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.run.service.common.entity.Result;

@FeignClient(name = "userBaseQueryService")
public interface UserBaseQueryService {

	/**
	 * 
	 * 根据用户编号查询用户信息
	 *
	 * @param queryUserParam
	 *            包括同一平台信息（uscInfo）、用户编号（userId）
	 * @return
	 */
	public Result getUserbyId(String queryUserParam);



	/**
	 * 
	 * 根据用户账号查询用户信息
	 *
	 * @param queryUserParam
	 *            包括同一平台信息（uscInfo）、 账号（手机、邮箱、登陆名称-->account）
	 * @return
	 */
	public Result getUserByaccount(String queryUserParam);



	/**
	 * 
	 * 用户登录认证
	 *
	 * @param oauthRquestInfo
	 * @return
	 */
	public Result userAuthz(String oauthRquestInfo);



	/**
	 * 
	 * token获取用户信息
	 *
	 * @param tokenInfo
	 * @return
	 */
	public Result oauthquery(String tokenInfo);

}