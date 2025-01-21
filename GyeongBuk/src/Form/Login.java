package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ChildPanel.RouteView;
import Utils.DB;
import Utils.ImageSize;
import Utils.Msg;
import Utils.Placeholder;

public class Login extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static String id = "user001";
	public static int no = DB.getInt("select u_no from user where u_id = ?", id);

	public static void main(String[] args) {
		new Login().setVisible(true);
	}

	public Login() {
		setBounds(0, 0, 500, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("로그인");
		setLocationRelativeTo(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel logo = new JLabel();
		logo.setSize(50, 50);
		logo.setIcon(ImageSize.set(new ImageIcon("datafiles/로고.png")));
		topPanel.add(logo, BorderLayout.WEST);
		
		JLabel label = new JLabel("\uB85C\uADF8\uC778");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		topPanel.add(label, BorderLayout.CENTER);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		topPanel.add(horizontalStrut_2, BorderLayout.EAST);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(0, 60));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(40);
		bottomPanel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(40);
		bottomPanel.add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		bottomPanel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1, 10, 0));
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		Placeholder.normalSet("자동 입력 방지 문자를 입력해주세요.", textField);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton bt1 = new JButton("\uB85C\uADF8\uC778");
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.sort(list);
				if (textField_1.getText().isEmpty() || textField_2.getText().isEmpty()) {
					Msg.fail("빈칸이 있습니다.");
				} else if (textField_2.getText().equals("admin") && textField_1.getText().equals("1234")) {
					Msg.ok("관리자님 환영합니다.");
					new AddRoute().setVisible(true);
				} else if (!DB.isTrue("select count(*) from user where u_id = ? and u_pw = ?", textField_2.getText(), textField_1.getText())) {
					Msg.fail("일치하는 회원이 없습니다.");
				} else if (!String.join("", list).equals(textField.getText())) {
					Msg.fail("자동 입력 방지 문자가 틀립니다.");
					System.out.println(String.join("", list));
					reload(panel_7);
				} else {
					var name = DB.getString("select u_nickname from user where u_id = ?", textField_2.getText());
					Msg.ok(name + "님 환영합니다.");
					dispose();
					new Main().setVisible(true);
				}
			}
		});
		panel_1.add(bt1);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 60));
		centerPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 0));
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new GridLayout(2, 1, 0, 5));
		
		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_1.setFont(new Font("Gulim", Font.PLAIN, 12));
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(2, 1, 0, 5));

		textField_2 = new JTextField();
		panel_3.add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		centerPanel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(40);
		panel_4.add(horizontalStrut_3, BorderLayout.WEST);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(40);
		panel_4.add(horizontalStrut_4, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut_1, BorderLayout.SOUTH);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(50, 0));
		panel_5.add(panel_6, BorderLayout.EAST);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(2, 4, 0, 0));
		
		JLabel refresh = new JLabel("");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reload(panel_7);
			}
		});
		refresh.setSize(50, 50);
		refresh.setIcon(ImageSize.set(new ImageIcon("datafiles/새로고침.png")));
		refresh.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(refresh);
		
		reload(panel_7);
	}
	
	JPanel panel_7;
	List<String> list = new ArrayList<>();
	boolean mynameisjuntae = true;
	void reload(JPanel panel_7) {
		panel_7.removeAll();
		list.removeAll(list);
		
		var random = new Random();
		int[] rowdata = new int[4];

		
		for (int i = 0; i < 8; i++) {
			mynameisjuntae = true;
			int value = random.nextInt(2);
			int number = random.nextInt(10);
			
			var box = new JLabel();
			box.setHorizontalAlignment(SwingConstants.CENTER);
			box.setBorder(BorderFactory.createLineBorder(Color.black));
			
			if (i < 4) { 
				rowdata[i] = value;
				if (value == 1) {
					if (!list.contains(number + "")) {
						box.setText(number + "");
						list.add(number + "");
					} else {
						i = i - 1;
						mynameisjuntae = false;
					}
				}
			} else {
				if (rowdata[i - 4] == 0) {
					if (!list.contains(number + "")) {
						box.setText(number + ""); 
						list.add(number + "");
					} else {
						i = i - 1;
						mynameisjuntae = false;
					}
				}
			}
			
			if (mynameisjuntae) {
				panel_7.add(box);
			}
		}
		
		panel_7.revalidate();
		panel_7.repaint();
	}
}