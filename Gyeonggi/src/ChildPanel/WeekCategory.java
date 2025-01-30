package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Utils.DB;

public class WeekCategory extends JPanel {

	public WeekCategory() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.SOUTH);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.EAST);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel bottom = new JPanel();
		bottom.setBackground(Color.white);
		panel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1, 0, 0, 0));
		bottom.add(new JLabel());
		for (int i = 0; i < 8; i++) {
			var names = new JLabel(DB.getString("select name from category where cno = ?", i + 1));
			names.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			bottom.add(names);
		}

		JPanel west = new JPanel();
		west.setBackground(Color.white);
		panel.add(west, BorderLayout.WEST);
		west.setLayout(new GridLayout(0, 1, 0, 0));
		for (int i = 8; i >= 1; i--) {
			int re = 100 + 10 * i;
			var label = new JLabel(re + "");
			label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			west.add(label);
		}

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.white);
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel line = new JPanel();
		line.setPreferredSize(new Dimension(1, 0));
		line.setBackground(new Color(0, 0, 0));
		panel_3.add(line, BorderLayout.WEST);

		var layer = new JLayeredPane();
		layer.setBackground(Color.white);
		panel_3.add(layer, BorderLayout.CENTER);

		var view = new JPanel();
		view.setBounds(0, 0, 784, 315);
		view.setBackground(Color.white);
		layer.add(view, JLayeredPane.DEFAULT_LAYER);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));

		for (int i = 0; i < 8; i++) {
			var border = new JPanel();
			border.setBackground(Color.white);
			border.setLayout(new BorderLayout());

			var line1 = new JPanel();
			line1.setBackground(Color.black);
			line1.setPreferredSize(new Dimension(0, 1));

			view.add(Box.createVerticalStrut(21));
			border.add(line1, BorderLayout.CENTER);

			view.add(border);
			view.add(Box.createVerticalStrut(17));
		}

		var r = DB.rank();
		int x = 70;
		int max = Collections.max(r);
		double multiple = 1.5;
		double nextMultiple = 1.5;
		for (int i = 0; i < 8; i++) {
			Circle c = null;
			if (r.get(i) <= 180 && r.get(i) >= 160) {
				multiple = 1.52;
			} else if (r.get(i) <= 159 && r.get(i) >= 150) {
				multiple = 1.4;
			} else if (r.get(i) <= 149 && r.get(i) >= 140) {
				multiple = 1.1;
			} else if (r.get(i) <= 139 && r.get(i) >= 130) {
				multiple = 1.05;
			} else if (r.get(i) <= 129 && r.get(i) >= 120) {
				multiple = 0.7;
			}
			
			if (r.get(i) == max) {
				c = new Circle(Color.red);
			} else {
				c = new Circle(Color.blue);
			}
			c.setLocation(x + 88 * i, 315 - (int)(r.get(i) * multiple));
			layer.add(c, JLayeredPane.MODAL_LAYER);
			
			if (i != 7) {
				if (r.get(i + 1) <= 180 && r.get(i + 1) >= 160) {
					nextMultiple = 1.52;
				} else if (r.get(i + 1) <= 159 && r.get(i + 1) >= 150) {
					nextMultiple = 1.4;
				} else if (r.get(i + 1) <= 149 && r.get(i + 1) >= 140) {
					nextMultiple = 1.1;
				} else if (r.get(i + 1) <= 139 && r.get(i + 1) >= 130) {
					nextMultiple = 1.05;
				} else if (r.get(i + 1) <= 129 && r.get(i + 1) >= 120) {
					nextMultiple = 0.7;
				}
				
				var line1 = new Line((x + 88 * i) + 5, 320 - (int)(r.get(i) * multiple), x + 88 * (i + 1) + 5, 320 - (int)(r.get(i + 1) * nextMultiple));
				line1.setBounds(0, 0, 784, 315);
				layer.add(line1, JLayeredPane.PALETTE_LAYER);
			}
		}
	}
}
