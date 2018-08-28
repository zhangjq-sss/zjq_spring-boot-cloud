package com.zjq.euraka_core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	protected static final Log logger = LogFactory.getLog(DateUtil.class);

	// 格式：年－月－日 小时：分钟：秒
	public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

	// 格式：年－月－日 小时：分钟
	public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

	// 格式：年月日 小时分钟秒
	public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

	// 格式：年－月－日
	public static final String LONG_DATE_FORMAT = "yyyy-MM-dd";

	// 格式：年月日
	public static final String EIGHT_STYLE_DATE_FORMAT = "yyyyMMdd";

	// 格式：月－日
	public static final String SHORT_DATE_FORMAT = "MM-dd";

	// 格式：小时：分钟：秒
	public static final String LONG_TIME_FORMAT = "HH:mm:ss";

	// 格式：小时:分钟
	public static final String SHORT_TIME_FORMAT = "HH:mm";

	// 格式：年-月
	public static final String MONTG_DATE_FORMAT = "yyyy-MM";

	// 格式：年
	public static final String YEAR_DATE_FORMAT = "yyyy";

	// 年的加减
	public static final int SUB_YEAR = Calendar.YEAR;

	// 月加减
	public static final int SUB_MONTH = Calendar.MONTH;

	// 天的加减
	public static final int SUB_DAY = Calendar.DATE;

	// 小时的加减
	public static final int SUB_HOUR = Calendar.HOUR;

	// 分钟的加减
	public static final int SUB_MINUTE = Calendar.MINUTE;

	// 秒的加减
	public static final int SUB_SECOND = Calendar.SECOND;

	static final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	@SuppressWarnings("unused")
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_ONE);

	public DateUtil() {
	}


    public static  Date  format (Date date){
	   SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateResult=null;
        try {
            String str=simpleDateFormat.format(date);
            dateResult=simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateResult;
    }


	/**
	 * 把符合日期格式的字符串转换为日期类型
	 */

	public static java.util.Date toDate(String dateStr) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			d = null;
		}
		return d;
	}

	public static java.util.Date toDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			d = null;
		}
		return d;
	}

	/**
	 * 把日期转换为字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatDateTime(java.util.Date date) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(FORMAT_ONE);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			// log.error(e);
		}
		return result;
	}

	public static String formatDateTime(java.util.Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			// log.error(e);
		}
		return result;
	}

	public static String formatDateTime(Long date, String format) {
		if (date == null || date == 0) {
			return "";
		}
		return formatDateTime(new Date(date), format);
	}

	public static String formatDateTime(Long date) {
		return formatDateTime(date, FORMAT_ONE);
	}

	/**
	 * 获取当前时间的指定格式
	 *
	 * @param format
	 * @return
	 */
	public static String getCurrDate(String format) {
		return formatDateTime(new Date(), format);
	}

	/**
	 *
	 * @param dateStr
	 * @param amount
	 * @return
	 */
	public static String dateSub(int dateKind, String dateStr, int amount) {
		Date date = toDate(dateStr, FORMAT_ONE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(dateKind, amount);
		return formatDateTime(calendar.getTime(), FORMAT_ONE);
	}

	/**
	 * 两个日期相减
	 *
	 * @param firstTime
	 * @param secTime
	 * @return 相减得到的秒数
	 */
	public static long timeSub(String firstTime, String secTime) {
		long first = toDate(firstTime, FORMAT_ONE).getTime();
		long second = toDate(secTime, FORMAT_ONE).getTime();
		return (second - first) / 1000;
	}

	/**
	 * 获得某月的天数
	 *
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @return int
	 */
	public static int getDaysOfMonth(String year, String month) {
		int days = 0;
		if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7") || month.equals("8")
				|| month.equals("10") || month.equals("12")) {
			days = 31;
		} else if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) {
			days = 30;
		} else {
			if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0)
					|| Integer.parseInt(year) % 400 == 0) {
				days = 29;
			} else {
				days = 28;
			}
		}

		return days;
	}

	/**
	 * 获取某年某月的天数
	 *
	 * @param year
	 *            int
	 * @param month
	 *            int 月份[1-12]
	 * @return int
	 */
	public static int getDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获得当前日期
	 *
	 * @return int
	 */
	public static int getToday() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 获得当前月份
	 *
	 * @return int
	 */
	public static int getToMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得当前年份
	 *
	 * @return int
	 */
	public static int getToYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回日期的天
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 返回日期的年
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回日期的月份，1-12
	 *
	 * @param date
	 *            Date
	 * @return int
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 计算两个日期相差的天数，如果date2 > date1 返回正数，否则返回负数
	 *
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return long
	 */
	public static long dayDiff(Date date1, Date date2) {
		return (date2.getTime() - date1.getTime()) / 86400000;
	}

	/**
	 * 比较两个日期的年差
	 *
	 * @param befor
	 * @param after
	 * @return
	 */
	public static int yearDiff(String before, String after) {
		Date beforeDay = toDate(before, LONG_DATE_FORMAT);
		Date afterDay = toDate(after, LONG_DATE_FORMAT);
		return getYear(afterDay) - getYear(beforeDay);
	}

	/**
	 * 比较指定日期与当前日期的差
	 *
	 * @param befor
	 * @param after
	 * @return
	 */
	public static int yearDiffCurr(String after) {
		Date beforeDay = new Date();
		Date afterDay = toDate(after, LONG_DATE_FORMAT);
		return getYear(beforeDay) - getYear(afterDay);
	}

	/**
	 * 比较指定日期与当前日期的差
	 *
	 * @param before
	 * @return
	 * @author chenyz
	 */
	public static long dayDiffCurr(String before) {
		Date currDate = DateUtil.toDate(currDay(), LONG_DATE_FORMAT);
		Date beforeDate = toDate(before, LONG_DATE_FORMAT);
		return (currDate.getTime() - beforeDate.getTime()) / 86400000;

	}

	/**
	 * 获取每月的第一周
	 *
	 * @param year
	 * @param month
	 * @return
	 * @author chenyz
	 */
	public static int getFirstWeekdayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
		c.set(year, month - 1, 1);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取每周的第一天
	 * 
	 * @param format
	 * @return
	 */
	public static String getFistDayOfWeek(String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return formatDateTime(cal.getTime(), format);
	}

	/**
	 * 获取每周的最后一天
	 * 
	 * @param format
	 * @return
	 */
	public static String getLastDayOfWeek(String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return formatDateTime(cal.getTime(), format);
	}

	/**
	 * 获取每月的最后一周
	 *
	 * @param year
	 * @param month
	 * @return
	 * @author chenyz
	 */
	public static int getLastWeekdayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SATURDAY); // 星期天为第一天
		c.set(year, month - 1, getDaysOfMonth(year, month));
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得当前日期字符串，格式"yyyy-MM-dd HH:mm:ss"
	 *
	 * @return
	 */
	public static String getNow() {
		Calendar today = Calendar.getInstance();
		return formatDateTime(today.getTime(), FORMAT_ONE);
	}

	/**
	 * 根据生日获取星座
	 *
	 * @param birth
	 *            YYYY-mm-dd
	 * @return
	 */
	public static String getAstro(String birth) {
		if (!isDate(birth)) {
			birth = "2000" + birth;
		}
		if (!isDate(birth)) {
			return "";
		}
		int month = Integer.parseInt(birth.substring(birth.indexOf("-") + 1, birth.lastIndexOf("-")));
		int day = Integer.parseInt(birth.substring(birth.lastIndexOf("-") + 1));
		String s = "魔羯水瓶双鱼牡羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
		int[] arr = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };
		int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
		return s.substring(start, start + 2) + "座";
	}

	/**
	 * 判断日期是否有效,包括闰年的情况
	 *
	 * @param date
	 *            YYYY-mm-dd
	 * @return
	 */
	public static boolean isDate(String date) {
		StringBuffer reg = new StringBuffer("^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
		reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
		reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
		reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
		reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
		reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
		reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
		Pattern p = Pattern.compile(reg.toString());
		return p.matcher(date).matches();
	}

	/**
	 * 取得指定日期过 months 月后的日期 (当 months 为负数表示指定月之前);
	 *
	 * @param date
	 *            日期 为null时表示当天
	 * @param month
	 *            相加(相减)的月数
	 */
	public static Date nextMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 取得指定日期过 day 天后的日期 (当 day 为负数表示指日期之前);
	 *
	 * @param date
	 *            日期 为null时表示当天
	 * @param day
	 *            相加(相减)的月数
	 */
	public static Date nextDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}

	/**
	 * 取得指定日期过second天后的日期 (当 second 为负数表示指日期之前);
	 *
	 * @param date
	 *            日期 为null时表示当天
	 * @param second
	 *            相加(相减)的秒数
	 */
	public static Date nextSecond(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 取得距离今天 day 日的日期
	 *
	 * @param day
	 * @param format
	 * @return
	 * @author chenyz
	 */
	public static String nextDay(int day, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, day);
		return formatDateTime(cal.getTime(), format);
	}

	/**
	 * 取得指定日期过 day 周后的日期 (当 day 为负数表示指定月之前)
	 *
	 * @param date
	 *            日期 为null时表示当天
	 */
	public static Date nextWeek(Date date, int week) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.WEEK_OF_MONTH, week);
		return cal.getTime();
	}

	/**
	 * 获取当前的日期(yyyy-MM-dd)
	 */
	public static String currDay() {
		return DateUtil.formatDateTime(new Date(), DateUtil.LONG_DATE_FORMAT);
	}

	/**
	 * 获取昨天的日期
	 *
	 * @return
	 */
	public static String befoDay() {
		return befoDay(DateUtil.LONG_DATE_FORMAT);
	}

	/**
	 * 根据时间类型获取昨天的日期
	 *
	 * @param format
	 * @return
	 * @author chenyz
	 */
	public static String befoDay(String format) {
		return DateUtil.formatDateTime(DateUtil.nextDay(new Date(), -1), format);
	}

	/**
	 * 获取明天的日期
	 */
	public static String afterDay() {
		return DateUtil.formatDateTime(DateUtil.nextDay(new Date(), 1), DateUtil.LONG_DATE_FORMAT);
	}

	/**
	 * 取得当前时间距离1900/1/1的天数
	 *
	 * @return
	 */
	public static int getDayNum() {
		int daynum = 0;
		GregorianCalendar gd = new GregorianCalendar();
		Date dt = gd.getTime();
		GregorianCalendar gd1 = new GregorianCalendar(1900, 1, 1);
		Date dt1 = gd1.getTime();
		daynum = (int) ((dt.getTime() - dt1.getTime()) / (24 * 60 * 60 * 1000));
		return daynum;
	}

	/**
	 * getDayNum的逆方法(用于处理Excel取出的日期格式数据等)
	 *
	 * @param day
	 * @return
	 */
	public static Date getDateByNum(int day) {
		GregorianCalendar gd = new GregorianCalendar(1900, 1, 1);
		Date date = gd.getTime();
		date = nextDay(date, day);
		return date;
	}

	/** 针对yyyy-MM-dd HH:mm:ss格式,显示yyyymmdd */
	public static String getYmdDateCN(String datestr) {
		if (datestr == null)
			return "";
		if (datestr.length() < 10)
			return "";
		StringBuffer buf = new StringBuffer();
		buf.append(datestr.substring(0, 4)).append(datestr.substring(5, 7)).append(datestr.substring(8, 10));
		return buf.toString();
	}

	/**
	 * 获取本月第一天
	 *
	 * @param format
	 * @return
	 */
	public static String getFirstDayOfMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		return formatDateTime(cal.getTime(), format);
	}

	/**
	 * 获取本月最后一天
	 *
	 * @param format
	 * @return
	 */
	public static String getLastDayOfMonth(String format) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return formatDateTime(cal.getTime(), format);
	}

	/**
	 * Returns a Date set to the first possible millisecond of the day, just after
	 * midnight. If a null day is passed in, a new Date is created. midnight (00m
	 * 00h 00s)
	 */
	public static Date getStartOfDay(Date day) {
		return getStartOfDay(day, Calendar.getInstance());
	}

	/**
	 * Returns a Date set to the first possible millisecond of the day, just after
	 * midnight. If a null day is passed in, a new Date is created. midnight (00m
	 * 00h 00s)
	 */
	public static Date getStartOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * Returns a Date set to the last possible millisecond of the day, just before
	 * midnight. If a null day is passed in, a new Date is created. midnight (00m
	 * 00h 00s)
	 */
	public static Date getEndOfDay(Date day) {
		return getEndOfDay(day, Calendar.getInstance());
	}

	public static Date getEndOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * 判断2个时间相差多少天<br>
	 * <br>
	 *
	 * @param pBeginTime
	 *            请假开始时间<br>
	 * @param pEndTime
	 *            请假结束时间<br>
	 * @return String 计算结果<br>
	 * @throws ParseException
	 * @Exception 发生异常<br>
	 */
	public static Long timeDiffForDay(Date pBeginTime, Date pEndTime) throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long day = (endL - beginL) / 86400000;
		return day;
	}

	/**
	 * 判断2个时间相差多少小时<br>
	 * <br>
	 *
	 * @param pBeginTime
	 *            请假开始时间<br>
	 * @param pEndTime
	 *            请假结束时间<br>
	 * @return String 计算结果<br>
	 * @throws ParseException
	 * @Exception 发生异常<br>
	 */
	public static Long timeDiffForHour(Date pBeginTime, Date pEndTime) throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long hour = ((endL - beginL) % 86400000) / 3600000;
		return hour;
	}

	/**
	 * 判断2个时间相差多少分<br>
	 * <br>
	 *
	 * @param pBeginTime
	 *            请假开始时间<br>
	 * @param pEndTime
	 *            请假结束时间<br>
	 * @return String 计算结果<br>
	 * @throws ParseException
	 * @Exception 发生异常<br>
	 */
	public static Long timeDiffForMin(Date pBeginTime, Date pEndTime) throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long min = ((endL - beginL) % 86400000 % 3600000) / 60000;
		return min;
	}

	/**
	 * 判断2个时间相差多少秒<br>
	 * <br>
	 *
	 * @param pBeginTime
	 *            开始时间<br>
	 * @param pEndTime
	 *            结束时间<br>
	 * @return String 计算结果<br>
	 * @throws ParseException
	 * @Exception 发生异常<br>
	 */
	public static Long timeDiffForSec(Date pBeginTime, Date pEndTime) throws ParseException {
		Long beginL = pBeginTime.getTime();
		Long endL = pEndTime.getTime();
		Long sec = (endL - beginL) / 1000;
		return sec;
	}

	/**
	 * 返回指定时间与当前时间差多少天(小时, 分钟, 刚才)
	 * 
	 * @param time
	 *            要比较的时间
	 * @return 多少天(小时, 分钟, 刚才)
	 */
	public static String getTime(Date time) {
		String result = null;
		Long temp = null;
		final Date currentDate = new Date();

		do {
			try {
				// 看差多少天
				temp = timeDiffForDay(time, currentDate);
				if (temp > 0l) {
					result = temp + "天前";
					break;
				}

				// 看差多少时
				temp = timeDiffForHour(time, currentDate);
				if (temp > 0l) {
					result = temp + "小时前";
					break;
				}

				// 看差多少分
				temp = timeDiffForMin(time, currentDate);
				if (temp > 0l) {
					result = temp + "分种前";
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} while (false);

		return result == null ? "刚才" : result;
	}

	/**
	 *
	 * @Title: formatLongToTimeStr
	 * @Description: 根据通话总秒数换算为hh:mm:ss格式
	 * @param seconds
	 *            通话时长
	 * @return
	 */
	public static String formatLongToTimeStr(Long seconds) {
		long hour = 0;
		long minute = 0;
		long second = 0;

		second = seconds.longValue() / 1000;

		if (second > 59) {
			minute = second / 60;
			second = second % 60;
		}
		if (minute > 59) {
			hour = minute / 60;
			minute = minute % 60;
		}
		return (String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":"
				+ String.format("%02d", second));
	}

	/**
	 *
	 * @Title: getBirthdayByAge
	 * @Description:根据年龄计算出生年
	 * @param age
	 * @return
	 */
	public static Date getBirthdayByAge(int age) {
		Date birthday = null;
		SimpleDateFormat yearFormat = new SimpleDateFormat(YEAR_DATE_FORMAT);
		SimpleDateFormat birthdayFormat = new SimpleDateFormat(LONG_DATE_FORMAT);
		String currentYear = yearFormat.format(new Date());
		int year = Integer.valueOf(currentYear) - age;

		try {
			birthday = birthdayFormat.parse(year + "-1-1");
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return birthday;
	}

	/**
	 *
	 * @Title: getCountDown
	 * @Description:获取倒计时
	 * @param addTime
	 *            获得时间（派工时，客户与业务员关系确立的时间）
	 * @return
	 */
	public static String getCountDown(long addTime) {
		Date nextDay = nextDay(new Date(addTime), 1);
		String countDownTime = formatDateTime(nextDay);
		// 获取当前时间
		String currDateTime = getCurrDate(FORMAT_ONE);
		// 倒计时时间-当前时间=剩余时间
		long seconds = timeSub(currDateTime, countDownTime) * 1000;
		if (seconds < 0) {
			return "已过时";
		}
		return formatLongToTimeStr(seconds);
	}

	/**
	 *
	 * @Title: getCountDown
	 * @Description:获取时间差
	 * @param addTime
	 *            获得时间差（派工时，客户与业务员关系确立的时间）
	 * @return
	 */
	public static Long getCountDownTime(long addTime) {
		Date nextDay = nextDay(new Date(addTime), 1);
		String countDownTime = formatDateTime(nextDay);
		// 获取当前时间
		String currDateTime = getCurrDate(FORMAT_ONE);
		// 倒计时时间-当前时间=剩余时间
		long seconds = timeSub(currDateTime, countDownTime) * 1000;
		if (seconds < 0) {
			return 0L;
		}
		return seconds;
	}

	/**
	 *
	 * @Title: getCountDownEscHoliday
	 * @Description:获取倒计时
	 * @param addTime
	 *            获得时间（派工时，客户与业务员关系确立的时间）
	 * @param holidays
	 *            假期的列表
	 * @param step
	 *            增加或者减小的秒数
	 * @return
	 */
	public static String getCountDownEscHoliday(Date addTime, List<String> holidays, int step) {
		Date nextDay = getNextDay(addTime, holidays, step);
		String countDownTime = formatDateTime(nextDay);
		// 获取当前时间
		String currDateTime = getCurrDate(FORMAT_ONE);
		// 倒计时时间-当前时间=剩余时间
		long seconds = timeSub(currDateTime, countDownTime) * 1000;
		if (seconds < 0) {
			return "已过时";
		}
		return formatLongToTimeStr(seconds);
	}

	/**
	 *
	 * @Title: getCountDownTimeEscHoliday
	 * @Description:获取时间差
	 * @param addTime
	 *            获得时间差（派工时，客户与业务员关系确立的时间）
	 * @param holidays
	 *            假期的列表
	 * @param step
	 *            增加或者减小的秒数
	 * @return
	 */
	public static Long getCountDownTimeEscHoliday(Date addTime, List<String> holidays, int step) {
		Date nextDay = getNextDay(addTime, holidays, step);
		String countDownTime = formatDateTime(nextDay);
		// 获取当前时间
		String currDateTime = getCurrDate(FORMAT_ONE);
		// 倒计时时间-当前时间=剩余时间
		long seconds = timeSub(currDateTime, countDownTime) * 1000;
		if (seconds < 0) {
			return 0L;
		}
		return seconds;
	}

	/**
	 * 跳过节假日获得有效的日期
	 * 
	 * @param addTime
	 * @param holidays
	 * @param step
	 * @return
	 */
	private static Date getNextDay(Date addTime, List<String> holidays, int step) {
		if (addTime != null) {
			int addNum = 0;
			Date compareTime = new Date(addTime.getTime());
			if (holidays == null || holidays.size() == 0) {
				addNum = 1;
			} else {
				String nextTimeString = "";
				do {
					addNum++;
					Date nextTime = nextSecond(compareTime, addNum * step);
					nextTimeString = formatDateTime(nextTime, LONG_DATE_FORMAT);
				} while (holidays.contains(nextTimeString));
				// 假期的数据
				nextTimeString = formatDateTime(compareTime, LONG_DATE_FORMAT);
				if (holidays.contains(nextTimeString)) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(compareTime);
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					compareTime = cal.getTime();
					addNum++;
				}
			}
			return nextSecond(compareTime, addNum * step);
		} else {
			return nextSecond(addTime, 1 * step);
		}
	}

	/**
	 *
	 * @Title: getRemainingTime
	 * @Description: 获取剩余时间
	 * @param planTime
	 *            计划时间
	 * @return
	 */
	public static Long getRemainingTime(Date planTime) {
		String countPlanTime = formatDateTime(planTime);
		// 获取当前时间
		String currDateTime = getCurrDate(FORMAT_ONE);
		// 倒计时时间-当前时间=剩余时间
		long seconds = timeSub(currDateTime, countPlanTime) * 1000;
		return seconds;
	}

	/**
	 *
	 * @Title: getAddTime
	 * @Description: 在某个时间上加N小时
	 * @param currentDate
	 * @param hour
	 * @return
	 */
	public static Date getAddTime(Date dateTime, Integer hour) {
		if (null != hour) {
			return DateUtils.addHours(dateTime, hour);
		}
		return null;
	}

	/**
	 *
	 * @Title: getMinutes
	 * @Description: 间隔n分的分钟数组
	 * @param n
	 * @return
	 */
	public static int[] getMinutes(Integer n) {
		if (n == null || n == 0) {
			n = 5;
		}
		// 如果60除以n的模不等于0，就默认n=5
		if (60 % n != 0) {
			n = 5;
		}
		int m = 60 / n;
		int beginNum = 0;
		int[] minutes = new int[m];
		minutes[0] = beginNum;
		for (int i = 1; i < m; i++) {
			beginNum += n;
			minutes[i] = beginNum;
		}
		return minutes;
	}

	/**
	 *
	 * @Title: getChineseDayOfWeek
	 * @Description: 根据日期获取中式周几
	 * @param date
	 * @return
	 */
	public static int getChineseDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case java.util.Calendar.MONDAY:
			return 1;
		case java.util.Calendar.TUESDAY:
			return 2;
		case java.util.Calendar.WEDNESDAY:
			return 3;
		case java.util.Calendar.THURSDAY:
			return 4;
		case java.util.Calendar.FRIDAY:
			return 5;
		case java.util.Calendar.SATURDAY:
			return 6;
		case java.util.Calendar.SUNDAY:
			return 7;
		}
		return -1;
	}

	/**
	 *
	 * @Title: getTime2
	 * @Description: 格式化时间显示
	 * @param time
	 * @return
	 */
	/*
	 * public static String formatDate(Date time) { String result = null;
	 * if(time==null){ return result; } final Date currentDate = new Date(); final
	 * Date yesterday = DateUtil.nextDay(currentDate, -1);
	 * 
	 * //当前时间年月日 String currentYMD = formatDateTime(currentDate,
	 * EIGHT_STYLE_DATE_FORMAT); //昨天时间年月日 String yesterdayYMD =
	 * formatDateTime(yesterday, EIGHT_STYLE_DATE_FORMAT); //传入时间年月日 String timerYMD
	 * = formatDateTime(time, EIGHT_STYLE_DATE_FORMAT); //当前时间年份 String currentYear
	 * = formatDateTime(currentDate, YEAR_DATE_FORMAT); //传入时间年份 String timerYear =
	 * formatDateTime(time, YEAR_DATE_FORMAT); do { //当天的时间显示格式为：时，分 if
	 * (timerYMD.equals(currentYMD)) { result = formatDateTime(time,
	 * SHORT_TIME_FORMAT); break; }
	 * 
	 * //昨天的时间显示格式为：昨天时，分 if (timerYMD.equals(yesterdayYMD)) { result = "昨天" +
	 * formatDateTime(time, SHORT_TIME_FORMAT); break; }
	 * 
	 * //昨天之前但是今年之内的时间显示格式为：月，日，时，分 if (timerYear.equals(currentYear) &&
	 * Integer.valueOf(timerYMD) < Integer.valueOf(yesterdayYMD)) { result =
	 * formatDateTime(time, FORMAT_TWO).substring(5); break; }
	 * //今年之前的时间显示格式为：年，月，日，时，分 if (Integer.valueOf(timerYear) <
	 * Integer.valueOf(currentYear)) { result = formatDateTime(time, FORMAT_TWO);
	 * break; } } while (false);
	 * 
	 * return result == null ? formatDateTime(time, FORMAT_TWO) : result; }
	 */

	/**
	 *
	 * @Title: getTime3
	 * @Description: 格式化时间显示
	 * @param time
	 * @return
	 */
	public static String formatDate(Date time) {
		String result = null;
		if (time == null) {
			return result;
		}
		final Date currentDate = new Date();

		// 当前时间年月日
		String currentYMD = formatDateTime(currentDate, EIGHT_STYLE_DATE_FORMAT);
		// 传入时间年月日
		String timerYMD = formatDateTime(time, EIGHT_STYLE_DATE_FORMAT);

		do {
			// 当天的时间显示格式为：时，分，秒
			if (timerYMD.equals(currentYMD)) {
				result = formatDateTime(time, LONG_TIME_FORMAT);
				break;
			} else {
				// 否则显示年，月，日
				result = formatDateTime(time, LONG_DATE_FORMAT);
			}
		} while (false);

		return result == null ? formatDateTime(time, FORMAT_TWO) : result;
	}

	/**
	 * 获取当前日期前一个月
	 * 
	 * @return
	 */
	public static String getBefoMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1); // 得到前一个月
		String start = format.format(c.getTime());
		return start;
	}

	/**
	 * 获取当前日期距离今天的天数
	 * 
	 * @return
	 */
	public static Long longDiffForDay(Long time) {
		try {
			if (time == null || time < 1) {
				return 0L;
			}
			String nowStr = getCurrDate(FORMAT_ONE);
			Date nowDay = toDate(nowStr, FORMAT_ONE);
			String resStr = formatDateTime(new Date(time), FORMAT_ONE);
			Date resDay = DateUtil.toDate(resStr, FORMAT_ONE);
			return DateUtil.timeDiffForDay(resDay, nowDay);
		} catch (Exception e) {
			return 0L;
		}
	}


    /**
     *
     * @param date
     * @param dateNum       数值
     * @param dateType      1:分钟  2:小时  3:天  4:月  5:年
     * @return
     */
	public static Date  getExpireDate(Date date ,int dateNum,int dateType){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        if(dateType!=0){
            if(1==dateType){
                calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+dateNum);
            }else if(2==dateType){
                calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+dateNum);
            }else if(3==dateType){
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)+dateNum);
            }else if(4==dateType){
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+dateNum);
            }else if(5==dateType){
                calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+dateNum);
            }
        }
        return calendar.getTime();
    }

}