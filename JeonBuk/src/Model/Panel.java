package Model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	public void setSize(Component c, int w, int h) {
		c.setPreferredSize(new Dimension(w, h));
	}
	
	public void setSize(int w, int h, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setPreferredSize(new Dimension(w, h));
		}
	}
	
	public void setMSize(int w, int h, Component c) {
		c.setPreferredSize(new Dimension(w, h));
		c.setMaximumSize(new Dimension(w, h));
	}
	
	public ImageIcon imageSize(ImageIcon icon, JLabel label) {
		Image get = icon.getImage();
		Image re = get.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
		return new ImageIcon(re);
	}
	
	public void font(int size, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, size));
		}
	}
}
