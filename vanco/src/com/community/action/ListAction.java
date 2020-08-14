package com.community.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.community.db.Community;
import com.community.db.CommunityDAO;
import com.controller.action.CommandAction;

/*
 * community list action
 */

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String path = request.getContextPath();
		
		ArrayList<Community> list = CommunityDAO.getInstance().getBoards();
		request.setAttribute("array", list);
		request.setAttribute("url", "/community/boardList.jsp");
		
		return "/community/community.jsp";
		
		
	}

}
