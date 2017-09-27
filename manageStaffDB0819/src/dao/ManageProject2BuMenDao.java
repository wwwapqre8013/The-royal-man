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
			// 5��ִ��sql���
			ResultSet rs = stat
					.executeQuery("select pro.id,pro.name "
							+ "from project as pro inner join m_bm_pro as m on pro.id = m.pro_id inner join bumen as bm on m.bm_id = bm.id where bm.id = "
							+ bmId);
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

	public List<Project> notSearchSub(int id) {
		List<Project> list = new ArrayList<Project>();
		try {
			getStatement();
			// 5��ִ��sql���
			ResultSet rs = stat
					.executeQuery("select * from project where id not in ("
							+ "select pro.id from project as pro inner join m_bm_pro as m on pro.id = m.pro_id"
							+ " inner join bumen as bm on m.bm_id = bm.id where bm.id = "
							+ id + ")");
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

	public boolean add(int bmId, int proId) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		boolean flag = false;
		try {
			getStatement();
			// 5��ִ��sql���
			String sql = "insert into m_bm_pro values (" + bmId + "," + proId
					+ ")";
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

	public boolean delete(int bmId,int proId) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		boolean flag = false;
		try {
			getStatement();
			// 5��ִ��sql���
			String sql = "delete from m_bm_pro where bm_id = "+bmId+" and pro_id = " +proId;// �����ַ���ƴ��
			int result = stat.executeUpdate(sql);// ��ɾ��ͳһ����update int�����ұ�ʾӰ������
			// 6�� ��������
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
