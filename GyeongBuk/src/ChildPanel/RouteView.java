package ChildPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RouteView extends JPanel {

	public RouteView(String cityName) {
		setOpaque(false);
		setSize(50, 20);
		add(new Rect());
		add(new JLabel(cityName));
	}

}

class Rect extends JPanel {
	Rect() {
		setSize(20, 20);
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillOval(0, 0, 10, 10);
	}
}
 