package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.BoardDAO;
import kr.co.vanlife.www.dto.BoardDTO;

public class ReplyOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getBoardDAO();
		BoardDTO dto = new BoardDTO();
		dto.setId(request.getParameter("id"));
		dto.setContents(request.getParameter("contents"));
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setGroupNum(Integer.parseInt(request.getParameter("groupNum")));
		dto.setStepNum(Integer.parseInt(request.getParameter("stepNum")));
		dto.setIndentNum(Integer.parseInt(request.getParameter("indentNum")));
		dao.replyOKDAO(dto);
		dao.replyCnt(dto);
		
		HttpSession session = request.getSession();
		ArrayList<BoardDTO> list = dao.listDAO();
		session.setAttribute("list", list);
		session.setAttribute("count", dao.myList(dto));
	}
}
