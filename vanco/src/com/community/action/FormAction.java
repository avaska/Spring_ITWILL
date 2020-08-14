package com.community.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.controller.action.CommandAction;

/*
 * board list Action
 */

public class FormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("url", "/community/boardRequest.jsp");
		
		return "/community/community.jsp";
	}

}
