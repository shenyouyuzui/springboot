package com.tvcp.util;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java8日期与时间操作工具类
 * 
 * @time：     2018年10月10日 下午10:53:10
 * @version：  V1.0.0
 */
public final class DateUtil {
	
	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	
	/**
	 * 中国时区
	 */
	private static final String CN_ZONE_ID = "Asia/Shanghai";
	
	private DateUtil() { }
	
	/** 
     * Date 转 LocalDateTime 
     * 
     * @param date 
     * @return LocalDateTime
     */  
    public static LocalDateTime dateToLocalDateTime(Date date) { 
    	if (date == null) {
    		return null;  
		}
    	long nanoOfSecond = (date.getTime() % 1000) * 1000000;  
    	return LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
    }  
    
    /** 
     * LocalDateTime 转  Date
     * 
     * @param localDateTime 
     * @return Date
     */  
    public static Date localDateTimeToDate(LocalDateTime localDateTime) { 
    	if (localDateTime == null) {
    		return null;  
    	}
    	return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }  
    
    /** 
     * Date 转 LocalDate 
     * 
     * @param date
     * @return LocalDate 
     */  
    public static LocalDate dateToLocalDate(Date date) {  
    	if (date == null) {
			return null;
		}
    	return dateToLocalDateTime(date).toLocalDate();  
    } 
    
    /** 
     * LocalDate 转 Date
     * 
     * @param localDate
     * @return Date 
     */  
    public static Date localDateToDate(LocalDate localDate) {  
    	if (localDate == null) {
    		return null;  
		}
    	return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    } 
    
    /** 
     * Timestamp 转 LocalDateTime 
     * 
     * @param date 
     * @return LocalDateTime 
     */  
    public static LocalDateTime timestampToLocalDateTime(Timestamp date) {  
    	if (date == null) {
    		return null;  
		}
    	return LocalDateTime.ofEpochSecond(date.getTime() / 1000, date.getNanos(), ZoneOffset.of("+8"));
    } 
    
    /** 
     * Timestamp 转 LocalDate
     * 
     * @param date 
     * @return LocalDate 
     */  
    public static LocalDate timestampToLocalDate(Timestamp date) { 
    	if (date == null) {
			return null;
		}
        return timestampToLocalDateTime(date).toLocalDate();  
    } 

