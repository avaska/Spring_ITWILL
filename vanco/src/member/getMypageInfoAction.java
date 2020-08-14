package member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import timeLine.timeLineDTO;

public class getMypageInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		
		// 내사진 다 가져 오기
		MemberDAO mdao = new MemberDAO();		
		ArrayList<timeLineDTO> myPic = mdao.getMyTimePic(userId);		
		request.setAttribute("myPic", myPic);
		
		
		
		ActionForward forward=new ActionForward();		
		forward.setPath("./mypage/mypage.jsp");		
		return forward;
	}

}
