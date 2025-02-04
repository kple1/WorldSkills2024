package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;

import Model.Panel;
import Utils.DB;
import Model.FollowModel;

public class FollowList extends Panel {

	public FollowList() {
		setLayout(new BorderLayout(0, 0));

		Component verticalStrut = Box.createVerticalStrut(170);
		add(verticalStrut, BorderLayout.SOUTH);

		Component verticalStrut_1 = Box.createVerticalStrut(170);
		add(verticalStrut_1, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel message = new JPanel();
		panel.add(message, BorderLayout.NORTH);
		message.setLayout(new GridLayout(2, 0, 0, 0));

		JLabel t1 = new JLabel(
				"\uC544\uC9C1 \uD314\uB85C\uC6B0\uD558\uB294 \uC0AC\uC6A9\uC790\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4.");
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		message.add(t1);

		JLabel t2 = new JLabel("\uCD94\uCC9C \uC0AC\uC6A9\uC790\uB97C \uD314\uB85C\uC6B0\uD574\uBCF4\uC138\uC694!");
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		message.add(t2);

		setPlain(t1, 20);
		setPlain(t2, 20);

		t1.setForeground(Color.LIGHT_GRAY);
		t2.setForeground(Color.LIGHT_GRAY);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

		JViewport viewport = scrollPane.getViewport();

		MouseAdapter mouseAdapter = new MouseAdapter() {
		    private Point origin = new Point();
		    private final double DRAG_SPEED = 0.03;

		    @Override
		    public void mousePressed(MouseEvent e) {
		        origin = e.getPoint();
		    }

		    @Override
		    public void mouseDragged(MouseEvent e) {
		        Point currentPoint = e.getPoint();
		        Point viewPosition = viewport.getViewPosition();

		        int deltaX = (int) ((origin.x - currentPoint.x) * DRAG_SPEED);
		        int deltaY = (int) ((origin.y - currentPoint.y) * DRAG_SPEED);

		        viewPosition.translate(deltaX, deltaY);

		        viewPosition.x = Math.max(0, Math.min(viewPosition.x, viewport.getViewSize().width - viewport.getWidth()));
		        viewPosition.y = Math.max(0, Math.min(viewPosition.y, viewport.getViewSize().height - viewport.getHeight()));

		        viewport.setViewPosition(viewPosition);
		    }
		};

		viewport.addMouseListener(mouseAdapter);
		viewport.addMouseMotionListener(mouseAdapter);

		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel view = new JPanel(new GridLayout(1, 0, 15, 0));
		var list = DB.getRecommendFollow();
		for (int i = 0; i < 6; i++) {
			var p = new FollowModel(list.get(i)[0], list.get(i)[1]);
			setSize(p, 110, 130);
			view.add(p);
		}
		scrollPane.setViewportView(view);
	}

}
