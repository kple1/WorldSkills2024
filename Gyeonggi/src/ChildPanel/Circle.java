package ChildPanel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Circle extends JPanel {

	Color color;
	public Circle(Color color) {
		this.color = color;
		setSize(10, 10);
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.fillOval(0, 0, 10, 10);
	}
}
