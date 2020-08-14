package com.community.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.addon.Thumbnail;
import com.community.db.Community;
import com.community.db.CommunityDAO;
import com.controller.action.CommandAction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/*
 * community requestBoard Action
 */

public class RequestBoardAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		

		//1. 현재 실행중인 웹프로젝트 정보 컨텍스트 객체 생성후, 
		ServletContext ctx = request.getServletContext();
		
		//2. 컨텍스트 객체에 실제 업로드될 서버측 경로 넣어서 저장하기 
		String realPath	= ctx.getRealPath("/commUpload/title");
		System.out.println(realPath);
		
		int max = 1024*1024*10;
		
		MultipartRequest multi = new MultipartRequest(request,realPath,max,"utf-8",new DefaultFileRenamePolicy());
		String name = multi.getParameter("name");
		String category = multi.getParameter("category");
		String image = multi.getFilesystemName("commPhoto");
		
		File thumbImage = new File(realPath+"/tn_"+image);
		Thumbnail.resize(multi.getFile("commPhoto"), thumbImage, 100, Thumbnail.RATIO);
		File backImage = new File(realPath+"/bg_"+image);
		Thumbnail.resize(multi.getFile("commPhoto"), backImage, 1000, Thumbnail.RATIO);

		File uploadfile = multi.getFile("commPhoto");
		
		if(uploadfile.exists() && uploadfile.isFile()) {
			uploadfile.delete();
		}
		
		System.out.println(name);
		System.out.println(category);
		
		int count = CommunityDAO.getInstance().getCommCount();
		count += 1;
		
		Community com = new Community();
		com.setNum(count);
		com.setCategory(category);
		com.setName(name);
		com.setPhoto(image);
		CommunityDAO.getInstance().insertCommunity(com);
		
		request.setAttribute("url", "list.bo");
		return "redirect2.jsp";
	}

	
}
