package kr.co.vanlife.www.command.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.MemberDTO;

public class ProfileEditCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		HttpSession session = request.getSession();
		session.setAttribute("profile", dao.getProfile(dto));
	}
}
