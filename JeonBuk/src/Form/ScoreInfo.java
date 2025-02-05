package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Utils.DB;

public class ScoreInfo extends Frame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ScoreInfo(4).setVisible(true);
	}

	/**
	 * Create the application.
	 */
	String rating = "";boolean state = true;
	public ScoreInfo(int score) {
		setFrame("학점 정보", 450, 300);
		getContentPane().setBackground(Color.white);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		addWindowListener(this);
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		top.setBackground(Color.white);
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		if (score >= 4) {
			rating = "A";
		} else if (score >= 3) {
			rating = "B";
		} else {
			rating = "C";
		}
		
		var img = new JLabel();
		img.setSize(50, 50);
		img.setIcon(imageSize(new ImageIcon("datafiles/grades/" + rating + ".png"), img));
		top.add(img, BorderLayout.WEST);
		
		var rating = new JLabel("평균 학점 : " + score + ".0");
		top.add(rating, BorderLayout.EAST);
		font(16, rating);
		
		var getName = DB.getString("select name from student where s_no = ?", Login.no);
		var msg = new JLabel("<html>" + getName + " 학생님의<br>평균 학점은" + score + ".0 입니다.");
		font(20, msg);
		panel.add(msg, BorderLayout.CENTER);
		
		var check = new JButton("확인");
		check.addActionListener(e->{
			state = false;
			dispose();
			new ScoreCalculate().setVisible(true);
		});
		panel.add(check, BorderLayout.SOUTH);
	}
	
	public void windowClosed(WindowEvent e) {
		if (state) new ScoreCalculate().setVisible(true);
	}
}
