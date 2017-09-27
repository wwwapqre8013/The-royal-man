package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.BuMen;
import entity.JiXiao;
import entity.Staff;
import entity.Project;

public class JiXiaoDao extends BaseDao{

	public List<JiXiao> searchAll() {
		List<JiXiao> list = new ArrayList<JiXiao>();
		try {
			getStatement();
			// 5、执行sql语句
			String sql = "select *from v_sta_bm_pro_jx";
			rs = stat.executeQuery(sql);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				JiXiao sc = new JiXiao();
				sc.setId(rs.getInt("jxId"));
				if(rs.getString("jixiao")!=null){
				sc.setJixiao(rs.getInt("jixiao"));
				}else{
					sc.setJixiao(-1);
				}
				sc.setBonus(rs.getString("bonus"));
				
				Staff sta = new Staff();
				sta.setName(rs.getString("staName"));
				sta.setId(rs.getInt("staId"));
				
				BuMen bm = new BuMen();
				bm.setId(rs.getInt("bmId"));
				bm.setName(rs.getString("bmName"));
				sta.setBm(bm);
				sc.setSta(sta);
				
				Project pro = new Project();
				pro.setId(rs.getInt("proId"));
				pro.setName(rs.getString("proName"));
				sc.setPro(pro);
				list.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 7、关闭连接
			closeAll();
		}
		return list;
	}


	public List<JiXiao> searchByCondition(JiXiao condition) {
		List<JiXiao> list = new ArrayList<JiXiao>();
		getStatement();
		String where = "";
		if (!condition.getSta().getName().equals("")) {
			where += "and staName like '%" + condition.getSta().getName() + "%'";
		}
		if (condition.getJixiao()!=-1) {
			where += " and jixiao = " + condition.getJixiao();
		}
		if (condition.getSta().getBm().getId() != -1) {
			where += " and bmId = " + condition.getSta().getBm().getId();
		}
		if ( condition.getPro().getId() != -1) {
			where += " and proId = " + condition.getPro().getId();
		}
		String sql ="select *from v_sta_bm_pro_jx where 1=1 "+ where;
		try {
			rs = stat.executeQuery(sql);
			// 6、 处理结果集
			while (rs.next())// next返回boolean类型
			{
				JiXiao sc = new JiXiao();
				sc.setId(rs.getInt("jxId"));
				if(rs.getString("jixiao")!=null){
				sc.setJixiao(rs.getInt("jixiao"));
				}else{
					sc.setJixiao(-1);
				}
				sc.setBonus(rs.getString("bonus"));
				
				Staff sta = new Staff();
				sta.setName(rs.getString("staName"));
				sta.setId(rs.getInt("staId"));
				
				BuMen bm = new BuMen();
				bm.setId(rs.getInt("bmId"));
				bm.setName(rs.getString("bmName"));
				sta.setBm(bm);
				sc.setSta(sta);
				
				Project pro = new Project();
				pro.setId(rs.getInt("proId"));
				pro.setName(rs.getString("proName"));
				sc.setPro(pro);
				list.add(sc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}


	public boolean save(Set<JiXiao> set) {
		boolean flag = true;
		for(JiXiao sc : set){
			if(sc.getId() == 0){
				flag = add(sc);
			}else{
				flag = update(sc);
			}
			if(flag == false){
				break;
			}
		}
		return flag;
	}


	private boolean update(JiXiao sc) {
		boolean flag  = false;
		try {
			String sql = "update jixiao set jixiao =? where id =?";
			getPreparedStatement(sql);
			pstat.setInt(1, sc.getJixiao());
			pstat.setInt(2, sc.getId());
			int result = pstat.executeUpdate();
			if(result >0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return flag;
	}


	private boolean add(JiXiao sc) {
		boolean flag = false;
		try {
			String sql = "insert into jixiao (sta_id,pro_id,jixiao) values (?,?,?)";
			getPreparedStatement(sql);
			pstat.setInt(1, sc.getSta().getId());
			pstat.setInt(2, sc.getPro().getId());
			pstat.setInt(3, sc.getJixiao());
			int result = pstat.executeUpdate();
			if(result >0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return flag;
	}
}
