package calendar.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import calendar.gui.frame.CMABoard;
import calendar.gui.frame.MeetingFrameNoMeeting;
import calendar.gui.frame.Parameters;
import util.MyUtility;

public class CalendarPanel extends JPanel implements ActionListener{
//	public static CalendarPanel calendarPanel = new CalendarPanel();
		
		//
		private JLabel calendarRange = new JLabel("" ,JLabel.CENTER);
	//年份
		private JLabel YearLabel = new JLabel("Year:");
		public JComboBox YearBox = new JComboBox();
		public ActionListener yearAct;
		
		//月份
		private JLabel MonthLabel = new JLabel("Month:");
		private JComboBox MonthBox = new JComboBox();
		
		//查看
		JButton button_ok = new JButton("Go");
		
		//今天
		JButton button_today = new JButton("Today");
		
		//今天的日期,年份,月份
		private Date now_date = new Date();
		@SuppressWarnings("deprecation")
		private int now_year = now_date.getYear() + 1900;
		@SuppressWarnings("deprecation")
		private int now_month = now_date.getMonth();
		private boolean todayFlag = false;   //是否显示今天的日期
		
		//用一行来显示日期
		public JButton[] button_day = new JButton[42];
		private final String[] week = {"S", "M", "T", "W", "Th", "F", "Sat"};
		private JButton[] button_week = new JButton[7];
		
		//用户选择年份和月份
		private String year_int = null;
		private int month_int;	
		
		//
		private boolean new_flag = false;
		private int startYear_;
		private int endYear_;
		private int startMonth_;
		private int startDate_;
		private int endMonth_;
		private int endDate_;
	/**
	 * Create the panel.
	 */
		
		public CalendarPanel() {
			Font font = new Font("Dialog", Font.BOLD, 14);
			calendarRange.setFont(font);
			YearLabel.setFont(font);
			MonthLabel.setFont(font);
			button_ok.setFont(font);
			button_today.setFont(font);
			
			//当前年份前10年和未来20年时间区间
			for(int i = now_year - 10; i <= now_year + 7; i++){
				YearBox.addItem(i + "");
			}
			YearBox.setSelectedIndex(10);
			
			//12个月的月份区间
			for(int i = 1; i < 13; i++){
				MonthBox.addItem(i + "");
			}
			MonthBox.setSelectedIndex(now_month);
			
			String startDate = (String)YearBox.getItemAt(0) + "-01-01";
			String endDate = (String)YearBox.getItemAt((int)YearBox.getItemCount() - 1) + "-12-31";
			calendarRange.setText(MyUtility.CalendarRange(startDate, endDate));
			
			//年份,月份,查看,今天
			JPanel panel_board = new JPanel();
			panel_board.setLayout(new BorderLayout());
			
			JPanel panel_ym = new JPanel();
			panel_ym.add(YearLabel);
			panel_ym.add(YearBox);
			panel_ym.add(MonthLabel);
			panel_ym.add(MonthBox);
			panel_ym.add(button_ok);
//			panel_ym.add(button_today);
			button_ok.addActionListener(this);
			button_today.addActionListener(this);
			
			panel_board.add(panel_ym, BorderLayout.CENTER);
			panel_board.add(calendarRange, BorderLayout.NORTH);
			
			
			JPanel panel_day = new JPanel();
			panel_day.setLayout(new GridLayout(7, 7, 3, 3));
			for(int i = 0; i < 7; i++){
				button_week[i] = new JButton(week[i]);
				button_week[i].setForeground(Color.black);
				panel_day.add(button_week[i]);
			}
			button_week[0].setForeground(Color.red);
			button_week[6].setForeground(Color.red);
			
			//1,2,3..30等日
			for(int i = 0; i < 42; i++){
				button_day[i] = new JButton(" ");
				panel_day.add(button_day[i]);
			}
			printDay();


			//
			setLayout(new BorderLayout());
			add(panel_board, BorderLayout.NORTH);
			add(panel_day, BorderLayout.CENTER);
			
			
		}

