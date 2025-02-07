package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Frame;
import Util.CellRender;
import Util.DB;

public class MyPage extends Frame implements ActionListener {
	public JPanel top;
	public JLabel l1;
	public JLabel name;
	public JLabel l2;
	public JLabel mileage;
	public JLabel l3;
	public JLabel reservationCount;
	public JPanel bottom;
	public JButton remove;
	public JButton travelInfo;
	public JButton bnoCheck;
	public JScrollPane scrollPane;
	public JTable table;
	public DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] {"예약번호", "Boardingpass번호", "스케쥴번호", "날짜", "도착지", "구분", "좌석", "금액"});

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new MyPage().setVisible(true);
	}

	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose) new Main("user").setVisible(true);
	}
	String getName = DB.getString("select kname from user where uno = ?", Login.no); 
	int getMileage = DB.getInt("select mileage from user where uno = ?", Login.no); 
	int sel = -1;
	public MyPage() {
		setFrame("마이페이지", 800, 400);
		setSize(850, 500);

		top = new JPanel();
		getContentPane().add(top, BorderLayout.NORTH);
		
		l1 = new JLabel("\uC774\uB984");
		top.add(l1);
		
		name = new JLabel(getName);
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(name);
		
		l2 = new JLabel("\uC794\uC5EC Mileage");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(l2);
		
		mileage = new JLabel(getMileage + "");
		mileage.setBorder(BorderFactory.createLineBorder(Color.black));
		mileage.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(mileage);
		
		l3 = new JLabel("\uC608\uC57D\uAC74\uC218");
		top.add(l3);
		
		reservationCount = new JLabel("57\uAC74(59\uC11D)");
		reservationCount.setHorizontalAlignment(SwingConstants.CENTER);
		reservationCount.setBorder(BorderFactory.createLineBorder(Color.black));
		top.add(reservationCount);
		
		setSize(100, 30, name, mileage, reservationCount);
		
		bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(bottom, BorderLayout.SOUTH);
		
		bnoCheck = new JButton("\uD56D\uACF5\uAD8C\uD655\uC778");
		bnoCheck.setBackground(Color.white);
		bottom.add(bnoCheck);
		
		travelInfo = new JButton("\uC5EC\uD589\uC9C0\uC815\uBCF4");
		travelInfo.setBackground(Color.white);
		bottom.add(travelInfo);
		
		remove = new JButton("\uC0AD\uC81C");
		remove.setBackground(Color.white);
		bottom.add(remove);
		
		setSize(130, 35, bnoCheck, travelInfo, remove);
		setBold(16, l1, l2, l3, name, mileage, reservationCount, bnoCheck, travelInfo, remove);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.getSelectionBackground();
				sel = table.getSelectedRow();
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model);
		for (int i = 0; i < 8; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new CellRender());
		}
		
		reload();
		
		bnoCheck.addActionListener(this);
		travelInfo.addActionListener(this);
		remove.addActionListener(this);
	}
	
	void reload() {
		model.setRowCount(0);
		var list = DB.getMyPage();
		for (int i = 0; i < list.size(); i++) {
			model.addRow(new Object[] {list.get(i)[0], list.get(i)[1], list.get(i)[2], list.get(i)[3], list.get(i)[4], list.get(i)[5], list.get(i)[6], String.format("%,d", Integer.parseInt(list.get(i)[7]))});
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		formClose = false;
		if (e.getSource().equals(bnoCheck)) {
			if (sel == -1) {
				fail("여행일정을 선택하세요.");
			} else {
				dispose();
				new Ticket().setVisible(true);
			}
		} else if (e.getSource().equals(travelInfo)) {
			if (sel == -1) {
				fail("여행일정을 선택하세요.");
			} else {
				dispose();
				new TravelGuide("datafiles/CountryImage/1.jpg", "datafiles/TravelImage/1.jpg", "남아프리카공화국", "요하네스버그", "", "", "유명하긴 해", "MyPage").setVisible(true);
			}
		} else if (e.getSource().equals(remove)) {
			if (sel == -1) {
				fail("여행일정을 선택하세요.");
			} else {
				if (DB.getInt("select count(*) from reservation where bno = ?", table.getValueAt(sel, 1)) == 1) {
					getMileage -= 1;
					mileage.setText(getMileage + "");
					DB.update("update user set mileage = ? where uno = ?", getMileage, Login.no);
				}
				DB.update("delete from reservation where rno = ?", table.getValueAt(sel, 0));
				reload();
			}
		}
	}
}