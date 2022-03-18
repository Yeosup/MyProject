package kr.co.vanlife.www.dto;

public class DirectMessageDTO {
	private int no;
	private String id;
	private String friendId;
	private String message;
	private String wtime;
	private String messageProfile;
	private String messageNickname;
	private String profile;
	private String nickname;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public String getMessageProfile() {
		return messageProfile;
	}
	public void setMessageProfile(String messageProfile) {
		this.messageProfile = messageProfile;
	}
	public String getMessageNickname() {
		return messageNickname;
	}
	public void setMessageNickname(String messageNickname) {
		this.messageNickname = messageNickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
