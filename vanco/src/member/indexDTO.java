package member;

public class indexDTO {
	//인기사진
	private int timeLineNum;
	private String title;
	private String content;
	private String writeTimeStr;
	private String userId;
	private String userNickname;
	private int photoNum;
	private String photoUrl;
	private String likesCount;
	private String replyCount;
	
	//최신글
	private String boardName;
	private int idx;
	private int moimNum;
	private int contentCount;
	
	
	
	public int getContentCount() {
		return contentCount;
	}
	public void setContentCount(int contentCount) {
		this.contentCount = contentCount;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getMoimNum() {
		return moimNum;
	}
	public void setMoimNum(int moimNum) {
		this.moimNum = moimNum;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
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
	public String getWriteTimeStr() {
		return writeTimeStr;
	}
	public void setWriteTimeStr(String writeTimeStr) {
		this.writeTimeStr = writeTimeStr;
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
	public String getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(String likesCount) {
		this.likesCount = likesCount;
	}
	public String getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(String replyCount) {
		this.replyCount = replyCount;
	}
	


}
