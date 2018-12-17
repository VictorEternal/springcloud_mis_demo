package org.run.shared.service.usc.manage.service.userInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.run.shared.service.usc.manage.api.userInfo.UserInfoRestManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.run.common.util.ResultBuilder;
import com.run.service.common.entity.Result;
import com.run.service.common.entity.constants.UcsApiUrlConstants;
import com.run.shared.usc.api.curd.UserBaseCurdService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(UcsApiUrlConstants.API_USERMANAGE_BASE_PATH)

class UserInfoRestManageServiceImpl implements UserInfoRestManageService {
	private static final Log	log	= LogFactory.getLog(UserInfoRestManageServiceImpl.class);
	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate		restTemplate;



	@Override
	public Result getUserInfo(String queryParamsInfo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Result updatePassWord(String paramsInfo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_USERMANAGE_SAVEUSER, method = RequestMethod.POST)
	public Result saveUser(@RequestBody String registerInfo) {
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(registerInfo, headers);
		String url = "http://192.168.31.218:8800" + UcsApiUrlConstants.API_USER_BASE_PATH
				+ UcsApiUrlConstants.API_CODE_REGISTER;
		try {
			Result result = restTemplate.postForObject(url, formEntity, Result.class);
			return result;

		} catch (Exception e) {
			log.error("保存用户信息异常：", e);
			return ResultBuilder.exceptionResult(e);
		}
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_USERMANAGE_UPDATEUSER, method = RequestMethod.POST)
	public Result updateUser(String updateUserParam) {
		return null;
		// try {
		// Result result = userBaseCurdService.updateUser(updateUserParam);
		// return result;
		// } catch (Exception e) {
		// log.error("保存用户信息异常：", e);
		// return ResultBuilder.exceptionResult(e);
		// }
	}



	@Override
	@RequestMapping(value = UcsApiUrlConstants.API_USERMANAGE_DELETEUSERINFO, method = RequestMethod.POST)
	public Result deleteuserinfo(String userId) {
		return null;
		// try {
		// Result result = userBaseCurdService.deleteUser(userId);
		// return result;
		// } catch (Exception e) {
		// log.error("保存用户信息异常：", e);
		// return ResultBuilder.exceptionResult(e);
		// }
	}



	@Override
	public Result getUserbyId(String queryUserParam) {
		// TODO Auto-generated method stub
		return null;
	}

}
