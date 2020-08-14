package timeLine;

import java.sql.Timestamp;

public class timeLineDTO {
	
	private int timeLineNum;
	private String title;
	private String content;
	private Timestamp writeTime;
	private String writeTimeStr;	
	private String userId;
	private int badCount;
	private String ip;
	private int photoNum;
	private String photoUrl;
	private int timeLineReplyNum;
	private int re_ref;
	private int re_lev;
	private int re_seq;
	private String reOwnerNick;
	private int likesNum;
	private String likeId;
	
	// sql 조합에 따른 추가 dto
	private int likesCount;
	private String userPhoto;
	private String userCity;
	private String userDistrict;
	private String userNickname;
	private int photoCount;
	private int replyCount;
	
	
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getPhotoCount() {
		return photoCount;
	}
	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}
	public String getWriteTimeStr() {
		return writeTimeStr;
	}
	public void setWriteTimeStr(String writeTimeStr) {
		this.writeTimeStr = writeTimeStr;
	}
	
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
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
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
	public int getTimeLineNum() {
		return timeLineNum;
	}
	public void setTimeLineNum(int timeLineNum) {
		this.timeLineNum = timeLineNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBadCount() {
		return badCount;
	}
	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPhotoNum() {
		return photoNum;
	}
	public void setPhotoNum(int photoNum) {
		this.photoNum = photoNum;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public int getTimeLineReplyNum() {
		return timeLineReplyNum;
	}
	public void setTimeLineReplyNum(int timeLineReplyNum) {
		this.timeLineReplyNum = timeLineReplyNum;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public String getReOwnerNick() {
		return reOwnerNick;
	}
	public void setReOwnerNick(String reOwnerNick) {
		this.reOwnerNick = reOwnerNick;
	}
	public int getLikesNum() {
		return likesNum;
	}
	public void setLikesNum(int likesNum) {
		this.likesNum = likesNum;
	}
	public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String likeId) {
		this.likeId = likeId;
	}


}
