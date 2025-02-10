package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Panel;

public class ReservationModel extends Panel implements ActionListener {
	public JLabel image;
	public JPanel panel;
	public JPanel top;
	public JLabel modelYear;
	public JLabel carName;
	public JPanel panel_1;
	public JLabel img1;
	public JLabel l1;
	public JLabel img2;
	public JLabel l2;
	public JPanel bottom;
	public JButton res3;
	public JButton res2;
	public JButton res1;
	public JLabel empty;
	public JPanel view;
	public JLabel l3;
	public JLabel l10;
	public JLabel l4;
	public JLabel l5;
	public JLabel l9;
	public JLabel l8;
	public JLabel l6;
	public JLabel l11;
	public JLabel l12;
	public JLabel l7;
	public JLabel l13;
	public JLabel l14;

	/**
	 * Create the panel.
	 */
	public ReservationModel(byte[] img, String getMY, String car_name, String p, String b, String a, String bee, String c, String d, String e, String f) {
		setSize(800, 300);
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		image = new JLabel("");
		image.setPreferredSize(new Dimension(300, 300));
		image.setSize(300, 300);
		image.setIcon(imageSize(new ImageIcon(img), image));
		add(image, BorderLayout.WEST);
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		top = new JPanel();
		top.setPreferredSize(new Dimension(0, 80));
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		modelYear = new JLabel(getMY + "년형");
		modelYear.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 0)));
		modelYear.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		modelYear.setForeground(new Color(0, 204, 0));
		top.add(modelYear);
		
		carName = new JLabel(car_name);
		carName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		top.add(carName);
		
		panel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		top.add(panel_1);
		
		img1 = new JLabel("");
		img1.setSize(20, 20);
		img1.setIcon(imageSize(new ImageIcon("datafiles/아이콘/mypage.png"), img1));
		panel_1.add(img1);
		
		l1 = new JLabel("실 가능 탑승 인원 " + p + "인");
		l1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(l1);
		
		img2 = new JLabel("");
		img2.setSize(20, 20);
		img2.setIcon(imageSize(new ImageIcon("datafiles/아이콘/bag.png"), img2));
		panel_1.add(img2);
		
		l2 = new JLabel("적재 가능 공간 가방 " + b + "개");
		l2.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_1.add(l2);
		
		bottom = new JPanel();
		panel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1, 4, 10, 0));
		
		empty = new JLabel("");
		bottom.add(empty);
		
		res1 = new JButton("\uC608\uC57D\uD558\uAE30");
		res1.setBackground(Color.PINK);
		res1.setForeground(Color.WHITE);
		bottom.add(res1);
		
		res2 = new JButton("\uC608\uC57D\uD558\uAE30");
		res2.setForeground(Color.WHITE);
		res2.setBackground(Color.PINK);
		bottom.add(res2);
		
		res3 = new JButton("\uC608\uC57D\uD558\uAE30");
		res3.setBackground(Color.PINK);
		res3.setForeground(Color.WHITE);
		bottom.add(res3);
		
		setSize(100, 30, res1, res2, res3);
		
		view = new JPanel();
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(3, 4, 0, 10));
		
		l3 = new JLabel("");
		l3.setOpaque(false);
		l3.setBackground(Color.LIGHT_GRAY);
		view.add(l3);
		
		l4 = new JLabel("\uAC00\uC785\uC548\uD568");
		l4.setOpaque(false);
		l4.setBackground(Color.LIGHT_GRAY);
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l4);
		
		l5 = new JLabel("\uC77C\uBC18\uBA74\uCC45");
		l5.setOpaque(false);
		l5.setBackground(Color.LIGHT_GRAY);
		l5.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l5);
		
		l6 = new JLabel("\uC288\uD37C\uBA74\uCC45");
		l6.setOpaque(false);
		l6.setBackground(Color.LIGHT_GRAY);
		l6.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l6);
		
		l7 = new JLabel("\uD68C\uC6D0\uAC00");
		l7.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l7);
		
		l8 = new JLabel(a + "원");
		l8.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l8);
		
		l9 = new JLabel(bee + "원");
		l9.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l9);
		
		l10 = new JLabel(c + "원");
		l10.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l10);
		
		l11 = new JLabel("비회원가");
		l11.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l11);
		
		l12 = new JLabel(d + "원");
		l12.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l12);
		
		l13 = new JLabel(e + "원");
		l13.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l13);
		
		l14 = new JLabel(f + "원");
		l14.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(l14);
		
		setFont(12, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
