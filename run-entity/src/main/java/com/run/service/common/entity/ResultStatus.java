package com.run.service.common.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultStatus {
	@JsonProperty("code")
	@XmlElement(name = "code")
	private String	resultCode;

	@JsonProperty("message")
	@XmlElement(name = "message")
	private String	resultMessage;

	@JsonProperty("timeStamp")
	@XmlElement(name = "timeStamp")
	private String	timeStamp;



	/**
	 * 
	 * 状态编号
	 *
	 * @return
	 */
	public String getResultCode() {
		return resultCode;
	}



	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}



	/**
	 * 
	 * 状态消息
	 *
	 * @return
	 */
	public String getResultMessage() {
		return resultMessage;
	}



	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}



	/**
	 * 
	 * 时间戳
	 *
	 * @return
	 */
	public String getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}



	public static ResultStatus getInstance() {
		return new ResultStatus();
	}
}
