package Form;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Utils.DB;

public class CourseCheck extends Frame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new CourseCheck().setVisible(true);
	}

	/**
	 * Create the application.
	 */boolean state = true;
	public CourseCheck() {
		setFrame("수강여부 확인", 400, 500);
		addWindowListener(this);
		JLabel title = new JLabel("\uC218\uAC15\uC5EC\uBD80 \uD655\uC778");
		title.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton print = new JButton("\uC778\uC1C4");
		print.addActionListener(e -> {
			try {
				table.print(JTable.PrintMode.FIT_WIDTH);
			} catch (PrinterException e1) {
				e1.printStackTrace();
			}
		});
		panel.add(print);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 0) {
					state = false;
					dispose();
					new ScoreCalculate().setVisible(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		var model = new DefaultTableModel(new Object[][] {}, new String[] {"과목", "담당 교수", "수강 여부", "수강 인원"});
		table.setModel(model);
		
		var list = DB.getCheckCourse();
		var course = DB.nowCourse(Login.no);
		for (int i = 0; i < 4; i++) {
			if (course.contains(DB.getString("select p_no from professor where name = ?", list.get(i)[1]))) {
				model.addRow(new Object[] {list.get(i)[0], list.get(i)[1], "O", list.get(i)[2] + "/10"});
			} else {
				model.addRow(new Object[] {list.get(i)[0], list.get(i)[1], "X", list.get(i)[2] + "/10"});
			}
		}
	}
	public void windowClosed(WindowEvent e) {
		if (state) new StudentMain().setVisible(true);
	}
}
