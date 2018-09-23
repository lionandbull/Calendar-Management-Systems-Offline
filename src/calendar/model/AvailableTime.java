package calendar.model;

public class AvailableTime {
	String time;
	Boolean available = true;
	Boolean reserved = false;
	
	public AvailableTime(String time) {
		this.time = time;
	}
	
	public void reserve() {
		this.reserved = true;
	}
	
	public void remove() {
		this.reserved = false;
	}
	
}
