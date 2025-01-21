package Utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Line extends JPanel {

	int x1;
	int x2;
	int y1;
	int y2;
	Color color;
	public Line(int x1, int y1, int x2, int y2, Color color) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.color = color;
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		g.drawLine(x1, y1, x2, y2);
	}
}
