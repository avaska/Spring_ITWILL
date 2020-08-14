package com.community.db;

/*
 * community table DTO / VO / Bean 

create table community (
	num int primary key auto_increment,
	name varchar(30) not null,
	category varchar(30) not null
);

 */

public class Community {

	private int num;
	private String name;
	private String category;
	private int writePriv;
	private String photo;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getWritePriv() {
		return writePriv;
	}
	public void setWritePriv(int writePriv) {
		this.writePriv = writePriv;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
