package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ChildPanel.GraphModel;
import ChildPanel.WeekCategory;
import Utils.DB;
import Utils.ImageSize;

public class Graph extends JFrame implements ActionListener {

	JLabel left,right,title,image,day;
	JLayeredPane view;
	JComboBox<String> comboBox;
	public static void main(String[] args) {
		new Graph().setVisible(true);
	}

	public Graph() {
		setBounds(0, 0, 800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("통계");
		setLocationRelativeTo(null);
		
		JPanel top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		top.add(horizontalStrut, BorderLayout.WEST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		top.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		top.add(verticalStrut_1, BorderLayout.SOUTH);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		top.add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		top.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel dp = new JPanel();
		dp.setBackground(new Color(255, 255, 255));
		panel_1.add(dp, BorderLayout.SOUTH);
		
		left = new JLabel("\u25C0");
		dp.add(left);
		
		day = new JLabel("2025-01-28");
		day.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		dp.add(day);
		
		right = new JLabel("\u25B6");
		dp.add(right);
		
		title = new JLabel("\uC77C\uBCC4 TOP5");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(title, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("일별 TOP5");
		comboBox.addItem("주별(카테고리)");
		comboBox.setPreferredSize(new Dimension(90, 30));
		panel_2.add(comboBox, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setPreferredSize(new Dimension(90, 30));
		panel.add(panel_3, BorderLayout.EAST);
		
		view = new JLayeredPane();
		getContentPane().add(view, BorderLayout.CENTER);
		
		image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setBounds(0, 0, 784, 353);
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/일별커피잔.png"), image));
		view.add(image, JLayeredPane.DEFAULT_LAYER);
		
		GraphModel[] panels = new GraphModel[5];
		var list = DB.getTop5();
		int[] x = new int[] {200, 386, 325, 230, 386};
		int[] y = new int[] {0, 50, 117, 189, 192};
		
		for (int i = 0; i < 5; i++) {
		    panels[i] = new GraphModel(list.get(i)[0], Integer.parseInt(list.get(i)[1]));
		    panels[i].setLocation(x[i], y[i]);
		    view.add(panels[i], JLayeredPane.PALETTE_LAYER);
		}
		
		comboBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(comboBox)) {
			view.removeAll();
			if (comboBox.getSelectedIndex() == 0) {
				title.setText("일별 TOP5");
				day.setText("2024-08-27");
				
				view.add(image, JLayeredPane.DEFAULT_LAYER);
				
				GraphModel[] panels = new GraphModel[5];
				var list = DB.getTop5();
				int[] x = new int[] {200, 386, 325, 230, 386};
				int[] y = new int[] {0, 50, 117, 189, 192};
				
				for (int i = 0; i < 5; i++) {
				    panels[i] = new GraphModel(list.get(i)[0], Integer.parseInt(list.get(i)[1]));
				    panels[i].setLocation(x[i], y[i]);
				    view.add(panels[i], JLayeredPane.PALETTE_LAYER);
				}
			} else {
				title.setText("주별(카테고리)");
				day.setText("2024-08-28~08-24");
				
				var wc = new WeekCategory();
				wc.setBounds(0, 0, 784, 353);
				view.add(wc, JLayeredPane.DEFAULT_LAYER);
			}
			view.revalidate();
			view.repaint();
		}
	}
}
