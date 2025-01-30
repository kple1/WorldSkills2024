package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB {
	static Statement st;
	static Connection c;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeeshop?serverTimeZone=UTC", "root", "1234");
			st = c.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace(); 
		}
	}
	
	public static boolean isTrue(String q, Object... o) {
		boolean save = false;
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < o.length; i++) {
				pstm.setObject(i + 1, o[i]);
			}
			var rs = pstm.executeQuery();
			if (rs.next()) {
				save = rs.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static String getString(String q, Object... o) {
		String save = "";
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < o.length; i++) {
				pstm.setObject(i + 1, o[i]);
			}
			var rs = pstm.executeQuery();
			if (rs.next()) {
				save = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static int getInt(String q, Object... o) {
		int save = 0;
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < o.length; i++) {
				pstm.setObject(i + 1, o[i]);
			}
			var rs = pstm.executeQuery();
			if (rs.next()) {
				save = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String[]> getMenu() {
		List<String[]> list = new ArrayList<>();
		String q = "select * from menu";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void update(String q, Object... o) {
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < o.length; i++) {
				pstm.setObject(i + 1, o[i]);
			}
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String[]> getTodayOrderInfo() {
		var list = new ArrayList<String[]>();
		String q = "select no, m.name, m.price from orderinfor o \r\n"
				+ "join menu m on o.mno = m.mno \r\n"
				+ "where date = DATE(now())";
		try {
			var pstm = c.prepareStatement(q);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getTop5() {
		List<String[]> list = new ArrayList<>();
		String q = "select m.name, sum(quantity) from orderinfor o\r\n"
				+ "join menu m on m.mno = o.mno\r\n"
				+ "where date = '2024-08-27'\r\n"
				+ "group by o.mno\r\n"
				+ "order by sum(quantity) desc\r\n"
				+ "limit 5;";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<Integer> rank() {
		List<Integer> list = new ArrayList<>();
		String q = "select sum(quantity) from orderinfor o\r\n"
				+ "join menu m on m.mno = o.mno\r\n"
				+ "join category c on c.cno = m.cno\r\n"
				+ "where date between '2024-08-18' and '2024-08-24'\r\n"
				+ "group by c.cno\r\n"
				+ "order by c.cno asc;";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
}
