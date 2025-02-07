package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Model.Frame;

public class Main extends Frame implements ActionListener {
	public JLayeredPane layer;
	public JLabel title;
	public JPanel buttonPanel;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public JPanel grid;
	public JButton b3;
	public JButton b1;
	public JButton b2;

	public static void main(String[] args) {
		new Main("user").setVisible(true);
	}
	
	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose) new Login().setVisible(true);
	}
	
	Timer timer;
	int x = 0;
	int y = 211;
	String state;
	public Main(String state) {
		this.state = state;
		
		getContentPane().setBackground(new Color(255, 255, 255));
		setFrame("¸ÞÀÎ", 300, 300);
		setSize(300, 300);
		
		layer = new JLayeredPane();
		getContentPane().add(layer, BorderLayout.CENTER);
		
		title = new JLabel("Admin Main");
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(0, 0, 284, 50);
		layer.add(title);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.setBounds(0, 61, 284, 163);
		layer.add(buttonPanel);
		buttonPanel.setLayout(new BorderLayout(0, 0));
		
		horizontalStrut = Box.createHorizontalStrut(75);
		buttonPanel.add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(75);
		buttonPanel.add(horizontalStrut_1, BorderLayout.EAST);
		
		grid = new JPanel();
		grid.setBackground(new Color(255, 255, 255));
		buttonPanel.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(3, 1, 0, 10));
		
		b1 = new JButton("New button");
		grid.add(b1);
		
		b2 = new JButton("New button");
		grid.add(b2);
		
		b3 = new JButton("New button");
		grid.add(b3);
		
		var img = new JLabel();
		img.setBounds(x, y, 30, 30);
		img.setIcon(imageSize(new ImageIcon("datafiles/airplane.png"), img));
		layer.add(img, JLayeredPane.PALETTE_LAYER);
		timer = new Timer(10, e-> {
			if (y >= -30) {
				x += 4;
				y -= 4;
			} else {
				x = -30;
				y = 241;
			}
			img.setLocation(x, y);
		});
		timer.start();
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		if (state.equals("user")) {
			b1.setText("Reservation");
			b2.setText("My Page");
			title.setText("Member Main");
		} else {
			b1.setText("Ranking");
			b2.setText("Register Flight");
		}
		b3.setText("Logout");
		
		b1.setBackground(Color.white);
		b2.setBackground(Color.white);
		b3.setBackground(Color.white);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		formClose = false;
		dispose();
		if (e.getSource().equals(b1)) {
			if (state.equals("user")) {
				new Reservation().setVisible(true);
			} else {
				new Graph().setVisible(true);
			}
		} else if (e.getSource().equals(b2)) {
			if (state.equals("user")) {
				new MyPage().setVisible(true);
			} else {
				new RegistFlight().setVisible(true);
			}
		} else if (e.getSource().equals(b3)) {
			new Login().setVisible(true);
		}
	}
}
