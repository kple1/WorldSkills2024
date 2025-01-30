package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import ChildPanel.CheckItem;

public class OrderCheck extends JFrame implements ActionListener {

	JButton allCancel, before, after;
	public static void main(String[] args) {
		new OrderCheck().setVisible(true);
	}

	public OrderCheck() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ÁÖ¹®È®ÀÎ");
		setBounds(0,0,500,650);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel title1 = new JLabel("\uC8FC\uBB38\uD558\uC2E0 \uB0B4\uC6A9\uC774 \uB9DE\uB098\uC694?");
		title1.setBackground(new Color(255, 255, 255));
		title1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title1);
		
		JLabel title2 = new JLabel("\uACB0\uC81C \uD6C4 \uCDE8\uC18C\uB098 \uBCC0\uACBD\uC774 \uC5B4\uB835\uC2B5\uB2C8\uB2E4.");
		title2.setBackground(new Color(255, 255, 255));
		title2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title2);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(0, 200));
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel btPanel = new JPanel();
		btPanel.setBackground(new Color(255, 255, 255));
		bottomPanel.add(btPanel, BorderLayout.SOUTH);
		
		allCancel = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		allCancel.setBackground(Color.white);
		allCancel.setBorder(BorderFactory.createLineBorder(Color.black));
		allCancel.setPreferredSize(new Dimension(100, 40));
		allCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		btPanel.add(allCancel);
		
		before = new JButton("\uC774\uC804");
		before.setBackground(Color.white);
		before.setBorder(BorderFactory.createLineBorder(Color.black));
		before.setPreferredSize(new Dimension(100, 40));
		before.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		btPanel.add(before);
		
		after = new JButton("\uB2E4\uC74C");
		after.setBackground(Color.white);
		after.setBorder(BorderFactory.createLineBorder(Color.black));
		after.setPreferredSize(new Dimension(100, 40));
		after.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		btPanel.add(after);
		
		JPanel interfacePanel = new JPanel();
		interfacePanel.setBackground(new Color(128, 128, 128));
		bottomPanel.add(interfacePanel, BorderLayout.CENTER);
		interfacePanel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		interfacePanel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		interfacePanel.add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel interfaceView = new JPanel();
		interfaceView.setBackground(new Color(128, 128, 128));
		interfacePanel.add(interfaceView, BorderLayout.CENTER);
		interfaceView.setLayout(new GridLayout(3, 3, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.white));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		view = new JPanel();
		view.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(view);
		view.setLayout(new GridLayout(0, 1, 0, 0));
		
		var orderLabel = new JLabel("ÁÖ¹® ±Ý¾×");
		orderLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		orderLabel.setForeground(Color.yellow);
		
		orderPrice = new JLabel(String.format("\\ %,d", tPrice));
		orderPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		orderPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		orderPrice.setForeground(Color.yellow);
		
		var salePriceLabel = new JLabel("ÇÒÀÎ ±Ý¾×");
		salePriceLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		salePriceLabel.setForeground(Color.yellow);
		
		salePrice = new JLabel(String.format("\\ %,d", saleP));
		salePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		salePrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		salePrice.setForeground(Color.yellow);
		
		var paymentPriceLabel = new JLabel("°áÁ¦ ±Ý¾×");
		paymentPriceLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		paymentPriceLabel.setForeground(Color.white);
		
		paymentPrice = new JLabel(String.format("\\ %,d", tPrice - saleP));
		paymentPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		paymentPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		paymentPrice.setForeground(Color.white);
		
		JLabel[] viewList = new JLabel[] {orderLabel, new JLabel(), orderPrice, salePriceLabel, new JLabel(), salePrice, paymentPriceLabel, new JLabel(), paymentPrice};
		for (int i = 0; i < viewList.length; i++) {
			interfaceView.add(viewList[i]);
		}
		
		after.addActionListener(this);
		before.addActionListener(this);
		allCancel.addActionListener(this);
		
		reload(this);
	}
	
	public static int tPrice;
	public static JPanel view;
	public static JLabel orderPrice;
	public static int saleP = 0;
	public static JLabel paymentPrice;
	public static JLabel salePrice;
	public static void reload(OrderCheck o) {
		view.removeAll();
		tPrice = 0;
		saleP = 0;
		for (int i = 0; i < ChooseMenu.buyItemList.size(); i++) {
			var panel1 = new CheckItem(ChooseMenu.buyItemList.get(i), ChooseMenu.amount.get(i), i, o);
			tPrice += ChooseMenu.buyItemPrice.get(i);
			if (Main.saleState.equals("go")) {
				saleP += 100 * ChooseMenu.amount.get(i);
			}
			panel1.setPreferredSize(new Dimension(0, 150));
			view.add(panel1);
		}
		if (ChooseMenu.buyItemList.size() <= 2) {
			for (int i = 0; i < 2; i++) {
				view.add(new JLabel());
			}
		}
		view.revalidate();
		orderPrice.setText(String.format("\\ %,d", OrderCheck.tPrice));
		paymentPrice.setText(String.format("\\ %,d", OrderCheck.tPrice - saleP));
		salePrice.setText(String.format("\\ %,d", saleP));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		if (e.getSource().equals(allCancel)) {
			new Main().setVisible(true);
		} else if (e.getSource().equals(before)) {
			new ChooseMenu().setVisible(true);
		} else if (e.getSource().equals(after)) {
			new Payment(tPrice, saleP, tPrice - saleP).setVisible(true);
		}
	}
}
