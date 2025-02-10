package Form;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Utils.DB;

public class ReservationList extends Frame {
	public JScrollPane scrollPane;
	public JTable table;

	public static void main(String[] args) {
		new ReservationList().setVisible(true);
	}

	DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"번호", "회원", "차량", "대여지점", "반납지점", "대여날짜", "대여시간", "반납날짜", "반납시간"});
	public ReservationList() {
		setFrame("예약목록", 700, 400);
		setSize(700, 400);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					var p = new JPopupMenu();
					var r = new JMenuItem("삭제");
					r.addActionListener(e1 -> {
						DB.update("delete from reservation where re_no = ?", table.getValueAt(table.getSelectedRow(), 0));
						reload();
					});
					p.add(r);
					p.show(table, e.getX(), e.getY());
				}
			}
		}); 
		scrollPane.setViewportView(table);
		table.setModel(model);
		reload();
	}

	void reload() {
		model.setRowCount(0);
		var list = DB.getReservation();
		for (int i = 0; i < list.size(); i++) {
			model.addRow(new Object[] {list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3], list.get(i)[4], list.get(i)[5], list.get(i)[6], list.get(i)[7], list.get(i)[8]});
		}
	}
}
