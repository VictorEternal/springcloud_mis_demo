package org.run.shared.service.usc.manage.api.uuMetaInfo;

/**
 * 
 * 元数据基础信息管理
 * 
 * @author: 邓帅
 * @version: 1.0, 2016年12月3日
 */
public interface UuMetaInfoRestService {

	/**
	 * 
	 * 根据元数据ID获取元数据信息
	 *
	 * @param uuMetaId
	 *            元数据ID
	 * @return UuMetaInfo json字符串
	 */
	String getUuMetaInfoByID(String uuMetaId);



	/**
	 * 
	 * 根据查询条件获取元数据分页数据
	 *
	 * @param queryParams{"queryParams":{"key1":"value1","key2":"value2"},"pageIndex":1,"pageSize":10}
	 * @return List<UuMetaInfo> json字符串
	 */
	String getUuMetaInfo(String queryParamsInfo);



	/**
	 * 
	 * 获取元数据树结构数据
	 *
	 * @return List<UuMetaInfo> json字符串
	 */
	String getUuMetaInfoTree();



	/**
	 * 
	 * 根据uuMetaId修改元数据信息
	 *
	 * @param UuMetaInfo
	 *            对象字符串
	 * @return
	 */
	String updateUuMetaInfo(String uuMetaInfo);



	/**
	 * 
	 * 根据uuMetaId删除元数据信息
	 *
	 * @param uuMetaId
	 * @return
	 */
	String deleteUuMetaInfoByID(String uuMetaId);



	/**
	 * 
	 * <method description>
	 *
	 * @param UuMetaInfo实体对象
	 *            json字符串
	 * @return
	 */
	String saveUuMetaInfo(String uuMetaInfo);

}
