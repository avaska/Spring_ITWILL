package com.board.db;

/*
 *	files table DTO / VO / Bean

create table files (
	num int primary key auto_increment,
	writer varchar(30),
	idx int,
	filename varchar(100),
	moimNum int
);

 */

public class Files {

	private int num;
	private String writer;
	private int idx;
	private String filename;
	private int moimNum;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getMoimNum() {
		return moimNum;
	}
	public void setMoimNum(int moimNum) {
		this.moimNum = moimNum;
	}
	
	
	
}
