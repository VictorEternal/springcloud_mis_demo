package com.run.service.common.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.alibaba.fastjson.JSONObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Result implements Serializable {

	private static final long	serialVersionUID	= -6925924956850004727L;

	/* 业务数据 */
	@JsonProperty("result")
	@XmlElement(name = "result")
	private JSONObject			result;

	/* 返回状态 */
	@JsonProperty("status")
	@XmlElement(name = "resultStatus")
	private ResultStatus		resultStatus;

	/* 返回异常 */
	@JsonProperty("exception")
	@XmlElement(name = "exception")
	private String				exception;

	/* 返回的附加参数 */
	@JsonProperty("attachments")
	@XmlElement(name = "attachments")
	private Map<String, String>	attachments			= new HashMap<String, String>();



	public Result() {
	}



	public Result(JSONObject result) {
		this.result = result;
	}



	public Result(String exception) {
		this.exception = exception;
	}



	public JSONObject recreate() throws Throwable {
		if (exception != null) {
			throw new Exception(exception);
		}
		return result;
	}



	public JSONObject getValue() {
		return result;
	}



	public void setValue(JSONObject value) {
		this.result = value;
	}



	public String getException() {
		return exception;
	}



	public void setException(String e) {
		this.exception = e;
	}



	public boolean hasException() {
		return exception != null;
	}



	public Map<String, String> getAttachments() {
		return attachments;
	}



	public String getAttachment(String key) {
		return attachments.get(key);
	}



	public String getAttachment(String key, String defaultValue) {
		String result = attachments.get(key);
		if (result == null || result.length() == 0) {
			result = defaultValue;
		}
		return result;
	}



	public void setAttachments(Map<String, String> map) {
		if (map != null && map.size() > 0) {
			attachments.putAll(map);
		}
	}



	public void setAttachment(String key, String value) {
		attachments.put(key, value);
	}



	public ResultStatus getResultStatus() {
		return resultStatus;
	}



	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}



	@Override
	public String toString() {
		return "Result [result=" + result + ", exception=" + exception + "]";
	}
}