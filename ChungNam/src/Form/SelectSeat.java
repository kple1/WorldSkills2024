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
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Model.Panel;
import Util.DB;

public class SelectSeat extends Frame{
	public JPanel east;
	public JPanel line;
	public JPanel panel;
	public JButton select;
	public JPanel top;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public JPanel topView;
	public JPanel west;
	public JPanel center;
	public JPanel centerView;
	public JPanel centerTop;
	public JScrollPane scroll;
	public JTable table;
	public JPanel radioPanel;
	public JPanel bottom;
	public JLabel resultLabel;
	public JLabel result;
	public JRadioButton adult;
	public JRadioButton child;
	public JRadioButton kind;
	public JPanel seatPanel;
	public JPanel firstPanel;
	public JPanel businessPanel;
	public JPanel economyPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new SelectSeat("1").setVisible(true);
	}

	/**
	 * Create the application.
	 */
	DefaultTableModel  model = new DefaultTableModel(new Object[][] {}, new String[] {"ÁÂ¼®", "±¸ºÐ", "±Ý¾×"});
	public SelectSeat(String s_no) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setFrame("ÁÂ¼® ¼±ÅÃ", 800, 800);
		setSize(800, 700);

		east = new JPanel();
		east.setBackground(new Color(255, 255, 255));
		east.setPreferredSize(new Dimension(300, 0));
		getContentPane().add(east, BorderLayout.EAST);
		east.setLayout(new BorderLayout(0, 0));
		
		line = new JPanel();
		line.setPreferredSize(new Dimension(1, 0));
		line.setBackground(new Color(0, 0, 0));
		east.add(line, BorderLayout.WEST);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		east.add(panel, BorderLayout.SOUTH);
		
		select = new JButton("\uC120\uD0DD\uC644\uB8CC");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				var list = new ArrayList<String>();
				for (int i = 0; i < model.getRowCount(); i++) {
					list.add(model.getValueAt(i, 0).toString());
				}
				Reservation.t4.setText(String.join(",", list));
				
				int getTime = DB.getInt("SELECT minute FROM travel where tname = ?", Reservation.c3.getSelectedItem());
				int departureTime = Integer.parseInt(Reservation.c4.getSelectedItem().toString().substring(0, 2)) + getTime  / 60;
				String[] input = new String[] {
						"Incheon",
						Reservation.c3.getSelectedItem().toString(),
						Reservation.c1.getSelectedItem() + "-" + Reservation.c2.getSelectedItem(),
						Reservation.t2.getText(),
						Reservation.c4.getSelectedItem().toString(),
						String.format("%d:00(%dºì)", departureTime, getTime),
						"1,000,000",
						Reservation.t3.getText()
				};
				for (int i = 0; i < input.length; i++) {
					var label = new JLabel(input[i]);
					setFont(12, label);
					Reservation.inputData.add(label);
				}
				Reservation.inputData.revalidate();
			}
		});
		select.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		select.setBackground(new Color(255, 255, 255));
		panel.add(select);
		
		top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		top.setPreferredSize(new Dimension(0, 100));
		east.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		horizontalStrut = Box.createHorizontalStrut(50);
		top.add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(50);
		top.add(horizontalStrut_1, BorderLayout.EAST);
		
		topView = new JPanel();
		topView.setBackground(new Color(255, 255, 255));
		top.add(topView, BorderLayout.CENTER);
		topView.setLayout(new BorderLayout(0, 0));
		
		west = new JPanel();
		west.setBackground(new Color(255, 255, 255));
		west.setPreferredSize(new Dimension(30, 0));
		topView.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(3, 1, 0, 5));
		
		Color[] color = new Color[] {Color.BLUE, Color.GREEN, Color.YELLOW};
		for (int i = 0; i < 3; i++) {
			var label = new JLabel();
			setSize(25, 25, label);
			label.setOpaque(true);
			label.setBackground(color[i]);
			west.add(label);
		}
		
		center = new JPanel();
		center.setBackground(new Color(255, 255, 255));
		topView.add(center, BorderLayout.CENTER);
		center.setLayout(new GridLayout(3, 1, 0, 5));
		
		centerView = new JPanel();
		centerView.setBackground(new Color(255, 255, 255));
		east.add(centerView, BorderLayout.CENTER);
		centerView.setLayout(new BorderLayout(0, 0));
		
		centerTop = new JPanel();
		centerTop.setBackground(new Color(255, 255, 255));
		centerTop.setPreferredSize(new Dimension(0, 300));
		centerView.add(centerTop, BorderLayout.NORTH);
		centerTop.setLayout(new BorderLayout(0, 0));
		
		scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(0, 230));
		centerTop.add(scroll, BorderLayout.NORTH);
		
		table = new JTable();
		scroll.setViewportView(table);
		
		table.setModel(model);
		
		radioPanel = new JPanel();
		radioPanel.setBackground(new Color(255, 255, 255));
		centerTop.add(radioPanel, BorderLayout.CENTER);
		
		adult = new JRadioButton("\uC131\uC778");
		adult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		radioPanel.add(adult);
		
		child = new JRadioButton("\uC5B4\uB9B0\uC774");
		child.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		radioPanel.add(child);
		
		kind = new JRadioButton("\uC720\uC544");
		kind.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		radioPanel.add(kind);
		
		bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		centerTop.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));
		
		resultLabel = new JLabel("\uD569\uACC4");
		resultLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		bottom.add(resultLabel, BorderLayout.WEST);
		
		result = new JLabel("2,430,000");
		result.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		bottom.add(result, BorderLayout.EAST);
		
		seatPanel = new JPanel();
		seatPanel.setBackground(new Color(255, 255, 255));
		getContentPane().add(seatPanel, BorderLayout.CENTER);
		seatPanel.setLayout(null);
		
		firstPanel = new JPanel();
		firstPanel.setBackground(new Color(255, 255, 255));
		firstPanel.setBounds(0, 0, 484, 170);
		seatPanel.add(firstPanel);
		firstPanel.setLayout(new GridLayout(3, 4, 40, 5));
		
		businessPanel = new JPanel();
		businessPanel.setBackground(new Color(255, 255, 255));
		businessPanel.setBounds(0, 179, 484, 180);
		seatPanel.add(businessPanel);
		businessPanel.setLayout(null);
		
		economyPanel = new JPanel();
		economyPanel.setBackground(new Color(255, 255, 255));
		economyPanel.setBounds(0, 369, 484, 292);
		seatPanel.add(economyPanel);
		economyPanel.setLayout(null);
		
		String[] names = new String[] {" First Class", " Business Class", " Economy Class"};
		for (int i = 0; i < 3; i++) {
			var label = new JLabel(names[i]);
			setFont(12, label);
			center.add(label);
		}
		
		int x = 0;
		int y = 0;
		var seat = DB.getSeat(Integer.parseInt(s_no));
		for (int i = 1; i <= 12; i++) {
			var s = new Seat("first", "F" + i, 60, 50);
			firstPanel.add(s);
			
			if (seat.contains("F" + i)) {
				s.setColor(Color.LIGHT_GRAY);
			}
			
			s.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					s.setColor(Color.red);
					model.addRow(new Object[] {s.getNum()});
				}
			});
		}
		
		for (int i = 1; i <= 24; i++) {
			var s = new Seat("business", "B" + i, 45, 35);
			s.setBounds(x, y, 50, 40);
			if (i % 6 == 0 && i != 1) {
				y += 50;
				x = 0;
			} else {
				if (i % 2 == 0) {
					x += 120;
				} else {
					x += 55;
				}
			}
			
			if (seat.contains("B" + i)) {
				s.setColor(Color.LIGHT_GRAY);
			}
			
			s.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					s.setColor(Color.red);
					model.addRow(new Object[] {s.getNum()});
				}
			});
			
			businessPanel.add(s);
		}
		
		x = 0;
		y = 0;
		for (int i = 1; i <= 56; i++) {
			var s = new Seat("econommy", "E" + i, 35, 25);
			s.setBounds(x, y, 40, 30);
			if (i % 8 == 0 && i != 1) {
				y += 40;
				x = 0;
			} else {
				if (i % 2 == 0) {
					x += 80;
				} else {
					x += 45;
				}
			}
			
			if (seat.contains("E" + i)) {
				s.setColor(Color.LIGHT_GRAY);
			}
			
			s.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					s.setColor(Color.red);
					model.addRow(new Object[] {s.getNum()});
				}
			});
			
			economyPanel.add(s);
		}
	}
}

class Seat extends Panel {
	JLabel label;
	JLabel setNum;
	public Seat(String className, String num, int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setLayout(null);
		setBackground(Color.white);
		
		var layer = new JLayeredPane();
		layer.setBounds(0, 0, width, height);
		add(layer);
		
		label = new JLabel();
		label.setBounds(0, 0, width, height);
		if (className.equals("first")) {
			label.setIcon(imageSize(new ImageIcon("datafiles/first.jpg"), label));
		} else if (className.equals("business"))  {
			label.setIcon(imageSize(new ImageIcon("datafiles/businuess.jpg"), label));
		} else {
			label.setIcon(imageSize(new ImageIcon("datafiles/econommy.jpg"), label));
		}
		layer.add(label, JLayeredPane.DEFAULT_LAYER);
		
		setNum = new JLabel(num);
		setNum.setHorizontalAlignment(SwingConstants.CENTER);
		setNum.setBounds(0, 0, width, height);
		layer.add(setNum, JLayeredPane.PALETTE_LAYER);
	}
	
	public void setColor(Color color) {
		setNum.setForeground(color);
	}
	
	public String getNum() {
		return setNum.getText();
	}
}