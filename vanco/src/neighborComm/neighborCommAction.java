package neighborComm;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

public class neighborCommAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Head에서 neiboard 클릭 시 neiBoard에서 지역 정보 가져오기, 미로그인 또는 정보 미등록시 접속거절 위한 페이지
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		request.setCharacterEncoding("utf-8");		
	 	String userId=request.getParameter("userId");
	 		 	
	 	MemberDAO mdao = new MemberDAO();	 	
	 	MemberDTO mdto = mdao.getUserInfo(userId);
	 	
	 	String userCity = mdto.getUserCity();		 		 	
	 	
	 	if(userCity.equals("미등록")){
	 		out.println("<script>");
			out.println("alert('지역정보 및 필수정보 등록 후 사용 가능합니다.');");
			out.println("location.href='mypage.me';");
			out.println("</script>");
			
			return null;			
	 	}
		 	
	 	
	 	ActionForward forward = new ActionForward();
		forward.setRedirect(true);//디스패치 방식으로 포워딩
		forward.setPath("./neighborComm.ne");
		
		return forward;
	 	
	}

}
