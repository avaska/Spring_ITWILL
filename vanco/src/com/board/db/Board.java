package com.board.db;

import java.sql.Timestamp;

/*
 * comments table DTO / VO / Bean

CREATE TABLE board(
	moimNum INT,
	idx INT,
	title VARCHAR(50),
	writer VARCHAR(50),
	regdate TIMESTAMP,
	count INT,
	content TEXT,
	regip VARCHAR(50),
	filename VARCHAR(50),
	thumbImg VARCHAR(50),
	pin INT,
	PRIMARY KEY(moimNum, idx)
);

 */

public class Board {

	private int idx;
	private String title;
	private String writer;
	private Timestamp regdate;
	private int count;
	private String content;
	private String regip;
	private String filename;
	private String thumbImg;
	private int moimNum;
	private int pin;
	private int countcomm;
	private int countfile;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public String getFilename() {
		return filename;
	}
	public String getThumbImg() {
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
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
	public int getPin() {
		return pin;
	}
	public int getCountcomm() {
		return countcomm;
	}
	public void setCountcomm(int countcomm) {
		this.countcomm = countcomm;
	}
	public int getCountfile() {
		return countfile;
	}
	public void setCountfile(int countfile) {
		this.countfile = countfile;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
}
