package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class ReservationStateChange extends JPanel {

	public ReservationStateChange(String getDay, String getLocate, String getDepartureTime, String getArrivalTime, String getSeat) {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(150, 0));
		add(buttonPanel, BorderLayout.EAST);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		buttonPanel.add(horizontalStrut);
		
		JButton changeButton = new JButton("\uBCC0\uACBD");
		buttonPanel.add(changeButton);
		
		JButton cancelButton = new JButton("\uCDE8\uC18C");
		buttonPanel.add(cancelButton);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 4, 0, 0));
		
		JLabel dayLabel = new JLabel("\uB0A0\uC9DC");
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(dayLabel);
		
		JLabel journeyLabel = new JLabel("\uC5EC\uC815 \uC77C\uC815");
		journeyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(journeyLabel);
		
		JLabel departureTimeLabel = new JLabel("\uCD9C\uBC1C \uC2DC\uAC04");
		departureTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(departureTimeLabel);
		
		JLabel arrivalTimeLabel = new JLabel("\uB3C4\uCC29 \uC2DC\uAC04");
		arrivalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(arrivalTimeLabel);
		
		JLabel seatLabel = new JLabel("\uC88C\uC11D");
		seatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(seatLabel);
		
		JLabel day = new JLabel(getDay);
		day.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(day);
		
		JLabel locate = new JLabel(getLocate);
		locate.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(locate);
		
		JLabel departureTime = new JLabel(getDepartureTime);
		departureTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(departureTime);
		
		JLabel arrivalTime = new JLabel(getArrivalTime);
		arrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(arrivalTime);
		
		JLabel seat = new JLabel(getSeat);
		seat.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(seat);
	}
}
