package Form;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ChildPanel.ReservationModel;
import Model.Frame;
import Utils.DB;

public class CarEditList extends Frame {
	public JScrollPane scrollPane;
	public JPanel view;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CarEditList().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public CarEditList() {
		setFrame("차량수정", 800, 400);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		view = new JPanel();
		scrollPane.setViewportView(view);
		view.setLayout(new GridLayout(0, 1, 0, 0));
		reload();
	}
	
	void reload () {
		view.removeAll();
		var list = DB.getCar();
		for (int i = 0; i < list.size(); i++) {
			int capture = i;
			var panel = new ReservationModel((byte[])list.get(i)[0], list.get(i)[1].toString(), list.get(i)[2].toString(), list.get(i)[3].toString(), list.get(i)[4].toString(), list.get(i)[5].toString(), list.get(i)[6].toString(), list.get(i)[7].toString(), list.get(i)[8].toString(), list.get(i)[9].toString(), list.get(i)[10].toString());
			var remove = new JButton("삭제");
			remove.addActionListener(e -> {
				int r = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				if (r == JOptionPane.YES_OPTION) {
					ok("삭제가 완료되었습니다.");
					DB.update("delete from nomember where c_no = ?", DB.getInt("select c_no from car where c_name = ?", list.get(capture)[2]));
					DB.update("delete from member where c_no = ?", DB.getInt("select c_no from car where c_name = ?", list.get(capture)[2]));
					DB.update("delete from reservation where c_no = ?", DB.getInt("select c_no from car where c_name = ?", list.get(capture)[2]));
					DB.update("delete from car where c_no = ?", DB.getInt("select c_no from car where c_name = ?", list.get(capture)[2]));
					reload();
				}
			});
			var edit = new JButton("수정");
			edit.addActionListener(e -> {
				dispose();
				new CarEdit((byte[])list.get(capture)[0], list.get(capture)[2].toString(), list.get(capture)[11].toString(), list.get(capture)[12].toString(), list.get(capture)[3].toString(), list.get(capture)[4].toString(), list.get(capture)[1].toString()).setVisible(true);
			});
			panel.panel_1.add(remove);
			panel.panel_1.add(edit);
			view.add(panel);
		}
		view.revalidate();
		view.repaint();
	}
}
