package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Form.Login;
import Model.Panel;
import Utils.DB;

public class Alim extends Panel {

	List<String> getNotRead = new ArrayList<>(); //���� ���� ������ ��ȣ
	public Alim() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		reload();
	}
	
	public void reload () {
		getNotRead.removeAll(getNotRead);
		removeAll();
		var noCheckPanel = new JPanel(new BorderLayout());
		setMSize(noCheckPanel, 500, 30);
		var noCheckLabel = new JLabel("Ȯ�� �� �� �˸�");
		setBold(noCheckLabel, 16);
		
		var time = new JLabel("�ð���");
		setPlain(time, 12);
		
		noCheckPanel.add(noCheckLabel, BorderLayout.WEST);
		noCheckPanel.add(time, BorderLayout.EAST);
		
		add(noCheckPanel);
		
		var a = DB.getAlim();
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).split(",")[1].equals("0")) {
				getNotRead.add(a.get(i).split(",")[0]);
			}
		}
		
		for (int i = 0; i < getNotRead.size(); i++) {
			add(new AlimModel(DB.getString("select u_name from user where u_no = ?", getNotRead.get(i)), 1, this));
		}
		
		var flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		setMSize(flow, 500, 30);
		var checkLabel = new JLabel("Ȯ�� �� �˸�");
		setBold(checkLabel, 16);
		flow.add(checkLabel);
		add(flow);
		
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).split(",")[1].equals("0")) {
				add(new AlimModel(DB.getString("select u_name from user where u_no = ?", a.get(i).split(",")[0]), 0, this));
			}
		}
		revalidate();
		repaint();
	}

}

class AlimModel extends Panel {
	AlimModel(String name, int state, Alim alim) {
		setMSize(this, 500, 30);
		setLayout(new BorderLayout());
		var label = new JLabel(DB.getString("select u_name from user where u_no = ?", Login.no) + "��, " + name + "���� �ȷο� �߽��ϴ�.");
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (state == 0) {
					fail("�̹� Ȯ���� ���� �Դϴ�.");
				} else {
					ok("Ȯ�εǾ����ϴ�.");
					
					String no = DB.getString("select u_no from user where u_name = ?", name);
					DB.update("update alim set a_follow = ? where a_follow = ? and u_no = ?", no + ",1", no + ",0", Login.no);
					alim.reload();
				}
			}
		});
		setPlain(label, 12);
		
		var line = new JPanel();
		setSize(line, 0, 1);
		line.setBackground(Color.black);
		
		add(label, BorderLayout.CENTER);
		add(line, BorderLayout.SOUTH);
	}
}
