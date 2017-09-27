package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;

	public void getConnection() {
		// 2¡¢¼ÓÔØÇý¶¯
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
							"root", "123456");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getStatement() {

		getConnection();
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getPreparedStatement(String sql) {

		getConnection();
		try {
			pstat = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeAll() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (pstat != null) {
				pstat.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
