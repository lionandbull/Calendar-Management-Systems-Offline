package calendar;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import calendar.gui.frame.CMABoard;
import calendar.gui.frame.MainFrame;
import calendar.model.MyCalendar;
import calendar.controller.*;
import storage.MyStorage;

public class Main {
	
	public static void main(String[] args) throws IOException {
		ArrayList<MyCalendar> calendars = new ArrayList<>();
		
		MyStorage myStorage = new MyStorage();
		if (new File(myStorage.filename).exists()) {
			calendars = myStorage.readCalendar();
		}
			
		MainFrame calendarFrame = new MainFrame(calendars);
		calendarFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		calendarFrame.addWindowListener (new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int flag = new QuitController().confirm(calendarFrame);
				 if ( flag == 0) {
						calendarFrame.myCalendars.add(CMABoard.myCalendar);
						try {
							myStorage.saveCalendars(calendarFrame.myCalendars);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						calendarFrame.dispose();
					}
				 else if (flag == 1) {
						calendarFrame.dispose();
				 }
				 
				}
				
             }
		);	
		 calendarFrame.setVisible(true);
		
	}
}
