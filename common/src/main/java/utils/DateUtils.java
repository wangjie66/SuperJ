/**	
 * <br>
 * Copyright 2013 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.utils <br>
 * FileName: DateUtils.java <br>
 * <br>
 * @version
 * @author wzgao
 * @created 2013-3-7
 * @last Modified 
 * @history
 */

package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 * 
 * @author wzgao
 */

public class DateUtils {

	public final static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 有符号时间
	 */
	public final static String FORMAT_NORMAL = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 无符号时间
	 */
	public final static String FORMAT_NO_SIGN = "yyyyMMddHHmmss";
	/**
	 * 时间格式 （"yyyyMMdd"）
	 */
	public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
	/**
	 * 时间格式 （"yyyyMM"）
	 */
	public final static String FORMAT_YYYYMM = "yyyyMM";
	/**
	 * 时间格式 （"yyyy"）
	 */
	public final static String FORMAT_YYYY = "yyyy";
	
	/**
	 * 时间格式 （"yyyy-MM-dd"）
	 */
	public final static String FORMAT_YYYYSMMSDD = "yyyy-MM-dd";

	/**
	 * 
	 * 获取当前时间字符串
	 * 
	 * @param formate
	 *            时间格式
	 * @return 时间字符串
	 * @author xkfeng@iflytek.com
	 * @created 2013-4-2 下午07:10:02
	 * @lastModified
	 * @history
	 */
	public static String getDate(String formate) {
		if (!StringUtils.isNotBlank(formate)) {
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_NO_SIGN);
			return df.format(new Date());
		} else {
			SimpleDateFormat df = new SimpleDateFormat(formate);
			return df.format(new Date());
		}

	}

	/**
	 * 
	 * 获取当前时间字符串，格式“yyyyMMddHHmmss”
	 * 
	 * @return 时间字符串
	 * @author xkfeng@iflytek.com
	 * @created 2013-4-2 下午07:07:48
	 * @lastModified
	 * @history
	 */
	public static String getDate() {
		String formate = FORMAT_NO_SIGN;
		SimpleDateFormat df = new SimpleDateFormat(formate);
		return df.format(new Date());
	}

	/**
	 * 获取当前时间字符串，格式“yyyyMMdd” ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
	 * 
	 * @return
	 * @author xzran
	 * @created 2016-2-22 下午06:44:25
	 * @lastModified
	 * @history
	 */
	public static String getDateEight() {
		String formate = FORMAT_YYYYMMDD;
		SimpleDateFormat df = new SimpleDateFormat(formate);
		return df.format(new Date());
	}

	/**
	 * 
	 * 时间格式化
	 * 
	 * @param date
	 *            日期时间
	 * @param formate
	 *            格式化
	 * @return 字符串类型时间
	 * @author xkfeng@iflytek.com
	 * @created 2013-4-2 下午07:08:21
	 * @lastModified
	 * @history
	 */
	public static String getDate(Date date, String formate) {
		if (!StringUtils.isNotBlank(formate)) {
			SimpleDateFormat df = new SimpleDateFormat(FORMAT_NO_SIGN);
			return df.format(date);
		} else {
			SimpleDateFormat df = new SimpleDateFormat(formate);
			return df.format(date);
		}

	}
	
	/**
	 * 	获取去年的这个时候
	 *  @param formate
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年8月15日 下午2:02:17
	 *  @lastModified       
	 *  @history
	 */
	public static String getLastYearNow(String formate){
		Calendar calendar = Calendar.getInstance();//得到日历的实例
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-1);
		return DateUtils.getDate(calendar.getTime(),formate);
	}
	
	/**
	 * 	一个月前的时间
	 *  @param formate
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年8月31日 下午7:54:07
	 *  @lastModified       
	 *  @history
	 */
	public static String getMonthAgo(String formate){
		Calendar calendar = Calendar.getInstance();//得到日历的实例
		calendar.set(Calendar.YEAR, calendar.get(Calendar.DATE)-30);
		return DateUtils.getDate(calendar.getTime(),formate);
	}
	
	/**
	 * 	5年前的年底时间
	 *  @param formate
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年8月31日 下午7:57:24
	 *  @lastModified       
	 *  @history
	 */
	public static String get5YearAgo(String formate){
		Calendar calendar = Calendar.getInstance();//得到日历的实例
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-4);
		return DateUtils.getDate(calendar.getTime(),formate);
	}
	
	/**
	 * 	取任意的月份
	 *  @param formate
	 *  @param ca
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年8月15日 下午2:11:29
	 *  @lastModified       
	 *  @history
	 */
	public static String getLastOrNextMonth(String formate, int ca){
		Calendar calendar = Calendar.getInstance();//得到日历的实例
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+ca);
		return DateUtils.getDate(calendar.getTime(),formate);
	}
	
	/**
	 * 	顺次取年份
	 *  @param formate
	 *  @param ca
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年8月31日 下午8:05:57
	 *  @lastModified       
	 *  @history
	 */
	public static String getLastOrNextYear(String formate, int ca){
		Calendar calendar = Calendar.getInstance();//得到日历的实例
		calendar.set(Calendar.MONTH, calendar.get(Calendar.YEAR)+ca);
		return DateUtils.getDate(calendar.getTime(),formate);
	}
	
	/**
	 * 	顺次取天
	 *  @param formate
	 *  @param ca
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年8月31日 下午8:06:43
	 *  @lastModified       
	 *  @history
	 */
	public static String getLastOrNextDay(String formate, int ca){
		Calendar calendar = Calendar.getInstance();//得到日历的实例
		calendar.set(Calendar.MONTH, calendar.get(Calendar.DATE)+ca);
		return DateUtils.getDate(calendar.getTime(),formate);
	}

	/**
	 * 
	 * 格式化前台时间控件格式为yyyyMMddHHmmss 年月日的情况，添加6位000000
	 * 
	 * @param date
	 *            日期时间
	 * @return 字符串类型时间
	 * @author wzgao
	 * @created 2013-3-19 下午02:46:34
	 * @lastModified
	 * @history
	 */
	public static String formatStartDate(String date) {

		if (StringUtils.isNotBlank(date) && date.length() == 10) {
			return date.replace(":", "").replace(" ", "").replace("-", "")
					.replace("/", "")
					+ "000000";
		} else if (StringUtils.isNotBlank(date) && date.length() == 4){
			return date + "0101000000";
		}else if (StringUtils.isNotBlank(date) && date.length() == 8){
			return date + "000000";
		}else if (StringUtils.isNotBlank(date)) {
			return date.replace(":", "").replace(" ", "").replace("-", "")
					.replace("/", "");
		}
		return null;
	}

	/**
	 * 
	 * 格式化前台结束时间控件格式为yyyyMMddHHmmss 年月日的情况，添加6位595959
	 * 
	 * @param date
	 *            日期时间
	 * @return 字符串类型时间
	 * @author wzgao
	 * @created 2013-4-1 下午01:34:51
	 * @lastModified
	 * @history
	 */
	public static String formatEndDate(String date) {
		if (StringUtils.isNotBlank(date) && date.length() == 10) {
			return date.replace(":", "").replace(" ", "").replace("-", "")
					.replace("/", "")
					+ "235959";
		} else if (StringUtils.isNotBlank(date) && date.length() == 4){
			return date + "1231235959";
		}else if (StringUtils.isNotBlank(date) && date.length() == 8){
			return date + "235959";
		}else if (StringUtils.isNotBlank(date)) {
			return date.replace(":", "").replace(" ", "").replace("-", "")
					.replace("/", "");
		}
		return null;
	}

	/**
	 * 
	 * 格式化前台时间控件格式为HHmmss
	 * 
	 * @param date
	 *            日期时间
	 * @return 字符串类型时间
	 * @author wzgao
	 * @created 2013-4-2 下午03:05:14
	 * @lastModified
	 * @history
	 */
	public static String formatTime(String date) {
		if (StringUtils.isNotBlank(date) && date.length() == 8) {
			return date.replace(":", "");
		}
		return null;
	}

	/**
	 * 
	 * 格式化前台日期时间
	 * 
	 * @param datetime
	 *            日期
	 * @return 格式话之后格式
	 * @author xkfeng@iflytek.com
	 * @created 2013-8-19 下午04:12:54
	 * @lastModified
	 * @history
	 */
	public static String formatDateTime(String datetime) {
		if (StringUtils.isNotBlank(datetime)) {
			return datetime.replaceAll(":", "").replaceAll(" ", "")
					.replaceAll("-", "").replaceAll("/", "");
		}
		return null;
	}

	/**
	 * 
	 * 数据库日期字符串格式化成前台展现格式
	 * 
	 * @param date
	 *            yyyyMMDD格式
	 * @return yyyy-MM-dd格式
	 * @author lwchen
	 * @created 2013-8-23 下午04:20:37
	 * @lastModified
	 * @history
	 */
	public static String formatDate(String date) {
		if (date.length() == 8) {
			return date.substring(0, 4) + "/" + date.substring(4, 6) + "/"
					+ date.substring(6, 8);
		} else if (date.length() == 14) {
			return date.substring(0, 4) + "/" + date.substring(4, 6) + "/"
					+ date.substring(6, 8) + " " + date.substring(8, 10) + ":"
					+ date.substring(10, 12) + ":" + date.substring(12, 14);
		}

		return null;
	}

	/**
	 * 
	 * 数据库日期字符串格式化成前台展现格式
	 * 
	 * @param date
	 *            yyyyMMDD格式
	 * @return yyyy-MM-dd格式
	 * @author lwchen
	 * @created 2013-8-23 下午04:20:37
	 * @lastModified
	 * @history
	 */
	public static String formatDateLine(String date) {
		if (date.length() == 8) {
			return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.substring(6, 8);
		} else if (date.length() == 14) {
			return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.substring(6, 8) + " " + date.substring(8, 10) + ":"
					+ date.substring(10, 12) + ":" + date.substring(12, 14);
		}

		return null;
	}

	/**
	 * 
	 * 获得当前月的第一天
	 * 
	 * @return data 日期
	 * @author lwchen
	 * @created 2013-8-23 下午05:06:52
	 * @lastModified
	 * @history
	 */
	public static Date getFirstDayOfMonth() {
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, 1);

		return ca.getTime();
	}

	/**
	 * 字符转时间
	 * 
	 * @param dateStr
	 *            时间字符串
	 * @param format
	 *            格式
	 * @return data 时间对象
	 * @author xkfeng@iflytek.com
	 * @created 2013-8-26 下午04:15:30
	 * @lastModified
	 * @history
	 */
	public static Date parseDate(String dateStr, String format) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date(0);
	}

	/**
	 * 
	 * 得到日期对应的周开始，结束，如"2013-08-20"->"2013.08.19-2013.08.25"
	 * 
	 * @param dateStr
	 *            日期
	 * @return 时间范围string
	 * @author xkfeng@iflytek.com
	 * @created 2013-9-5 下午07:28:50
	 * @lastModified
	 * @history
	 */
	public static String getDateRangeByDate(String dateStr) {
		try {
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			int weekday = calendar.get(Calendar.DAY_OF_WEEK);
			return getDate(addDays(d, 2 - weekday), "yyyy.M.d") + "-"
					+ getDate(addDays(d, 7 - weekday + 1), "yyyy.M.d");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * 得到给定日期所在月的最后一天
	 * 
	 * @param date
	 *            指定日期
	 * @param fromFmt
	 *            指定格式
	 * @param toFmt
	 *            转换格式
	 * @return 最后一天
	 * @author xkfeng@iflytek.com
	 * @created 2013-8-21 下午03:37:07
	 * @lastModified
	 * @history
	 */
	public static String getLastDayByMonth(String date, String fromFmt,
                                           String toFmt) {
		try {
			Date temp = new SimpleDateFormat(fromFmt).parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(temp);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			Date d = addMilliseconds(addMonths(calendar.getTime(), 1), -1);
			return getDate(d, toFmt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * 得到给定日期所在月的第一天
	 * 
	 * @param date
	 *            指定日期
	 * @param fromFmt
	 *            指定格式
	 * @param toFmt
	 *            转换格式
	 * @return 指定日期所在月的第一天
	 * @author xkfeng@iflytek.com
	 * @created 2013-8-26 下午08:31:12
	 * @lastModified
	 * @history
	 */
	public static String getFirstDayByMonth(String date, String fromFmt,
                                            String toFmt) {
		try {
			Date temp = new SimpleDateFormat(fromFmt).parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(temp);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return getDate(calendar.getTime(), toFmt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * 得到给定日期所在周星期一
	 * 
	 * @param date
	 *            指定日期
	 * @param fromFmt
	 *            指定格式
	 * @param toFmt
	 *            转换格式
	 * @return 给定日期所在周星期一
	 * @author xkfeng@iflytek.com
	 * @created 2013-8-21 下午03:27:36
	 * @lastModified
	 * @history
	 */
	public static String getFirstDateByWeek(String date, String fromFmt,
                                            String toFmt) {
		try {
			Date d = new SimpleDateFormat(fromFmt).parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			int weekday = calendar.get(Calendar.DAY_OF_WEEK);
			String day = "";
			if (1 == weekday) {
				day = getDate(addDays(d, 2 - weekday - 7), toFmt);
			} else {
				day = getDate(addDays(d, 2 - weekday), toFmt);
			}
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * 得到给定日期所在周星期日
	 * 
	 * @param date
	 *            指定日期
	 * @param fromFmt
	 *            指定格式
	 * @param toFmt
	 *            转换格式
	 * @return 日期所在周的星期日的日期
	 * @author xkfeng@iflytek.com
	 * @created 2013-8-21 下午03:28:22
	 * @lastModified
	 * @history
	 */
	public static String getLastDateByWeek(String date, String fromFmt,
                                           String toFmt) {
		try {
			Date d = new SimpleDateFormat(fromFmt).parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d);
			int weekday = calendar.get(Calendar.DAY_OF_WEEK);
			String day = "";
			if (1 == weekday) {
				day = getDate(addSeconds(d, 86400 - 1), toFmt);
			} else {
				day = getDate(addSeconds(d, (7 - weekday + 2) * 86400 - 1),
						toFmt);
			}
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 得到前一天
	 * 
	 * @param fromFmt
	 *            formate格式
	 * @return 当前日期前一天
	 * @author rongwang
	 * @created 2013-10-22 上午10:45:46
	 * @lastModified
	 * @history
	 */
	public static String getYesterday(String fromFmt) {
		// 取时间
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		// 把日期往后增加一天.整数往后推,负数往前移动
		calendar.add(Calendar.DATE, -1);
		// 这个时间就是日期往后推一天的结果
		date = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(fromFmt);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * Adds to a date returning a new object. The original date object is
	 * unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param calendarField
	 *            the calendar field to add to
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 * @deprecated Will become privately scoped in 3.0
	 */
	public static Date add(Date date, int calendarField, int amount) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of years to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of months to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of weeks to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of days to a date returning a new object. The original date
	 * object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of hours to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of minutes to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of seconds to a date returning a new object. The original
	 * date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	// -----------------------------------------------------------------------
	/**
	 * Adds a number of milliseconds to a date returning a new object. The
	 * original date object is unchanged.
	 * 
	 * @param date
	 *            the date, not null
	 * @param amount
	 *            the amount to add, may be negative
	 * @return the new date object with the amount added
	 * @throws IllegalArgumentException
	 *             if the date is null
	 */
	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

	/**
	 * 
	 * 获取当前时间 （格式yyyyMMddHHmmss）
	 * 
	 * @return
	 * @author aoliu
	 * @created 2016年1月6日 上午9:32:43
	 * @lastModified
	 * @history
	 */
	public static String getNowDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_NO_SIGN);
		return sdf.format(new Date());
	}

	/**
	 * 字符串yyyy-MM-dd HH:mm:ss 转换yyyyMMddHHmmss ｛说明该函数的含义和作用，如果函数较为复杂，请详细说明｝
	 * 
	 * @param str
	 * @return
	 * @author qxb-810
	 * @created 2016年2月23日 下午4:10:30
	 * @lastModified
	 * @history
	 */
	public static String getDateFormat(String str) {
		if (StringUtils.isNotBlank(str)) {
			return str.replace("-", "").replace(":", "").replace(" ", "");
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 
	 *  获取指定格式时间
	 *  @param dateStr
	 *  @param formatStr
	 *  @return
	 *  @throws ParseException
	 *  @author qxb-810
	 *  @created 2016年3月14日 下午3:42:21
	 *  @lastModified       
	 *  @history
	 */
	public static String getDateFormat(String dateStr, String formatStr)
			throws ParseException {
		if (StringUtils.isNotBlank(dateStr)) {
			if (StringUtils.isBlank(formatStr)) {
				formatStr = DateUtils.FORMAT_NORMAL;
			}
			SimpleDateFormat df = new SimpleDateFormat(formatStr);
			return df.format(df.parse(dateStr));
		}
		return "";
	}
	public static void main(String[] args) throws ParseException {
		System.out.println(getDate(parseDate("20171214153800", "yyyyMMddHHmmss"), "yyyy/MM/dd HH:mm:ss"));
	}
	/**
	 * 
	 *  获取当前年份
	 *  @return
	 *  @author lwsong
	 *  @created 2016年4月5日 下午5:46:46
	 *  @lastModified       
	 *  @history
	 */
	public static int getCurrentYear(){
		Calendar c= Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 *  相差 多少分钟
	 *  @return
	 *  @author khlu@iflyteck.com
	 *  @created 2017年6月19日 上午10:07:19
	 *  @lastModified
	 *  @history
	 */
	public static BigDecimal getMintesWithTwoTime(Date begin, Date end){
		if(begin == null || end == null){
			return null;
		}
		Calendar dateOne= Calendar.getInstance(),dateTwo= Calendar.getInstance();
		dateOne.setTime(begin);
		dateTwo.setTime(end);
		BigDecimal fengZi = new BigDecimal(dateTwo.getTimeInMillis() - dateOne.getTimeInMillis());
		BigDecimal fengmu = new BigDecimal(1000*50);
		return fengZi.divide(fengmu,2, RoundingMode.HALF_UP);
	}

	/**
	 *  获取n天之后的日期
	 *  @author fgwu
	 *  @created 2017年10月23日 下午3:45:41
	 *  @lastModified
	 *  @history
	 */
	public static String getDateByDays(String date, int days){
		Date formatStartDate = DateUtils.parseDate(date, DateUtils.FORMAT_YYYYMMDD);
		Date rq = DateUtils.addDays(formatStartDate, days);
		return DateUtils.getDate(rq, DateUtils.FORMAT_YYYYMMDD);
	}

	/**
	 * 格式化时间为 xx:xx:xx
	 * @param sec 秒
	 * @return
	 */
	public static String formatTime(int sec) {
		String formatTime = "00:00";
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (sec > 0) {
			minute = sec / 60;
			if (minute < 60) {
				second = sec % 60;
				formatTime = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = sec - hour * 3600 - minute * 60;
				formatTime = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return formatTime;
	}

	private static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}
	/**
	 *  ｛年月日是否相等｝
	 *  @param d1
	 *  @param d2
	 *  @return boolean
	 *  @author kuiyao
	 *  @created 2017年12月26日 上午11:23:29
	 *  @lastModified       
	 *  @history           
	 */
	public static boolean sameDate(Date d1, Date d2) {
	    if(null == d1 || null == d2)  
	        return false;  
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(d1);  
	    cal1.set(Calendar.HOUR_OF_DAY, 0);
	    cal1.set(Calendar.MINUTE, 0);
	    cal1.set(Calendar.SECOND, 0);
	    cal1.set(Calendar.MILLISECOND, 0);
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(d2);  
	    cal2.set(Calendar.HOUR_OF_DAY, 0);
	    cal2.set(Calendar.MINUTE, 0);
	    cal2.set(Calendar.SECOND, 0);
	    cal2.set(Calendar.MILLISECOND, 0);
	    return  cal1.getTime().equals(cal2.getTime());  
	}  
}
