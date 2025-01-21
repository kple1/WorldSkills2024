package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Form.Calendar;

public class DayPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[] x = {40, 0, 40, 80};
        int[] y = {0, 40, 80, 40};
        g.setColor(Color.BLACK);
        g.drawPolygon(x, y, 4);

        try {
        	if (yellowBack) {
        		bgc = Color.YELLOW;
        	} else if (day < time.getDayOfMonth() && month == time.getMonthValue() || month < time.getMonthValue()) {
        		bgc = Color.LIGHT_GRAY;
        	} else {
        		bgc = Color.ORANGE;
        	}
        } catch (Exception e) {
        	
        }

        g.setColor(bgc);
        int[] fillX = {40, 1, 40, 79};
        int[] fillY = {1, 40, 79, 40};
        g.fillPolygon(fillX, fillY, 4);
    }

    private Color bgc;
    private LocalDate time = LocalDate.now();
    private int day;
    private int month;
    public JLabel label;
    private boolean yellowBack;
    
    public void stateChange() {
    	yellowBack = true;
    }
    
    public DayPanel(int day, int month) {
        this.day = day;
        this.month = month;

        setLayout(new BorderLayout());
        setOpaque(false);

        label = new JLabel(String.valueOf(day));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
        add(label, BorderLayout.CENTER);

        String week = String.valueOf(LocalDate.of(2024, month, day).getDayOfWeek());
        switch (week) {
            case "SUNDAY":
                week = "일";
                break;
            case "MONDAY":
                week = "월";
                break;
            case "TUESDAY":
                week = "화";
                break;
            case "WEDNESDAY":
                week = "수";
                break;
            case "THURSDAY":
                week = "목";
                break;
            case "FRIDAY":
                week = "금";
                break;
            case "SATURDAY":
                week = "토";
                break;
        }
        setToolTipText(week);
    }
}
