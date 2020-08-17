package neighborComm;

import java.sql.Timestamp;

public class neiBoardDTO {
	
	private int num;
	private String userId;	
	private String userNickname;	
	private String userPhoto;	
	private String upPhoto1;
	private String upPhoto2;	
	private String upPhoto3;	
	private String upPhoto4;	
	private String subject;	
	private String content;	
	private int re_ref;	
	private int re_lev;	
	private int re_seq;	
	private int readCount;	
	private int replyCount;	
	private Timestamp writeTime;
	private String ip;
	
	// 댓글게시 위치잡기용 페이지번호, 컨텐트번호 추가
	private int contentPageNum;
	private int contentNum;
	
	// 대댓글 게시 시 @ 원작자 닉네임 표기를 위한 원작자 id저장 정보 추가
	private String reOwnerNick;
	
	
	public String getReOwnerNick() {
		return reOwnerNick;
	}
	public void setReOwnerNick(String reOwnerNick) {
		this.reOwnerNick = reOwnerNick;
	}
	public int getContentNum() {
		return contentNum;
	}
	public void setContentNum(int contentNum) {
		this.contentNum = contentNum;
	}
	public int getContentPageNum() {
		return contentPageNum;
	}
	public void setContentPageNum(int contentPageNum) {
		this.contentPageNum = contentPageNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUpPhoto1() {
		return upPhoto1;
	}
	public void setUpPhoto1(String upPhoto1) {
		this.upPhoto1 = upPhoto1;
	}
	public String getUpPhoto2() {
		return upPhoto2;
	}
	public void setUpPhoto2(String upPhoto2) {
		this.upPhoto2 = upPhoto2;
	}
	public String getUpPhoto3() {
		return upPhoto3;
	}
	public void setUpPhoto3(String upPhoto3) {
		this.upPhoto3 = upPhoto3;
	}
	public String getUpPhoto4() {
		return upPhoto4;
	}
	public void setUpPhoto4(String upPhoto4) {
		this.upPhoto4 = upPhoto4;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Timestamp getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Timestamp writeTime) {
		this.writeTime = writeTime;
	}	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
}
