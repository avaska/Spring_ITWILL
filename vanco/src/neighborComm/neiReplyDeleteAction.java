package neighborComm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class neiReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int replyNum = Integer.parseInt(request.getParameter("replyNum"));
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		
		// 자바스크립트 사용위한 프린트라이터 객체 생성 (항상 캐릭터 타입 설정이 객체보다 먼저 나와야 함!!!)
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward=null;
		
		// 댓글이 1개 이상 달리면 삭제 안되도록 처리하기(re_ref 개수가 1초과이고, re_lev=0이면 삭제 불가)
		neiBoardDAO ndao = new neiBoardDAO();
		int re_refChk=ndao.getRe_refCount(re_ref);
		
		if(re_refChk>1 && re_lev==0){
			out.println("<script>");
			out.println("alert('댓글이 달린 글은 삭제할 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");				
		}else{			
				// 댓글 1개만 있을때, 			
				// check 리턴받기 : 1(성공), 0(실패)
				int delCheck=ndao.delReply(replyNum);
				
				if(delCheck==1){							
				// DB저장된 댓글 갯수 리셋하기
				ndao.updateReplyCount(num);		
				
				forward = new ActionForward();
				forward.setRedirect(false);//리다이렉트 방식으로 포워딩
				forward.setPath("./neighborComm/neiBoardContent.jsp?num="+num+"&pageNum="+pageNum);
				
			}else{
				out.println("<script>");
				out.println("alert('삭제에 실패하였습니다.');");
				out.println("history.back();");
				out.println("</script>");	
			}
	}
		
		return forward;
	}

}
