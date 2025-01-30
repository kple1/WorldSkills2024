package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.ImageSize;

public class ShowItem extends JPanel {

	public ShowItem(String path, String name, String getPrice) {
		setSize(100, 150);
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(0, 50));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		JLabel itemName = new JLabel(name);
		itemName.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel.add(itemName);
		
		JLabel price = new JLabel(getPrice);
		price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel.add(price);
		
		JLabel image = new JLabel("");
		image.setSize(100, 150);
		image.setIcon(ImageSize.set(new ImageIcon(path), image));
		add(image, BorderLayout.CENTER);

	}

}
