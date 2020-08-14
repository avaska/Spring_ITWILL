package com.community.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.Board;
import com.board.db.BoardDAO;
import com.controller.action.CommandAction;

/*
 * board insert Action
 */

public class InsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		///////////////////////////////////////////////////
		// 초기 받아?�� �? �??�� ???��
		///////////////////////////////////////////////////

		request.setCharacterEncoding("UTF-8");
		response.setContentType("contentType=text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		int count = 0;
		int moimNum = 0;
		int pin = 0;
		String title = request.getParameter("title"); // "?��목입?��?��.";
		String writer = request.getParameter("writer"); // "?��?��?��?���?";
		String content = request.getParameter("content");
		String[] files2 = request.getParameterValues("files");
		String regip = request.getRemoteAddr();

		if (request.getParameter("moimNum") != null && !(request.getParameter("moimNum").trim().equals(""))
				&& !(request.getParameter("moimNum").trim().equals("null"))) {
			moimNum = Integer.parseInt(request.getParameter("moimNum"));
		}

		if (request.getParameter("pin") != null && !(request.getParameter("pin").trim().equals(""))
				&& !(request.getParameter("pin").trim().equals("null"))) {
			if (request.getParameter("pin").equals("on")) {
				pin = 1;
			}
		}

		PrintWriter out = response.getWriter();

		///////////////////////////////////////////////////
		// ?��?��?�� �??��
		///////////////////////////////////////////////////

		boolean check = false;
		String checkMsg = "";

		if (title == "" || title == null) {
			checkMsg += "title?�� null?��?��?��. \\n";
			check = true;
		}

		if (writer == "" || writer == null) {
			checkMsg += "writer�? null?��?��?��. \\n";
			check = true;
		}

		if (content == "" || content == null) {
			checkMsg += "content�? null?��?��?��.";
			check = true;
		}

		if (check == true) {

			out.println("<script>alert('" + checkMsg + "');</script>");
			out.println("<script>window.history.go(-1);</script>");
			return null;

		} else {

			Board article = new Board();
			article.setContent(content);
			article.setCount(count);
			article.setRegip(regip);
			article.setTitle(title);
			article.setWriter(writer);
			article.setMoimNum(moimNum);
			article.setPin(pin);


			BoardDAO.getInstance().insertArticle(article);
			Map<String, Object> map = new HashMap<String, Object>();
			int idx = BoardDAO.getInstance().getArticleNum(moimNum);
			map.put("idx",idx);
			if (request.getParameterValues("files") != null) {
				int[] files = new int[files2.length];
				for (int i = 0; i < files2.length; i++) {
					
					files[i] = Integer.parseInt(files2[i]);
					map.put("file", files[i]);
					BoardDAO.getInstance().updateFiles(map);
					map.remove("file");
				}
			}		
			request.setAttribute("url", "board.bo?moimNum="+moimNum);
			return "redirect2.jsp";
		}
	}
}
