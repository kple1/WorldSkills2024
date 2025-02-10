package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class Frame extends JFrame {
	public void setFrame(String name, int w, int h) {
		setTitle(name);
		setBounds(0,0,w,h);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
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
	
	public void ok(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Á¤º¸", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void fail(String msg) {
		JOptionPane.showMessageDialog(null, msg, "°æ°í", JOptionPane.ERROR_MESSAGE);
	}
	
	public void placeholder(JTextComponent c, String text) {
		c.setText(text);
		c.setForeground(Color.LIGHT_GRAY);
		c.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (c.getText().equals(text)) {
					c.setText("");
					c.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (c.getText().isEmpty()) {
					c.setText(text);
					c.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
	}
}
