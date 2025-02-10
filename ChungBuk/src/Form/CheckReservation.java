package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Model.Frame;
import Utils.DB;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckReservation extends Frame {
	public Component horizontalStrut;
	public Component verticalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut_1;
	public JPanel panel;
	public JPanel top;
	public JLabel l1;
	public JLabel l2;
	public Component verticalStrut_2;
	public JPanel line;
	public JPanel panel_1;
	public JButton search;
	public JPanel panel_2;
	public Component verticalStrut_3;
	public Component verticalStrut_4;
	public JPanel panel_3;
	public JPanel panel_4;
	public JPanel panel_5;
	public JLabel l3;
	public JLabel lblNewLabel;
	public JTextField r_name;
	public JLabel lblNewLabel_1;
	public JTextField r_no;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CheckReservation().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public CheckReservation() {
		setFrame("¿¹¾àÈ®ÀÎ", 600, 400);
		setSize(600, 400);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		top = new JPanel();
		top.setPreferredSize(new Dimension(0, 100));
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		
		l1 = new JLabel("\uC815\uBCF4\uC785\uB825");
		l1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		top.add(l1);
		
		l2 = new JLabel("\uACE0\uAC1D\uB2D8\uC758 \uC608\uC57D\uD655\uC778\uC744 \uC704\uD574 \uC608\uC57D\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		l2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		l2.setForeground(Color.GRAY);
		top.add(l2);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		top.add(verticalStrut_2);
		
		line = new JPanel();
		line.setBackground(Color.BLACK);
		line.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
		line.setAlignmentX(Component.LEFT_ALIGNMENT);
		top.add(line);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		search = new JButton("\uC870\uD68C");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var list = DB.reserCheck(r_name.getText(), r_no.getText());
				if (list[0].equals("0")) {
					fail("ÀÏÄ¡ÇÏ´Â Á¤º¸°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù.");
				} else {
					ok(String.format("<html>È¸¿ø´ÔÀÇ ¿¹¾àÀÌ '%s'Â÷·®<br>'%s'~'%s'³¯Â¥¿¡ ÀâÇô ÀÖ½À´Ï´Ù.<html\\>", list[1], list[2], list[3]));
				}
			}
		});
		search.setPreferredSize(new Dimension(100, 30));
		search.setForeground(Color.WHITE);
		search.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		search.setBackground(Color.PINK);
		panel_1.add(search);
		
		panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_3, BorderLayout.NORTH);
		
		verticalStrut_4 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_4, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(3, 0, 0, 10));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		lblNewLabel = new JLabel("\uC608\uC57D\uC790 \uC774\uB984");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel_4.add(lblNewLabel);
		
		r_name = new JTextField();
		panel_4.add(r_name);
		r_name.setColumns(30);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		
		lblNewLabel_1 = new JLabel("\uC608\uC57D\uBC88\uD638    ");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_1);
		
		r_no = new JTextField();
		r_no.setColumns(30);
		panel_5.add(r_no);
		
		l3 = new JLabel("* \uC608\uC57D\uBC88\uD638\uAC00 \uC0DD\uAC01\uB098\uC9C0 \uC54A\uC73C\uC2DC\uBA74, \uB85C\uADF8\uC778\uC744 \uD1B5\uD574 \uB9C8\uC774\uD398\uC774\uC9C0\uC5D0\uC11C \uD655\uC778\uD558\uC2E4 \uC218 \uC788\uC2B5\uB2C8\uB2E4.");
		l3.setForeground(Color.RED);
		l3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel_3.add(l3);
	}

}
