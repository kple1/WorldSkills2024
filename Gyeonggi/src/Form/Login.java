package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Utils.DB;
import Utils.Msg;
import Utils.Placeholder;

public class Login extends JFrame {
	private JTextField pwField;
	private JTextField idField;
	public static String id = "user001";
	public static String pw = "user001!";
	public static String no = "1";

	public static void main(String[] args) {
		new Login().setVisible(true);
	}

	public Login() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("로그인");
		setBounds(0, 0, 300, 250);
		setLocationRelativeTo(null);
		
		Component horizontalStrut = Box.createHorizontalStrut(30);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		Component verticalStrut = Box.createVerticalStrut(30);
		getContentPane().add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(30);
		getContentPane().add(verticalStrut_1, BorderLayout.SOUTH);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(30);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(4, 0, 0, 10));
		
		idField = new JTextField();
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		Placeholder.set(idField, "ID");
		view.add(idField);
		
		pwField = new JTextField();
		pwField.setHorizontalAlignment(SwingConstants.CENTER);
		Placeholder.set(pwField, "PW");
		view.add(pwField);
		
		Component verticalStrut_2 = Box.createVerticalStrut(15);
		view.add(verticalStrut_2);
		
		JPanel buttonPanel = new JPanel();
		view.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(1, 2, 5, 0));
		
		JButton login = new JButton("\uB85C\uADF8\uC778");
		login.addActionListener(e -> {
			if (idField.getText().isEmpty()) {
				Msg.fail("빈칸이 있습니다.");
			} else if (idField.getText().equals("admin") && pwField.getText().equals("1234")) {
			 	Msg.ok("관리자님 환영합니다.");
			 	dispose();
			 	new Admin().setVisible(true);
			} else if (!DB.isTrue("select count(*) from user where id = ? and pw = ?", idField.getText(), pwField.getText())) {
				Msg.fail("없는 회원입니다.");
				idField.setText("");
				pwField.setText("");
				idField.requestFocus();
			} else {
				var name = DB.getString("select uname from user where uid = ?", idField.getText());
				Msg.ok(name + "님 환영합니다.");
				id = idField.getText();
				pw = pwField.getText();
				no = DB.getString("select no from user where uid = ?", idField.getText());
				dispose();
				new Main().setVisible(true);
			}
		});
		login.setForeground(new Color(255, 255, 255));
		login.setBackground(new Color(255, 128, 0));
		buttonPanel.add(login);
		
		JButton exit = new JButton("\uC885\uB8CC");
		exit.addActionListener(e -> {
			dispose();
		});
		exit.setForeground(Color.WHITE);
		exit.setBackground(new Color(255, 128, 0));
		buttonPanel.add(exit);
	}
}
