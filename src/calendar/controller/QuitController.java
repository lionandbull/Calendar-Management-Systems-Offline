package calendar.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class QuitController {
	public int confirm(JFrame app) {
		int c = JOptionPane.showConfirmDialog (app, "Do you want to save and exit?");

		if (c == JOptionPane.OK_OPTION) {
			return 0;
		}
		else if(c == JOptionPane.NO_OPTION) {
			return 1;
		}
		return 2;
	}
}