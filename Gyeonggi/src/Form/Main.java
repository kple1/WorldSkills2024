package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Utils.ImageSize;
import Utils.Msg;

public class Main extends JFrame {

	Timer timer;
	public static String saleState = "go";
	public static void main(String[] args) {
		new Main().setVisible(true);
	}

	public Main() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("메인");
		setBounds(0, 0, 500, 750);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.WEST);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel.add(verticalStrut, BorderLayout.NORTH);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel.add(verticalStrut_1, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 5, 0));
		
		JButton eat = new JButton("\uB9E4\uC7A5\uC5D0\uC11C \uBA39\uC5B4\uC694");
		eat.addActionListener(e -> {
			saleState = "eat";
			Msg.ok("매장 내 1회용 컵 사용금지입니다.");
			dispose();
			new ChooseMenu().setVisible(true);
		});
		eat.setBorder(BorderFactory.createLineBorder(Color.black));
		panel_1.add(eat);
		
		JButton go = new JButton("\uD3EC\uC7A5\uD574\uC11C \uAC08\uB798\uC694");
		go.addActionListener(e -> {
			saleState = "go";
			new ChooseMenu().setVisible(true);
		});
		go.setBorder(BorderFactory.createLineBorder(Color.black));
		panel_1.add(go);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_3, BorderLayout.EAST);
		
		JPanel view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
		
		JLabel image = new JLabel("");
		view.add(image, BorderLayout.CENTER);
		
		imageView(image);
	}
	
	int count = 1;
	void imageView(JLabel image) {
		timer = new Timer(1000, e -> {
			image.setIcon(ImageSize.set(new ImageIcon("datafiles/main/" + count + ".png"), image));
			if (count == 3) {
				count = 1;
			} else {
				++count;
			}
		});
		timer.start();
	}
}
