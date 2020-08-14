package timeLine;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getTimeLineInfoAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		int startPage = Integer.parseInt(request.getParameter("startPage"));
		int pageCount = Integer.parseInt(request.getParameter("pageCount"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		//0. 
		timeLineDAO tdao = new timeLineDAO();		
		ArrayList<timeLineDTO> list = tdao.getTimeLineInfo(startPage, pageCount);		
		
		//1. 
		JSONObject timeReObj = new JSONObject();
		
		//2. 
		JSONArray jArray = new JSONArray();
		
		//3. for
		for(int i=0;i<list.size();i++){
			
			JSONObject tempObj = new JSONObject(); 
			tempObj.put("timeLineNum", list.get(i).getTimeLineNum());
			tempObj.put("title", list.get(i).getTitle());
			tempObj.put("content", list.get(i).getContent());
			tempObj.put("writeTimeStr", list.get(i).getWriteTimeStr());
			tempObj.put("userId", list.get(i).getUserId());
			tempObj.put("userPhoto", list.get(i).getUserPhoto());
			tempObj.put("userCity", list.get(i).getUserCity());
			tempObj.put("userDistrict", list.get(i).getUserDistrict());
			tempObj.put("userNickname", list.get(i).getUserNickname());
			tempObj.put("replyCount", list.get(i).getReplyCount());
			
			
			jArray.add(tempObj);
			
		} // for
		
		timeReObj.put("timeInfoList",jArray);	
		
		
		// ajax 리턴
		out.print(timeReObj.toString());
		out.flush();		
		
		return null;
	}

}
