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
import kr.co.vanlife.www.dto.MemberDTO;

public class WriteOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getBoardDAO();
		BoardDTO dto = new BoardDTO();
		dto.setId(((MemberDTO)request.getSession().getAttribute("userInfo")).getId());
		dto.setContents(request.getParameter("contents"));
		dao.writeOKDAO(dto);
		
		HttpSession session = request.getSession();
		ArrayList<BoardDTO> list = dao.listDAO();
		session.setAttribute("list", list);
		
		session.setAttribute("count", dao.myList(dto));
		
		ArrayList<BoardDTO> originList = dao.originListDAO();
		session.setAttribute("originList", originList);
	}
}
