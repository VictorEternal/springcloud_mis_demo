package com.run.shared.service.usc.crud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.mongodb.WriteResult;
import com.run.base.util.AppCodeAttributeUtil;
import com.run.base.util.DateUtils;
import com.run.base.util.VerifyCodeUtil;
import com.run.common.util.MongoUtils;
import com.run.common.util.ParamChecker;
import com.run.common.util.RegexUtil;
import com.run.common.util.ResultBuilder;
import com.run.service.common.entity.Result;
import com.run.service.common.entity.constants.MongoConstants;
import com.run.service.common.entity.constants.ParamKeyConstants;
import com.run.service.common.entity.constants.ResultMsgConstants;
import com.run.service.common.entity.constants.UcsApiUrlConstants;
import com.run.shared.usc.api.curd.UserBaseCurdService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(UcsApiUrlConstants.API_USER_BASE_PATH)
public class UserBaseCurdServiceImpl implements UserBaseCurdService {
	@Autowired
	private MongoTemplate	ucsTemplate;
	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate	restTemplate;



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_REGISTER, method = RequestMethod.POST)
	public Result saveUser(@RequestBody String registerInfo) {
		// 参数为空
		if (ParamChecker.isBlank(registerInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(registerInfo)) {
			return ResultBuilder.invalidResult();
		}

		try {
			JSONObject registerInfoJson = JSON.parseObject(registerInfo);
			// 没有业务数据
			if (!registerInfoJson.containsKey(ParamKeyConstants.USER_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}

			JSONObject userInfo = registerInfoJson.getJSONObject(ParamKeyConstants.USER_INFO);
			String uid = UUID.randomUUID().toString().replace("-", "");

			// 检查参数中是否包含登陆账号、手机、邮件地址是否已经有其中一个
			if (!checkAccountHasOneAccount(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.EMPTY_ACCOUNT);
			}
			// 检查登陆账号格式是否合法
			if (!checkLoginAccountIsInvalid(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.LOGIN_ACOOUNT_INVALID);
			}
			// 检查手机号码格式是否合法
			if (!checkMobileIsInvalid(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.MOBILE_INVALID);
			}
			// 检查邮件地址格式是否合法
			if (!checkEmailIsInvalid(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.EMAIL_INVALID);
			}
			// 检查注册参数中登陆名是否已经注册
			if (checkLoginAccountHasRegistered(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.LOGIN_ACCOUNT_REGISTERED);
			}
			// 检查注册参数中手机是否已经注册
			if (checkMobileHasRegistered(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.MOBILE_REGISTERED);
			}
			// 检查注册参数中邮件地址是否已经注册
			if (checkEmailHasRegistered(userInfo)) {
				return ResultBuilder.failResult(ResultMsgConstants.EMAIL_REGISTERED);
			}
			// 检查参数中如果不包含登陆账号，则随机生成
			if (!checkParamHasLoginAccount(userInfo)) {
				String loginAccount = VerifyCodeUtil.getRandomLoginAcount(uid);
				userInfo.put(ParamKeyConstants.LOGIN_ACCOUNT, loginAccount);
			}

			// 生成用户系统默认编号
			String partnerId = "";
			if (userInfo.containsKey(ParamKeyConstants.PARTNER_ID)) {
				partnerId = userInfo.getString(ParamKeyConstants.PARTNER_ID);
			}
			if (StringUtils.isBlank(partnerId)) {
				partnerId = uid;
			}

			// 如果原始用户编号(originId)不存在，默认为partnerId
			if (userInfo.containsKey(ParamKeyConstants.ORIGIN_ID)) {
				String originId = userInfo.getString(ParamKeyConstants.ORIGIN_ID);
				if (StringUtils.isBlank(originId)) {
					userInfo.put(ParamKeyConstants.ORIGIN_ID, partnerId);
				}
			} else {
				userInfo.put(ParamKeyConstants.ORIGIN_ID, partnerId);
			}

			// 添加用户系列编号
			userInfo.put(ParamKeyConstants._ID, partnerId);
			userInfo.put(ParamKeyConstants.USER_ID, partnerId);
			userInfo.put(ParamKeyConstants.PARTNER_ID, partnerId);

			// 添加注册时间
			String registerTime = DateUtils.stampToDate(System.currentTimeMillis() + "");
			userInfo.put(ParamKeyConstants.REGISTER_TIME, registerTime);
			ucsTemplate.insert(userInfo, MongoConstants.MONGODB_USERINFO_COLL);

			// 返回参数值
			Map<String, String> map = Maps.newHashMap();
			map.put("userId", partnerId);

			return ResultBuilder.successResult(JSONObject.toJSONString(map), ResultMsgConstants.REGISTER_SUCCESS);
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_UPDATEUSER, method = RequestMethod.POST)
	public Result updateUser(@RequestBody String updateUserParam) {
		// 参数为空
		if (ParamChecker.isBlank(updateUserParam)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(updateUserParam)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject updateUserInfoJson = JSON.parseObject(updateUserParam);
			// 没有业务数据
			if (!updateUserInfoJson.containsKey(ParamKeyConstants.USER_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}

			JSONObject userInfo = updateUserInfoJson.getJSONObject(ParamKeyConstants.USER_INFO);
			// 将不可更改的属性过滤
			userInfo = AppCodeAttributeUtil.filterAttribute(null, userInfo);
			// 获取用户编号
			String id = updateUserInfoJson.getString(ParamKeyConstants.USER_ID);
			if (StringUtils.isBlank(id)) {
				return ResultBuilder.failResult(ResultMsgConstants.USER_QUERY_FAIL_ID);
			}
			Criteria criteria = Criteria.where(ParamKeyConstants.USER_ID).is(id);
			Query query = new Query(criteria);
			JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
			String userInfoStr = JSON.toJSONString(userInfo, SerializerFeature.WriteDateUseDateFormat);
			Update update = MongoUtils.JsonStringToUpdate(userInfoStr);
			WriteResult updateMulti = ucsTemplate.updateMulti(query, update, MongoConstants.MONGODB_USERINFO_COLL);
			if (updateMulti.getN() > 0) {
				return ResultBuilder.successResult(null, ResultMsgConstants.USER_MODIFY_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.USER_MODIFY_FAIL_DATA);
			}

		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	public Result deleteUser(String userId) {
		return null;
	}



	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_UPDATEPSD, method = RequestMethod.POST)
	public Result updatePassword(@RequestBody String updatepasswordInfo) {
		// 参数为空
		if (ParamChecker.isBlank(updatepasswordInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(updatepasswordInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject updatepasswordInfoJson = JSON.parseObject(updatepasswordInfo);
			// 没有用户数据
			if (!updatepasswordInfoJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取用户账户密码参数
			JSONObject quInfoJson = updatepasswordInfoJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 没有密码数据
			if (!quInfoJson.containsKey(ParamKeyConstants.PASSWORD)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 没有新登录密码数据
			if (!quInfoJson.containsKey(ParamKeyConstants.NEW_PASSWORD)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 没有userID数据
			if (!quInfoJson.containsKey(ParamKeyConstants.USER_ID)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取传入的登录密码
			String password = quInfoJson.getString(ParamKeyConstants.PASSWORD);
			// 获取传入的新登录密码
			String newPassword = quInfoJson.getString(ParamKeyConstants.NEW_PASSWORD);
			// 获取userID
			String userId = quInfoJson.getString(ParamKeyConstants.USER_ID);
			Query query = new Query();
			Criteria criteria = Criteria.where(ParamKeyConstants.USER_ID).is(userId).and(ParamKeyConstants.PASSWORD)
					.is(password);
			query.addCriteria(criteria);
			List<? extends Map<String, Object>> users = (List<? extends Map<String, Object>>) ucsTemplate.find(query,
					new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
			// 返回参数值
			if (CollectionUtils.isNotEmpty(users) && users.size() > 0) {
				Update update = new Update();
				update.set(ParamKeyConstants.PASSWORD, newPassword);
				WriteResult updateMulti = ucsTemplate.updateMulti(
						new Query(Criteria.where(ParamKeyConstants.USER_ID).is(userId)), update,
						MongoConstants.MONGODB_USERINFO_COLL);
				int count = updateMulti.getN();
				if (count > 0) {
					return ResultBuilder.successResult(null, ResultMsgConstants.UPDATE_PSD_SUCCESS);
				} else {
					return ResultBuilder.failResult(ResultMsgConstants.UPDATE_PSD_FAIL);
				}
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.UPDATE_PSD_PAPAM_INVALID);
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_UPDATEUSER_SECURITY, method = RequestMethod.POST)
	public Result updateUserSecurity(@RequestBody String updateSecurityInfo) {
		// 参数为空
		if (ParamChecker.isBlank(updateSecurityInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(updateSecurityInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject updateSecurityInfoJson = JSON.parseObject(updateSecurityInfo);
			// 没有新登录数据
			if (!updateSecurityInfoJson.containsKey(ParamKeyConstants.ACCOUNT)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 没有userID数据
			if (!updateSecurityInfoJson.containsKey(ParamKeyConstants.USER_ID)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取传入的登录数据
			String account = updateSecurityInfoJson.getString(ParamKeyConstants.ACCOUNT);
			// 获取userID
			String userId = updateSecurityInfoJson.getString(ParamKeyConstants.USER_ID);
			Update update = new Update();
			// 验证登录名类型（如果登录名类型验证通过邮箱验证则修改邮箱，反之）
			if (RegexUtil.validateEmail(account)) {
				update.set(ParamKeyConstants.EMAIL, account);
				update.set("emailActivationSign", '1');
			} else if (RegexUtil.validateMobile(account)) {
				update.set(ParamKeyConstants.MOBILE, account);
				update.set("mobileActivationSign", '1');
			}
			// 将登录名修改
			update.set(ParamKeyConstants.LOGIN_ACCOUNT, account);

			WriteResult updateMulti = ucsTemplate.updateMulti(
					new Query(Criteria.where(ParamKeyConstants.USER_ID).is(userId)), update,
					MongoConstants.MONGODB_USERINFO_COLL);
			int count = updateMulti.getN();
			if (count > 0) {
				return ResultBuilder.successResult(null, ResultMsgConstants.UPDATE_USER_SECURITY_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.UPDATE_USER_SECURITY_FAIL);
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_RESETPSD, method = RequestMethod.POST)
	public Result resetPassword(@RequestBody String resetpasswordInfo) {
		// 参数为空
		if (ParamChecker.isBlank(resetpasswordInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(resetpasswordInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject resetpasswordInfoJson = JSON.parseObject(resetpasswordInfo);
			// 没有用户数据
			if (!resetpasswordInfoJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取传入的用户数据
			JSONObject quInfoJson = resetpasswordInfoJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 获取登录数据
			String account = quInfoJson.getString(ParamKeyConstants.ACCOUNT);
			// 获取新密码
			String newPassword = quInfoJson.getString(ParamKeyConstants.NEW_PASSWORD);
			Update update = new Update();
			if (StringUtils.isEmpty(newPassword)) {
				return ResultBuilder.failResult(ResultMsgConstants.USER_RESET_PSD_PAPAM);
			}
			// 修改密码
			update.set(ParamKeyConstants.PASSWORD, newPassword);

			WriteResult updateMulti = ucsTemplate.updateMulti(
					new Query(Criteria.where(ParamKeyConstants.LOGIN_ACCOUNT).is(account)), update,
					MongoConstants.MONGODB_USERINFO_COLL);
			int count = updateMulti.getN();
			if (count > 0) {
				return ResultBuilder.successResult(null, ResultMsgConstants.USER_RESET_PSD_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.USER_RESET_PSD_FAIL);
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	public Result merge(String mergeInfo) {
		// 参数为空
		if (ParamChecker.isBlank(mergeInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(mergeInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject mergeInfoJson = JSON.parseObject(mergeInfo);
			// 没有用户数据
			if (!mergeInfoJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取传入的用户数据
			JSONObject quInfoJson = mergeInfoJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 获取UserId数据
			String userId = quInfoJson.getString(ParamKeyConstants.USER_ID);
			// 获取UserIds数据
			String userIds = quInfoJson.getString("userIds");
			return ResultBuilder.failResult("未实现该接口");
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_LOGINOUT, method = RequestMethod.POST)
	public Result loginout(@RequestBody String loginInfo) {
		// 参数为空
		if (ParamChecker.isBlank(loginInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(loginInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject loginInfoJson = JSON.parseObject(loginInfo);
			// 没有用户数据
			if (!loginInfoJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 移除token
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
			headers.setContentType(type);
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
			HttpEntity<String> formEntity = new HttpEntity<String>(loginInfo, headers);
			String url = "http://localhost:8802" + UcsApiUrlConstants.API_USER_TOKEN + "/"
					+ UcsApiUrlConstants.API_CODE_REMOVE_TOKEN;
			Result result = restTemplate.postForObject(url, formEntity, Result.class);
			return result;
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	/**
	 * 
	 * 检查注册参数中用户名、手机、邮件是否包含其中一个
	 *
	 * @param userInfo
	 * @return
	 */
	private boolean checkAccountHasOneAccount(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.LOGIN_ACCOUNT)) {
			if (StringUtils.isNotBlank(userInfo.getString(ParamKeyConstants.LOGIN_ACCOUNT))) {
				return true;
			}
		}
		if (userInfo.containsKey(ParamKeyConstants.EMAIL)) {
			if (StringUtils.isNotBlank(userInfo.getString(ParamKeyConstants.EMAIL))) {
				return true;
			}
		}
		if (userInfo.containsKey(ParamKeyConstants.MOBILE)) {
			if (StringUtils.isNotBlank(userInfo.getString(ParamKeyConstants.MOBILE))) {
				return true;
			}
		}
		return false;

	}



	/**
	 * 
	 * 检查注册参数中用户名是否已经注册
	 *
	 * @param userInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkLoginAccountHasRegistered(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.LOGIN_ACCOUNT)) {
			String loginAccount = userInfo.getString(ParamKeyConstants.LOGIN_ACCOUNT);
			if (StringUtils.isNotBlank(loginAccount)) {
				Query query = new Query();
				query.addCriteria(Criteria.where(ParamKeyConstants.LOGIN_ACCOUNT).is(loginAccount));
				List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) ucsTemplate.find(query,
						new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
				if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * 
	 * 检查注册参数中手机是否已经注册
	 *
	 * @param userInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkEmailHasRegistered(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.EMAIL)) {
			String email = userInfo.getString(ParamKeyConstants.EMAIL);
			if (StringUtils.isNotBlank(email)) {
				Query query = new Query();
				query.addCriteria(Criteria.where(ParamKeyConstants.EMAIL).is(email));
				List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) ucsTemplate.find(query,
						new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
				if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * 
	 * 检查注册参数中邮件地址是否已经注册
	 *
	 * @param userInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkMobileHasRegistered(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.MOBILE)) {
			String mobile = userInfo.getString(ParamKeyConstants.MOBILE);
			if (StringUtils.isNotBlank(mobile)) {
				Query query = new Query();
				query.addCriteria(Criteria.where(ParamKeyConstants.MOBILE).is(mobile));
				List<HashMap<String, Object>> mapList = (List<HashMap<String, Object>>) ucsTemplate.find(query,
						new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
				if (CollectionUtils.isNotEmpty(mapList) && mapList.size() > 0) {
					return true;
				}
			}
		}
		return false;
	}



	/**
	 * 
	 * 检查注册参数中是否含有登陆账号
	 *
	 * @param userInfo
	 * @return
	 */
	private boolean checkParamHasLoginAccount(JSONObject userInfo) {
		if (!userInfo.containsKey(ParamKeyConstants.LOGIN_ACCOUNT)) {
			return false;
		}
		if (userInfo.containsKey(ParamKeyConstants.LOGIN_ACCOUNT)) {
			if (StringUtils.isBlank(userInfo.getString(ParamKeyConstants.LOGIN_ACCOUNT))) {
				return false;
			}
		}

		return true;
	}



	/**
	 * 
	 * 检查登录名是否合法
	 *
	 * @param userInfo
	 * @return
	 */
	private boolean checkLoginAccountIsInvalid(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.LOGIN_ACCOUNT)
				&& userInfo.get(ParamKeyConstants.LOGIN_ACCOUNT) != null) {
			if (!RegexUtil.validateLoginAccount(userInfo.getString(ParamKeyConstants.LOGIN_ACCOUNT))) {
				return false;
			}
		}
		return true;
	}



	/**
	 * 
	 * 检查手机号码是否合法
	 *
	 * @param userInfo
	 * @return
	 */
	private boolean checkMobileIsInvalid(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.MOBILE) && userInfo.get(ParamKeyConstants.MOBILE) != null) {
			if (!RegexUtil.validateMobile(userInfo.getString(ParamKeyConstants.MOBILE))) {
				return false;
			}
		}
		return true;
	}



	/**
	 * 
	 * 检查登录名是否合法
	 *
	 * @param userInfo
	 * @return
	 */
	private boolean checkEmailIsInvalid(JSONObject userInfo) {
		if (userInfo.containsKey(ParamKeyConstants.EMAIL) && userInfo.get(ParamKeyConstants.EMAIL) != null) {
			if (!RegexUtil.validateEmail(userInfo.getString(ParamKeyConstants.EMAIL))) {
				return false;
			}
		}
		return true;
	}

}
