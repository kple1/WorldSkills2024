package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Form.Login;
import Form.Main;
import Model.Panel;
import Utils.DB;

public class FollowFollowing extends Panel {
	private JTable table;
	DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "번호", "이름", "ID" });
	public FollowFollowing(String state) {
		setLayout(new BorderLayout(0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.SOUTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel top = new JPanel(new BorderLayout());
		panel.add(top, BorderLayout.NORTH);

		var follower = new JLabel();
		setBold(follower, 16);
		top.add(follower, BorderLayout.WEST);

		var exit = new JLabel();
		exit.setSize(30, 30);
		exit.setIcon(imageSize(new ImageIcon("datafiles/icon/next.png"), exit));
		top.add(exit, BorderLayout.EAST);

		var scroll = new JScrollPane();
		panel.add(scroll, BorderLayout.CENTER);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					var pm = new JPopupMenu();
					var a = new JMenuItem("채팅하기");
					a.addActionListener(e2 -> {
						Main.view.removeAll();
						Main.view.setLayout(new BorderLayout());
						Main.view.add(new Chatting(DB.getString("select u_name from user where id = ?", table.getValueAt(table.getSelectedRow(), 2))));
						Main.view.revalidate();
					});
					var b = new JMenuItem("팔로우 취소");
					b.addActionListener(e1 -> {
						List<String> list = new ArrayList<>();
						String[] getFollowing = DB.getString("select u_following from user where id = ?", table.getValueAt(table.getSelectedRow(), 2)).split(",");
						String getNo = DB.getString("select u_no from user where id = ?", table.getValueAt(table.getSelectedRow(), 2));
						for (int i = 0; i < getFollowing.length; i++) {
							if (!getFollowing[i].equals(Login.no)) {
								list.add(getFollowing[i]);
							}
						}
						DB.update("update user set u_following = ? where u_no = ?", String.join(",", list), getNo);
						reload(state, model, follower);
					});
					pm.add(a);
					pm.add(b);
					pm.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		scroll.setViewportView(table);
		table.setModel(model);
		
		reload(state, model, follower);
	}

	void reload(String state, DefaultTableModel model, JLabel follower) {
		model.setRowCount(0);
		var a = DB.getFollowList();
		if (state.equals("팔로워")) {
			follower.setText("팔로워 " + a.size() + "명");
			for (int i = 0; i < a.size(); i++) {
				model.addRow(new Object[] { i + 1, a.get(i)[0], a.get(i)[1] });
			}
		} else {
			//팔로잉 만들어야 하는데 똑같은 레파토리라 안 만듦 사실 귀찮음 ㅎㅎ
		}
	}
}
