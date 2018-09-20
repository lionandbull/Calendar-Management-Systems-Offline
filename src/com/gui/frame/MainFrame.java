package com.gui.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gui.panel.*;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	public static MainFrame mainFrame = new MainFrame();
	public static JPanel panel = MyCalendar.myCalendar;  
	public PanelAction panelAction = new PanelAction();
	public JToolBar toolBar;  
	
	public JToolBar buildToolBar() {  
		if (toolBar == null) {  
		toolBar = new JToolBar();  
		toolBar.setLayout(new FlowLayout());  
		toolBar.add(buildButton("New calendar"));  
		toolBar.add(buildButton("Load calendar"));  
		toolBar.setEnabled(false); // 设置toolBar的可移动性  
		}  
		return toolBar;  
		}  
	
	public JPanel buildPanel(String title) {  
		if (panel == null) {  
		panel = new JPanel();  
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
		String num = e.getActionCommand();  
		System.out.println(num);
		new CMABoard();
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
		this.setTitle("CMABoard");
		this.setSize(550, 500);
		this.add(buildToolBar(), BorderLayout.NORTH);  
		this.add(buildPanel("Calendar"), BorderLayout.CENTER); 
//		this.setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
