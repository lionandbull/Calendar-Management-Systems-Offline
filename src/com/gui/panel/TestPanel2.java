package com.gui.panel;

import javax.swing.JPanel;


import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JSplitPane;

public class TestPanel2 extends JPanel {
	public static TestPanel2 testPanel2 = new TestPanel2();
	public static String a = "Test";
	/**
	 * Create the panel.
	 */
	public TestPanel2() {
		setLayout(new BorderLayout());
		
		
		JButton btnNewButton_1 = new JButton("New button");
		add(btnNewButton_1, BorderLayout.CENTER);
		
		JButton btnNewButton_2 = new JButton("New button");
		add(btnNewButton_2, BorderLayout.EAST);
		
		JButton btnNewButton_3 = new JButton("New button");
		add(btnNewButton_3, BorderLayout.NORTH);
		
		JButton btnNewButton_4 = new JButton("New button");
		add(btnNewButton_4, BorderLayout.SOUTH);
		
		

	}

}
