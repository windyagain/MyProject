package com.me.account.common.utils;

import java.text.*;
import java.util.*;

/**
 * 时间操作/格式化
 * 
 * @author zhangzz
 * 
 */
public class DateFormatUtil {
	/**
	 * 得到当前的日期字符串，方便存放文件夹
	 * 
	 * @param date
	 *            日期对象
	 * @return 返回日期字符串
	 */
	public static final String getDateString(Date date) {
		SimpleDateFormat formattxt = new SimpleDateFormat("yyyy-MM-dd");
		if (date != null)
			return formattxt.format(date);
		return null;
	}

	/**
	 * 得到日期/时间字符串
	 * 
	 * @param date
	 *            日期对象
	 * @param formatString
	 *            格式化字符串 例："yyyy-MM-dd HH:mm:ss"
	 * @return 返回日期/时间字符串
	 */
	public static final String getDateTimeString(Date date, String formatString) {
		SimpleDateFormat formattxt = new SimpleDateFormat(formatString);
		return formattxt.format(date);
	}

	/**
	 * 得到当前日期/时间字符串
	 * 
	 * @return 返回日期/时间字符串
	 */
	public static final String getNowDateTimeString() {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formattxt.format(date);
	}
	

	/**
	 * 得到当前日期字符串
	 * 
	 * @return 返回当前日期字符串
	 */
	public static final String getNowDateString() {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat("yyyy-MM-dd");
		return formattxt.format(date);
	}

	/**
	 * 得到日期对象
	 * 
	 * @param dateTimeString
	 *            日期字符串
	 * @param formatString)
	 *            格式化字符串，如：yyyy-MM-dd HH:mm:ss
	 * @return 返回日期对象
	 */
	public static final Date getDate(String dateTimeString, String formatString) {
		SimpleDateFormat formattxt = new SimpleDateFormat(formatString);
		Date date = null;
		try {
			date = formattxt.parse(dateTimeString);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			// date = new Date();
		}
		return date;
	}
	/**
	 * 格式化日期得到新的Date对象
	 * @param date 日期字对象
	 * @param formatString 格式化字符串，如：yyyy-MM-dd HH:mm:ss
	 * @return 返回格式化后的新Date
	 */
	public static final Date formateDate(Date date, String formatString){
		
		SimpleDateFormat formattxt = new SimpleDateFormat(formatString);
		try {
			if (date != null && !"".equals(date)){
				date = formattxt.parse(formattxt.format(date));
			}
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}
		return date;
	}

	/**
	 * 得到上一年的日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @return 上一年的日期字符串
	 */
	public static final String getLastYearString(Date date) {
		Calendar lastYear = Calendar.getInstance();
		lastYear.setTime(date);
		lastYear.add(Calendar.YEAR, -1);
		return DateFormatUtil.getDateString(lastYear.getTime());
	}

	/**
	 * 得到上一月的日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @return 上一月的日期字符串
	 */
	public static final String getLastMonthString(Date date) {
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.setTime(date);
		lastMonth.add(Calendar.MONTH, -1);
		return DateFormatUtil.getDateString(lastMonth.getTime());
	}

	/**
	 * 得到上一周的日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @return 上一月周日期字符串
	 */
	public static final String getLastWeekString(Date date) {
		Calendar lastWeek = Calendar.getInstance();
		lastWeek.setTime(date);
		lastWeek.add(Calendar.HOUR_OF_DAY, -168);
		return DateFormatUtil.getDateString(lastWeek.getTime());
	}
	/**
	 * 取下一周
	 * @param date 
	 * @return 
	 */
	public static final String getNextWeekString(Date date) {
		Calendar nextWeek = Calendar.getInstance();
		nextWeek.setTime(date);
		nextWeek.add(Calendar.HOUR_OF_DAY, +168);
		return DateFormatUtil.getDateString(nextWeek.getTime());
	}
/**
 * 当前下一天
 * @param date
 * @return
 */
	public static final Date getNextDate(Date date) {
		Calendar nextDate = Calendar.getInstance();
		nextDate.setTime(date);
		nextDate.add(Calendar.HOUR_OF_DAY, +24);
		SimpleDateFormat formattxt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = formattxt.parse(formattxt.format(nextDate.getTime()));
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return date;
	}
	/**
	 * 得到昨天的日期字符串
	 * 
	 * @param date
	 *            日期对象
	 * @return 昨天的日期字符串
	 */
	public static final String getYesterdayString(Date date) {
		Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(date);
		yesterday.add(Calendar.HOUR_OF_DAY, -24);
		return DateFormatUtil.getDateString(yesterday.getTime());
	}

