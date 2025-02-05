package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Frame;
import Utils.DB;

public class Login extends Frame {
	private JTextField id;
	private JTextField pw;
	public static String no = "1";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Login().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Login() {
		setFrame("�α���", 400, 200);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton login = new JButton("\uB85C\uADF8\uC778");
		login.addActionListener(e->{
			var c = DB.getId(id.getText());
			if (id.getText().isEmpty() || pw.getText().isEmpty()) {
				fail("��ĭ�� �����մϴ�.");
			} else if (!c[0].equals("1")) {
				fail("�������� �ʴ� ȸ���Դϴ�.");
			} else if (!DB.isTrue("select count(*) from " + c[2] + " where pw = ?", pw.getText())) {
				fail("��й�ȣ�� Ȯ�����ּ���.");
			} else {
				String s = c[2].equals("student") ? "�л�" : "����";
				String f = c[2].equals("student") ? "s_no" : "p_no";
				String n = DB.getString("select name from " + c[2] + " where id = ?", id.getText());
				ok(String.format("%s %s�� ȯ���մϴ�.", n, s));
				no = DB.getString("select " + f + " from " + c[2] + " where id = ?", id.getText());
				dispose();
				if (s.equals("�л�")) {
					new StudentMain().setVisible(true);
				} else {
					new ProfessorMain().setVisible(true);
				}
			}
		});
		panel.add(login, BorderLayout.SOUTH);
		
		JPanel view = new JPanel();
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(2, 2, 0, 15));
		
		JLabel l1 = new JLabel("ID");
		view.add(l1);
		
		id = new JTextField();
		view.add(id);
		id.setColumns(10);
		
		JLabel l2 = new JLabel("PW");
		view.add(l2);
		
		pw = new JTextField();
		pw.setColumns(10);
		view.add(pw);
	}
}

