package ChildPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.DCController;

public class DC extends JPanel implements MouseListener {

	JLabel d, c, amountLabel;
	int amount;
	int startAmount;
	DCController con;
	public DC(int startAmount, DCController con) {
		this.con = con;
		this.amount = startAmount;
		this.startAmount = startAmount;
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		d = new JLabel("-");
		amountLabel = new JLabel(amount + "");
		c = new JLabel("+");

		d.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		amountLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		c.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));

		add(d);
		add(amountLabel);
		add(c);

		d.addMouseListener(this);
		c.addMouseListener(this);
	}

	public int getAmount() {
		return amount;
	}

	public JLabel discountClick() {
		return d;
	}

	public JLabel countClick() {
		return c;
	}

	public void setAmount(String newAmount) {
		amountLabel.setText(newAmount);
	}

	public void addAmount(int addAmount) {
		amount += addAmount;
	}

	public void subAmount(int subAmount) {
		if (startAmount != amount) {
			amount -= subAmount;
		} else {
			d.setForeground(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(d) && con.isDlock()) {
			con.setClock(true);
			con.setTlock(true);
			subAmount(1);
		} else if (e.getSource().equals(c) && con.isClock()) {
			d.setForeground(Color.BLACK);
			addAmount(1);
		}
		if (con.isTlock()) amountLabel.setText(amount + "");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
