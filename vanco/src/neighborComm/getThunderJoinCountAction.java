package neighborComm;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getThunderJoinCountAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String moimNum = request.getParameter("moimNum");
		String thunderNum = request.getParameter("thunderNum");

		moimDAO mdao = new moimDAO();
		ArrayList<thunderDTO> list = mdao.getThunderJoinCount(moimNum, thunderNum);
		
		JSONObject finalObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		for(int i=0;i<list.size();i++){
			JSONObject tempObj = new JSONObject(); 
			tempObj.put("thunderPerson", list.get(i).getThunderPerson());
			tempObj.put("thunderJoinCount", list.get(i).getThunderJoinCount());
			
			jArray.add(tempObj);
						
		} // for끝
		
		finalObj.put("count",jArray);		
		
		out.print(finalObj);
		out.flush();
		
		return null;
	}

}
