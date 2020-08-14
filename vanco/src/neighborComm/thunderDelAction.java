package neighborComm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class thunderDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
	 	int thunderNum=Integer.parseInt(request.getParameter("thunderNum"));
	 	int moimNum=Integer.parseInt(request.getParameter("moimNum"));
	 	
	 	neiBoardDAO ndao = new neiBoardDAO();
	 	int result = ndao.delThunder(thunderNum);
	 	
	 	
	 	// 자바스크립트 사용위한 프린트라이터 객체 생성 (항상 캐릭터 타입 설정이 객체보다 먼저 나와야 함!!!)
	 	response.setContentType("text/html;charset=UTF-8");
	 	PrintWriter out = response.getWriter();
	 		
	 	ActionForward forward=null;	
	 			
 		out.println("<script>");
 		out.println("alert('삭제되었습니다.');");
 		out.println("</script>");
 		
 		forward = new ActionForward();
 		forward.setRedirect(false);
 		forward.setPath("moimContent.ne?moimNum="+moimNum);
	 	
		return forward;
	}

}
