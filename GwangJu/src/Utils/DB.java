package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Form.Login;

public class DB {
	static Connection c;
	static Statement st;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodfind?serverTimeZone=UTC", "root", "1234");
			st = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String t = getString("select u_following from user where id = ?", "user03");
		if (t.isEmpty()) {
			System.out.print("yeah");	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static String getString(String q, Object... o) {
		String save = "null";
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < o.length; i++) {
				pstm.setObject(i + 1, o[i]);
			}
			var rs = pstm.executeQuery();
			if (rs.next()) {
				save = rs.getString(1);
			}
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<Integer[]> getCoordinate() {
		var list = new ArrayList<Integer[]>();
		String q = "select x, y from restaurant";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new Integer[] {rs.getInt(1), rs.getInt(2)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<Integer> getTino(String r_no) {
		var list = new ArrayList<Integer>();
		String q = "select ti_no from timeline where r_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, r_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<String[]> getResInfo() {
		List<String[]> list = new ArrayList<>();
		String q = "select * from restaurant";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static Object[] getTimeLine(String uno) {
		Object[] list = new Object[] {};
		String q = "select u_name, DATEDIFF(now(), ti_date), ti_review, r.img1, r.img2, r_name, ti_star from timeline t\r\n"
				+ "join user u on t.u_no = u.u_no\r\n"
				+ "join restaurant r on r.r_no = t.r_no\r\n"
				+ "where t.u_no = ?\r\n"
				+ "limit 1";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, uno);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list = new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getBytes(4), rs.getBytes(5), rs.getString(6)};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getRecommendFollow() {
		var list = new ArrayList<String[]>();
		String q = "select u.u_name, count(*) from timeline t\r\n"
				+ "join user u on t.u_no = u.u_no\r\n"
				+ "group by t.u_no\r\n"
				+ "order by count(*) desc\r\n"
				+ "limit 6";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void update(String q, Object... o) {
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0 ; i < o.length; i++) {
				pstm.setObject(i + 1, o[i]);
			}
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Integer> getMyReview() {
		String q = "SELECT r_no FROM foodfind.timeline where u_no = ?;";
		List<Integer> save = new ArrayList<>();
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, Login.no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				save.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<String> getAlim() {
		var list = new ArrayList<String>();
		String q = "SELECT a_follow FROM foodfind.alim where u_no = ? order by a_followdate desc;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, Login.no);
			var rs = pstm.executeQuery();
			
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getFollowList() {
		var list = new ArrayList<String[]>();
		String q = "SELECT u_name, id, u_following FROM foodfind.user;";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				List<String> containList = new ArrayList<>();
				String[] splitList = rs.getString(3).split(",");
				for (int i = 0; i < splitList.length; i++) {
					containList.add(splitList[i]);
				}
				if (containList.contains(Login.no)) {
					list.add(new String[] {rs.getString(1), rs.getString(2)});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getChat(String c_give, String c_take) {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT * FROM foodfind.chat where c_give = ? and c_take = ?\r\n"
				+ "union all\r\n"
				+ "SELECT * FROM foodfind.chat where c_give = ? and c_take = ?\r\n"
				+ "order by c_no";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, c_give);
			pstm.setString(2, c_take);
			pstm.setString(3, c_take);
			pstm.setString(4, c_give);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<String[]> getResComment(String r_no) {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT u_name, ti_review FROM foodfind.timeline t\r\n"
				+ "join user u on t.u_no = u.u_no\r\n"
				+ "where r_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, r_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static byte[][] getImage(String r_no) {
		byte[][] list = new byte[][] {};
		String q = "select img1, img2 from restaurant where r_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, r_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list = new byte[][] {rs.getBytes(1), rs.getBytes(2)};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
  	}
}
