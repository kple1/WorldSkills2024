package Form;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Utils.DB;

public class MyPage extends Frame {
	public JScrollPane scrollPane;
	public JTable table;

	public static void main(String[] args) {
		new MyPage().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"��ȣ", "ȸ��", "����", "�뿩����", "�ݳ�����", "�뿩��¥", "�뿩�ð�", "�ݳ���¥", "�ݳ��ð�"});
	public MyPage() {
		setFrame("����������", 800, 400);
		setSize(800, 400);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					var p = new JPopupMenu();
					var a = new JMenuItem("����");
					a.addActionListener(e1 -> {
						var convertDate = LocalDate.parse(table.getValueAt(table.getSelectedRow(), 7).toString());
						var nowDate = LocalDate.now();
						if (nowDate.isAfter(convertDate)) {
							DB.update("delete from reservation where re_no = ?", table.getValueAt(table.getSelectedRow(), 0));
							model.removeRow(table.getSelectedRow());
							ok("������ �Ϸ�Ǿ����ϴ�.");
						} else {
							fail("���� ��Ż ���� �׸��Դϴ�.");
						}
					});
					p.add(a);
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
		var list = DB.getMyPage();
		for (int i = 0; i < list.size(); i++) {
			model.addRow(new Object[] {list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3], list.get(i)[4], list.get(i)[5], list.get(i)[6], list.get(i)[7], list.get(i)[8]});
		}
	}
}
