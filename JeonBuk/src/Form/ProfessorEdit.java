package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.Frame;
import Utils.DB;

public class ProfessorEdit extends Frame {
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public JPanel panel;
	public JPanel west;
	public JPanel view;
	public JButton save;
	public JPanel w1;
	public JPanel e1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ProfessorEdit().setVisible(true);
	}

	/**
	 * Create the application.
	 */boolean state = true;
	Path sourcePath, targetPath;
	public ProfessorEdit() {
		setFrame("���� ��������", 600, 300);
		addWindowListener(this);
		horizontalStrut = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
		
		verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut, BorderLayout.SOUTH);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1, BorderLayout.NORTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		west = new JPanel();
		panel.add(west, BorderLayout.WEST);
		west.setLayout(new BorderLayout(0, 0));
		
		var img = new JLabel();
		setBorder(Color.black, img);
		img.setSize(150, 300);
		img.setIcon(imageSize(new ImageIcon("datafiles/professor/" + Login.no + ".png"), img));
		west.add(img, BorderLayout.CENTER);
		
		var loadImg = new JButton("���� �ҷ�����");
		loadImg.addActionListener(e-> {
			JFileChooser file = new JFileChooser();
			int returnValue = file.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File sel = file.getSelectedFile();
				img.setIcon(imageSize(new ImageIcon(sel.toString()), img));
			}

			Path targetDir = Paths.get("datafiles/professor/");
			sourcePath = file.getSelectedFile().toPath();
			targetPath = targetDir.resolve(Login.no + ".png");
		});
		west.add(loadImg, BorderLayout.SOUTH);
		
		view = new JPanel();
		panel.add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));
		
		save = new JButton("\uC800\uC7A5");
		save.addActionListener(e -> {
			if (!Pattern.matches("[��-�R]*", text[0].getText())) {
				fail("�̸��� �ѱ۷� ���ּ���.");
			} else if (!Pattern.matches("010-\\d{4}-\\d{4}", text[1].getText())) {
				fail("��ȭ��ȣ�� Ȯ�����ּ���.");
			} else if (!text[2].getText().equals(DB.getString("select pw from professor where p_no = ?", Login.no)))  {
				fail("���� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			} else {
				ok("������ �Ϸ�Ǿ����ϴ�.");
				DB.update("update professor set pw = ? where p_no = ?", text[3].getText(), Login.no);
				
				try {
					Files.delete(targetPath);
					Files.copy(sourcePath, targetPath, StandardCopyOption.COPY_ATTRIBUTES);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		view.add(save, BorderLayout.SOUTH);
		
		w1 = new JPanel(new GridLayout(4, 1, 0, 10));
		String[] sl = new String[] {"�̸�", "��ȭ��ȣ", "���� ��й�ȣ", "������ ��й�ȣ"};
		for (int i = 0; i < 4; i++) {
			var label = new JLabel(sl[i]);
			font(16, label);
			w1.add(label);
		}
		view.add(w1, BorderLayout.WEST);
		
		e1 = new JPanel(new GridLayout(4, 1, 0, 10));
		for (int i = 0; i < 4; i++) {
			text[i] = new JTextField();
			e1.add(text[i]);
		}
		view.add(e1, BorderLayout.CENTER);
	}
	JTextField[] text = new JTextField[4];
	
	public void windowClosed(WindowEvent e) {
		if (state) new ProfessorMain().setVisible(true);
	}
}
