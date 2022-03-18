package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.DirectMessageDAO;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.DirectMessageDTO;
import kr.co.vanlife.www.dto.MemberDTO;


public class MessageWriteCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		DirectMessageDAO daoD = DirectMessageDAO.getMessageDAO();
		DirectMessageDTO dtoD = new DirectMessageDTO();
		
		HttpSession session = request.getSession();
		dto.setFriendId(request.getParameter("friendId"));
		dto.setId(request.getParameter("id"));
		dao.addMessageFriend(dto);
		session.setAttribute("addDirectMessagefriendId",dao.addDirectMessagefriendId(dto));
		
		dtoD.setId(request.getParameter("id"));
		ArrayList<DirectMessageDTO> send = daoD.getDirectMessageList(dtoD);		
		session.setAttribute("send", send);
		
		ArrayList<DirectMessageDTO> read = daoD.DirectMessageReadList(dtoD);
		session.setAttribute("read", read);
	}
}
