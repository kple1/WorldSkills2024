package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ChildPanel.ReceiptModel;
import Utils.DB;
import Utils.ImageSize;

public class Receipt extends JFrame implements WindowListener {
	
	public static void main(String[] args) {
		new Receipt("246350789").setVisible(true);
	}
	
	public Receipt(String getono) {
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 500, 450);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("¿µ¼öÁõ");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(500, 150));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel title = new JLabel("\uC601\uC218\uC99D");
		title.setBackground(new Color(255, 255, 255));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		panel.add(title, BorderLayout.NORTH);
		
		JPanel topInfo = new JPanel();
		topInfo.setBackground(new Color(255, 255, 255));
		panel.add(topInfo, BorderLayout.CENTER);
		topInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel leftbox = new JPanel();
		leftbox.setBackground(new Color(255, 255, 255));
		topInfo.add(leftbox, BorderLayout.WEST);
		leftbox.setLayout(new BoxLayout(leftbox, BoxLayout.Y_AXIS));
		
		JLabel l1 = new JLabel("\uB2E4\uBC29\uCEE4\uD53C");
		l1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		leftbox.add(l1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		leftbox.add(verticalStrut);
		
		JLabel l1_1 = new JLabel("\uB300\uD45C : \uAE40\uB2E4\uBC29");
		l1_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		leftbox.add(l1_1);
		
		JLabel l1_2 = new JLabel("\uC804\uD654 : 054-1234-1234");
		l1_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		leftbox.add(l1_2);
		
		JPanel rightbox = new JPanel();
		rightbox.setBackground(new Color(255, 255, 255));
		topInfo.add(rightbox, BorderLayout.EAST);
		rightbox.setLayout(new BoxLayout(rightbox, BoxLayout.Y_AXIS));
		
		JLabel day = new JLabel("2024-08-27 18:16:29");
		day.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		day.setHorizontalAlignment(SwingConstants.RIGHT);
		rightbox.add(day);
		
		JLabel l2 = new JLabel("               \uC8FC\uBB38\uBC88\uD638");
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		rightbox.add(l2);
		
		JLabel ono = new JLabel("      2360");
		ono.setHorizontalAlignment(SwingConstants.RIGHT);
		ono.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 35));
		rightbox.add(ono);
		
		JPanel line = new JPanel();
		line.setPreferredSize(new Dimension(0, 1));
		line.setBackground(new Color(0, 0, 0));
		topInfo.add(line, BorderLayout.SOUTH);
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		bottom.setPreferredSize(new Dimension(500, 130));
		getContentPane().add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));
		
		JLabel uuid = new JLabel("880742359879");
		uuid.setPreferredSize(new Dimension(0, 40));
		uuid.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		uuid.setVerticalAlignment(SwingConstants.TOP);
		uuid.setHorizontalAlignment(SwingConstants.CENTER);
		bottom.add(uuid, BorderLayout.SOUTH);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBackground(new Color(255, 255, 255));
		resultPanel.setPreferredSize(new Dimension(0, 40));
		bottom.add(resultPanel, BorderLayout.NORTH);
		resultPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel line_1 = new JPanel();
		line_1.setPreferredSize(new Dimension(0, 1));
		line_1.setBackground(Color.BLACK);
		resultPanel.add(line_1, BorderLayout.NORTH);
		
		JPanel line_2 = new JPanel();
		line_2.setPreferredSize(new Dimension(0, 1));
		line_2.setBackground(Color.BLACK);
		resultPanel.add(line_2, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		resultPanel.add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		resultPanel.add(horizontalStrut_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		resultPanel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uD569\uACC4 :");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel_2.add(lblNewLabel, BorderLayout.WEST);
		
		JLabel price = new JLabel(String.format("\\ %,d", ChooseMenu.buyItemPrice.stream().mapToInt(Integer::intValue).sum()));
		price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel_2.add(price, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		bottom.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_1, BorderLayout.NORTH);
		
		JLabel image = new JLabel("");
		image.setVerticalAlignment(SwingConstants.BOTTOM);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setSize(300, 80);
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/¹ÙÄÚµå.png"), image));
		panel_1.add(image, BorderLayout.CENTER);
		
		JPanel view = new JPanel();
		view.setBackground(new Color(255, 255, 255));
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new GridLayout(0, 1, 0, 0));
		
		for (int i = 0; i < ChooseMenu.buyItemList.size(); i++) {
			String name = DB.getString("select name from menu where mno = ?", ChooseMenu.buyItemList.get(i));
			String amount = ChooseMenu.amount.get(i) + "";
			int rprice = ChooseMenu.buyItemPrice.get(i);
			view.add(new ReceiptModel(name, amount, rprice));
		}
		
		int sumAmount = ChooseMenu.amount.stream().mapToInt(Integer::intValue).sum();
		if (Main.saleState.equals("go")) {
			view.add(new ReceiptModel("ÇÒÀÎ", sumAmount + "", sumAmount * 100));
		} else {
			view.add(new ReceiptModel("ÇÒÀÎ", 0 + "", 0));
		}
		
		pack();
		setLocationRelativeTo(null);
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
		new Main().setVisible(true);
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
