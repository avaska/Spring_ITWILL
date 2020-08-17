package com.comments.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comments.db.Comments;
import com.comments.db.CommentsDAO;
import com.controller.action.CommandAction;

/*
 * comments Insert Action - ajax
 */

public class InsertAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		String content = request.getParameter("content");
		String idx2 = request.getParameter("idx");
		int idx = 0;
		try{
			
			idx = Integer.parseInt(idx2);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String writer = "손님";
		

		if (request.getParameter("writer") != null && !(request.getParameter("writer").trim().equals(""))
				&& !(request.getParameter("writer").trim().equals("null"))) {
			writer = request.getParameter("writer");
		}
		
		
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		Comments comments = new Comments();
		comments.setIdx(idx);
		comments.setContent(content);
		comments.setWriter(writer);
		comments.setMoimNum(moimNum);
		CommentsDAO.getInstance().insertComment(comments);
		System.out.println(content);
		
		return null;
	}
	
	
}
