package neighborComm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class moimJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String moimNum = request.getParameter("moimNum");		
		String userId = request.getParameter("userId");		
		int maxQuota = Integer.parseInt(request.getParameter("maxQuota"));
		
		ActionForward forward = new ActionForward();
		
		moimDAO mdao = new moimDAO();
		int result = mdao.insertMoimMember(moimNum, userId, maxQuota);
		
		// result -1 : 강퇴회원 가입불가  //    0:정원초과  // 1:성공가입 
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		if(result==-1){
			out.print("<script>");
			out.print("alert('강제퇴장된 회원은 재가입이 불가능 합니다.');");
			out.print("location.href='neighborComm.ne';");
			out.print("</script>");
			forward=null;
		}else if(result==0){
			out.print("<script>");
			out.print("alert('가입정원이 초과되었습니다.');");
			out.print("location.href='moimContent.ne?moimNum="+moimNum+"';");
			out.print("</script>");		
			forward=null;
		}else{						
			forward.setRedirect(true); // false : 리다이렉트 방식으로 포워딩
			forward.setPath("./moimContent.ne?moimNum="+moimNum);
				
		}
		return forward;
	}

}
