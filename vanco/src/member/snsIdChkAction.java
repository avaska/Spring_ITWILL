package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class snsIdChkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String userId = request.getParameter("userId");
		// System.out.println("액션페이지로 넘어온 id값 :"+userId);
		
		//1. DB에 아이디 존재 여부 확인 후 결과값 리턴
		MemberDAO mdao = new MemberDAO();
		boolean idChk = mdao.snsLoginIdChk(userId);
		// System.out.println("액션페이지 체크값 :"+idChk);
		
		
		if(idChk==false){
			//3. id값이 없으면 false값을 ajax data로 리턴시킴			
			response.setContentType("text/html;charset=UTF-8");			
			out.print("noId");			
			return null;
			
			
		}else{
			MemberDTO mdto = mdao.getUserInfo(userId);
			String userNickname=mdto.getUserNickname();
			String userPhoto=mdto.getUserPhoto();
			/*String url = request.getParameter("url");*/
			//System.out.println("아이디있을때 이프안쪽 문장 작동함!");
			
			HttpSession session = request.getSession();
			session.setAttribute("userId",userId);
			session.setAttribute("userNickname",userNickname);
			session.setAttribute("userPhoto",userPhoto);		
		}
		forward = new ActionForward();
		forward.setRedirect(false);//리다이렉트 방식으로 포워딩
		forward.setPath("vanco.me");			
		return forward;
		
	} //execute 메소드 종료
}// 클래스 종료
