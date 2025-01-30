package ChildPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AdminModel extends JPanel {

	public static JLabel no, price, name;
	public AdminModel(String ono, String getName, int getPrice) {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.gray);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.gray);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		no = new JLabel(ono);
		no.setForeground(Color.white);
		no.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel.add(no, BorderLayout.WEST);
		
		price = new JLabel(String.format("\\ %,d", getPrice));
		price.setForeground(Color.white);
		price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(price, BorderLayout.EAST);
		
		name = new JLabel(getName);
		name.setForeground(Color.white);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel.add(name, BorderLayout.CENTER);
	}
	
	public void setYellow() {
		name.setForeground(Color.orange);
		price.setForeground(Color.orange);
		no.setForeground(Color.orange);
	}
}
