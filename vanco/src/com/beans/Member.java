package com.beans;

/*
 * member table DTO / VO / Bean 

create table member (
	idx int primary key auto_increment,
	name varchar(30) not null,
	user_id varchar(30) not null,
	user_pw varchar(30) not null
);

 */

public class Member {

	private int idx;
	private String name;
	private String user_id;
	private String user_pw;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	
}
