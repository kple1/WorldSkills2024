package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import Form.Login;
import Model.Panel;
import Utils.CellRenderer;
import Utils.DB;

public class Write extends Panel {
	private JTextField textField;
	private JTable table;
	private JTextField rating;
	boolean windowState = false; // false = empty true = showInformation

	public Write() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 200));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut, BorderLayout.SOUTH);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					reload();
				}
			}
		});
		placeholder(textField, "음식점을 검색하세요.");
		panel_1.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		JLabel ratingLabel = new JLabel();
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				var r = DB.getMyReview();
				var getClickedRow = table.getSelectedRow() + 1;
				if (r.contains(getClickedRow)) {
					int getRating = DB.getInt(
							"SELECT convert(ti_star, SIGNED) FROM foodfind.timeline where u_no = ? and r_no = ?",
							Login.no, getClickedRow);
					rating.setVisible(false);
					ratingLabel.setVisible(true);

					String star = "★".repeat(getRating) + "☆".repeat(5 - getRating);
					ratingLabel.setText(star);
					ratingLabel.setForeground(Color.red);
					setPlain(ratingLabel, 20);
					topView.add(ratingLabel);
					topView.revalidate();
					topView.repaint();

					add.setIcon(imageSize(new ImageIcon("datafiles/icon/write.png"), add));

					wr1.getImage().setIcon(imageSize(new ImageIcon("datafiles/review/tino" + getClickedRow + "-1.jpg"),
							wr1.getImage()));
					wr2.getImage().setIcon(imageSize(new ImageIcon("datafiles/review/tino" + getClickedRow + "-2.jpg"),
							wr2.getImage()));
					wr1.getImage().setText("");
					wr2.getImage().setText("");

					ta.setText(DB.getString("select ti_review from timeline where r_no = ?", getClickedRow));
					ta.setForeground(Color.black);
					windowState = true;
				} else {
					placeholder(ta, "문구를 입력하세요.");
					rating.setVisible(true);
					ratingLabel.setVisible(false);

					wr1.getImage().setIcon(null);
					wr2.getImage().setIcon(null);
					wr1.getImage().setText("+");
					wr2.getImage().setText("+");

					add.setIcon(imageSize(new ImageIcon("datafiles/icon/add.png"), add));
					windowState = false;
				}
			}
		});
		model = new DefaultTableModel(new Object[][] {}, new String[] { "번호", "음식점 이름" });
		table.setModel(model);

		reload();

		scrollPane.setViewportView(table);

		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(500, 300));
		add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		bottom.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		bottom.add(horizontalStrut_3, BorderLayout.EAST);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		bottom.add(verticalStrut_2, BorderLayout.NORTH);

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		bottom.add(verticalStrut_3, BorderLayout.SOUTH);

		JPanel view = new JPanel();
		bottom.add(view, BorderLayout.CENTER);
		view.setLayout(new BorderLayout(0, 0));

		topView = new JPanel(new FlowLayout(FlowLayout.LEFT));
		view.add(topView, BorderLayout.NORTH);

		JLabel startLabel = new JLabel("\uBCC4\uC810");
		startLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		topView.add(startLabel);

		rating = new JTextField();
		((AbstractDocument) rating.getDocument()).setDocumentFilter(new DocumentFilter() {

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (fb.getDocument().getLength() - length + text.length() <= 3) {
					super.replace(fb, offset, length, text, attrs);
				}
			}

			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (fb.getDocument().getLength() + string.length() <= 3) {
					super.insertString(fb, offset, string, attr);
				}
			}
		});
		topView.add(rating);
		rating.setColumns(10);

		JPanel bottomView = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		view.add(bottomView, BorderLayout.SOUTH);

		add = new JLabel("");
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (windowState) {
					
				} else {
					if (ta.getText().isEmpty() || ta.getText().equals("문구를 입력하세요.") || !WriteModel.success || rating.getText().isEmpty()) {
						fail("빈칸이 존재합니다.");
					} else if (table.getSelectedRow() == -1) {
						fail("타임라인을 쓸 음식점을 선택해 주세요.");
					} else if (ta.getText().length() <= 10 || ta.getText().length() >= 60) {
						fail("타임라인은 10자 이상 60자 이내로 써주세요.");
					} else {
						ok("타임라인 작성이 완료되었습니다.");
						int r_no = table.getSelectedRow() + 1;
						String review = ta.getText();
						String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						String uno = Login.no;
						String star = rating.getText();
						DB.update(
								"insert into timeline (r_no, ti_review, ti_date, u_no, ti_star) values (?, ?, ?, ?, ?)",
								r_no, review, date, uno, star);
						try {
							Files.copy(wr1.sp, wr1.tp, StandardCopyOption.COPY_ATTRIBUTES);
							Files.copy(wr2.sp, wr2.tp, StandardCopyOption.COPY_ATTRIBUTES);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		setSize(add, 30, 30);
		add.setSize(30, 30);
		add.setIcon(imageSize(new ImageIcon("datafiles/icon/add.png"), add));
		bottomView.add(add);

		delete = new JLabel("");
		delete.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (windowState) {
					ok("타임라인이 삭제되었습니다.");
					placeholder(ta, "문구를 입력하세요.");
					rating.setVisible(true);
					ratingLabel.setVisible(false);

					wr1.getImage().setIcon(null);
					wr2.getImage().setIcon(null);
					wr1.getImage().setText("+");
					wr2.getImage().setText("+");

					add.setIcon(imageSize(new ImageIcon("datafiles/icon/add.png"), add));
					windowState = false;
					
					//데이터 처리
					DB.update("delete from timeline where r_no = ? and u_no = ?", table.getSelectedRow() + 1, Login.no);
					
					try { 
						Path p1 = Paths.get(String.format("datafiles/review/tino%s-1.jpg", table.getSelectedRow() + 1));
						Path p2 = Paths.get(String.format("datafiles/review/tino%s-2.jpg", table.getSelectedRow() + 1));
						Files.delete(p1);
						Files.delete(p2);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					reload();
				} else {
					placeholder(ta, "문구를 입력하세요.");
					rating.setVisible(true);
					ratingLabel.setVisible(false);

					wr1.getImage().setIcon(null);
					wr2.getImage().setIcon(null);
					wr1.getImage().setText("+");
					wr2.getImage().setText("+");

					add.setIcon(imageSize(new ImageIcon("datafiles/icon/add.png"), add));
					windowState = false;
				}
			}
		});
		setSize(add, 30, 30);
		delete.setSize(30, 30);
		delete.setIcon(imageSize(new ImageIcon("datafiles/icon/delete.png"), delete));
		bottomView.add(delete);

		JPanel mainView = new JPanel();
		view.add(mainView, BorderLayout.CENTER);
		mainView.setLayout(new GridLayout(1, 2, 10, 0));

		mainView.add(wr1);
		mainView.add(wr2);

		JPanel center = new JPanel();
		add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));

		JPanel line1 = new JPanel();
		setSize(line1, 0, 1);
		line1.setBackground(new Color(0, 0, 0));
		center.add(line1, BorderLayout.NORTH);
		line1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel line2 = new JPanel();
		setSize(line2, 0, 1);
		line2.setBackground(Color.BLACK);
		center.add(line2, BorderLayout.SOUTH);
		line2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ta = new JTextArea();
		placeholder(ta, "문구를 입력하세요.");
		center.add(ta, BorderLayout.CENTER);
	}

	JTextArea ta;
	DefaultTableModel model;
	HashSet<Integer> contains;
	JLabel add, delete;
	JPanel topView;
	WriteModel wr1 = new WriteModel();
	WriteModel wr2 = new WriteModel();

	void reload() {
		model.setRowCount(0);
		contains = new HashSet<>();
		var list = DB.getResInfo();
		var r = DB.getMyReview();
		for (int i = 0; i < list.size(); i++) {
			if (r.contains(i + 1)) {
				contains.add(i);
			}
			if (list.get(i)[1].toString().contains(textField.getText()) || textField.getText().isEmpty() || textField.getText().equals("음식점을 검색하세요.")) {
				model.addRow(new Object[] { i + 1, list.get(i)[1] });
			}
		}
		if (model.getRowCount() == 0) {
			fail("해당 내용의 음식점은 존재하지 않습니다.");
		}
		
		var ren = new CellRenderer(contains);
		table.getColumnModel().getColumn(0).setCellRenderer(ren);
		table.getColumnModel().getColumn(1).setCellRenderer(ren);
	}
}

