package Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Form.Login;
import Utils.DB;

public class TimeLineModel extends Panel {

	public TimeLineModel(String getname, String getago, String getcomment, byte[] img1, byte[] img2, String resname) {
		setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, BorderLayout.EAST);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.SOUTH);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		setSize(top, 0, 80);
		panel.add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		var profilePanel = new JPanel();
		setSize(profilePanel, 0, 50);
		profilePanel.setLayout(new BorderLayout());
		profilePanel.add(new Util("mypage", getname), BorderLayout.WEST);
		
		Util h = new Util("noheart", getago + "ÀÏ Àü");;
		profilePanel.add(h, BorderLayout.EAST);
		top.add(profilePanel, BorderLayout.NORTH);
		
		var comment = new JLabel(getcomment);
		setPlain(comment, 12);
		top.add(comment);
		
		JPanel bottom = new JPanel();
		setSize(bottom, 0, 60);
		setBorder(bottom, Color.LIGHT_GRAY);
		panel.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));
		bottom.add(new Util("icon", resname), BorderLayout.WEST);
		
		Util u = null;
		boolean find = false;
		String[] getPin = DB.getString("select u_pin from user where id = ?", Login.id).split(",");
		String resNo = DB.getString("select r_no from restaurant where r_name = ?", resname);
		for (int i = 0; i < getPin.length; i++) {
			if (getPin[i].equals(resNo)) {
				find = true;
				break;
			}
		}
		if (find) {
			u = new Util("pinblack", "");
		} else {
			u = new Util("pinwhite", "");
		}
		
		u.getImage().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		bottom.add(u, BorderLayout.EAST);
		
		add(verticalStrut_1, BorderLayout.NORTH);
		
		JPanel view = new JPanel();
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		view.add(verticalStrut_2, BorderLayout.NORTH);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		view.add(verticalStrut_3, BorderLayout.SOUTH);
		
		JPanel showImage = new JPanel();
		view.add(showImage, BorderLayout.CENTER);
		showImage.setLayout(new GridLayout(1, 2, 10, 0));
		
		byte[][] imgs = new byte[][] {img1, img2};
		for (int i = 0; i < 2; i++) {
			var image = new JLabel();
			image.setSize(200, 200);
			image.setIcon(imageSize(new ImageIcon(imgs[i]),  image));
			showImage.add(image);
		}
	}

}

class Util extends Panel {
	JLabel image;
	Util(String path, String var) {
		image = new JLabel();
		image.setSize(50, 50);
		image.setIcon(imageSize(new ImageIcon("datafiles/icon/"+path+".png"),image));
		add(image);
		
		var name = new JLabel(var);
		setPlain(name, 12);
		add(name);
	}
	
	JLabel getImage() {
		return image;
	}
}
