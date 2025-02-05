package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Frame extends JFrame implements WindowListener {
	public void setFrame(String title, int w, int h) {
		setTitle(title);
		setBounds(0, 0, w, h);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
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
	
	public void ok(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Á¤º¸", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void fail(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Á¤º¸", JOptionPane.ERROR_MESSAGE);
	}

	public void font(int size, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, size));
		}
	}
	
	public void setBorder(Color color, JComponent... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setBorder(BorderFactory.createLineBorder(color));
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
