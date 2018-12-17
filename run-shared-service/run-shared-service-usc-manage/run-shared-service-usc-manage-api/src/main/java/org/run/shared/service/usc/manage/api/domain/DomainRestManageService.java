package org.run.shared.service.usc.manage.api.domain;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 
* 用户后台管理域信息维护
*		
* @author: Aska
* @version: 1.0, 2017年1月17日
 */
@FeignClient(name = "DomainRestManageService")
public interface DomainRestManageService {
	
	/**
	 * 
	* 根据域ID获取域数据
	* 
	* @param domainId
	* @return DomainInfo json字符串
	 */
	String getDomainInfoByID(String domainId);

	/**
	 * 
	* 根据查询条件获取分页数据信息
	* @param queryParams{"queryParams":{"key1":"value1","key2":"value2"},"pageIndex":1,"pageSize":10}
	* @return List<DomainInfo> json字符串
	 */
	String getDomainInfo(String queryParamsInfo);

	/**
	 * 
	* 根据域ID修改域数据信息
	* 
	* @param domainInfo DomainInfo对象json字符串
	* @return
	 */
	String updateDomainInfo(String domainInfo);

	/**
	 * 
	* 根据域ID删除域数据信息
	*
	* @param domainId
	* @return
	 */
	String deleteDomainInfoByID(String domainId);

	/**
	 * 
	* 新增域数据信息
	*
	* @param domainInfo
	* @return
	 */
	String saveDomainInfo(String domainInfo);

	/**
	 * 
	* 获取所有域数据字典
	*
	* @return  [{"domainId":"123","domainName":"123"}]
	 */
	String getDomainDic();
}
