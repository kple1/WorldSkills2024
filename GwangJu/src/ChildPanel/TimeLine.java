package ChildPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Form.Login;
import Model.Panel;
import Model.TimeLineModel;
import Utils.DB;

public class TimeLine extends Panel {

	/**
	 * Create the panel.
	 */
	public TimeLine() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		String[] getPin = DB.getString("select u_following from user where id = ?", Login.id).split(",");
		for (int i = 0; i < getPin.length; i++) {
			var get = DB.getTimeLine(getPin[i]);
			panel.add(new TimeLineModel((String)get[0], (String)get[1], (String)get[2], (byte[])get[3], (byte[])get[4], (String)get[5]));
		}
	}

}
