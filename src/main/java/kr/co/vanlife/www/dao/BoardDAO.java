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
import kr.co.vanlife.www.dto.MemberDTO;

public class BoardDAO {
	private static BoardDAO boardDAO = new BoardDAO();
	private DataSource ds;
	private final String TABLE_NAME_B = "board";
	private final String TABLE_NAME_M = "member";
	private final String TABLE_NAME_F = "friend";
	private int result;
	private String sql;
	
	private BoardDAO() {
		try {
			Context cont = new InitialContext();
			ds = (DataSource) cont.lookup("java:comp/env/jdbc/testdb");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static BoardDAO getBoardDAO() {
		return boardDAO;
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
	public BoardDTO getboBoardDTO(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT b.*, m.* FROM "+TABLE_NAME_B+ " b, "+TABLE_NAME_M+" m"+" WHERE b.no=? AND m.id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getNo());
			ps.setString(2, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setContents(rs.getString("contents"));
				dto.setGood(rs.getInt("good"));
				dto.setBad(rs.getInt("bad"));
				dto.setWtime(rs.getString("wtime"));
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setStepNum(rs.getInt("stepNum"));
				dto.setIndentNum(rs.getInt("indentNum"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return dto;
	}
	public int getCurrentNum() {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT MAX(no) FROM "+TABLE_NAME_B;
		int curNum = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				curNum = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return curNum+1;
	}
	public void writeOKDAO(BoardDTO dto) {
		Connection conn  = getConnection();
		PreparedStatement ps = null;
		int curNum = getCurrentNum();
		sql = "INSERT INTO "+TABLE_NAME_B+"(id, contents, groupNum) VALUES(?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getContents());
			ps.setInt(3, curNum);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("sql 오류");
		}finally {
			close(ps, conn);
		}
	}
	public void modifyOK(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_B+" SET contents=?, wtime=now() WHERE no=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getContents());
			ps.setInt(2, dto.getNo());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public void updateStepNum(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_B+" SET stepNum=stepNum+1 WHERE groupNum=? AND stepNum>=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getGroupNum());
			ps.setInt(2, dto.getStepNum()+1);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public BoardDTO replyDAO(BoardDTO dto) {
		getboBoardDTO(dto);
		return dto;
	}
	public void replyOKDAO(BoardDTO dto) {
		updateStepNum(dto);
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "INSERT INTO "+TABLE_NAME_B+"(id, contents, groupNum, stepNum, indentNum) VALUES(?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getContents());
			ps.setInt(3, dto.getGroupNum());
			ps.setInt(4, dto.getStepNum()+1);
			ps.setInt(5, dto.getIndentNum()+1);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public void deleteDAO(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps =null;
		sql = "DELETE FROM "+TABLE_NAME_B+" WHERE no=?";
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
	public void replyCntDown(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_B+" SET replyCnt = replyCnt - 1 WHERE groupNum=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getGroupNum());
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
	}
	public ArrayList<BoardDTO> listDAO(){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql ="SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_M+" on "+TABLE_NAME_B+".id="+TABLE_NAME_M+".id"+" ORDER BY groupNum DESC, stepNum ASC";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setContents(rs.getString("contents"));
				dto.setGood(rs.getInt("good"));
				dto.setBad(rs.getInt("bad"));
				int wtime = Integer.parseInt(rs.getString("wtime").substring(11, 13));
				String timeType = "오전";
				if(wtime >= 12) {
					timeType = "오후";
					wtime -= 12;
				}
				dto.setWtime(rs.getString("wtime").substring(0, 11).replace("-", ".")+" "+timeType+" "+wtime+":"+rs.getString("wtime").substring(14, 16)+"");
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setStepNum(rs.getInt("stepNum"));
				dto.setIndentNum(rs.getInt("indentNum"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
				dto.setReplyCnt(rs.getInt("replyCnt"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	public ArrayList<BoardDTO> search(String kindOfSearch, String searchKeyword) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String searchIdSql = "SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_M+" on "+TABLE_NAME_B+".id like concat('%', "+TABLE_NAME_M+".id, '%') where "+TABLE_NAME_M+".id like ? ORDER BY groupNum DESC, stepNum ASC;";
		String searchNicknameSql = "SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_M+" on "+TABLE_NAME_B+".id like concat('%', "+TABLE_NAME_M+".id, '%') where "+TABLE_NAME_M+".nickname like ? ORDER BY groupNum DESC, stepNum ASC;";
		String searchKeywordSql = "SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_M+" on "+TABLE_NAME_B+".id like concat('%', "+TABLE_NAME_M+".id, '%') where "+TABLE_NAME_B+".contents like ? ORDER BY groupNum DESC, stepNum ASC;";
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
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setContents(rs.getString("contents"));
				dto.setGood(rs.getInt("good"));
				dto.setBad(rs.getInt("bad"));
				dto.setWtime(rs.getString("wtime"));
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setStepNum(rs.getInt("stepNum"));
				dto.setIndentNum(rs.getInt("indentNum"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	public BoardDTO myList(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT COUNT(*) FROM "+TABLE_NAME_B+" WHERE id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				dto.setMyList(rs.getInt(1));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(ps, conn);
		}
		return dto;
	}
	public void replyCnt(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_B+" SET replyCnt = replyCnt + 1 WHERE no=?";
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
	public void goodCnt(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_B+" SET good = good + 1 WHERE no=?";
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
	public void badCnt(BoardDTO dto) {
		Connection conn = getConnection();
		PreparedStatement ps = null;
		sql = "UPDATE "+TABLE_NAME_B+" SET bad = bad + 1 WHERE no=?";
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
	public ArrayList<BoardDTO> originListDAO(){
		ArrayList<BoardDTO> originList = new ArrayList<BoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql = "SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_M+" on "+TABLE_NAME_B+".id="+TABLE_NAME_M+".id"+" WHERE wtime > curdate() and stepNum=0 ORDER BY groupNum DESC";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfile(rs.getString("profile"));
				dto.setPromise(rs.getString("promise"));
				dto.setContents(rs.getString("contents"));
				originList.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return originList;
	}
	public BoardDTO viewDAO(BoardDTO dto) {
		dto = getboBoardDTO(dto);
		return dto;
	}
	public ArrayList<BoardDTO> myListDAO(BoardDTO bDto){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql ="SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_M+" on "+TABLE_NAME_B+".id="+TABLE_NAME_M+".id"+" WHERE "+TABLE_NAME_M+".id=? ORDER BY groupNum DESC, stepNum ASC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bDto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setContents(rs.getString("contents"));
				dto.setGood(rs.getInt("good"));
				dto.setBad(rs.getInt("bad"));
				int wtime = Integer.parseInt(rs.getString("wtime").substring(11, 13));
				String timeType = "오전";
				if(wtime >= 12) {
					timeType = "오후";
					wtime -= 12;
				}
				dto.setWtime(rs.getString("wtime").substring(0, 11).replace("-", ".")+" "+timeType+" "+wtime+":"+rs.getString("wtime").substring(14, 16)+"");
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setStepNum(rs.getInt("stepNum"));
				dto.setIndentNum(rs.getInt("indentNum"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
				dto.setReplyCnt(rs.getInt("replyCnt"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
	public ArrayList<BoardDTO> friendListDAO(BoardDTO bDto){
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		sql ="SELECT * FROM "+TABLE_NAME_B+" join "+TABLE_NAME_F+" on "+TABLE_NAME_B+".id="+TABLE_NAME_F+".friendId"+" WHERE "+TABLE_NAME_F+".id=? and stepNum=0 ORDER BY groupNum DESC, stepNum ASC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bDto.getId());
			rs = ps.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setContents(rs.getString("contents"));
				dto.setGood(rs.getInt("good"));
				dto.setBad(rs.getInt("bad"));
				int wtime = Integer.parseInt(rs.getString("wtime").substring(11, 13));
				String timeType = "오전";
				if(wtime >= 12) {
					timeType = "오후";
					wtime -= 12;
				}
				dto.setWtime(rs.getString("wtime").substring(0, 11).replace("-", ".")+" "+timeType+" "+wtime+":"+rs.getString("wtime").substring(14, 16)+"");
				dto.setGroupNum(rs.getInt("groupNum"));
				dto.setStepNum(rs.getInt("stepNum"));
				dto.setIndentNum(rs.getInt("indentNum"));
				dto.setProfile(rs.getString("profile"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPromise(rs.getString("promise"));
				dto.setReplyCnt(rs.getInt("replyCnt"));
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs, ps, conn);
		}
		return list;
	}
}