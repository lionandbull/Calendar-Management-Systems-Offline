package calendar.model;
import java.util.*;

public class Meeting {
	String location;
	Person person;
	public String meetDate;
	public String meetTime;
	
	public Meeting(Person person, String meetDate, String meetTime, String location) {
		this.person = person;
		this.meetDate = meetDate;
		this.meetTime = meetTime;
		this.location = location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString() {
		String s = "A meeting with " + person.name + " on: " + meetDate + " " + meetTime + " at " + location;
		return s;
	}
	
	public String checkMonth() {
		String s = "";
		int size = meetDate.length();
		for (int j = 5; j < size; j++) {
			if (meetDate.charAt(j) != '/') {
				s += meetDate.charAt(j);
			}
			else {
				break;
			}
		}
		return meetDate.substring(0, 5) + s;
	}
	
	public String getLocation() { return location; }
	public String getPerson() { return person.name; }
	public String getMeetDate() { return meetDate; }
	public String getMeetTime() { return meetTime; }

}
