package com.run.service.common.entity.constants;

public class ResultMsgConstants {
	/* 异常消息常量 */
	public static final String	EMPTY_PARAM						= "传入参数为空！";
	public static final String	INVALID_PARAM					= "传入参数非法！";
	public static final String	RUNTIME_EXCEPTION				= "服务内部异常！";
	public static final String	NO_BUSINESS_EXCEPTION			= "没有业务数据！";

	/* 公共常量 */
	public static final String	USC_PARAM						= "统一平台参数：";
	public static final String	USER_PARAM						= ",用户信息参数：";
	public static final String	BUSINESS_PARAM					= ",业务参数：";

	/* 注册信息 */
	public static final String	REGISTER_PARAM					= "用户注册失败，请您检查参数输入是否正确！";
	public static final String	RIGISTER_IF_EXITS				= "用户信息注册失败，请检查用户名手机号或邮箱是否已经注册,格式是否正确。";
	public static final String	RIGISTER_EXCEPTION				= "用户信息注册异常";
	public static final String	REGISTER_SUCCESS				= "用户信息注册成功";
	public static final String	REGISTER_FAIL_NOT_SAME			= "用户信息注册失败，用户名必须与手机号相同。";
	public static final String	REGISTER_EXCEPTION_ENCRYT		= "用户信息注册异常,密码解密加密异常";
	public static final String	EMPTY_ACCOUNT					= "用户注册失败:请填写登录账号或者手机或者邮件地址！";
	public static final String	LOGIN_ACCOUNT_REGISTERED		= "用户注册失败:登陆名已经被注册！";
	public static final String	MOBILE_REGISTERED				= "用户注册失败:手机号码已经被注册！";
	public static final String	EMAIL_REGISTERED				= "用户注册失败:邮件地址已经被注册！";
	public static final String	EMAIL_INVALID					= "用户注册失败:邮件地址非法！";
	public static final String	MOBILE_INVALID					= "用户注册失败:手机号码非法！";
	public static final String	LOGIN_ACOOUNT_INVALID			= "用户注册失败:登录名非法，登录名由英文大小写字母与数字组成！";

	/* 修改密码 */
	public static final String	UPDATE_DATA_SYC_EXCEPTION		= "用户修改密码同步异常";
	public static final String	UPDATE_PSD_ACCOUNT_EXCEPTION	= "用户名不符合规则";
	public static final String	UPDATE_PSD_EMAIL_INVALID		= "邮箱不符合规则";
	public static final String	UPDATE_PSD_ENCYPT_EXCEPTION		= "用户重置密码异常,密码解密加密异常";
	public static final String	UPDATE_PSD_PAPAM_INVALID		= "用户名或原密码不正确";
	public static final String	UPDATE_PSD_EXCEPTION			= "用户修改密码异常";
	public static final String	UPDATE_PSD_FAIL					= "用户修改密码失败";
	public static final String	UPDATE_PSD_SUCCESS				= "用户修改密码成功";

	/* 用户查询信息 */
	public static final String	USER_QUERY_SUCCESS				= "用户信息查询成功";
	public static final String	USER_QUERY_FAIL					= "用户信息查询失败!";
	/* 用户认证密码异常 */
	public static final String	USER_AUTHZ_PSD_EXCEPTION		= "用户认证异常,密码解密加密异常！";
	/* 用户信息修改 */
	public static final String	USER_QUERY_FAIL_ID				= "用户信息修改失败:必须传入用户编号!";
	public static final String	USER_MODIFY_FAIL_DATA			= "用户信息修改失败:数据库同步异常!";
	public static final String	USER_MODIFY_SUCCESS				= "用户信息修改成功!";
	public static final String	USER_QUERY_EXCEPTION			= "用户信息查询异常!";
	public static final String	USER_QUERY_INPUT_ACCOUNT		= "请输入登录账户！";
	/* 用户安全信息修改 */
	public static final String	UPDATE_USER_SECURITY_SUCCESS	= "用户安全信息修改成功！";
	public static final String	UPDATE_USER_SECURITY_FAIL		= "用户安全信息修改失败！";
	/* 用户重置密码 */
	public static final String	USER_RESET_PSD_SUCCESS			= "用户重置密码成功！";
	public static final String	USER_RESET_PSD_FAIL				= "用户重置密码失败！";
	public static final String	USER_RESET_PSD_PAPAM			= "未传入重置密码！";
	/* 用户退出 */
	public static final String	USER_LOGINOUT_SUCCESS			= "用户退出成功！";
	public static final String	USER_LOGINOUT_FAIL				= "用户退出失败！";
	/* token */
	public static final String	TOKEN_AUTHZ_SUCCESS				= "token验证成功！";
	public static final String	TOKEN_AUTHZ_FAIL				= "token验证失败！";
	public static final String	TOKEN_CREATE_SUCCESS			= "生成token成功！";
	public static final String	TOKEN_CREATE_FAIL				= "生成token失败！";
	public static final String	TOKEN_REMOVE_SUCCESS			= "token移除成功！";
	public static final String	TOKEN_REMOVE_FAIL				= "token移除失败！";
	public static final String	TOKEN_GET_USERID_SUCCESS		= "根据token查询userid成功！";
	public static final String	TOKEN_GET_USERID_FAIL			= "生成token查询userid失败！";

}
