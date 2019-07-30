package com.dream.basics.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * @author lishenglin
 * @date 2018/05/09
 * <p>备注：日期常用方法,提供对日期型数据的常用处理方法
 */
public final class DateUtil {
	private static final Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * 日期格式化对象,日期型（yyyy-MM-dd）
	 */
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 日期格式化对象,日期型（yyyy-MM-dd）
	 */
	private static final SimpleDateFormat DATE_FORMATS = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 日期时间格式化对象,日期时间型（yyyy-MM-dd HH:mm:ss）
	 */
	private static final SimpleDateFormat DATE_TIME_FORMAT         =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 日期时间格式(24小时制):yyyyMMdd_HH:mm:ss<br>
	 * 例如:20051102 23:01:01
	 */
	public static final  SimpleDateFormat DATETIME24_PATTERN_BIAS_ =
			new SimpleDateFormat("yyyyMMdd_HH:mm:ss");

	/**
	 * 日期时间格式(24小时制):yyyyMMdd HH:mm:ss<br>
	 * 例如:20051102230101
	 */
	public static final SimpleDateFormat DATE_TIME_FORMAT_24 = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 日期格式:yyyy-mm-dd<br>
	 * 例如:2005-11-02
	 */
	public static final String DATE_PATTERN_LINE = "yyyy-MM-dd";

	/**
	 * 日期格式:yyyy/mm/dd<br>
	 * 例如:2005/11/02
	 */
	public static final String DATE_PATTERN_BIAS = "yyyy/MM/dd";

	/**
	 * 日期时间格式(24小时制):yyyy-mm-dd HH:mm:ss<br>
	 * 例如:2005-11-02 23:01:01
	 */
	public static final String DATETIME24_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式:yyyyMMdd<br>
	 * 例如:2005/11/02
	 */
	public static final String DATE_PATTERN_BIASSS = "yyyyMMdd";

	/**
	 * 日期时间格式(12小时制):yyyy-mm-dd hh:mm:ss<br>
	 * 例如:2005-11-02 11:01:01
	 */
	public static final String DATETIME12_PATTERN_LINE = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 日期时间格式(24小时制):yyyy/mm/dd HH:mm:ss<br>
	 * 例如:2005/11/02 23:01:01
	 */
	public static final String DATETIME24_PATTERN_BIAS = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 日期时间格式(12小时制):yyyy/mm/dd hh:mm:ss<br>
	 * 例如:2005/11/02 11:01:01
	 */
	public static final String DATETIME12_PATTERN_BIAS = "yyyy/MM/dd hh:mm:ss";