	/**
	 * 得到当前日期字符串，没有连接线的
	 * 
	 * @return 返回当前日期字符串
	 */
	public static final String getNoLineNowDateString() {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat("yyyyMMdd");
		return formattxt.format(date);
	}

	/**
	 * 取得当时的时间戳。年（四位）_月（两位）_日（两位）_时（两位）_分（两位）_秒（两位）_毫秒（三位）
	 * 
	 * @return 当时的时间戳
	 */
	public static final String getNowTimeStamp() {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat(
				"yyyy_MM_dd_HH_mm_ss_SSS");
		return formattxt.format(date);

	}

	/**
	 * 本周星期一
	 * 
	 * @param calendar
	 *            Calendar
	 * @return String yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}
	//星期二
	public static String getTuesdayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 2);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}
	//星期三
	public static String getWednesdayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 3);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}
	//星期四
	public static String getThursdayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 4);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}
	//星期五
	public static String getFridayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 5);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}
	//星期六
	public static String getSaturdayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 6);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}
	/**
	 * 本周星期天
	 * 
	 * @param calendar
	 *            Calendar
	 * @return String yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek(Calendar calendar) {
		int dayofweek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0)
			dayofweek = 7;
		calendar.add(Calendar.DATE, -dayofweek + 7);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		return fmt.format(calendar.getTime());
	}

	/**
	 * 根据日期求星期几
	 * 
	 * @param date
	 *            日期
	 * @return String 星期
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	/**
	 * 比较两个日期相差天数（不包括时分秒）
	 * @param fDate
	 * @param oDate
	 * @return 
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

       Calendar aCalendar = Calendar.getInstance();

       aCalendar.setTime(fDate);

       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

       aCalendar.setTime(oDate);

       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

       return day2 - day1;

    }
	
	/**
	 * 计算年龄
	 * @param fDate
	 * @param oDate
	 * @return 
	 */
	public static int ageOfBirthday(String birthday) {
		Date birDate = DateFormatUtil.getDate(birthday, "yyyy-MM-dd");
        Calendar aCalendar = Calendar.getInstance();
        
        int curYear = aCalendar.get(Calendar.YEAR);
        
        aCalendar.setTime(birDate);

        int birYear = aCalendar.get(Calendar.YEAR);
 
        return curYear - birYear+1;

    }
	
	/**
	 * 转换毫秒数成“分、秒”，如“01:53”。若超过60分钟则显示“时、分、秒”，如“01:01:30
	 * 
	 * @param 待转换的毫秒数
	 * */
	public static String converLongTimeToStr(long time) {
		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;

		long hour = (time) / hh;
		long minute = (time - hour * hh) / mi;
		long second = (time - hour * hh - minute * mi) / ss;

		String strHour = hour < 10 ? "0" + hour : "" + hour;
		String strMinute = minute < 10 ? "0" + minute : "" + minute;
		String strSecond = second < 10 ? "0" + second : "" + second;
		if (hour > 0) {
			return strHour + ":" + strMinute + ":" + strSecond;
		} else {
			return strMinute + ":" + strSecond;
		}
	}
	
	/**
	 * 转换毫秒数成日期
	 * 
	 * @param 待转换的毫秒数
	 * */
	public static String converLongTimeToStrDate(long time){
        Date dat=new Date(time);  
        GregorianCalendar gc = new GregorianCalendar();   
        gc.setTime(dat);  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sb=format.format(gc.getTime());  
        return sb;
	}
	
	/**
	 * 获取某个时间延迟多少分钟后的时间
	 * 
	 * @param 分钟
	 * */
	public static Date getAfterMinTime(Date tempDate,long min){
		long tempDatehm = tempDate.getTime();
		long afterhm = tempDatehm + min*60*1000;
		String  str = DateFormatUtil.converLongTimeToStrDate(afterhm);
		Date afterTime = DateFormatUtil.getDate(str, "yyyy-MM-dd HH:mm:ss");
		return afterTime;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 System.out.println("====="+DateFormatUtil.getDate("2013-4-2", "yyyy-MM-dd"));  
	}


}
