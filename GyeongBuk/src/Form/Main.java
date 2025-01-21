package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Utils.ImageSize;

public class Main extends JFrame {

	Timer timer;
    int x = 0;
    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    public Main() {
        setTitle("메인");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); 

        var topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(0, 50));

        var label = new JLabel();
        label.setBounds(0, 0, 50, 50);
        label.setIcon(ImageSize.set(new ImageIcon("datafiles/로고.png")));
        topPanel.add(label, BorderLayout.WEST);

        var title = new JLabel("시외버스 예약 시스템");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.black);
        title.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        topPanel.add(title, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        
        var centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(0, 200));
        centerPanel.setLayout(new GridLayout(4, 1, 0, 5));
        String[] btNames = new String[] {"로그인", "비회원 예매하기", "비회원 예매 확인 및 변경", "노선 보기"};
        for (int i = 0; i < 4; i++) {
        	final int capture = i;
        	
        	var button = new JButton(btNames[i]);
        	if (i == 0 && !Login.id.isEmpty()) {
        		button.setText("로그아웃");
        		btNames[i] = "로그아웃";
        	}
        	if (i == 1 && !Login.id.isEmpty()) {
        		button.setText("예매하기");
        		btNames[i] = "예매하기";
        	}
        	
        	button.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e) {
    				dispose();
    				switch (btNames[capture]) {
    					case "예매하기": new Reservation().setVisible(true); break;
    					case "비회원 예매하기": new NonUser().setVisible(true); break;
    					case "로그인": new Login().setVisible(true); break;
    				}
        		}
        	});
        	button.setPreferredSize(new Dimension(0, 35));
        	label.setFont(new Font("맑은 고딕", 12, Font.PLAIN));
        	centerPanel.add(button);
        }
        add(centerPanel, BorderLayout.CENTER);
        
        var bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 100));
        
        images = new JPanel(new GridLayout(1, 4, 5, 0));
        images.setBounds(0, 0, getWidth() * 4, 100);
        
        bottomPanel.add(images);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        timer = new Timer(10, e -> {
        	if (x == 0) {
        		timer.setDelay(1000);
                reload();
        	}
        	
        	if (x >= getWidth() * -1) {
        		timer.setDelay(10);
        		x -= 5;
        		images.setLocation(x, 0);
        	} else {
        		x = 0;
        	}
    		//System.out.println(x + " / " + getWidth() * -1);
        });
        timer.start();
    }
    
    JPanel images;
    void reload() {
    	images.removeAll();
    	images.setBounds(0, 0, getWidth() * 4, 100);
        for (int i = 0; i < 4; i++) {
        	var image = new JLabel();
        	image.setSize(getWidth(), 100);
        	image.setIcon(ImageSize.set(new ImageIcon("datafiles/홍보/" + paths[i] + ".gif")));
        	images.add(image);
        }
        images.revalidate();
        images.repaint();
        images.setLocation(0, 0);
        
		int save = paths[0];
        for (int i = 0; i < 3; i++) { 
            paths[i] = paths[i + 1];
        }
        paths[3] = save;
    }
    
    int paths[] = new int[] {1, 2, 3, 4};
}