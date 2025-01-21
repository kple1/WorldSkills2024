package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ChildPanel.DayPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Calendar extends JFrame {

	JLabel dayInfo;
	public static void main(String[] args) {
		new Calendar("Àú´Â È«ÄáÀÌ¿ä").setVisible(true);
	}

	public Calendar(String ¾îµð¼­¿À¼Ì¾î¿ä) {
		setBounds(0, 0, 750, 450);
		setTitle("\uB2EC\uB825");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 30));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		JLabel left = new JLabel("\u25C0");
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (month == LocalDate.now().getMonthValue()) {
					left.setForeground(Color.LIGHT_GRAY);
				} else {
					--month;
					dayInfo.setText("2025³â " + month + "¿ù");
					reload(viewPanel);
				}
			}
		});
		left.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		topPanel.add(left);
		
		dayInfo = new JLabel("2025³â " + month + "¿ù");
		dayInfo.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		topPanel.add(dayInfo);
		
		JLabel right = new JLabel("\u25B6");
		right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (month == 12) {
					right.setForeground(Color.LIGHT_GRAY);
				} else {
					++month;
					dayInfo.setText("2025³â " + month + "¿ù");
					reload(viewPanel);
				}
			}
		});
		right.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		topPanel.add(right);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(0, 40));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton select = new JButton("\uC120\uD0DD\uC644\uB8CC");
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (¾îµð¼­¿À¼Ì¾î¿ä.equals("Reservation")) {
					new Reservation().setVisible(true);
					String format = String.format("2025-%02d-%02d", month, clickedDay);
					Reservation.day.setText(format);
				} else {
					ReservationList.oneField.setText("2025-" + month + "-" + save1 + "");
					ReservationList.twoField.setText("2025-" + month + "-" + save2 + ""); 
				}
			}
		});
		select.setPreferredSize(new Dimension(100, 30));
		bottomPanel.add(select);
		
		viewPanel = new JPanel();
		getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewPanel.setLayout(null);
		
		reload(viewPanel); 
	}	
	
	JPanel viewPanel;
	boolean start = false;
	private LocalDate time = LocalDate.now();
	private int x = 0;
	private int y = 0;
	private int month = time.getMonthValue();
	private int day = time.getMonthValue();
	private int save1 = 0;
	private int save2 = 0;
	public static int clickedDay = LocalDate.now().getDayOfMonth();
	void reload(JPanel viewPanel) {
		viewPanel.removeAll();
		x = 0;
		y = 0;
		
		int end = YearMonth.of(2025, month).lengthOfMonth();
		int first = LocalDate.of(2025, month, 1).getDayOfWeek().getValue();
		
		if (first == 7) first = 0; 
		for (int i = 0; i < first; i++) {
			x += 90;
		}
		
		for (int i = 0; i < end; i++) {
			final int count = i;
			DayPanel panel = new DayPanel(i + 1, month);
			if (i >= save1 - 1 && i <= save2 - 1) {
				panel.stateChange();
			}
			
			if (i + 1 == clickedDay) {
				panel.label.setForeground(Color.red);
			}

			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					clickedDay = count + 1;
					if (e.getClickCount() == 2) {
						if (save1 == 0) {
							save1 = count + 1;
						} else if (save2 == 0) {
							save2 = count + 1;
						} else {
							save1 = count + 1;
							save2 = 0;
						}
						System.out.println(save1 + " / " + save2);
						panel.repaint();
					}
					reload(viewPanel);
				}
			});
			panel.setBounds(x, y, 80, 80);
			if (x == 540 || x == 585) {
				if (start) {
					x = 0;
					start = false;
				} else {
					x = 45;
					start = true;
				}
				y += 45;
			} else {
				x += 90;
			}
			viewPanel.add(panel);
		}
		viewPanel.revalidate();
		viewPanel.repaint();
	}
}
