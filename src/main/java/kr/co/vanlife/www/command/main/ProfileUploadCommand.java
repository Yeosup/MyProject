package kr.co.vanlife.www.command.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.dao.FriendDAO;
import kr.co.vanlife.www.dao.MemberDAO;
import kr.co.vanlife.www.dto.FriendDTO;
import kr.co.vanlife.www.dto.MemberDTO;

public class ProfileUploadCommand implements Command {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO();
		FriendDAO daoM = FriendDAO.getFriendDAO();
		FriendDTO dtoM = new FriendDTO();
		MultipartRequest multi = null;
		int fileMaxSize = 10 * 1024 * 1024;
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		String file = "";
		String oriFile = "";
		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
			String name = multi.getParameter("id");
			System.out.println("이름 " + name);
			dto.setId(multi.getParameter("id"));
			dto.setNickname(multi.getParameter("nickname"));
			dto.setPromise(multi.getParameter("promise"));
			Enumeration<String> files = multi.getFileNames();		// 폼 요소 중 input 태그 속성이 file로 된 파라미터의 이름들을 반환
															// upload 된 파일이 없으면 비어있는 Enumeration을 반환
			while(files.hasMoreElements())
			{
				String str = files.nextElement();
				file = multi.getFilesystemName(str);			// 사용자가 지정해서 서버에 실제로 업로드된 파일명 반환
																// 파일명이 중복되는 경우 변경된 파일명 반환
				oriFile = multi.getOriginalFileName(str);		// 사용자가 업로드한 실제 파일명을 반환.
																// 이때의 파일명은 파일 중복을 고려한 파일명 변경 전의 이름을 말한다.
				System.out.println("저장된 경로 " + savePath + " 저장된 이름 " + file + " 원본이름 " + oriFile);
			}
			dao.profile(dto, file);
			dtoM.setId(multi.getParameter("id"));
			dtoM.setNickname(multi.getParameter("nickname"));
			dtoM.setPromise(multi.getParameter("promise"));
			daoM.friendProfile(dtoM, file);
			HttpSession session = request.getSession();
			session.setAttribute("profile", dao.getProfile(dto));
			// System.out.println(dto.getProfile()); // 디버깅
			
			ArrayList<MemberDTO> memberList = dao.memberListDAO();
			session.setAttribute("memberList", memberList);
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("main_profile.jsp");
			return;
		}
	}
}
