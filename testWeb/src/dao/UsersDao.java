package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class UsersDao {

	public User searchByUserNameAndPassword(User searchUser){
	User users = null;
	Connection conn = null;
		try {
			//2����������
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/manage_student","root","123456");
			
			//4������sqlִ����
			Statement stat=conn.createStatement();
			
			//5��ִ��sql����
			ResultSet rs= stat.executeQuery("select * from users where username = '"+searchUser.getUsername()+"'and password = '"+searchUser.getPassword()+"'");
			//6����������
			if(rs.next()){
		    users= new User();
		    users.setId(rs.getInt("id"));
		    users.setUsername(rs.getString("username"));
		    users.setPassword(rs.getString("password"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return users;
	}
}
