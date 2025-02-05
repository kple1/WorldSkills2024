package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Utils.DB;

public class ProfessorMain extends Frame implements ActionListener {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ProfessorMain().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	JButton edit, check;boolean state = true;
	public ProfessorMain() {
		setFrame("교수 메인", 300, 300);
		addWindowListener(this);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		JPanel view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(3, 0, 0, 30));
		
		var name = new JLabel(DB.getString("select name from professor where p_no = ?", Login.no) + " 교수님");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		
		edit = new JButton("정보수정");
		check = new JButton("수강생 조회");
		font(20, name, edit, check);
		setBorder(Color.black, edit, check);
		edit.setBackground(Color.white);
		check.setBackground(Color.white);
		
		view.add(name);
		view.add(edit);
		view.add(check);
		
		edit.addActionListener(this);
		check.addActionListener(this);
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		if (state) new Login().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		state = false;
		dispose();
		if (e.getSource().equals(edit)) {
			new ProfessorEdit().setVisible(true);
		} else if (e.getSource().equals(check)) {
			new StudentManage().setVisible(true);
		}
	} 
}
