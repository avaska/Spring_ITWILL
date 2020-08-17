package neighborComm;

import java.sql.Timestamp;

public class thunderDTO {

	private String thunderNum;
	private String userId;
	private String thunderName;
	private String thunderPlace;
	private Timestamp thunderDate;
	private String thunderPerson;
	private Timestamp makingTime;
	private String userPhoto;
	private int userLevel;
	
	// 조인 컬럼 저장용
	private int thunderJoinCount;
	
	
	
	public int getThunderJoinCount() {
		return thunderJoinCount;
	}
	public void setThunderJoinCount(int thunderJoinCount) {
		this.thunderJoinCount = thunderJoinCount;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getMoimNum() {
		return moimNum;
	}
	public void setMoimNum(String moimNum) {
		this.moimNum = moimNum;
	}
	private String moimNum;
	
	public String getThunderNum() {
		return thunderNum;
	}
	public void setThunderNum(String thunderNum) {
		this.thunderNum = thunderNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getThunderName() {
		return thunderName;
	}
	public void setThunderName(String thunderName) {
		this.thunderName = thunderName;
	}
	public String getThunderPlace() {
		return thunderPlace;
	}
	public void setThunderPlace(String thunderPlace) {
		this.thunderPlace = thunderPlace;
	}
	public Timestamp getThunderDate() {
		return thunderDate;
	}
	public void setThunderDate(Timestamp thunderDate) {
		this.thunderDate = thunderDate;
	}
	public String getThunderPerson() {
		return thunderPerson;
	}
	public void setThunderPerson(String thunderPerson) {
		this.thunderPerson = thunderPerson;
	}
	public Timestamp getMakingTime() {
		return makingTime;
	}
	public void setMakingTime(Timestamp makingTime) {
		this.makingTime = makingTime;
	}

	
	
	
}
