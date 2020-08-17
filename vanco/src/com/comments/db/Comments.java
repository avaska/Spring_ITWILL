package com.comments.db;

import java.sql.Timestamp;

/*
 * comments table DTO / VO / Bean 

create table comments (
   moimNum int, 
   num int,
   idx int,
   writer varchar(30),
   userId varchar(30),
   content text,
   regdate timestamp,
   reNum int,
   reDep int,
   reOdr int,
   primary key(moimNum, num, idx)
);

 */

public class Comments {
	
	private int num;
	private int idx;
	private String writer;
	private String content;
	private Timestamp regdate;		
	private int reNum;
	private int reDep;
	private int reOdr;
	private int moimNum;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getReNum() {
		return reNum;
	}
	public void setReNum(int reNum) {
		this.reNum = reNum;
	}
	public int getReDep() {
		return reDep;
	}
	public void setReDep(int reDep) {
		this.reDep = reDep;
	}
	public int getReOdr() {
		return reOdr;
	}
	public void setReOdr(int reOdr) {
		this.reOdr = reOdr;
	}
	public int getMoimNum() {
		return moimNum;
	}
	public void setMoimNum(int moimNum) {
		this.moimNum = moimNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
