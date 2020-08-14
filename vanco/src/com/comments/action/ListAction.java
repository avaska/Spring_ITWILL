package com.comments.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.comments.db.Comments;
import com.comments.db.CommentsDAO;
import com.controller.action.CommandAction;

/*
 * comments List Action - ajax
 */

public class ListAction implements CommandAction {

	@SuppressWarnings("unchecked")
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("contentType=text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("moimNum", moimNum);
		
		int page = 0;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int count = CommentsDAO.getInstance().getCommentsCount(map);
		
		ArrayList<Comments> commentsList = CommentsDAO.getInstance().getCommentsList(map, count-10);
		
		JSONObject obj = new JSONObject();
		
		JSONArray cArray = new JSONArray();
		
		for(int i=0; i<commentsList.size(); i++) {
			
			JSONObject cObject = new JSONObject();
			
			cObject.put("num", commentsList.get(i).getNum());
			cObject.put("moimNum", commentsList.get(i).getMoimNum());
			cObject.put("idx", commentsList.get(i).getIdx());
			cObject.put("writer", commentsList.get(i).getWriter());
			cObject.put("content", commentsList.get(i).getContent());
			cObject.put("regdate", commentsList.get(i).getRegdate().toString().substring(5, 16));
			cObject.put("reDep", commentsList.get(i).getReDep());
			cObject.put("reOdr", commentsList.get(i).getReOdr());
			if(commentsList.get(i).getReNum() > 0){
				map.put("reNum", commentsList.get(i).getReNum());
				String reUserName = CommentsDAO.getInstance().getCommentUser(map);
				cObject.put("reUserName", reUserName);
				map.remove("reNum");
			}
			cArray.add(cObject);
		}
		obj.put("comments", cArray);
		obj.put("page", page);
		
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		
		return null;
	}

}
