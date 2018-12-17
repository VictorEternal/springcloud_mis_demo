package org.run.shared.service.usc.manage.api.userInfo;

import com.run.service.common.entity.Result;

/**
 * 
* 用户后台管理
*		
* @author: 梅松
* @version: 1.0, 2016年12月15日
 */
public interface UserInfoRestManageService {

	
	/**
	 * 用户后台管理
	* 根据查询条件获取用户信息列表(分页)
	*
	* @return
	 */
	Result getUserInfo(String queryParamsInfo);


	/**
	 * 
	* 用户后台管理修改密码
	*
	* @return
	 */
	Result updatePassWord(String paramsInfo);

	/**
	 * 用户后台管理
	* 新增用户信息
	*
	* @return
	 */
	public Result saveUser(String registerInfo);
	
	/**
	 * 用户后台管理
	* 修改用户信息
	*
	* @param registerInfo  {"uscInfo":{},"userInfo":{}}
	* @return
	 */
	public Result updateUser(String updateUserParam);
	
	/**
	 * 
	* 根据_id删除用户数据
	*
	* @param userId
	* @return
	 */
	public Result deleteuserinfo(String userId);

	
	/**
	 * 
	* 查询用户信息
	*
	* @return
	 */
	public Result getUserbyId(String queryUserParam);
}
