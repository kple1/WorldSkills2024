package Model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Frame extends JFrame implements WindowListener {
	public void setFrame(String title, int w, int h) {
		addWindowListener(this);
		setTitle(title);
		setBounds(0, 0, w, h);
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
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
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
