
package org.run.shared.service.usc.manage.api.joinInfo;

public interface JoinInfoRestManageService {

	/**
	 * 
	* 根据appId获取接入方信息
	*
	* @param appId
	* @return Joininfo json字符串
	 */
	String getJoininfoByID(String appId);

	/**
	 * 
	* 根据查询条件获取接入方信息(分页)
	*
	* 根据查询条件获取分页数据信息
	* @param queryParams{"queryParams":{"key1":"value1","key2":"value2"},"pageIndex":1,"pageSize":10}
	* @return List<Joininfo> json字符串
	* @return
	 */
	String getJoininfo(String queryParamsInfo);

	/**
	 * 
	* 根据appId修改接入方信息
	*
	* @param Joininfo json字符串
	* @return
	 */
	String updateJoininfo(String joininfo);

	/**
	 * 
	* 根据appId删除接入方信息
	*
	* @param appId
	* @return
	 */
	String deleteJoininfoByID(String appId);

	/**
	 * 
	* 保存接入方信息
	*
	* @param Joininfo对象json字符串
	* @return
	 */
	String saveJoininfo(String joininfo);

	/**
	 * 
	* 获取所有接入方信息数据字典
	*
	* @return ["appId":"123","appName":"123"] json字符串
	 */
	String getJoinInfoDic();
	
	/**
	 * 
	* 获取所有接入方信息数据
	*
	* @return List<JoinInfo> json字符串
	 */
	String getAllJoinInfo();

}
