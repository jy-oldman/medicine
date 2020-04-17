package com.jy.medicine.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具类
 * 
 * @author Administrator
 *
 */
public class DateFormat {

	/**
	 * 格式化日期为"yyyy-MM-dd HH:mm:ss"格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateFormat1(Date date) {
		String str = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		Date date2 = new Date();
		try {
			date2 = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;
	}

	/**
	 * 格式化日期为"yyyy-MM-dd"格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateFormat2(Date date) {
		String str = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(str);
		Date date2 = new Date();
		try {
			date2 = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2;
	}
}
