package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Utils.Circle;
import Utils.DB;
import Utils.ImageSize;
import Utils.Line;
import Utils.Msg;

public class Route extends JFrame {
	private JLabel departureLabel;
	static final double COST_MULTIPLIER = 50 * 0.8;
	int departureX = 0;
	int departureY = 0;
	int arrivalX = 0;
	int arrivalY = 0;
	int loadingX = 0;
	int loadingY = 0;
	JLayeredPane layer;
	List<Integer[]> saveLineLocation1 = new ArrayList<>();
	List<Integer[]> saveLineLocation2 = new ArrayList<>();
	List<String[]> list = DB.getLocation();
	Timer timer;
	Line line = null;
	int i = 0;

	public static void main(String[] args) {
		new Route().setVisible(true);
	}

	public Route() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("지도");

		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(500, 50));
		getContentPane().add(panel, BorderLayout.NORTH);

		departureLabel = new JLabel("\uCD9C\uBC1C\uC9C0");
		departureLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(departureLabel);

		var c1 = new JComboBox<String>();
		for (int i = 0; i < list.size(); i++) {
			c1.addItem(list.get(i)[1].toString());
		}
		panel.add(c1);

		JLabel arrivalLabel = new JLabel("\uB3C4\uCC29\uC9C0");
		arrivalLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(arrivalLabel);

		var c2 = new JComboBox<String>();
		for (int i = 0; i < list.size(); i++) {
			c2.addItem(list.get(i)[1].toString());
		}
		panel.add(c2);

		JLabel type = new JLabel("\uAD6C\uBD84");
		type.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(type);

		var c3 = new JComboBox<String>();
		c3.addItem("직통");
		c3.addItem("경유");
		panel.add(c3);

		JButton search = new JButton("\uAC80\uC0C9");
		search.addActionListener(e -> {
			reload();

			Reservation.test(c1.getSelectedIndex(), c2.getSelectedIndex(), "Route");
			for (int i = 0; i < Reservation.path.size(); i++) {
				if (i == Reservation.path.size() - 1) continue; 
				departureX = DB.getInt("select x from location where l_name = ?", Reservation.path.get(i));
				departureY = DB.getInt("select y from location where l_name = ?", Reservation.path.get(i));
				arrivalX = DB.getInt("select x from location where l_name = ?", Reservation.path.get(i + 1));
				arrivalY = DB.getInt("select y from location where l_name = ?", Reservation.path.get(i + 1));

				line = new Line(departureX, departureY, arrivalX, arrivalY, Color.RED);
				line.setBounds(0, 0, 500, 750);
				layer.add(line, JLayeredPane.MODAL_LAYER);
			}
			Msg.ok(String.format("%s\n소요시간: %s\n금액: %,d", String.join("→", Reservation.path), 0, Reservation.viaductPrice));
		});
		search.setForeground(new Color(255, 255, 255));
		search.setBackground(new Color(128, 128, 128));
		panel.add(search);

		layer = new JLayeredPane();
		layer.setPreferredSize(new Dimension(500, 750));

		reload();

		pack();
		setLocationRelativeTo(null);
	}

	void reload() {
		layer.removeAll();

		var mapImage = new JLabel();
		mapImage.setBounds(0, 0, 500, 750);
		mapImage.setIcon(ImageSize.setSize(new ImageIcon("datafiles/지도.png"), mapImage));
		layer.add(mapImage, JLayeredPane.DEFAULT_LAYER);
		getContentPane().add(layer, BorderLayout.CENTER);

		List<Integer> distance = new ArrayList<>();
		HashMap<Integer, String> locateOne = new HashMap<Integer, String>();
		HashMap<Integer, String> locateTwo = new HashMap<Integer, String>();
		for (int i = 0; i < list.size(); i++) {
			var circle = new Circle(Color.BLACK);
			circle.setLocation(Integer.parseInt(list.get(i)[2]) - 5, Integer.parseInt(list.get(i)[3]) - 5);
			layer.add(circle, JLayeredPane.POPUP_LAYER);

			for (int j = 0; j < list.size(); j++) {
				if (i == j)
					continue;
				int dis = Reservation.distance(list.get(i)[1].toString(), list.get(j)[1].toString());
				distance.add(dis);
				locateOne.put(dis, list.get(i)[1].toString());
				locateTwo.put(dis, list.get(j)[1].toString());
			}
			Collections.sort(distance);

			for (int j = 0; j < 6; j++) {
				int x1 = DB.getInt("select x from location where l_name = ?", locateOne.get(distance.get(j)));
				int y1 = DB.getInt("select y from location where l_name = ?", locateOne.get(distance.get(j)));
				int x2 = DB.getInt("select x from location where l_name = ?", locateTwo.get(distance.get(j)));
				int y2 = DB.getInt("select y from location where l_name = ?", locateTwo.get(distance.get(j)));

				var line = new Line(x1, y1, x2, y2, Color.WHITE);
				line.setBounds(0, 0, 500, 750);
				layer.add(line, JLayeredPane.PALETTE_LAYER);

			}
			distance.clear();
			locateOne.clear();
			locateTwo.clear();
		}
		layer.revalidate();
		layer.repaint();
	}

	static int calculateDistance(int[] pos1, int[] pos2) {
		return (int) Math.sqrt(Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2));
	}
}
