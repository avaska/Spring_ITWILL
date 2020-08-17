package com.community.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.Board;
import com.board.db.BoardDAO;
import com.controller.action.CommandAction;

/*
 * board count Action
 */

public class CountAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("moimNum", moimNum);
		Board article = BoardDAO.getInstance().getArticle(map);
		
		String regip = request.getRemoteAddr();
		System.out.println(regip);
		System.out.println(moimNum);
		System.out.println(idx);
		System.out.println(article.getRegip());
		if(!regip.equals(article.getRegip())) {
			int count = article.getCount();
			article.setCount(count++);
			BoardDAO.getInstance().setArticleCount(article);
		}
		request.setAttribute("url", "content.bo?moimNum="+moimNum+"&idx="+idx);
		return "redirect2.jsp";

	}

}
