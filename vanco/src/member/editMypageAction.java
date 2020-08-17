package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class editMypageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession	session = request.getSession();

		// 자바스크립트 사용위한 프린트라이터 객체 생성 (항상 캐릭터 타입 설정이 객체보다 먼저 나와야 함!!!)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();	
		
		
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userNickname = request.getParameter("userNickname");
		String userPwd = request.getParameter("userPwd");
		int userBirth = Integer.parseInt(request.getParameter("userBirth"));
		String userCity = request.getParameter("userCity");
		String userDistrict = request.getParameter("userDistrict");
		String userComment = request.getParameter("userComment");
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setUserId(userId);
		mdto.setUserNickname(userNickname);
		mdto.setUserPwd(userPwd);
		mdto.setUserPosition(request.getParameter("userPosition"));
		mdto.setUserBirth(userBirth);
		mdto.setUserCity(userCity);
		mdto.setUserDistrict(userDistrict);
		mdto.setUserComment(userComment);
		
	
		MemberDAO mdao = new MemberDAO();		
		int result = mdao.editUserInfo(mdto);

		if(result==1){
			out.println("<script>");
			out.println("alert('회원정보가 수정 되었습니다');");
			out.println("location.href='./mypage.me'");
			out.println("</script>");	
			
			session.setAttribute("userId", userId);
			session.setAttribute("userNickname", userNickname);
			
			return null;
		}else{
			out.println("<script>");
			out.println("alert('회원정보 수정을 실패하였습니다.');");
			out.println("location.href='./mypage.me'");
			out.println("</script>");	
			
			return null;
		}
	
	}

}
