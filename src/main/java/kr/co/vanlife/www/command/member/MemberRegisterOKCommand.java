package kr.co.vanlife.www.command.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.MemberDTO;

public class MemberRegisterOKCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setBirthYear(Integer.parseInt(request.getParameter("birthYear")));
		dto.setBirthMonth(Integer.parseInt(request.getParameter("birthMonth")));
		dto.setBirthDate(Integer.parseInt(request.getParameter("birthDate")));
		dto.setGender(request.getParameter("gender"));
		dto.setEmail(request.getParameter("email"));
		dto.setMobile(request.getParameter("mobile"));
		dto.setProfile(request.getParameter("profile"));
		dto.setNickname(request.getParameter(""));
		dto.setPromise(request.getParameter(""));
		
		dao.memberRegisterDAO(dto);
	}
}
