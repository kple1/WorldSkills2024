package Form;

import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class OrderNumber extends JFrame {

	public static void main(String[] args) {

	}

	public OrderNumber(String ono) {
		setTitle("\uC8FC\uBB38\uBC88\uD638");
		setBounds(0, 0, 280, 190);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Component verticalStrut = Box.createVerticalStrut(35);
		getContentPane().add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(35);
		getContentPane().add(verticalStrut_1, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel ol = new JLabel("\uC8FC\uBB38\uBC88\uD638");
		ol.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		ol.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(ol, BorderLayout.NORTH);
		
		JLabel numb = new JLabel(ono);
		numb.setHorizontalAlignment(SwingConstants.CENTER);
		numb.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		panel.add(numb, BorderLayout.CENTER);
		
	}
}
