package com.run.shared.service.usc.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.run.common.util.ParamChecker;
import com.run.common.util.ResultBuilder;
import com.run.service.common.entity.Result;
import com.run.service.common.entity.constants.MongoConstants;
import com.run.service.common.entity.constants.ParamKeyConstants;
import com.run.service.common.entity.constants.ResultMsgConstants;
import com.run.service.common.entity.constants.UcsApiUrlConstants;
import com.run.shared.usc.api.query.UserBaseQueryService;

/**
 * 
 * @author lyc
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(UcsApiUrlConstants.API_USER_BASE_PATH)
public class UserBaseQueryServiceImpl implements UserBaseQueryService {
	@Autowired
	private MongoTemplate	ucsTemplate;
	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate	restTemplate;



	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_QUERYUSER_ID, method = RequestMethod.POST)
	public Result getUserbyId(@RequestBody String queryUserParam) {
		// 参数为空
		if (ParamChecker.isBlank(queryUserParam)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(queryUserParam)) {
			return ResultBuilder.invalidResult();
		}

		try {
			JSONObject queryUserParamJson = JSON.parseObject(queryUserParam);
			// 没有用户认证信息数据
			if (!queryUserParamJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取用户认证信息数据
			JSONObject quInfoJson = queryUserParamJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 没有UserID数据
			if (!quInfoJson.containsKey(ParamKeyConstants.USER_ID)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取userID
			String userId = quInfoJson.getString(ParamKeyConstants.USER_ID);
			Query query = new Query();
			Criteria criteria = Criteria.where(ParamKeyConstants.USER_ID).is(userId);
			query.addCriteria(criteria);
			List<? extends Map<String, Object>> users = (List<? extends Map<String, Object>>) ucsTemplate.find(query,
					new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
			// 返回参数值
			Map<String, Object> map = Maps.newHashMap();
			if (CollectionUtils.isNotEmpty(users) && users.size() > 0) {
				map.put("userInfo", users.get(0));
				return ResultBuilder.successResult(JSONObject.toJSONString(map), ResultMsgConstants.USER_QUERY_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.USER_QUERY_FAIL);
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@SuppressWarnings({ "unchecked" })
	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_QUERYUSER_ACCOUNT, method = RequestMethod.POST)
	public Result getUserByaccount(@RequestBody String queryUserParam) {
		// 参数为空
		if (ParamChecker.isBlank(queryUserParam)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(queryUserParam)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject queryUserParamJson = JSON.parseObject(queryUserParam);
			// 没有用户认证信息数据
			if (!queryUserParamJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取用户认证信息数据
			JSONObject quInfoJson = queryUserParamJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 没有ACCOUNT数据
			if (!quInfoJson.containsKey(ParamKeyConstants.ACCOUNT)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取登录名
			String account = quInfoJson.getString(ParamKeyConstants.ACCOUNT);
			Query query = new Query();
			Criteria criteria = Criteria.where(ParamKeyConstants.LOGIN_ACCOUNT).is(account);
			;
			query.addCriteria(criteria);
			List<? extends Map<String, Object>> users = (List<? extends Map<String, Object>>) ucsTemplate.find(query,
					new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
			// 返回参数值
			Map<String, Object> map = Maps.newHashMap();
			if (CollectionUtils.isNotEmpty(users) && users.size() > 0) {
				map.put("userInfo", users.get(0));
				return ResultBuilder.successResult(JSONObject.toJSONString(map), ResultMsgConstants.USER_QUERY_SUCCESS);
			} else {
				// 无业务数据返回
				return ResultBuilder.noBuisnessResult();
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_USERAUTH, method = RequestMethod.POST)
	public Result userAuthz(@RequestBody String oauthRquestInfo) {
		// 参数为空
		if (ParamChecker.isBlank(oauthRquestInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(oauthRquestInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject oauthRquestInfoJson = JSON.parseObject(oauthRquestInfo);
			// 没有用户名数据
			if (!oauthRquestInfoJson.containsKey(ParamKeyConstants.LOGIN_BASE_BEAN)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取用户账户密码参数
			JSONObject loginBaseBeanJson = oauthRquestInfoJson.getJSONObject(ParamKeyConstants.LOGIN_BASE_BEAN);
			// 获取传入的登录密码
			String account = loginBaseBeanJson.getString(ParamKeyConstants.ACCOUNT);
			// 没有ACCOUNT数据
			if (!loginBaseBeanJson.containsKey(ParamKeyConstants.ACCOUNT)) {
				return ResultBuilder.noBuisnessResult();
			}
			String password = loginBaseBeanJson.getString(ParamKeyConstants.PASSWORD);
			Query query = new Query();
			Criteria criteria = new Criteria();
			criteria.orOperator(Criteria.where(ParamKeyConstants.LOGIN_ACCOUNT).is(account),
					Criteria.where(ParamKeyConstants.EMAIL).is(account),
					Criteria.where(ParamKeyConstants.MOBILE).is(account));
			query.addCriteria(criteria);
			List<? extends Map<String, Object>> users = (List<? extends Map<String, Object>>) ucsTemplate.find(query,
					new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
			// 返回参数值
			Map<String, Object> map = Maps.newHashMap();
			// 判断是否查询出来用户
			if (CollectionUtils.isNotEmpty(users) && users.size() > 0) {
				map.put("userInfo", users.get(0));
				Map<String, Object> user = users.get(0);
				// 获取根据用户名查询出来的用户密码
				String userPassWord = user.get(ParamKeyConstants.PASSWORD) == null ? ""
						: user.get(ParamKeyConstants.PASSWORD).toString();
				// 判断传入密码是否和查询密码一致
				if (StringUtils.isNotEmpty(userPassWord) && StringUtils.isNotEmpty(password)
						&& userPassWord.equals(password)) {
					return ResultBuilder.successResult(JSONObject.toJSONString(map),
							ResultMsgConstants.USER_QUERY_SUCCESS);
				} else {
					return ResultBuilder.failResult(ResultMsgConstants.USER_AUTHZ_PSD_EXCEPTION);
				}
			} else {
				// 无业务数据返回
				return ResultBuilder.noBuisnessResult();
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_AUTHQUERY, method = RequestMethod.POST)
	public Result oauthquery(@RequestBody String tokenInfo) {
		// 参数为空
		if (ParamChecker.isBlank(tokenInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(tokenInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject tokenInfoJson = JSON.parseObject(tokenInfo);
			// 没有业务数据
			if (!tokenInfoJson.containsKey(ParamKeyConstants.TOKEN)) {
				return ResultBuilder.noBuisnessResult();
			}

			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
			headers.setContentType(type);
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
			HttpEntity<String> formEntity = new HttpEntity<String>(tokenInfo, headers);
			String url = "http://localhost:8802" + UcsApiUrlConstants.API_USER_TOKEN + "/"
					+ UcsApiUrlConstants.API_CODE_GET_USERID;
			Result result = restTemplate.postForObject(url, formEntity, Result.class);
			JSONObject valueJson = result.getValue();
			if (valueJson == null || !valueJson.containsKey(ParamKeyConstants.USER_ID)) {
				return ResultBuilder.failResult(ResultMsgConstants.USER_QUERY_FAIL);
			}
			String userId = valueJson.getString(ParamKeyConstants.USER_ID);
			Query query = new Query();
			Criteria criteria = Criteria.where(ParamKeyConstants.USER_ID).is(userId);
			query.addCriteria(criteria);
			List<? extends Map<String, Object>> users = (List<? extends Map<String, Object>>) ucsTemplate.find(query,
					new HashMap<String, Object>().getClass(), MongoConstants.MONGODB_USERINFO_COLL);
			// 返回参数值
			Map<String, Object> map = Maps.newHashMap();
			if (CollectionUtils.isNotEmpty(users) && users.size() > 0) {
				map.put("userInfo", users.get(0));
				return ResultBuilder.successResult(JSONObject.toJSONString(map), ResultMsgConstants.USER_QUERY_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.USER_QUERY_FAIL);
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}
}
