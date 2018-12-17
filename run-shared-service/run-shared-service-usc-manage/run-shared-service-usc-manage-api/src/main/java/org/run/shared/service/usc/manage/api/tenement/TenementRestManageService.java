package org.run.shared.service.usc.manage.api.tenement;


/**
 * 
* 用户后台管理租户信息维护
* rest服务		
* @author: 邓帅
* @version: 1.0, 2016年12月2日
 */
public interface TenementRestManageService {
	
	/**
	 * 
	* 根据租户ID获取租户数据
	* 
	* @param tenementId
	* @return tenementInfo json字符串
	 */
	String getTenementInfoByID(String tenementId);

	/**
	 * 
	* 根据查询条件获取分页数据信息
	* @param queryParams{"queryParams":{"key1":"value1","key2":"value2"},"pageIndex":1,"pageSize":10}
	* @return List<Tenement> json字符串
	 */
	String getTenementInfo(String queryParamsInfo);

	/**
	 * 
	* 根据租户ID修改租户数据信息
	* 
	* @param tenementInfo tenementInfo对象json字符串
	* @return
	 */
	String updateTenementInfo(String tenementInfo);

	/**
	 * 
	* 根据租户ID删除租户数据信息
	*
	* @param tenementId
	* @return
	 */
	String deleteTenementInfoByID(String tenementId);

	/**
	 * 
	* 新增租户数据信息
	*
	* @param tenementInfo
	* @return
	 */
	String saveTenementInfo(String tenementInfo);

	/**
	 * 
	* 获取所有租户数据信息
	*
	* @return  [{"tenementId":"123","tenementName":"123"}]
	 */
	String getTenementDic();
}
