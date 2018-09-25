package calendar.model;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;


public class Model {
	Hashtable<String, MyCalendar> calendars = new Hashtable<>();
	
	public MyCalendar add(MyCalendar mc) {
		MyCalendar old = calendars.get(mc.getName());
		calendars.put(mc.getName(), mc);
		
		return old;
	}
	
	/** Remove any contact associated with name, and return it if it was present. */
	public MyCalendar remove(String name) {
		MyCalendar old = calendars.get(name);
		calendars.remove(name);
		return old;
	}

	/** Return all contacts as a sorted array. */
	public MyCalendar[] getArray() {
		MyCalendar[] array = calendars.values().toArray(new MyCalendar[0]);
		Arrays.sort(array);
		return array;
	}

	/** Return all contacts as an iterator. */
	public Iterator<MyCalendar> calendars() {
		return calendars.values().iterator();
	}
}
