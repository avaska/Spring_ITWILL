package com.itwillbs.domain;

import java.sql.Timestamp;

public class MemberVO {
	//회원 정보를 저장하는 객체(자바빈, DTO)
	private String userid;
	private String userpw;
	private String username;
	private String email;
	private Timestamp regdate;
	private Timestamp updatedate;
	
	// set/get
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getUserpw() {
		return userpw;
	}


	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}


	public Timestamp getUpdatedate() {
		return updatedate;
	}


	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	// alt + Shift + S 
	// Generate toString
	// toString()
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", email=" + email
				+ ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}


	
	
	
	
	
	
}
