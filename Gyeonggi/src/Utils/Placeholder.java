package Utils;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class Placeholder  {
	public static void set(JTextField field, String text) {
		field.setText(text);
		field.setForeground(Color.LIGHT_GRAY);
		field.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (field.getText().equals(text)) {
					field.setText("");
					field.setForeground(Color.black);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (field.getText().isEmpty()) {
					field.setText(text);
					field.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
	}
}	
