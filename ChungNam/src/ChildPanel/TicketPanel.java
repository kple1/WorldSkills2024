package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Model.Panel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TicketPanel extends Panel {
	public JLayeredPane layer;
	public JLabel image;
	public JLabel seatOption;
	public JLabel l1;
	public JLabel l2;
	public JLabel l3;
	public JLabel l4;
	public JLabel arrival;
	public JLabel travel;
	public JLabel departueDate;
	public JLabel departureTime;
	public JLabel name;
	public JLabel date;
	public JLabel flight;
	public JLabel seat;
	public JLabel gate;
	public JLabel airline;

	/**
	 * Create the panel.
	 */
	//table¿¡¼­ Á¤º¸°¡Á®¿À°í DBÃ³¸®ÇÏ¸é µÊ ÇÏÁö¸¸ ±ÍÂú¾Æ¼­ ³­ °Ç³Ê¶Û°ÅÀÓ
	public TicketPanel() {
		setPreferredSize(new Dimension(850, 415));
		setLayout(new BorderLayout(0, 0));
		
		layer = new JLayeredPane();
		add(layer, BorderLayout.CENTER);
		
		airline = new JLabel("<html>South African Airways<html/>");
		airline.setVerticalAlignment(SwingConstants.TOP);
		airline.setHorizontalAlignment(SwingConstants.CENTER);
		airline.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		airline.setBounds(760, 294, 78, 39);
		layer.add(airline);
		
		gate = new JLabel("100");
		gate.setHorizontalAlignment(SwingConstants.CENTER);
		gate.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		gate.setBounds(702, 294, 60, 26);
		layer.add(gate);
		
		seat = new JLabel("7355");
		seat.setHorizontalAlignment(SwingConstants.CENTER);
		seat.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		seat.setBounds(631, 292, 60, 26);
		layer.add(seat);
		
		flight = new JLabel("7355");
		flight.setHorizontalAlignment(SwingConstants.CENTER);
		flight.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		flight.setBounds(744, 213, 94, 26);
		layer.add(flight);
		
		date = new JLabel("2024-09-01");
		date.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 17));
		date.setBounds(638, 213, 94, 26);
		layer.add(date);
		
		name = new JLabel("Kim Jiyeon(\uC131\uC778)");
		name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		name.setBounds(642, 122, 184, 30);
		layer.add(name);
		
		departureTime = new JLabel("17:00");
		departureTime.setHorizontalAlignment(SwingConstants.CENTER);
		departureTime.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		departureTime.setBounds(467, 292, 89, 15);
		layer.add(departureTime);
		
		departueDate = new JLabel("2024-09-01");
		departueDate.setHorizontalAlignment(SwingConstants.CENTER);
		departueDate.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		departueDate.setBounds(467, 277, 89, 15);
		layer.add(departueDate);
		
		travel = new JLabel("Johannesburg");
		travel.setHorizontalAlignment(SwingConstants.CENTER);
		travel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		travel.setBounds(467, 259, 89, 15);
		layer.add(travel);
		
		arrival = new JLabel("SA");
		arrival.setHorizontalAlignment(SwingConstants.CENTER);
		arrival.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		arrival.setBounds(467, 213, 89, 47);
		layer.add(arrival);
		
		l4 = new JLabel("17:00");
		l4.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setBounds(31, 192, 89, 15);
		layer.add(l4);
		
		l3 = new JLabel("2024-09-01");
		l3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setBounds(31, 172, 89, 15);
		layer.add(l3);
		
		l2 = new JLabel("Incheon");
		l2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setBounds(31, 155, 89, 15);
		layer.add(l2);
		
		l1 = new JLabel("ICN");
		l1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 50));
		l1.setBounds(31, 105, 89, 47);
		layer.add(l1);
		
		image = new JLabel("");
		image.setBounds(0, 0, 850, 412);
		image.setIcon(new ImageIcon("G:\\\uC815\uBCF4\uAE30\uC220\\\uACFC\uC81C\\2024_\uC804\uAD6D\\\uBC31\uC5C5\\ChungNam3\\datafiles\\ticket.jpg"));
		layer.add(image, JLayeredPane.DEFAULT_LAYER);
		
		seatOption = new JLabel("Economy Class");
		seatOption.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		seatOption.setBounds(395, 30, 172, 26);
		layer.add(seatOption, JLayeredPane.PALETTE_LAYER);
	}

}
