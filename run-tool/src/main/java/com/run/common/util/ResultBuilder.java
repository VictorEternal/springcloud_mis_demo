package com.run.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.run.base.util.DateUtils;
import com.run.service.common.entity.Result;
import com.run.service.common.entity.ResultStatus;
import com.run.service.common.entity.constants.ResultCodeConstants;
import com.run.service.common.entity.constants.ResultMsgConstants;

/**
 * 
 * 构建返回参数
 * 
 * @author: lyc
 * @version: 1.0, 2017年1月11日
 */
public class ResultBuilder {
	/**
	 * 
	 * 构建返回值
	 *
	 * @param data
	 *            业务数据
	 * @param msgCode
	 *            状态编码
	 * @param msgDetail
	 *            状态消息
	 * @param exception
	 *            异常信息
	 * 
	 * @return
	 */
	public static Result getResult(JSONObject data, String msgCode, String msgDetail, Throwable exception) {
		Result result = new Result();
		ResultStatus status = new ResultStatus();
		status.setResultCode(msgCode);
		status.setResultMessage(msgDetail);
		status.setTimeStamp(DateUtils.stampToDate(System.currentTimeMillis() + ""));

		result.setValue(data);
		result.setResultStatus(status);
		if (exception != null) {
			result.setException(exception.getMessage());
		}
		return result;
	}



	/**
	 * 
	 * 成功状态
	 *
	 * @param data
	 *            业务数据
	 * @param msgDetail
	 *            返回消息
	 * @return
	 */
	public static Result successResult(String data, String msgDetail) {
		return getResult(JSON.parseObject(data), ResultCodeConstants.RE_CODE_0000, msgDetail, null);
	}



	/**
	 * 
	 * 传入参数为空时的返回值
	 *
	 * @return
	 */
	public static Result emptyResult() {
		return getResult(null, ResultCodeConstants.RE_CODE_0001, ResultMsgConstants.EMPTY_PARAM, null);
	}



	/**
	 * 
	 * 传入参数非法时的返回值
	 *
	 * @return
	 */
	public static Result invalidResult() {
		return getResult(null, ResultCodeConstants.RE_CODE_0002, ResultMsgConstants.INVALID_PARAM, null);
	}



	/**
	 * 
	 * 服务内部执行异常时的返回值
	 *
	 * @return
	 */
	public static Result exceptionResult(Throwable exception) {
		return getResult(null, ResultCodeConstants.RE_CODE_0003, ResultMsgConstants.RUNTIME_EXCEPTION, exception);
	}



	/**
	 * 
	 * 传入参数没有业务数据的返回值
	 *
	 * @return
	 */
	public static Result noBuisnessResult() {
		return getResult(null, ResultCodeConstants.RE_CODE_0004, ResultMsgConstants.NO_BUSINESS_EXCEPTION, null);
	}



	/**
	 * 
	 * 失败状态
	 *
	 * 
	 * @param msgDetail
	 *            失败消息
	 * @return
	 */
	public static Result failResult(String msgDetail) {
		return getResult(null, ResultCodeConstants.RE_CODE_0005, msgDetail, null);
	}
}
