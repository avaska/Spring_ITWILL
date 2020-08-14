package com.community.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.db.BoardDAO;
import com.board.db.Files;
import com.controller.action.CommandAction;

/*
 * board fileDelete Action - ajax
 */

public class FileDeleteAction implements CommandAction {

	@SuppressWarnings("deprecation")
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		response.setCharacterEncoding("UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		Files myfile = BoardDAO.getInstance().downFile(num);
		String filename = myfile.getFilename();
		String uploadFileName = request.getRealPath("/commUpload")+"/"+filename;
		File uploadfile = new File(uploadFileName);
		String uploadFileName2 = request.getRealPath("/commUpload")+"/tn_"+filename.substring(3);
		File uploadfile2 = new File(uploadFileName2);
		if(uploadfile.exists() && uploadfile.isFile()) {
			uploadfile.delete();
			
		}
		if(uploadfile2.exists() && uploadfile2.isFile()) {
			uploadfile2.delete();
		}
		BoardDAO.getInstance().deleteFile(num);
		
		PrintWriter out = response.getWriter();
		out.write(filename);
		
		return null;
	}

}
