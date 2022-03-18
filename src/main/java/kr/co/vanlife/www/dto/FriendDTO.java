package kr.co.vanlife.www.dto;

public class FriendDTO {
	private String id;
	private String friendId;
	private String profile;
	private String nickname;
	private String promise;
	private int friendCnt;
	
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
	public String getPromise() {
		return promise;
	}
	public void setPromise(String promise) {
		this.promise = promise;
	}
	public int getFriendCnt() {
		return friendCnt;
	}
	public void setFriendCnt(int friendCnt) {
		this.friendCnt = friendCnt;
	}
}
