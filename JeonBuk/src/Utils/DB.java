package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
	static Connection c;
	static Statement st;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/academic?serverTimeZone=UTC", "root", "1234");
			st = c.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isTrue(String q, Object...objects) {
		boolean save = false;
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < objects.length; i++) {
				pstm.setObject(i + 1, objects[i]);
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
	
	public static String[] getId(String id) {
		String[] s = new String[] {};
		String q = "SELECT id, 'student' AS source_table FROM student WHERE id = ?\r\n"
				+ "UNION ALL\r\n"
				+ "SELECT  id, 'professor' AS source_table FROM professor WHERE id = ?;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, id);
			pstm.setString(2, id);
			var rs = pstm.executeQuery();
			if (rs.next()) {
				s = new String[] {rs.getRow() + "", rs.getString(1), rs.getString(2)};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.asList(getId("pro01")));
	}
	
	public static String getString(String q, Object... objects) {
		String save = "";
		try {
			var pstm = c.prepareStatement(q);
			for (int i = 0; i < objects.length; i++) {
				pstm.setObject(i + 1, objects[i]);
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
	
	public static List<String[]> getTakeCourse(String s_no) {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT d.d_name, p.name, p.phone, score FROM academic.enrolment e\r\n"
				+ "join student s on s.s_no = e.s_no\r\n"
				+ "join professor p on p.p_no = e.p_no\r\n"
				+ "join division d on s.d_no = d.d_no\r\n"
				+ "where e.s_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, s_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getCheckCourse() {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT p.subject, p.name, count(e.p_no) from enrolment e\r\n"
				+ "join professor p on p.p_no = e.p_no\r\n"
				+ "join student s on s.s_no = e.s_no\r\n"
				+ "group by p.p_no";
		try {
			var rs = st.executeQuery(q);
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String> nowCourse(String s_no) {
		List<String> list = new ArrayList<>();
		String q = "SELECT p_no FROM academic.enrolment where s_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, s_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<String[]> getMyCourse(String s_no) {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT p.subject, grades, score FROM academic.enrolment e\r\n"
				+ "join professor p on p.p_no = e.p_no \r\n"
				+ "join student s on s.s_no = e.s_no\r\n"
				+ "where e.s_no = ?;";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, s_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3)});
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
	
	public static List<String[]> getStudentManage(String p_no) {
		List<String[]> list = new ArrayList<>();
		String q = "SELECT adminssion, name, d_name, score from enrolment e \r\n"
				+ "join student s on s.s_no = e.s_no \r\n"
				+ "join division d on d.d_no = s.d_no\r\n"
				+ "where p_no = ? order by e.s_no";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, p_no);
			var rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String[] getStudentInfo (String s_no, String p_no) {
		String[] list = new String[] {};
		String q = "SELECT subject, adminssion, s.name, score, remarks FROM enrolment e\r\n"
				+ "join professor p on p.p_no = e.p_no\r\n"
				+ "join student s on s.s_no = e.s_no\r\n"
				+ "where e.s_no = ? and p.p_no = ?";
		try {
			var pstm = c.prepareStatement(q);
			pstm.setString(1, s_no);
			pstm.setString(2, p_no);
			var rs = pstm.executeQuery();
			if (rs.next()) {
				list = new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
