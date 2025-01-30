package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ReceiptModel extends JPanel {

	public ReceiptModel(String getName, String getAmount, int getPrice) {
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(500, 30));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel name = new JLabel(getName);
		name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		name.setPreferredSize(new Dimension(300, 0));
		panel.add(name, BorderLayout.WEST);
		
		JLabel price = new JLabel(String.format("%,d", getPrice));
		price.setPreferredSize(new Dimension(50, 0));
		price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel.add(price, BorderLayout.EAST);
		
		JLabel amount = new JLabel(getAmount);
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel.add(amount, BorderLayout.CENTER);
		
	}

}
