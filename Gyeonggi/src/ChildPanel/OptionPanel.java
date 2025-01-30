package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Form.ChooseMenu;
import Form.MenuInformation;
import Utils.DB;
import Utils.DCController;

public class OptionPanel extends JPanel {

	public static int countAmount = 0;
	public static DCController controller = new DCController();
	public OptionPanel(String oname, int p, int index, int lno) {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel optionName = new JLabel(oname);
		optionName.setPreferredSize(new Dimension(100, 0));
		optionName.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		panel.add(optionName, BorderLayout.WEST);

		DC dcPanel = new DC(0, controller);
		var l = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(dcPanel.countClick())) {
					if (MenuInformation.itemAmount == countAmount + 1) {
						controller.setClock(false);
						controller.setTlock(false);
					}
					if (MenuInformation.itemAmount > countAmount) {
						MenuInformation.tPrice += p;
						++countAmount;
						ChooseMenu.optionsAmount.get(index).set(lno, countAmount);
					}
				} else if (e.getSource().equals(dcPanel.discountClick())) {
					if (MenuInformation.itemAmount != 1) {
						--countAmount;
						ChooseMenu.optionsAmount.get(index).set(lno, countAmount);
						MenuInformation.tPrice -= p;
					} else {
						controller.setDlock(false);
					}
				}
				MenuInformation.totalPrice.setText(String.format("\\ %,d", MenuInformation.tPrice));
			}
		};

		dcPanel.countClick().addMouseListener(l);
		dcPanel.discountClick().addMouseListener(l);

		dcPanel.setBackground(Color.white);
		panel.add(dcPanel, BorderLayout.EAST);
		dcPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel price = new JLabel(String.format("\\ %,d", p));
		price.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
		price.setForeground(Color.blue);
		price.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(price, BorderLayout.CENTER);

		JPanel line = new JPanel();
		line.setPreferredSize(new Dimension(0, 1));
		line.setBackground(new Color(0, 0, 0));
		panel.add(line, BorderLayout.SOUTH);

	}

}
