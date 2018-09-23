package calendar.model;
import java.util.*;

public class DailyTimeSlot {
	Calendar dateCalendar = Calendar.getInstance();
	String dateString;
	Map<String, Boolean> timeSlot = new HashMap<String, Boolean>();
	
	public DailyTimeSlot(int duration, Calendar earliestHour, Calendar latestHour) {
		dateCalendar = (Calendar) earliestHour.clone();
		int month = dateCalendar.get(Calendar.MONTH);
		int date = dateCalendar.get(Calendar.DATE);
		dateString = Integer.toString(month) + "/" + Integer.toString(date);
		
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
			this.timeSlot.put(tmpNow, false);
			// Consider boundary conditions !
			now.add(Calendar.MINUTE, duration);
		}
	
	}
}
