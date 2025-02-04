package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Model.Frame;
import Model.Panel;
import Utils.DB;

public class FoodInfor extends Frame  {

	public static void main(String[] args) {
		new FoodInfor(1).setVisible(true);
	}

	public FoodInfor(int r_no) {
		setFrame("음식점 정보", 450, 550);
		
		JPanel top = new JPanel();
		getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JLabel title = new JLabel("\uD55C\uC6B8\uBC25\uC0C1");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		top.add(title, BorderLayout.WEST);
		
		JLabel showLocation = new JLabel("\uC704\uCE58\uBCF4\uAE30");
		showLocation.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		showLocation.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		showLocation.setVerticalAlignment(SwingConstants.BOTTOM);
		top.add(showLocation, BorderLayout.EAST);
		
		JScrollPane scroll = new JScrollPane();
		setBorder(scroll, new Color(240, 240, 240));	
		setSize(scroll, 500, 150);
		getContentPane().add(scroll, BorderLayout.SOUTH);
		
		var grid = new JPanel(new GridLayout(0, 1, 0, 0));
		scroll.setViewportView(grid);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel resinfo = new JPanel();
		panel.add(resinfo, BorderLayout.SOUTH);
		resinfo.setLayout(new BoxLayout(resinfo, BoxLayout.Y_AXIS));
		
		var rating = new JLabel("별점 4.4 평가 4명");//동적으로 변경
		var operatingTime = new JLabel("수요일 휴무 OPEN AM 9:30   CLOSE PM 08:30");//동적으로 변경
		var resType = new JLabel("한식");//동적으로 변경
		resType.setSize(20, 20);
		resType.setIcon(imageSize(new ImageIcon("datafiles/icon/icon.png"), resType));
		
		setPlain(16, rating, operatingTime, resType);
		
		resinfo.add(rating);
		resinfo.add(operatingTime);
		resinfo.add(resType);
		
		JPanel imageView = new JPanel();
		panel.add(imageView, BorderLayout.CENTER);
		imageView.setLayout(new GridLayout(1, 2, 10, 0));
		
		var img = DB.getImage(r_no + "");
		var img1 = new JLabel();
		setSize(img1, 200, 200);
		img1.setSize(200, 200);
		img1.setIcon(imageSize(new ImageIcon(img[0]), img1));
		
		var img2 = new JLabel();
		setSize(img2, 200, 200);
		img2.setSize(200, 200);
		img2.setIcon(imageSize(new ImageIcon(img[1]), img2));
		
		imageView.add(img1);
		imageView.add(img2);
		
		var review = new JLabel("리뷰");
		setPlain(review, 16);
		grid.add(review);
		var list = DB.getResComment(r_no + "");
		for (int i = 0; i < list.size(); i++) {
			grid.add(new FoodInforModel(list.get(i)[0], list.get(i)[1]));
		}
	}
}

class FoodInforModel extends Panel {
	FoodInforModel(String getName, String comment) {
		setLayout(new BorderLayout());
		
		var flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		var mypage = new JLabel();
		mypage.setSize(30, 30);
		mypage.setIcon(imageSize(new ImageIcon("datafiles/icon/mypage.png"), mypage));
		flow.add(mypage);
		var name = new JLabel(getName);
		flow.add(name);
		var message = new JLabel(comment);
		setPlain(name, 12);
		setPlain(message, 12);
		
		add(flow, BorderLayout.NORTH);
		add(message, BorderLayout.SOUTH);
	}
}
