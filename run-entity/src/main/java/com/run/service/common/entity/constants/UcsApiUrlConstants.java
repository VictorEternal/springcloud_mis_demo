package com.run.service.common.entity.constants;

/**
 * 
 * 统一用户中心接口列表
 * 
 * @author: lyc
 * @version: 1.0, 2016年11月21日
 */
public class UcsApiUrlConstants {
	// 基础路径
	/** 用户路径 **/
	public static final String	API_USER_BASE_PATH							= "/user";
	public static final String	API_USER_CHECK_PATH							= "/check";
	public static final String	API_USER_TOKEN								= "/token";
	/** 客户路径 **/
	public static final String	API_CUST_BASE_PATH							= "cust";
	/** 用户管理路径 **/
	public static final String	API_USERMANAGE_BASE_PATH					= "usermanage";

	// 用户管理
	/** 用户注册 **/
	public static final String	API_CODE_REGISTER							= "/register";
	/** 重置密码 **/
	public static final String	API_CODE_RESETPSD							= "resetpassword";
	/** 修改密码 **/
	public static final String	API_CODE_UPDATEPSD							= "updatepassword";
	/** 更新用户 **/
	public static final String	API_CODE_UPDATEUSER							= "updateuser";
	/** 修改用户安全信息（手机、邮箱） **/
	public static final String	API_CODE_UPDATEUSER_SECURITY				= "updateusersecurity";
	/** 通过用户编号查询用户 **/
	public static final String	API_CODE_QUERYUSER_ID						= "queryuserbyid";
	/** 通过账号、邮箱、手机号码查询用户 **/
	public static final String	API_CODE_QUERYUSER_ACCOUNT					= "queryuserbyaccount";
	/** 检查登录名 **/
	public static final String	API_CODE_CHECK_LOGINNAME					= "/checkloginname";
	/** 删除用户 **/
	public static final String	API_CODE_DELETEUSER							= "deleteuser";
	/** 合并用户 **/
	public static final String	API_CODE_MERGEUSER							= "mergeuser";
	/** 合并用户查询 **/
	public static final String	API_CODE_MERGEUSER_QUERY					= "mergeuserquery";

	// 用户token
	/** 验证token票据是否合法 **/
	public static final String	API_CODE_USERAUTHVERIFY						= "userauthverify";
	/** token刷新 **/
	public static final String	API_CODE_REFRESHTOKEN						= "refreshtoken";
	/* 验证用户token */
	public static final String	API_CODE_AUTHENTICATE						= "authenticate";
	/* 根据token获取userId */
	public static final String	API_CODE_GET_USERID							= "getuserid";
	/* 获取或者生成token */
	public static final String	API_CODE_CREATE_TOKEN						= "createtoken";
	/* 移除token */
	public static final String	API_CODE_REMOVE_TOKEN						= "removetoken";

	// 单点登录
	/** 授权登录 **/
	public static final String	API_CODE_AUTHLOGIN							= "authlogin";
	/** SDK第三方登录 **/
	public static final String	API_CODE_SDK_THIRDLOGIN						= "checkthirdrepeatbind";
	/** 用户认证 **/
	public static final String	API_CODE_USERAUTH							= "oauth";
	/** 用户退出 **/
	public static final String	API_CODE_LOGINOUT							= "logout";
	/** 根据token查询用户信息 **/
	public static final String	API_CODE_AUTHQUERY							= "authquery";

	// 第三方授权绑定管理
	/** 删除用户第三方绑定信息 **/
	public static final String	API_CODE_DEL_THIRDLOGIN						= "deletethirdlogin";
	/** 新增用户第三方绑定信息 **/
	public static final String	API_CODE_SAVE_THIRDLOGIN					= "savethirdlogin";
	/** 查询用户第三方绑定信息 **/
	public static final String	API_CODE_QUERY_THIRDLOGIN					= "querythirdlogin";

	// 安全管理
	/** 登录安全 **/
	public static final String	API_CODE_LOGIN_SAFE							= "loginsafe";
	/** 发送短信，邮件 **/
	public static final String	API_CODE_SEND_MESSAGE						= "sendmessage";
	/** 校验验证码 **/
	public static final String	API_CODE_CHECK_CODE							= "checkcode";
	/** 获取安全项的值 **/
	public static final String	API_CODE_GET_SECURITY_VALUE					= "getsecurityvalue";
	/** 获取AES key **/
	public static final String	API_CODE_GET_AES_KEY						= "getaeskey";
	/** 密码传输加密方式获取密码 **/
	public static final String	API_CODE_PASSWORD_TRANSMISSION				= "passwordtransmission";
	/** 密码存储加密方式获取密码 **/
	public static final String	API_CODE_PASSWORD_STORAGE					= "passwordstorage";

