package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ChildPanel.Alim;
import ChildPanel.FollowList;
import ChildPanel.MyPage;
import ChildPanel.Raider;
import ChildPanel.TimeLine;
import ChildPanel.Write;
import Model.Frame;
import Model.Panel;
import Utils.DB;

public class Main extends Frame {

	int saveBefore = 0;
	public static JPanel bottom, view;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new Main().setVisible(true);
		});
	}

	public Main() {
		setFrame("메인", 500, 650);
		setSize(500, 650);

		bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(660, 60));
		getContentPane().add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new GridLayout(1, 5, 0, 0));

		view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);

		init();
	}

	void init() {
		switchWindow();
		map();
	}

	void showRes() {
		JScrollPane reslist = new JScrollPane();
		reslist.setBounds(0, 274, 484, 270);
		view.add(reslist);

		JPanel grid = new JPanel();
		reslist.setViewportView(grid);
		grid.setLayout(new GridLayout(0, 1, 0, 10));

		var rsList = DB.getResInfo();

		for (int i = 0; i < rsList.size(); i++) {
			grid.add(new ResModel(rsList.get(i)[1], "3.9", "2", "중식", "영업 중"));
		}
	}

	boolean viewState = true;
	private JTextField search;
	int height = 550;
	Timer timer;
	int angle = 0;
	boolean findres = false;

	void map() {
		view.setLayout(null);

		JLayeredPane layer = new JLayeredPane();

		JLabel remove = new JLabel("X");
		remove.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.setText("");
			}
		});
		remove.setForeground(new Color(0, 255, 64));
		remove.setHorizontalAlignment(SwingConstants.CENTER);
		remove.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		remove.setBounds(425, 8, 30, 30);
		layer.add(remove, JLayeredPane.MODAL_LAYER);

		search = new JTextField();
		search.setBounds(12, 10, 443, 30);
		layer.add(search, JLayeredPane.PALETTE_LAYER);

		if (viewState) {
			height = 550;
			remove.setVisible(false);
			search.setVisible(false);
		} else {
			height = 270;
			remove.setVisible(true);
			search.setVisible(true);
		}
		layer.setBounds(0, 0, 484, height);
		view.add(layer);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 484, height);
		setBorder(scrollPane, new Color(250, 250, 250));
		layer.add(scrollPane, JLayeredPane.DEFAULT_LAYER);

		var map = new JLabel();
		map.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (findres) {
					var people = new JLabel();
					people.setSize(30, 30);
					people.setLocation(e.getX() - 15, e.getY() - 15);
					people.setIcon(imageSize(new ImageIcon("datafiles/icon/person.png"), people));
					map.add(people, JLayeredPane.MODAL_LAYER);

					var raid = new Raider(e.getX(), e.getY(), angle);
					raid.setBounds(e.getX() - 100, e.getY() - 100, 200, 200);
					map.add(raid, JLayeredPane.PALETTE_LAYER);
					timer = new Timer(100, e1 -> {
						if (angle != -360) {
							angle -= 9;
							raid.angle = angle;
							map.revalidate();
							map.repaint();
						} else {
							timer.stop();
						}
					});
					timer.start();
					map.revalidate();
					map.repaint();
				}
			}
		});
		map.setSize(660, 800);
		map.setIcon(imageSize(new ImageIcon("datafiles/map.jpg"), map));
		scrollPane.setViewportView(map);

		JButton foodfind = new JButton("\uB0B4 \uC8FC\uBCC0 \uB9DB\uC9D1 \uCC3E\uAE30");
		foodfind.addActionListener(e -> {
			map.removeAll();
			if (foodfind.getText().equals("내 주변 맛집 찾기")) {
				ok("내 주변 맛집찾기를 시작합니다. 지도를 클릭해 위치를 선정해주세요!");
				foodfind.setText("종료하기");
				findres = true;
			} else {
				ok("맛집 찾기가 종료되었습니다.");
				foodfind.setText("내 주변 맛집 찾기");
				findres = false;
				showIcon(map);
			}

			map.revalidate();
			map.repaint();
		});
		foodfind.setBackground(Color.CYAN);
		foodfind.setForeground(Color.white);
		foodfind.setBounds(12, 490, 141, 29);
		setPlain(foodfind, 12);
		layer.add(foodfind, JLayeredPane.MODAL_LAYER);

		JLabel showRes = new JLabel("");
		showRes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				view.removeAll();
				viewState = false;
				foodfind.setVisible(false);
				showRes();
				map();
				view.revalidate();
				view.repaint();
			}
		});
		showRes.setBounds(390, 460, 65, 60);
		showRes.setIcon(imageSize(new ImageIcon("datafiles/icon/mainicon.png"), showRes));
		layer.add(showRes, JLayeredPane.MODAL_LAYER);
		
		showIcon(map);
	}
	
	void showIcon(JLabel map) {
		var list = DB.getCoordinate();
		for (int i = 0; i < list.size(); i++) {
			int capture = i;
			var label = new JLabel();
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					new FoodInfor(capture + 1).setVisible(true);
				}
			});
			label.setSize(20, 20);
			label.setLocation(list.get(i)[0], list.get(i)[1]);
			label.setIcon(imageSize(new ImageIcon("datafiles/icon/icon.png"), label));
			map.add(label);
		}
	}

	void switchWindow() {
		String[] paths = new String[] { "home", "timeline", "write", "bell", "mypage" };
		MainModel[] ps = new MainModel[5];
		for (int i = 0; i < 5; i++) {
			int capture = i;
			ps[i] = new MainModel(paths[i]);
			ps[i].getImage().addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					ps[capture].getLine().setBackground(Color.CYAN);
					ps[saveBefore].getLine().setBackground(Color.black);
					saveBefore = capture;
					switch (capture) {
						case 0: {
							view.removeAll();
							map();
							view.revalidate();
							view.repaint();
							break;
						}
						case 1: {
							view.removeAll();
							view.setLayout(new BorderLayout());
							if (DB.getString("select u_following from user where id = ?", Login.id).isEmpty()) {
								view.add(new FollowList());
							} else {
								view.add(new TimeLine());
							}
							view.revalidate(); 
							break;
						}
						case 2: {
							view.removeAll();
							view.setLayout(new BorderLayout());
							view.add(new Write());
							view.revalidate();
							break;
						}
						case 3: {
							view.removeAll();
							view.setLayout(new BorderLayout());
							view.add(new Alim());
							view.revalidate();
							break;
						}
						case 4: {
							view.removeAll();
							view.setLayout(new BorderLayout());
							view.add(new MyPage());
							view.revalidate();
							break;
						}
					}
				}
			});
			bottom.add(ps[i]);
		}
		ps[0].getLine().setBackground(Color.CYAN);
	}
}

