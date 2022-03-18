package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.FriendDAO;
import kr.co.vanlife.www.dto.FriendDTO;

public class DeleteFriendCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendDAO dao = FriendDAO.getFriendDAO();
		FriendDTO dto = new FriendDTO();
		HttpSession session = request.getSession();
		
		dto.setId(request.getParameter("id"));
		dto.setFriendId(request.getParameter("friendId"));
		dao.deleteFriendDAO(dto);
		
		ArrayList<FriendDTO> friendList = dao.friendListDAO(dto);		
		session.setAttribute("friendList", friendList);
		
		session.setAttribute("friendCnt", dao.friendList(dto));
	}
}
