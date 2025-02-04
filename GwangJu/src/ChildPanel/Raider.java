package ChildPanel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Model.Panel;

public class Raider extends Panel {

	int x, y;
	public int angle;
	public Raider(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2d.setColor(Color.RED);
        g2d.fillArc(0, 0, 200, 200, 90, angle);
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2d.setStroke(new BasicStroke(1)); // 경계선 두께 설정
        g2d.drawArc(0, 0, 200, 200, 90, angle);
	}
}