	// 静态初始化时区
	static {
		// 中国时区
		TimeZone tzChina = TimeZone.getTimeZone("Asia/Chongqing");
		DateUtil.DATE_TIME_FORMAT.setTimeZone(tzChina);
		DateUtil.DATE_FORMAT.setTimeZone(tzChina);
	}

	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param now 给定日期
	 * @return 被格式化后的字符串
	 */
	public static String formatDate(Calendar now) {
		return formatDate(now, DATETIME24_PATTERN_LINE);
	}



	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 给定日期
	 * @return 被格式化后的字符串
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATETIME24_PATTERN_LINE);
	}



	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param now            给定日期
	 * @param formatePattern 格式化模式
	 * @return 被格式化后的字符串<br>
	 */
	public static String formatDate(Calendar now, String formatePattern) {
		if (now == null) {
			return null;
		}
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATETIME24_PATTERN_LINE;
		}
		Date tempDate = now.getTime();
		SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
		return dateFormate.format(tempDate);
	}



	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param date           java.util.Date类型数据
	 * @param formatePattern 指定的日期格式化模式
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate(Date date, String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATETIME24_PATTERN_LINE;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}



	/**
	 * 将java.sql.Timestamp数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param date           Timestamp数据
	 * @param formatePattern 日期格式化模式
	 * @return 格式化后的日期的字符串形式
	 */
	public static String formatDate(java.sql.Timestamp date, String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATETIME24_PATTERN_LINE;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}



	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd
	 *
	 * @param date java.util.Date类型数据
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate_(Date date) {
		return formatDate(date, DATE_PATTERN_LINE);
	}



	/**
	 * 将代表日期的长整形数值转换为yyyy-MM-dd HH:mm:ss格式的字符串<br>
	 *
	 * @param datetime 需要转换的日期的长整形数值
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long datetime) {
		return formatDate(datetime, DATETIME24_PATTERN_LINE);
	}



	/**
	 * 将代表日期的字符串转换yyyy-MM-dd HH:mm:ss格式的字符串
	 *
	 * @param datetime 需要转换的日期
	 * @return 格式化后的日期字符串
	 */
	public static String formate(String datetime) {
		return formatDate(datetime, DATETIME24_PATTERN_LINE);
	}



	/**
	 * 将代表日期的字符串转换未指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param datetime       需要转换的日期的字符串
	 * @param formatePattern 指定的日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(String datetime, String formatePattern) {
		if (datetime == null || datetime.trim().length() <= 0) {
			return "";
		}
		try {
			Date date = null;
			boolean flag =
					formatePattern != null
							&& (formatePattern.equals(DATE_PATTERN_BIAS)
							|| formatePattern.equals(DATE_PATTERN_LINE));
			if (flag) {
				date = parseDate(datetime);
			} else {
				date = parseDateTime(datetime);
			}

			return formatDate(date, formatePattern);
		} catch (Exception ex) {
			log.error("日期转换失败:", ex);
			return null;
		}
	}



	/**
	 * 将代表日期的长整形数值转换为y指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param datetime       需要转换的日期的长整形数值
	 * @param formatePattern 指定的日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long datetime, String formatePattern) {
		if (datetime <= 0) {
			return "";
		}
		try {
			Date date = new Date(datetime);
			return formatDate(date, formatePattern);
		} catch (Exception ex) {
			log.error("日期转换失败:", ex);
			return null;
		}
	}



	/**
	 * 将java.sql.Date数据转换为指定格式的字符串<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 *
	 * @param date java.sql.Date类型数据
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate(java.sql.Date date) {
		return formatDate(toUtilDate(date));
	}



	/**
	 * 将java.sql.Date转换为java.util.Date数据类型
	 *
	 * @param date 需要转换的java.sql.Date数据
	 * @return 转换后的java.util.Date数据
	 */
	public static Date toUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		} else {
			return new Date(date.getTime());
		}
	}



	/**
	 * 得到当前系统日期
	 *
	 * @return 得到系统当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}



	/**
	 * 得到当前系统日期
	 *
	 * @return 得到系统当前日期
	 */
	public static java.sql.Timestamp getCurrentDateTime() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}



	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyy-MM-dd格式
	 *
	 * @param src 日期数据字符串
	 * @return java.util.Date型日期类型数据
	 */
	public static Date parseDate(String src) {
		if (src == null || src.trim().length() <= 0) {
			return null;
		}
		try {
			return DateUtil.DATE_FORMAT.parse(src);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}



	public static Date parseDateDayMonthYear(String src) {
		if (src == null || src.trim().length() <= 0) {
			return null;
		}
		try {
			SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
			return sp.parse(src);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}



	/**
	 * 根据日期、小时、分钟、秒组合成日期时间
	 *
	 * @param date   日期
	 * @param hour   小时
	 * @param minute 分钟
	 * @param second 秒
	 * @return 组合后的日期时间
	 */
	public static Date parseDate(String date, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();

		Date dateObj = parseDate(date);
		cal.set(getYear(dateObj), getMonth(dateObj), getDay(dateObj), hour, minute, second);
		return cal.getTime();
	}



	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyy-MM-dd HH:mm:ss格式
	 *
	 * @param src 日期数据字符串
	 * @return java.util.Date型日期时间型数据
	 */
	public static Date parseDateTime(String src) {
		if (StringUtils.isBlank(src)) {
			return null;
		}

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(src);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}



	/**
	 * 解析Date，字符串必须是yyyy-MM-dd HH:mm:ss格式
	 *
	 * @param src 日期数据字符串
	 * @return
	 */
	public static Date parseAllDate(String src) {
		try {
			if (src == null || "".equals(src)) {
				return null;
			}
			if (src.length() == 10) {
				return DATE_FORMAT.parse(src);
			} else if (src.length() == 19) {

				return DATE_TIME_FORMAT.parse(src);
			} else if (src.length() > 19) {
				src = src.substring(0, 19);
				return DATE_TIME_FORMAT.parse(src);
			} else {
				// throw new BusinessRunTimeException(
				// "长度不符。日期格式为:yyyy-mm-dd:,时间格式为:yyyy-mm-dd hh:mi:ss");
				return null;
			}

		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}



	/**
	 * 将java.util.Date转换为java.sql.Date数据类型
	 *
	 * @param date 需要转换的java.util.Date数据
	 * @return 转换后的java.sql.Date数据
	 */
	public static java.sql.Date toSqlDate(Date date) {
		if (date == null) {
			return null;
		} else {
			return new java.sql.Date(date.getTime());
		}
	}



	/**
	 * 将java.util.Date转换为java.sql.Timestamp
	 *
	 * @param date 需要转换的java.util.Date数据
	 * @return 转换后的java.sql.Timestamp
	 */
	public static java.sql.Timestamp toTimestamp(Date date) {
		if (date == null) {
			return null;
		} else {
			return new java.sql.Timestamp(date.getTime());
		}
	}



	/**
	 * 得到指定年月的最后一天
	 *
	 * @param year  指定年
	 * @param month 指定月
	 * @return 指定年月的最后一天
	 */
	public static Date getMonthLastDay(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar lCal = Calendar.getInstance();
			lCal.set(year, month, 1);
			lCal.add(Calendar.DATE, -1);
			return lCal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}



	/**
	 * 得到指定年月的第一天
	 *
	 * @param year  指定年
	 * @param month 指定月
	 * @return 指定年月的第一天
	 */
	public static Date getMonthFirstDay(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, 1, 0, 0, 0);
			return cal.getTime();
		} else {
			// throw new BusinessRunTimeException("月份传入错误必须介于1和12之间");
			return null;
		}
	}



	/**
	 * 得到指定年月的最后一天的最后小时分秒
	 *
	 * @param year  指定年
	 * @param month 指定月
	 * @return 年月的最后一天的最后小时分秒
	 */
	public static Date getMonthLastDatetime(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar lCal = Calendar.getInstance();
			lCal.set(year, month, 1, 23, 59, 59);
			lCal.add(Calendar.DATE, -1);
			return lCal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}



	/**
	 * 得到指定年月的第一天的开始小时分秒
	 *
	 * @param year  指定年
	 * @param month 指定月
	 * @return 年月的第一天的开始小时分秒
	 */
	public static Date getMonthFirstDatetime(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, 1, 0, 0, 0);
			return cal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}



	/**
	 * 得到指定日期所在周的指定星期几的日期
	 *
	 * @param date      指定日期
	 * @param dayOfWeek 指定星期几
	 * @return 指定星期几的日期
	 */
	public static Date getDateOfWeek(Date date, int dayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return c.getTime();
	}



	/**
	 * 得到指定日期为当前年的第几周
	 *
	 * @param date 指定日期
	 * @return 当前年的第几周
	 */
	public static int getWeekOfYear2(Date date) {
		Calendar cal = Calendar.getInstance();
		// 设置周一为一周的第一天
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 设置周一为第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}



	/**
	 * 得到指定日期为当前年的第几周
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}



	/**
	 * 得到指定年的第几周的第一天日期
	 *
	 * @param year  指定年
	 * @param nWeek 第几周
	 * @return 第一天日期
	 */
	public static Date getWeekOfFirstDate(int year, int nWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, nWeek - 1);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 1);
		return c.getTime();
	}



	/**
	 * 周一为一周的第一天
	 *
	 * @param year
	 * @param nWeek
	 * @return
	 */
	public static Date getWeekOfFirstDate2(int year, int nWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, nWeek);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return c.getTime();
	}



	/**
	 * 得到指定年的第几周的最后一天日期
	 *
	 * @param year  指定年
	 * @param nWeek 第几周
	 * @return 最后一天日期
	 */
	public static Date getWeekOfLastDate(int year, int nWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, nWeek);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}



	/**
	 * 星期天为一周的最后一天
	 *
	 * @param year
	 * @param nWeek
	 * @return
	 */
	public static Date getWeekOfLastDate2(int year, int nWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, nWeek);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return c.getTime();
	}



	/**
	 * 得到当前年
	 *
	 * @return 当前年
	 */
	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}



	public static int getCurrentDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}



	public static String getFirstDayOfMonth(int year, int month, String PATTERN_BIASSS) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return formatDate(c.getTime(), PATTERN_BIASSS);
	}



	public static String getFirstDayOfMonth() {
		return getFirstDayOfMonth(DATE_PATTERN_BIASSS);
	}



	public static String getFirstDayOfMonth(String PATTERN_BIASSS) {
		Calendar c = Calendar.getInstance();
		return getFirstDayOfMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH), PATTERN_BIASSS);
	}



	public static String getLastDayOfMonth(int year, int month, String PATTERN_BIASSS) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return formatDate(c.getTime(), PATTERN_BIASSS);
	}



	public static String getLastDayOfMonth() {
		return getLastDayOfMonth(DATE_PATTERN_BIASSS);
	}



	public static String getLastDayOfMonth(String PATTERN_BIASSS) {
		Calendar c = Calendar.getInstance();
		return getLastDayOfMonth(c.get(Calendar.YEAR), c.get(Calendar.MONTH), PATTERN_BIASSS);
	}



	/**
	 * 得到日期中的年份
	 *
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}



	/**
	 * 得到日期中的月份
	 *
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}



	/**
	 * 得到日期中的天
	 *
	 * @param date 日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}



	/**
	 * 得到日期中的小时
	 *
	 * @param date 日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}



	/**
	 * 得到当前月<br>
	 * 0:一月;1:二月;....;11:十二月
	 *
	 * @return 当前月
	 */
	public static int getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH);
	}



	/**
	 * 根据日期判断是星期几1:表示星期一
	 *
	 * @param pTime
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("deprecation")
	public static int dayForWeek(String pTime) throws Throwable {

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date tmpDate = format.parse(pTime);

			Calendar cal = new GregorianCalendar();

			cal.set(tmpDate.getYear(), tmpDate.getMonth(), tmpDate.getDay());

			return cal.get(Calendar.DAY_OF_WEEK);
		} catch (Exception e) {
			throw new Throwable();
		}
	}



	/**
	 * 取某个时间点后几个月的某个时间点
	 *
	 * @param d     原日期
	 * @param count 几个月后
	 * @return 目标日期
	 */
	public static Date afterMonths(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + count);
		return c.getTime();
	}



	/**
	 * 得到指定年的第一天
	 *
	 * @param year 指定年
	 * @return 指定年的第一天
	 */
	public static Date getYearFirstDay(int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, 0, 1, 0, 0, 0);
		return cal.getTime();
	}



	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}



	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}



	/**
	 * 计算时间差.时间必须是头天00:00:00-23:59:59
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static long dateDifference(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long diff = null;
		try {
			diff = sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long day = diff / (1000 * 3600 * 24);
		return day;
	}



	/**
	 * @param showDate
	 * @param format
	 * @param interDay
	 * @return
	 */
	public static String turnDate(String showDate, String format, int interDay) {

		// 日期加指定天数
		Calendar cal = Calendar.getInstance();

		Date tempDate = DateUtil.parseDates(showDate);
		if (null == tempDate) {
			return null;
		}
		cal.setTime(tempDate);

		cal.add(Calendar.DAY_OF_MONTH, interDay);

		String next = DateUtil.formatDates(cal.getTime(), format);

		return next;
	}



	/**
	 * 日期增加天数
	 *
	 * @param date
	 * @param interDay
	 * @return
	 */
	public static Date addDate(Date date, int interDay) {

		// 日期加指定天数
		Calendar cal = Calendar.getInstance();

		if (null == date) {
			return null;
		}
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_MONTH, interDay);

		return cal.getTime();
	}



	/**
	 * 增加月
	 *
	 * @param date
	 * @param interMonth
	 * @return
	 */
	public static Date addMonth(Date date, int interMonth) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.MONTH, interMonth);
		return cl.getTime();
	}



	/**
	 * 增加小时
	 *
	 * @param date
	 * @param interHour
	 * @return
	 */
	public static Date addHour(Date date, int interHour) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.HOUR, interHour);
		return cl.getTime();
	}



	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyyMMdd HH:mm:ss
	 *
	 * @param date           java.util.Date类型数据
	 * @param formatePattern 指定的日期格式化模式
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDates(Date date, String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATE_PATTERN_BIASSS;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}



	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyyMMdd格式
	 *
	 * @param src 日期数据字符串
	 * @return java.util.Date型日期类型数据
	 */
	public static Date parseDates(String src) {
		if (src == null || src.trim().length() <= 0) {
			return null;
		}
		try {
			return DateUtil.DATE_FORMATS.parse(src);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}



	/**
	 * 获取今天0点时间戳
	 *
	 * @return
	 */
	public static long getTodayBeginTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}



	/**
	 * 获取昨天0点时间戳
	 *
	 * @return
	 */
	public static long getYesterdayBeginTime() {
		return getTodayBeginTime() - (1000 * 60 * 60 * 24);
	}



	/**
	 * 获取当前时间前几周的第一天时间
	 *
	 * @return
	 */
	public static String getPreviousWeekday(int weeks) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}



	/**
	 * 获取当前时间周一的时间
	 *
	 * @return
	 */
	public static String getThisPreviousWeekday(int weeks) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}



	/**
	 * 获取当前时间前一周的最后一天时间
	 *
	 * @return
	 */
	public static String getPreviousWeekSunday(int weeks) {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(5, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = sdf.format(monday);
		return preMonday;
	}



	/**
	 * 上多少月的第一天 flag 表示前几月可以加减 上一月-1 下一月+1
	 *
	 * @return
	 */
	public static String getPreviousMonthFirst(int flag) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(5, 1);
		lastDate.add(2, flag);
		str = sdf.format(lastDate.getTime());
		return str;
	}



	/**
	 * 上几月的最后一天
	 *
	 * @return
	 */
	public static String getPreviousMonthEnd(int flag) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, flag);
		lastDate.set(5, 1);
		lastDate.roll(5, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}



	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		int dayOfWeek = cd.get(7) - 1;

		if (dayOfWeek == 1) {
			return 0;
		}

		return (1 - dayOfWeek);
	}



	/**
	 * 获取当前年的第一天
	 *
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}



	/**
	 * 获取当年的最后一天
	 *
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}



	/**
	 * 获取某年最后一天日期
	 *
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}



	/**
	 * 获取某年第一天日期
	 *
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}



	/**
	 * 根据年月获取当月的所有天数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<String> getMonthFullDay(int year, int month) {
		SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
		List<String> fullDayList = new ArrayList<>(32);
		// 获得当前日期对象
		Calendar cal = Calendar.getInstance();
		cal.clear(); // 清除信息
		cal.set(Calendar.YEAR, year);
		// 1月从0开始
		cal.set(Calendar.MONTH, month - 1);
		// 当月1号
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int count = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int j = 1; j <= count; j++) {
			fullDayList.add(dateFormatYYYYMMDD.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return fullDayList;
	}



	public static Date parseDate(String source, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * 获取某天的最大时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
		LocalDateTime localDateTime =
				LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
		;
		LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}



	/**
	 * 获取某天的最小时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
		LocalDateTime localDateTime =
				LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
	}



	/**
	 * 求两个
	 *
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return
	 */
	public static Long betweenAsSeconds(Date start, Date end) {
		return Duration.between(start.toInstant(), end.toInstant()).getSeconds();
	}



	/**
	 * 根据年,周获取这周的开始时间
	 *
	 * @param year
	 * @param weekNum
	 * @return
	 */
	public static Date getStartTimeOfWeekNum(int year, int weekNum) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		return getStartOfDay(cal.getTime());
	}



	/**
	 * 根据年,周获取这周的结束时间
	 *
	 * @param year
	 * @param weekNum
	 * @return
	 */
	public static Date getEndTimeOfWeekNum(int year, int weekNum) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		return getEndOfDay(cal.getTime());
	}



	/**
	 * 获取未来任意天内的日期数组
	 *
	 * @param intervals intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> getFutureDays(int intervals) {
		ArrayList<String> futureDaysList = new ArrayList<>();
		for (int i = 1; i <= intervals; i++) {
			futureDaysList.add(getFutureDate(i));
		}
		return futureDaysList;
	}



	/**
	 * 获取未来 第 future 天的日期
	 *
	 * @param future
	 * @return
	 */
	public static String getFutureDate(int future) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
		Date today = calendar.getTime();
		return DateUtil.formatDate(today, DateUtil.DATE_PATTERN_LINE);
	}



	/**
	 * <p>方法名称:  </p>
	 * <p>方法描述:</p>
	 * <p>创建人:WuBo</p>
	 * <p>创建时间:2019-07-04 17:11 </p>
	 * <p>修改人: </p>
	 * <p>修改时间: </p>
	 * <p>修改备注:     </p>
	 *
	 * @param day        日期   2018-10-12
	 * @param hourMinute 小时:分钟   12:12
	 * @param minutes    增加的分钟数   25
	 * @return
	 */
	public static String addMinute(String day, String hourMinute, int minutes) {
		hourMinute = day + " " + hourMinute + ":00";
		Date date = parseDate(hourMinute, DATETIME24_PATTERN_LINE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		Date time = calendar.getTime();
		if (!formatDate(time, "yyyy-MM-dd").equals(day)) {
			return null;
		}
		return formatDate(time, DATETIME24_PATTERN_LINE);
	}



	/**
	 * 判断两个时间是否是同一天
	 *
	 * @param startTime 开始时间  09:12
	 * @param endTime   结束时间     08:12
	 * @return
	 */
	public static boolean isNotSameDay(String startTime, String endTime) {
		Date startDateTime = parseDate(addMinute("2018-02-12", startTime, 0), DATETIME24_PATTERN_LINE);
		Date endDateTime = parseDate(addMinute("2018-02-12", endTime, 0), DATETIME24_PATTERN_LINE);
		return startDateTime.getTime() > endDateTime.getTime();
	}



	public static Date timeToDateTime(String time) {
		String dateTimeStr = " 2019-09-07 " + time + ":00";
		return parseDate(dateTimeStr, DATETIME24_PATTERN_LINE);
	}



	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 *
	 * @param nowTime   当前时间
	 * @param startTime 开始时间
	 * @param endTime   结束时间
	 * @return
	 * @author WuBo
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}



	/**
	 * 计算出一个时间点距离这一天的开始时间多少分钟
	 *
	 * @param time
	 * @return
	 */
	public static long calculateTimeDistDayStartMinutes(String time) {
		Date now = new Date();
		Date startOfDay = getStartOfDay(now);
		String endDayStr = formatDate(now, DATE_PATTERN_LINE) + " " + time + ":00";
		Date end = parseDate(endDayStr, DATETIME24_PATTERN_LINE);
		long distSeconds = Duration.between(startOfDay.toInstant(), end.toInstant()).getSeconds();
		return distSeconds / 60;
	}



	/**
	 * 判断一个时间加一定的分钟数是否跨天
	 *
	 * @param time
	 * @param minutes
	 * @return
	 */
	public static boolean addTimeIsCrossDay(String time, int minutes) {
		Date now = new Date();
		String startTimeStr = formatDate(now, DATE_PATTERN_LINE) + " " + time + ":00";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(startTimeStr, DATETIME24_PATTERN_LINE));
		calendar.add(Calendar.MINUTE, minutes);
		Date endOfDay = getEndOfDay(now);
		return calendar.getTime().after(endOfDay);
	}



	public static void main(String[] args) {
		String s = DateUtil.formatDate(new Date(), "HH:mm");
		System.out.println(s);
	}

}
