package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TravelGuide extends Frame {
	public JPanel panel;
	public Component horizontalStrut;
	public Component horizontalStrut_1;
	public Component verticalStrut;
	public Component verticalStrut_1;
	public JPanel panel_1;
	public JLabel title;
	public JPanel grid;
	public JPanel countryPanel;
	public JPanel travelPanel;
	public JLabel lblNewLabel;
	public JLabel lblNewLabel_1;
	public JLabel img1;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	public JLabel img2;
	public JPanel center;
	public Component verticalStrut_2;
	public Component verticalStrut_3;
	public JPanel panel_2;
	public JLabel explain;
	public JPanel panel_3;
	public JPanel panel_4;
	public JLabel minuteLabel;
	public JTextField minute;
	public JLabel mileateLabel;
	public JTextField mileate;
	public JLabel explantationLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new TravelGuide("datafiles/CountryImage/1.jpg", "datafiles/TravelImage/1.jpg", "³²¾ÆÇÁ¸®Ä«°øÈ­±¹", "¿äÇÏ³×½º¹ö±×", "", "", "À¯¸íÇÏ±ä ÇØ", "MyPage").setVisible(true);
	}

	boolean formClose = true;
	public void windowClosed(WindowEvent e) {
		if (formClose && formName.equals("MyPage")) new MyPage().setVisible(true);
	}
	String formName;
	public TravelGuide(String im1, String im2, String n1, String n2, String t1, String t2, String exp, String formName) {
		this.formName = formName;
		setFrame("¿©Çà °¡ÀÌµå", 800, 350);
		setSize(900, 350);
		addWindowListener(this);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 0));
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.WEST);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1, BorderLayout.EAST);
		
		verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut, BorderLayout.SOUTH);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		title = new JLabel("\uC5EC\uD589\uAC00\uC774\uB4DC");
		title.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		panel_1.add(title, BorderLayout.NORTH);
		
		grid = new JPanel();
		panel_1.add(grid, BorderLayout.CENTER);
		grid.setLayout(new GridLayout(1, 0, 0, 0));
		
		countryPanel = new JPanel();
		grid.add(countryPanel);
		countryPanel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("\uB098\uB77C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		countryPanel.add(lblNewLabel, BorderLayout.NORTH);
		
		lblNewLabel_1 = new JLabel("<\uB0A8\uC544\uD504\uB77C\uCE74\uACF5\uD654\uAD6D>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		countryPanel.add(lblNewLabel_1, BorderLayout.SOUTH);
		
		img1 = new JLabel("");
		img1.setHorizontalAlignment(SwingConstants.CENTER);
		img1.setSize(200, 100);
		img1.setIcon(imageSize(new ImageIcon(im1), img1));
		countryPanel.add(img1, BorderLayout.CENTER);
		
		travelPanel = new JPanel();
		grid.add(travelPanel);
		travelPanel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_2 = new JLabel("\uC5EC\uD589\uC9C0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		travelPanel.add(lblNewLabel_2, BorderLayout.NORTH);
		
		lblNewLabel_3 = new JLabel("<\uC694\uD558\uB124\uC2A4\uBC84\uADF8>");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		travelPanel.add(lblNewLabel_3, BorderLayout.SOUTH);
		
		img2 = new JLabel("");
		img2.setHorizontalAlignment(SwingConstants.CENTER);
		img2.setSize(200, 100);
		img2.setIcon(imageSize(new ImageIcon(im2), img2));
		travelPanel.add(img2, BorderLayout.CENTER);
		
		center = new JPanel();
		getContentPane().add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));
		
		verticalStrut_2 = Box.createVerticalStrut(100);
		center.add(verticalStrut_2, BorderLayout.NORTH);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		center.add(verticalStrut_3, BorderLayout.SOUTH);
		
		panel_2 = new JPanel();
		center.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		explain = new JLabel(exp);
		explain.setVerticalAlignment(SwingConstants.NORTH);
		explain.setBorder(BorderFactory.createLineBorder(Color.black));
		explain.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		explain.setEnabled(false);
		explain.setPreferredSize(new Dimension(0, 90));
		panel_2.add(explain, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(250, 0));
		panel_3.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(3, 2, 0, 10));
		
		minuteLabel = new JLabel("Minute");
		minuteLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_4.add(minuteLabel);
		
		minute = new JTextField(t1);
		minute.setEnabled(false);
		minute.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_4.add(minute);
		minute.setColumns(10);
		
		mileateLabel = new JLabel("Mileate");
		mileateLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_4.add(mileateLabel);
		
		mileate = new JTextField(t2);
		mileate.setEnabled(false);
		mileate.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_4.add(mileate);
		mileate.setColumns(10);
		
		explantationLabel = new JLabel("Explantation");
		explantationLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		panel_4.add(explantationLabel);
	}

}
