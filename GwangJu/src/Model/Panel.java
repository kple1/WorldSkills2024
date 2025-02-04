package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class Panel extends JPanel {
	public void setPlain(Component c, int size) {
		c.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, size));
	}
	
	public void setBold(Component c, int size) {
		c.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, size));
	}
	
	public void setSize(Component c, int w, int h) {
		c.setPreferredSize(new Dimension(w, h));
	}
	
	public void setMSize(Component c, int w, int h) {
		c.setPreferredSize(new Dimension(w, h));
		c.setMaximumSize(new Dimension(w, h));
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
	
	public void placeholder(JTextComponent c, String comment) {
		c.setText(comment);
		c.setForeground(Color.LIGHT_GRAY);
		c.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (c.getText().equals(comment)) {
					c.setText("");
					c.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (c.getText().isEmpty()) {
					c.setText(comment);
					c.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
	}
	
	public void setBorder(JComponent c, Color color) {
		c.setBorder(BorderFactory.createLineBorder(color));
	}
}
