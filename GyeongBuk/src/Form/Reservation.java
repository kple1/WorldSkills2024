package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Utils.DB;
import Utils.ImageSize;
import Utils.Msg;
import Utils.Node;

public class Reservation extends JFrame {
	public static JLabel selectSeatLabel;
	private JTextField start;
	private JTextField end;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	static DefaultTableModel model3;
	private List<String[]> list = DB.getLocation();
	static List<Integer> distances = new ArrayList<>();
	static int month = LocalDate.now().getMonthValue();
	static int hour = LocalDateTime.now().getHour();
	static int dayOfMonth = LocalDate.now().getDayOfMonth();
	private int c1 = 0;
	private int c2 = 0;
	private int clickedRow = -1;
	static String location1;
	static String location2;
	public static JLabel day;
	
	public static void main(String[] args) {
		new Reservation().setVisible(true);
	}

	private void init() {
		model1 = new DefaultTableModel(new Object[][] {}, new String[] { "No", "이름" });
		model2 = new DefaultTableModel(new Object[][] {}, new String[] { "No", "이름" });
		model3 = new DefaultTableModel(new Object[][] {}, new String[] { "No", "출발시간", "도착시간", "좌석" });
		
		table_1 = new JTable();
		table_2 = new JTable();
		table_3 = new JTable();
	}
	
