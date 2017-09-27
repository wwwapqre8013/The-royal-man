package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ProjectDao extends BaseDao{

	public List<Project> searchAll() {
		List<Project> list = new ArrayList<Project>();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat.executeQuery("select *from project");
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				Project pro = new Project();

				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return list;
	}
	
	
	public List<Project> searchSub(int id) {
		List<Project> list = new ArrayList<Project>();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat.executeQuery("select pro.id,pro.name "
					+ "from project as pro inner join bj_pro as m on pro.id = m.pro_id inner join bumen as bj on m.bj_id = bj.id where bj.id = "+id);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				Project pro = new Project();

				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return list;
	}
	
	public int deleteSearchSub(Project pro) {
		// 执行成功返回0或者1表示成功失败
		int result = 0;
		try {
		getStatement();
			// 5、执行sql语句
			String sql = "delete from project as pro where id =" + pro.getId()+" and delete"
					+ "from bj_pro as bs where pro.id = bs.pro_id";// 利用字符串拼接
			result = stat.executeUpdate(sql);// 增删改统一调用update int类型且表示影响行数
			// 6、 处理结果集

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return result;
	}
	public Project searchById(int id) {
		Project pro = new Project();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat.executeQuery("select *from project where id = "
					+ id);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				// list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return pro;
	}

	public boolean add(Project pro) {
		// 执行成功返回0或者1表示成功失败
		boolean flag = false;
		try {
			getStatement();
			// 5、执行sql语句
			String sql = "insert into project (name)values('"
					+ pro.getName() + "')";
			int result = stat.executeUpdate(sql);// 增删改统一调用update int类型且表示影响行数
			// 6、 处理结果集
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return flag;
	}

	public boolean updata(Project pro) {
		// 执行成功返回0或者1表示成功失败
		boolean flag = false;
		try {
			// Statement stat = con.createStatement();
			String sql = "update project set name=? where id =?";
			getPreparedStatement(sql);
			pstat.setString(1, pro.getName());
			pstat.setInt(2, pro.getId());
			// 5、执行sql语句
			// String sql = "update project set name='"+pro.getName()
			// +"', sex='"+pro.getSex()+"',age ='"+pro.getAge()+"' where id= "
			// +pro.getId();
			// 简单的字符串拼接
			int result = pstat.executeUpdate();
			// 6、 处理结果集
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return flag;
	}

	public int delete(Project pro) {
		// 执行成功返回0或者1表示成功失败
		int result = 0;
		try {
		getStatement();
			// 5、执行sql语句
			String sql = "delete from project where id =" + pro.getId();// 利用字符串拼接
			result = stat.executeUpdate(sql);// 增删改统一调用update int类型且表示影响行数
			// 6、 处理结果集

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return result;
	}
	

	public List<Project> searchByCondition(Project condition) {
		List<Project> list = new ArrayList<Project>();
		getStatement();
		String where = "";
		if(!condition.getName().equals("")){
		where += "and name like '%"+condition.getName()+"%'";
		}
		String sql = "select *from project where 1=1 " +where;
		System.out.println(sql);

		try {
			rs = stat.executeQuery(sql);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return list;
	}


}
