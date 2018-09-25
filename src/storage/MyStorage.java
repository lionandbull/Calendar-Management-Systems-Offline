package storage;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import calendar.model.*;
import util.MyUtility;

public class MyStorage {
	public static String filename = "calendars.txt";
	static MyCalendar myCalendar;
    
    
    // 生成并保存 Person 对象
    public static void createAndSave(ArrayList<MyCalendar> mc) throws IOException {
//        ArrayList<MyCalendar> calendars = createCalendars();
    	ArrayList<MyCalendar> calendars = mc;
        saveCalendars(calendars);
    }
 
    // 读取并显示 Person 对象
    public static void readAndShow() throws IOException {
        ArrayList<MyCalendar> calendars = readCalendar();
        showCalendars(calendars);
    }
 
    // 创建要保存的 Person 对象
    public static ArrayList<MyCalendar> createCalendars() {
        ArrayList<MyCalendar> result = new ArrayList<MyCalendar>();
        Calendar startingDate = Calendar.getInstance();
		Calendar endingDate = Calendar.getInstance();
		endingDate.add(Calendar.MONTH, 1);
		Calendar latestHour = Calendar.getInstance();
		latestHour.set(Calendar.HOUR_OF_DAY, 16);
		latestHour.set(Calendar.MINUTE, 0);
		Calendar earliestHour = Calendar.getInstance();
		earliestHour.set(Calendar.HOUR_OF_DAY, 8);
		earliestHour.set(Calendar.MINUTE, 0);
		MyCalendar mc = new MyCalendar("TestCalendar", "Wade", startingDate, endingDate, earliestHour, latestHour, 15, "Home");
		System.out.println(mc.availableDates.keySet());
		System.out.println(mc.availableDates.values().iterator().next().timeSlot.keySet());
		Methods.scheduleMeeting(mc, new Person("God"), "2018/9/28", "9:15");
		Methods.scheduleMeeting(mc, new Person("God"), "2018/9/29", "9:15");
		Methods.scheduleMeeting(mc, new Person("God"), "2018/10/1", "9:15");
		Methods.scheduleMeeting(mc, new Person("God"), "2018/10/2", "9:15");
		System.out.println(mc.meetings);
		myCalendar = mc;
        result.add(mc);
        return result;
    }
 

    public static void saveCalendars(ArrayList<MyCalendar> calendars) throws IOException {
 
        // 生成文件内容
        String data = "";
        for (MyCalendar mc : calendars) {
            data += getCalendarString(mc) + "\n";
        }
 
        // 保存文件内容
        FileWriter writer = new FileWriter(filename);
        writer.write(data);
        writer.close();
        System.out.println("Calendars have saved.");
    }
 
    public static String getCalendarString(MyCalendar mc) {
    	// Get parameters' string of constructor
    	String returnString = mc.getName() + "/t" + mc.getOrganizer() + "/t" + mc.getStartDate() + "/t" + mc.getEndDate() + "/t" + mc.getEarliestHour() + "/t" + mc.getLatestHour()
		+ "/t" + mc.getDuration() + "/t" + mc.getLocation() + "/t" + getAvailableDatesString(mc) + "/t" + getMeetings(mc);
        return returnString;
    }
    
    public static String getAvailableDatesString(MyCalendar mc) {
    	String availableDates = "";
    	for (AvailableDate value : mc.availableDates.values()) {
    		availableDates = availableDates + value.date + "/:" + getTimeSlotString(value) + "/r";
    	}
    	availableDates = availableDates.substring(0, availableDates.length() - 2);
    	return availableDates;
    }
    
    public static String getTimeSlotString(AvailableDate ad) {
    	String timeSlot = "";
    	for (AvailableTime value : ad.timeSlot.values()) {
    		timeSlot = timeSlot + value.time + "/;" + getAvailableTimeString(value) + "/e";
    	}
    	timeSlot = timeSlot.substring(0, timeSlot.length() - 2);
    	return timeSlot + ad.getMonth() + "/w" + ad.getDay() + "/w" + ad.getDate() + "/w" + ad.getAvailable() + "/w" + ad.getReserved() + "/w"; 
    }
    
