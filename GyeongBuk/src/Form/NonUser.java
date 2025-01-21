package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Utils.ImageSize;
import Utils.Msg;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class NonUser extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		new NonUser().setVisible(true);
	}

	public NonUser() {
		setBounds(0, 0, 400, 258);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("비회원 정보입력");
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel logo = new JLabel();
		logo.setBounds(0, 0, 50, 50);
		logo.setIcon(ImageSize.set(new ImageIcon("datafiles/로고.png")));
		panel.add(logo, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("\uBE44\uD68C\uC6D0 \uC815\uBCF4\uC785\uB825");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_2, BorderLayout.CENTER);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_2, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 30));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_1.add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panel_1.add(verticalStrut, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
					Msg.fail("빈칸이 있습니다.");
				} else if (!Pattern.matches("010-\\d[4]-\\d[4]", textField.getText())) {
					Msg.fail("전화번호 형식을 확인해주세요.");
				} else if (textField_1.getText().length() != 4) {
					Msg.fail("비밀번호는 숫자 4자리로 입력해주세요.");
				} else {
					//예매 or 예매내역
				}
			}
		});
		panel_1.add(btnNewButton, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_1 = Box.createVerticalStrut(24);
		panel_4.add(verticalStrut_1, BorderLayout.SOUTH);
		
		Component verticalStrut_2 = Box.createVerticalStrut(59);
		panel_4.add(verticalStrut_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(150, 0));
		panel_4.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(2, 0, 0, 5));
		
		JLabel lblNewLabel_3 = new JLabel("\uC804\uD654\uBC88\uD638");
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		panel_3.add(lblNewLabel);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(2, 0, 0, 5));
		
		textField_1 = new JTextField();
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		panel_5.add(textField);
		textField.setColumns(10);
		
	}
}
