package com.gotech.tv.launcher.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeFormatUtil
{
	/**
	 * CN:将格式为"yyyy:MM:dd/HH:mm:ss"的字符串，转换为时间对象
	 * 
	 * @param Str
	 * @return
	 */
	public static Date parseDate(String Str)
	{
		// 2013:03:06/10:34:23
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy:MM:dd/HH:mm:ss", Locale.US);
		Date time = null;
		if (Str != null)
		{
			try
			{
				time = myFmt.parse(Str);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return time;
	}

	public static String formatTimeSting(Date time)
	{
		if (time != null)
		{
			String week = String.format("%ta", time);
			String month = String.format("%tb", time);
			String day = String.format("%te", time);
			String year = String.format("%tY", time);
			String min = String.format("%tR", time);
			String msg = week + ", " + month + " " + day + " " + year + ", " + min + "";
			return msg;
		}
		else
		{
			return null;
		}
	}

	/**
	 * CN:根据指定时区的时间计算另一个时区对应的时间
	 * 
	 * @param date
	 *            　CN:指定的时间
	 * @param oldZone
	 *            　CN:指定的时区
	 * @param newZone
	 *            　CN:需要计算的时区
	 * @return
	 */
	public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone)
	{
		Date dateTmp = null;
		if (date != null)
		{
			int timeOffset = oldZone.getOffset(date.getTime()) - newZone.getOffset(date.getTime());
			dateTmp = new Date(date.getTime() - timeOffset);
		}
		return dateTmp;
	}
}
