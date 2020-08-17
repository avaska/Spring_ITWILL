package timeLine;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class likeClickCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		String timeLineNum = request.getParameter("timeLineNum");	
		String likeId = request.getParameter("likeId");
		
		timeLineDAO tdao = new timeLineDAO();
		int clickCheck = tdao.getLikeClickId(timeLineNum, likeId); 
		
		// clickCheck = 0  : 좋아요 안누름  // 1=누름
		
		out.print(clickCheck);
		
				
		return null;
	}

}