	// 客户管理
	/** 根据客户id查询用户信息 **/
	public static final String	API_CODE_CUST_QUERYUSERBYCUSTID				= "queryuserbycustid";
	/** 根据 手机邮箱户号查询用户信息 **/
	public static final String	API_CODE_CUST_QUERYUSERBYACCOUNT			= "queryuserbyaccount";
	/** 客户信息关联 **/
	public static final String	API_CODE_CUST_ASSOCIATED					= "associated";
	/** 客户信息解联 **/
	public static final String	API_CODE_CUST_UNASSOCIATED					= "unassociated";
	/** 客户信息新增 **/
	public static final String	API_CODE_CUST_SAVE							= "savecust";
	/** 客户信息查询 **/
	public static final String	API_CODE_CUST_QUERY							= "querycust";
	/** 客户信息删除 **/
	public static final String	API_CODE_CUST_DELETE						= "deletecust";

	// 用户后台管理
	// 用户后台租户管理
	/** 根据域id获取域信息 */
	public static final String	API_USERMANAGE_GETTENEMENTINFOBYID			= "gettenementinfobyid";
	/** 获取域信息(分页) */
	public static final String	API_USERMANAGE_GETTENEMENTINFO				= "gettenementinfo";
	/** 根据域ID修改域信息 */
	public static final String	API_USERMANAGE_UPDATETENEMENTINFO			= "updatetenementinfo";
	/** 根据域ID删除域信息 */
	public static final String	API_USERMANAGE_DELETETENEMENTINFOBYID		= "deletetenementinfobyid";
	/** 新增域信息 */
	public static final String	API_USERMANAGE_SAVETENEMENTINFO				= "savetenementinfo";
	/** 获取所有域信息 */
	public static final String	API_USERMANAGE_GETTENEMENTINFODIC			= "gettenementinfodic";

	// 用户后台域管理
	/** 根据域id获取域信息 */
	public static final String	API_USERMANAGE_GETDOMAININFOBYID			= "getdomaininfobyid";
	/** 获取域信息(分页) */
	public static final String	API_USERMANAGE_GETDOMAININFO				= "getdomaininfo";
	/** 根据域ID修改域信息 */
	public static final String	API_USERMANAGE_UPDATEDOMAININFO				= "updatedomaininfo";
	/** 根据域ID删除域信息 */
	public static final String	API_USERMANAGE_DELETEDOMAININFOBYID			= "deletedomaininfobyid";
	/** 新增域信息 */
	public static final String	API_USERMANAGE_SAVEDOMAININFO				= "savedomaininfo";
	/** 获取所有域信息数据字典 */
	public static final String	API_USERMANAGE_GETDOMAININFODIC				= "getdomaininfodic";

	// 用户后台接入方管理
	/** 保存接入方信息 */
	public static final String	API_USERMANAGE_SAVEJOININFO					= "savejoininfo";
	/** 根据接入方ID删除域信息 */
	public static final String	API_USERMANAGE_DELETEJOININFOBYID			= "deletejoininfobyid";
	/** 根据接入方ID修改域信息 */
	public static final String	API_USERMANAGE_UPDATEJOININFO				= "updatejoininfo";
	/** 获取接入方信息(分页) */
	public static final String	API_USERMANAGE_GETJOININFO					= "getjoininfo";
	/** 获取所有接入方信息 */
	public static final String	API_USERMANAGE_GETALLJOININFO				= "getalljoininfo";
	/** 获取接入方信息数据字典 */
	public static final String	API_USERMANAGE_GETJOININFODIC				= "getjoininfodic";
	/** 根据接入方id获取接入方信息 */
	public static final String	API_USERMANAGE_GETJOININFOBYID				= "getjoininfobyid";

