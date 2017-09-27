package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BuMen;
import entity.Staff;

public class StaffDao extends BaseDao{

	public List<Staff> searchAll() {
		List<Staff> list = new ArrayList<Staff>();
		try {
			getStatement();
			// 5��ִ��sql���
			ResultSet rs = stat
					.executeQuery("SELECT s.*,bm.name as bmName,bm.staNums from staff as s left join bumen as bm on bm.id = s.bm_id ");
			// 6�� ��������
			while (rs.next())// next����boolean����
			{
				Staff sta = new Staff();
				sta.setId(rs.getInt("id"));
				sta.setName(rs.getString("name"));
				sta.setSex(rs.getString("sex"));
				sta.setAge(rs.getInt("age"));

				BuMen bm = new BuMen();
				bm.setId(rs.getInt("bm_id"));
				bm.setName(rs.getString("bmName"));
				bm.setStaNums(rs.getInt("bm.staNums"));
				sta.setBm(bm);
				list.add(sta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return list;
	}

	public Staff searchById(int id) {
		Staff sta = new Staff();
		try {
			getStatement();
			// 5��ִ��sql���
			ResultSet rs = stat
					.executeQuery("SELECT s.*,bm.name as bmName,bm.staNums from staff as s left join bumen as bm on s.bm_id = bm.id where s.id = "
							+ id);
			// 6�� ��������
			while (rs.next())// next����boolean����
			{
				sta.setId(rs.getInt("id"));
				sta.setName(rs.getString("name"));
				sta.setSex(rs.getString("sex"));
				sta.setAge(rs.getInt("age"));

				BuMen bm = new BuMen();
				bm.setId(rs.getInt("bm_id"));
				bm.setName(rs.getString("bmName"));
				bm.setStaNums(rs.getInt("bm.staNums"));
				sta.setBm(bm);
				// list.add(sta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7���ر�����
			closeAll();
		}
		return sta;
	}

	public boolean add(Staff sta) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		boolean flag = false;
		try {
			getStatement();
			// 5��ִ��sql���
			String sql = "insert into staff (name,sex,age,bm_id)values('"
					+ sta.getName() + "','" + sta.getSex() + "',"
					+ sta.getAge() + "," + sta.getBm().getId() + ")";
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

	public boolean updata(Staff sta) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		boolean flag = false;
		try {
			// Statement stat = con.createStatement();
			String sql = "update staff set name=?,sex=?,age=?,bm_id=? where id =?";
			getPreparedStatement(sql);
			pstat.setString(1, sta.getName());
			pstat.setString(2, sta.getSex());
			pstat.setInt(3, sta.getAge());
			pstat.setInt(4, sta.getBm().getId());
			pstat.setInt(5, sta.getId());
			// 5��ִ��sql���
			// String sql = "update staff set name='"+sta.getName()
			// +"', sex='"+sta.getSex()+"',age ='"+sta.getAge()+"' where id= "
			// +sta.getId();
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

	public int delete(Staff sta) {
		// ִ�гɹ�����0����1��ʾ�ɹ�ʧ��
		int result = 0;
		try {
			getStatement();
			// 5��ִ��sql���
			String sql = "delete from staff where id =" + sta.getId();// �����ַ���ƴ��
			result = stat.executeUpdate(sql);// ��ɾ��ͳһ����update int�����ұ�ʾӰ������
			// 6�� ��������
			// 7���ر�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return result;
	}

	public List<Staff> searchByCondition(Staff condition) {
		List<Staff> list = new ArrayList<Staff>();
		getStatement();
		String where = "";
		if (!condition.getName().equals("")) {
			where += "and s.name like '%" + condition.getName() + "%'";
		}
		if (!condition.getSex().equals("")) {
			where += "and sex ='" + condition.getSex() + "'";
		}
		if (condition.getAge() != -1) {
			where += "and age = " + condition.getAge();
		}
		if (condition.getBm() != null && condition.getBm().getId() == 0) {
			where += "and bm_id = 0 or bm_id is null";
		} else if (condition.getBm() != null && condition.getBm().getId() != -1) {
			where += "and bm_id = " + condition.getBm().getId();
		}

		String sql = "SELECT s.*,bm.name as bmName,bm.staNums from staff as s left join bumen as bm on bm.id = s.bm_id where 1=1 "
				+ where;

		try {
			rs = stat.executeQuery(sql);
			// 6�� ��������
			while (rs.next())// next����boolean����
			{
				Staff sta = new Staff();
				sta.setId(rs.getInt("id"));
				sta.setName(rs.getString("name"));
				sta.setSex(rs.getString("sex"));
				sta.setAge(rs.getInt("age"));

				BuMen bm = new BuMen();
				bm.setId(rs.getInt("bm_id"));
				bm.setName(rs.getString("bmName"));
				bm.setStaNums(rs.getInt("bm.staNums"));
				sta.setBm(bm);
				list.add(sta);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}
}
