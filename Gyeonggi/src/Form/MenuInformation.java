package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ChildPanel.DC;
import ChildPanel.OptionPanel;
import Utils.DB;
import Utils.DCController;
import Utils.ImageSize;

public class MenuInformation extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new MenuInformation("1", 1, 1, "1,2,5,6").setVisible(true);
	}

	JButton ice, ice1, ice2, hot, cancel, select;
	JPanel iceType;
	public static JLabel totalPrice;
	public static int tPrice;
	public int price;
	public static int itemAmount;
	int amount;
	int index;

	public MenuInformation(String num, int amount, int index, String opno) {
		price = DB.getInt("select price from menu where mno = ?", num);
		tPrice = price * amount;
		itemAmount = amount;
		this.amount = amount;
		this.index = index;
		
		setTitle("»ó¼¼Á¤º¸");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(0, 0, 356, 349);

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(0, 150));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel coldTypePanel = new JPanel();
		coldTypePanel.setBackground(Color.white);
		coldTypePanel.setPreferredSize(new Dimension(0, 50));
		panel.add(coldTypePanel, BorderLayout.SOUTH);

		hot = new JButton("HOT");
		hot.setBackground(new Color(255, 255, 255));
		hot.setForeground(Color.red);
		hot.setBorder(BorderFactory.createLineBorder(Color.red));
		hot.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		hot.setPreferredSize(new Dimension(100, 45));
		coldTypePanel.add(hot);

		ice = new JButton("ICED");
		ice.setForeground(Color.blue);
		ice.setBorder(BorderFactory.createLineBorder(Color.blue));
		ice.setBackground(new Color(255, 255, 255));
		ice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		ice.setPreferredSize(new Dimension(100, 45));
		coldTypePanel.add(ice);

		JLabel image = new JLabel("");
		image.setPreferredSize(new Dimension(70, 0));
		image.setSize(70, 100);
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/menuIMG/" + num + ".png"), image));
		panel.add(image, BorderLayout.WEST);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.white);
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel name = new JLabel(DB.getString("select name from menu where mno = ?", num));
		name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel_3.add(name);

		JLabel empty = new JLabel("");
		panel_3.add(empty);

		var controller = new DCController();
		DC dc = new DC(amount, controller);
		var listener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(dc.countClick())) {
					tPrice += price;
				} else if (e.getSource().equals(dc.discountClick())) {
					if (itemAmount != 1) {
						tPrice -= price; 
					}
				}
				itemAmount = dc.getAmount();
				totalPrice.setText(String.format("\\ %,d", tPrice));
			}
		};

		dc.countClick().addMouseListener(listener);
		dc.discountClick().addMouseListener(listener);

		dc.setBackground(Color.white);
		panel_3.add(dc);

		totalPrice = new JLabel("\\ " + tPrice);
		totalPrice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		totalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(totalPrice);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(350, 60));
		bottomPanel.setBackground(Color.white);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		cancel = new JButton("\uCDE8\uC18C");
		cancel.setBackground(new Color(255, 255, 255));
		cancel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		cancel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		cancel.setPreferredSize(new Dimension(100, 45));
		bottomPanel.add(cancel);

		select = new JButton("\uC120\uD0DD\uC644\uB8CC");
		select.setBackground(new Color(255, 255, 255));
		select.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		select.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		select.setPreferredSize(new Dimension(100, 45));
		bottomPanel.add(select);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.white);
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 100));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_2.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		iceType = new JPanel();
		iceType.setBackground(new Color(255, 255, 255));
		panel_1.add(iceType);
		iceType.setLayout(new BorderLayout(0, 0));

		JPanel line = new JPanel();
		line.setPreferredSize(new Dimension(0, 1));
		line.setBackground(new Color(0, 0, 0));
		iceType.add(line, BorderLayout.SOUTH);

		JLabel iceLabel = new JLabel("\uC5BC\uC74C");
		iceLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		iceType.add(iceLabel, BorderLayout.WEST);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		iceType.add(panel_6, BorderLayout.EAST);

		ice1 = new JButton("\uAC01\uC5BC\uC74C");
		ice1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		ice1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		ice1.setForeground(new Color(0, 0, 255));
		ice1.setBackground(new Color(255, 255, 255));
		ice1.setPreferredSize(new Dimension(60, 30));
		panel_6.add(ice1);

		ice2 = new JButton("\uAC04\uC5BC\uC74C");
		ice2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		ice2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		ice2.setForeground(new Color(0, 0, 255));
		ice2.setPreferredSize(new Dimension(60, 30));
		ice2.setBackground(new Color(255, 255, 255));
		panel_6.add(ice2);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel line_1 = new JPanel();
		line_1.setPreferredSize(new Dimension(0, 1));
		line_1.setBackground(Color.BLACK);
		panel_5.add(line_1, BorderLayout.SOUTH);

		JLabel euroOptionLabel = new JLabel("\uC720\uB8CC\uC635\uC158");
		euroOptionLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel_5.add(euroOptionLabel, BorderLayout.WEST);

		JPanel optionPanel = new JPanel();
		optionPanel.setBackground(new Color(255, 255, 255));
		panel_2.add(optionPanel, BorderLayout.CENTER);
		optionPanel.setLayout(new GridLayout(4, 1, 0, 0));

		for (int i = 0; i < opno.split(",").length; i++) {
			String oname = DB.getString("select name from options where opno = ?", opno.split(",")[i]);
			int price = DB.getInt("select price from options where opno = ?", opno.split(",")[i]);
			optionPanel.add(new OptionPanel(oname, price, index, i));
		}

		hot.addActionListener(this);
		ice.addActionListener(this);
		cancel.addActionListener(this);
		select.addActionListener(this);
		
		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(hot)) {
			hot.setBackground(Color.red);
			hot.setForeground(Color.white);
			ice.setBackground(Color.white);
			ice.setForeground(Color.blue);
			iceType.setVisible(false);
		} else if (e.getSource().equals(ice)) {
			hot.setBackground(Color.white);
			hot.setForeground(Color.red);
			ice.setBackground(Color.blue);
			ice.setForeground(Color.white);
			iceType.setVisible(true);
		} else if (e.getSource().equals(cancel)) {
			dispose();
		} else if (e.getSource().equals(select)) {
			ChooseMenu.price -= price * amount;
			ChooseMenu.price += tPrice;
			ChooseMenu.totalPrice.setText(String.format("\\ %,d", ChooseMenu.price));
			ChooseMenu.amount.set(index, itemAmount);
			ChooseMenu.buyItemPrice.set(index, tPrice);
			ChooseMenu.itemListPanelManage();
			dispose();
		}
	}
}
