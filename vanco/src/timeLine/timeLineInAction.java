package timeLine;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class timeLineInAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String timeLineNum = request.getParameter("timeLineNum");
		
		
		// 글쓴이 정보(vancomember)와 타임라인 기본정보(timeline) 조인 해서 가져오기
		timeLineDAO tdao = new timeLineDAO();		
		timeLineDTO tdto = tdao.getTimeLineIn(timeLineNum);		
		request.setAttribute("tdto", tdto);
		
		
		// 업로드한 사진 가져오기
		timeLineDAO tdao1 = new timeLineDAO();		
		ArrayList<timeLineDTO> photoList = tdao1.getTimeInPhoto(timeLineNum);		
		request.setAttribute("photoList", photoList);
		
		// 댓글 가져오기
		timeLineDAO tdao2 = new timeLineDAO();		
		ArrayList<timeLineDTO> replyList = tdao2.getTimeInReply(timeLineNum);		
		request.setAttribute("replyList", replyList);

		ActionForward forward=new ActionForward();		
		forward.setPath("./timeLine/timeLineIn.jsp");		
		return forward;
	}

}
