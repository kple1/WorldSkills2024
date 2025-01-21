package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Utils.DB;
import Utils.ImageSize;

public class Seat extends JFrame {

	private JPanel view = new JPanel(); 
	private String location1;
	private String location2;
	private String rating;
	private int ratingNumber;
	private List<Object[]> list = DB.getTicket();
	private String date;
	private boolean chooseSeatState = false;
	public static int chooseSeatNumber = 0;
	
	public static void main(String[] args) {
		new Seat("서울", "세종", "2024.08.26 19:00", "우등").setVisible(true);
	}


	public Seat(String location1, String location2, String date, String rating) {
		this.location1 = location1;
		this.location2 = location2;
		this.rating = rating;
		this.date = date;
		
		setBounds(0, 0, 400, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("좌석 선택");
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		JLabel locate = new JLabel(location1 + "→" + location2);
		locate.setFont(new Font("굴림", Font.BOLD, 16));
		topPanel.add(locate);
		
		JLabel dateLabel = new JLabel(date);
		dateLabel.setForeground(new Color(128, 128, 128));
		topPanel.add(dateLabel);
		
		JPanel bottomPanel = new JPanel();
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		JButton select = new JButton("선택 완료");
		select.addActionListener(e -> {
			dispose();
			if (chooseSeatState) {
				Reservation.selectSeatLabel.setText(String.valueOf(chooseSeatNumber));
			}
		});
		bottomPanel.add(select);
		
		getContentPane().add(view, BorderLayout.CENTER);
		
		seatType(rating);
	}
	
	private HashMap<Integer, Integer> disableSeatList = new HashMap<>();
	private HashMap<Integer, Integer> userList = new HashMap<>();
	private TreeMap<Integer, Integer> sortDisableSeat;
	private void disableSeat() {
		int l_no1 = DB.getInt("select l_no from location where l_name = ?", location1);
		int l_no2 = DB.getInt("select l_no from location where l_name = ?", location2);
		
		if (rating.equals("일반")) {
			ratingNumber = 1;
		} else if (rating.equals("우등")) {
			ratingNumber = 2;
		} else if (rating.equals("VIP")) {
			ratingNumber = 3;
		}
		
		for (int i = 0; i < list.size(); i++) {
			String[] getDateTime = date.split(" ");
			int getL_no1 = (int)list.get(i)[0];
			int getL_no2 = (int)list.get(i)[1];
			int getRating = (int)list.get(i)[2];
			String getDate = list.get(i)[5].toString();
			String[] test = getDateTime[0].replace('.', '-').split("-");
			String replaceDate = String.format("%s-%02d-%02d", test[0], Integer.parseInt(test[1]), Integer.parseInt(test[2]));
			String getTime = list.get(i)[6].toString();
			String replaceTime = getDateTime[1].toString() + ":00";
			
			if (getL_no1 == l_no1 && getL_no2 == l_no2 && ratingNumber == getRating && getDate.equals(replaceDate) && getTime.equals(replaceTime)) {
				disableSeatList.put((int)list.get(i)[4], (int)list.get(i)[3]);
				userList.put((int)list.get(i)[3], (int)list.get(i)[4]);
			}
		}
		
		sortDisableSeat = new TreeMap<>(disableSeatList);
	}
	
	private void seatType(String rating) {
		switch (rating) {
			case "일반": {
				view.setLayout(new GridLayout(11, 5, 5, 5));
				seat(55, 53, 2, 5);
				break;
			}
			
			case "우등": {
				view.setLayout(new GridLayout(9, 4, 5, 5));
				seat(36, 0, 1, 4);
				break;
			}
			
			case "VIP": {
				view.setLayout(new GridLayout(5, 2, 5, 5));
				seat(10, 0, 100, 100);
				break;
			}
		}
	}
	
	private void seat(int loop, int ignoreNum, int addedNum, int multiple) {
		disableSeatList.clear();
		disableSeat();
		
		int l_no1 = DB.getInt("select l_no from location where l_name = ?", location1);
		int l_no2 = DB.getInt("select l_no from location where l_name = ?", location2);
		
		int num = 1;
		for (int i = 1; i <= loop; i++) {
			var layer = new JLayeredPane();
			
			final int clickedNum = num; 
			var seat = new JLabel();
			seat.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					seat.setIcon(ImageSize.changeColor(0xFF97c03e));
					chooseSeatState = true;
					chooseSeatNumber = clickedNum;
				}
			});
			seat.setBounds(0, 0, 50, 50);
			if (sortDisableSeat.containsValue(num)) {   
				 if (DB.isTrue("select count(*) from ticket where l_no1 = ? and l_no2 = ? and u_no = ? and rating = ? and seat = ?", l_no1, l_no2, Login.no, ratingNumber, num)) {
					seat.setIcon(ImageSize.changeColor(0xFFff0000));
				} else {
					seat.setIcon(ImageSize.changeColor(0xFFc0c0c0));
				}
			} else {
				seat.setIcon(ImageSize.set(new ImageIcon("datafiles/좌석.png")));	
			}
			
			seat.setHorizontalAlignment(SwingConstants.CENTER);
			layer.add(seat, 100);
			
			var number = new JLabel(String.valueOf(num));
			number.setBounds(0, 0, 50, 50);
			number.setForeground(Color.white);
			number.setHorizontalAlignment(SwingConstants.CENTER);
			layer.add(number, 0);
			
			layer.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					int paneWidth = layer.getWidth();
					int paneHeight = layer.getHeight();
					
					int centerX = paneWidth / 2;
					int centerY = paneHeight / 2;
					
					seat.setLocation(centerX - seat.getWidth() / 2, centerY - seat.getHeight() / 2);
					number.setLocation(centerX - number.getWidth() / 2, centerY - seat.getHeight() / 2);
				}
			});
			
			if ((i + addedNum) % multiple == 0 && i != ignoreNum) {
				view.add(new JLabel());
			} else {
				view.add(layer);
				++num;
			}
		}
	}
}
