package com.run.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {
	public final static String	COMPACTDATE_FORMAT		= "yyyyMMdd";
	public final static String	COMPACTDATETIME_FORMAT	= "yyyyMMddHHmmss";
	public final static String	DATE_FORMAT				= "yyyy-MM-dd";
	public final static String	DATETIME_FORMAT			= "yyyy-MM-dd HH:mm:ss";
	public final static String	FULL_FORMAT				= "yyyy-MM-dd HH:mm:ss.SSS";
	public final static String	DATE_START				= "yyyy-MM-dd 00:00:00";
	public final static String	DATE_END				= "yyyy-MM-dd 23:59:59";
	public static final int		DateMillSecond			= 1000 * 60 * 60 * 24;
	public static final String	END						= "end";
	public static final String	START					= "start";



	public static String formatDate(Date date) {
		if (date == null)
			return null;
		return new SimpleDateFormat(DATETIME_FORMAT).format(date);
	}



	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		return new SimpleDateFormat(pattern).format(date);
	}



	public static Date addDate(Date date, int value) {
		return addDate(date, Calendar.DATE, value);
	}



	public static Date addDate(Date date, int field, int value) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(field, value);
		if (date.getClass().equals(java.util.Date.class))
			return new java.util.Date(c.getTimeInMillis());
		else if (date.getClass().equals(java.sql.Date.class))
			return new java.sql.Date(c.getTimeInMillis());
		else if (date.getClass().equals(java.sql.Timestamp.class))
			return new java.sql.Timestamp(c.getTimeInMillis());
		else
			return c.getTime();
	}



	public static Date valueOf(String datestr) {
		return toDate(datestr);
	}



	public static Date toDate(String datestr) {
		if (StringUtils.isBlank(datestr))
			return null;
		try {
			return org.apache.commons.lang.time.DateUtils.parseDate(datestr, new String[] { COMPACTDATE_FORMAT,
					COMPACTDATETIME_FORMAT, DATE_FORMAT, DATETIME_FORMAT, FULL_FORMAT });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



	/**
	 * 计算倒计时剩余时间(s单位)
	 * 
	 * @param eTime
	 * @return
	 * @throws Exception
	 */
	public static int getLastTime(long eTime) throws Exception {
		Calendar calendar = Calendar.getInstance();
		int value = 0;
		value = (int) (Math.abs(eTime - calendar.getTimeInMillis()) / 1000);
		return value;
	}



	/**
	 * 获取一天的开始时间(00:00:00)或者结束时间(23:59:59)的字符串形式('yyyy-MM-dd hh24:mm:ss')
	 * 
	 * @param str
	 *            String start(开始时间);end(结束时间)
	 * @return String 'yyyy-MM-dd hh24:mm:ss'
	 */
	public static String getDateString(String str) {
		if (str.equalsIgnoreCase(START)) {
			return DateUtils.formatDate(new Date(), DateUtils.DATE_START);
		} else if (str.equalsIgnoreCase(END)) {
			return DateUtils.formatDate(new Date(), DateUtils.DATE_END);
		} else {
			return null;
		}
	}



	/**
	 * 
	 * 
	 * 将时间字符串转换成时间戳字符串
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static String dateToStamp(String dateStr) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
		Date date = simpleDateFormat.parse(dateStr);
		long ts = date.getTime();
		return String.valueOf(ts);
	}



	/**
	 * 
	 * 将时间戳字符串转换成时间字符串
	 *
	 * @param timestamp
	 * @return
	 */
	public static String stampToDate(String timestamp) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(timestamp);
		Date date = new Date(lt);
		return simpleDateFormat.format(date);
	}

}
