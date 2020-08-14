package timeLine;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String timeLineNum = request.getParameter("timeLineNum");
				
		timeLineDAO tdao = new timeLineDAO();
		int likesCount = tdao.getLikeCount(timeLineNum); 
		
		// ajax리턴
		out.print(likesCount);
		out.flush();		
		
		return null;
	}

}
