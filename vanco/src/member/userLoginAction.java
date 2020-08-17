package member;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
				
		//세션 저장을 위한 객체 생성
		HttpSession session = request.getSession();
		//포워드 객체 생성
		ActionForward forward = null;
		// 자바스크립트 사용위한 프린트라이터 객체 생성 (항상 캐릭터 타입 설정이 객체보다 먼저 나와야 함!!!)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// DAO 불러오기(객체 생성)
		MemberDAO mdao = new MemberDAO();
		int logResult = mdao.userLogin(userId, userPwd);

		// rs처리
		if(logResult==1){
			session.setAttribute("userId",userId);
			
			// 향후 사용하기 위해 Session값에 여러 사용자 정보 저장하기 (session에 id값은 로그인 시 받아옴)	
			ArrayList sessionList=null;	
			sessionList=mdao.getSessionInfo(userId);
			for(int i=0; i<sessionList.size();i++){
				MemberDTO mdto = (MemberDTO)sessionList.get(i);
				
				int userNum=mdto.getUserNum();
				
				String userNickname=mdto.getUserNickname();
				String userPhoto=mdto.getUserPhoto();
				
				
				session.setAttribute("userNickname",userNickname);
				session.setAttribute("userPhoto",userPhoto);
			}
			
			
			// 로그인 성공 처리
			
			forward = new ActionForward();
			forward.setRedirect(false);//리다이렉트 방식으로 포워딩
			forward.setPath("loginOK.me");

				
		}else if(logResult==0){
							
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호를 확인해 주세요');");
			out.println("history.back();");
			out.println("</script>");			
			return null;			
				
		}else{		
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호를 확인해 주세요');");
			out.println("history.back();");
			out.println("</script>");			
			return null;		
		}

		
		return forward;
	}

}
