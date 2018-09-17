package com.calendar;
import java.util.*;

public class MyUtility {
	
	public static Calendar calendarSetDate(int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, date);
		return calendar;
	}
	
	public static Calendar calendarSetTime(int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		return calendar;
	}
	
	public static String calendarToDate(Calendar calendar) {
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		String dateString = Integer.toString(month) + "/" + Integer.toString(date);
		return dateString;
	}
	
	public static String calendarToTime(Calendar calendar) {
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		String timeString;
		if (minute == 0){
			 timeString= Integer.toString(hour) + ":" + "00";
		}
		else {
			timeString = Integer.toString(hour) + ":" + Integer.toString(minute);
		}
		
		return timeString;
	}
	
	
}
