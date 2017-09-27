package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ManageProject2BuMenDao extends BaseDao{

	public List<Project> searchSub(int bmId) {
		List<Project> list = new ArrayList<Project>();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat
					.executeQuery("select pro.id,pro.name "
							+ "from project as pro inner join m_bm_pro as m on pro.id = m.pro_id inner join bumen as bm on m.bm_id = bm.id where bm.id = "
							+ bmId);
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

	public List<Project> notSearchSub(int id) {
		List<Project> list = new ArrayList<Project>();
		try {
			getStatement();
			// 5、执行sql语句
			ResultSet rs = stat
					.executeQuery("select * from project where id not in ("
							+ "select pro.id from project as pro inner join m_bm_pro as m on pro.id = m.pro_id"
							+ " inner join bumen as bm on m.bm_id = bm.id where bm.id = "
							+ id + ")");
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

	public boolean add(int bmId, int proId) {
		// 执行成功返回0或者1表示成功失败
		boolean flag = false;
		try {
			getStatement();
			// 5、执行sql语句
			String sql = "insert into m_bm_pro values (" + bmId + "," + proId
					+ ")";
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

	public boolean delete(int bmId,int proId) {
		// 执行成功返回0或者1表示成功失败
		boolean flag = false;
		try {
			getStatement();
			// 5、执行sql语句
			String sql = "delete from m_bm_pro where bm_id = "+bmId+" and pro_id = " +proId;// 利用字符串拼接
			int result = stat.executeUpdate(sql);// 增删改统一调用update int类型且表示影响行数
			// 6、 处理结果集
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return flag;
	}

}
