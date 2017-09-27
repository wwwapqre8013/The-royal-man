package entity;

public class JiXiao {
	private int id;
	private Staff sta;
	private Project pro;
	private int jixiao;
	private String bonus;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Staff getSta() {
		return sta;
	}
	public void setSta(Staff sta) {
		this.sta = sta;
	}
	public Project getPro() {
		return pro;
	}
	public void setPro(Project pro) {
		this.pro = pro;
	}
	public int getJixiao() {
		return jixiao;
	}
	public void setJixiao(int jixiao) {
		this.jixiao = jixiao;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
}
