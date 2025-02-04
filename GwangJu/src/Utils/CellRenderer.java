package Utils;

import java.awt.*;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.table.*;

public class CellRenderer extends DefaultTableCellRenderer {

	HashSet<Integer> list;
	public CellRenderer(HashSet<Integer> list) {
		this.list = list;
	}

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) { 
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (list.contains(row)) {
        	c.setForeground(Color.cyan);
        } else {
        	c.setForeground(Color.black);
        }
        return c;
    }
}