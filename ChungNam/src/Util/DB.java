package Util;

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
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline?serverTimeZone=UTC", "root", "1234");
			st = c.createStatement();
		} catch (Exception e) {
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
		} catch (Exception e) {
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
	
	public static List<String> getContry(int conno) {
		List<String> list = new ArrayList<>();
		String q = "SELECT countryname FROM airline.country where conno = ?;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setInt(1, conno);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getTraval(int cno) {
		List<String> list = new ArrayList<>();
		String q = "SELECT tname FROM airline.travel where cno = ?;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setInt(1, cno);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getSeat(int s_no) {
		List<String> list = new ArrayList<>();
		String q = "SELECT seat FROM reservation where sno = ?;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setInt(1, s_no);
			var rs = pstm.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> getDepartureTime(String tno) {
		List<String> list = new ArrayList<>();
		String q = "SELECT departureTime FROM airline.schedule where tno = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, tno);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
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
	
	public static List<String[]> getMyPage() {
		List<String[]> list = new ArrayList<>();
		String q = "select rno, bno, r.sno, departureDate, tname, \r\n"
				+ "case\r\n"
				+ " when division = 1 then '성인'\r\n"
				+ " when division = 2 then '어린이' \r\n"
				+ " else '유아' end division, seat, amount from reservation r\r\n"
				+ "join schedule s on s.sno = r.sno\r\n"
				+ "join travel t on t.tno = s.tno\r\n"
				+ "where uno = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, Login.no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> data() {
		List<String[]> data = new ArrayList<>();
		String q = "SELECT concat(id, '(', kname, ')') as name, count(r.uno) FROM reservation r\r\n"
				+ "join user u on u.uno = r.uno\r\n"
				+ "group by r.uno\r\n"
				+ "order by count(r.uno) desc\r\n"
				+ "limit 10;";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				data.add(new String[] {rs.getString(1), rs.getString(2)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
