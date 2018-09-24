package calendar.model;
import java.util.*;

import util.MyUtility;


public class MyCalendar {
	String name;
	String organizer;
	String location;
	public Calendar startingDate;
	public Calendar endingDate;
	Calendar earliestHour;
	Calendar latestHour;
	public Map<String, AvailableDate> availableDates = new HashMap<String, AvailableDate>();
	public ArrayList<Meeting> meetings = new ArrayList<>();
	public ArrayList<String> meetingsString = new ArrayList<>();
	
	int duration;
	
	public MyCalendar(String name, String organizer, Calendar startingDate, Calendar endingDate,
			Calendar earliestHour, Calendar latestHour, int duration, String location) {
		this.name = name;
		this.organizer = organizer;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.earliestHour = earliestHour;
		this.latestHour = latestHour;
		this.duration = duration;
		this.location = location;
		
		//Initiate the availableDates
		Calendar now = Calendar.getInstance();
		now = (Calendar) startingDate.clone();
		while (endingDate.after(now) || MyUtility.calendarToDate(endingDate).equals(MyUtility.calendarToDate(now))) {
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			int date = now.get(Calendar.DATE);
			int day = now.get(Calendar.DAY_OF_WEEK);
			
			String dateString = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(date);
			this.availableDates.put(dateString, new AvailableDate(dateString, duration, earliestHour, latestHour, day, month));
			now.add(Calendar.DATE, 1);
		}
	}
	
	public void addDate(Calendar add_date) {
		if (add_date.after(endingDate)) {
			Calendar now = Calendar.getInstance();
			now = (Calendar) endingDate.clone();
			System.out.println("hahahaha" + " " + MyUtility.calendarToDate(add_date));
			while (add_date.after(now) || MyUtility.calendarToDate(add_date).equals(MyUtility.calendarToDate(now))) {
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1;
				int date = now.get(Calendar.DATE);
				int day = now.get(Calendar.DAY_OF_WEEK);
				
				String dateString = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(date);
				this.availableDates.put(dateString, new AvailableDate(dateString, duration, earliestHour, latestHour, day, month));
				now.add(Calendar.DATE, 1);
			}
			endingDate = add_date;
			System.out.println(MyUtility.calendarToDate(endingDate));
		}
		else if (startingDate.after(add_date)) {
			Calendar now = Calendar.getInstance();
			now = (Calendar) add_date.clone();
			while (startingDate.after(now)) {
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1;
				int date = now.get(Calendar.DATE);
				int day = now.get(Calendar.DAY_OF_WEEK);
				
				String dateString = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(date);
				this.availableDates.put(dateString, new AvailableDate(dateString, duration, earliestHour, latestHour, day, month));
				now.add(Calendar.DATE, 1);
			}
			startingDate = add_date;
		}
	}
	
	public void removeDate(String date) {
		if (availableDates.get(date) == null) {
			throw new Error("This date is out of the range!");
		}
		else {
			availableDates.get(date).available = false;
			// Set all timeslots to false
			availableDates.get(date).clearTimeSlot();
		}
	}
	
	public void addMeeting(Meeting meeting) {
		this.meetings.add(meeting);
	}
	
//	public static void main(String[] args) {
//		Calendar startingDate = Calendar.getInstance();
//		Calendar endingDate = Calendar.getInstance();
//		Calendar earliestHour = Calendar.getInstance();
//		Calendar latestHour = Calendar.getInstance();
//		startingDate.set(Calendar.MONTH, 1);
//		startingDate.set(Calendar.DATE, 1);
//		endingDate.set(Calendar.MONTH, 2);
//		endingDate.set(Calendar.DATE, 10);
//		int duration = 20;
//		String location = "home";
//		MyCalendar test = new MyCalendar("Personal", "Wade", startingDate, endingDate, earliestHour, 
//				latestHour, duration, location);
//		System.out.println(test.availableDates);
//	}
}
	

