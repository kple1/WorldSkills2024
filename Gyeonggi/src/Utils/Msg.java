package Utils;

import javax.swing.JOptionPane;

public class Msg {
	public static void ok(String msg) {
		JOptionPane.showMessageDialog(null, msg, "����", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void fail(String msg) {
		JOptionPane.showMessageDialog(null, msg, "���", JOptionPane.ERROR_MESSAGE);
	}
}
