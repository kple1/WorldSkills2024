package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import ChildPanel.AdminCar;
import Model.Frame;
import Utils.DB;

public class AdminHome extends Frame implements ActionListener {
	public JLabel title;
	public JPanel panel;
	public JPanel panel_1;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public JPanel view;
	public JButton editCar;
	public JButton registCar;
	public JButton reserList;
	public JPanel movePanel;
	public JScrollPane scrollPane;
	Timer timer;
	int x = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new AdminHome().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public AdminHome() {
		setFrame("°ü¸®ÀÚ È¨", 700, 500);
		setSize(800, 500);
		
		title = new JLabel("\uAD00\uB9AC\uC790\uB2D8 \uD658\uC601\uD569\uB2C8\uB2E4.");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		getContentPane().add(title, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 150));
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		movePanel = new JPanel();
		movePanel.setBounds(0, 0, 5120, 150);
		movePanel.setLayout(new GridLayout(1, 0, 10, 0));
		var list = DB.getAdminCar();
		for (int i = 0; i < list.size(); i++) {
			var panel = new AdminCar((byte[])list.get(i)[0], list.get(i)[1].toString());
			movePanel.add(panel);
		}
		
		panel.add(movePanel);
		timer = new Timer(10, e -> {
			if (x != -5120) {
				x -= 2;
			} else {
				timer.stop();
			}
			movePanel.setLocation(x, 0);
		});
		timer.start();
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		verticalStrut = Box.createVerticalStrut(70);
		panel_1.add(verticalStrut, BorderLayout.NORTH);
		
		verticalStrut_1 = Box.createVerticalStrut(70);
		panel_1.add(verticalStrut_1, BorderLayout.SOUTH);
		
		horizontalStrut = Box.createHorizontalStrut(150);
		panel_1.add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(150);
		panel_1.add(horizontalStrut_1, BorderLayout.EAST);
		
		view = new JPanel();
		panel_1.add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(1, 3, 20, 0));
		
		registCar = new JButton("\uCC28\uB7C9\uB4F1\uB85D");
		registCar.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		view.add(registCar);
		
		reserList = new JButton("\uC608\uC57D\uB0B4\uC5ED");
		reserList.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		view.add(reserList);
		
		editCar = new JButton("\uCC28\uB7C9\uC218\uC815");
		editCar.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		view.add(editCar);
		
		registCar.addActionListener(this);
		reserList.addActionListener(this);
		editCar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		if (e.getSource().equals(registCar)) {
			new RegistCar().setVisible(true);
		} else if (e.getSource().equals(reserList)) {
			new ReservationList().setVisible(true);
		} else if (e.getSource().equals(editCar)) {
			new CarEditList().setVisible(true);
		}
	}
}
