package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ChildPanel.ReservationModel;
import Model.Frame;
import Utils.DB;

public class Reservation extends Frame implements MouseListener, ActionListener {
	public JPanel panel;
	public JLabel title;
	public JPanel panel_1;
	public JPanel panel_2;
	public JLabel l1;
	public JPanel panel_3;
	public static JLabel date;
	public JLabel cal1;
	public JComboBox c1;
	public JLabel l2;
	public JComboBox c2;
	public JLabel l3;
	public Component horizontalStrut;
	public JLabel l4;
	public JPanel panel_4;
	public static JLabel redate;
	public JLabel cal2;
	public JComboBox c3;
	public JLabel l5;
	public JComboBox c4;
	public JLabel l6;
	public Component horizontalStrut_1;
	public JLabel l7;
	public JComboBox c5;
	public JLabel l8;
	public JComboBox c6;
	public JLabel l9;
	public JComboBox c7;
	public JLabel l10;
	public JComboBox c8;
	public JButton search;
	public JScrollPane scrollPane;
	public JPanel view;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Reservation().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Reservation() {
		setFrame("예약", 800, 500);
		setSize(800, 550);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 120));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("\uB80C\uD130\uCE74 \uB300\uC5EC\uAE30\uAC04\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title, BorderLayout.NORTH);
		
		panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.setPreferredSize(new Dimension(0, 40));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		l8 = new JLabel("\uC5F0\uB8CC");
		panel_1.add(l8);
		
		c6 = new JComboBox();
		c6.addItem("전체");
		String[] fuelType = new String[] {"가솔린"  ,
									      "LPG   "  ,
									      "디젤  "  ,
									      "전기차"};
		for (int i = 0; i < fuelType.length; i++) {
			c6.addItem(fuelType[i]);
		}
		c6.setPreferredSize(new Dimension(70, 20));
		panel_1.add(c6);
		
		l9 = new JLabel("\uB300\uC5EC\uC9C0\uC810");
		panel_1.add(l9);
		
		c7 = new JComboBox();
		c7.addItem("= 선택 =");
		String[] rentalList = new String[] {"본사",
										  "신라호텔지",
										  "가산디지털",
										  "가평터미널",
										  "간성터미널"};
		for (int i = 0; i < rentalList.length; i++) {
			c7.addItem(rentalList[i]);
		}
		c7.setPreferredSize(new Dimension(80, 20));
		panel_1.add(c7);
		
		l10 = new JLabel("\uBC18\uB0A9\uC9C0\uC810");
		panel_1.add(l10);
		
		c8 = new JComboBox();
		c8.addItem("= 선택 =");                         
		String[] returnList = new String[] {"사천공항    "  ,
										    "강남고속터미"  ,
										    "상암DMC     "  ,
										    "서대구KTX역 "  ,
										    "서부패밀리아"};
		for (int i = 0; i < returnList.length; i++) {
			c8.addItem(returnList[i]);
		}
		c8.setPreferredSize(new Dimension(80, 20));
		panel_1.add(c8);
		
		search = new JButton("\uC870\uD68C");
		panel_1.add(search);
		
		panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(panel_2, BorderLayout.CENTER);
		
		l1 = new JLabel("\uB300\uC5EC\uC77C");
		panel_2.add(l1);
		
		panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_2.add(panel_3);
		
		date = new JLabel("2024-05-08");
		panel_3.add(date);
		
		cal1 = new JLabel("");
		cal1.setSize(20, 20);
		cal1.setIcon(imageSize(new ImageIcon("datafiles/아이콘/calendar.png"), cal1));
		panel_3.add(cal1);
		
		c1 = new JComboBox();
		for (int i = 7; i <= 22; i++) {
			c1.addItem(i + "");
		}
		panel_2.add(c1);
		
		l2 = new JLabel("\uC2DC");
		panel_2.add(l2);
		
		c2 = new JComboBox();
		for (int i = 0; i <= 5; i++) {
			c2.addItem(i + "0");
		}
		panel_2.add(c2);
		
		l3 = new JLabel("\uBD84");
		panel_2.add(l3);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut);
		
		l4 = new JLabel("\uBC18\uB0A9\uC77C");
		panel_2.add(l4);
		
		panel_4 = new JPanel();
		panel_4.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_2.add(panel_4);
		
		redate = new JLabel("2024-05-09");
		panel_4.add(redate);
		
		cal2 = new JLabel("");
		cal2.setSize(20, 20);
		cal2.setIcon(imageSize(new ImageIcon("datafiles/아이콘/calendar.png"), cal2));
		panel_4.add(cal2);
		
		c3 = new JComboBox();
		for (int i = 7; i <= 22; i++) {
			c3.addItem(i + "");
		}
		panel_2.add(c3);
		
		l5 = new JLabel("\uC2DC");
		panel_2.add(l5);
		
		c4 = new JComboBox();
		for (int i = 0; i <= 5; i++) {
			c4.addItem(i + "0");
		}
		panel_2.add(c4);
		
		l6 = new JLabel("\uBD84");
		panel_2.add(l6);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_1);
		
		l7 = new JLabel("\uCC28\uB7C9\uD0C0\uC785");
		panel_2.add(l7);
		
		c5 = new JComboBox();
		c5.addItem("전체");
		String[] typeList = new String[] {"경/소형" ,
				                          "준중형 " ,
				                          "중형   " ,
				                          "고급형 " ,
				                          "전기차 " ,
				                          "SUV    " ,
				                          "7-9인승" ,
				                          "11-12승"};
		for (int i = 0; i < typeList.length; i++) {
			c5.addItem(typeList[i]);
		}
		c5.setPreferredSize(new Dimension(70, 20));
		panel_2.add(c5);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		view = new JPanel();
		scrollPane.setViewportView(view);
		view.setLayout(new GridLayout(0, 1, 0, 0));
		
		reload();
		
		setFont(12, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, c1, c2, c3, c4, c5, c6, c7, c8, search);
		
		cal1.addMouseListener(this);
		cal2.addMouseListener(this);
		search.addActionListener(this);
	}
	
	void reload() {
		view.removeAll();
		var list = DB.getCar();
		for (int i = 0; i < list.size(); i++) {
			view.add(new ReservationModel((byte[])list.get(i)[0], list.get(i)[1].toString(), list.get(i)[2].toString(), list.get(i)[3].toString(), list.get(i)[4].toString(), list.get(i)[5].toString(), list.get(i)[6].toString(), list.get(i)[7].toString(), list.get(i)[8].toString(), list.get(i)[9].toString(), list.get(i)[10].toString()));
		}
		view.revalidate();
		view.repaint();
	}

	String state;
	public static int setDate = LocalDate.now().getDayOfMonth();
	public static int setRedate = setDate + 1;
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(cal1)) {
			state = "date";
			new Calendar(state, setDate).setVisible(true);
		} else if (e.getSource().equals(cal2)) {
			state = "redate";
			new Calendar(state, setRedate).setVisible(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(search)) {
			if (c6.getSelectedIndex() == -1 || c7.getSelectedIndex() == -1) {
				fail("대여 지점과 반납 지점을 선택해주세요.");
			} else {
				//기차나
			}
		}
	}
}
