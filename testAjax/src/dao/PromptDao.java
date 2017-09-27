package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PromptDao {

	public List<String> search(String mes) {
		List<String> list = new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testindex", "root", "123456");
			Statement stat = conn.createStatement();
			String sql = "select * from message where content like '"+mes+"%'";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				
				list.add(rs.getString("content"));
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
