package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.Frame;
import Utils.DB;

public class Login extends Frame {
	public JLabel title;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public JPanel bottom;
	public JPanel view;
	public JPanel pw;
	public JPanel id;
	public JButton login;

	JTextField t1, t2;
	public static String no = "1";
	public static void main(String[] args) {
		new Login().setVisible(true);
	}

	public Login() {
		setFrame("로그인", 350, 300);
		setSize(350, 300);
		
		title = new JLabel("\uB85C\uADF8\uC778");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		getContentPane().add(title, BorderLayout.NORTH);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		bottom = new JPanel();
		getContentPane().add(bottom, BorderLayout.SOUTH);
		
		view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(3, 1, 0, 5));
		
		id = new JPanel(new BorderLayout());
		id.setBorder(BorderFactory.createLineBorder(Color.black));

		var mypage = new JLabel();
		mypage.setVerticalAlignment(SwingConstants.CENTER);
		mypage.setSize(30, 30);
		mypage.setIcon(imageSize(new ImageIcon("datafiles/아이콘/mypage.png"), mypage));
		id.add(mypage, BorderLayout.WEST);
		
		t1 = new JTextField();
		id.add(t1, BorderLayout.CENTER);
		placeholder(t1, "아이디를 입력해주세요.");	
		view.add(id);
		
		pw = new JPanel(new BorderLayout());
		pw.setBorder(BorderFactory.createLineBorder(Color.black));
		view.add(pw);
		
		JLabel lock = new JLabel();
		lock.setVerticalAlignment(SwingConstants.CENTER);
		lock.setSize(30, 30);
		lock.setIcon(imageSize(new ImageIcon("datafiles/아이콘/login.png"), lock));
		pw.add(lock, BorderLayout.WEST);
		
		t2 = new JTextField();
		pw.add(t2, BorderLayout.CENTER);
		placeholder(t2, "비밀번호를 입력해주세요.");	
		
		login = new JButton("\uB85C\uADF8\uC778");
		login.addActionListener(e -> {
			if (t1.getText().equals("아이디를 입력해주세요.") || t2.getText().equals("비밀번호를 입력해주세요.")) {
				fail("빈칸이 존재합니다.");
				t1.requestFocusInWindow();
			} else if (t1.getText().equals("admin") && t2.getText().equals("1234")) {
				ok("관리자님 환영합니다.");
				dispose();
				new AdminHome().setVisible(true);
			} else if (!DB.isTrue("select count(*) from user where u_id = ? and u_pw = ?", t1.getText(), t2.getText())) {
				fail("아아디 또는 비밀번호가 일치하지 않습니다.");
			} else {
				String name = DB.getString("select u_name from user where u_id = ?", t1.getText());
				ok(String.format("%s님 환영합니다.", name));
				dispose();
				new Main().setVisible(true);
				Main.login.setIcon(imageSize(new ImageIcon("datafiles/아이콘/logout.png"), Main.login));
			}
		});
		login.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		login.setBackground(new Color(255, 128, 255));
		view.add(login);
		
		int c = 0;
		String[] ls = new String[] {"아이디찾기", "비밀번호찾기"};
		for (int i = 0; i < 5; i++) {
			var label = new JLabel();
			setBold(12, label);
			if (i == 0 || i == 2 || i == 4) {
				label.setText(" | ");
				bottom.add(label);
			} else {
				label.setText(ls[c]);
				bottom.add(label);
				++c;
			}
		}
	}
}
