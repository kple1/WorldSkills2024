package Util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRender extends DefaultTableCellRenderer {
	public CellRender() {
		
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		setHorizontalAlignment(SwingConstants.CENTER);
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (isSelected) {
            c.setBackground(Color.YELLOW);
        } else {
            c.setBackground(Color.WHITE);
        }
		return c;
	}
}
