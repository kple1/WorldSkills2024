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
		setFrame("¾ÆÀÌµğ º¯°æ", 500, 300);
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
		if (state.equals("¾Æ¾Æµğ")) {
			l1.setText("¿ø·¡ »ç¿ëÇß´ø ¾ÆÀÌµğ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		} else {
			l1.setText("¿ø·¡ »ç¿ëÇß´ø ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		l1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		grid.add(l1);

		JPanel panel = new JPanel();
		grid.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JButton check = new JButton("\uD655\uC778");
		check.addActionListener(e -> {
			if (state.equals("¾ÆÀÌµğ")) {
				if (text1.getText().isEmpty()) {
					fail("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
				} else if (!DB.getString("select id from user where u_no = ?", Login.no).equals(text1.getText())) {
					fail("»ç¿ëÇÏ´ø ¾ÆÀÌµğ/ºñ¹Ğ¹øÈ£¿Í ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
				} else {
					ok("È®ÀÎµÇ¾ú½À´Ï´Ù. º¯°æÇÒ ¾ÆÀÌµğ/ºñ¹Ğ¹øÈ£¸¦ Àû¾îÁÖ¼¼¿ä.");
					checkVerify = true;
				}
			} else {
				if (text1.getText().isEmpty()) {
					fail("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
				} else if (!DB.getString("select pw from user where u_no = ?", Login.no).equals(text1.getText())) {
					fail("»ç¿ëÇÏ´ø ¾ÆÀÌµğ/ºñ¹Ğ¹øÈ£¿Í ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
				} else {
					ok("È®ÀÎµÇ¾ú½À´Ï´Ù. º¯°æÇÒ ¾ÆÀÌµğ/ºñ¹Ğ¹øÈ£¸¦ Àû¾îÁÖ¼¼¿ä.");
					checkVerify = true;
				}
			}
		});
		check.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
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
		if (state.equals("¾ÆÀÌµğ")) {
			l2.setText("º¯°æÇÒ ¾ÆÀÌµğ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		} else {
			l2.setText("º¯°æÇÒ ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		l2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
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
		if (state.equals("¾ÆÀÌµğ")) {
			change.setText("¾ÆÀÌµğ º¯°æÇÏ±â");
		} else {
			change.setText("ºñ¹Ğ¹øÈ£ º¯°æÇÏ±â");
		}
		change.addActionListener(e -> {
			if (state.equals("¾ÆÀÌµğ")) {
				if (!checkVerify) {
					fail("È®ÀÎÀÌ ¿Ï·áµÇÁö ¾Ê¾Ò½À´Ï´Ù.");
				} else if (text2.getText().isEmpty()) {
					fail("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
				} else if (DB.isTrue("select count(*) from user where id = ?", text2.getText())) {
					fail("ÀÌ¹Ì ´©±º°¡°¡ »ç¿ëÇÏ°í ÀÖ´Â ¾ÆÀÌµğÀÔ´Ï´Ù.");
				} else {
					DB.update("update user set id = ? where u_no = ?", text2.getText(), Login.no);
					ok("¾ÆÀÌµğ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
					dispose();
					new Login().setVisible(true);
				}
			} else {
				if (!checkVerify) {
					fail("È®ÀÎÀÌ ¿Ï·áµÇÁö ¾Ê¾Ò½À´Ï´Ù.");
				} else if (text2.getText().isEmpty()) {
					fail("ºóÄ­ÀÌ ÀÖ½À´Ï´Ù.");
				} else if (!isValidPassword(text2.getText())) {
					fail("ºñ¹Ğ¹øÈ£ Çü½ÄÀ» È®ÀÎÇØÁÖ¼¼¿ä.");
				} else {
					DB.update("update user set pw = ? where u_no = ?", text2.getText(), Login.no);
					ok("ºñ¹Ğ¹øÈ£ º¯°æÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
					dispose();
					new Login().setVisible(true);
				}
			}
		});
		change.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		grid.add(change);
	}
	public boolean isValidPassword(String password) {
        if (password.length() < 5 || password.length() > 20) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (c >= '°¡' && c <= 'ÆR') {
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

        return hasSpecialChar; // Æ¯¼ö¹®ÀÚ°¡ ÀÖ¾î¾ß true
    }
}
