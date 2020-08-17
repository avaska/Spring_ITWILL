package com.community.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.Board;
import com.board.db.BoardDAO;
import com.community.db.CommunityDAO;
import com.controller.action.CommandAction;

/*
 * board list Action
 */

public class BoardAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		
		String page2 = request.getParameter("page") == null ? "0" : request.getParameter("page");
		
		int page = Integer.parseInt(page2);	
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		ArrayList<Board> articleList = BoardDAO.getInstance().getArticleList(page,moimNum);
		String commName = CommunityDAO.getInstance().getCommName(moimNum);
		String commPhoto = CommunityDAO.getInstance().getCommPhoto(moimNum);
		request.setAttribute("commPhoto", commPhoto);
		request.setAttribute("commName", commName);
		request.setAttribute("array", articleList);
		request.setAttribute("page", page);

		request.setAttribute("url", "/community/commBoard.jsp?moimNum="+moimNum);
		
		return "/community/community.jsp";
	}

}
