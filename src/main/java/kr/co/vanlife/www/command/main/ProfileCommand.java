package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.MemberDTO;

public class ProfileCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		HttpSession session = request.getSession();
		
		ArrayList<MemberDTO> memberList = dao.memberListDAO();
		session.setAttribute("memberList", memberList);
		
		dto.setId(request.getParameter("id"));
		session.setAttribute("profile", dao.getProfile(dto));
		
	}
}
