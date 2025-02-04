package Model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener, KeyListener, WindowListener, MouseListener {
	
	public void setFrame(String title, int w, int h) {
		setBounds(0, 0, w, h);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		setLocationRelativeTo(null);
		setBackground(Color.white);
	}
	
	public void setFrame(String title) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		setBackground(Color.white);
	}
	
	public void setPack() {
		pack();
		setLocationRelativeTo(null);
	}
	
	public void setPlain(Component c, int size) {
		c.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, size));
	}
	
	public void setPlain(int size, Component... c) {
		for (int i = 0; i < c.length; i++) {
			c[i].setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, size));
		}
	}
	
	public void setBold(Component c, int size) {
		c.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, size));
	}
	
	public void setSize(Component c, int w, int h) {
		c.setPreferredSize(new Dimension(w, h));
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
	
	public void placeholder(JTextField c, String comment) {
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