	// 用户后台接入方用户类型管理
	/** 保存接入方用户类型信息 */
	public static final String	API_USERMANAGE_SAVEJOINUSERTYPEINFO			= "savejoinusertypeinfo";
	/** 根据接入方用户类型code删除用户类型信息 */
	public static final String	API_USERMANAGE_DELETEJOINUSERTYPE			= "deletejoinusertype";
	/** 根据接入方用户类型code修改用户类型信息 */
	public static final String	API_USERMANAGE_UPDATEJOINUSERTYPEINFO		= "updatejoinusertypeinfo";
	/** 获取接入方用户类型信息(分页) */
	public static final String	API_USERMANAGE_GETJOINUSERTYPEINFO			= "getjoinusertypeinfo";
	/** 根据userTypeCode获取接入方用户类型数据 */
	public static final String	API_USERMANAGE_GETJOINUSERTYPEINFOBYCODE	= "getjoinusertypeinfobycode";
	/** 获取所有接入方信息 */
	public static final String	API_USERMANAGE_GETALLJOINUSERTYPEINFO		= "getalljoinusertypeinfo";
	/** 获取接入方用户类型信息数据字典 */
	public static final String	API_USERMANAGE_GETJOINUSERTYPEDIC			= "getjoinusertypedic";

	// 用户后台元数据管理
	/** 根据元数据ID获取元数据信息 */
	public static final String	API_USERMANAGE_GETUUMETAINFOBYID			= "getuumetainfobyid";
	/** 根据接入方用户类型code删除用户类型信息 */
	public static final String	API_USERMANAGE_DELETEUUMETAINFOBYID			= "deleteuumetainfobyid";
	/** 根据元数据ID修改元数据信息 */
	public static final String	API_USERMANAGE_UPDATEUUMETAINFO				= "updateuumetainfo";
	/** 获取元数据信息(分页) */
	public static final String	API_USERMANAGE_GETUUMETAINFO				= "getuumetainfo";
	/** 获取元数据信息(树结构) */
	public static final String	API_USERMANAGE_GETUUMETAINFOTREE			= "getuumetainfotree";
	/** 保存元数据信息 */
	public static final String	API_USERMANAGE_SAVEUUMETAINFO				= "saveuumetainfo";

	// 用户后台元数据关系维护管理
	/** 保存元数据关系信息 */
	public static final String	API_USERMANAGE_SAVEJOINUURELATION			= "savejoinuurelation";
	/** 根据uuRelationId删除元数据关系 */
	public static final String	API_USERMANAGE_DELETEJOINUURELATIONBYID		= "deletejoinuurelationbyid";
	/** 根据userTypeCode删除元数据关系 */
	public static final String	API_USERMANAGE_DELUURELATIONBYUSERTYPECODE	= "deluurelationbyusertypecode";
	/** 获取元数据关系（分页） */
	public static final String	API_USERMANAGE_GETJOINUURELATION			= "getjoinuurelation";
	/** 根据uuRelationId获取元数据关系 */
	public static final String	API_USERMANAGE_GETJOINUURELATIONBYID		= "getjoinuurelationbyid";
	/** 根据userTypeCode获取元数据关系 */
	public static final String	API_USERMANAGE_GETUURELATIONBYUSERTYPECODE	= "getuurelationbyusertypecode";
	/** 根据userTypeCode获取元数据id集合 逗号分隔的字符串 */
	public static final String	API_USERMANAGE_GETUUMETAIDSBYUSERTYPECODE	= "getuumetaidsbyusertypecode";
	/** 根据userTypeCode获取元数据 */
	public static final String	API_USERMANAGE_GETMETAINFOBYCODE			= "getmetainfobycode";

	// 用户管理
	/** 获取用户数据 （分页） */
	public static final String	API_USERMANAGE_GETUSERINFO					= "getuserinfo";
	/** 用户后台管理重置密码 */
	public static final String	API_USERMANAGE_UPDATEPASSWORD				= "updatepassword";
	/** 用户后台管理保存用户信息 */
	public static final String	API_USERMANAGE_SAVEUSER						= "saveuser";
	/** 用户后台管理保存用户信息 */
	public static final String	API_USERMANAGE_UPDATEUSER					= "updateuser";
	/** 用户后台管理根据用户id删除用户信息 */
	public static final String	API_USERMANAGE_DELETEUSERINFO				= "deleteuserinfo";
	/** 用户后台管理根据用户id获取用户信息 */
	public static final String	API_USERMANAGE_GETUSERBYID					= "getuserbyid";

}
