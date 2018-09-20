package com.gui.frame;

import com.calendar.*;
import java.util.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.gui.panel.*;

public class CMABoard extends JFrame {

	private JPanel contentPane;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CMABoard frame = new CMABoard();
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
	public CMABoard() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Starting date
		JLabel lblNewLabel = new JLabel("Start date:");
		lblNewLabel.setBounds(16, 61, 70, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox_SD_Day = new JComboBox();
		comboBox_SD_Day.setBounds(281, 57, 95, 27);
		for (int i = 1; i <= 31; i++) {
			comboBox_SD_Day.addItem(Integer.toString(i));
		}
		contentPane.add(comboBox_SD_Day);
		
		JComboBox comboBox_SD_Month = new JComboBox();
		
		for (int i = 1; i <= 12; i++) {
			comboBox_SD_Month.addItem(Integer.toString(i));
		}
		
		comboBox_SD_Month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String month = (String)cb.getSelectedItem();
				updateComboBox(month, comboBox_SD_Day);
			}
		});
		comboBox_SD_Month.setBounds(140, 57, 95, 27);
		contentPane.add(comboBox_SD_Month);
		
		JLabel lblNyearel = new JLabel("Month");
		lblNyearel.setBounds(94, 61, 40, 16);
		contentPane.add(lblNyearel);
		
		JLabel lblDate = new JLabel("Day");
		lblDate.setBounds(247, 61, 61, 16);
		contentPane.add(lblDate);
		
		// End date
		JLabel lblEndDate = new JLabel("End date:");
		lblEndDate.setBounds(16, 89, 70, 16);
		contentPane.add(lblEndDate);
		
		JLabel label_1 = new JLabel("Month");
		label_1.setBounds(94, 89, 40, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Day");
		label_2.setBounds(247, 89, 61, 16);
		contentPane.add(label_2);
		
		JComboBox comboBox_ED_Day = new JComboBox();
		comboBox_ED_Day.setBounds(281, 85, 95, 27);
		for (int i = 1; i <= 31; i++) {
			comboBox_ED_Day.addItem(Integer.toString(i));
		}
		contentPane.add(comboBox_ED_Day);
		
		JComboBox comboBox_ED_Month = new JComboBox();
		comboBox_ED_Month.setBounds(140, 85, 95, 27);
		contentPane.add(comboBox_ED_Month);
		
		for (int i = 1; i <= 12; i++) {
			comboBox_ED_Month.addItem(Integer.toString(i));
		}
		
		comboBox_ED_Month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				String month = (String)cb.getSelectedItem();
				updateComboBox(month, comboBox_ED_Day);
			}
		});
		
		// Organizer
		JLabel lblOrganizer = new JLabel("Organizer:");
		lblOrganizer.setBounds(16, 33, 70, 16);
		contentPane.add(lblOrganizer);
		
		JTextField organizer = new JTextField(20);
		organizer.setBounds(94, 28, 130, 26);
		contentPane.add(organizer);
		
		// Location
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(16, 190, 70, 16);
		contentPane.add(lblLocation);
		
		JTextField location = new JTextField(20);
		location.setBounds(94, 185, 282, 26);
		contentPane.add(location);
		
		// Calendar Name
		JLabel lblCalendarName = new JLabel("Calendar Name:");
		lblCalendarName.setBounds(16, 11, 99, 16);
		contentPane.add(lblCalendarName);
		
		JTextField calName = new JTextField(20);
		calName.setBounds(128, 6, 159, 26);
		contentPane.add(calName);
		
		// Earliest Hour & Latest Hour
		JLabel label = new JLabel("Earliest hour:");
		label.setBounds(16, 121, 83, 16);
		contentPane.add(label);
		
		JLabel label_3 = new JLabel("Hour");
		label_3.setBounds(104, 121, 40, 16);
		contentPane.add(label_3);
		
		JComboBox comboBox_EH_Hour = new JComboBox();
		comboBox_EH_Hour.setBounds(140, 117, 95, 27);
		for (int i = 0; i <= 23; i++) {
			comboBox_EH_Hour.addItem(Integer.toString(i));
		}
		contentPane.add(comboBox_EH_Hour);
		
		JLabel label_4 = new JLabel("Min");
		label_4.setBounds(247, 121, 61, 16);
		contentPane.add(label_4);
		
		JComboBox comboBox_EH_Minute = new JComboBox();
		comboBox_EH_Minute.setBounds(281, 117, 95, 27);
		for (int i = 0; i <= 59; i++) {
			comboBox_EH_Minute.addItem(Integer.toString(i));
		}
		contentPane.add(comboBox_EH_Minute);
		
		JLabel label_5 = new JLabel("Latest hour:");
		label_5.setBounds(16, 155, 95, 16);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Hour");
		label_6.setBounds(104, 155, 40, 16);
		contentPane.add(label_6);
		
		JComboBox comboBox_LH_Hour = new JComboBox();
		comboBox_LH_Hour.setBounds(140, 151, 95, 27);
		for (int i = 0; i <= 23; i++) {
			comboBox_LH_Hour.addItem(Integer.toString(i));
		}
		contentPane.add(comboBox_LH_Hour);
		
		JLabel label_7 = new JLabel("Min");
		label_7.setBounds(247, 155, 61, 16);
		contentPane.add(label_7);
		
		JComboBox comboBox_LH_Minute = new JComboBox();
		comboBox_LH_Minute.setBounds(281, 151, 95, 27);
		for (int i = 0; i <= 59; i++) {
			comboBox_LH_Minute.addItem(Integer.toString(i));
		}
		contentPane.add(comboBox_LH_Minute);
		
		// Duration
		JComboBox comboBox_duration = new JComboBox();
		comboBox_duration.setBounds(317, 29, 95, 27);
		comboBox_duration.addItem("10 min");
		comboBox_duration.addItem("15 min");
		comboBox_duration.addItem("20 min");
		comboBox_duration.addItem("30 min");
		comboBox_duration.addItem("60 min");
		contentPane.add(comboBox_duration);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setBounds(247, 32, 83, 16);
		contentPane.add(lblDuration);
		
		// Submit
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetParameters.setOrganizer(organizer.getText());
				SetParameters.setLocation(location.getText());
				SetParameters.setName(calName.getText());
				SetParameters.setStartingDateMonth((String)comboBox_SD_Month.getSelectedItem());
				SetParameters.setStartingDateDay((String)comboBox_SD_Day.getSelectedItem());
				SetParameters.setEndingDateMonth((String)comboBox_ED_Month.getSelectedItem());
				SetParameters.setEndingDateDay((String)comboBox_ED_Day.getSelectedItem());
				SetParameters.setEarliestHourHour((String)comboBox_EH_Hour.getSelectedItem());
				SetParameters.setEarliestHourMin((String)comboBox_EH_Minute.getSelectedItem());
				SetParameters.setLatestHourHour((String)comboBox_LH_Hour.getSelectedItem());
				SetParameters.setLatestHourMin((String)comboBox_LH_Minute.getSelectedItem());
				String duration = (String)comboBox_duration.getSelectedItem();
				String a = duration.substring(0, 2);
				SetParameters.setDuration(Integer.parseInt(a));
				System.out.println(SetParameters.myString());
			}
		});
		btnSubmit.setBounds(172, 223, 95, 38);
		contentPane.add(btnSubmit);
		
		
		
		
		
	}
	protected void updateComboBox(String month, JComboBox cb) {
		Calendar cal = Calendar.getInstance();
		// Remember to change the year of 2018!!!!!!!!!!!!!!
		cal.set(Calendar.YEAR,2018);
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
		int previousMonth = Integer.valueOf((String) cb.getItemAt(cb.getItemCount()-1));
		if (dateOfMonth > previousMonth) {
			for (int i = dateOfMonth; i > previousMonth; i--) {
				cb.addItem(String.valueOf(i));
			}
		}
		else if(dateOfMonth < previousMonth) {
			for (int i = previousMonth; i > dateOfMonth; i--) {
				cb.removeItem(String.valueOf(i));
			}
		}
	}
	
	
}
