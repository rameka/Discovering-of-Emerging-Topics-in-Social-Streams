package com.mycompany.design;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jfree.ui.RefineryUtilities;
import org.jvnet.substance.SubstanceLookAndFeel;

import com.mycompany.logic.TwitterRestCall;

public class MainForm implements ActionListener {
	private JButton jbuttonTrends, jbuttonTraining, jbuttonAggregation,
			jButtonChangePoint, jbuttonBurstAnalysis;
	private TwitterRestCall twitterRestCall;
	public static DefaultTableModel defaultTableModel, defaultTableModelUser,
			defaultTableTime;
	private JTable jTable, jTableUser, jTableTime;
	private JScrollPane jScrollPane, jScrollPaneUser, jScroPaneTime;
	public static JComboBox<String> jComboBox;
	public static JRadioButton jRadioButtonkey;
	public static JRadioButton jRadioButtonLink;
	static {
		try {
			SubstanceLookAndFeel
					.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceBinaryWatermark");
			SubstanceLookAndFeel
					.setCurrentTheme("org.jvnet.substance.theme.SubstanceInvertedTheme");
			SubstanceLookAndFeel
					.setCurrentGradientPainter("org.jvnet.substance.painter.SpecularGradientPainter");
			SubstanceLookAndFeel
					.setCurrentButtonShaper("org.jvnet.substance.button.ClassicButtonShaper");
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void design() {

		twitterRestCall = new TwitterRestCall();
		JFrame frame = new JFrame("Discovering Emerging Topics");
		// frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTabbedPane tab = new JTabbedPane();
		frame.add(tab, BorderLayout.CENTER);
		frame.add(createPanelLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = frame.getSize();
		screenSize.height = screenSize.height / 2;
		screenSize.width = screenSize.width / 2;
		size.height = size.height / 2;
		size.width = size.width / 2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		// frame.setLocation(x, y);
		frame.setSize(1000, 800);
		frame.setVisible(true);
	}

	public JPanel createPanelLayout() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(null);
		jComboBox = new JComboBox<String>();
		jComboBox.setBounds(10, 30, 150, 30);
		jPanel.add(jComboBox);

		jRadioButtonkey = new JRadioButton("Key based Detection", true);
		jRadioButtonkey.addActionListener(this);

		jRadioButtonLink = new JRadioButton("Link based Detection");
		jRadioButtonLink.addActionListener(this);
		jRadioButtonkey.setBounds(10, 80, 150, 30);
		jRadioButtonLink.setBounds(200, 80, 200, 30);
		jbuttonTrends = new JButton("Twitter Trends");
		jbuttonTrends.setBounds(10, 160, 100, 30);
		jbuttonTrends.addActionListener(this);
		jbuttonTraining = new JButton("Perform Training");
		jbuttonTraining.setBounds(120, 160, 140, 30);
		jbuttonTraining.addActionListener(this);
		jbuttonAggregation = new JButton("Aggregation");
		jbuttonAggregation.setBounds(270, 160, 140, 30);
		jbuttonAggregation.addActionListener(this);
		jButtonChangePoint = new JButton("Change Point Analysis");
		jButtonChangePoint.setBounds(10, 230, 160, 30);
		jButtonChangePoint.addActionListener(this);

		jbuttonBurstAnalysis = new JButton("Burst Analysis");
		jbuttonBurstAnalysis.setBounds(270, 230, 160, 30);
		jbuttonBurstAnalysis.addActionListener(this);
		jPanel.add(jbuttonBurstAnalysis);

		jPanel.add(jbuttonTrends);
		jPanel.add(jbuttonTraining);
		jPanel.add(jbuttonAggregation);
		jPanel.add(jButtonChangePoint);
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("Trends Name");
		defaultTableModel.addColumn("URI");
		defaultTableModel.addColumn("In Time");
		defaultTableModel.addColumn("Out Time");
		defaultTableModel.addColumn("Retweet Count");
		jTable = new JTable(defaultTableModel);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		jScrollPane = new JScrollPane(jTable);
		jScrollPane.setBounds(10, 470, 850, 200);

		defaultTableModelUser = new DefaultTableModel();
		defaultTableModelUser.addColumn("Id");
		defaultTableModelUser.addColumn("User Name");
		defaultTableModelUser.addColumn("Screen Name");
		defaultTableModelUser.addColumn("Retweet");
		jTableUser = new JTable(defaultTableModelUser);
		jScrollPaneUser = new JScrollPane(jTableUser);
		jScrollPaneUser.setBounds(430, 30, 400, 200);

		defaultTableTime = new DefaultTableModel();
		defaultTableTime.addColumn("In Time");
		defaultTableTime.addColumn("out Time");
		defaultTableTime.addColumn("Diff");
		defaultTableTime.addColumn("Total Count");
		jTableTime = new JTable(defaultTableTime);
		jScroPaneTime = new JScrollPane(jTableTime);
		jScroPaneTime.setBounds(430, 250, 400, 200);

		jPanel.add(jRadioButtonkey);
		jPanel.add(jRadioButtonLink);
		jPanel.add(jScrollPane);
		jPanel.add(jScrollPaneUser);
		jPanel.add(jScroPaneTime);
		return jPanel;
	}

	public static void main(String[] args) {
		MainForm mainForm = new MainForm();
		mainForm.design();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jbuttonTrends) {
			twitterRestCall.getTrends();
		} else if (e.getSource() == jRadioButtonLink) {
			jComboBox.removeAllItems();
			defaultTableModel.setRowCount(0);
			defaultTableModelUser.setRowCount(0);
			jRadioButtonLink.setSelected(true);
			jRadioButtonkey.setSelected(false);
		} else if (e.getSource() == jRadioButtonkey) {
			jRadioButtonLink.setSelected(false);
			jRadioButtonkey.setSelected(true);
		} else if (e.getSource() == jbuttonTraining) {
			String trendName = jComboBox.getSelectedItem().toString();
			twitterRestCall.getTweetSearch(trendName);
		} else if (e.getSource() == jbuttonAggregation) {
			twitterRestCall.getAggregation();
		} else if (e.getSource() == jButtonChangePoint) {
			int rowCount = defaultTableModel.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				String trendName = defaultTableModel.getValueAt(i, 0)
						.toString();
				System.out.println(trendName);
				if (jComboBox.getSelectedItem().toString().equals(trendName)) {
					String inTime = defaultTableModel.getValueAt(i, 2)
							.toString();
					String outTime = defaultTableModel.getValueAt(i, 3)
							.toString();
					long inMilli = convertStringtoDate(inTime);
					long outMilli = convertStringtoDate(outTime);
					long diff = (outMilli - inMilli);
					Vector<String> rowData = new Vector<String>();
					rowData.add(String.valueOf(inMilli));
					rowData.add(String.valueOf(outMilli));
					rowData.add(String.valueOf(diff));
					if (jRadioButtonLink.isSelected()) {
						String retweet_count = defaultTableModel.getValueAt(i,
								4).toString();
						rowData.add(retweet_count);
					} else {
						rowData.add(String.valueOf(100));
					}

					defaultTableTime.addRow(rowData);
				}
			}
		} else if (e.getSource() == jbuttonBurstAnalysis) {
			try
			{
				BarChart barChart=new BarChart("Burst Detection");
				barChart.pack();
				RefineryUtilities.centerFrameOnScreen(barChart);
				barChart.setVisible(true);
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
			
		}
	}

	public long convertStringtoDate(String time) {
		String[] inSplitter = time.split(" ");
		Calendar calendar = Calendar.getInstance();
		String getInMonth = inSplitter[1].toUpperCase();
		int month = 0;
		int date = Integer.parseInt(inSplitter[2]);
		if (getInMonth.contains("JAN")) {
			month = Calendar.JANUARY;
		} else if (getInMonth.contains("FEB")) {
			month = Calendar.FEBRUARY;
		} else if (getInMonth.contains("MAR")) {
			month = Calendar.MARCH;
		} else if (getInMonth.contains("APR")) {
			month = Calendar.APRIL;
		} else if (getInMonth.contains("MAY")) {
			month = Calendar.MAY;
		} else if (getInMonth.contains("JUN")) {
			month = Calendar.JUNE;
		} else if (getInMonth.contains("JUL")) {
			month = Calendar.JULY;
		} else if (getInMonth.contains("AUG")) {
			month = Calendar.AUGUST;
		} else if (getInMonth.contains("SEP")) {
			month = Calendar.SEPTEMBER;
		} else if (getInMonth.contains("OCT")) {
			month = Calendar.OCTOBER;
		} else if (getInMonth.contains("NOV")) {
			month = Calendar.NOVEMBER;
		} else if (getInMonth.contains("DEC")) {
			month = Calendar.DECEMBER;
		}
		String timeStamp = inSplitter[3];
		calendar.set(2014, month, date);
		int hour = Integer.parseInt(timeStamp.split(":")[0]);
		int minutes = Integer.parseInt(timeStamp.split(":")[1]);
		int secs = Integer.parseInt(timeStamp.split(":")[2]);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minutes);
		calendar.set(Calendar.SECOND, secs);
		System.out.println(calendar.getTime());
		return calendar.getTimeInMillis();
	}
}
