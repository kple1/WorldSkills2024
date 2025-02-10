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
		setFrame("�α���", 350, 300);
		setSize(350, 300);
		
		title = new JLabel("\uB85C\uADF8\uC778");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("���� ���", Font.BOLD, 20));
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
		mypage.setIcon(imageSize(new ImageIcon("datafiles/������/mypage.png"), mypage));
		id.add(mypage, BorderLayout.WEST);
		
		t1 = new JTextField();
		id.add(t1, BorderLayout.CENTER);
		placeholder(t1, "���̵� �Է����ּ���.");	
		view.add(id);
		
		pw = new JPanel(new BorderLayout());
		pw.setBorder(BorderFactory.createLineBorder(Color.black));
		view.add(pw);
		
		JLabel lock = new JLabel();
		lock.setVerticalAlignment(SwingConstants.CENTER);
		lock.setSize(30, 30);
		lock.setIcon(imageSize(new ImageIcon("datafiles/������/login.png"), lock));
		pw.add(lock, BorderLayout.WEST);
		
		t2 = new JTextField();
		pw.add(t2, BorderLayout.CENTER);
		placeholder(t2, "��й�ȣ�� �Է����ּ���.");	
		
		login = new JButton("\uB85C\uADF8\uC778");
		login.addActionListener(e -> {
			if (t1.getText().equals("���̵� �Է����ּ���.") || t2.getText().equals("��й�ȣ�� �Է����ּ���.")) {
				fail("��ĭ�� �����մϴ�.");
				t1.requestFocusInWindow();
			} else if (t1.getText().equals("admin") && t2.getText().equals("1234")) {
				ok("�����ڴ� ȯ���մϴ�.");
				dispose();
				new AdminHome().setVisible(true);
			} else if (!DB.isTrue("select count(*) from user where u_id = ? and u_pw = ?", t1.getText(), t2.getText())) {
				fail("�ƾƵ� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			} else {
				String name = DB.getString("select u_name from user where u_id = ?", t1.getText());
				ok(String.format("%s�� ȯ���մϴ�.", name));
				dispose();
				new Main().setVisible(true);
				Main.login.setIcon(imageSize(new ImageIcon("datafiles/������/logout.png"), Main.login));
			}
		});
		login.setFont(new Font("���� ���", Font.PLAIN, 16));
		login.setBackground(new Color(255, 128, 255));
		view.add(login);
		
		int c = 0;
		String[] ls = new String[] {"���̵�ã��", "��й�ȣã��"};
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
