package timeLine;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getMoreImgAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String timeLineNum=request.getParameter("timeLineNum");
		String photoCount=request.getParameter("photoCount");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		
		//0. DB
		timeLineDAO tdao = new timeLineDAO();		
		ArrayList<timeLineDTO> list = tdao.getMoreImg(timeLineNum, photoCount);
		
		
		
		//1. 
		JSONObject timeImgObj = new JSONObject();
		
		//2. json
		JSONArray jArray = new JSONArray();
		
		//3. for
		for(int i=0;i<list.size();i++){
			
			JSONObject tempObj = new JSONObject(); // �迭���� �����͸� ������ �� json������Ʈ
			tempObj.put("photoUrl", list.get(i).getPhotoUrl());		
			
		// 4. tempObj
		jArray.add(tempObj);
			
		} // for 종료
		
		
		timeImgObj.put("timeImgList",jArray);	
		
		
		// ajax�� ������ ������
		out.print(timeImgObj.toString());
		out.flush();		
		
		return null;
	}

}
