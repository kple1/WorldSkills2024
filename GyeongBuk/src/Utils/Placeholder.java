package Utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class Placeholder {
	public static void normalSet(String comment, JTextField c) {
		c.setText(comment);
		c.setForeground(Color.LIGHT_GRAY);
		
		c.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (c.getText().equals(comment)) {
					c.setText("");
					c.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (c.getText().isEmpty()) { 
					c.setText(comment);
					c.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
	}
}
