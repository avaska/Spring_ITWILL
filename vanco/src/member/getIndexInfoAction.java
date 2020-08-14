package member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getIndexInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		// 오늘의 인기사진 가져오기
		indexDAO idao = new indexDAO();		
		ArrayList<indexDTO> todayPic = idao.getTodayPic();		
		request.setAttribute("todayPic", todayPic);
		
		// 위클리 인기사진 가져오기
		ArrayList<indexDTO> weeklyPic = idao.getWeeklyPic();		
		request.setAttribute("weeklyPic", weeklyPic);
				
		
		// 최근글 가져오기
		ArrayList<indexDTO> latestBoard = idao.getLatestBoard();		
		request.setAttribute("latestBoard", latestBoard);
		
		// 오늘의 인기글 가져오기
		ArrayList<indexDTO> todayBoard = idao.getTodayBoard();		
		request.setAttribute("todayBoard", todayBoard);
		
		// 커뮤니티 게시판 탑6
		ArrayList<indexDTO> commTop6 = idao.getcommTop6();		
		request.setAttribute("commTop6", commTop6);

		ActionForward forward=new ActionForward();		
		forward.setPath("/index.jsp");		
		return forward;
	}

}
