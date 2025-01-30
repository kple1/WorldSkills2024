package ChildPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Utils.DB;
import Utils.ImageSize;

public class OrderInformationModel extends JPanel {

	public OrderInformationModel(String ono) {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);
		
		String no = DB.getString("select m.mno from orderinfor o \r\n"
				+ "join menu m on o.mno = m.mno\r\n"
				+ "where no = ?", ono);
		String quantity = DB.getString("select quantity from orderinfor where no = ?", ono);
		String name = DB.getString("select name from menu where mno = ?", no);
		String price = String.format("\\ %,d", DB.getInt("select price from orderinfor where no = ?", ono));
		String[] options = DB.getString("select options from orderinfor where no = ?", ono).split(",");
		String[] oq = DB.getString("select optionqty from orderinfor where no = ?", ono).split(",");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setPreferredSize(new Dimension(130, 130));
		add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel amount = new JLabel(quantity + "\uAC1C    ");
		amount.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		panel.add(amount, BorderLayout.EAST);
		
		JLabel image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setVerticalAlignment(SwingConstants.CENTER);
		image.setSize(100, 130);
		image.setIcon(ImageSize.set(new ImageIcon("datafiles/menuIMG/" + no + ".png"), image));
		panel.add(image, BorderLayout.CENTER);
		
		JPanel info = new JPanel();
		info.setBackground(Color.white);
		add(info, BorderLayout.CENTER);
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		
		info.add(new Model(name, price));
		
		for (int i = 0; i < options.length; i++) {
			String oName = DB.getString("select name from menu where opno = ?", options[i]);
			int oPrice = DB.getInt("select price from menu where opno = ?", options[i]);
			String filter = String.format("\\ %,d", oPrice * Integer.parseInt(oq[i])) + " (" + oq[i] + ")";
			if (oq[i].equals("0")) continue;
			info.add(new Model(oName, filter));
		}
	}

}

class Model extends JPanel {
	Model(String o1, String o2){
		setLayout(new BorderLayout());
		setBackground(Color.white);
		setPreferredSize(new Dimension(200, 30));
		
		var name = new JLabel(o1);
		name.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		add(name, BorderLayout.WEST);
		
		var price = new JLabel(o2);
		price.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		add(price, BorderLayout.EAST);
	}
}