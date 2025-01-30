package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Form.ChooseMenu;
import Form.OrderCheck;
import Utils.DB;

public class CheckItem extends JPanel {

	public CheckItem(String itemNum, int itemAmount, int listIndex, OrderCheck o) {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		var showItem = new ItemCount2(itemNum, itemAmount, listIndex, o);
		showItem.setPreferredSize(new Dimension(100, 0));
		add(showItem, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel normalPrice = new JPanel();
		normalPrice.setBackground(new Color(255, 255, 255));
		panel_1.add(normalPrice, BorderLayout.SOUTH);
		normalPrice.setLayout(new BorderLayout(0, 0));

		var price = new JLabel(String.format("\\ %,d", DB.getInt("select price from menu where mno = ?", itemNum)));
		price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

		var itemName2 = new JLabel(DB.getString("select name from menu where mno = ?", itemNum));
		itemName2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));

		normalPrice.add(itemName2, BorderLayout.WEST);
		normalPrice.add(price, BorderLayout.EAST);

		var itemName1 = new JLabel(DB.getString("select name from menu where mno = ?", itemNum));
		itemName1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));

		var totalPrice = new JLabel(String.format("\\ %,d", ChooseMenu.buyItemPrice.get(listIndex)));
		totalPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));

		JPanel totalPricePanel = new JPanel();
		totalPricePanel.setBackground(new Color(255, 255, 255));
		panel_1.add(totalPricePanel, BorderLayout.NORTH);
		totalPricePanel.setLayout(new BorderLayout(0, 0));

		totalPricePanel.add(itemName1, BorderLayout.WEST);
		totalPricePanel.add(totalPrice, BorderLayout.EAST);
	}
}
