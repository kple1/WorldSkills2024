package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Utils.ImageSize;
import Utils.Msg;

public class Payment extends JFrame implements ActionListener {
	private JButton before, allCancel;

	public static void main(String[] args) {
		new Payment(1000, 1000, 1000).setVisible(true);
	}

	public Payment(int oPrice, int sPrice, int rPrice) {
		setBounds(0, 0, 450, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("°áÁ¦");
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 200));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(155);
		panel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(155);
		panel.add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(40);
		panel.add(verticalStrut, BorderLayout.NORTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(40);
		panel.add(verticalStrut_1, BorderLayout.SOUTH);
		
		JLabel payment = new JLabel("");
		payment.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Msg.ok("°áÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
				dispose();
				new CheckPayment().setVisible(true);
			}
		});
		payment.setSize(120, 120);
		payment.setIcon(ImageSize.set(new ImageIcon("datafiles/°áÁ¦.png"), payment));
		payment.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(payment, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		allCancel = new JButton("\uC804\uCCB4\uCDE8\uC18C");
		allCancel.setBorder(BorderFactory.createLineBorder(Color.black));
		allCancel.setPreferredSize(new Dimension(100, 40));
		allCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		bottomPanel.add(allCancel);
		
		before = new JButton("\uC774\uC804");
		before.setBorder(BorderFactory.createLineBorder(Color.black));
		before.setPreferredSize(new Dimension(100, 40));
		before.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		bottomPanel.add(before);
		
		JPanel viewPanel = new JPanel();
		viewPanel.setBackground(new Color(128, 128, 128));
		getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewPanel.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		viewPanel.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		viewPanel.add(horizontalStrut_3, BorderLayout.EAST);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		viewPanel.add(verticalStrut_2, BorderLayout.NORTH);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		viewPanel.add(verticalStrut_3, BorderLayout.SOUTH);
		
		JPanel view = new JPanel();
		view.setBackground(new Color(128, 128, 128));
		viewPanel.add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(3, 3, 0, 0));
		
		JLabel orderPriceLabel = new JLabel("\uC8FC\uBB38 \uAE08\uC561");
		orderPriceLabel.setForeground(new Color(255, 255, 0));
		orderPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		orderPriceLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		view.add(orderPriceLabel);
		
		JLabel orderPrice = new JLabel(String.format("\\ %,d", oPrice));
		orderPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		orderPrice.setForeground(Color.YELLOW);
		orderPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		view.add(orderPrice);
		
		JLabel saleLabel = new JLabel("\uD560\uC778 \uAE08\uC561");
		saleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		saleLabel.setForeground(Color.YELLOW);
		saleLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		view.add(saleLabel);
		
		JLabel salePrice = new JLabel(String.format("\\ %,d", sPrice));
		salePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		salePrice.setForeground(Color.YELLOW);
		salePrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		view.add(salePrice);
		
		JLabel resultPriceLabel = new JLabel("\uACB0\uC81C \uAE08\uC561");
		resultPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		resultPriceLabel.setForeground(new Color(255, 255, 255));
		resultPriceLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		view.add(resultPriceLabel);
		
		JLabel resultPrice = new JLabel(String.format("\\ %,d", rPrice));
		resultPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		resultPrice.setForeground(new Color(255, 255, 255));
		resultPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		view.add(resultPrice);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
		if (e.getSource().equals(allCancel)) {
			new Main().setVisible(true);
		} else if (e.getSource().equals(before)) {
			new OrderCheck().setVisible(true);
		}
	}
}
