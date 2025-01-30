package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ChildPanel.AdminModel;
import Utils.DB;
import Utils.ImageSize;

public class Admin extends JFrame implements ActionListener {

	JButton graph;
	public static void main(String[] args) {
		new Admin().setVisible(true);
	}

	public Admin() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 455, 600);
		setTitle("°ü¸®ÀÚ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		graph = new JButton("\uD1B5\uACC4");
		graph.setForeground(new Color(255, 255, 255));
		graph.setBackground(new Color(243, 215, 12));
		graph.setPreferredSize(new Dimension(80, 35));
		graph.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel.add(graph);
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setPreferredSize(new Dimension(0, 100));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(255, 255, 255));
		topPanel.add(titlePanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uC624\uB298\uC758 \uC8FC\uBB38\uD604\uD669");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		titlePanel.add(lblNewLabel);
		
		JLabel image = new JLabel("");
		image.setSize(30, 30);
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/»õ·Î°íÄ§.png"), image));
		image.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				reload();
			}
		});
		titlePanel.add(image);
		
		var getToday = LocalDate.now();
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		JLabel today = new JLabel(getToday.format(formatter));
		today.setBackground(new Color(255, 255, 255));
		today.setHorizontalAlignment(SwingConstants.CENTER);
		today.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		topPanel.add(today, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		topPanel.add(panel_1, BorderLayout.SOUTH);
		
		orderCount = new JLabel("ÃÑÁÖ¹®°Ç¼ö : 110°Ç (¹ÌÃ³¸® 10°Ç)");
		orderCount.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_1.add(orderCount);
		
		news = new JLabel("NEW");
		news.setForeground(new Color(255, 0, 0));
		news.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_1.add(news);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.gray));
		panel_2.add(scrollPane, BorderLayout.CENTER);
		
		view = new JPanel();
		view.setBackground(new Color(128, 128, 128));
		scrollPane.setViewportView(view);
		view.setLayout(new GridLayout(0, 1, 0, 0));
		
		reload();
	}
	
	JPanel view;
	JLabel orderCount, news;
	void reload () {
		view.removeAll();
		notCompleCount = 0;
		var list = DB.getTodayOrderInfo();
		for (int i = 0; i < list.size(); i++) {
			int capture = i;
			var model = new AdminModel(list.get(i)[0], list.get(i)[1], Integer.parseInt(list.get(i)[2]));
			model.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new OrderInformation(list.get(capture)[0]).setVisible(true);
				}
			});
			if (DB.getInt("select complete from orderinfor where no = ?", list.get(i)[0]) == 0) {
				model.setYellow();
				++notCompleCount;
			}
			view.add(model);
		}
		orderCount.setText("ÃÑÁÖ¹®°Ç¼ö : " + list.size() + "°Ç (¹ÌÃ³¸® " + notCompleCount + "°Ç)");
		
		if (notCompleCount != 0) {
			news.setVisible(true);
		} else {
			news.setVisible(false);
		}
		view.revalidate();
		view.repaint();
	}

	int notCompleCount;
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(graph)) {
			dispose();
			new Graph().setVisible(true);
		}
	}
}
