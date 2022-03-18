package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.BoardDAO;
import kr.co.vanlife.www.dao.FriendDAO;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.BoardDTO;
import kr.co.vanlife.www.dto.FriendDTO;
import kr.co.vanlife.www.dto.MemberDTO;

public class LoginOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = MemberDAO.getMemberDAO();
		MemberDTO mDto = new MemberDTO();
		BoardDAO bDao = BoardDAO.getBoardDAO();
		BoardDTO bDto = new BoardDTO();
		FriendDAO fDao = FriendDAO.getFriendDAO();
		FriendDTO fDto = new FriendDTO();
		
		mDto.setId(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		session.setAttribute("userInfo",mDao.memberCheckDAO(mDto));
		
		ArrayList<BoardDTO> list = bDao.listDAO();
		session.setAttribute("list", list);		
		
		ArrayList<BoardDTO> originList = bDao.originListDAO();
		session.setAttribute("originList", originList);
		
		bDto.setId(request.getParameter("id"));
		session.setAttribute("count", bDao.myList(bDto));
		
		fDto.setId(request.getParameter("id"));
		session.setAttribute("friendCnt", fDao.friendList(fDto));
		
		ArrayList<MemberDTO> memberList = mDao.memberListDAO();
		session.setAttribute("memberList", memberList);		
	}
}
