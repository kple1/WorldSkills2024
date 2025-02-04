package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Form.Main;
import Model.Panel;

public class MyPage extends Panel implements MouseListener {

	public MyPage() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel top = new JPanel();
		setSize(top, 500, 100);
		add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		var profile = new JLabel();
		profile.setSize(100, 100);
		profile.setIcon(imageSize(new ImageIcon("datafiles/icon/mypage.png"), profile));
		
		top.add(profile, BorderLayout.WEST);
		
		var myinfo = new JPanel(new BorderLayout());
		var name = new JLabel("성준호님,");
		setBold(name, 30);
		myinfo.add(name, BorderLayout.NORTH);
		
		var follow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		myinfo.add(follow, BorderLayout.CENTER);
		
		follower = new JLabel("팔로워 5명");
		following = new JLabel("팔로잉 5명");
		setPlain(follower, 16);
		setPlain(following, 16);
		
		follow.add(follower);
		follow.add(Box.createHorizontalStrut(20));
		follow.add(following);
		
		top.add(myinfo, BorderLayout.CENTER);
		
		JPanel view = new JPanel();
		add(view, BorderLayout.CENTER);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		String[] mname = new String[] {"아이디 변경하기", "비밀번호 변경하기", "로그아웃 하기"};
		String[] pats = new String[] {"next.png", "next.png", "signout.png"};
		for (int i = 0; i < 3; i++) {
			view.add(new MyPageModel(mname[i], pats[i]));
		}
		
		follower.addMouseListener(this);
		following.addMouseListener(this);
	}

	JLabel follower, following;
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(follower)) {
			Main.view.removeAll();
			Main.view.setLayout(new BorderLayout());
			Main.view.add(new FollowFollowing("팔로워"));
			Main.view.revalidate();
		} else if (e.getSource().equals(following)) {
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class MyPageModel extends Panel {
	MyPageModel (String modelName, String modelImage) {
		setMSize(this, 500, 35);
		setLayout(new BorderLayout());
		
		var name = new JLabel(modelName);
		setPlain(name, 12);
		
		var image = new JLabel();
		image.setSize(30, 30);
		image.setIcon(imageSize(new ImageIcon("datafiles/icon/" + modelImage), image));
		
		add(name, BorderLayout.WEST);
		add(image, BorderLayout.EAST);
		
		var line = new JPanel();
		setSize(line, 500, 1);
		line.setBackground(Color.black);
		add(line, BorderLayout.SOUTH);
	}
}