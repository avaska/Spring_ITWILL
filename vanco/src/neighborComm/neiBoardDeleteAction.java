package neighborComm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class neiBoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// 자바스크립트 사용위한 프린트라이터 객체 생성 (항상 캐릭터 타입 설정이 객체보다 먼저 나와야 함!!!)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward=null;
		
		neiBoardDAO ndao = new neiBoardDAO();
		
		// check 리턴받기 : 1(성공), 0(실패)
		int delCheck=ndao.delContent(num);
		   
		if(delCheck==1){
			
		out.println("<script>");
		out.println("alert('삭제되었습니다.');");
		out.println("</script>");
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./neighborComm/neighborComm.jsp?pageNum="+pageNum);
				
	}else{
		out.println("<script>");
		out.println("alert('삭제에 실패하였습니다.');");
		out.println("history.back();");
		out.println("</script>");	
	}	
		return forward;
	}

}
