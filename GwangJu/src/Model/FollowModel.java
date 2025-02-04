package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Form.Login;
import Utils.DB;

public class FollowModel extends Panel {

	public FollowModel(String getName, String postCount) {
		setLayout(new BorderLayout(0, 0));
		setBorder(this, Color.LIGHT_GRAY);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(10);
		add(verticalStrut, BorderLayout.SOUTH);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		add(verticalStrut_1, BorderLayout.NORTH);

		JPanel view = new JPanel();
		add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		view.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		panel.add(horizontalStrut_3, BorderLayout.EAST);

		JLabel image = new JLabel("");
		image.setSize(50, 50);
		image.setIcon(imageSize(new ImageIcon("datafiles/icon/mypage.png"), image));
		image.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(image, BorderLayout.CENTER);

		JButton follow = new JButton("\uD314\uB85C\uC6B0");
		follow.setBackground(Color.green);
		setPlain(follow, 12);
		follow.addActionListener(e -> {
			String no = DB.getString("select u_no from user where u_name = ?", getName);
			if (follow.getText().equals("ÆÈ·Î¿ì")) {
				follow.setText("ÆÈ·Î¿ì Ãë¼Ò");
				follow.setBackground(Color.LIGHT_GRAY);

				String getFollow = DB.getString("select u_following from user where id = ?", Login.id);
				if (getFollow.isEmpty()) {
					DB.update("update user set u_following = ? where id = ?", no, Login.id);
				} else {
					String[] followList = getFollow.split(",");
					DB.update("update user set u_following = ? where id = ?", String.join(",", followList) + "," + no, Login.id);
				}
			} else {
				follow.setText("ÆÈ·Î¿ì");
				follow.setBackground(Color.GREEN);
				
				List<String> list = new ArrayList<>();
				String[] getFollow = DB.getString("select u_following from user where id = ?", Login.id).split(",");
				for (int i = 0; i < getFollow.length; i++) {
					if (!getFollow[i].equals(no)) {
						list.add(getFollow[i]);
					}
				}
				DB.update("update user set u_following = ? where id = ?", String.join(",", list), Login.id);
			}
		});
		view.add(follow, BorderLayout.SOUTH);

		JPanel grid = new JPanel();
		view.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel name = new JLabel(getName);
		setPlain(name, 12);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		grid.add(name);

		JLabel pcLabel = new JLabel("Æ÷½ºÆ®" + postCount + "°³");
		setBold(pcLabel, 16);
		pcLabel.setHorizontalAlignment(SwingConstants.CENTER);
		grid.add(pcLabel);

	}

}
