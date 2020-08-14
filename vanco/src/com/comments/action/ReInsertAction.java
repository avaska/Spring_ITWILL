package com.comments.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comments.db.Comments;
import com.comments.db.CommentsDAO;
import com.controller.action.CommandAction;

/*
 * comments ReInsert Action - ajax
 */

public class ReInsertAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		String content = request.getParameter("content");
		String idx2 = request.getParameter("idx");
		String reNum2 = request.getParameter("reNum");
		String writer = "손님";

		if (request.getParameter("writer") != null && !(request.getParameter("writer").trim().equals(""))
				&& !(request.getParameter("writer").trim().equals("null"))) {
			writer = request.getParameter("writer");
		}
		
		int idx = Integer.parseInt(idx2);
		int reNum = Integer.parseInt(reNum2);
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		Comments comments = new Comments();
		comments.setIdx(idx);
		comments.setReNum(reNum);
		comments.setContent(content);
		comments.setWriter(writer);
		comments.setMoimNum(moimNum);
		CommentsDAO.getInstance().reInsertComment(comments);
		System.out.println(content);
		
		
		return null;
	}
	
	
}
