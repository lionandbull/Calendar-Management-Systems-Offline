package calendar.gui.panel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import calendar.gui.frame.MainFrame;
import calendar.gui.frame.Parameters;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.Font;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class InitiatePanel extends JPanel {
	public static InitiatePanel initiatePanel = new InitiatePanel();
	
	/**
	 * Create the panel.
	 */
	public InitiatePanel() {
		setLayout(null);
		
		// Starting date
				JLabel lblNewLabel = new JLabel("Start date:");
				lblNewLabel.setBounds(16, 61, 70, 16);
				this.add(lblNewLabel);
				
				JComboBox comboBox_SD_Day = new JComboBox();
				comboBox_SD_Day.setBounds(281, 57, 95, 27);
				for (int i = 1; i <= 31; i++) {
					comboBox_SD_Day.addItem(Integer.toString(i));
				}
				this.add(comboBox_SD_Day);
				
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
				this.add(comboBox_SD_Month);
				
				JLabel lblNyearel = new JLabel("Month");
				lblNyearel.setBounds(94, 61, 40, 16);
				this.add(lblNyearel);
				
				JLabel lblDate = new JLabel("Day");
				lblDate.setBounds(247, 61, 61, 16);
				this.add(lblDate);
				
				// End date
				JLabel lblEndDate = new JLabel("End date:");
				lblEndDate.setBounds(16, 89, 70, 16);
				this.add(lblEndDate);
				
				JLabel label_1 = new JLabel("Month");
				label_1.setBounds(94, 89, 40, 16);
				this.add(label_1);
				
				JLabel label_2 = new JLabel("Day");
				label_2.setBounds(247, 89, 61, 16);
				this.add(label_2);
				
				JComboBox comboBox_ED_Day = new JComboBox();
				comboBox_ED_Day.setBounds(281, 85, 95, 27);
				for (int i = 1; i <= 31; i++) {
					comboBox_ED_Day.addItem(Integer.toString(i));
				}
				this.add(comboBox_ED_Day);
				
				JComboBox comboBox_ED_Month = new JComboBox();
				comboBox_ED_Month.setBounds(140, 85, 95, 27);
				this.add(comboBox_ED_Month);
				
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
				this.add(lblOrganizer);
				
				JTextField organizer = new JTextField(20);
				organizer.setBounds(94, 28, 130, 26);
				this.add(organizer);
				
				// Location
				JLabel lblLocation = new JLabel("Location:");
				lblLocation.setBounds(16, 190, 70, 16);
				this.add(lblLocation);
				
				JTextField location = new JTextField(20);
				location.setBounds(94, 185, 282, 26);
				this.add(location);
				
				// Calendar Name
				JLabel lblCalendarName = new JLabel("Calendar Name:");
				lblCalendarName.setBounds(16, 11, 99, 16);
				this.add(lblCalendarName);
				
				JTextField calName = new JTextField(20);
				calName.setBounds(128, 6, 159, 26);
				this.add(calName);
				
				// Earliest Hour & Latest Hour
				JLabel label = new JLabel("Earliest hour:");
				label.setBounds(16, 121, 83, 16);
				this.add(label);
				
				JLabel label_3 = new JLabel("Hour");
				label_3.setBounds(104, 121, 40, 16);
				this.add(label_3);
				
				JComboBox comboBox_EH_Hour = new JComboBox();
				comboBox_EH_Hour.setBounds(140, 117, 95, 27);
				for (int i = 0; i <= 23; i++) {
					comboBox_EH_Hour.addItem(Integer.toString(i));
				}
				this.add(comboBox_EH_Hour);
				
				JLabel label_4 = new JLabel("Min");
				label_4.setBounds(247, 121, 61, 16);
				this.add(label_4);
				
				JComboBox comboBox_EH_Minute = new JComboBox();
				comboBox_EH_Minute.setBounds(281, 117, 95, 27);
				for (int i = 0; i <= 59; i++) {
					comboBox_EH_Minute.addItem(Integer.toString(i));
				}
				this.add(comboBox_EH_Minute);
				
				JLabel label_5 = new JLabel("Latest hour:");
				label_5.setBounds(16, 155, 95, 16);
				this.add(label_5);
				
				JLabel label_6 = new JLabel("Hour");
				label_6.setBounds(104, 155, 40, 16);
				this.add(label_6);
				
				JComboBox comboBox_LH_Hour = new JComboBox();
				comboBox_LH_Hour.setBounds(140, 151, 95, 27);
				for (int i = 0; i <= 23; i++) {
					comboBox_LH_Hour.addItem(Integer.toString(i));
				}
				this.add(comboBox_LH_Hour);
				
				JLabel label_7 = new JLabel("Min");
				label_7.setBounds(247, 155, 61, 16);
				this.add(label_7);
				
				JComboBox comboBox_LH_Minute = new JComboBox();
				comboBox_LH_Minute.setBounds(281, 151, 95, 27);
				for (int i = 0; i <= 59; i++) {
					comboBox_LH_Minute.addItem(Integer.toString(i));
				}
				this.add(comboBox_LH_Minute);
				
				// Duration
				JComboBox comboBox_duration = new JComboBox();
				comboBox_duration.setBounds(317, 29, 95, 27);
				comboBox_duration.addItem("10 min");
				comboBox_duration.addItem("15 min");
				comboBox_duration.addItem("20 min");
				comboBox_duration.addItem("30 min");
				comboBox_duration.addItem("60 min");
				this.add(comboBox_duration);
				
				JLabel lblDuration = new JLabel("Duration:");
				lblDuration.setBounds(247, 32, 83, 16);
				this.add(lblDuration);
				
				// Submit
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Parameters.setOrganizer(organizer.getText());
						Parameters.setLocation(location.getText());
						Parameters.setName(calName.getText());
						Parameters.setStartingDateMonth((String)comboBox_SD_Month.getSelectedItem());
						Parameters.setStartingDateDay((String)comboBox_SD_Day.getSelectedItem());
						Parameters.setEndingDateMonth((String)comboBox_ED_Month.getSelectedItem());
						Parameters.setEndingDateDay((String)comboBox_ED_Day.getSelectedItem());
						Parameters.setEarliestHourHour((String)comboBox_EH_Hour.getSelectedItem());
						Parameters.setEarliestHourMin((String)comboBox_EH_Minute.getSelectedItem());
						Parameters.setLatestHourHour((String)comboBox_LH_Hour.getSelectedItem());
						Parameters.setLatestHourMin((String)comboBox_LH_Minute.getSelectedItem());
						String duration = (String)comboBox_duration.getSelectedItem();
						String a = duration.substring(0, 2);
						Parameters.setDuration(Integer.parseInt(a));
						System.out.println(Parameters.myString());
						
						JPanel testPanel = TestPanel2.testPanel2;
						MainFrame.panel.removeAll();  
						MainFrame.panel.add(testPanel);  
						MainFrame.panel.validate();  
						
					}
				});
				btnSubmit.setBounds(192, 275, 95, 38);
				this.add(btnSubmit);
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

