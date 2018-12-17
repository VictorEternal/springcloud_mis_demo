package com.run.service.common.entity.constants;

/**
 * 
 * 传入参数key值
 * 
 * @author: lyc
 * @version: 1.0, 2017年1月12日
 */
public class ParamKeyConstants {
	/** 公共参数 **/
	public static final String	USER_TYPE_CODE	= "userTypeCode";
	/** 注册传入参数Key值 **/
	public static final String	USER_INFO		= "userInfo";
	public static final String	_ID				= "_id";
	public static final String	USER_ID			= "userId";
	public static final String	PARTNER_ID		= "partnerId";
	public static final String	ORIGIN_ID		= "originId";
	public static final String	REGISTER_TIME	= "registerTime";
	public static final String	LOGIN_ACCOUNT	= "loginAccount";
	public static final String	EMAIL			= "email";
	public static final String	MOBILE			= "mobile";
	public static final String	PASSWORD		= "password";
	public static final String	NEW_PASSWORD	= "newPassword";

	/** 用户登录验证 **/
	// 用户账户密码参数
	public static final String	LOGIN_BASE_BEAN	= "loginBaseBean";
	// 统一用户访问信息
	public static final String	USC_INFO		= "uscInfo";
	// 登陆名,邮箱或用户名或手机号
	public static final String	ACCOUNT			= "account";
	// 租户方编号：必填，统一用户中心分配给接入方的编号 ,
	public static final String	TENEMENT_CODE	= "tenementCode";
	// 设备号
	public static final String	DEVICEID		= "deviceid";

	/** 通过用户编号查询用户信息 **/
	// 用户认证信息
	public static final String	QU_INFO			= "quInfo";
	// 认证成功后的访问令牌
	public static final String	TOKEN			= "token";

}
