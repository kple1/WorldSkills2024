package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.YearMonth;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Model.Panel;

public class Calendar extends Frame implements MouseListener {
	public JPanel panel;
	public JLabel left;
	public JLabel right;
	public JLabel date;
	public Component verticalStrut;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public JPanel view;

	public static void main(String[] args) {
		new Calendar("data", LocalDate.now().getMonthValue()).setVisible(true);
	}

	String state;
	public Calendar(String state, int setDay) {
		setFrame("달력", 300, 300);
		setSize(300, 300);
		this.state = state;
		getDay = setDay;
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		left = new JLabel("");
		left.setBorder(BorderFactory.createLineBorder(Color.black));
		left.setSize(30, 30);
		left.setIcon(imageSize(new ImageIcon("datafiles/아이콘/Left.png"), left));
		left.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel.add(left, BorderLayout.WEST);
		
		right = new JLabel("");
		right.setBorder(BorderFactory.createLineBorder(Color.black));
		right.setSize(30, 30);
		right.setIcon(imageSize(new ImageIcon("datafiles/아이콘/Right.png"), right));
		panel.add(right, BorderLayout.EAST);
		
		date = new JLabel("2025년2월");
		date.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		date.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(date, BorderLayout.CENTER);
		
		verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(6, 7, 0, 0));
		reload();
		
		left.addMouseListener(this);
		right.addMouseListener(this);
	}

	int getYear = 2025;
	public static int getMonth = LocalDate.now().getMonthValue();
	public static int pageMonth = LocalDate.now().getMonthValue();
	public static int getDay = LocalDate.now().getDayOfMonth();
	boolean reloading = true;
	void reload() {
		view.removeAll();
		int ended = YearMonth.of(getYear, getMonth).lengthOfMonth();
		int first = LocalDate.of(getYear, getMonth, 1).getDayOfWeek().getValue();
		
		if (first == 7) first = 0;
		for (int i = 0; i < first; i++) {
			view.add(new JLabel());
		}
		
		for (int i = 1; i <= ended; i++) {
			int capture = i;
			var panel = new DayPanel(i);
			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					getDay = capture;
					pageMonth = getMonth;
					if (state.equals("date")) {
						if (Reservation.setRedate < getDay) {
							reloading = false;
							fail("대여 일은 반납 일보다 미래일 수 없습니다.");
						} else {
							Reservation.date.setText(String.format("2025-%s-%s", getMonth, getDay));
							Reservation.setDate = getDay;
						}
						
					} else {
						Reservation.redate.setText(String.format("2025-%s-%s", getMonth, getDay));
						Reservation.setRedate = getDay;
					}
					if (reloading) {
						reload();
					}
				}
			});
			if (getMonth < pageMonth) {
				panel.setForeground();
			} else if (getMonth == pageMonth && i < getDay) {
				panel.setForeground();
			}
			
			if (getMonth == pageMonth && i == getDay) {
				panel.setBackground();
			}
			view.add(panel);
		}
		
		for (int i = 0; i < 42 - (ended + first); i++) {
			view.add(new JLabel());
		}
		view.revalidate();
		view.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(left)) {
			if (getMonth != 1) {
				--getMonth;
			}
		} else if (e.getSource().equals(right)) {
			if (getMonth != 12) {
				++getMonth;
			}
		}
		date.setText(String.format("2025년%s월", getMonth));
		reload();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class DayPanel extends Panel {
	JLabel label;
	DayPanel(int day) {
		setLayout(new BorderLayout());
		label = new JLabel(String.valueOf(day));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		setFont(12, label);
		add(label);
	}
	
	void setBackground() {
		label.setBackground(Color.GREEN);
		label.setForeground(Color.WHITE);
	}
	
	void setForeground() {
		label.setForeground(Color.LIGHT_GRAY);
	}
}
