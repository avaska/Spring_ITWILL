package timeLine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class insertTimeReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String timeLineNum = request.getParameter("timeLineNum");
		String content = request.getParameter("content");		
		String userId = request.getParameter("userId");
		
		timeLineDAO tdao = new timeLineDAO();
		tdao.insertTimeReply(timeLineNum, content, userId); 
				
		return null;
	}

}
