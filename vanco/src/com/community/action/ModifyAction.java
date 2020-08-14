package com.community.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.Board;
import com.board.db.BoardDAO;
import com.board.db.Files;
import com.community.db.CommunityDAO;
import com.controller.action.CommandAction;

/*
 * board modify Action
 */

public class ModifyAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String idx2 = request.getParameter("idx");
		int idx = Integer.parseInt(idx2);
		
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("moimNum", moimNum);
		
		Board article = BoardDAO.getInstance().getArticle(map);
		ArrayList<Files> list = BoardDAO.getInstance().getFiles(map);
		String commName = CommunityDAO.getInstance().getCommName(moimNum);
		request.setAttribute("commName", commName);
		
		request.setAttribute("article", article);
		request.setAttribute("filesList", list);
		
		request.setAttribute("url", "/community/commBoardModify.jsp?moimNum="+moimNum+"&idx="+idx);
		
		return "/community/community.jsp";
	}

}