class WriteModel extends Panel {
	public static boolean leftAlign = false;
	public static boolean success = false;
	JLabel add;
	public Path sp;
	public Path tp;

	WriteModel() {
		setLayout(new BorderLayout());
		setSize(this, 200, 200);
		setBorder(this, Color.black);

		add = new JLabel("+");
		add.setSize(200, 200);
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				var file = new JFileChooser();
				int returnValue = file.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					add.setText("");
					File sel = file.getSelectedFile(); // 절대 경로
					add.setIcon(imageSize(new ImageIcon(sel.toString()), add));

					Path targetDir = Paths.get("datafiles/review/");
					Path sourcePath = file.getSelectedFile().toPath();

					String indexPlusOne = DB.getString("select ti_no + 1 from timeline order by ti_no desc limit 1");
					String numberState = leftAlign ? "2" : "1";
					Path targetPath = targetDir.resolve("tino" + indexPlusOne + "-" + numberState + ".jpg"); // 파일 이름 변경 가능

					sp = sourcePath;
					tp = targetPath;

					if (leftAlign) {
						leftAlign = false;
						success = true;
					} else {
						leftAlign = true;
					}
				}
			}
		});
		add.setHorizontalAlignment(SwingConstants.CENTER);
		setBold(add, 30);
		add(add);
	}

	JLabel getImage() {
		return add;
	}
}
