package kr.co.vanlife.www;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.vanlife.www.command.Command;
import kr.co.vanlife.www.command.main.AddFriendCommand;
import kr.co.vanlife.www.command.main.BadCommand;
import kr.co.vanlife.www.command.main.DeleteCommand;
import kr.co.vanlife.www.command.main.DeleteFriendCommand;
import kr.co.vanlife.www.command.main.FriendCommand;
import kr.co.vanlife.www.command.main.FriendListCommand;
import kr.co.vanlife.www.command.main.GoodCommand;
import kr.co.vanlife.www.command.main.LoginOKCommand;
import kr.co.vanlife.www.command.main.MessageCommand;
import kr.co.vanlife.www.command.main.MessageReadCommand;
import kr.co.vanlife.www.command.main.MessageSendCommand;
import kr.co.vanlife.www.command.main.MessageWriteCommand;
import kr.co.vanlife.www.command.main.ModifyOKCommand;
import kr.co.vanlife.www.command.main.MyListCommand;
import kr.co.vanlife.www.command.main.MyMessageDeleteCommand;
import kr.co.vanlife.www.command.main.ViewCommand;
import kr.co.vanlife.www.command.main.ProfileCommand;
import kr.co.vanlife.www.command.main.ProfileEditCommand;
import kr.co.vanlife.www.command.main.ProfileUploadCommand;
import kr.co.vanlife.www.command.main.ReplyCommand;
import kr.co.vanlife.www.command.main.ReplyOKCommand;
import kr.co.vanlife.www.command.main.SearchCommand;
import kr.co.vanlife.www.command.main.WriteOKCommand;
import kr.co.vanlife.www.command.member.LoginCommand;
import kr.co.vanlife.www.command.member.MemberRegisterOKCommand;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		String commandName = request.getServletPath();
		String viewPage = null;
		Command cmd = null;
		boolean flag = true;
		//member 관리
		if(commandName.equals("/index.do")) {
			viewPage = "index.jsp";
		}else if(commandName.equals("/login/login.do")) {
			viewPage = "login.jsp";
		}else if(commandName.equals("/member/terms.do")) {
			viewPage = "terms.jsp";
		}else if(commandName.equals("/member/memberRegisterOK.do")) {
			cmd = new MemberRegisterOKCommand();
			cmd.excute(request, response);
			viewPage = "../login/login.jsp";
			flag = false;
		}else if(commandName.equals("/main/main.do")) {
			cmd = new LoginCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/mainOK.do")) {
			cmd = new LoginOKCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/logout.do")) {
			request.getSession().invalidate();
			viewPage = "../index.jsp";
		}else if(commandName.equals("/main/main_profile.do")) {
			cmd = new ProfileCommand();
			cmd.excute(request, response);
			viewPage = "main_profile.jsp";
		}else if(commandName.equals("/main/profileUpload.do")) {
			cmd = new ProfileUploadCommand();
			cmd.excute(request, response);
			viewPage = "main_profile.jsp";
		}else if(commandName.equals("/main/profileEdit.do")) {
			cmd = new ProfileEditCommand();
			cmd.excute(request, response);
			viewPage = "profileEdit.jsp";
		}// board 관리
		else if(commandName.equals("/main/writeOK.do")) {
			cmd = new WriteOKCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
//			flag = false;
		}else if(commandName.equals("/main/reply.do")) {
			cmd = new ReplyCommand();
			cmd.excute(request, response);
			viewPage = "main_reply.jsp";
		}else if(commandName.equals("/main/replyOK.do")) {
			cmd = new ReplyOKCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/good.do")) {
			cmd = new GoodCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/bad.do")) {
			cmd = new BadCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/addFriend.do")) {
			cmd = new AddFriendCommand();
			cmd.excute(request, response);
			viewPage = "main_friend.jsp";
		}else if(commandName.equals("/main/mainFriend.do")) {
			cmd = new FriendCommand();
			cmd.excute(request, response);
			viewPage = "main_friend.jsp";
		}else if(commandName.equals("/main/mainMessage.do")) {
			cmd = new MessageCommand();
			cmd.excute(request, response);
			viewPage = "main_message.jsp";
		}else if(commandName.equals("/main/mainSearch.do")) {
			cmd = new SearchCommand();
			cmd.excute(request, response);
			viewPage = "main_search.jsp";
		}else if(commandName.equals("/main/mainModify.do")) {
			cmd = new ViewCommand();
			cmd.excute(request, response);
			viewPage = "main_modify.jsp";
		}else if(commandName.equals("/main/mainModifyOK.do")) {
			cmd = new ModifyOKCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/mainDelete.do")) {
			cmd = new DeleteCommand();
			cmd.excute(request, response);
			viewPage = "main.jsp";
		}else if(commandName.equals("/main/deleteFriend.do")) {
			cmd = new DeleteFriendCommand();
			cmd.excute(request, response);
			viewPage = "main_friend.jsp";
		}else if(commandName.equals("/main/mainMyList.do")) {
			cmd = new MyListCommand();
			cmd.excute(request, response);
			viewPage = "main_myList.jsp";
		}else if(commandName.equals("/main/messageWrite.do")) {
			cmd = new MessageWriteCommand();
			cmd.excute(request, response);
			viewPage = "messageWrite.jsp";
		}else if(commandName.equals("/main/messageRead.do")) {
			cmd = new MessageReadCommand();
			cmd.excute(request, response);
			viewPage = "messageRead.jsp";
		}else if(commandName.equals("/main/myMessageRead.do")) {
			cmd = new MessageReadCommand();
			cmd.excute(request, response);
			viewPage = "myMessageRead.jsp";
		}else if(commandName.equals("/main/sendMessage.do")) {
			cmd = new MessageSendCommand();
			cmd.excute(request, response);
			viewPage = "messageSend.jsp";
		}else if(commandName.equals("/main/messageReply.do")) { //추후 수정 필요 답장 시 친구 id로 등록 되게
			cmd = new MessageWriteCommand();
			cmd.excute(request, response);
			viewPage = "messageWrite.jsp";
		}else if(commandName.equals("/main/mainFriendList.do")) { //추후 수정 필요 답장 시 친구 id로 등록 되게
			cmd = new FriendListCommand();
			cmd.excute(request, response);
			viewPage = "main_friendList.jsp";
		}else if(commandName.equals("/main/deleteMyMessage.do")) { //추후 수정 필요 답장 시 친구 id로 등록 되게
			cmd = new MyMessageDeleteCommand();
			cmd.excute(request, response);
			viewPage = "main_message.jsp";
		}
		
		if(flag) {
			response.sendRedirect(viewPage);
		}else {
			RequestDispatcher dsPatcher = request.getRequestDispatcher(viewPage);
			dsPatcher.forward(request, response);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
}
