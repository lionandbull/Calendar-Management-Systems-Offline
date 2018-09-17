package com.calendar;
import java.util.*;


public class AvailableDate {
	int month;
	int day;
	String date;
	Boolean available = false;
	Boolean reserved = false;
	String error = null;
	Map<String, AvailableTime> timeSlot = new HashMap<String, AvailableTime>();
	
	public AvailableDate(String date, int duration, Calendar earliestHour, Calendar latestHour, int day, int month) {
		this.date = date;
		this.day = day;
		this.month = month;
		
		Calendar now = Calendar.getInstance();
		now = (Calendar) earliestHour.clone();
		while (latestHour.after(now)) {
			int hour = now.get(Calendar.HOUR_OF_DAY);
			int minute = now.get(Calendar.MINUTE);
			String tmpNow;
			if (minute == 0){
				tmpNow = Integer.toString(hour) + ":" + "00";
			}
			else {
				tmpNow = Integer.toString(hour) + ":" + Integer.toString(minute);
			}
			AvailableTime availableTime = new AvailableTime(tmpNow);
			this.timeSlot.put(tmpNow, availableTime);
			// Consider boundary conditions !
			now.add(Calendar.MINUTE, duration);
		}
	}
	
	public void addTimeSlot(String time) {
		if (this.available == true &&
			this.timeSlot.get(time).available == false) {
			this.timeSlot.get(time).available = true;
		}
		else if (this.available == false){
			throw new Error("This date is not available!");
		}
		else if (this.timeSlot.get(time).available == false){
			throw new Error("This time is not available!");
		}
	}
	
	public void removeTimeSlot(String time) {
		this.timeSlot.get(time).available = false;
		this.timeSlot.get(time).reserved = false;
	}
	
	public void clearTimeSlot() {
		for (AvailableTime value : timeSlot.values()) {
			value.available = false;
		}
	}
}
