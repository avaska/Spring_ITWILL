package com.member.db;

import java.sql.Timestamp;

//DTO Ŭ������ ����
	//1.ȸ������ ������ �� ������ �����Ͽ� DB�� insert �뵵�� Ŭ����
	//2.DB�� ���� ȸ�������� �˻��� �� �˻��� �����͸� ������ �뵵�� Ŭ����

public class MemberDTO {
	
	private int userNum;
	private String userId;
	private String userNickname;
	private String userPwd;
	private String userPhoto;
	private String dogPhoto;
	private String userCity;
	private String userDistrict;
	private int userBirth;
	private String userGender;
	private String userPosition;
	private String userComment;
	private Timestamp joinDate;
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getDogPhoto() {
		return dogPhoto;
	}
	public void setDogPhoto(String dogPhoto) {
		this.dogPhoto = dogPhoto;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserDistrict() {
		return userDistrict;
	}
	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}
	public int getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(int userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	public Timestamp getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}
	

}