		public void setCalendarPanel(int startYear, int endYear, int startMonth, int endMonth, int startDate, int endDate) {
			new_flag = true;
			startYear_ = startYear;
			startMonth_ = startMonth;
			startDate_ = startDate;
			endYear_ = endYear; 
			endMonth_ = endMonth;
			endDate_ = endDate;
//			System.out.println(startYear_);
//			System.out.println(startMonth_);
//			System.out.println(startDate_);
//			System.out.println(endYear_);
//			System.out.println(endMonth_);
//			System.out.println(endDate_);
			for (int i = 0; i < 42; i++) {
				button_day[i].removeActionListener(this);
			}
			String start = Integer.toString(startYear) + "-" + Integer.toString(startMonth) + "-" + Integer.toString(startDate);
			String end = Integer.toString(endYear) + "-" + Integer.toString(endMonth) + "-" + Integer.toString(endDate);
			calendarRange.setText(MyUtility.CalendarRange(start, end));
			
			//Starting year and ending year
			YearBox.removeAllItems();
			for(int i = startYear; i <= endYear; i++){
				YearBox.addItem(i + "");
			}
			yearAct = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox cb = (JComboBox)e.getSource();
					if (startYear != endYear) {
						if (((String)cb.getSelectedItem()).equals((String)Integer.toString(startYear))) {
							MonthBox.removeAllItems();
							for(int i = startMonth; i < 13; i++){
								MonthBox.addItem(i + "");
							}
							MonthBox.setSelectedIndex(0);
						}
						else if (((String)cb.getSelectedItem()).equals((String)Integer.toString(endYear))) {
							MonthBox.removeAllItems();
							for(int i = 1; i < endMonth + 1; i++){
								MonthBox.addItem(i + "");
							}
							MonthBox.setSelectedIndex(0);
						}
						else {
							MonthBox.removeAllItems();
							for(int i = 1; i < 13; i++){
								MonthBox.addItem(i + "");
							}
							MonthBox.setSelectedIndex(0);
						}
					}
					else {
						MonthBox.removeAllItems();
						for(int i = startMonth; i < endMonth + 1; i++){
							MonthBox.addItem(i + "");
						}
						MonthBox.setSelectedIndex(0);
					}	
				}
			};
			YearBox.addActionListener(yearAct);
			YearBox.setSelectedIndex(0);
			
			
			int monthSelected = Integer.parseInt((String)MonthBox.getSelectedItem());
			int year_selected = Integer.parseInt((String)YearBox.getSelectedItem());
			
			if (startYear == endYear) {
				if (monthSelected == startMonth && monthSelected != endMonth) {
					printDayStart(startDate);
				}
				else if (monthSelected == endMonth && monthSelected != startMonth) {
					printDayEnd(endDate);
				}
				else if (monthSelected == endMonth && monthSelected == startMonth) {
					printDayStartEnd(startDate, endDate);
				}
				else {
					printDay_2();
				}
			}
			else {
				if (year_selected == startYear && monthSelected == startMonth) {
					printDayStart(startDate);
				}
				else if (year_selected == endYear && monthSelected == endMonth) {
					printDayEnd(endDate);
				}
				else {
					printDay_2();
				}
			}
			
		
		}
		
		private void printDay(){
			clearBtns();
			
			if(todayFlag){
				year_int = now_year + "";
				month_int = now_month;
			}else{
				year_int = YearBox.getSelectedItem().toString();
				month_int = MonthBox.getSelectedIndex();
			}
			System.out.println(month_int);
			int year_sel = Integer.parseInt(year_int) - 1900;
			
			/**Date构造函数
			 *@param   year    the year minus 1900.
		     *@param   month   the month between 0-11.
		     *@param   date    the day of the month between 1-31.
			 */
			Date firstDay = new Date(year_sel, month_int, 1);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(firstDay);
			
			//
			int days = 0; //本月有多少天
			int day_week = firstDay.getDay();  //星期几
			if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 || month_int == 7 || month_int == 9 || month_int == 11 ){
				days = 31;
			}else if(month_int == 3 || month_int == 5|| month_int == 8 || month_int == 10 ){
				days = 30;
			}else{
				if(cal.isLeapYear(year_sel)){
					days = 29;
				}else{
					days = 28;
				}
			}
			
			//根据选定月份第一天是星期几来确定按钮的绘制位置 day_week就是我们绘制的起始位置。
			int count = 1;
			
			for(int i = day_week; i < day_week + days; count++, i++){
				if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){  //6+31 = 37
					if(i == day_week + now_date.getDate() - 1){
						button_day[i].setForeground(Color.blue);
						button_day[i].setText(count + "");
					}else{
						button_day[i].setForeground(Color.red);
						button_day[i].setText(count + "");
					}
				}else{
						button_day[i].setForeground(Color.black);
						button_day[i].setText(count + "");
					
				}
			}
			
			//
			if(day_week == 0){
				for(int i = days; i < 42; i++){
					button_day[i].setText("");
				}
			}else{
				//第一天不是周日,则将第一天前面的按钮置空
				for(int i = 0; i < day_week; i++){
					button_day[i].setText("");
				}
				//最后一天后面的按钮置空
				for(int i = day_week + days; i < 42; i++){
					button_day[i].setText("");
				}
			}
		}
		
		private void printDay_2(){
			clearBtns();
			
			if(todayFlag){
				year_int = now_year + "";
				month_int = now_month;
			}else{
				year_int = YearBox.getSelectedItem().toString();
				month_int = Integer.parseInt((String)MonthBox.getSelectedItem());
			}
			System.out.println(month_int);
			int year_sel = Integer.parseInt(year_int) - 1900;
			
			/**Date构造函数
			 *@param   year    the year minus 1900.
		     *@param   month   the month between 0-11.
		     *@param   date    the day of the month between 1-31.
			 */
			Date firstDay = new Date(year_sel, month_int - 1, 1);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(firstDay);
			
			//
			int days = 0; //本月有多少天
			int day_week = firstDay.getDay();  //星期几
			if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 || month_int == 7 || month_int == 9 || month_int == 11 ){
				days = 31;
			}else if(month_int == 3 || month_int == 5|| month_int == 8 || month_int == 10 ){
				days = 30;
			}else{
				if(cal.isLeapYear(year_sel)){
					days = 29;
				}else{
					days = 28;
				}
			}
			
			//根据选定月份第一天是星期几来确定按钮的绘制位置 day_week就是我们绘制的起始位置。
			int count = 1;
			
			for(int i = day_week; i < day_week + days; count++, i++){
				if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){  //6+31 = 37
					if(i == day_week + now_date.getDate() - 1){
						button_day[i].setForeground(Color.blue);
						button_day[i].setText(count + "");
					}else{
						button_day[i].setForeground(Color.red);
						button_day[i].setText(count + "");
					}
				}else{
					
						button_day[i].setForeground(Color.black);
						button_day[i].setText(count + "");
					
				}
			}
			
			//
			if(day_week == 0){
				for(int i = days; i < 42; i++){
					button_day[i].setText("");
				}
			}else{
				//第一天不是周日,则将第一天前面的按钮置空
				for(int i = 0; i < day_week; i++){
					button_day[i].setText("");
				}
				//最后一天后面的按钮置空
				for(int i = day_week + days; i < 42; i++){
					button_day[i].setText("");
				}
			}
			//
			for (int i = 0; i < 42; i++) {
				if (button_day[i].getText() != "") {
					button_day[i].addActionListener(this);
				}
			}
		}
		
		private void printDayStart(int startDate) {
			clearBtns();
			
			if(todayFlag){
				year_int = now_year + "";
				month_int = now_month;
			}else{
				year_int = YearBox.getSelectedItem().toString();
				month_int = Integer.parseInt((String)MonthBox.getSelectedItem());
			}
			
			int year_sel = Integer.parseInt(year_int) - 1900;
			
			/**Date构造函数
			 *@param   year    the year minus 1900.
		     *@param   month   the month between 0-11.
		     *@param   date    the day of the month between 1-31.
			 */
			Date firstDay = new Date(year_sel, month_int - 1, 1);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(firstDay);
			
			//
			int days = 0; //本月有多少天
			int day_week = firstDay.getDay();  //星期几
			if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 || month_int == 7 || month_int == 9 || month_int == 11 ){
				days = 31;
			}else if(month_int == 3 || month_int == 5|| month_int == 8 || month_int == 10 ){
				days = 30;
			}else{
				if(cal.isLeapYear(year_sel)){
					days = 29;
				}else{
					days = 28;
				}
			}
			
			//根据选定月份第一天是星期几来确定按钮的绘制位置 day_week就是我们绘制的起始位置。
			int count = 1;
			
			for(int i = day_week; i < day_week + days; count++, i++){
				if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){  //6+31 = 37
					if(i == day_week + now_date.getDate() - 1){
						button_day[i].setForeground(Color.blue);
						button_day[i].setText(count + "");
					}else{
						button_day[i].setForeground(Color.red);
						button_day[i].setText(count + "");
					}
				}else{
//					if(i == day_week + now_date.getDate() - 1){
//						button_day[i].setForeground(Color.blue);
//						button_day[i].setText(count + "");

						button_day[i].setForeground(Color.black);
						button_day[i].setText(count + "");
					
				}
			}
			
			//
			if(day_week == 0){
				for(int i = days; i < 42; i++){
					button_day[i].setText("");
				}
			}else{
				//第一天不是周日,则将第一天前面的按钮置空
				for(int i = 0; i < day_week; i++){
					button_day[i].setText("");
				}
				//最后一天后面的按钮置空
				for(int i = day_week + days; i < 42; i++){
					button_day[i].setText("");
				}
			}
			for(int i = 0; i < day_week + startDate - 1; i++){
				button_day[i].setText("");
			}
			
			// Add listener
						for (int i = 0; i < 42; i++) {
							if (button_day[i].getText() != "") {
								button_day[i].addActionListener(this);
							}
						}
		}

		private void printDayEnd(int endDate) {
			clearBtns();
			
			if(todayFlag){
				year_int = now_year + "";
				month_int = now_month;
			}else{
				year_int = YearBox.getSelectedItem().toString();
				month_int = Integer.parseInt((String)MonthBox.getSelectedItem());
			}
			
			int year_sel = Integer.parseInt(year_int) - 1900;
			
			/**Date构造函数
			 *@param   year    the year minus 1900.
		     *@param   month   the month between 0-11.
		     *@param   date    the day of the month between 1-31.
			 */
			Date firstDay = new Date(year_sel, month_int - 1, 1);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(firstDay);
			
			//
			int days = 0; //本月有多少天
			int day_week = firstDay.getDay();  //星期几
			if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 || month_int == 7 || month_int == 9 || month_int == 11 ){
				days = 31;
			}else if(month_int == 3 || month_int == 5|| month_int == 8 || month_int == 10 ){
				days = 30;
			}else{
				if(cal.isLeapYear(year_sel)){
					days = 29;
				}else{
					days = 28;
				}
			}
			
			//根据选定月份第一天是星期几来确定按钮的绘制位置 day_week就是我们绘制的起始位置。
			int count = 1;
			
			for(int i = day_week; i < day_week + days; count++, i++){
				if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){  //6+31 = 37
					if(i == day_week + now_date.getDate() - 1){
						button_day[i].setForeground(Color.blue);
						button_day[i].setText(count + "");
					}else{
						button_day[i].setForeground(Color.red);
						button_day[i].setText(count + "");
					}
				}else{
//					if(i == day_week + now_date.getDate() - 1){
//						button_day[i].setForeground(Color.blue);
//						button_day[i].setText(count + "");
					
						button_day[i].setForeground(Color.black);
						button_day[i].setText(count + "");
					
				}
			}
			
			//
			if(day_week == 0){
				for(int i = days; i < 42; i++){
					button_day[i].setText("");
				}
			}else{
				//第一天不是周日,则将第一天前面的按钮置空
				for(int i = 0; i < day_week; i++){
					button_day[i].setText("");
				}
				//最后一天后面的按钮置空
				for(int i = day_week + days; i < 42; i++){
					button_day[i].setText("");
				}
			}
			for(int i = day_week + endDate; i < 42; i++){
				button_day[i].setText("");
			}
			
			// Add listener
			for (int i = 0; i < 42; i++) {
				if (button_day[i].getText() != "") {
					button_day[i].addActionListener(this);
				}
			}
		}

		private void printDayStartEnd(int startDate, int endDate) {
			clearBtns();
			
			if(todayFlag){
				year_int = now_year + "";
				month_int = now_month;
			}else{
				year_int = YearBox.getSelectedItem().toString();
				month_int = Integer.parseInt((String)MonthBox.getSelectedItem());
			}
			
			int year_sel = Integer.parseInt(year_int) - 1900;
			
			/**Date构造函数
			 *@param   year    the year minus 1900.
		     *@param   month   the month between 0-11.
		     *@param   date    the day of the month between 1-31.
			 */
			Date firstDay = new Date(year_sel, month_int - 1, 1);
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(firstDay);
			
			//
			int days = 0; //本月有多少天
			int day_week = firstDay.getDay();  //星期几
			if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 || month_int == 7 || month_int == 9 || month_int == 11 ){
				days = 31;
			}else if(month_int == 3 || month_int == 5|| month_int == 8 || month_int == 10 ){
				days = 30;
			}else{
				if(cal.isLeapYear(year_sel)){
					days = 29;
				}else{
					days = 28;
				}
			}
			
			//根据选定月份第一天是星期几来确定按钮的绘制位置 day_week就是我们绘制的起始位置。
			int count = 1;
			
			for(int i = day_week; i < day_week + days; count++, i++){
				if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){  //6+31 = 37
					if(i == day_week + now_date.getDate() - 1){
						button_day[i].setForeground(Color.blue);
						button_day[i].setText(count + "");
					}else{
						button_day[i].setForeground(Color.red);
						button_day[i].setText(count + "");
					}
				}else{
//					if(i == day_week + now_date.getDate() - 1){
//						button_day[i].setForeground(Color.blue);
//						button_day[i].setText(count + "");
					
						button_day[i].setForeground(Color.black);
						button_day[i].setText(count + "");
					
				}
			}
			
			//
			if(day_week == 0){
				for(int i = days; i < 42; i++){
					button_day[i].setText("");
				}
			}else{
				//第一天不是周日,则将第一天前面的按钮置空
				for(int i = 0; i < day_week; i++){
					button_day[i].setText("");
				}
				//最后一天后面的按钮置空
				for(int i = day_week + days; i < 42; i++){
					button_day[i].setText("");
				}
			}
			for(int i = day_week + endDate; i < 42; i++){
				button_day[i].setText("");
			}
			for(int i = 0; i < day_week + startDate - 1; i++){
				button_day[i].setText("");
			}
			// Add listener
			for (int i = 0; i < 42; i++) {
				if (button_day[i].getText() != "") {
					button_day[i].addActionListener(this);
				}
			}
		}
		
		private void clearBtns(){
			for(int i = 0; i < 42; i++){
				button_day[i].setText("");
			}
		}
		
		//----->按钮监听函数
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button_ok){
				todayFlag = false;
				for (int i = 0; i < 42; i++) {
					button_day[i].removeActionListener(this);
				}
				if (new_flag == false) {
					printDay();
				}
				else {
					int monthSelected = Integer.parseInt((String)MonthBox.getSelectedItem());
					int year_selected = Integer.parseInt((String)YearBox.getSelectedItem());
					if (startYear_ == endYear_) {
						if (monthSelected == startMonth_ && monthSelected != endMonth_) {
							printDayStart(startDate_);
						}
						else if (monthSelected == endMonth_ && monthSelected != startMonth_) {
							printDayEnd(endDate_);
						}
						else if (monthSelected == endMonth_ && monthSelected == startMonth_) {
							printDayStartEnd(startDate_, endDate_);
						}
						else {
							printDay_2();
						}
					}
					else {
						if (year_selected == startYear_ && monthSelected == startMonth_) {
							printDayStart(startDate_);
						}
						else if (year_selected == endYear_ && monthSelected == endMonth_) {
							printDayEnd(endDate_);
						}
						else {
							printDay_2();
						}
					}
				}
				
				
				
			}
			else if(e.getSource() == button_today){
				todayFlag = true;
				YearBox.setSelectedIndex(10);
				MonthBox.setSelectedIndex(now_month);
				printDay();
			}	
			else {
				String year = (String) YearBox.getSelectedItem();;
				String month = (String) MonthBox.getSelectedItem();
				String day = e.getActionCommand();
				String nowDate = year + "/" + month + "/" + day;
//				String meetingDate = year + "/" + month + "/" + day;
//				if (CMABoard.myCalendar.meetingsString.contains(meetingDate)) {
//					Object[] options = {"Set Unavailable", "Update Existing Meeting(s)", "Schedule A Meeting"};
//					int response = JOptionPane.showOptionDialog(this, 
//							"This date has meeting(s).", 
//							"Please choose an option",
//							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
//					// Set unavailable
//					if(response==0)
//					{ 
//						System.out.println(0);
//					}
//					// Schedule a meeting
//					else if(response==1)
//					{ 
//						new MeetingFrameNoMeeting(year, month, day);
//					}
//				}
				if (CMABoard.myCalendar.availableDates.get(nowDate).available == true) {
					Object[] options = {"Set Unavailable", "Schedule A Meeting"};
					int response = JOptionPane.showOptionDialog(this, 
							"This date is available.", 
							"Please choose an option",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					// Set unavailable
					if(response==0)
					{ 
						CMABoard.myCalendar.availableDates.get(nowDate).available = false;
					}
					// Schedule a meeting
					else if(response==1)
					{ 
						new MeetingFrameNoMeeting(year, month, day);
						
					}
				}
				else {
					Object[] options = {"No", "Yes"};
					int response = JOptionPane.showOptionDialog(this, 
							"This date: " + nowDate +" is not available, do you want to set it to be available?", 
							"Warning",
							JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if(response==0)
					{ 
						
					}
					else if(response==1)
					{ 
						CMABoard.myCalendar.availableDates.get(nowDate).available = true;
					}
				}
				
				
				
			}
		}

}
