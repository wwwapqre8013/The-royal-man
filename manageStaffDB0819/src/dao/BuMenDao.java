package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BuMen;

public class BuMenDao extends BaseDao{


	public List<BuMen> searchAll() {
		List<BuMen> list = new ArrayList<BuMen>();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat.executeQuery("select *from bumen");
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				BuMen bm = new BuMen();

				bm.setId(rs.getInt("id"));
				bm.setName(rs.getString("name"));
				bm.setStaNums(rs.getInt("staNums"));
				list.add(bm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return list;
	}

	
	public BuMen searchById(int id) {
		BuMen bm = new BuMen();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat.executeQuery("select *from bumen where id = "
					+ id);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				bm.setId(rs.getInt("id"));
				bm.setName(rs.getString("name"));
				bm.setStaNums(rs.getInt("staNums"));
				// list.add(bm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return bm;
	}

	
	
	public boolean add(BuMen bm) {
		// 执行成功返回0或者1表示成功失败
		boolean flag = false;
		try {
			getStatement();
			// 5、执行sql语句
			String sql = "insert into bumen (name) values('"
					+ bm.getName() + "')";
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

	public boolean updata(BuMen bm) {
		// 执行成功返回0或者1表示成功失败
		boolean flag = false;
		try {
			// Statement stat = con.createStatement();
			String sql = "update bumen set name=? where id =?";
			getPreparedStatement(sql);
			pstat.setString(1, bm.getName());
			pstat.setInt(2, bm.getId());
			// 5、执行sql语句
			// String sql = "update bumen set name='"+bm.getName()
			// +"', sex='"+bm.getSex()+"',age ='"+bm.getAge()+"' where id= "
			// +bm.getId();
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

	public int delete(BuMen bm) {
		// 执行成功返回0或者1表示成功失败
		int result = 0;
		try {
		getStatement();
			// 5、执行sql语句
			String sql = "delete from bumen where id =" + bm.getId();// 利用字符串拼接
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

	public List<BuMen> searchByCondition(BuMen condition) {
		List<BuMen> list = new ArrayList<BuMen>();
		getStatement();
		String where = "";
		if(!condition.getName().equals("")){
		where += "and name like '%"+condition.getName()+"%'";
		}
		if(condition.getStaNums()!=-1){
			where += "and staNums = "+condition.getStaNums();
			}
		
		String sql = "select *from bumen where 1=1 " +where;
		System.out.println(sql);

		try {
			rs = stat.executeQuery(sql);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				BuMen bm = new BuMen();
				bm.setId(rs.getInt("id"));
				bm.setName(rs.getString("name"));
				bm.setStaNums(rs.getInt("staNums"));
				list.add(bm);
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
