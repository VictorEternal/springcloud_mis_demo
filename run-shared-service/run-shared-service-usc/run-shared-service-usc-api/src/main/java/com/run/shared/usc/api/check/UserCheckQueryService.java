package com.run.shared.usc.api.check;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "userCheckQueryService")
public interface UserCheckQueryService {

	/**
	 * 
	 * 检查登录名是否已被注册，返回true表示已经注册
	 *
	 * @param loginAccount
	 *            登录名
	 * @return
	 */
	public boolean checkLoginAccountIfExits(String loginAccount);



	/**
	 * 
	 * 检查手机号码是否已被注册，返回true表示已经注册
	 *
	 * @param mobile
	 *            手机号码
	 * @return
	 */
	public boolean checkMobileIfExits(String mobile);



	/**
	 * 
	 * 检查邮件是否已被注册，返回true表示已经注册
	 *
	 * @param email
	 *            邮件地址
	 * @return
	 */
	public boolean checkEmailIfExits(String email);

}