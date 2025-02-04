package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Frame;
import Utils.DB;

public class Login extends Frame {
	JLabel login;
	LoginModel m1, m2;
	public static String id = "user01";
	public static String no = "1";
	public static void main(String[] args) {
		new Login().setVisible(true);
	}
	
	public Login() {
		setFrame("로그인", 450, 330);
		getContentPane().setBackground(Color.white);
		
		Component vs1 = Box.createVerticalStrut(20);
		getContentPane().add(vs1, BorderLayout.NORTH);
		
		Component vs2 = Box.createVerticalStrut(20);
		getContentPane().add(vs2, BorderLayout.SOUTH);
		
		Component hs1 = Box.createHorizontalStrut(20);
		getContentPane().add(hs1, BorderLayout.WEST);
		
		Component hs2 = Box.createHorizontalStrut(20);
		getContentPane().add(hs2, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel title = new JLabel("LOGIN");
		title.setForeground(Color.CYAN);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title, BorderLayout.NORTH);
		setBold(title, 30);
		
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottom.setBackground(Color.white);
		panel.add(bottom, BorderLayout.SOUTH);
		
		login = new JLabel("로그인");
		login.setForeground(Color.CYAN);
		bottom.add(login);
		setPlain(login, 16);
		
		JPanel view = new JPanel();
		view.setBackground(Color.white);
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(null);
		
		JPanel t1 = new JPanel();
		m1 = new LoginModel("Id");
		t1.setBounds(43, 35, 311, 34);
		view.add(t1);
		t1.setLayout(new BorderLayout(0, 0));
		t1.add(m1);
		
		JPanel t2 = new JPanel();
		m2 = new LoginModel("Password");
		t2.setBounds(43, 109, 311, 34);
		view.add(t2);
		t2.setLayout(new BorderLayout(0, 0));
		t2.add(m2);
		
		login.addMouseListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(login)) {
			if (m1.getText().isEmpty() || m2.getText().isEmpty()) {
				fail("빈칸이 존재합니다.");
			} else if (!DB.isTrue("select count(*) from user where id = ? or pw = ?", m1.getText(), m2.getText())) {
				fail("존재하는 회원이 없습니다.");
			} else {
				id = m1.getText();
				no = DB.getString("select u_no from user where id = ?", m1.getText());
				ok(String.format("%s님, 로그인되었습니다.", DB.getString("select u_name from user where id = ?", m1.getText())));
				dispose();
				new Main().setVisible(true);
			}
		}
	}
}

class LoginModel extends Model.Panel {
	public JTextField textField;
	public LoginModel(String placeholder) {
		setLayout(new BorderLayout());
		
		var line = new JPanel();
		setSize(line, 0, 1);
		line.setBackground(Color.black);
		add(line, BorderLayout.SOUTH);
		
		textField = new JTextField();
		setPlain(textField, 16);
		placeholder(textField, placeholder);
		add(textField, BorderLayout.CENTER);
	}
	
	public String getText() {
		return textField.getText();
	}
}
