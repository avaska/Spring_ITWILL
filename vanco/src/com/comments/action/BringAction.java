package com.comments.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comments.db.Comments;
import com.comments.db.CommentsDAO;
import com.controller.action.CommandAction;

/*
 * comments Bring Action - ajax
 */

public class BringAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("idx", idx);
		map.put("moimNum", moimNum);
		map.put("num", num);
		
		Comments comments = CommentsDAO.getInstance().bringComment(map);
		
		PrintWriter out = response.getWriter();
		out.print(comments.getContent());
		
		return null;
	}

}
