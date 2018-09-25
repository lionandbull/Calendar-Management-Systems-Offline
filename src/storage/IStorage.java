package storage;

import java.util.Iterator;

import calendar.model.MyCalendar;




public interface IStorage {
	/** Retrieve contacts. */
	Iterator<MyCalendar> calendars();
	
	/** Store updated contacts. */
	boolean store(Iterator<MyCalendar> it);
}
