package calendar.model;
import java.util.*;

public class Methods {
	
	public static void scheduleMeeting(MyCalendar calendar, Person person, String date, String time) {
		
		if (calendar.availableDates.get(date).available == true &&
			calendar.availableDates.get(date).timeSlot.get(time).available == true &&
			calendar.availableDates.get(date).timeSlot.get(time).reserved == false){
			Meeting meeting = new Meeting(person, date, time, calendar.location);
//			calendar.availableDates.get(date).reserved = true;
			calendar.availableDates.get(date).timeSlot.get(time).reserved = true;
			calendar.addMeeting(meeting);
			calendar.meetingsString.add(meeting.meetDate);
			
		}
//		else if (calendar.availableDates.get(date) == null) {
//			throw new Error("This date is out of the range!");
//		}
//		else if (calendar.availableDates.get(date).available == false) {
//			return "Date not available";
////			throw new Error("This date is not available!");
//		}
//		else if (calendar.availableDates.get(date).available == true &&
//				calendar.availableDates.get(date).timeSlot.get(time).available == true &&
//				calendar.availableDates.get(date).timeSlot.get(time).reserved == true) {
//			return "Reserved";
////			throw new Error("This time has been reserved!");
//		}
//		
//		else if (calendar.availableDates.get(date).available == true &&
//				calendar.availableDates.get(date).timeSlot.get(time).available == false) {
//			return "Time not available";
////			throw new Error("This time is not available!");
//		}
//		return null;	
	}
	
	public static void cancelMeeting(MyCalendar calendar ,Meeting meeting, Person person, String date, String time) {
		meeting = null;
		calendar.availableDates.get(date).timeSlot.get(time).reserved = false;
	}
	
	public static ArrayList<String> showDailySchedule(MyCalendar calendar, String date) {
		if (calendar.availableDates.get(date).reserved == false) {
			System.out.println("No meeting at this date!");
			return null;
		}
		else {
			ArrayList<String> arrayList = new ArrayList<>();
			for (AvailableTime value : calendar.availableDates.get(date).timeSlot.values()) {
				if (value.reserved == true) {
					arrayList.add(value.time);
				}
			}
			int size = calendar.meetings.size();
			for (int j = 0; j < size; j++) {
				if (calendar.meetings.get(j).meetDate == date) {
					System.out.println(calendar.meetings.get(j).toString());
				}
			}
			return arrayList;
		}
	}
	
//	public static void showMonthlySchedule(MyCalendar calendar, int month) {
//		Boolean flag = true;
//		int size = calendar.meetings.size();
//		for (int j = 0; j < size; j++) {
//			if (calendar.meetings.get(j).checkMonth() == month) {
//				flag = false;
//				System.out.println(calendar.meetings.get(j).toString());
//			}
//		}
//		if (flag == true) {
//			System.out.println("No meeting on this Month!");
//		}
//	}
	
	public static void closeIndividualTS(MyCalendar calendar, String date, String time) {
		calendar.availableDates.get(date).removeTimeSlot(time);
	}
	
	public static void closeDayOfWeekTS(MyCalendar calendar, int day, String time) {
		for (AvailableDate value : calendar.availableDates.values()) {
			if (value.day == day) {
				value.removeTimeSlot(time);
			}
		}
	}
	
	public static void closeDateTS(MyCalendar calendar, String date) {
		calendar.removeDate(date);
	}
	
	public static void closeTimeTS(MyCalendar calendar, String time) {
		for (AvailableDate value : calendar.availableDates.values()) {
			value.removeTimeSlot(time);
		}
	}
	
	
}
