package com.run.shared.usc.api.curd;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.run.service.common.entity.Result;

/**
 * 
 * 用户中心基本功能数据写入操作，主要包括新增用户信息、删除用户信息、更新用户信息
 * 
 * @author: lyc
 * @version: 1.0, 2016年11月19日
 */
@FeignClient(name = "userBaseCurdService")
public interface UserBaseCurdService {

	/**
	 * 
	 * 新增用户信息
	 *
	 * @param registerInfo
	 *            新增用户信息
	 * @return
	 */
	public Result saveUser(String registerInfo);



	/**
	 * 
	 * 根据用户唯一编号更新用户信息
	 *
	 * @param updateUserInfo
	 *            更新用户信息
	 * @return
	 */

	public Result updateUser(String updateUserInfo);



	/**
	 * 根据用户编号删除用户信息
	 * 
	 * @param deleteUserInfo
	 *            删除用户信息
	 * @return
	 */
	public Result deleteUser(String deleteUserInfo);



	/**
	 * 修改密码
	 * 
	 * @param updatepasswordInfo
	 *
	 * @return
	 */
	public Result updatePassword(String updatepasswordInfo);



	/**
	 * 
	 * 修改用户安全信息(邮箱、邮箱)
	 *
	 * @param updateSecurityInfo
	 *            统一用户访问信息（uscInfo）、手机，邮箱（baseInfo）
	 * @return
	 */
	public Result updateUserSecurity(String updateSecurityInfo);



	/**
	 * 
	 * 重置密码
	 *
	 * @param resetpasswordInfo
	 *            "quInfo": { "account": "string", "newPassword": "string" }
	 * @return
	 */
	public Result resetPassword(String resetpasswordInfo);



	/**
	 * 
	 * 合并用户
	 *
	 * @param mergeInfo
	 * @return
	 */
	public Result merge(String mergeInfo);



	/**
	 * 
	 * 退出登录
	 *
	 * @param loginInfo
	 * @return
	 */
	public Result loginout(String loginInfo);

}