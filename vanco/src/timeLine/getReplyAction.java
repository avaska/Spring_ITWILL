package timeLine;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getReplyAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String timeLineNum = request.getParameter("timeLineNum");
		// ajax������ ���� ������ Ÿ�� ���� �� ���� �غ�
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		timeLineDAO tdao = new timeLineDAO();		
		ArrayList<timeLineDTO> list = tdao.getTimeReply(timeLineNum);	
		
		//1. ���� js���Ͽ� ������ json������Ʈ ����(��� ���� ������ �Ѱ��� ��� �������� out.print�� ���Ͻ����ֱ� ���� ��ü)
		JSONObject timeReObj = new JSONObject();
		
		//2. json�����͸� ������ ���� �迭 ���� 
		JSONArray jArray = new JSONArray();
		
		//3. for������ arraylist���� ������ ������
		for(int i=0;i<list.size();i++){
			
			JSONObject tempObj = new JSONObject(); // �迭���� �����͸� ������ �� json������Ʈ
			tempObj.put("timeLineReplyNum", list.get(i).getTimeLineReplyNum());
			tempObj.put("timeLineNum", list.get(i).getTimeLineNum());
			tempObj.put("userId", list.get(i).getUserId());
			tempObj.put("content", list.get(i).getContent());
			tempObj.put("writeTimeStr", list.get(i).getWriteTimeStr());
			tempObj.put("re_ref", list.get(i).getRe_ref());
			tempObj.put("re_lev", list.get(i).getRe_lev());
			tempObj.put("reOwnerNick", list.get(i).getReOwnerNick());
			tempObj.put("userPhoto", list.get(i).getUserPhoto());
			tempObj.put("userNickname", list.get(i).getUserNickname());
			
			jArray.add(tempObj);
			
		} // for�� ����
		
		
		timeReObj.put("replyList",jArray);	
		
		
		// ajax�� ������ ������
		out.print(timeReObj.toString());
		out.flush();		
		
		return null;
	}

}
