package com.community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.db.CommunityDAO;
import com.controller.action.CommandAction;

/*
 * board write Action
 */

public class WriteAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		//return "write.jsp?moimNum="+moimNum;
		request.setAttribute("moimNum", moimNum);
		String commName = CommunityDAO.getInstance().getCommName(moimNum);
		request.setAttribute("commName", commName);
		
		request.setAttribute("url", "/community/commBoardWrite.jsp");

		return "/community/community.jsp";
	}

}
