package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Frame;

public class Main extends Frame {
	public JPanel top;
	public JPanel panel;
	public JLabel image;
	public JPanel east;
	public JPanel west;
	public JPanel center;
	public static JLabel login;
	public static void main(String[] args) {
		new Main().setVisible(true);
	}
	
	public Main() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setFrame("메인", 700, 450);
		setSize(700, 450);
		
		top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		top.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		east = new JPanel();
		east.setBackground(new Color(255, 255, 255));
		east.setPreferredSize(new Dimension(100, 0));
		top.add(east, BorderLayout.EAST);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		image = new JLabel("");
		image.setBounds(0, 0, 684, 361);
		image.setIcon(imageSize(new ImageIcon("datafiles/company.jpg"), image));
		panel.add(image);
		
		login = new JLabel();
		login.addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		var mypage = new JLabel();
		login.setSize(30, 30);
		mypage.setSize(30, 30);
		login.setIcon(imageSize(new ImageIcon("datafiles/아이콘/login.png"), login));
		mypage.setIcon(imageSize(new ImageIcon("datafiles/아이콘/mypage.png"), mypage));
		east.add(login);
		east.add(mypage);
		
		west = new JPanel();
		west.setBackground(new Color(255, 255, 255));
		top.add(west, BorderLayout.WEST);
		
		var img1 = new JLabel();
		var img2 = new JLabel();
		img1.setSize(100, 40);
		img2.setSize(80, 40);
		img1.setIcon(imageSize(new ImageIcon("datafiles/로고/logo.png"), img1));
		img2.setIcon(imageSize(new ImageIcon("datafiles/로고/tabocar.png"), img2));
		west.add(img1);
		west.add(img2);
		
		center = new JPanel();
		center.setBackground(Color.white);
		top.add(center, BorderLayout.CENTER);
		int c = 0;
		String[] ls = new String[] {"실시간 예약", "예약확인", "차량목록", "렌트통계"};
		for (int i = 0; i < 9; i++) {
			int capture = i;
			var label = new JLabel();
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					dispose();
					if (capture == 1) {
						new Reservation().setVisible(true);
					} else if (capture == 3) {
						new CheckReservation().setVisible(true);
					} else if (capture == 5) {
						new CarList().setVisible(true);
					} else if (capture == 7) {
						new Graph().setVisible(true);
					}
				}
			});
			if ((i + 1) % 2 == 0) {
				label.setText(ls[c]);
				++c;
			} else {
				label.setText("|");
			}
			setFont(16, label);
			center.add(label);
		}
	}
}