package Form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Utils.DB;

public class StudentManage extends Frame {
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public JPanel panel;
	public JPanel top;
	public JLabel l1;
	public JTextField textField;
	public JPanel panel_1;
	public JButton print;
	public JScrollPane scrollPane;
	public JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new StudentManage().setVisible(true);
	}

	/**
	 * Create the application.
	 */boolean state = true;
	public StudentManage() {
		setFrame("학생관리 목록",  400, 500);
		setSize(400, 500);
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
		
		top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(top, BorderLayout.NORTH);
		
		l1 = new JLabel("\uD559\uACFC\uBA85");
		top.add(l1);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					reload();
				}
			}
		});
		top.add(textField);
		textField.setColumns(10);
		
		font(12, l1, textField);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		print = new JButton("\uC778\uC1C4");
		print.addActionListener(e -> {
			try {
				table.print(JTable.PrintMode.FIT_WIDTH);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		panel_1.add(print);
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {return false;}
		};
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedColumn() == 0 && e.getClickCount() == 2) {
					state = false;
					dispose();
					new StudentEdit(DB.getString("select s_no from student where name = ?", table.getValueAt(table.getSelectedRow(), 1))).setVisible(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		model = new DefaultTableModel(new Object[][] {}, new String[] {"학번", "이름", "학과", "성적"});
		table.setModel(model);
		
		reload();
	}
	
	public void windowClosed(WindowEvent e) {
		if (state) new ProfessorMain().setVisible(true);
	}
	DefaultTableModel model;
	void reload() {
		model.setRowCount(0);
		var list = DB.getStudentManage(Login.no);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i)[2].contains(textField.getText().trim()) || textField.getText().isEmpty()) {
				model.addRow(new Object[] {list.get(i)[0],list.get(i)[1],list.get(i)[2],list.get(i)[3]});
			}
		}
	}
}
