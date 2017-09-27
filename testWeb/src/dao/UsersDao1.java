package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class UsersDao1 {
	
	
	public boolean insert(User users){
		boolean flag = false;
	    Connection conn = null;
		try {
			//2，加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/manage_student","root","123456");
			//4，建立sql执行器
			
			//5，执行sql语言
			String sql = "insert into users (username,password)values(?,?)";
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1, users.getUsername());
			pstat.setString(2, users.getPassword());
			int rs = pstat.executeUpdate();
			//6，处理结果集
			if(rs>0){
		    flag=true;
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
		return flag;
	}

	public User searchByUserNameAndPassword(User searchUser){
	User users = null;
	Connection conn = null;
		try {
			//2，加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/manage_student","root","123456");
			
			//4，建立sql执行器
			Statement stat=conn.createStatement();
			
			//5，执行sql语言
			ResultSet rs= stat.executeQuery("select * from users where username = '"+searchUser.getUsername()+"'and password = '"+searchUser.getPassword()+"'");
			//6，处理结果集
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
