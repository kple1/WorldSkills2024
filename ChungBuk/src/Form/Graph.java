package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Model.Panel;
import Utils.DB;

public class Graph extends Frame {
	public JPanel infoPanel;
	public JComboBox comboBox;
	public JPanel border;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public JPanel rightView;
	public JPanel centerView;
	public JLabel title;
	public Component verticalStrut_2;
	public JPanel view;
	public JPanel names;
	public JPanel bars;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Graph().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Graph() {
		setFrame("통계", 600, 400);
		setSize(600, 400);
		
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(180, 0));
		getContentPane().add(infoPanel, BorderLayout.EAST);
		infoPanel.setLayout(new BorderLayout(0, 0));
		
		comboBox = new JComboBox();
		String[] cList = new String[] {"가솔린",
				"LPG",
				"디젤",
				"전기차"};
		for (int i = 0; i < 4; i++) {
			comboBox.addItem(cList[i]);
		}
		infoPanel.add(comboBox, BorderLayout.NORTH);
		
		border = new JPanel();
		infoPanel.add(border, BorderLayout.CENTER);
		border.setLayout(new BorderLayout(0, 0));
		
		verticalStrut = Box.createVerticalStrut(79);
		border.add(verticalStrut, BorderLayout.NORTH);
		
		verticalStrut_1 = Box.createVerticalStrut(120);
		border.add(verticalStrut_1, BorderLayout.SOUTH);
		
		rightView = new JPanel();
		border.add(rightView, BorderLayout.CENTER);
		rightView.setLayout(new BoxLayout(rightView, BoxLayout.Y_AXIS));
		
		centerView = new JPanel();
		getContentPane().add(centerView, BorderLayout.CENTER);
		centerView.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("\uC608\uC57D \uAC74\uC218 TOP5");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		centerView.add(title, BorderLayout.NORTH);
		
		verticalStrut_2 = Box.createVerticalStrut(40);
		centerView.add(verticalStrut_2, BorderLayout.SOUTH);
		
		view = new JPanel();
		centerView.add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
		
		names = new JPanel();
		view.add(names, BorderLayout.SOUTH);
		names.setLayout(new GridLayout(1, 5, 30, 0));
		
		bars = new JPanel();
		view.add(bars, BorderLayout.CENTER);
		bars.setLayout(new GridLayout(1, 5, 30, 0));
	
		comboBox.addActionListener(e -> {
			reload(comboBox.getSelectedIndex() + 1);
		});
		reload(1);
	}
	
	void reload(int index) {
		names.removeAll();
		bars.removeAll();
		rightView.removeAll();
		Color[] cs = new Color[] {Color.black, Color.blue, Color.red, Color.green, Color.yellow};
		var list = DB.getRank(index);
		for (int i = 0; i < 5; i++) {
			var panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			var box = new JLabel();
			box.setOpaque(true);
			box.setPreferredSize(new Dimension(10, 10));
			box.setBackground(cs[i]);
			panel.add(box);
			panel.add(new JLabel(list.get(i)[0]));
			rightView.add(panel);
		}
		
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i)[1]) > max) {
				max = Integer.parseInt(list.get(i)[1]);
			}
		}
		for (int i = 0; i < 5; i++) {
			var panel = new Bar(max, Integer.parseInt(list.get(i)[1]), cs[i]);
			bars.add(panel);
			names.add(new JLabel(list.get(i)[0].toString()));
		}
		
		names.revalidate();
		bars.revalidate();
		rightView.revalidate();
	}

}

class Bar extends Panel {
    Bar(int max, int count, Color color) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        var bar = new JPanel();
        bar.setBackground(color);
        bar.setBorder(BorderFactory.createLineBorder(Color.black));
        int size = (int) (280 * ((double) count / max));
        bar.setPreferredSize(new Dimension(50, size));

        add(Box.createVerticalGlue());
        add(bar);
    }
}

