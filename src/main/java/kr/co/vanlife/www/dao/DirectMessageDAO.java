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

import kr.co.vanlife.www.dto.DirectMessageDTO;

public class DirectMessageDAO {
	private static DirectMessageDAO messageDAO = new DirectMessageDAO();
	private DataSource ds;
	private final String TABLE_MANE_D = "directMessage";
	private final String TABLE_NAME_M = "member";
	private int result;
	private String sql;
	
	private DirectMessageDAO() {
		try {
			Context cont = new InitialContext();
			ds = (DataSource) cont.lookup("java:comp/env/jdbc/testdb");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static DirectMessageDAO getMessageDAO() {
		return messageDAO;
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
	public ArrayList<DirectMessageDTO> getDirectMessageList(DirectMessageDTO dto){
		ArrayList<DirectMessageDTO> directMessageList = new ArrayList<DirectMessageDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_MANE_D+" JOIN "+TABLE_NAME_M+" on "+TABLE_MANE_D+".id="+TABLE_NAME_M+".id"+" WHERE "+TABLE_MANE_D+".id=? order by wtime desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				DirectMessageDTO dDto = new DirectMessageDTO();
				dDto.setNo(rs.getInt("no"));
				dDto.setId(rs.getString("id"));
				dDto.setFriendId(rs.getString("friendId"));
				dDto.setMessage(rs.getString("message"));
				int wtime = Integer.parseInt(rs.getString("wtime").substring(11, 13));
				String timeType = "오전";
				if(wtime >= 12) {
					timeType = "오후";
					wtime -= 12;
				}
				dDto.setWtime(rs.getString("wtime").substring(0, 11).replace("-", ".")+" "+timeType+" "+wtime+":"+rs.getString("wtime").substring(14, 16)+"");
				dDto.setMessageProfile(rs.getString("messageProfile"));
				dDto.setMessageNickname(rs.getString("messageNickname"));
				dDto.setProfile(rs.getString("profile"));
				dDto.setNickname(rs.getString("nickname"));
				directMessageList.add(dDto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return directMessageList;
	}
	public ArrayList<DirectMessageDTO> DirectMessageReadList(DirectMessageDTO dto){
		ArrayList<DirectMessageDTO> readList = new ArrayList<DirectMessageDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_MANE_D+" JOIN "+TABLE_NAME_M+" on "+TABLE_MANE_D+".id="+TABLE_NAME_M+".id"+" WHERE "+TABLE_MANE_D+".friendId=? order by wtime desc";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				DirectMessageDTO dDto = new DirectMessageDTO();
				dDto.setNo(rs.getInt("no"));
				dDto.setId(rs.getString("id"));
				dDto.setFriendId(rs.getString("friendId"));
				dDto.setMessage(rs.getString("message"));
				int wtime = Integer.parseInt(rs.getString("wtime").substring(11, 13));
				String timeType = "오전";
				if(wtime >= 12) {
					timeType = "오후";
					wtime -= 12;
				}
				dDto.setWtime(rs.getString("wtime").substring(0, 11).replace("-", ".")+" "+timeType+" "+wtime+":"+rs.getString("wtime").substring(14, 16)+"");
				dDto.setMessageProfile(rs.getString("messageProfile"));
				dDto.setMessageNickname(rs.getString("messageNickname"));
				dDto.setProfile(rs.getString("profile"));
				dDto.setNickname(rs.getString("nickname"));
				readList.add(dDto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return readList;
	}
	public void sendDirectMessage(DirectMessageDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "INSERT INTO "+TABLE_MANE_D+" VALUES(null, ?, ?, ?, now(), ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getFriendId());
			ps.setString(3, dto.getMessage());
			ps.setString(4, dto.getMessageProfile());
			ps.setString(5, dto.getMessageNickname());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public DirectMessageDTO messageReadDAO(DirectMessageDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_MANE_D+" WHERE no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getNo());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setMessage(rs.getString("message"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	public void deleteMyMessage(DirectMessageDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql ="DELETE FROM "+TABLE_MANE_D+" WHERE no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getNo());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
}
