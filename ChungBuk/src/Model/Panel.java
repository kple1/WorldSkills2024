package Model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel extends JPanel {
	public void setSize(int w, int h, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setPreferredSize(new Dimension(w, h));
		}
	}
	
	public void setFont(int size, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, size));
		}
	}
	
	public void setBold(int size, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setFont(new Font("¸¼Àº °íµñ", Font.BOLD, size));
		}
	}
	
	public ImageIcon imageSize(ImageIcon img, JLabel label) {
		Image get = img.getImage();
		Image re = get.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
		return new ImageIcon(re);
	}
}
