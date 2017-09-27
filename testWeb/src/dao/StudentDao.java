package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Student;

public class StudentDao {

	public List<Student> serchAll() {
		List<Student> list = new ArrayList<Student>();
		try {
			//2����������
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/manage_student","root","123456");
			
			//4������sqlִ����
			Statement stat=con.createStatement();
			
			//5��ִ��sql����
			ResultSet rs= stat.executeQuery("select * from student");
			//6����������
			while(rs.next()){
			Student stu=new Student();
			stu.setId(rs.getInt("id"));
			stu.setName(rs.getString("name"));
			stu.setSex(rs.getString("sex"));
			stu.setAge(rs.getInt("age"));
			list.add(stu);
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