    public static String getAvailableTimeString(AvailableTime at) {
    	return at.getTime() + "/q" + at.getAvailable() + "/q" + at.getReserved();
    }
    
    public static String getMeetings(MyCalendar mc) {
    	String meetings = "";
    	for (Meeting value : mc.meetings) {
    		meetings = meetings + getMeeting(value) + "/a";
    	}
    	if (meetings == "") {
    		return meetings;
    	}
    	meetings = meetings.substring(0, meetings.length() - 2);
    	return meetings;
    }
    
    public static String getMeeting(Meeting meeting) {
    	return meeting.getLocation() + "/s" + meeting.getPerson() + "/s" + meeting.getMeetDate() + "/s" + meeting.meetTime;
    }
 
  
    // 从文件中读取 Person 对象
    public static ArrayList<MyCalendar> readCalendar() throws IOException {
        ArrayList<MyCalendar> result = new ArrayList<MyCalendar>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        
        while ((line = reader.readLine()) != null) {
            result.add(getCalendarFromString(line));
        }
 
        return result;
    }
 
    // 通过一行文件内容生成一个 Person 对象
    public static MyCalendar getCalendarFromString(String line) {

		
        String[] parts = line.split("/t"); 
        String name = parts[0];
        String organizer = parts[1];
        Calendar startingDate = MyUtility.calendarSetDate(parts[2]);
        Calendar endingDate = MyUtility.calendarSetDate(parts[3]);
        Calendar earliestHour = MyUtility.calendarSetTime(parts[4]);
        Calendar latestHour = MyUtility.calendarSetTime(parts[5]);
        int duration = Integer.parseInt(parts[6]);
        String location = parts[7];
        MyCalendar mc = new MyCalendar(name, organizer, startingDate, endingDate, earliestHour, latestHour, duration, location);
        // Set meetings
        if (parts.length == 10) {
        	String[] partMeeings = parts[9].split("/a");
            ArrayList<Meeting> meetings = new ArrayList<>();
            for (int i = 0; i < partMeeings.length; i++) {
            	String[] partMeeing = partMeeings[i].split("/s");
            	meetings.add(new Meeting(new Person(partMeeing[1]), partMeeing[2], partMeeing[3], partMeeing[0]));
            }
            mc.meetings = meetings;
        }

        
        //*** Set availabelDates ***//
        // TimeSlot
        Map<String, AvailableDate> availableDates = new HashMap<String, AvailableDate>();
        String[] partavailableDate = parts[8].split("/r");
        
        for (int i=0; i<partavailableDate.length; i++) {
        	String ADKey = (partavailableDate[i].split("/:"))[0];
        	String[] partAvailableDate =  (partavailableDate[i].split("/:"))[1].split("/w");
        	String[] partTimeSlot = (partavailableDate[i].split("/:"))[1].split("/e");
        	mc.availableDates.get(ADKey).available = Boolean.parseBoolean(partAvailableDate[3]);
        	mc.availableDates.get(ADKey).reserved = Boolean.parseBoolean(partAvailableDate[4]);
        	LinkedHashMap<String, AvailableTime> timeSlot = new LinkedHashMap<String, AvailableTime>();
        	for (int j=0; j<partTimeSlot.length; j++) {
        		String TSKey = (partTimeSlot[j].split("/;"))[0];
        		String[] partTS = (partTimeSlot[j].split("/;"))[1].split("/q");
 
        		mc.availableDates.get(ADKey).timeSlot.get(TSKey).available = Boolean.parseBoolean(partTS[1]);
        		mc.availableDates.get(ADKey).timeSlot.get(TSKey).reserved = Boolean.parseBoolean(partTS[2]);
        	}
        }
        return mc; 
    }
 
    // 显示 Person 对象
    public static void showCalendars(ArrayList<MyCalendar> calendars) {
        for (MyCalendar mc : calendars) {
            System.out.println(mc.getName() + ", " +
                    mc.getOrganizer() + ", " +
                    mc.getLocation());
        }
    }
}
