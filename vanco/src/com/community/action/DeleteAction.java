package com.community.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;
import com.board.db.Files;
import com.comments.db.CommentsDAO;
import com.controller.action.CommandAction;

/*
 * board Delete Action
 */

public class DeleteAction implements CommandAction {

	@SuppressWarnings("deprecation")
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int moimNum = Integer.parseInt(request.getParameter("moimNum"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("moimNum", moimNum);
		
		// Board article = BoardDAO.getInstance().getArticle(map);
		ArrayList<Files> filesList = BoardDAO.getInstance().getFiles(map);
		
		for (int i = 0; i < filesList.size(); i++) {
			String filename = filesList.get(i).getFilename();
			String uploadFileName = request.getRealPath("/commUpload") + "/" + filename;
			File uploadfile = new File(uploadFileName);
			String uploadFileName2 = request.getRealPath("/commUpload") + "/tn_" + filename.substring(3);
			File uploadfile2 = new File(uploadFileName2);
			
			if (uploadfile.exists() && uploadfile.isFile()) {
				uploadfile.delete();
				BoardDAO.getInstance().deleteFile(filesList.get(i).getNum());
			}
			
			if (uploadfile2.exists() && uploadfile2.isFile()) {
				uploadfile2.delete();
			}
		}
		
		BoardDAO.getInstance().deleteArticle(map);
		CommentsDAO.getInstance().deleteComments(map);
		
		request.setAttribute("url", "board.bo?moimNum="+moimNum);

		return "redirect2.jsp";
	}

}
