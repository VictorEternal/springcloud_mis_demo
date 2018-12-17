
package org.run.shared.service.usc.manage.api.joinInfo;

/**
 * 
* 接入方后台管理rest服务
*		
* @author: 梅松
* @version: 1.0, 2017年1月17日
 */
public interface JoinUserTypeRestManageService {

	/**
	 * 
	* 根据接入方用户类型Code获取用户类型信息
	*
	* @param userTypeCode
	* @return JoinUserTypeInfo实体对象 json字符串
	 */
	String getJoinUserTypeInfoByUserTypeCode(String userTypeCode);

	/**
	 * 
	* 根据查询条件获取接入方用户类型分页数据信息
	* @param queryParams{"queryParams":{"key1":"value1","key2":"value2"},"pageIndex":1,"pageSize":10}
	* @return List<JoinUserTypeInfo> json字符串
	 */
	String getJoinUserTypeInfo(String queryParamsInfo);

	/**
	 * 
	* 根据接入方用户类型编号userTypeCode修改接入方用户类型信息
	*
	* @param JoinUserTypeInfo实体bean json字符串
	* @return
	 */
	String updateJoinUserTypeInfo(String joinUserTypeInfo);

	/**
	 * 
	* 
	* 根据接入方用户类型编号删除接入方用户类型信息
	* @param userTypeCode
	* @return
	 */
	String deleteJoinUserTypeInfoByUserTypeCode(String userTypeCode);

	/**
	 * 
	* 保存接入方用户类型信息
	*
	* @param JoinUserTypeInfo 实体beanjson字符串
	* @return
	 */
	String saveJoinUserTypeInfo(String joinUserTypeInfo);

	/**
	 * 
	* 获取接入方用户类型数据字典
	*
	* @return [{"userTypeCode":"123","userTypeName":"123"}]
	 */
	String getJoinUserTypeDic();
	
	/**
	 * 
	* 获取所有接入方用户类型数据
	*
	* @return List<JoinUserTypeInfo> json字符串
	 */
	String getAllJoinUserTypeInfo();

}
