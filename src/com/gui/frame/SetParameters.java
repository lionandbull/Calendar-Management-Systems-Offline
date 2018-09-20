package com.gui.frame;

import java.util.Calendar;
	
public class SetParameters {
	// Starting date
	static int SD_month;
	static int SD_day;
	// Ending date
	static int ED_month;
	static int ED_day;
	// Earliest Hour
	static int EH_hour;
	static int EH_min;
	// Latest Hour
	static int LH_hour;
	static int LH_min;
	static String name;
	static String organizer;
	static String location;
	static int duration;
	
	// -------------- Set starting/ending dates and earliest/latest hours ------------------
	public static void setStartingDateMonth(String month) {
		SD_month = Integer.parseInt(month);
	}
	public static void setStartingDateDay(String day) {
		SD_day = Integer.parseInt(day);
	}
	public static void setEndingDateMonth(String month) {
		ED_month = Integer.parseInt(month);
	}
	public static void setEndingDateDay(String day) {
		ED_day = Integer.parseInt(day);
	}
	public static void setEarliestHourHour(String hour) {
		EH_hour = Integer.parseInt(hour);
	}
	public static void setEarliestHourMin(String min) {
		EH_min = Integer.parseInt(min);
	}
	public static void setLatestHourHour(String hour) {
		LH_hour = Integer.parseInt(hour);
	}
	public static void setLatestHourMin(String min) {
		LH_min = Integer.parseInt(min);
	}
	
	// -------------------------
	public static void setDuration(int dur) {
		duration = dur;
	}
	
	// -------------------------
	public static void setName(String name_) {
		name = name_;
	}
	
	// -------------------------
	public static void setOrganizer(String organizer_) {
		organizer = organizer_;
	}
	
	// -------------------------
	public static void setLocation(String location_) {
		location = location_;
	}
	
	public static String myString() {
		String s = Integer.toString(SD_month) + Integer.toString(SD_day) + Integer.toString(ED_month) + Integer.toString(ED_day) +
				Integer.toString(EH_hour) + Integer.toString(EH_min) + Integer.toString(LH_hour) + Integer.toString(LH_min) + Integer.toString(duration);
		s = s + name + location + organizer;
		return s;
	}
	
}
