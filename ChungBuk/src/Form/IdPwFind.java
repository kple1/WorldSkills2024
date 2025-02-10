package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Model.Frame;
import Utils.DB;

public class IdPwFind extends Frame implements ActionListener {
	public JPanel top;
	public JButton findpw;
	public JButton findid;
	public JPanel panel;
	public JPanel bottomPanel;
	public JPanel radio;
	public JPanel panel_2;
	public JPanel panel_3;
	public JPanel panel_4;
	public Component verticalStrut_1;
	public JRadioButton r1;
	public JRadioButton r2;
	public JLabel l1;
	public JTextField t1;
	public JLabel l2;
	public JTextField t2;
	public JLabel l3;
	public JTextField c1;
	public JLabel l4;
	public JTextField c2;
	public JLabel l5;
	public JTextField c3;
	public JButton verify;
	public JTextField email;
	public JButton button;
	public JLabel l6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new IdPwFind().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public IdPwFind() {
		setFrame("아이디/비밀번호 찾기", 600, 400);
		setSize(600, 400);
		
		top = new JPanel();
		getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new GridLayout(1, 2, 0, 0));
		top.setPreferredSize(new Dimension(0, 80));
		
		findid = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		findid.setBackground(Color.PINK);
		top.add(findid);
		
		findpw = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		findpw.setBackground(Color.WHITE);
		top.add(findpw);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 1, 0, 8));
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);
		
		radio = new JPanel();
		radio.setVisible(false);
		panel.add(radio);
		
		r1 = new JRadioButton("\uD734\uB300\uC804\uD654\uB85C \uCC3E\uAE30");
		r1.setSelected(true);
		radio.add(r1);
		
		email = new JTextField(40);
		
		r2 = new JRadioButton("\uC774\uBA54\uC77C\uB85C \uCC3E\uAE30");
		radio.add(r2);
		
		panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(panel_2);
		
		l1 = new JLabel("\uC774\uB984");
		panel_2.add(l1);
		
		t1 = new JTextField();
		panel_2.add(t1);
		t1.setColumns(45);
		
		panel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(panel_3);
		
		l2 = new JLabel("\uC774\uBA54\uC77C");
		panel_3.add(l2);
		
		t2 = new JTextField();
		t2.setColumns(45);
		panel_3.add(t2);
		
		panel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(panel_4);
		
		l3 = new JLabel("\uD734\uB300\uC804\uD654");
		panel_4.add(l3);
		
		c1 = new JTextField();
		c1.setColumns(10);
		panel_4.add(c1);
		
		l4 = new JLabel("-");
		panel_4.add(l4);
		
		c2 = new JTextField();
		c2.setColumns(10);
		panel_4.add(c2);
		
		l5 = new JLabel("-");
		panel_4.add(l5);
		
		c3 = new JTextField();
		c3.setColumns(10);
		panel_4.add(c3);
		
		//아이디 찾는 거랑 중복인데 걍 건너뜀
		verify = new JButton("\uC778\uC99D");
		verify.setVisible(false);
		panel_4.add(verify);
		
		bottomPanel = new JPanel(new FlowLayout());
		panel.add(bottomPanel);
		
		button = new JButton("아이디 찾기");
		button.setBackground(Color.pink);
		button.setForeground(Color.white);
		bottomPanel.add(button);
		
		r1.addActionListener(this);
		r2.addActionListener(this);
		findpw.addActionListener(this);
		findid.addActionListener(this);
		button.addActionListener(this);
		
		l6 = new JLabel("이메일");
		
		setFont(12, findid, findpw, r1, r2, l1, t1, l2, t2, l3, c1, l4, c2, l5, c3, verify, button, l6);
		placeholder(t1, "이름을 입력하세요.");
		placeholder(t2, "이메일주소를 입력하세요.");
		placeholder(c1, "010");
		placeholder(c2, "1234");
		placeholder(c3, "5678");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(r1)) {
			panel_4.removeAll();
			panel_4.add(l3);
			panel_4.add(c1);
			panel_4.add(l4);
			panel_4.add(c2);
			panel_4.add(l5);
			panel_4.add(c3);
			panel_4.add(verify);
			panel_4.revalidate();
			panel_4.repaint();
			r2.setSelected(false);
		} else if (e.getSource().equals(r2)) {
			panel_4.removeAll();
			panel_4.add(l6);
			panel_4.add(email);
			panel_4.add(verify);
			panel_4.revalidate();
			panel_4.repaint();
			r1.setSelected(false);
		} else if (e.getSource().equals(findpw)) {
			l1.setText("아이디");
			l2.setText("이름");
			placeholder(t1, "아이디");
			placeholder(t2, "홍길동");
			placeholder(email, "abcd@gmail.com");
			findpw.setBackground(Color.pink);
			findpw.setForeground(Color.white);
			findid.setBackground(Color.white);
			findid.setForeground(Color.black);
			panel_4.add(verify);
			panel_4.revalidate();
			panel_4.repaint();
			button.setVisible(false);
			radio.setVisible(true);
			r1.setSelected(true);
			r2.setSelected(false);
		} else if (e.getSource().equals(findid)) {
			l1.setText("이름");
			l2.setText("이메일");
			placeholder(t1, "이름을 입력하세요.");
			placeholder(t2, "이메일주소를 입력하세요.");
			findpw.setBackground(Color.white);
			findpw.setForeground(Color.black);
			findid.setBackground(Color.pink);
			findid.setForeground(Color.white);
			button.setVisible(true);
			
			radio.setVisible(false);
			
			panel_4.removeAll();
			panel_4.add(new JLabel("휴대전화"));
			panel_4.add(c1);
			panel_4.add(l4);
			panel_4.add(c2);
			panel_4.add(l5);
			panel_4.add(c3);
			panel_4.revalidate();
			panel_4.repaint();
		} else if (e.getSource().equals(button)) {
			String phone = String.format("%s-%s-%s", c1.getText(), c2.getText(), c3.getText());
			if (!Pattern.matches("^[a-zA-Z0-9._%+-]{3,}@[a-zA-Z0-9.-]{2,}\\.[a-zA-Z]{2,}$", t2.getText())) {
				fail("이메일이 올바르지 않습니다.");
			} else if (!Pattern.matches("[0-9]{3}\\-[0-9]{4}\\-[0-9]{4}", phone)) {
				fail("휴대전화가 올바르지 않습니다.");
			} else if (!DB.isTrue("select count(*) from user where u_name = ? and u_phone = ? and u_email = ?", t1.getText(), phone, t2.getText())) {
				fail("일치하는 정보를 가진 회원이 존재하지 않습니다.");
			} else {
				ok(String.format("회원님의 아이디는 '%s'입니다.", DB.getString("select u_id from user where u_name = ? and u_phone = ? and u_email = ?", t1.getText(), phone, t2.getText())));
			}
		}
	}

}
