package neighborComm;

import java.sql.Timestamp;

public class moimDTO {
	
	private int moimNum;
	private String userId;
	private String moimCategory;
	private String moimTitle;
	private String moimIntro;
	private String moimPhoto;
	private Timestamp makingTime;
	private String userCity;
	private String userDistrict;
	private int maxQuota;
	private int memberCount;
	
	
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public int getMaxQuota() {
		return maxQuota;
	}
	public void setMaxQuota(int maxQuota) {
		this.maxQuota = maxQuota;
	}
	public int getMoimNum() {
		return moimNum;
	}
	public void setMoimNum(int moimNum) {
		this.moimNum = moimNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMoimCategory() {
		return moimCategory;
	}
	public void setMoimCategory(String moimCategory) {
		this.moimCategory = moimCategory;
	}
	public String getMoimTitle() {
		return moimTitle;
	}
	public void setMoimTitle(String moimTitle) {
		this.moimTitle = moimTitle;
	}
	public String getMoimIntro() {
		return moimIntro;
	}
	public void setMoimIntro(String moimIntro) {
		this.moimIntro = moimIntro;
	}
	public String getMoimPhoto() {
		return moimPhoto;
	}
	public void setMoimPhoto(String moimPhoto) {
		this.moimPhoto = moimPhoto;
	}
	public Timestamp getMakingTime() {
		return makingTime;
	}
	public void setMakingTime(Timestamp makingTime) {
		this.makingTime = makingTime;
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

	
	
	

	
}