	/** 
     * localDateTime转自定义格式string 
     * 
     * @param localDateTime LocalDateTime对象
     * @param format 指定转换的格式，如：yyyy-MM-dd hh:mm:ss 
     * @return 
     */  
    public static String localDateTimeToFormatStr(LocalDateTime localDateTime, String format) {  
        try {  
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);  
            return localDateTime.format(formatter);  
        } catch (DateTimeParseException e) {  
        	LOGGER.error("格式错误", e);
        }  
        return null;  
    }
    
    /** 
     * string 转 LocalDateTime 
     * 
     * @param dateStr 例："2017-08-11 01:00:00" 
     * @param format  例："yyyy-MM-dd HH:mm:ss" 
     * @return 
     */  
    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {  
        try {  
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);  
            return LocalDateTime.parse(dateStr, formatter);  
        } catch (DateTimeParseException e) {  
        	LOGGER.error("格式错误", e);  
        }  
        return null;  
    }
    
    /** 
     * 获取指定日期的天数 
     * 
     * @param date 指定的日期
     * @return Integer 天数
     */  
    public static Integer getDays(Date date) { 
    	if (date == null) {
    		return null;
		}
        return dateToLocalDateTime(date).getMonth().length(dateToLocalDate(date).isLeapYear());  
    } 
    
    /** 
     * 获取指定日期的数字格式的星期 
     * 
     * @param date 
     * @return int 数字格式的星期
     */  
    public static Integer getWeek(Date date) {
    	if (date == null) {
    		return null;
		}
        return dateToLocalDateTime(date).getDayOfWeek().getValue();  
    } 
    
    /** 
     * 获取指定日期的中文格式的星期 
     * 
     * @param date 
     * @return String 中文格式的星期，如：星期一 
     */  
    public static String getWeekStr(Date date) {
    	if (date == null) {
    		return null;
		}
    	String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    	int week = dateToLocalDateTime(date).getDayOfWeek().getValue();
    	return weekDays[week - 1];  
    } 
    
    /** 
     * 计算两个日期相差的天数,相同日期为0天
     * 
     * @param before 前一日期
     * @param after  后一日期
     * @return int 天数
     */  
    public static Integer getDiffDay(Date before, Date after) { 
    	if (before == null || after == null) {
			return null;
		}
    	Long days = dateToLocalDate(before).until(dateToLocalDate(after), ChronoUnit.DAYS);
        return days.intValue();  
    }  
    
    /** 
     * 计算两个日期相差的天数,相同日期算一天
     * 
     * @param before 前一日期
     * @param after  后一日期
     * @return int 天数
     */  
    public static Integer getDiffDays(Date before, Date after) { 
    	if (before == null || after == null) {
    		return null;
    	}
    	Date start = DateUtils.truncate(before, Calendar.DATE);
		Date end = DateUtils.truncate(after, Calendar.DATE);
		if (end.getTime() - start.getTime() == 0) {
			return 1;
		}
    	Long days = dateToLocalDate(start).until(dateToLocalDate(end), ChronoUnit.DAYS);
    	return days.intValue() + 1;  
    }  
  
    /** 
     * 计算两个日期相差的月数
     * 
     * @param before 前一日期
     * @param after  后一日期 
     * @return int 月数
     */  
    public static Integer getDiffMonths(Date before, Date after) { 
    	if (before == null || after == null) {
			return null;
		}
    	Long months = dateToLocalDate(before).until(dateToLocalDate(after), ChronoUnit.MONTHS);
        return months.intValue();  
    }  
  
    /** 
     * 计算两个日期相差的年数
     * 
     * @param before 前一日期
     * @param after 后一日期
     * @return int 年数
     */  
    public static Integer getDiffYears(Date before, Date after) {  
    	if (before == null || after == null) {
			return null;
		}
    	Long years = dateToLocalDate(before).until(dateToLocalDate(after), ChronoUnit.YEARS);
        return years.intValue();  
    }  
    
    /** 
     * 增加或减少年/月/周/天/小时/分/秒数 
     * 
     * @param date 指定的日期 
     * @param chronoUnit 年/月/周/天/小时/分/秒数，如：ChronoUnit.DAYS 指的是天
     * @param num 增加或减少的数，如：增加2 或 减少-2
     * @return Date 
     */  
    public static Date addOrSubDate(Date date, ChronoUnit chronoUnit, int num) {  
    	if (date == null) {
    		return null;
		}
    	LocalDateTime localDateTime = dateToLocalDateTime(date).plus(num, chronoUnit);
    	return localDateTimeToDate(localDateTime);  
    }
    
    /** 
     * 判断当前时间是否在指定的时间范围内(包含时分秒) 
     * 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return boolean
     */  
    public static Boolean isTimeInRange(Date startDate, Date endDate) { 
    	if (startDate == null || endDate == null) {
			return false;
		}
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(CN_ZONE_ID)));  
        LocalDateTime start = dateToLocalDateTime(startDate);  
        LocalDateTime end = dateToLocalDateTime(endDate);  
        return (start.isBefore(now) && end.isAfter(now)) || start.isEqual(now) || end.isEqual(now);  
    }
    
    /** 
     * 判断当前时间是否在指定的时间范围内(不含时分秒)
     * 
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return boolean
     */  
    public static Boolean isDateInRange(Date startDate, Date endDate) { 
    	if (startDate == null || endDate == null) {
    		return false;
    	}
    	Date now = DateUtils.truncate(new Date(), Calendar.DATE);
		Date start = DateUtils.truncate(startDate, Calendar.DATE);
		Date end = DateUtils.truncate(endDate, Calendar.DATE);  
    	return now.getTime() - start.getTime() >= 0 && end.getTime() - now.getTime() >= 0;  
    }
    
    /** 
     * 判断当前时间是否在指定的时间之前(包含时分秒) 
     * 
     * @param date 指定时间
     * @return boolean
     */  
    public static Boolean isTimeInBefore(Date date) { 
    	if (date == null) {
			return false;
		}
    	LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(CN_ZONE_ID)));  
    	LocalDateTime beforeDate = dateToLocalDateTime(date);  
    	return now.isBefore(beforeDate);  
    }
    
    /** 
     * 判断当前时间是否在指定的时间之前(不含时分秒) 
     * 
     * @param date 指定时间
     * @return boolean
     */  
    public static Boolean isDateInBefore(Date date) { 
    	if (date == null) {
    		return false;
    	}
    	Date now = DateUtils.truncate(new Date(), Calendar.DATE);
		Date beforeDate = DateUtils.truncate(date, Calendar.DATE);  
    	return beforeDate.getTime() - now.getTime() > 0;  
    }
    
    /** 
     * 判断当前时间是否在指定的时间之后(包含时分秒)
     * 
     * @param date 指定时间
     * @return boolean
     */  
    public static Boolean isTimeInAfter(Date date) {  
    	if (date == null) {
			return false;
		}
    	LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(CN_ZONE_ID)));  
    	LocalDateTime afterDate = dateToLocalDateTime(date);  
    	return now.isAfter(afterDate);  
    }
    
    /** 
     * 判断当前时间是否在指定的时间之后(不含时分秒)
     * 
     * @param date 指定时间
     * @return boolean
     */  
    public static Boolean isDateInAfter(Date date) {  
    	if (date == null) {
    		return false;
    	}
    	Date now = DateUtils.truncate(new Date(), Calendar.DATE);
		Date afterDate = DateUtils.truncate(date, Calendar.DATE);  
    	return now.getTime() - afterDate.getTime() > 0;  
    }

    /**
     * 获得指定日期最小时间 :<br/>
     * 如：传入的日期为：2018-10-10 10:10:10，处理后为：2018-10-10 00:00:00
     * 
     * @param date 指定日期
     * @return
     */
    public static Date getStartOfDay(Date date) {  
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());  
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);  
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());  
    } 
    
    /**
     * 获得指定日期最大时间 :<br/>
     * 如：传入的日期为：2018-10-10 10:10:10，处理后为：2018-10-10 23:59:59
     * 
     * @param date 指定日期
     * @return
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;  
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);  
        Date endDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        // 防止保存到mySql数据库自动增加1秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();  
    }
    
}