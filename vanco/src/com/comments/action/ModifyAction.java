package com.comments.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comments.db.Comments;
import com.comments.db.CommentsDAO;
import com.controller.action.CommandAction;

/*
 * comments Modify Action - ajax
 */

public class ModifyAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		String content = request.getParameter("content");
		int idx = Integer.parseInt(request.getParameter("idx"));
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		int num = Integer.parseInt(request.getParameter("num"));
		String writer = request.getParameter("writer");
		
		Comments comments = new Comments();
		comments.setIdx(idx);
		comments.setContent(content);
		comments.setWriter(writer);
		comments.setMoimNum(moimNum);
		comments.setNum(num);
		CommentsDAO.getInstance().modifyComment(comments);
		System.out.println(content);
		
		
		return null;
	}
	
	
}
