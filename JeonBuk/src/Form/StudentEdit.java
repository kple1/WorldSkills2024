package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JPanel;

import Model.Frame;
import Utils.DB;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class StudentEdit extends Frame {
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public JPanel panel;
	public JPanel setGrid;
	public JPanel namePanel;
	public JPanel fieldPanel;
	public JLabel l4;
	public JLabel l1;
	public JLabel l2;
	public JLabel l3;
	public JTextField t4;
	public JTextField t1;
	public JTextField t2;
	public JTextField t3;
	public JButton save;
	public JPanel panel_1;
	public Component verticalStrut_2;
	public JPanel panel_2;
	public JLabel l5;
	public JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new StudentEdit("1").setVisible(true);
	}

	/**
	 * Create the application.
	 */boolean state = true;
	public StudentEdit(String s_no) {
		setFrame("학생 정보수정", 500, 600);
		setSize(450, 600);
		addWindowListener(this);
		horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		setGrid = new JPanel();
		setGrid.setPreferredSize(new Dimension(0, 250));
		panel.add(setGrid, BorderLayout.NORTH);
		setGrid.setLayout(new BorderLayout(0, 0));
		
		namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(150, 0));
		setGrid.add(namePanel, BorderLayout.WEST);
		namePanel.setLayout(new GridLayout(4, 1, 0, 10));
		
		l1 = new JLabel("\uACFC\uBAA9");
		namePanel.add(l1);
		
		l2 = new JLabel("\uD559\uBC88");
		namePanel.add(l2);
		
		l3 = new JLabel("\uC774\uB984");
		namePanel.add(l3);
		
		l4 = new JLabel("\uC131\uC801");
		namePanel.add(l4);
		
		fieldPanel = new JPanel();
		setGrid.add(fieldPanel, BorderLayout.CENTER);
		fieldPanel.setLayout(new GridLayout(4, 1, 0, 10));
		
		t1 = new JTextField();
		t1.setEnabled(false);
		fieldPanel.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setEnabled(false);
		fieldPanel.add(t2);
		t2.setColumns(10);
		
		t3 = new JTextField();
		t3.setEnabled(false);
		fieldPanel.add(t3);
		t3.setColumns(10);
		
		t4 = new JTextField();
		fieldPanel.add(t4);
		t4.setColumns(10);
		
		var s = DB.getStudentInfo(s_no, Login.no);
		t1.setText(s[0]);
		t2.setText(s[1]);
		t3.setText(s[2]);
		t4.setText(s[3]);
		
		save = new JButton("\uC800\uC7A5");
		save.addActionListener(e -> {
			if (t3.getText().isEmpty()) {
				fail("성적을 입력해주세요.");
			} else if (textField.getText().length() <= 10) {
				fail("특기사항을 더 길게 작성해주세요.");
			} else {
				ok("저장되었습니다.");
				DB.update("update enrolment set score = ?, remarks = ? where s_no = ? and p_no = ?", t4.getText(), textField.getText(), s_no, Login.no);
			}
		});
		panel.add(save, BorderLayout.SOUTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_2, BorderLayout.SOUTH);
		
		panel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		l5 = new JLabel("\uD2B9\uAE30\uC0AC\uD56D");
		l5.setHorizontalAlignment(SwingConstants.LEFT);
		panel_2.add(l5);
		
		textField = new JTextField();
		panel_1.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		textField.setText(s[4]);
		
		font(12, l1, l2, l3, l4, l5, t1, t2, t3, t4, textField, save);
	}
	
	public void windowClosed(WindowEvent e) {
		if (state) new StudentManage().setVisible(true);
	}
}
