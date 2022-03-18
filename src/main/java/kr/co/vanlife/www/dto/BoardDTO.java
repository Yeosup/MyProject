package kr.co.vanlife.www.dto;

public class BoardDTO {
	private int no;
	private String id;
	private String contents;
	private int good;
	private int bad;
	private String wtime;
	private int groupNum;
	private int stepNum;
	private int indentNum;
	private String profile;
	private String nickname;
	private String promise;
	private int myList;
	private int replyCnt;
	private int friendList;
	
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}
	public int getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}
	public int getStepNum() {
		return stepNum;
	}
	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}
	public int getIndentNum() {
		return indentNum;
	}
	public void setIndentNum(int indentNum) {
		this.indentNum = indentNum;
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
	public int getMyList() {
		return myList;
	}
	public void setMyList(int myList) {
		this.myList = myList;
	}
	public int getReplyCnt() {
		return replyCnt;
	}
	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}
	public int getFriendList() {
		return friendList;
	}
	public void setFriendList(int friendList) {
		this.friendList = friendList;
	}
}
