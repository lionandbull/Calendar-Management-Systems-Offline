package calendar.model;
import java.util.*;

import util.MyUtility;


public class AvailableDate {
	int month;
	public int day;
	public String date;
	public Boolean available = true;
	public Boolean reserved = false;
	String error = null;
	public LinkedHashMap<String, AvailableTime> timeSlot = new LinkedHashMap<String, AvailableTime>();
	
	public AvailableDate(String date, int duration, Calendar earliestHour, Calendar latestHour, int day, int month) {
		this.date = date;
		this.day = day;
		this.month = month;
		
		int hour;
		int minute;
		
		Calendar now = Calendar.getInstance();
		now = (Calendar) earliestHour.clone();
		while (latestHour.after(now) || MyUtility.calendarToTime(now).equals(MyUtility.calendarToTime(latestHour))) {
			hour = now.get(Calendar.HOUR_OF_DAY);
			minute = now.get(Calendar.MINUTE);
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
	}
	
	public void clearTimeSlot() {
		for (AvailableTime value : timeSlot.values()) {
			value.available = false;
		}
	}
	
	public ArrayList<String> toTimeArrayList(){
		ArrayList<String> returnArray = new ArrayList<>();
		String tmp = "";
		for (String key : timeSlot.keySet()) {
			if (tmp == "") {
				tmp = key;
			}
			else {
				returnArray.add(tmp + "-" + key);
				tmp = key;
			}
		}
		return returnArray;
	}
	 
}
