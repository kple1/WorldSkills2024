package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ChildPanel.OrderInformationModel;
import Utils.DB;
import Utils.Msg;

public class OrderInformation extends JFrame implements ActionListener {

	JButton sc;
	public static void main(String[] args) {
		new OrderInformation("").setVisible(true);
	}

	String no;
	public OrderInformation(String no) {
		this.no = no;
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 400, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("주문상세");
		setLocationRelativeTo(null);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel orderLabel = new JLabel("\uC8FC\uBB38\uBC88\uD638 : " + no);
		orderLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(orderLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		sc = new JButton("\uC644\uB8CC");
		sc.setForeground(new Color(255, 255, 255));
		sc.setBackground(new Color(255, 128, 0));
		sc.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel_1.add(sc);
		
		JPanel view = new JPanel();
		view.setBackground(new Color(255, 255, 255));
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout());
		
		view.add(new OrderInformationModel(no), BorderLayout.NORTH);
		
		sc.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(sc)) {
			Msg.ok("음료 제작이 완료되었습니다.");
			DB.update("update orderinfor set complete = 1 where no = ?", no);
			dispose();
		}
	}
}
