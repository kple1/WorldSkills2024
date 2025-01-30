package ChildPanel;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GraphModel extends JPanel {
	
	int orderAmount;
	public GraphModel(String getName, int orderAmount) {
		this.orderAmount = orderAmount;
		setLayout(new BorderLayout());
		setOpaque(false);
		setSize(20 * orderAmount, 20 * orderAmount);
		
		var name = new JLabel(getName + "(" + orderAmount + ")");
		name.setVerticalAlignment(SwingConstants.CENTER);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		add(name);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		var g2d = (Graphics2D) g;
		
		var r = new Random();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		g2d.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g2d.fillOval(0, 0, 20 * orderAmount, 20 * orderAmount);
	}
}
