package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ChildPanel.RouteView;
import Utils.Circle;
import Utils.DB;
import Utils.ImageSize;
import Utils.Msg;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddRoute extends JFrame {
	private JTextField textField;
	private JTextField x;
	private JTextField y;
	private List<String[]> list = DB.getLocation();

	public static void main(String[] args) {
		new AddRoute().setVisible(true);
	}

	public AddRoute() {
		setTitle("노선 추가");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(500, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("\uC9C0\uC5ED\uBA85");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(80, 20));
		panel.add(textField);
		
		JLabel lblX = new JLabel("\uC704\uCE58 x");
		lblX.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(lblX);
		
		x = new JTextField();
		x.setEnabled(false);
		x.setPreferredSize(new Dimension(50, 20));
		panel.add(x);
		
		JLabel lblY = new JLabel("y");
		lblY.setFont(new Font("굴림", Font.PLAIN, 16));
		panel.add(lblY);
		
		y = new JTextField();
		y.setEnabled(false);
		y.setPreferredSize(new Dimension(50, 20));
		panel.add(y);
		
		JButton add = new JButton("\uCD94\uAC00");
		add.addActionListener(e -> {
			int parseX = Integer.parseInt(x.getText());
			int parseY = Integer.parseInt(y.getText());
			if (textField.getText().isEmpty()) {
			    Msg.fail("빈칸이 있습니다.");
			} else if (DB.isTrue("select count(*) from location where l_name = ?", textField.getText())) {
			    Msg.fail("이미 있는 지역명입니다.");
			} else if (list.stream().anyMatch(entry -> 
			        Math.abs(Integer.parseInt(entry[2]) - parseX) <= 10 && 
			        Math.abs(Integer.parseInt(entry[3]) - parseY) <= 10)) {
			    Msg.fail("인근에 지역이 있습니다.");
			} else {
				Msg.ok("노선이 추가되었습니다.");
				DB.crud("insert into location (l_name, x, y) values (?, ?, ?)", textField.getText(), x.getText(), y.getText());
				dispose();
				new Login().setVisible(true);
			}
		});
		panel.add(add);
		
		JLayeredPane layer = new JLayeredPane();
		layer.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				var rc = new Circle(Color.RED);
				rc.setLocation(e.getX(), e.getY());
				x.setText(e.getX() + "");
				y.setText(e.getY() + "");
				layer.add(rc, JLayeredPane.PALETTE_LAYER);
			}
		});
		layer.setPreferredSize(new Dimension(500, 750));
		getContentPane().add(layer, BorderLayout.CENTER);
		
		var image = new JLabel();
		image.setBounds(0, 0, 500, 750);
		image.setIcon(ImageSize.setSize(new ImageIcon("datafiles/지도.png"), image));
		layer.add(image, JLayeredPane.DEFAULT_LAYER);
		
		for (int i = 0; i < list.size(); i++) {
			var rv = new RouteView(list.get(i)[1]);
			rv.setLocation(Integer.parseInt(list.get(i)[2]), Integer.parseInt(list.get(i)[3]));
			layer.add(rv, JLayeredPane.PALETTE_LAYER);
		}
		
		pack();
		setLocationRelativeTo(null);
	}
}
