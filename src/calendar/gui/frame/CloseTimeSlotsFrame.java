package calendar.gui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import calendar.model.AvailableDate;
import calendar.model.Meeting;
import util.MyUtility;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CloseTimeSlotsFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CloseTimeSlotsFrame frame = new CloseTimeSlotsFrame();
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
	public CloseTimeSlotsFrame() {
		setTitle("Close Timeslots");
		setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Close an Indivisual Timeslot");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CloseIndividualTSFrame();
			}
		});
		btnNewButton.setBounds(106, 32, 262, 45);
		contentPane.add(btnNewButton);
		
		JButton btnAllTimeslotsOn = new JButton("All timeslots on a given Day of Week and time");
		btnAllTimeslotsOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CloseTSOfDAYOfWeekAndTime();
			}
		});
		btnAllTimeslotsOn.setBounds(76, 263, 343, 45);
		contentPane.add(btnAllTimeslotsOn);
		
		JButton btnAllTimeslotsOn_1 = new JButton("All timeslots on a given Day");
		btnAllTimeslotsOn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog(
	                    contentPane,
	                    "The date format is as below:\n" + 
	                    "e.g. \"2018/1/1\" ",
	                    "Close all timeslots on a given Day",
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
				CMABoard.myCalendar.availableDates.get(s).available = false;
				Object[] options = {"Ok"};
				int response = JOptionPane.showOptionDialog(contentPane, 
						"You have closed the date: " + s , 
						"Succeed!",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(response==0)
				{ 
		
				}
			    return;
			}
			else {
				Object[] options = {"Ok"};
				int response = JOptionPane.showOptionDialog(contentPane, 
						"Wrong date format. Please use the date format as below: \n "
						+ "e.g. \"2018/1/1\".", 
						"Warning",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(response==0)
				{ 
		
				}
			}
		}
	
	}
});
		btnAllTimeslotsOn_1.setBounds(106, 104, 262, 45);
		contentPane.add(btnAllTimeslotsOn_1);
		
		JButton btnAllTimeslotsAt = new JButton("All timeslots at a given time");
		btnAllTimeslotsAt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> a = CMABoard.myCalendar.availableDates.values().iterator().next().toTimeArrayList();
				Object[] possibilities = a.toArray();
				String s = (String)JOptionPane.showInputDialog(
				                    contentPane,
				                    "Please choose a timeslot.",
				                    "Close all timeslots at a given time",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    possibilities,
				                    possibilities[0]);
				
				if ((s != null) && (s.length() > 0)) {
					for (AvailableDate value : CMABoard.myCalendar.availableDates.values()) {
						value.timeSlot.get(MyUtility.GetFirstTime(s)).available = false;
					}
					Object[] options = {"Ok"};
					int response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " + s + " of all dates.", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
				}
			}
		});
		btnAllTimeslotsAt.setBounds(106, 182, 262, 45);
		contentPane.add(btnAllTimeslotsAt);
		
	}

}
