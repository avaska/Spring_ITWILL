package dm;

import java.sql.Timestamp;

public class dmDTO {
	private int num;
	private String sendUserId;
	private String receiveUserId;
	private String sendNickname;
	private String receiveNickname;
	private String dmContent;
	private Timestamp writeTime;
	private String ip;
	private int readCheck;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public String getSendNickname() {
		return sendNickname;
	}
	public void setSendNickname(String sendNickname) {
		this.sendNickname = sendNickname;
	}
	public String getReceiveNickname() {
		return receiveNickname;
	}
	public void setReceiveNickname(String receiveNickname) {
		this.receiveNickname = receiveNickname;
	}
	public String getDmContent() {
		return dmContent;
	}
	public void setDmContent(String dmContent) {
		this.dmContent = dmContent;
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
	public int getReadCheck() {
		return readCheck;
	}
	public void setReadCheck(int readCheck) {
		this.readCheck = readCheck;
	}
	

}
