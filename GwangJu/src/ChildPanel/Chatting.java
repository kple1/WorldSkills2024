package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Form.Login;
import Model.Panel;
import Utils.DB;

public class Chatting extends Panel {
	private JTextField text;

	public Chatting(String getName) {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setSize(500, 50);
		add(panel, BorderLayout.NORTH);

		var left = new JLabel();
		left.setSize(50, 50);
		left.setIcon(imageSize(new ImageIcon("datafiles/icon/next.png"), left));

		var mypage = new JLabel();
		mypage.setSize(50, 50);
		mypage.setIcon(imageSize(new ImageIcon("datafiles/icon/mypage.png"), mypage));

		var name = new JLabel(getName);
		setPlain(name, 20);

		panel.add(left);
		panel.add(mypage);
		panel.add(name);

		JPanel view = new JPanel();
		add(view, BorderLayout.CENTER);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

		var line = new JPanel();
		setMSize(line, 500, 1);
		view.add(line);

		String getNo = DB.getString("select u_no from user where u_name = ?", getName);
		var list = DB.getChat(getNo, Login.no);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[2].equals(Login.no)) {
				view.add(new ChattingTakeModel(list.get(i)[3], list.get(i)[4]));
			} else {
				view.add(new ChattingGiveModel(list.get(i)[3], list.get(i)[4]));
			}
		}

		var bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(500, 50));
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(10);
		bottomPanel.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		bottomPanel.add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(10);
		bottomPanel.add(verticalStrut, BorderLayout.SOUTH);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		bottomPanel.add(verticalStrut_1, BorderLayout.NORTH);

		JPanel sendPanel = new JPanel();
		bottomPanel.add(sendPanel, BorderLayout.CENTER);
		sendPanel.setLayout(new BorderLayout(0, 0));

		JLabel send = new JLabel("");
		send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!text.getText().isEmpty()) {
					var get = LocalDateTime.now();
					var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH½ÃmmºÐ");
					view.add(new ChattingGiveModel(text.getText(), get.format(formatter)));
					view.revalidate();
					view.repaint();
				} else {
					fail("ºóÄ­ÀÔ´Ï´Ù.");
				}
			}
		});
		send.setSize(30, 30);
		send.setIcon(imageSize(new ImageIcon("datafiles/icon/next.png"), send));
		sendPanel.add(send, BorderLayout.EAST);

		text = new JTextField();
		sendPanel.add(text, BorderLayout.CENTER);
		text.setColumns(10);
	}

}

class ChattingTakeModel extends Panel {
	public ChattingTakeModel(String msg, String getDate) {
		setMSize(this, 500, 30);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		var img = new JLabel();
		img.setSize(20, 20);
		img.setIcon(imageSize(new ImageIcon("datafiles/icon/mypage.png"), img));

		var message = new JLabel(msg);
		setPlain(message, 12);

		var date = new JLabel(getDate);
		setPlain(date, 12);
		date.setForeground(Color.LIGHT_GRAY);

		add(img);
		add(message);
		add(date);
	}
}

class ChattingGiveModel extends Panel {
	public ChattingGiveModel(String msg, String getDate) {
		setMSize(this, 500, 40);
		setLayout(new FlowLayout(FlowLayout.RIGHT));

		var message = new JLabel(); 
		message.setText(formatMessage(msg));
		setPlain(message, 12);
		message.setOpaque(true);
		message.setBackground(Color.cyan);

		var date = new JLabel(getDate);
		setPlain(date, 12);
		date.setForeground(Color.LIGHT_GRAY);

		add(date);
		add(message);

	}
	private String formatMessage(String msg) {
        if (msg.length() > 25) {
            int mid = msg.length() / 2;
            return "<html>" + msg.substring(0, mid) + "<br>" + msg.substring(mid) + "</html>";
        }
        return msg;
    }
}
