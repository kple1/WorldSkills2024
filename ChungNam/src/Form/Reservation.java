package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Frame;
import Util.DB;

public class Reservation extends Frame implements ActionListener {
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public JPanel panel;
	public JLabel l1;
	public JPanel panel_1;
	public JButton reservation;
	public JPanel grid;
	public JPanel output;
	public JPanel input;
	public JPanel west1;
	public JPanel center1;
	public static JTextField t2;
	public static JTextField t1;
	public static JComboBox c4;
	public static JComboBox c1;
	public static JComboBox c2;
	public static JComboBox c3;
	public static JTextField t4;
	public static JTextField t3;
	public JPanel east1;
	public JPanel panel_3;
	public JPanel panel_2;
	public JLabel l2;
	public JPanel west2;
	public static JPanel inputData;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Reservation().setVisible(true);
	}

	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose) new Main("user").setVisible(true);
	}
	public Reservation() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setFrame("예약", 500, 800);
		setSize(479, 760);

		horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		l1 = new JLabel("Reservation");
		l1.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		panel.add(l1, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		reservation = new JButton("\uC608\uC57D");
		panel_1.add(reservation);
		
		grid = new JPanel();
		grid.setBackground(new Color(255, 255, 255));
		panel.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(2, 1, 0, 10));
		
		input = new JPanel();
		input.setBackground(new Color(255, 255, 255));
		grid.add(input);
		input.setLayout(new BorderLayout(0, 0));
		
		west1 = new JPanel();
		west1.setBackground(new Color(255, 255, 255));
		input.add(west1, BorderLayout.WEST);
		west1.setLayout(new GridLayout(8, 1, 0, 10));
		
		east1 = new JPanel();
		east1.setBackground(new Color(255, 255, 255));
		input.add(east1, BorderLayout.EAST);
		String[] ps = new String[] {null, "Date", null, null, "Traval", null, null, "Seat"};
		east1.setLayout(new GridLayout(8, 1, 0, 10));
		for (int i = 0; i < 8; i++) {
			int capture = i;
			var label = new JLabel();
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (capture == 1) {
						new SelectDate("Reservation").setVisible(true);
					} else if (capture == 4) {
						String im1 = "datafiles/CountryImage/" + (c2.getSelectedIndex() + 1) + ".jpg";
						String im2 = "datafiles/TravelImage/" + (c3.getSelectedIndex() + 1) + ".jpg";
						String n1 = c2.getSelectedItem().toString();
						String n2 = c3.getSelectedItem().toString();
						
						int getTime = DB.getInt("select minute from travel where tname = ?", n2);
						String m1 = String.format("%d시간 (%d분)", getTime / 60, getTime);
						
						String exp = DB.getString("select explanation from travel where tname = ?", n2);
						new TravelGuide(im1, im2, n1, n2, m1, String.valueOf(getTime), exp, "Reserva").setVisible(true);
					} else if (capture == 7) {
						new SelectSeat("1").setVisible(true);
					}
				}
			});
			label.setSize(30, 30);
			label.setIcon(imageSize(new ImageIcon("datafiles/" + ps[i] + ".png"), label));
			east1.add(label);
		}
		
		center1 = new JPanel();
		center1.setBackground(new Color(255, 255, 255));
		input.add(center1, BorderLayout.CENTER);
		center1.setLayout(new GridLayout(8, 1, 0, 10));
		
		t1 = new JTextField();
		t1.setText(DB.getString("select ename from user where uno = ?", Login.no));
		t1.setEnabled(false);
		center1.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setEnabled(false);
		center1.add(t2);
		t2.setColumns(10);
		
		c1 = new JComboBox();
		center1.add(c1);
		
		c2 = new JComboBox();
		center1.add(c2);
		
		c3 = new JComboBox();
		center1.add(c3);
		
		c4 = new JComboBox();
		center1.add(c4);
		
		t3 = new JTextField();
		t3.setEnabled(false);
		center1.add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		t4.setEnabled(false);
		center1.add(t4);
		t4.setColumns(10);
		
		
		String[] ns1 = new String[] {"User name", "DepartureDate", "Continent", "Country", "Travel", "DepartureTime", "Gate", "Seat"};
		for (int i = 0; i < ns1.length; i++) {
			var label = new JLabel(ns1[i]);
			setBold(16, label);
			west1.add(label);
		}
		
		output = new JPanel();
		output.setBorder(BorderFactory.createLineBorder(Color.black));
		output.setBackground(new Color(255, 255, 255));
		grid.add(output);
		output.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		output.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		var line = new JPanel();
		setSize(0, 1, line);
		line.setBackground(Color.cyan);
		panel_2.add(line, BorderLayout.SOUTH);
		
		l2 = new JLabel("Flight Schedule");
		l2.setBackground(new Color(255, 255, 255));
		l2.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		panel_2.add(l2, BorderLayout.NORTH);
		
		west2 = new JPanel();
		west2.setPreferredSize(new Dimension(150, 0));
		west2.setBackground(new Color(255, 255, 255));
		output.add(west2, BorderLayout.WEST);
		west2.setLayout(new GridLayout(8, 1, 0, 10));
		
		inputData = new JPanel();
		inputData.setBackground(new Color(255, 255, 255));
		output.add(inputData, BorderLayout.CENTER);
		inputData.setLayout(new GridLayout(8, 1, 0, 10));
		
		String[] ns2 = new String[] {"출발지", "도착지", "", "출발일", "출발시간", "도착시간", "금액", "게이트"};
		for (int i = 0; i < ns2.length; i++) {
			var label = new JLabel(ns2[i]);
			setBold(16, label);
			west2.add(label);
		}
		
		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);
		reservation.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(c1)) {
			c2.removeAllItems();
			var list = DB.getContry(c1.getSelectedIndex() + 1);
			for (int i = 0; i < list.size(); i++) {
				c2.addItem(list.get(i));
			}
		} else if (e.getSource().equals(c2)) {
			c3.removeAllItems();
			var list = DB.getTraval(c2.getSelectedIndex() + 1);
			for (int i = 0; i < list.size(); i++) {
				c3.addItem(list.get(i));
			}
		} else if (e.getSource().equals(c3)) {
			c4.removeAllItems();
			String tno = DB.getString("select tno from travel where tname = ?", c3.getSelectedItem());
			var list = DB.getDepartureTime(tno);
			for (int i = 0; i < list.size(); i++) {
				c4.addItem(list.get(i));
			}
		} else if (e.getSource().equals(c4)) {
			String tno = DB.getString("select tno from travel where tname = ?", c3.getSelectedItem());
			var getGate = DB.getString("select gate from schedule where tno = ? and departuretime = ?", tno, c4.getSelectedItem());
			t3.setText(getGate);
		} else if (e.getSource().equals(reservation)) {
			inputData.removeAll();
			ok("예약이 완료되었습니다.");
			String[] seat = t4.getText().split(",");
			String bno = DB.getString("select bno + 1 from reservation order by bno desc limit 1");
			for (int i = 0; i < seat.length; i++) {
				DB.update("insert into reservation (bno, uno, sno, division, seat) values (?, ?, 1, 1, ?)", bno, Login.no, seat[i]);
			}
			inputData.revalidate();
		}
	}
}
