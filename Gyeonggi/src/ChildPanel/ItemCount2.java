package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Form.ChooseMenu;
import Form.Main;
import Form.OrderCheck;
import Utils.DB;
import Utils.ImageSize;
import Utils.Msg;

public class ItemCount2 extends JPanel {

	int amount;
	public ItemCount2(String path, int amoun, int index, OrderCheck o) {
		this.amount = amoun;
		
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBackground(Color.white);
		
		int price = DB.getInt("select price from menu where mno = ?", path);
		var x = new Oval2();
		x.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChooseMenu.buyItemList.remove(index);
				ChooseMenu.buyItemPrice.remove(index);
				ChooseMenu.amount.remove(index);
				OrderCheck.saleP -= 100 * amount;
				OrderCheck.reload(o);
				if (ChooseMenu.buyItemList.size() == 0) {
					Msg.fail("주문내역이 없습니다.");
					o.dispose();
					new Main().setVisible(true);
				}
			}
		});
		panel.add(x);
		add(panel, BorderLayout.NORTH);
		
		JPanel bottom = new JPanel(new FlowLayout());
		bottom.setBackground(Color.white);

		var amountLabel = new JLabel(amount + "");
		var left = new JLabel("-");
		left.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (amount != 1) {
					if (Main.saleState.equals("go")) OrderCheck.saleP -= 100; OrderCheck.salePrice.setText(String.format("\\ %,d", OrderCheck.saleP));
					--amount;
					amountLabel.setText(amount + "");
					OrderCheck.tPrice -= price;
					OrderCheck.orderPrice.setText(String.format("\\ %,d", OrderCheck.tPrice));
					OrderCheck.paymentPrice.setText(String.format("\\ %,d", OrderCheck.tPrice - OrderCheck.saleP));
				}
			}
		});
		var right = new JLabel("+");
		right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (amount != 10) {
					if (Main.saleState.equals("go")) OrderCheck.saleP += 100;
					++amount;
					amountLabel.setText(amount + "");
					OrderCheck.tPrice += price;
					OrderCheck.orderPrice.setText(String.format("\\ %,d", OrderCheck.tPrice)); OrderCheck.salePrice.setText(String.format("\\ %,d", OrderCheck.saleP));
					OrderCheck.paymentPrice.setText(String.format("\\ %,d", OrderCheck.tPrice - OrderCheck.saleP));
				}
			}
		});
		
		bottom.add(left);
		bottom.add(amountLabel);
		bottom.add(right);
		add(bottom, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);
		
		JLabel image = new JLabel("");
		image.setSize(new Dimension(50, 80));
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/menuIMG/" + path + ".png"), image));
		add(image, BorderLayout.CENTER);
	}
}

class Oval2 extends JPanel {
	Oval2() {
		setSize(10, 10);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillOval(0, 0, 10, 10);
	}
}
