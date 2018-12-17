
package org.run.shared.service.usc.manage.api.joinInfo;

public interface JoinUuRelationRestManageService {

	/**
	 * 
	* 根据属性关系ID获取属性关系
	*
	* @param 梅松
	* @return JoinUuRelation 对象json字符串
	 */
	String getJoinUuRelationByID(String uuRelationId);

	/**
	 * 
	* 根据查询条件获取分页数据信息
	* @param queryParams{"queryParams":{"key1":"value1","key2":"value2"},"pageIndex":1,"pageSize":10}
	* @return List<JoinUuRelation> json字符串
	 */
	String getJoinUuRelation(String queryParamsInfo);


	/**
	 * 
	* 根据uuRelationId删除 接入方扩展属性维护记录
	*
	* @param uuRelationId
	* @return
	 */
	String deleteJoinUuRelationByID(String uuRelationId);

	/**
	 * 
	* 保存用户类型属性关系
	* 按照各级下拉框选择后保存的针对用户类型的扩展属性集合
	* @param List<JoinUuRelation> json字符串  且公共字段一致（如：appId  userTypeCode  domainId等）
	* @return
	 */
	String saveJoinUuRelation(String joinUuRelationSaveInfos);

	/**
	 * 
	* 根据userTypeCode删除接入方扩展属性关系记录
	*
	* @param userTypeCode
	* @return
	 */
	String deleteJoinUuRelationByUserTypeCode(String userTypeCode);

	/**
	 * 
	* 根据接入方类型code获取接入方用户类型选中的扩展属性集合，数据较齐全可用作回显用
	*
	* @param userTypeCode
	* @return List<JoinUuRelation> json字符串
	 */
	String getJoinUuRelationByUserTypeCode(String userTypeCode);

	/**
	 * 
	* 根据接入方用户类型code获取选中的元数据ID集合，用于checkBox回显用
	*
	* @param userTypeCode
	* @return List<String> json字符串
	 */
	String getUuMetaIdsByUserTypeCode(String userTypeCode);

	
	/**
	 * 
	* 根据接入方类型code获取扩展属性元数据信息
	*
	* @param userTypeCode
	* @return JoinUserTypeRelationExtend json字符串
	 */
	public String getMetaInfoExtendByCode(String userTypeCode); 
}
