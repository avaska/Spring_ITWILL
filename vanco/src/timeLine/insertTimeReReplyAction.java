package timeLine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class insertTimeReReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String timeLineNum = request.getParameter("timeLineNum");
		String re_ref = request.getParameter("re_ref");
		String content = request.getParameter("content");		
		String userId = request.getParameter("userId");
		String reOwnerNick = request.getParameter("reOwnerNick");
		
		timeLineDAO tdao = new timeLineDAO();
		tdao.insertTimeReReply(timeLineNum, re_ref, content, userId, reOwnerNick); 
				
		return null;
	}

}