class MainModel extends Panel {

	JLabel image;
	JPanel line;

	public MainModel(String imgPath) {
		setLayout(new BorderLayout());

		line = new JPanel();
		setSize(line, 0, 1);
		line.setBackground(Color.BLACK);
		add(line, BorderLayout.NORTH);

		image = new JLabel();
		image.setVerticalAlignment(SwingConstants.CENTER);
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setSize(40, 40);
		image.setIcon(imageSize(new ImageIcon("datafiles/icon/" + imgPath + ".png"), image));
		add(image);
	}

	public JLabel getImage() {
		return image;
	}

	public JPanel getLine() {
		return line;
	}
}

class ResModel extends Panel {
	ResModel(String resName, String rating, String rest, String rType, String openingState) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		var f1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		var f2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		var title = new JLabel(resName);
		var ratingest = new JLabel(String.format("별점 : %s   평가 %s명", rating, rest));

		f1.add(title);
		f2.add(ratingest);

		title.setHorizontalAlignment(SwingConstants.LEFT);
		ratingest.setHorizontalAlignment(SwingConstants.LEFT);

		setBold(title, 16);
		setPlain(ratingest, 12);

		add(f1);
		add(f2);
		add(new RestType(rType, openingState));

		var grid = new JPanel(new GridLayout(1, 4, 10, 0));
		setSize(grid, 440, 100);
		String r_no = DB.getString("select r_no from restaurant where r_name = ?", resName);
		var list = DB.getTino(r_no);
		for (int i = 0; i < 2; i++) {
			try {
				for (int j = 1; j <= 2; j++) {
					var image = new JLabel();
					setSize(image, 100, 100);
					image.setSize(100, 100);
					image.setIcon(
							imageSize(new ImageIcon("datafiles/review/tino" + list.get(i) + "-" + j + ".jpg"), image));
					grid.add(image);
				}
			} catch (Exception e) {

			}
		}
		add(grid);
	}
}

class RestType extends Panel {
	RestType(String rName, String openingState) {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		var circle = new JLabel();
		circle.setSize(20, 20);
		circle.setIcon(imageSize(new ImageIcon("datafiles/icon/icon.png"), circle));
		add(circle);

		var restName = new JLabel(rName);
		setPlain(restName, 12);
		add(restName);

		var openState = new JLabel("   " + openingState);
		setPlain(openState, 12);
		add(openState);
	}
}
