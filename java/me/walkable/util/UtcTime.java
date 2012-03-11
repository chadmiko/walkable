/**
 * 
 */
package me.walkable.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Christopher Butera
 *
 */
public class UtcTime {

	public static Timestamp getCurrentTime(){
		Calendar c = Calendar.getInstance();
		TimeZone z = c.getTimeZone();
		int offset = z.getRawOffset();
		int offsetHrs = offset / 1000 / 60 / 60;
		int offsetMins = offset / 1000 / 60 % 60;
		c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
		c.add(Calendar.MINUTE, (-offsetMins));
		Timestamp currentTimestamp = new Timestamp(c.getTimeInMillis());
		return currentTimestamp;
	}

	public static String getFileTime(){
		String filenameFormat = "yyyy-MM-dd_HH-mm-ss";
		Timestamp time = getCurrentTime();
		SimpleDateFormat dFormat = new SimpleDateFormat(filenameFormat);
		String fileTime = dFormat.format(time);
		return fileTime;
	}
	
}
