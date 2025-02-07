package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Random;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Model.Panel;
import Util.DB;

public class Graph extends Frame {
	public Component verticalStrut;
	public JLabel l1;
	public JPanel westGrid;
	public JPanel panel;
	public JPanel line;
	public JPanel view;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Graph().setVisible(true);
	}

	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose) new Main("professor").setVisible(true);
	}
	public Graph() {
		setFrame("øπæ‡Top10", 600, 500);
		setSize(600, 500);
		
		verticalStrut = Box.createVerticalStrut(40);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		l1 = new JLabel("\uC608\uC57D Top10");
		l1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(l1, BorderLayout.NORTH);
		
		westGrid = new JPanel();
		getContentPane().add(westGrid, BorderLayout.WEST);
		westGrid.setPreferredSize(new Dimension(100, 0));
		westGrid.setLayout(new GridLayout(10, 1, 0, 10));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		line = new JPanel();
		line.setPreferredSize(new Dimension(1,0));
		line.setBackground(new Color(0, 0, 0));
		panel.add(line, BorderLayout.WEST);
		
		view = new JPanel();
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(10, 1, 0, 0));
		
		var list = DB.data();
		
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
		    if (Integer.parseInt(list.get(i)[1]) > max) {
		        max = Integer.parseInt(list.get(i)[1]);
		    }
		}
		for (int i = 0; i < 10; i++) {
			var label = new JLabel(list.get(i)[0]);
			setFont(12, label);
			westGrid.add(label);
			view.add(new Bar(Integer.parseInt(list.get(i)[1]), max));
		}
	}
}

class Bar extends Panel {
	Random r = new Random();
	Bar(int count, int max) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		var bar = new JPanel();
		
		int size = (int) (350 * ((double) count / max));
		bar.setPreferredSize(new Dimension(size, 30));
		bar.setBackground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		add(bar);
		
		var gun = new JLabel(count + "∞«");
		setFont(12, gun);
		add(gun);
	}
}
