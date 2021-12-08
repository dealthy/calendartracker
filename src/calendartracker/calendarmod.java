package calendartracker;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

//swing & awt

public class calendarmod extends JFrame {

	DefaultTableModel model; // week label
	Calendar cal = new GregorianCalendar(); // calendar resource
	JLabel tbanner; // title

	calendarmod() {

		// creating the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Your Personal Health Tracker");
		this.setSize(500, 300);
		this.setLayout(new BorderLayout());
		this.setVisible(true);

		// month one the calendar display
		tbanner = new JLabel();
		tbanner.setHorizontalAlignment(SwingConstants.CENTER);

		// go one month before
		JButton backmonth = new JButton("<-");
		backmonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});

		// go one month after
		JButton forwardmonth = new JButton("->");
		forwardmonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cal.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});

		// canvas
		JPanel yrmoninfo = new JPanel();
		yrmoninfo.setLayout(new BorderLayout());
		yrmoninfo.add(backmonth, BorderLayout.WEST);
		yrmoninfo.add(tbanner, BorderLayout.CENTER);
		yrmoninfo.add(forwardmonth, BorderLayout.EAST);

		// labels for week time
		String[] columns = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
		model = new DefaultTableModel(null, columns);
		JTable table = new JTable(model);
		JScrollPane datespane = new JScrollPane(table);

		///////////////////////////////
		// creating operational buttons
		///////////////////////////////

		// lead to page inputting new data
		JButton newinfo = new JButton("New Data");
		newinfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// trigger adding data window
			}
		});

		// year long data
		JButton yrdata = new JButton("Year Long Data");
		yrdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// display data for the entire year
			}
		});

		// show selected week
		JButton weekdata = new JButton("Show Selected Week");
		weekdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// show the activity data for the entire week
			}
		});

		// creating a pane for all the data
		JPanel opbnts = new JPanel();
		opbnts.setLayout(new BorderLayout());
		opbnts.add(newinfo, BorderLayout.WEST);
		opbnts.add(yrdata, BorderLayout.CENTER);
		opbnts.add(weekdata, BorderLayout.EAST);

		this.add(yrmoninfo, BorderLayout.NORTH);
		this.add(datespane, BorderLayout.CENTER);
		this.add(opbnts, BorderLayout.SOUTH);

		this.updateMonth();

	}

	// initiating the calendar
	void updateMonth() {

		cal.set(Calendar.DAY_OF_MONTH, 1);

		// top banner for month & year
		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
		int year = cal.get(Calendar.YEAR);
		tbanner.setText(month + " " + year);

		// gathering data for filling in table
		int startDay = cal.get(Calendar.DAY_OF_WEEK);
		int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

		// initiating the table for days of month
		model.setRowCount(0);
		model.setRowCount(weeks);

		// filling in the dates of month
		int i = startDay - 1;
		for (int day = 1; day <= numberOfDays; day++) {
			model.setValueAt(day, i / 7, i % 7);
			i = i + 1;
		}

	}

	/*
	 * void getActivityData() { model.get }
	 */

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					calendarmod frame = new calendarmod();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
