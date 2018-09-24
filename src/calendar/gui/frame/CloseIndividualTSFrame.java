package calendar.gui.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calendar.model.AvailableTime;
import calendar.model.Meeting;
import util.MyUtility;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class CloseIndividualTSFrame extends JFrame {

	private JPanel contentPane;
	private JPanel inputPanle = new JPanel();
	private JComboBox comboBox_1;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CloseIndividualTSFrame frame = new CloseIndividualTSFrame();
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
	public CloseIndividualTSFrame() {
		setTitle("Close an Indivisual Timeslot");
		setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Date:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
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
				String s = textField_1.getText();
				
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
				AvailableTime thisTime = CMABoard.myCalendar.availableDates.get(s).timeSlot.get(MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()));
//				System.out.println(s);
//				System.out.println(comboBox_1.getSelectedItem());
//				System.out.println(thisTime.reserved);
				thisTime.reserved = true;
				Object[] options = {"Ok"};
				int response = JOptionPane.showOptionDialog(contentPane, 
						"You have closed the time:\n " 
								+ s + " " + MyUtility.GetFirstTime((String)comboBox_1.getSelectedItem()), 
						"Succeed!",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(response==0)
				{ 
		
				}
				dispose();
			}
		});
	
		contentPane.add(inputPanle, BorderLayout.CENTER);
		
		
		
		GroupLayout gl_inputPanle = new GroupLayout(inputPanle);
		gl_inputPanle.setHorizontalGroup(
			gl_inputPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanle.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(29)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(145, Short.MAX_VALUE))
		);
		gl_inputPanle.setVerticalGroup(
			gl_inputPanle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanle.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addGroup(gl_inputPanle.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(113, Short.MAX_VALUE))
		);
		inputPanle.setLayout(gl_inputPanle);
		contentPane.add(btnSubmit, BorderLayout.SOUTH);
		
	}

}
