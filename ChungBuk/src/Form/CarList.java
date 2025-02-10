package Form;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ChildPanel.ReservationModel;
import Model.Frame;
import Utils.DB;

public class CarList extends Frame {
	public JLabel title;
	public JScrollPane scrollPane;
	public JPanel view;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CarList().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public CarList() {
		setFrame("차량목록", 850, 500);
		
		title = new JLabel("\uCC28\uB7C9\uBAA9\uB85D");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		getContentPane().add(title, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		view = new JPanel();
		scrollPane.setViewportView(view);
		view.setLayout(new GridLayout(0, 1, 0, 0));
		
		var list = DB.getCar();
		for (int i = 0; i < list.size(); i++) {
			var panel = new ReservationModel((byte[])list.get(i)[0], list.get(i)[1].toString(), list.get(i)[2].toString(), list.get(i)[3].toString(), list.get(i)[4].toString(), list.get(i)[5].toString(), list.get(i)[6].toString(), list.get(i)[7].toString(), list.get(i)[8].toString(), list.get(i)[9].toString(), list.get(i)[10].toString());
			panel.bottom.setVisible(false);
			var bt = new JButton("예약하기");
			bt.addActionListener(e -> {
				dispose();
				new Reservation().setVisible(true);
			});
			setSize(100, 30, bt);
			panel.panel_1.add(bt);
			view.add(panel);
		}
	}

}