	public Reservation() {
		setBounds(0, 0, 400, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("예매");

		init();
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel logo = new JLabel("");
		logo.setBounds(0, 0, 50, 50);
		logo.setIcon(ImageSize.set(new ImageIcon("datafiles/로고.png")));
		panel.add(logo, BorderLayout.WEST);

		Component st1 = Box.createHorizontalStrut(20);
		panel.add(st1, BorderLayout.EAST);

		JLabel title = new JLabel("\uACE0\uC18D\uBC84\uC2A4 \uC608\uB9E4");
		title.setFont(new Font("굴림", Font.PLAIN, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(title, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(0, 30));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		Component st2 = Box.createHorizontalStrut(20);
		panel_2.add(st2);

		JLabel dayLabel = new JLabel("\uB0A0\uC9DC");
		panel_2.add(dayLabel);

		Component st3 = Box.createHorizontalStrut(20);
		panel_2.add(st3);

		JLabel calendar = new JLabel("\uD83D\uDCC5");
		calendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Calendar("Reservation").setVisible(true);
			}
		});
		calendar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 12));
		panel_2.add(calendar);

		day = new JLabel("날짜를 선택하세요");
		day.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		panel_2.add(day);

		Component st4 = Box.createHorizontalStrut(270);
		panel_2.add(st4);

		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		JLabel locate = new JLabel("");
		locate.setFont(new Font("굴림", Font.PLAIN, 16));
		panel_13.add(locate, BorderLayout.WEST);

		JLabel price = new JLabel("6,800");
		price.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		panel_13.add(price, BorderLayout.EAST);

		Component st5 = Box.createHorizontalStrut(20);
		panel_1.add(st5, BorderLayout.WEST);

		JPanel area_b = new JPanel();
		area_b.setVisible(false);
		area_b.setPreferredSize(new Dimension(400, 0));
		panel_1.add(area_b, BorderLayout.EAST);
		area_b.setLayout(new BorderLayout(0, 0));

		Component st6 = Box.createHorizontalStrut(20);
		area_b.add(st6, BorderLayout.WEST);

		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(0, 30));
		area_b.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.WEST);

		Component st7 = Box.createHorizontalStrut(20);
		panel_9.add(st7);

		JLabel rankLabel = new JLabel("\uB4F1\uAE09");
		panel_9.add(rankLabel);

		JComboBox<String> com1 = new JComboBox<String>();
		com1.addItem("일반");
		com1.addItem("우등");
		com1.addItem("VIP");
		panel_9.add(com1);

		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.EAST);

		JLabel divisionLabel = new JLabel("\uAD6C\uBD84");
		panel_10.add(divisionLabel);

		var com2 = new JComboBox<String>();
		com2.addItem("직통");
		com2.addItem("경유");
		panel_10.add(com2);

		Component st8 = Box.createHorizontalStrut(20);
		panel_10.add(st8);

		JPanel panel_11 = new JPanel();
		panel_11.setPreferredSize(new Dimension(0, 30));
		area_b.add(panel_11, BorderLayout.SOUTH);
		panel_11.setLayout(new BorderLayout(0, 0));

		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.WEST);
		panel_12.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel seat = new JLabel("");
		seat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				var getStartTime = table_3.getValueAt(clickedRow, 1) + ":00";
				new Seat(location1, location2, day.getText() + " " + getStartTime, com1.getSelectedItem().toString()).setVisible(true);
			}
		});
		seat.setSize(30, 30);
		seat.setIcon(ImageSize.setSize(new ImageIcon("datafiles/좌석.png"), seat));
		seat.setBackground(Color.gray);
		panel_12.add(seat);

		selectSeatLabel = new JLabel("\uC88C\uC11D\uC744 \uC120\uD0DD\uD574\uC8FC\uC138\uC694.");
		panel_12.add(selectSeatLabel);

		JButton successReservation = new JButton("\uC608\uC57D\uC644\uB8CC");
		successReservation.addActionListener(e -> {
			if (seat.getText().equals("좌석을 선택해주세요.") || clickedRow == -1) {
				Msg.fail("일정과 좌석을 선택하세요.");
			} else {
				Msg.ok("예매가 완료되었습니다.");
				
				int l1 = DB.getInt("select l_no from location where l_name = ?", location1);
				int l2 = DB.getInt("select l_no from location where l_name = ?", location2);
				var getStartTime = table_3.getValueAt(clickedRow, 1) + ":00";
				
				//회원 비회원 판별
				DB.crud("insert into ticket(u_no, l_no1, l_no2, date, time, rating, division, seat, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 
						Login.no, 
						l1, 
						l2, 
						day.getText(), 
						getStartTime, 
						com1.getSelectedIndex() + 1, 
						com2.getSelectedIndex() + 1, 
						selectSeatLabel.getText(), 
						price.getText().replace(",", ""));
			}
		});
		panel_11.add(successReservation, BorderLayout.EAST);

		Component st9 = Box.createHorizontalStrut(20);
		area_b.add(st9, BorderLayout.EAST);

		JScrollPane scrollPane_2 = new JScrollPane();
		area_b.add(scrollPane_2, BorderLayout.CENTER);

		table_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!day.getText().equals("날짜를 선택하세요")) {
					location1 = (String) table_1.getValueAt(table_1.getSelectedRow(), 1);
				} else {
					Msg.fail("출발날짜를 선택하세요.");
				}
			}
		});
		table_1.setModel(model1);
		table_1.getTableHeader().setBackground(Color.blue);
		table_1.getTableHeader().setForeground(Color.white);
		
		for (int i = 0; i < list.size(); i++) {
			model1.addRow(new Object[] { i + 1, list.get(i)[1] });
		}
		
		scrollPane_2.setViewportView(table_3);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(160, 0));
		panel_3.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(0, 25));
		panel_4.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel startLabel = new JLabel("\uCD9C\uBC1C\uC9C0");
		panel_6.add(startLabel);

		start = new JTextField();
		start.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					model1.setRowCount(0);
					c1 = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i)[2].contains(start.getText()) || start.getText().isEmpty()) {
							++c1;
							model1.addRow(new Object[] { c1, list.get(i)[1] });
						}
					}

					if (c1 == 0) {
						Msg.fail("검색된 역이 없습니다.");
					}
				}
			}
		});
		panel_6.add(start);
		start.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);

		table_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (location1 != null) {
					location2 = (String) table_2.getValueAt(table_2.getSelectedRow(), 1);
					locate.setText(location1 + "→" + location2);

					comboEvent(com1, com2, locate, price);
					
					area_b.setVisible(true);
					setSize(800, 500);
				} else {
					Msg.fail("출발지를 먼저 선택하세요.");
				}
			}
		});
		table_2.getTableHeader().setBackground(Color.blue);
		table_2.getTableHeader().setForeground(Color.white);
		table_2.setModel(model2);

		for (int i = 0; i < list.size(); i++) {
			model2.addRow(new Object[] { i + 1, list.get(i)[1] });
		}

		scrollPane.setViewportView(table_1);

		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(160, 0));
		panel_3.add(panel_5, BorderLayout.EAST);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(0, 25));
		panel_5.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel endLabel = new JLabel("\uB3C4\uCC29\uC9C0");
		panel_7.add(endLabel);

		end = new JTextField();
		end.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					model2.setRowCount(0);
					c2 = 0;
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i)[2].contains(end.getText()) || end.getText().isEmpty()) {
							++c2;
							model2.addRow(new Object[] { c2, list.get(i)[1] });
						}
					}
					if (c2 == 0) {
						Msg.fail("검색된 역이 없습니다.");
					}
				}
			}
		});
		panel_7.add(end);
		end.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_5.add(scrollPane_1, BorderLayout.CENTER);
		
		table_3.getTableHeader().setBackground(Color.blue);
		table_3.getTableHeader().setForeground(Color.white);
		table_3.setModel(model3);
		scrollPane_1.setViewportView(table_2);
		
		table_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table_3.setSelectionBackground(Color.yellow);
				clickedRow = table_3.getSelectedRow();
			}
		});
		
		com1.addActionListener(e -> {
			comboEvent(com1, com2, locate, price);
		});
		
		com2.addActionListener(e -> {
			comboEvent(com1, com2, locate, price);
		});
	}
	
	//combo1, 2, jtable_2 클릭에 대한 이벤트
	private void comboEvent(JComboBox<String> com1, JComboBox<String> com2, JLabel locate, JLabel price) {
		double divisionOption = 0;
		if (com1.getSelectedIndex() == 0) {
			divisionOption = 1;
		} else if (com1.getSelectedIndex() == 1) {
			divisionOption = 1.2;
		} else {
			divisionOption = 1.5;
		}
		
		String[] getBirth = DB.getString("select u_birth from user where u_id = ?", Login.id).split("-");
		int age = 2026 - Integer.parseInt(getBirth[0]);
		int getMonth = Integer.parseInt(getBirth[1]);
		int getDay = Integer.parseInt(getBirth[2]);
		int nowMonth = LocalDate.now().getMonthValue();
		int nowDay = LocalDate.now().getDayOfMonth();
		if (getMonth >= nowMonth && getDay >= nowDay) {
			++age;
		}
		
		double ageOption = 0;
		if (age < 13) {
			ageOption = 0.5;
		} else if (age < 19) {
			ageOption = 0.8;
		} else if (age >= 65) {
			ageOption = 0.6;
		} else {
			ageOption = 1;
		}
		
		if (com2.getSelectedIndex() == 0) {
			direct();
			locate.setText(location1 + "→" + location2);
			
			int result = (int) ((int) (((distance(location1, location2) * 50) * 2) * divisionOption) * ageOption);
			price.setText(String.format("%,d", result));
		} else {		
			test(table_1.getSelectedRow(), table_2.getSelectedRow(), "Reservation");
			locate.setText(String.join("→", path));
			price.setText(String.format("%,d", viaductPrice));
		}
	}
	
	//직통
	private void direct() {
		model3("직통");
	}

	//직통 피타고라스
	public static int distance(String locateName1, String locateName2) {
		int locate1_x = DB.getInt("select x from location where l_name = ?", locateName1);
		int locate1_y = DB.getInt("select y from location where l_name = ?", locateName1);
		int locate2_x = DB.getInt("select x from location where l_name = ?", locateName2);
		int locate2_y = DB.getInt("select y from location where l_name = ?", locateName2);
		int a = Math.abs(locate1_x - locate2_x);
		int b = Math.abs(locate1_y - locate2_y);
		int result = (int) Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2))) / 2;
		return result;
	}
	
	//경유(거리 * 1.1) 합계
	static int summary() {
		int sum = 0;
		for (int i = 0; i < distances.size(); i++) {
			if (i == 0) {
				sum += distances.get(i);
			} else {
				sum += distances.get(i) * 1.1;
			}
		}
		return sum;
	}
	
	//경유, 직통에 따라 model3에 row추가
	static void model3(String option) {
		int dis = distance(location1, location2);
		
		int val = option.equals("직통") ? dis : summary();
		
		model3.setRowCount(0);
		if (day.getText().equals("2025-" + month + "-" + dayOfMonth) && hour >= 6 && hour < 22) {
			for (int i = hour; i < 22; i++) {
				int count = i - hour + 1;
				model3.addRow(new Object[] { count, String.format("%s:00", i), String.format("%s:%s", i + val / 60, val % 60), "0/45" });
			}
		} else {
			for (int i = 1; i < 18; i++) {
				model3.addRow(new Object[] { i, String.format("%s:00", 5 + i), String.format("%s:%s", 5 + i + val / 60, val % 60), "0/45" });
			}
		}
	}
	
	static List<String> path;
	static int viaductPrice;
	public static void test(int start, int end, String whereIsGoing) {
		var list = DB.getLocation();
        int[][] positions = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
        	positions[i][0] = Integer.parseInt(list.get(i)[2]);
        	positions[i][1] = Integer.parseInt(list.get(i)[3]);
        }
        
        int n = list.size();
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            List<Node> nearest = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int distance = Route.calculateDistance(positions[i], positions[j]);
                    nearest.add(new Node(j, distance));
                }
            }

            nearest.sort(Comparator.comparingInt(o -> o.cost));
            for (int k = 0; k < Math.min(6, nearest.size()); k++) { 
                Node neighbor = nearest.get(k);
                graph.get(i).add(neighbor);
                graph.get(neighbor.id).add(new Node(i, neighbor.cost));
            }
        }

        if (start == -1 || end == -1) {
            System.out.println("잘못된 도시 이름입니다.");
            return;
        }

        int[] distances = new int[n];
        int[] previous = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        boolean[] visited = new boolean[n];

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (visited[current.id]) continue;
            visited[current.id] = true;

            for (Node neighbor : graph.get(current.id)) {
                int newDist = distances[current.id] + neighbor.cost;
                if (!visited[neighbor.id] && newDist < distances[neighbor.id]) {
                    distances[neighbor.id] = newDist;
                    previous[neighbor.id] = current.id;
                    pq.add(new Node(neighbor.id, newDist));
                }
            }
        }

        path = new ArrayList<>();
        for (int at = end; at != -1; at = previous[at]) {
            path.add(list.get(at)[1]);   
        }
        Collections.reverse(path);
	}
}
            