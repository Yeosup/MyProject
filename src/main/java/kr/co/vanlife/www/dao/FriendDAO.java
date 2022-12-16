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

import kr.co.vanlife.www.dto.BoardDTO;
import kr.co.vanlife.www.dto.FriendDTO;

public class FriendDAO {
	private static FriendDAO friendDAO = new FriendDAO();
	private FriendDTO dto = new FriendDTO();
	private DataSource ds;
	private final String TABLE_NAME_F = "friend";
	private final String TABLE_NAME_B = "board";
	private final String TABLE_NAME_M = "member";
	private int result;
	private String sql;
	
	private FriendDAO() {
		try {
			Context cont = new InitialContext();
			ds = (DataSource) cont.lookup("java:comp/env/jdbc/LMK");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static FriendDAO getFriendDAO() {
		return friendDAO;
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
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void close(PreparedStatement ps, Connection conn) {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void addFriendDAO(FriendDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "INSERT INTO "+TABLE_NAME_F+"(id, friendId, profile, nickname, promise) SELECT ?, ?, ?, ?, ? FROM DUAL WHERE NOT EXISTS (SELECT * FROM friend WHERE id=? and friendId=?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getFriendId());
			ps.setString(3, dto.getProfile());
			ps.setString(4, dto.getNickname());
			ps.setString(5, dto.getPromise());
			ps.setString(6, dto.getId());
			ps.setString(7, dto.getFriendId());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public ArrayList<FriendDTO> friendListDAO(FriendDTO dto){
		ArrayList<FriendDTO> friendList = new ArrayList<FriendDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_NAME_F+" WHERE id=? order by no desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				FriendDTO dtoList = new FriendDTO();
				dtoList.setId(rs.getString("id"));
				dtoList.setFriendId(rs.getString("friendId"));
				dtoList.setProfile(rs.getString("profile"));
				dtoList.setNickname(rs.getString("nickname"));
				dtoList.setPromise(rs.getString("promise"));
				friendList.add(dtoList);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return friendList;
	}
	public FriendDTO friendList(FriendDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT COUNT(*) FROM "+TABLE_NAME_F+" WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setFriendCnt(rs.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	public void deleteFriendDAO(FriendDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "delete from "+TABLE_NAME_F+" where id=? and friendId=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getFriendId());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public void friendProfile(FriendDTO dto, String profile) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_F+" SET profile=?, nickname=?, promise=? WHERE friendId=?";
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
}
