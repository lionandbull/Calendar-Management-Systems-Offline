package calendar.gui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import calendar.model.AvailableDate;
import calendar.model.AvailableTime;
import util.MyUtility;

public class CloseTSOfDAYOfWeekAndTime extends JFrame {

	private JPanel contentPane;
	private JPanel inputPanle = new JPanel();
	private JComboBox comboBox_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CloseTSOfDAYOfWeekAndTime frame = new CloseTSOfDAYOfWeekAndTime();
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
	public CloseTSOfDAYOfWeekAndTime() {
		setTitle("Close all timeslots on a given Day of Week and time");
		setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Day of Week:");
		
		JLabel lblNewLabel_1 = new JLabel("Time:");
		
		JComboBox comboBox_1 = new JComboBox();
		ArrayList<String> timeArrayList = CMABoard.myCalendar.availableDates.values().iterator().next().toTimeArrayList();
		String tmpTime;
		for (String i : timeArrayList) {
			comboBox_1.addItem(i);
		}
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dayOfWeek = (String)comboBox.getSelectedItem();
				String time = MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem());
				for (AvailableDate value : CMABoard.myCalendar.availableDates.values()) {
					if (value.day == Integer.parseInt(dayOfWeek)) {
						value.timeSlot.get(time).available = false;
					}
				}
				Object[] options = {"Ok"};
				int response;
				switch (dayOfWeek) {
				case "1":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Monday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;

				case "2":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Tuesday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;
					
				case "3":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Wednesday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;
				case "4":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Thursday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;
				case "5":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Friday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;
				case "6":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Saturday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;
				case "7":
					response = JOptionPane.showOptionDialog(contentPane, 
							"You have closed the time:\n " 
									+ MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()) + " of each Sunday", 
							"Succeed!",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response==0)
					{ 
			
					}
					break;
					
				}
				
				dispose();
			}
		});
	
		contentPane.add(inputPanle, BorderLayout.CENTER);
		
		comboBox = new JComboBox();
		for (int i = 1 ; i < 8; i++) {
			comboBox.addItem(Integer.toString(i));
		}
		
		
		
		GroupLayout gl_inputPanle = new GroupLayout(inputPanle);
		gl_inputPanle.setHorizontalGroup(
			gl_inputPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanle.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(29)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.LEADING, false)
						.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBox_1, 0, 115, Short.MAX_VALUE))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_inputPanle.setVerticalGroup(
			gl_inputPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanle.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		inputPanle.setLayout(gl_inputPanle);
		contentPane.add(btnSubmit, BorderLayout.SOUTH);
	}

}
