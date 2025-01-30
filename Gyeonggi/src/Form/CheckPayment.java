package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Utils.DB;

public class CheckPayment extends JFrame implements ActionListener, WindowListener {
	JButton y, n;
	JProgressBar pro;
	boolean clickState = false;

	public static void main(String[] args) {
		new CheckPayment().setVisible(true);
	}

	public CheckPayment() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 450, 300);
		setTitle("결제");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.SOUTH);

		JPanel view = new JPanel();
		view.setBackground(new Color(255, 255, 255));
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(0, 100));
		view.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_2 = Box.createHorizontalStrut(61);
		panel.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(60);
		panel.add(horizontalStrut_3, BorderLayout.EAST);

		JPanel mix = new JPanel();
		mix.setBackground(new Color(255, 255, 255));
		panel.add(mix, BorderLayout.CENTER);
		mix.setLayout(new GridLayout(2, 1, 0, 10));

		JPanel prPanel = new JPanel();
		prPanel.setBackground(new Color(255, 255, 255));
		mix.add(prPanel);
		prPanel.setLayout(new BorderLayout(0, 0));

		pro = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		pro.setForeground(Color.GREEN);
		prPanel.add(pro, BorderLayout.CENTER);

		JPanel btPanel = new JPanel();
		btPanel.setBackground(new Color(255, 255, 255));
		mix.add(btPanel);
		btPanel.setLayout(new BorderLayout(0, 0));

		y = new JButton("\uC608");
		y.setPreferredSize(new Dimension(100, 35));
		y.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		y.setBorder(BorderFactory.createLineBorder(Color.black));
		y.setBackground(Color.WHITE);
		btPanel.add(y, BorderLayout.WEST);

		n = new JButton("\uC544\uB2C8\uC624");
		n.setPreferredSize(new Dimension(100, 35));
		n.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		n.setBorder(BorderFactory.createLineBorder(Color.black));
		n.setBackground(Color.WHITE);
		btPanel.add(n, BorderLayout.EAST);

		JLabel info = new JLabel("<html>결제금액 : " + String.format("\\ %,d", ChooseMenu.buyItemPrice.stream().mapToInt(Integer::intValue).sum()) + "<br>결제가 정상적으로 처리되었습니다.<br>영수증 출력을 하시겠습니까?<html/>");
		info.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		info.setBackground(new Color(255, 255, 255));
		info.setVerticalAlignment(SwingConstants.TOP);
		view.add(info, BorderLayout.CENTER);

		y.addActionListener(this);
		n.addActionListener(this);
	}

	Timer timer;
	int count = 0;
	String no;
	@Override
	public void actionPerformed(ActionEvent e) {
		var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		var today = LocalDate.now();
		String eqDate = DB.getString("select date from orderinfor where no = ?", DB.getString("select no from orderinfor order by no desc limit 1 ").equals(today.format(formatter)) ? "yes" : "no");
		
		if (eqDate.equals("yes")) {
			no = DB.getString("select no + 1 from orderinfor order by no desc limit 1 ");
		} else {
			no = DB.getString("select no from orderinfor order by no desc limit 1 ");
		}
		String ono;
		
		if (e.getSource().equals(y)) {
			clickState = true;
			timer = new Timer(100, e1 -> {
				if (count != 100) {
					++count;
					pro.setValue(count);
				} else {
					timer.stop();
					dispose();
					new Receipt(no).setVisible(true);
				}
			});
			timer.start();
		} else if (e.getSource().equals(n)) {
			dispose();
			new OrderNumber(no).setVisible(true);
		}
		
		for (int i = 0; i < ChooseMenu.buyItemList.size(); i++) {
			String mno = ChooseMenu.buyItemList.get(i);
			String amount = String.valueOf(ChooseMenu.amount.get(i));
			if (eqDate.equals("yes")) {
				int getDayNo = Integer.parseInt(DB.getString("select ono from orderinfor where no = ?", no).substring(5, 8)) + 1;
				ono = String.valueOf(getDayNo);
			} else {
				ono = "001";
			}
			
			String cno = DB.getString("select cno from menu where mno = ?", mno);
			
			String result1 = ChooseMenu.options.get(i).stream()
			        .map((Integer n) -> String.valueOf(n))
			        .collect(Collectors.joining(","));
			
			String result2 = ChooseMenu.optionsAmount.get(i).stream()
			        .map((Integer n) -> String.valueOf(n))
			        .collect(Collectors.joining(","));
			
			String price = ChooseMenu.buyItemPrice.get(i) + "";
			DB.update("insert into orderinfor (ono, date, uno, mno, quantity, togo, options, optionqty, price, complete) values (?,?,?,?,?,?,?,?,?,?)"
					, no + cno + ono
					, today.format(formatter)
					, Login.no
					, mno
					, amount
					, Main.saleState.equals("go") ? "1" : "0"
					, result1
					, result2
					, price
					, 0
					); 
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (!clickState) {
			new OrderNumber(no).setVisible(true);
		}
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
