package calendar.gui.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calendar.gui.panel.*;
import calendar.model.Meeting;
import calendar.model.Methods;
import calendar.model.MyCalendar;
import util.MyUtility;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
//	public static MainFrame mainFrame = new MainFrame();
	public static CalendarPanel panel = new CalendarPanel();
	public PanelAction panelAction = new PanelAction();
	public JToolBar toolBar;  
	public JToolBar toolBar2;
	
	
	
	public JToolBar buildToolBar() {  
		if (toolBar == null) {  
		toolBar = new JToolBar();  
		toolBar.setLayout(new FlowLayout());  
		toolBar.add(buildButton("New calendar"));  
		toolBar.add(buildButton("Load calendar")); 
		toolBar.add(buildButton("Show Meeting(s)"));
		toolBar.add(buildButton("Add Date"));  
		toolBar.add(buildButton("Close Date"));    
		
		toolBar.setEnabled(false); // 设置toolBar的可移动性  
		}  
		return toolBar;  
		}  
	
	public JToolBar buildToolBar2() {  
		if (toolBar2 == null) {  
			toolBar2 = new JToolBar(JToolBar.VERTICAL); 
			toolBar2.add(buildButton("Show Meeting(s)"));
			toolBar2.add(buildButton("New calendar"));  
			toolBar2.add(buildButton("New calendar")); 
		
		toolBar2.setEnabled(false); // 设置toolBar的可移动性  
		}  
		return toolBar2;  
		}  
	
	public JPanel buildPanel(String title) {  
		if (panel == null) {  
		panel = new CalendarPanel();  
		panel.add(new Label("Calendar"), BorderLayout.CENTER);  
//		panel.setBorder(BorderFactory.createTitledBorder(""));  
		}  
		return panel;  
		}  
	
	public JButton buildButton(String name) {  
		JButton btn = new JButton(name);  
		btn.addActionListener(panelAction);
		return btn;  
		}
	
	private class PanelAction implements ActionListener {  
		public void actionPerformed(ActionEvent e) {  
		String btnName = e.getActionCommand();  
		if (btnName == "New calendar") {
			
			new CMABoard();
			
		}
		else if (btnName == "Load calendar") {
			
		}
		else if (btnName == "Add Date") {
			if (CMABoard.myCalendar == null) {
				JOptionPane.showMessageDialog(panel,
						"There is no available calendar, please create a new calendar or upload an existed calendar.",
						"Error",
					    JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String s = (String)JOptionPane.showInputDialog(
                    panel,
                    "The date format is as below:\n" + 
		                    "e.g. \"2018/1/1\" ",
		                    "Add a date",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null);

			//If a string was returned, say so.
			if ((s != null) && (s.length() > 0)) {
				
				if (MyUtility.isValidDate(s)) {
					String tmp;
					if (s.substring(5, 6).equals("0")) {
						if (s.substring(8, 9).equals("0")) {
							tmp = s.substring(0, 5) + s.substring(6, 8) + s.substring(9, 10);
						}
						else {
							tmp = s.substring(0, 5) + s.substring(6, 9);
						}
					}
					else {
						if (s.substring(s.length() - 2, s.length() - 1).equals("0")) {
							tmp = s.substring(0, s.length()-2) + s.substring(s.length() - 1, s.length());
						}
						else {
							tmp = s;
						}
					}
					s = tmp;
					int year = MyUtility.DateStringToInts(s).get(0);
					int month = MyUtility.DateStringToInts(s).get(1);
					int day = MyUtility.DateStringToInts(s).get(2);
					CMABoard.myCalendar.addDate(MyUtility.calendarSetDate(year, month, day));
					MyUtility.UpdateDate();
					
				}
				else {
					Object[] options = {"Ok"};
					int response = JOptionPane.showOptionDialog(panel, 
							"Wrong date format. Please use the date format as below: \n "
							+ "e.g. '2018/1/1'.", 
							"Warning",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
				}
				return;
			}
			Object[] options = {"Ok"};
			int response = JOptionPane.showOptionDialog(panel, 
					"You haven't typed anything.", 
					"Warning",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(response==0)
			{ 
	
			}
		}
		else if (btnName == "Show Meeting(s)") {
			
			if (CMABoard.myCalendar == null) {
				JOptionPane.showMessageDialog(panel,
						"There is no available calendar, please create a new calendar or upload an existed calendar.",
						"Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else if(CMABoard.myCalendar.meetings.size() == 0) {
				JOptionPane.showMessageDialog(panel,
						"There is no meeting.",
						"Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else {
				new ShowMeeting();
			}
		}
		else if (btnName == "Close Date") {
			if (CMABoard.myCalendar == null) {
				JOptionPane.showMessageDialog(panel,
						"There is no available calendar, please create a new calendar or upload an existed calendar.",
						"Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else {
				new CloseTimeSlotsFrame();
			}
			
		}
	}
}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		this.setTitle("Calendar Management System");
		this.setSize(550, 500);
		this.add(buildToolBar(), BorderLayout.NORTH);  
		this.add(buildPanel("Calendar"), BorderLayout.CENTER); 
//		this.add(buildToolBar2(), BorderLayout.EAST);  
//		this.setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

}
