package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ChildPanel.ReservationStateChange;
import Utils.DB;
import Utils.ImageSize;

public class ReservationList extends JFrame {
	public static JTextField oneField;
	public static JTextField twoField;
	private List<Object[]> list = DB.getUserTicket();

	public static void main(String[] args) {
		new ReservationList().setVisible(true);
	}

	public ReservationList() {
		setBounds(0, 0, 600, 500);
		setLocationRelativeTo(null);
		setTitle("¿¹¸Å ³»¿ª");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		JLabel logo = new JLabel("");
		logo.setSize(50, 50);
		logo.setIcon(ImageSize.set(new ImageIcon("datafiles/·Î°í.png")));
		topPanel.add(logo, BorderLayout.WEST);

		JLabel title = new JLabel("\uC608\uB9E4 \uB0B4\uC5ED");
		title.setFont(new Font("±¼¸²", Font.PLAIN, 14));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(title, BorderLayout.CENTER);

		Component horizontalStrut_2 = Box.createHorizontalStrut(40);
		topPanel.add(horizontalStrut_2, BorderLayout.EAST);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(0, 350));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut, BorderLayout.WEST);

		Component verticalStrut = Box.createVerticalStrut(20);
		bottomPanel.add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		bottomPanel.add(verticalStrut_1, BorderLayout.SOUTH);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		bottomPanel.add(horizontalStrut_1, BorderLayout.EAST);

		JScrollPane scrollPane = new JScrollPane();
		bottomPanel.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel view = new JPanel();
		view.setPreferredSize(new Dimension(0, 80));
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(2, 1, 5, 0));

		JPanel info = new JPanel();
		view.add(info);
		info.setLayout(new BorderLayout(0, 0));

		JLabel ratingLabel = new JLabel("\uBC14\uB78C (VIP \u2660)");
		String getName = DB.getString("select u_nickname from user where u_no = ?", Login.no);
		if (list.size() > 50 && list.size() <= 100) {
			ratingLabel.setText(getName + "(VIP ¢¼)");
			ratingLabel.setForeground(Color.GREEN);
		} else if (list.size() > 100) {
			ratingLabel.setText(getName + "(SVIP ¢À)");
			ratingLabel.setForeground(Color.BLUE);
		} else {
			ratingLabel.setText(getName + "(ÀÏ¹Ý)");
			ratingLabel.setForeground(Color.BLACK);
		}
		ratingLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		info.add(ratingLabel, BorderLayout.WEST);

		JLabel searchCount = new JLabel("\uAC80\uC0C9\uAC74\uC218 : 78\uAC74");
		searchCount.setText("°Ë»ö°Ç¼ö : " + list.size() + "°Ç");
		searchCount.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		searchCount.setHorizontalAlignment(SwingConstants.CENTER);
		info.add(searchCount, BorderLayout.CENTER);

		JLabel price = new JLabel("\uAE08\uC561 : 1,138,635");
		price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		info.add(price, BorderLayout.EAST);

		JPanel func = new JPanel();
		view.add(func);

		JLabel searchStandard = new JLabel("\uAC80\uC0C9 \uAE30\uC900");
		func.add(searchStandard);

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.addItem("³¯Â¥");
		comboBox.addItem("Áö¿ª");
		comboBox.setPreferredSize(new Dimension(60, 20));
		func.add(comboBox);

		JLabel calendar = new JLabel("");
		calendar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Calendar("reserlist").setVisible(true);
			}
		});
		calendar.setSize(25, 25);
		calendar.setIcon(ImageSize.setSize(new ImageIcon("datafiles/´Þ·Â.png"), calendar));
		func.add(calendar);

		oneField = new JTextField();
		func.add(oneField);
		oneField.setColumns(10);

		JLabel hipeun = new JLabel("-");
		func.add(hipeun);

		twoField = new JTextField();
		twoField.setColumns(10);
		func.add(twoField);

		JButton searchButton = new JButton("\uAC80\uC0C9");
		searchButton.addActionListener(e -> {
			reload(panel);
		});
		func.add(searchButton);

		reload(panel);
	}

	void reload(JPanel panel) {
		panel.removeAll();
		for (int i = 0; i < list.size(); i++) {
			String day = list.get(i)[0].toString();

			String locate1 = DB.getString("select l_name from location where l_no = ?", list.get(i)[1].toString());
			String locate2 = DB.getString("select l_name from location where l_no = ?", list.get(i)[2].toString());
			String locate = locate1 + "¡æ" + locate2;

			String departureTime = list.get(i)[3].toString().substring(0, 5);

			int distance = Reservation.distance(locate1, locate2);

			String arrivalTime = (Integer.parseInt(departureTime.split(":")[0]) + distance / 60) + ":" + (Integer.parseInt(departureTime.split(":")[1]) + distance % 60);
			String shit = list.get(i)[4].toString();
			var childPanel = new ReservationStateChange(day, locate, departureTime, arrivalTime, shit);
			childPanel.setPreferredSize(new Dimension(0, 100));

			int listDay = Integer.parseInt(day.split("-")[2]);
			int listMonth = Integer.parseInt(day.split("-")[1]);
			if (oneField.getText().isEmpty()) {
				panel.add(childPanel);
			} else {
				int onefieldDay = Integer.parseInt(oneField.getText().split("-")[2]);
				int twofieldDay = Integer.parseInt(twoField.getText().split("-")[2]);
				int getMonth = Integer.parseInt(oneField.getText().split("-")[1]);
				if ((onefieldDay <= listDay && twofieldDay >= listDay && getMonth == listMonth)) {
					panel.add(childPanel);
				}				
			}
		}
		panel.revalidate();
	}
}
