package calendar.model;

public class AvailableTime {
	public String time;
	public Boolean available = true;
	public Boolean reserved = false;
	
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
