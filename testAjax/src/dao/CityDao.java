package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Sheng;
import entity.Shi;

public class CityDao {

	public List<Sheng> searchSheng() {
		List<Sheng> list = new ArrayList<Sheng>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testindex", "root", "123456");
			Statement stat = conn.createStatement();
			String sql = "select * from sheng ";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
			Sheng sheng = new Sheng();
			sheng.setId(rs.getInt("id"));
			sheng.setName(rs.getString("sheng"));
			list.add(sheng);
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
	
	public List<Shi> searchShiBySheng(int sId) {
		List<Shi> list = new ArrayList<Shi>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/testindex", "root", "123456");
			Statement stat = conn.createStatement();
			String sql = "select * from shi where s_id = " +sId;
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				Shi shi = new Shi();
				shi.setId(rs.getInt("id"));;
				shi.setName(rs.getString("shi"));	
				list.add(shi);
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
