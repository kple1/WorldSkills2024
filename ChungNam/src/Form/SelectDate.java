package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Model.Panel;

public class SelectDate extends Frame implements MouseListener {
	public JPanel panel;
	public JLabel left;
	public JLabel day;
	public JLabel right;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut;
	public JPanel view;
	public JPanel weekPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new SelectDate("flex").setVisible(true);
	}

	public void windowClosed(WindowEvent e) {
		var c = Calendar.getInstance();
		c.set(getYear, getMonth - 1, getDay + 1);
		
		var formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (form.equals("Reservation")) {
			Reservation.t2.setText(formatter.format(c.getTime()));
			String[] list = new String[] { "아프리카", "북아메리카", "남아메리카", "유럽", "아시아", "오세아니아"};   
			for (int i = 0; i < list.length; i++) {
				Reservation.c1.addItem(list[i]);	
			}
		} else {
			RegistFlight.t1.setText(formatter.format(c.getTime()));
		}
	}
	/**
	 * Create the application.
	 */
	String[] ws = new String[] {"일", "월", "화", "수", "목", "금", "토"};

	public JPanel grid;

	String form;
	public SelectDate(String form) {
		this.form = form;
		
		setFrame("날짜선택", 600, 400);
		setSize(600, 400);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		left = new JLabel("<");
		panel.add(left);

		day = new JLabel(String.format("%s년 %s월", getYear, getMonth));
		panel.add(day);

		right = new JLabel(">");
		panel.add(right);

		setBold(16, left, day, right);

		horizontalStrut = Box.createHorizontalStrut(40);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);

		horizontalStrut_1 = Box.createHorizontalStrut(40);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);

		verticalStrut = Box.createVerticalStrut(40);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);

		view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));

		weekPanel = new JPanel();
		view.add(weekPanel, BorderLayout.NORTH);
		weekPanel.setLayout(new GridLayout(1, 7, 5, 0));

		for (int i = 0; i < 7; i++) {
			var label = new JLabel(ws[i]);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			setFont(12, label);
			weekPanel.add(label);
		}

		grid = new JPanel();
		view.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(6, 7, 5, 5));

		reload();
	}

	int getYear = 2025;
	int getMonth = LocalDate.now().getMonthValue();
	int getDay = 0;
	int beforeClicked = 0;

	void reload() {
		grid.removeAll();

		int ended = YearMonth.of(getYear, getMonth).lengthOfMonth();
		int first = LocalDate.of(getYear, getMonth, 1).getDayOfWeek().getValue();
		int bm = getMonth == 1 ? 0 : 1;
		int beforeMonthMaxDay = LocalDate.of(getYear, getMonth - bm, first).lengthOfMonth();
		if (first == 7)
			first = 0;
		for (int i = beforeMonthMaxDay - first; i < beforeMonthMaxDay; i++) {
			grid.add(new ShowDay(i + 1, false));
		}

		ShowDay[] sd = new ShowDay[ended];
		for (int i = 0; i < ended; i++) {
			int capture = i;
			sd[i] = new ShowDay(i + 1, true);
			sd[i].getDay().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					sd[capture].getDay().setBackground(Color.YELLOW);
					sd[beforeClicked].getDay().setBackground(Color.CYAN);
					beforeClicked = capture;
					getDay = capture;
				}
			});
			grid.add(sd[i]);
		}
		for (int i = 0; i < 42 - (ended + first); i++) {
			grid.add(new ShowDay(i + 1, false));
		}
		grid.revalidate();
		grid.repaint();

		left.addMouseListener(this);
		right.addMouseListener(this);
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
		reload();
		day.setText("2025년 " + getMonth + "월");
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

class ShowDay extends Panel {
	JLabel day;

	ShowDay(int getDay, boolean backState) {
		setLayout(new BorderLayout());
		day = new JLabel(getDay + "");
		day.setHorizontalAlignment(SwingConstants.CENTER);
		day.setOpaque(true);
		if (backState) {
			day.setBackground(Color.CYAN);
		} else {
			day.setBackground(Color.LIGHT_GRAY);
		}
		setBold(12, day);
		add(day);
	}

	public JLabel getDay() {
		return day;
	}
}
