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
import Form.MenuInformation;
import Utils.DB;
import Utils.ImageSize;

public class ItemCount extends JPanel {

	static int amount;
	public ItemCount(String path, int amoun, int index) {
		this.amount = amoun;
		
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.setBackground(Color.white);
		
		int price = DB.getInt("select price from menu where mno = ?", path);
		var x = new Oval();
		x.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ChooseMenu.buyItemList.remove(path);
				ChooseMenu.price -= price;
				ChooseMenu.itemListPanelManage();
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
					--amount;
					ChooseMenu.amount.set(index, amount);
					amountLabel.setText(ChooseMenu.amount.get(index) + "");
					ChooseMenu.price -= price;
					ChooseMenu.totalPrice.setText(String.format("\\ %,d", ChooseMenu.price));
				}
			}
		});
		var right = new JLabel("+");
		right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				++amount;
				ChooseMenu.amount.set(index, amount);
				ChooseMenu.price += price;
				ChooseMenu.totalPrice.setText(String.format("\\ %,d", ChooseMenu.price));
				amountLabel.setText(ChooseMenu.amount.get(index) + "");
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
		image.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new MenuInformation(path, amount, index, DB.getString("select opno from menu where mno = ?", path)).setVisible(true);
			}
		});
		image.setSize(new Dimension(40, 40));
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/menuIMG/" + path + ".png"), image));
		add(image, BorderLayout.CENTER);
	}
}

class Oval extends JPanel {
	Oval() {
		setSize(10, 10);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillOval(0, 0, 10, 10);
	}
}
