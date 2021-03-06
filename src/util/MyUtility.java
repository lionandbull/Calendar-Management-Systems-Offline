package util;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JLabel;

import calendar.gui.frame.CMABoard;
import calendar.gui.frame.MainFrame;
import calendar.gui.frame.Parameters;
import calendar.model.AvailableTime;
import calendar.model.Meeting;
import calendar.model.MyCalendar;

public class MyUtility {
	public static boolean monthlyScheduledflag = true;
	public static boolean dailylyScheduledflag = true;
	
	public static Calendar calendarSetDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, date);
		return calendar;
	}
	
	public static Calendar calendarSetDate(String s) {
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(s.substring(0, 4));
		int month;
		int j = 5;
		System.out.println(s);
		while(!s.substring(j, j + 1).equals("/")) {
			j++;
		}
		month = Integer.parseInt(s.substring(5, j));
		int date = Integer.parseInt(s.substring(j+1, s.length()));
		calendar.set(Calendar.YEAR, year);
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
	
	public static Calendar calendarSetTime(String s) {
		Calendar calendar = Calendar.getInstance();
		int hour;
		int i = 0;
		while (!s.substring(i, i + 1).equals(":")) {
			i ++;
		}
		hour = Integer.parseInt(s.substring(0, i));
		int minute = Integer.parseInt(s.substring(i+1, s.length()));
		
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		return calendar;
	}
	
	public static String calendarToDate(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		String dateString = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(date);
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
	
	public static String CalendarRange(String name,String start, String end) {
		String s = "\"" + name + "\"" + " calendar : range from ";
		s = s + start + " to ";
		s = s + end;
		return s;
	}
	
	public static String GetFirstTime(String s) {
		int j = 0;
		for (int i = 0; i < s.length(); i++) {
			j ++;
			if (s.charAt(i) == '-') {
				break;
			}
		}
		return s.substring(0, j - 1);
	}
	
	public static ArrayList<Meeting> showMonthlySchedule(MyCalendar calendar, String yearMonth) {
		ArrayList<Meeting> returnList = new ArrayList<>();
		int size = calendar.meetings.size();
		for (int j = 0; j < size; j++) {
			if (calendar.meetings.get(j).checkMonth().equals(yearMonth)) {
				monthlyScheduledflag = false;
				returnList.add(calendar.meetings.get(j));
			}
		}
		return returnList;
	}
	
	public static ArrayList<Meeting> showDailySchedule(MyCalendar calendar, String date) {
		ArrayList<Meeting> returnList = new ArrayList<>();
		
//		for (AvailableTime value : calendar.availableDates.get(date).timeSlot.values()) {
//			if (value.reserved == true) {
//				returnList.add(value.time);
//			}
//		}
		int size = calendar.meetings.size();
		for (int j = 0; j < size; j++) {
			
			if (calendar.meetings.get(j).meetDate.equals(date)) {
				dailylyScheduledflag = false;
				returnList.add(calendar.meetings.get(j));
			}
		}
		return returnList;
	}
	
	public static boolean isValidDate(String s){
	    try {
	    	// 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd其中MM为大写
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    	// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2004/02/29会被接受，并转换成2004/03/01
	        dateFormat.setLenient(false);
	        dateFormat.parse(s);
	        if (s.length() > 10) {
	        	return false;
	        }
	        return true;
	     }catch (Exception e) {
	        // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
	        return false;
	    }
	}

	public static ArrayList<Integer> DateStringToInts(String s){
		ArrayList<Integer> returnList = new ArrayList<>();
		int year = Integer.parseInt(s.substring(0, 4));
		int month;
		int day;
		int j = 4;
		int k = s.length();
		for (int i = 5; i < s.length(); i++) {
			j ++;
			if (s.charAt(i) == '/') {
				break;
			}
		}
		month = Integer.parseInt(s.substring(5, j));
		for (int i = s.length() - 1; i > 0; i--) {
			k --;
			if (s.charAt(i) == '/') {
				break;
			}
		}
		day = Integer.parseInt(s.substring(k + 1, s.length()));
		returnList.add(year);
		returnList.add(month);
		returnList.add(day);
		return returnList;
	}
	
	public static void UpdateDate() {
		int SD_year = MyUtility.DateStringToInts(MyUtility.calendarToDate(CMABoard.myCalendar.startingDate)).get(0);
		int SD_month = MyUtility.DateStringToInts(MyUtility.calendarToDate(CMABoard.myCalendar.startingDate)).get(1);
		int ED_year = MyUtility.DateStringToInts(MyUtility.calendarToDate(CMABoard.myCalendar.endingDate)).get(0);
		int ED_month = MyUtility.DateStringToInts(MyUtility.calendarToDate(CMABoard.myCalendar.endingDate)).get(1);
		int SD_day = MyUtility.DateStringToInts(MyUtility.calendarToDate(CMABoard.myCalendar.startingDate)).get(2);
		int ED_day = MyUtility.DateStringToInts(MyUtility.calendarToDate(CMABoard.myCalendar.endingDate)).get(2);
		Parameters.SD_year = SD_year;
		Parameters.SD_month = SD_month;
		Parameters.SD_day = SD_day;
		Parameters.ED_year = ED_year;
		Parameters.ED_month = ED_month;
		Parameters.ED_day = ED_day;
		Parameters.name = CMABoard.myCalendar.getName();
		Parameters.location = CMABoard.myCalendar.getLocation();
		Parameters.duration = Integer.parseInt(CMABoard.myCalendar.getDuration());
		
		MainFrame.panel.YearBox.removeActionListener(MainFrame.panel.yearAct);
		MainFrame.panel.setCalendarPanel(SD_year, ED_year, SD_month, ED_month, SD_day, ED_day);
	}
}
