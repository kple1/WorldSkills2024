package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Frame;
import Utils.DB;

public class RegistCar extends Frame implements ActionListener {
	public JPanel bottom;
	public JButton cancel;
	public JButton regist;
	public JPanel panel;
	public JLabel image;
	public JPanel p1;
	public JPanel p2;
	public JTextField textField;
	public JPanel p3;
	public JComboBox<String> c1;
	public JComboBox<String> c2;
	public JPanel p4;
	public JPanel p5;
	public String path;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new RegistCar().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	boolean pictureRegist = true;
	public RegistCar() {
		setFrame("차량등록", 600, 400);
		setSize(600, 298);
		
		bottom = new JPanel();
		getContentPane().add(bottom, BorderLayout.SOUTH);
		
		cancel = new JButton("\uCDE8\uC18C");
		bottom.add(cancel);
		
		regist = new JButton("\uB4F1\uB85D");
		bottom.add(regist);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		
		image = new JLabel("");
		image.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					var file = new JFileChooser();
					var filter = new FileNameExtensionFilter("i am a pizza...", "jpg");
					file.setFileFilter(filter);
					int re =  file.showOpenDialog(null);
					if (re == JFileChooser.APPROVE_OPTION) {
						File sel = file.getSelectedFile();  //절대 경로
						image.setIcon(imageSize(new ImageIcon(String.valueOf(sel)), image));
						pictureRegist = false;
						path = String.valueOf(sel);
					}
				}
			}
		});
		image.setBorder(BorderFactory.createLineBorder(Color.black));
		image.setPreferredSize(new Dimension(200, 200));
		panel.add(image);
		
		p1 = new JPanel();
		getContentPane().add(p1, BorderLayout.CENTER);
		p1.setLayout(new BorderLayout(0, 0));
		
		p2 = new JPanel();
		p2.setPreferredSize(new Dimension(0, 80));
		p1.add(p2, BorderLayout.NORTH);
		p2.setLayout(new GridLayout(2, 1, 0, 10));
		
		textField = new JTextField();
		placeholder(textField, "제목을 입력해주세요.");
		p2.add(textField);
		textField.setColumns(10);
		
		p3 = new JPanel();
		p2.add(p3);
		p3.setLayout(new GridLayout(1, 2, 5, 0));
		
		c1 = new JComboBox<String>();
		c1.addItem("= 차종 =");
		for (int i = 0; i < 8; i++) {
			c1.addItem(DB.getString("select t_name from type where t_no = ?", i + 1));
		}
		p3.add(c1);
		
		c2 = new JComboBox<String>();
		c2.addItem("= 연료 =");
		for (int i = 0; i < 4; i++) {
			c2.addItem(DB.getString("select f_name from fuel where f_no = ?", i + 1));
		}
		p3.add(c2);
		
		p4 = new JPanel();
		p1.add(p4, BorderLayout.WEST);
		p4.setLayout(new GridLayout(3, 0, 0, 10));
		
		p5 = new JPanel(new GridLayout(3, 0, 0, 10));
		p1.add(p5, BorderLayout.CENTER);
		
		String[] ns = new String[] {"실 가능 탑승인원", "적재 가능 공간 가방", "출시일"};
		String[] ps = new String[] {"탑승 가능한 최대 인원을 입력해주세요.", "적재가능한 가방의 최대 개수를 입력해주세요.", "등록할 차량의 출시일을 입력해주세요."};
		for (int i = 0; i < 3; i++) {
			var label = new JLabel(ns[i]);
			setFont(12, label);
			p4.add(label);
			
			ts[i] = new JTextField();
			placeholder(ts[i], ps[i]);
			p5.add(ts[i]);
		}
		
		cancel.addActionListener(this);
		regist.addActionListener(this);
	}
	JTextField[] ts = new JTextField[3];
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cancel)) {
			
		} else if (e.getSource().equals(regist)) {
			if (pictureRegist || textField.getText().equals("제목을 입력해주세요.") || ts[0].getText().equals("탑승 가능한 최대 인원을 입력해주세요.") || ts[1].getText().equals("적재가능한 가방의 최대 개수를 입력해주세요.") || ts[2].getText().equals("등록할 차량의 출시일을 입력해주세요.")) {
				fail("빈칸이 존재합니다.");
			} else if (!Pattern.matches("[0-9]*", ts[1].getText()) || !Pattern.matches("[0-9]*", ts[2].getText())) {
				fail("인원 수와 공간은 숫자로 입력해주세요.");
				ts[0].setText("");
				ts[1].setText("");
				ts[0].requestFocusInWindow();
			} else {
				ok("등록이 완료되었습니다.");
				DB.update("insert into car (c_name, t_no, f_no, c_people, c_release, c_space, c_img) values (?,?,?,?,?,?,?)", textField.getText(), c1.getSelectedIndex() + 1, c2.getSelectedIndex() + 1, ts[0].getText(), ts[2].getText(), ts[1].getText(), getByte(String.valueOf(path)));
			}
		}
	}

	public byte[] getByte(String filePath){
		byte[] byteFile = null;
		try {
			byteFile = Files.readAllBytes(new File(filePath).toPath());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return byteFile;
	}
}
