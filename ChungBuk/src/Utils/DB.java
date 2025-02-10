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
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar?serverTimeZone=UTC", "root", "1234");
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
			// TODO: handle exception
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
			// TODO: handle exception
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
			// TODO: handle exception
		}
		return save;
	}
	
	public static List<String[]> getMyPage() {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT re_no, u_no, c.c_name, re.r_name, rt.rt_name, r.re_date, r.re_time, r.re_redate, r.re_retime  FROM reservation r\r\n"
				+ "join car c on c.c_no = r.c_no\r\n"
				+ "join rental re on re.r_no = r.r_no\r\n"
				+ "join returnpoint rt on rt.rt_no = r.rt_no\r\n"
				+ "where r.u_no = ?\r\n"
				+ "";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, Login.no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
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
	
	public static List<Object[]> getCar() {
		List<Object[]> list = new ArrayList<>();
		String q = "SELECT c_img, c_release, c_name, c_people, c_space, m.m_join, m.m_normal, m.m_super, n.n_join, n.n_normal, n.n_super, t.t_name, f.f_name FROM rentcar.car r\r\n"
				+ "join fuel f on f.f_no = r.f_no\r\n"
				+ "join member m on m.c_no = r.c_no\r\n"
				+ "join nomember n on n.c_no = r.c_no\r\n"
				+ "join type t on t.t_no = r.t_no";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new Object[] {rs.getBytes(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String[] reserCheck(String name, String reno) {
		String[] list = new String[] {};
		String q = "SELECT count(*), c.c_name, re_date, re_redate FROM reservation r\r\n"
				+ "join car c on c.c_no = r.c_no\r\n"
				+ "join user u on r.u_no = u.u_no\r\n"
				+ "where u.u_name = ? and re_no = ?;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, name);
			pstm.setString(2, reno);
			var rs = pstm.executeQuery();
			if (rs.next()) { 
				list = new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getRank(int fno) {
		List<String[]> list = new ArrayList<>();
		String q = "select c.c_name, count(c.c_name) from reservation r\r\n"
				+ "join car c on c.c_no = r.c_no \r\n"
				+ "join fuel f on f.f_no = c.f_no\r\n"
				+ "where f.f_no = ?\r\n"
				+ "group by c.c_name\r\n"
				+ "order by count(c.c_name) desc\r\n"
				+ "limit 5;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setInt(1, fno);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<Object[]> getAdminCar() {
		var list = new ArrayList<Object[]>();
		String q = "SELECT c_img, c_name FROM rentcar.car;";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new Object[] {rs.getBytes(1), rs.getString(2)});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public static List<String[]> getReservation() {
		var list = new ArrayList<String[]>();
		String q = "SELECT re_no, u_no, c.c_name, re.r_name, rt.rt_name, re_date, re_time, re_redate, re_retime FROM rentcar.reservation r\r\n"
				+ "join car c on c.c_no = r.c_no\r\n"
				+ "join returnpoint rt on rt.rt_no = r.rt_no\r\n"
				+ "join rental re on re.r_no = r.r_no\r\n"
				+ "order by re_no asc;";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
