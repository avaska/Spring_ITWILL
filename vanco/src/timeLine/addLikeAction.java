package timeLine;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String timeLineNum = request.getParameter("timeLineNum");
		String userId = request.getParameter("userId");		
		String likeId = request.getParameter("likeId");
		
		timeLineDAO tdao = new timeLineDAO();
		tdao.addLikeCount(timeLineNum, userId, likeId); 
		
				
		return null;
	}

}
