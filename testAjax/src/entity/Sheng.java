package entity;

import java.util.List;

public class Sheng {
private int id;
private String name;
List<Shi> shis; 

public List<Shi> getShis() {
	return shis;
}
public void setShis(List<Shi> shis) {
	this.shis = shis;
}
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

}
