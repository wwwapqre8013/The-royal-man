package entity;

import java.io.Serializable;

public class Staff implements Serializable{
	private String name;
	private String sex;
	private int age;
	private int id;
	private BuMen bm;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BuMen getBm() {
		return bm;
	}

	public void setBm(BuMen bm) {
		this.bm = bm;
	}

}
