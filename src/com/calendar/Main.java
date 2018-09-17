package com.calendar;
import java.util.*;


public class Main {
	public static void main(String[] args) {
		
		Calendar startingDate_ = MyUtility.calendarSetDate(1, 1);
		Calendar endingDate_ = MyUtility.calendarSetDate(2, 20);
		Calendar earliestHour_ = MyUtility.calendarSetTime(9, 0);
		Calendar latestHour_ = MyUtility.calendarSetTime(15, 0);
		MyCalendar calendar = new MyCalendar("Personal", "Wade", startingDate_, endingDate_, 
				earliestHour_, latestHour_, 20, "Home");
		
		// add available date
		calendar.addDate("1/3");
		calendar.addDate("1/4");
		calendar.addDate("1/10");
		
		// add time
		calendar.availableDates.get("1/3").addTimeSlot("10:00");
		calendar.availableDates.get("1/4").addTimeSlot("10:00");
		calendar.availableDates.get("1/3").addTimeSlot("11:20");
		
		// add meeting
		Person person = new Person("Connie");
		Control.scheduleMeeting(calendar, person, "1/3", "10:00");
		Control.scheduleMeeting(calendar, person, "1/3", "11:20");
		
		// show daily schedule
		Control.showDailySchedule(calendar, "1/3");
		Control.showDailySchedule(calendar, "1/4");
		Control.showMonthlySchedule(calendar, 1);
		Control.showMonthlySchedule(calendar, 2);
		
		System.out.println("---------------");
		Control.closeTimeTS(calendar, "10:00");
		
	}
}
