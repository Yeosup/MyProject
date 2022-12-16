package kr.co.vanlife.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.vanlife.www.dto.MemberDTO;

public class MemberDAO {
	private static MemberDAO memberDAO = new MemberDAO();
	private MemberDTO dto = new MemberDTO();
	private DataSource ds;
	private final String TABLE_NAME = "member";
	private final String TABLE_NAME_B = "board";
	private final String TABEE_NAME_F = "friend";
	private int result;
	private String sql;
	
	private MemberDAO() {
		try {
			Context cont = new InitialContext();
			ds = (DataSource) cont.lookup("java:comp/env/jdbc/LMK");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getMemberDAO() {
		return memberDAO;
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if(rs!=null)rs.close();
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement ps, Connection conn) {
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void memberRegisterDAO(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "INSERT INTO "+TABLE_NAME+"(id, pw, name, birthYear, birthMonth, birthDate, gender, email, mobile, profile, nickname, promise) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setInt(4, dto.getBirthYear());
			ps.setInt(5, dto.getBirthMonth());
			ps.setInt(6, dto.getBirthDate());
			ps.setString(7, dto.getGender());
			ps.setString(8, dto.getEmail());
			ps.setString(9, dto.getMobile());
			ps.setString(10, dto.getProfile());
			ps.setString(11, dto.getNickname());
			ps.setString(12, dto.getPromise());
			
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("회원 등록에 실패했습니다.");
		}finally {
			close(ps, conn);
		}
	}
	public void memberModifyDAO(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME+" SET name=?, birthYear=?, birthMonth=?, birthDate=?, gender=?, email=?, mobile=?, profile=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getBirthYear());
			ps.setInt(3, dto.getBirthMonth());
			ps.setInt(4, dto.getBirthDate());
			ps.setString(5, dto.getGender());
			ps.setString(6, dto.getEmail());
			ps.setString(7, dto.getMobile());
			ps.setString(8, dto.getProfile());
			ps.setString(9, dto.getId());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public MemberDTO memberLoginDAO(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_NAME+" WHERE id=? AND pw=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirthYear(rs.getInt("birthYear"));
				dto.setBirthMonth(rs.getInt("birthMonth"));
				dto.setBirthDate(rs.getInt("birthDate"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
				dto.setFriendId(rs.getString("friendId"));
			}else
				dto = null;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	public MemberDTO memberCheckDAO(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_NAME+" WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBirthYear(rs.getInt("birthYear"));
				dto.setBirthMonth(rs.getInt("birthMonth"));
				dto.setBirthDate(rs.getInt("birthDate"));
				dto.setGender(rs.getString("gender"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
				dto.setFriendId(rs.getString("friendId"));
			}else
				dto = null;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	public void profile(MemberDTO dto, String profile) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME+" SET profile=?, nickname=?, promise=? WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, profile);
			ps.setString(2, dto.getNickname());
			ps.setString(3, dto.getPromise());
			ps.setString(4, dto.getId());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public MemberDTO getProfile(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT profile, nickname, id FROM "+TABLE_NAME+" WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
//				System.out.println("이미지 : " + rs.getString("profile")); // 디버깅
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setId(rs.getString("id"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	public ArrayList<MemberDTO> memberListDAO(){
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_NAME;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile(rs.getString("profile"));
				dto.setPromise(rs.getString("promise"));
				memberList.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return memberList;
	}
	public ArrayList<MemberDTO> searchMemberListDAO(String kindOfSearch, String searchKeyword) {
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String searchIdSql = "SELECT * FROM "+TABLE_NAME+" WHERE  id LIKE ?";
		String searchNicknameSql = "SELECT * FROM "+TABLE_NAME+" WHERE nickname LIKE ?";
		String searchKeywordSql = "SELECT * FROM "+TABLE_NAME+" WHERE contents LIKE ?;";
		try {
			if(kindOfSearch.equals("searchId")) {
				ps = conn.prepareStatement(searchIdSql);
				ps.setString(1, "%" + searchKeyword + "%");
			}else if(kindOfSearch.equals("searchNickname")) {
				ps = conn.prepareStatement(searchNicknameSql);
				ps.setString(1, "%" + searchKeyword + "%");
			}else if(kindOfSearch.equals("searchContents")) {
				ps = conn.prepareStatement(searchKeywordSql);
				ps.setString(1, "%" + searchKeyword + "%");
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile(rs.getString("profile"));
				dto.setPromise(rs.getString("promise"));
				memberList.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return memberList;
	}
	public void addMessageFriend(MemberDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql="UPDATE "+TABLE_NAME+" SET friendId=? WHERE id=?";
		try {
			ps =conn.prepareStatement(sql);
			ps.setString(1, dto.getFriendId());
			ps.setString(2, dto.getId());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public MemberDTO addDirectMessagefriendId(MemberDTO dto){
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT "+TABEE_NAME_F+".profile, "+TABEE_NAME_F+".nickname, "+TABLE_NAME+".friendId FROM "+TABLE_NAME+" join "+TABEE_NAME_F+" on "+TABLE_NAME+".friendId="+TABEE_NAME_F+".friendId where "+TABLE_NAME+".id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setFriendId(rs.getString("friendId"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
}