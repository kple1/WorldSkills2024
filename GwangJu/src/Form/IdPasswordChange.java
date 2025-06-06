package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Frame;
import Utils.DB;

public class IdPasswordChange extends Frame {
	private JTextField text1;
	private JTextField text2;
	private boolean checkVerify = false;

	public static void main(String[] args) {
		new IdPasswordChange("asd").setVisible(true);
	}

	public IdPasswordChange(String state) {
		getContentPane().setBackground(Color.white);
		setFrame("아이디 변경", 500, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.SOUTH);

		JPanel grid = new JPanel();
		grid.setBackground(Color.white);
		getContentPane().add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(5, 1, 0, 5));

		JLabel l1 = new JLabel();
		if (state.equals("아아디")) {
			l1.setText("원래 사용했던 아이디를 입력해주세요.");
		} else {
			l1.setText("원래 사용했던 비밀번호를 입력해주세요.");
		}
		l1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		grid.add(l1);

		JPanel panel = new JPanel();
		grid.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JButton check = new JButton("\uD655\uC778");
		check.addActionListener(e -> {
			if (state.equals("아이디")) {
				if (text1.getText().isEmpty()) {
					fail("빈칸이 있습니다.");
				} else if (!DB.getString("select id from user where u_no = ?", Login.no).equals(text1.getText())) {
					fail("사용하던 아이디/비밀번호와 일치하지 않습니다.");
				} else {
					ok("확인되었습니다. 변경할 아이디/비밀번호를 적어주세요.");
					checkVerify = true;
				}
			} else {
				if (text1.getText().isEmpty()) {
					fail("빈칸이 있습니다.");
				} else if (!DB.getString("select pw from user where u_no = ?", Login.no).equals(text1.getText())) {
					fail("사용하던 아이디/비밀번호와 일치하지 않습니다.");
				} else {
					ok("확인되었습니다. 변경할 아이디/비밀번호를 적어주세요.");
					checkVerify = true;
				}
			}
		});
		check.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		setSize(check, 100, 0);
		panel.add(check, BorderLayout.EAST);

		JPanel line = new JPanel();
		setSize(line, 0, 1);
		line.setBackground(new Color(0, 0, 0));
		panel.add(line, BorderLayout.SOUTH);

		text1 = new JTextField();
		setBorder(text1, Color.white);
		panel.add(text1, BorderLayout.CENTER);
		text1.setColumns(10);

		JLabel l2 = new JLabel();
		if (state.equals("아이디")) {
			l2.setText("변경할 아이디를 입력해주세요.");
		} else {
			l2.setText("변경할 비밀번호를 입력해주세요.");
		}
		l2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		grid.add(l2);

		JPanel panel_1 = new JPanel();
		grid.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel line1 = new JPanel();
		setSize(line1, 0, 1);
		line1.setBackground(Color.BLACK);
		panel_1.add(line1, BorderLayout.SOUTH);

		text2 = new JTextField();
		setBorder(text2, Color.white);
		text2.setColumns(10);
		panel_1.add(text2, BorderLayout.CENTER);

		JButton change = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD\uD558\uAE30");
		if (state.equals("아이디")) {
			change.setText("아이디 변경하기");
		} else {
			change.setText("비밀번호 변경하기");
		}
		change.addActionListener(e -> {
			if (state.equals("아이디")) {
				if (!checkVerify) {
					fail("확인이 완료되지 않았습니다.");
				} else if (text2.getText().isEmpty()) {
					fail("빈칸이 있습니다.");
				} else if (DB.isTrue("select count(*) from user where id = ?", text2.getText())) {
					fail("이미 누군가가 사용하고 있는 아이디입니다.");
				} else {
					DB.update("update user set id = ? where u_no = ?", text2.getText(), Login.no);
					ok("아이디 변경이 완료되었습니다.");
					dispose();
					new Login().setVisible(true);
				}
			} else {
				if (!checkVerify) {
					fail("확인이 완료되지 않았습니다.");
				} else if (text2.getText().isEmpty()) {
					fail("빈칸이 있습니다.");
				} else if (!isValidPassword(text2.getText())) {
					fail("비밀번호 형식을 확인해주세요.");
				} else {
					DB.update("update user set pw = ? where u_no = ?", text2.getText(), Login.no);
					ok("비밀번호 변경이 완료되었습니다.");
					dispose();
					new Login().setVisible(true);
				}
			}
		});
		change.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		grid.add(change);
	}
	public boolean isValidPassword(String password) {
        if (password.length() < 5 || password.length() > 20) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (c >= '가' && c <= '힣') {
                return false;
            }
        }

        String specialChars = "!@#$%";
        boolean hasSpecialChar = false;
        for (char c : password.toCharArray()) {
            if (specialChars.indexOf(c) >= 0) {
                hasSpecialChar = true;
                break;
            }
        }

        return hasSpecialChar; // 특수문자가 있어야 true
    }
}
