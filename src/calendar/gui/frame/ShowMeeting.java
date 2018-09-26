package calendar.gui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import calendar.model.Meeting;
import calendar.model.Methods;
import util.MyUtility;

public class ShowMeeting extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnDailyButton = new JButton("Daily Meeting");
	private JButton btnMonthlyMeeting = new JButton("Monthly Meeting");
	private JButton btnAllMeeting = new JButton("All Meeting");
	private DefaultListModel<Meeting> listModel = new DefaultListModel<Meeting>();
	private JList meetingList;
	private JButton removeBtn = new JButton("Remove meeting");
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShowMeeting frame = new ShowMeeting();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ShowMeeting() {
		setTitle("Scheduled meeting(s)");
		setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setSize(400, 350);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new FlowLayout());  
		toolBar.add(btnAllMeeting);
		toolBar.add(btnDailyButton);  
		toolBar.add(btnMonthlyMeeting);
		btnAllMeeting.addActionListener(this);
		btnMonthlyMeeting.addActionListener(this);
		btnDailyButton.addActionListener(this);
		removeBtn.addActionListener(this);
	
		
		meetingList = new JList<Meeting>();
		meetingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(meetingList);
		
		meetingList.setModel(listModel);
		
		
		btnAllMeeting.doClick();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(toolBar, BorderLayout.NORTH);
		getContentPane().add(removeBtn, BorderLayout.SOUTH);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeBtn) {
			System.out.println(meetingList.getSelectedValue());
			if (meetingList.getSelectedValue() == null) {
				JOptionPane.showMessageDialog(this,
						"Please choose a meeting to delete.",
						"Warning",
					    JOptionPane.WARNING_MESSAGE);
			}
			else {
				Methods.cancelMeeting(CMABoard.myCalendar, (Meeting) meetingList.getSelectedValue());
				JOptionPane.showMessageDialog(this,
						"You have remove this meeting successfully.",
						"Succeed!",
					    JOptionPane.WARNING_MESSAGE);
				listModel = new DefaultListModel<Meeting>();
				meetingList.setModel(listModel);
				btnAllMeeting.doClick();
			}
		}
		if(e.getSource() == btnAllMeeting) {
			listModel.removeAllElements();
			for (Meeting value : CMABoard.myCalendar.meetings) {
				listModel.addElement(value);
			}
		}
		else if (e.getSource() == btnMonthlyMeeting) {
			listModel.removeAllElements();
			ArrayList<String> a = new ArrayList<>();
			for (int i = Parameters.SD_year; i <= Parameters.ED_year; i++) {
				if (Parameters.SD_year == Parameters.ED_year) {
					for (int j = Parameters.SD_month; j <= Parameters.ED_month; j++) {
						a.add(Integer.toString(i) + "/" + Integer.toString(j));
					}
				}
				else {
					if (i == Parameters.SD_year) {
						for (int j = Parameters.SD_month; j <= 12; j++) {
							a.add(Integer.toString(i) + "/" + Integer.toString(j));
						}
					}
					else if (i == Parameters.ED_year) {
						for (int j = 1; j <= Parameters.ED_month; j++) {
							a.add(Integer.toString(i) + "/" + Integer.toString(j));
						}
					}
					else {
						for (int j = 1; j <= 12; j++) {
							a.add(Integer.toString(i) + "/" + Integer.toString(j));
						}
					}
				}
				
			}
			Object[] possibilities = a.toArray();
			String s = (String)JOptionPane.showInputDialog(
			                    contentPane,
			                    "Please choose a month.",
			                    "Check a given month meeting(s)",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    possibilities[0]);
			if ((s != null) && (s.length() > 0)) {
				ArrayList<Meeting> monthlyMeeting = MyUtility.showMonthlySchedule(CMABoard.myCalendar, s);
				System.out.println(monthlyMeeting);
				int size = monthlyMeeting.size();
				for (int i = 0; i < size; i ++) {
					listModel.addElement(monthlyMeeting.get(i));
				}
				if (listModel.size() == 0) {
					Object[] options = {"Ok"};
					int response = JOptionPane.showOptionDialog(this, 
							"There is no meeting on " + s, 
							"Warning",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
				}
			    return;
			}

			//If you're here, the return value was null/empty.
			
			
			
			
		}
		else if (e.getSource() == btnDailyButton) {
			listModel.removeAllElements();
			String s = (String)JOptionPane.showInputDialog(
			                    contentPane,
			                    "The date format is as below:\n" + 
			                    "e.g. \"2018/1/1\" ",
			                    "Check a given date meeting(s)",
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
						System.out.println(s);
						ArrayList<Meeting> dailyMeeting = MyUtility.showDailySchedule(CMABoard.myCalendar, s);
						int size = dailyMeeting.size();
						for (int i = 0; i < size; i ++) {
							listModel.addElement(dailyMeeting.get(i));
						}
						if (listModel.size() == 0) {
							Object[] options = {"Ok"};
							int response = JOptionPane.showOptionDialog(this, 
									"There is no meeting at " + s, 
									"Warning",
									JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
							if(response==0)
							{ 
					
							}
						}
					    return;
					}
					else {
						Object[] options = {"Ok"};
						int response = JOptionPane.showOptionDialog(this, 
								"Wrong date format. Please use the date format as below: \n "
								+ "e.g. \"2018/1/1\".", 
								"Warning",
								JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if(response==0)
						{ 
				
						}
					}
					return;
				}
			Object[] options = {"Ok"};
			int response = JOptionPane.showOptionDialog(this, 
					"You haven't typed anything.", 
					"Warning",
					JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(response==0)
			{ 
	
			}
			
		}
	}

}
