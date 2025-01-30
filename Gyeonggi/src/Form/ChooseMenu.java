package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ChildPanel.ItemCount;
import ChildPanel.ShowItem;
import Utils.DB;
import Utils.ImageSize;
import Utils.Msg;

public class ChooseMenu extends JFrame {

	JPanel top, view, bottom;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel left;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel right;
	public static JPanel menuListPanel;
	private JLabel allCancel;
	private JLabel lbl1;
	public static JLabel totalPrice;
	private JLabel lblNewLabel_1;
	private JLabel orderCheck;
	private JPanel pageController;
	private JPanel gapSetting;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private JPanel pageView;
	private int selectedLabel = 0;
	private int startPage = 1;
	private int endPage = 6;
	private int beforeCategorySize = 0;
	public static int price = 0;

	public static void main(String[] args) {
		new ChooseMenu().setVisible(true);
	}

	public ChooseMenu() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("메뉴선택");

		view = new JPanel();
		view.setBackground(Color.white);
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));

		pageController = new JPanel();
		pageController.setBackground(Color.white);
		pageController.setPreferredSize(new Dimension(0, 30));
		view.add(pageController, BorderLayout.SOUTH);

		gapSetting = new JPanel();
		gapSetting.setPreferredSize(new Dimension(500, 400));
		gapSetting.setBackground(Color.white);
		view.add(gapSetting, BorderLayout.CENTER);
		gapSetting.setLayout(new BorderLayout(0, 0));

		horizontalStrut = Box.createHorizontalStrut(30);
		gapSetting.add(horizontalStrut, BorderLayout.WEST);

		horizontalStrut_1 = Box.createHorizontalStrut(30);
		gapSetting.add(horizontalStrut_1, BorderLayout.EAST);

		pageView = new JPanel();
		pageView.setBackground(new Color(255, 255, 255));
		gapSetting.add(pageView, BorderLayout.CENTER);
		pageView.setLayout(new GridLayout(2, 3, 0, 0));

		top = new JPanel();
		top.setBackground(Color.orange);
		top.setPreferredSize(new Dimension(500, 50));
		getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new GridLayout(2, 4, 0, 0));

		String[] nList = new String[] { "커피", "차", "음료", "디저트", "스무디/쉐이크", "샌드위치", "전통차", "아이스크림" };
		JLabel[] labels = new JLabel[nList.length];
		for (int i = 0; i < 8; i++) {
			int capture = i;
			labels[i] = new JLabel(nList[i]);
			labels[0].setForeground(Color.red);
			labels[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					labels[selectedLabel].setForeground(Color.black);
					labels[capture].setForeground(Color.red);
					selectedLabel = capture;
					blackDot = 0;

					pageControllerManage(capture);

					beforeCategorySize = 0;
					for (int i = capture; i > 0; i--) {
						int getSize = DB.getInt("select count(*) from menu where cno = ?", i);
						beforeCategorySize += getSize;
					}
					startPage = beforeCategorySize + 1;
					endPage = beforeCategorySize + 6;
					pageViewManage();
				}
			});
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			top.add(labels[i]);
		}

		bottom = new JPanel();
		bottom.setBackground(Color.white);
		bottom.setPreferredSize(new Dimension(500, 90));
		getContentPane().add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(5);
		bottom.add(verticalStrut, BorderLayout.NORTH);

		Component verticalStrut_1 = Box.createVerticalStrut(5);
		bottom.add(verticalStrut_1, BorderLayout.SOUTH);

		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(30, 0));
		bottom.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		left = new JLabel(" \u25C1");
		left.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (spage == 1) {
					left.setText("◁");
				}
				--spage;
				--epage;
				itemListPanelManage();
			}
		});
		left.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(left, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.white);
		bottom.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblNewLabel_1 = new JLabel("\uC8FC\uBB38\uD655\uC778");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.SOUTH);

		orderCheck = new JLabel("");
		orderCheck.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
				new OrderCheck().setVisible(true);
			}
		});
		orderCheck.setSize(50, 50);
		orderCheck.setIcon(ImageSize.set(new ImageIcon("datafiles/주문확인.png"), orderCheck));
		panel_1.add(orderCheck, BorderLayout.CENTER);

		panel_2 = new JPanel();
		panel_2.setBackground(Color.white);
		bottom.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		panel_3.setBackground(Color.white);
		panel_3.setBorder(BorderFactory.createLineBorder(Color.black));
		panel_3.setPreferredSize(new Dimension(80, 0));
		panel_2.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));

		lbl1 = new JLabel("\uCD1D\uACB0\uC81C\uAE08\uC561");
		panel_3.add(lbl1);

		totalPrice = new JLabel("\\ 0");
		totalPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_3.add(totalPrice);

		allCancel = new JLabel("\uC804\uCCB4\uCDE8\uC18C");
		allCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buyItemList.clear();
				buyItemPrice.clear();
				options.clear();
				optionsAmount.clear();
				amount.clear();
				spage = 0;
				epage = 3;
				price = 0;
				totalPrice.setText("\\ 0");
				itemListPanelManage();
			}
		});
		panel_3.add(allCancel);

		panel_4 = new JPanel();
		panel_4.setBackground(Color.white);
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		panel_5 = new JPanel();
		panel_5.setBackground(Color.white);
		panel_5.setPreferredSize(new Dimension(30, 0));
		panel_4.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new BorderLayout(0, 0));

		right = new JLabel("\u25B7");
		right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				++spage;
				++epage;
				itemListPanelManage();
			}
		});
		right.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(right, BorderLayout.CENTER);

		menuListPanel = new JPanel();
		menuListPanel.setLayout(new GridLayout(1, 3, 10, 0));
		menuListPanel.setBackground(Color.white);
		panel_4.add(menuListPanel, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(null);

		pageControllerManage(0);
		pageViewManage();
		itemListPanelManage();
	}

	public static int spage = 0;
	public static int epage = 3;
	//********************************************************************************************
	public static List<List<Integer>> options = new ArrayList<>();
	public static List<List<Integer>> optionsAmount = new ArrayList<>();
	public static List<String> buyItemList = new ArrayList<>(); //아이템 번호
	public static List<Integer> buyItemPrice = new ArrayList<>(); //옶션 값 까지 포함한 한 개의 아이템 가격
	public static List<Integer> amount = new ArrayList<>(); //아이템 수량
	//********************************************************************************************
	
	public static void itemListPanelManage() {
		menuListPanel.removeAll();
		for (int i = spage; i < epage; i++) {
			var order = new JPanel(new BorderLayout());
			order.setBackground(Color.white);
			order.setBorder(BorderFactory.createLineBorder(Color.black));
			try {
				order.add(new ItemCount(buyItemList.get(i), amount.get(i), i));
			} catch (Exception e) {

			}
			menuListPanel.add(order);
		}
		totalPrice.setText(String.format("\\ %,d", price));
		menuListPanel.revalidate();
		menuListPanel.repaint();
	}

	void pageViewManage() {
		pageView.removeAll();
		var list = DB.getMenu();
		for (int i = startPage; i <= endPage; i++) {
			try {
				if (selectedLabel + 1 == DB.getInt("select cno from menu where mno = ?", i)) {
					int capture = i;
					var panel = new ShowItem("datafiles/menuIMG/" + i + ".png", list.get(i - 1)[1], "\\" + new DecimalFormat().format(Integer.parseInt(list.get(i)[3])));
					panel.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							String opno = DB.getString("select opno from menu where mno = ?", capture);
							
							buyItemList.add(String.valueOf(capture));
							options.add(new ArrayList<>());
							optionsAmount.add(new ArrayList<>());
							for (int i = 0; i < opno.split(",").length; i++) {
								options.get(buyItemList.size() - 1).add(Integer.parseInt(opno.split(",")[i]));
								optionsAmount.get(buyItemList.size() - 1).add(0);
							}
							buyItemPrice.add(DB.getInt("select price from menu where mno = ?", capture));
							amount.add(1);
							price += DB.getInt("select price from menu where mno = ?", capture);
							itemListPanelManage();
							if (buyItemList.size() >= 4) {
								right.setText("▶");
							}
						}
					});
					pageView.add(panel);
				}
			} catch (Exception e) {
				pageView.add(new JPanel());
			}
		}
		pageView.revalidate();
	}

	int blackDot = 0;

	void pageControllerManage(int capture) {
		pageController.removeAll();

		int c_count = DB.getInt("select count(*) from menu where cno = ?", capture + 1);
		int plus = c_count % 6 >= 1 ? 1 : 0;

		var left = new JLabel("<");
		left.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (blackDot != 0) {
					blackDot -= 1;
					startPage -= 6;
					endPage -= 6;

					pageViewManage();
					pageControllerManage(capture);
				}
			}
		});
		left.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pageController.add(left);

		for (int i = 0; i < (c_count / 6) + plus; i++) {
			if (i == blackDot)
				pageController.add(new JLabel("●"));
			else
				pageController.add(new JLabel("○"));
		}

		var right = new JLabel(">");
		right.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (blackDot != (c_count / 6) + plus - 1) {
					left.setText("◀");
					blackDot += 1;
					startPage += 6;
					endPage += 6;

					pageViewManage();
					pageControllerManage(capture);
				}
			}
		});
		right.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		pageController.add(right);
		pageController.revalidate();
		pageController.repaint();
	}
}