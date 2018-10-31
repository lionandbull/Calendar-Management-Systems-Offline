package calendar.model;
import java.util.*;

import util.MyUtility;


public class Main {
	public static void main(String[] args) {
		
		Calendar startingDate_ = MyUtility.calendarSetDate(1, 1);
		Calendar endingDate_ = MyUtility.calendarSetDate(2, 20);
		Calendar earliestHour_ = MyUtility.calendarSetTime(9, 0);
		Calendar latestHour_ = MyUtility.calendarSetTime(15, 0);
		MyCalendar calendar = new MyCalendar("Personal", "Wade", startingDate_, endingDate_, 
				earliestHour_, latestHour_, 20, "Home");
		
		System.out.println(calendar.availableDates.values().iterator().next().timeSlot.values().iterator().next());
		System.out.println(calendar.availableDates.values().iterator().next().toTimeArrayList());
		
//		// add available date
//		calendar.addDate("1/3");
//		calendar.addDate("1/4");
//		calendar.addDate("1/10");
//		
//		// add time
//		calendar.availableDates.get("1/3").addTimeSlot("10:00");
//		calendar.availableDates.get("1/4").addTimeSlot("10:00");
//		calendar.availableDates.get("1/3").addTimeSlot("11:20");
//		
//		// add meeting
//		Person person = new Person("Connie");
//		Methods.scheduleMeeting(calendar, person, "1/3", "10:00");
//		Methods.scheduleMeeting(calendar, person, "1/3", "11:20");
//		
//		// show daily schedule
//		Methods.showDailySchedule(calendar, "1/3");
//		Methods.showDailySchedule(calendar, "1/4");
//		Methods.showMonthlySchedule(calendar, 1);
//		Methods.showMonthlySchedule(calendar, 2);
//		
//		System.out.println("---------------");
//		Methods.closeTimeTS(calendar, "10:00");
		
	}
}
