package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Form.Login;

public class DB {
	
	public static Statement st;
	public static Connection c;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation?serverTimeZone=UTC", "root", "1234");
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
	
	public static List<String[]> getLocation() {
		List<String[]> save = new ArrayList<>();
		String q = "select * from location";
		try {
			var pstm = c.prepareStatement(q);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				save.add(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static List<Object[]> getTicket() {
		List<Object[]> save = new ArrayList<>();
		String q = "select l_no1, l_no2, rating, seat, u_no, date, time from ticket";
		try {
			var pstm = c.prepareStatement(q);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				save.add(new Object[] { rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getDate(6), rs.getTime(7) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public static void crud(String q, Object... o) {
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
	
	public static List<Object[]> getUserTicket() {
		List<Object[]> list = new ArrayList<>();
		String q = "SELECT date, l_no1, l_no2, time, seat FROM busreservation.ticket where u_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setInt(1, Login.no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new Object[] { rs.getDate(1), rs.getInt(2), rs.getInt(3), rs.getTime(4), rs.getInt(5) });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
