package Form;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ChildPanel.TicketPanel;
import Model.Frame;

public class Ticket extends Frame {
	public JScrollPane scrollPane;
	public JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Ticket().setVisible(true);
	}

	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose) new MyPage().setVisible(true);
	}
	public Ticket() {
		setFrame("BOARDING PASS", 865, 460);
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(new TicketPanel());
	}
}