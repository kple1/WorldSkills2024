package Form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Frame;
import Model.Panel;
import Utils.DB;

public class ScoreCalculate extends Frame{


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new ScoreCalculate().setVisible(true);
	}

	/**
	 * Create the application.
	 */
	int avg;boolean state = true;
	public ScoreCalculate() {
		setFrame("학점 계산하기", 350, 400);
		addWindowListener(this);
		Component h1 = Box.createHorizontalStrut(20);
		getContentPane().add(h1, BorderLayout.WEST);
		
		Component h2 = Box.createHorizontalStrut(20);
		getContentPane().add(h2, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton cal = new JButton("\uACC4\uC0B0\uD558\uAE30");
		cal.addActionListener(e -> {
			state = false;
			dispose();
			new ScoreInfo(avg).setVisible(true);
		});
		panel.add(cal);
		
		JPanel top = new JPanel();
		top.setPreferredSize(new Dimension(0, 50));
		getContentPane().add(top, BorderLayout.NORTH);
		top.setLayout(new BorderLayout(0, 0));
		
		JLabel title = new JLabel("\uD559\uC810 \uACC4\uC0B0\uD558\uAE30");
		title.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(title, BorderLayout.NORTH);
		
		JPanel line = new JPanel();
		line.setPreferredSize(new Dimension(0, 1));
		line.setBackground(new Color(0, 0, 0));
		top.add(line, BorderLayout.SOUTH);
		
		JPanel view = new JPanel();
		getContentPane().add(view, BorderLayout.CENTER);
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		
		var list = DB.getMyCourse(Login.no);
		for (int i = 0; i < list.size(); i++) {
			view.add(new ScoreModel(list.get(i)[0], list.get(i)[1], list.get(i)[2]));
			if (list.get(i)[2].equals("A")) {
				avg += 4;
			} else if (list.get(i)[2].equals("B")) {
				avg += 3;
			} else {
				avg += 2;
			}
		}
		avg /= list.size();
	}
	
	public void windowClosed(WindowEvent e) {
		if (state) new CourseCheck().setVisible(true);
	}
}

class ScoreModel extends Panel {
	ScoreModel(String object, String score, String rating) {
		setLayout(new BorderLayout());
		setMSize(350, 50, this);
		
		var o = new JLabel(object);
		var a = new JLabel("-");
		var s = new JLabel(score + "점");
		var r = new JLabel(rating);
		var f = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		font(16, o, a, s);
		font(20, r);
		
		a.setHorizontalAlignment(SwingConstants.CENTER);
		r.setHorizontalAlignment(SwingConstants.CENTER);
		r.setOpaque(true);
		if (rating.equals("A")) {
			r.setBackground(Color.red);
		} else if (rating.equals("B")) {
			r.setBackground(Color.blue);
		} else {
			r.setBackground(Color.yellow);
		}
		
		setSize(30, 30, r);
		
		f.add(s);
		f.add(r);
		
		add(o, BorderLayout.WEST);
		add(a, BorderLayout.CENTER);
		add(f, BorderLayout.EAST);
		
	}
}
