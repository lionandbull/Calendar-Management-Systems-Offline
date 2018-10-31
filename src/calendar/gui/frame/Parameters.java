package calendar.gui.frame;

import java.util.Calendar;

import calendar.model.MyCalendar;
	
public class Parameters {
	
	// Starting date
	public static int SD_year;
	public static int SD_month;
	public static int SD_day;
	// Ending date
	public static int ED_year;
	public static int ED_month;
	public static int ED_day;
	// Earliest Hour
	public static int EH_hour;
	public static int EH_min;
	// Latest Hour
	public static int LH_hour;
	public static int LH_min;
	public static String name;
	public static String organizer;
	public static String location;
	public static int duration;
	
	public static void setFromMyCalendar(MyCalendar mc) {
		
	}
	// -------------- Set starting/ending dates and earliest/latest hours ------------------
	public static void setStartingDateYear(String year) {
		SD_year = Integer.parseInt(year);
	}
	public static void setStartingDateMonth(String month) {
		SD_month = Integer.parseInt(month);
	}
	public static void setStartingDateDay(String day) {
		SD_day = Integer.parseInt(day);
	}
	public static void setEndingDateYear(String year) {
		ED_year = Integer.parseInt(year);
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
		String s = Integer.toString(SD_year) + "/" + Integer.toString(SD_month) + "/" + Integer.toString(SD_day) + "-" + Integer.toString(ED_year)
		+ "/" +Integer.toString(ED_month) + "/" + Integer.toString(ED_day) + " " +
				Integer.toString(EH_hour) + Integer.toString(EH_min) + Integer.toString(LH_hour) + Integer.toString(LH_min) + Integer.toString(duration);
		s = s + name + location + organizer;
		return s;
	}
	
}
