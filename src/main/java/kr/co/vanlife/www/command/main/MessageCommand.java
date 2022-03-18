package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.DirectMessageDAO;
import kr.co.vanlife.www.dao.FriendDAO;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.DirectMessageDTO;
import kr.co.vanlife.www.dto.FriendDTO;
import kr.co.vanlife.www.dto.MemberDTO;

public class MessageCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendDAO dao = FriendDAO.getFriendDAO();
		FriendDTO dto = new FriendDTO();
		DirectMessageDAO daoD = DirectMessageDAO.getMessageDAO();
		DirectMessageDTO dtoD = new DirectMessageDTO();
		MemberDAO mDao = MemberDAO.getMemberDAO();
		MemberDTO mDto = new MemberDTO();
		HttpSession session = request.getSession();
		
		mDto.setId(request.getParameter("id"));
		session.setAttribute("userInfo",mDao.memberCheckDAO(mDto));
		
		dto.setId(request.getParameter("id"));		
		ArrayList<FriendDTO> friendList = dao.friendListDAO(dto);		
		session.setAttribute("friendList", friendList);
		
		session.setAttribute("friendCnt", dao.friendList(dto));
		
		dtoD.setId(request.getParameter("id"));
		ArrayList<DirectMessageDTO> send = daoD.getDirectMessageList(dtoD);		
		session.setAttribute("send", send);
		
		ArrayList<DirectMessageDTO> read = daoD.DirectMessageReadList(dtoD);
		session.setAttribute("read", read);
	}
}
