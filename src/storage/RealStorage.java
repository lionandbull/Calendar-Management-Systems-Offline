package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import calendar.model.MyCalendar;


/**
 * Storage uses 'tab' character to separate values. One contact stored per line
 */
public class RealStorage implements IStorage {
	File storageFile = new File("calendars.txt");
	
	public static final String SEP = "\t";

	@Override
	public Iterator<MyCalendar> calendars() {
		ArrayList<MyCalendar> calendars = new ArrayList<>();

		Scanner sc;
		try {
			sc = new Scanner(storageFile);
		} catch (FileNotFoundException fnfe) {
			return calendars.iterator();
		}

		// retrieve each contact, one at a time.
		try {
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] vals = line.split(SEP);
				MyCalendar c = new MyCalendar();
				calendars.add(c);
			}
		} finally {
			sc.close();
		}

		return calendars.iterator();
	}

	@Override
	public boolean store(Iterator<MyCalendar> it) {
		try (PrintStream ps = new PrintStream(storageFile)) {
			while (it.hasNext()) {
				MyCalendar c = it.next();
				ps.println(c.getName());
			}
		} catch (IOException ioe) {
			return false;
		}
		
		return true;
	}

}