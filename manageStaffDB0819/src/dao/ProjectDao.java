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
			// 5��ִ��sql���
			ResultSet rs = stat.executeQuery("select *from project");
			// 6�� ��������
			while (rs.next())// next����boolean����
			{
				Project pro = new Project();

				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return list;
	}
	
	
	public List<Project> searchSub(int id) {
		List<Project> list = new ArrayList<Project>();
		try {
			getStatement();
			// 5��ִ��sql���
			ResultSet rs = stat.executeQuery("select pro.id,pro.name "
					+ "from project as pro inner join bj_pro as m on pro.id = m.pro_id inner join bumen as bj on m.bj_id = bj.id where bj.id = "+id);
			// 6�� ��������
			while (rs.next())// next����boolean����
			{
				Project pro = new Project();

				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return list;
	}
	
	public int deleteSearchSub(Project pro) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		int result = 0;
		try {
		getStatement();
			// 5��ִ��sql���
			String sql = "delete from project as pro where id =" + pro.getId()+" and delete"
					+ "from bj_pro as bs where pro.id = bs.pro_id";// �����ַ���ƴ��
			result = stat.executeUpdate(sql);// ��ɾ��ͳһ����update int�����ұ�ʾӰ������
			// 6�� ��������

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
			// 5��ִ��sql���
			ResultSet rs = stat.executeQuery("select *from project where id = "
					+ id);
			// 6�� ��������
			while (rs.next())// next����boolean����
			{
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				// list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return pro;
	}

	public boolean add(Project pro) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		boolean flag = false;
		try {
			getStatement();
			// 5��ִ��sql���
			String sql = "insert into project (name)values('"
					+ pro.getName() + "')";
			int result = stat.executeUpdate(sql);// ��ɾ��ͳһ����update int�����ұ�ʾӰ������
			// 6�� ��������
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return flag;
	}

	public boolean updata(Project pro) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		boolean flag = false;
		try {
			// Statement stat = con.createStatement();
			String sql = "update project set name=? where id =?";
			getPreparedStatement(sql);
			pstat.setString(1, pro.getName());
			pstat.setInt(2, pro.getId());
			// 5��ִ��sql���
			// String sql = "update project set name='"+pro.getName()
			// +"', sex='"+pro.getSex()+"',age ='"+pro.getAge()+"' where id= "
			// +pro.getId();
			// �򵥵��ַ���ƴ��
			int result = pstat.executeUpdate();
			// 6�� ��������
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return flag;
	}

	public int delete(Project pro) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		int result = 0;
		try {
		getStatement();
			// 5��ִ��sql���
			String sql = "delete from project where id =" + pro.getId();// �����ַ���ƴ��
			result = stat.executeUpdate(sql);// ��ɾ��ͳһ����update int�����ұ�ʾӰ������
			// 6�� ��������

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
			// 6�� ��������
			while (rs.next())// next����boolean����
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
