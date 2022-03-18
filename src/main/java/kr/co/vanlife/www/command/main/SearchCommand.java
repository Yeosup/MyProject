package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.BoardDAO;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.BoardDTO;
import kr.co.vanlife.www.dto.MemberDTO;

public class SearchCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getBoardDAO();
		MemberDAO mDao = MemberDAO.getMemberDAO();
		
		String kindOfSearch = request.getParameter("kindOfSearch");
		String searchKeyword = request.getParameter("searchKeyword");
		ArrayList<BoardDTO> list = dao.search(kindOfSearch, searchKeyword);
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		ArrayList<MemberDTO> memberList = mDao.searchMemberListDAO(kindOfSearch, searchKeyword);
		session.setAttribute("memberList", memberList);
		
//		ArrayList<MemberDTO> memberList = mDao.memberListDAO();
//		session.setAttribute("memberList", memberList);	
		
	}
}
