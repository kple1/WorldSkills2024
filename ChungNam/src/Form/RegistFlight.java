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
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Frame;
import Util.DB;

public class RegistFlight extends Frame implements ActionListener {
	public JLabel l1;
	public JPanel panel;
	public JButton regist;
	public JPanel gap;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public JPanel view;
	public JPanel east;
	public JPanel west;
	public JPanel center;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new RegistFlight().setVisible(true);
	}

	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose) new Main("professor").setVisible(true);
	}
	public static JTextField t1, t2, t3;
    JComboBox<String> c1, c2, c3, c4;
	public RegistFlight() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setFrame("비행 일정 등록", 500, 450);
		l1 = new JLabel("\uBE44\uD589 \uC2A4\uCF00\uC974 \uB4F1\uB85D");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		getContentPane().add(l1, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		regist = new JButton("\uB4F1\uB85D");
		regist.setBackground(Color.white);
		regist.setPreferredSize(new Dimension(100, 30));
		regist.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(regist);
		
		gap = new JPanel();
		gap.setBackground(new Color(255, 255, 255));
		getContentPane().add(gap, BorderLayout.CENTER);
		gap.setLayout(new BorderLayout(0, 0));
		
		horizontalStrut = Box.createHorizontalStrut(60);
		gap.add(horizontalStrut, BorderLayout.EAST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(60);
		gap.add(horizontalStrut_1, BorderLayout.WEST);
		
		verticalStrut = Box.createVerticalStrut(20);
		gap.add(verticalStrut, BorderLayout.SOUTH);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		gap.add(verticalStrut_1, BorderLayout.NORTH);
		
		view = new JPanel();
		gap.add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
		
		east = new JPanel();
		east.setBackground(new Color(255, 255, 255));
		east.setPreferredSize(new Dimension(50, 0));
		view.add(east, BorderLayout.EAST);
		east.setLayout(new GridLayout(7, 1, 0, 10));
		
		var cal = new JLabel();
		cal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new SelectDate("RegistFlight").setVisible(true);
			}
		});
		cal.setSize(30, 30);
		cal.setIcon(imageSize(new ImageIcon("datafiles/Date.png"), cal));
		east.add(cal);
		
		west = new JPanel();
		west.setBackground(new Color(255, 255, 255));
		west.setPreferredSize(new Dimension(100, 0));
		view.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(7, 1, 0, 10));
		
		center = new JPanel();
		center.setBackground(new Color(255, 255, 255));
		view.add(center, BorderLayout.CENTER);
		center.setLayout(new GridLayout(7, 1, 0, 10));
		
		t1 = new JTextField();
		t1.setEnabled(false);
		t2 = new JTextField();
		t3 = new JTextField();
		c1 = new JComboBox<String>();
		c2 = new JComboBox<String>();
		c3 = new JComboBox<String>();
		c4 = new JComboBox<String>();
		
		Component[] lc = new Component[] {t1, c1, c2, c3, c4, t2, t3};
		for (int i = 0; i < lc.length; i++) {
			center.add(lc[i]);
			setFont(12, lc[i]);
		}
		
		String [] ls = new String[] {"DepartureDate", "Continent", "Country", "Travel", "DepartureTime", "Gate", "airNo"};
		for (int i = 0; i < ls.length; i++) {
			var label = new JLabel(ls[i]);
			setBold(13, label);
			west.add(label);
		}
		
		String[] list = new String[] { "아프리카", "북아메리카", "남아메리카", "유럽", "아시아", "오세아니아"};   
		for (int i = 0; i < list.length; i++) {
			c1.addItem(list[i]);	
		}
		
		c1.addActionListener(this);
		c2.addActionListener(this);
		c3.addActionListener(this);
		c4.addActionListener(this);
		regist.addActionListener(this);
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
			t2.setText(getGate);
		} else if (e.getSource().equals(regist)) {
			if (t1.getText().isEmpty() || t2.getText().isEmpty()) {
				fail("빈 항목이 있습니다.");
			} else if (!Pattern.matches("[0-9]*", t2.getText()) || !Pattern.matches("[0-9]*", t3.getText())) {
				fail("Gate와 airNo는 숫자로 입력해주세요.");
			} else if (DB.isTrue("select count(*) from schedule where departuretime = ? and departuredate = ? and gate = ?", c4.getSelectedItem(), t1.getText(), t2.getText())) {
				fail("해당 게이트에는 다른 비행스케쥴이 있습니다.");
			} else if (DB.isTrue("select count(*)airNo from schedule where airNo = ?", t3.getText())) {
				fail("해당 항공기는 다른 비행스케쥴이 있습니다.");
			} else {
				ok("스케쥴 등록이 완료되었습니다.");
				DB.update("insert into schedule (departuredate, tno, departuretime, gate, airNo) values (?, ?, ?, ?, ?)", t1.getText(), c3.getSelectedIndex() + 1, c4.getSelectedItem(), t2.getText(), t3.getText());
				formClose = false;
				dispose();
				new Main("professor").setVisible(true);
			}
		}
	}

}
