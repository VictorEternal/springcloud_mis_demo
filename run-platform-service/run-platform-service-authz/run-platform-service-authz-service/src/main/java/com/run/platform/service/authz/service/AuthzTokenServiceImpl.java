package com.run.platform.service.authz.service;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.run.base.util.DateUtils;
import com.run.base.util.VerifyCodeUtil;
import com.run.common.util.ParamChecker;
import com.run.common.util.ResultBuilder;
import com.run.platform.service.authz.api.AuthzTokenService;
import com.run.service.common.entity.Result;
import com.run.service.common.entity.constants.MongoConstants;
import com.run.service.common.entity.constants.ParamKeyConstants;
import com.run.service.common.entity.constants.ResultMsgConstants;
import com.run.service.common.entity.constants.UcsApiUrlConstants;

@RestController
@RequestMapping(UcsApiUrlConstants.API_USER_TOKEN)
public class AuthzTokenServiceImpl implements AuthzTokenService {
	@Autowired
	private StringRedisTemplate redisTemplate;



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_AUTHENTICATE, method = RequestMethod.POST)
	public Result authenticate(@RequestBody String authInfo) {
		// 参数为空
		if (ParamChecker.isBlank(authInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(authInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject authInfoJson = JSON.parseObject(authInfo);
			// 没有用户数据
			if (!authInfoJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取传入的用户数据
			JSONObject quInfoJson = authInfoJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 获取参数token
			String token = quInfoJson.getString(ParamKeyConstants.TOKEN);
			// 获取参数userID
			String userId = quInfoJson.getString(ParamKeyConstants.USER_ID);
			// 根据token获取用户
			String redisUserId = redisTemplate.opsForValue().get(token);
			if (userId != null && redisUserId != null && userId.equals(redisUserId)) {
				return ResultBuilder.successResult(null, ResultMsgConstants.TOKEN_AUTHZ_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.TOKEN_AUTHZ_FAIL);
			}

		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_CREATE_TOKEN, method = RequestMethod.POST)
	public Result createToken(@RequestBody String userInfo) {
		// 参数为空
		if (ParamChecker.isBlank(userInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(userInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {
			JSONObject userInfoJson = JSON.parseObject(userInfo);
			// 没有业务数据
			if (!userInfoJson.containsKey(ParamKeyConstants.USER_ID)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取参数userId
			String userId = userInfoJson.getString(ParamKeyConstants.USER_ID);
			// 生成token
			String token = UUID.randomUUID().toString().replace("-", "");

			redisTemplate.opsForValue().set(token, userId);
			// 返回参数值
			Map<String, String> map = Maps.newHashMap();
			map.put(ParamKeyConstants.USER_ID, userId);
			map.put(ParamKeyConstants.TOKEN, token);

			return ResultBuilder.successResult(JSONObject.toJSONString(map), ResultMsgConstants.TOKEN_CREATE_SUCCESS);
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_REMOVE_TOKEN, method = RequestMethod.POST)
	public Result removeToken(@RequestBody String userInfo) {
		// 参数为空
		if (ParamChecker.isBlank(userInfo)) {
			return ResultBuilder.emptyResult();
		}
		// 参数非法
		if (ParamChecker.isNotMatchJson(userInfo)) {
			return ResultBuilder.invalidResult();
		}
		try {

			JSONObject userInfoJson = JSON.parseObject(userInfo);
			// 没有用户数据
			if (!userInfoJson.containsKey(ParamKeyConstants.QU_INFO)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取传入的用户数据
			JSONObject quInfoJson = userInfoJson.getJSONObject(ParamKeyConstants.QU_INFO);
			// 没有业务数据
			if (!quInfoJson.containsKey(ParamKeyConstants.TOKEN)) {
				return ResultBuilder.noBuisnessResult();
			}
			// 获取参数token
			String token = quInfoJson.getString(ParamKeyConstants.TOKEN);
			// 获取参数userID
			String userId = quInfoJson.getString(ParamKeyConstants.USER_ID);
			String redisUserId = redisTemplate.opsForValue().get(token);
			// 如果从库中获取到的userId与传入的userId相同则从库中移除
			if (userId != null && redisUserId != null && userId.equals(redisUserId)) {
				// 移除库中的key-value
				redisTemplate.delete(token);
				return ResultBuilder.successResult(null, ResultMsgConstants.TOKEN_REMOVE_SUCCESS);
			} else {
				return ResultBuilder.failResult(ResultMsgConstants.TOKEN_REMOVE_FAIL);
			}
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_CODE_GET_USERID, method = RequestMethod.POST)
	public Result getUserId(@RequestBody String tokenInfo) {
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
			// 获取参数token
			String token = tokenInfoJson.getString(ParamKeyConstants.TOKEN);

			String userId = redisTemplate.opsForValue().get(token);
			// 返回参数值
			Map<String, String> map = Maps.newHashMap();
			map.put(ParamKeyConstants.USER_ID, userId);
			map.put(ParamKeyConstants.TOKEN, token);

			return ResultBuilder.successResult(JSONObject.toJSONString(map), ResultMsgConstants.TOKEN_GET_USERID_SUCCESS);
		} catch (Exception e) {
			return ResultBuilder.exceptionResult(e);
		}
	}
}
