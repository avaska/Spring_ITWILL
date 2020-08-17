package member;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class snsUserJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 카카오에서 얻어온 정보 처리
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userNickname = request.getParameter("userNickname");	
		String userPhoto = request.getParameter("userPhoto");
		
		//액션 포워드 객체 생성
		ActionForward forward = new ActionForward();
		
		
		// 정보 dto에 저장
		MemberDTO mdto=new MemberDTO();
		
		mdto.setUserId(userId);
		mdto.setUserPwd(userPwd);
		mdto.setUserNickname(userNickname);
		mdto.setUserPhoto(userPhoto);
		
		// request에 담기지 않은 시간정보는 DTO의 setter 객체 메소드를 통해 추가 저장한다		
		mdto.setJoinDate(new Timestamp(System.currentTimeMillis()));
		
		// 로그인 DB처리
		MemberDAO mdao = new MemberDAO();
		int logResult = mdao.snsUserJoin(mdto);
		
		if(logResult==1){
			HttpSession session = request.getSession();
			session.setAttribute("userId",userId);
			session.setAttribute("userNickname",userNickname);
			session.setAttribute("userPhoto",userPhoto);
		}
		
		forward.setRedirect(false);				
		forward.setPath("./vanco.me");			
		return forward;
	}

}
