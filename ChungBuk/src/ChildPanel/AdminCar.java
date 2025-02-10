package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Model.Panel;

public class AdminCar extends Panel {
	public JLabel carname;
	public JLabel image;

	public AdminCar(byte[] imgPath, String name) {
		setPreferredSize(new Dimension(150, 150));
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		carname = new JLabel(name);
		carname.setBorder(BorderFactory.createLineBorder(Color.black));
		carname.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		carname.setHorizontalAlignment(SwingConstants.CENTER);
		add(carname, BorderLayout.SOUTH);
		
		image = new JLabel("");
		image.setSize(150, 150);
		image.setIcon(imageSize(new ImageIcon(imgPath), image));
		add(image, BorderLayout.CENTER);
	}
}