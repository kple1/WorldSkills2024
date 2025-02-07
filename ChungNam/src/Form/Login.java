package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import Model.Frame;
import Util.DB;

public class Login extends Frame {
	public JLayeredPane layer;
	public JLabel l1;
	public JTextField id;
	public JLabel lblPw;
	public JPasswordField pw;
	public JCheckBox c;
	public JButton login;
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
	int x = 0;
	int y = 211;
	Timer timer;
	public Login() {
		setFrame("·Î±×ÀÎ", 300, 300);
		setSize(300, 280);
		
		layer = new JLayeredPane();
		layer.setBackground(new Color(255, 255, 255));
		getContentPane().add(layer, BorderLayout.CENTER);
		getContentPane().setBackground(Color.white);
		
		l1 = new JLabel("Login");
		l1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		l1.setBounds(12, 10, 57, 27);
		layer.add(l1);
		
		JLabel l2 = new JLabel("ID");
		l2.setBounds(45, 69, 42, 15);
		layer.add(l2);
		
		id = new JTextField();
		id.setBounds(99, 66, 155, 21);
		layer.add(id);
		id.setColumns(10);
		
		lblPw = new JLabel("PW");
		lblPw.setBounds(45, 94, 42, 15);
		layer.add(lblPw);
		
		pw = new JPasswordField();
		pw.setEchoChar('¡Ü');
		pw.setColumns(10);
		pw.setBounds(99, 91, 155, 21);
		layer.add(pw);
		
		c = new JCheckBox("\uBE44\uBC00\uBC88\uD638 \uD655\uC778\uD558\uAE30");
		c.addActionListener(e -> {
		    if (c.isSelected()) {
		        pw.setEchoChar((char) 0);
		    } else {
		        pw.setEchoChar('¡Ü');
		    }
		});
		c.setBackground(new Color(255, 255, 255));
		c.setBounds(79, 126, 130, 23);
		layer.add(c);
		
		login = new JButton("\uB85C\uADF8\uC778");
		login.addActionListener(e->{
			if (id.getText().isEmpty() || pw.getText().isEmpty()) {
				fail("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
			} else if (id.getText().equals("admin") && pw.getText().equals("1234")) {
				ok("°ü¸®ÀÚ´Ô È¯¿µÇÕ´Ï´Ù.");
				dispose();
				new Main("professor").setVisible(true);
			} else {
				ok(DB.getString("select kname from user where uno = ?", no) + "(" + DB.getString("select ename from user where uno = ?", no) + ")´Ô È¯¿µÇÕ´Ï´Ù.");
				dispose();
				new Main("user").setVisible(true);
			}
		});
		login.setBounds(93, 185, 97, 27);
		layer.add(login);
		
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
	}
}
