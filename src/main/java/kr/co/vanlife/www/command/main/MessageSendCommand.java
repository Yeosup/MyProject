package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.DirectMessageDAO;
import kr.co.vanlife.www.dto.DirectMessageDTO;

public class MessageSendCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DirectMessageDAO dao = DirectMessageDAO.getMessageDAO();
		DirectMessageDTO dto = new DirectMessageDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setFriendId(request.getParameter("friendId"));
		dto.setMessage(request.getParameter("message"));
		dto.setMessageProfile(request.getParameter("messageProfile"));
		dto.setMessageNickname(request.getParameter("messageNickname"));
		
		dao.sendDirectMessage(dto);
		
		HttpSession session = request.getSession();
		ArrayList<DirectMessageDTO> send = dao.getDirectMessageList(dto);		
		session.setAttribute("send", send);
		
		ArrayList<DirectMessageDTO> read = dao.DirectMessageReadList(dto);
		session.setAttribute("read", read);
	}
}
