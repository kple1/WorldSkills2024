package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Utils.DB;

public class StudentMain extends Frame implements WindowListener {
	private JTable table;

	public static void main(String[] args) {
		new StudentMain().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	boolean state = true;
	public StudentMain() {
		setFrame("학생 메인", 400, 500);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		addWindowListener(this);
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel msg = new JLabel("<html>식품영양학과 <b>김현우</b> 선생님의 수강중인<br>강죄입니다.<html/>");
		msg.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(msg, BorderLayout.NORTH);
		
		JPanel bottom = new JPanel();
		panel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1, 2, 10, 0));
		
		var check = new JButton("수강여부 확인");
		var edit = new JButton("정보 수정하기");

		setSize(100, 30, check, edit);
		
		bottom.add(check);
		bottom.add(edit);
		
		check.addActionListener(e -> {
			state = false;
			dispose();
			new CourseCheck().setVisible(true);
		});
		
		edit.addActionListener(e -> {
			dispose();
			new StudentManage().setVisible(true);
		});
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		var model = new DefaultTableModel(new Object[][] {}, new String[] {"과목", "담당 교수", "교수 번호", "점수"});
		table.setModel(model);
		
		var list = DB.getTakeCourse(Login.no);
		for (int i = 0; i < list.size(); i++) {
			model.addRow(new Object[] {list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3]});
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		if (state) new Login().setVisible(true);
	}
}
