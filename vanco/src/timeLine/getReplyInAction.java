package timeLine;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getReplyInAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String timeLineNum = request.getParameter("timeLineNum");
		
		// ajax리턴을 위한 데이터 타입 설정 및 변수 준비
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		timeLineDAO tdao = new timeLineDAO();		
		ArrayList<timeLineDTO> list = tdao.getTimeInReply(timeLineNum);	
		
		//1. 최종 js파일에 리턴할 json오브젝트 생성(모든 번개 정보를 한곳에 담아 마지막에 out.print로 리턴시켜주기 위한 객체)
		JSONObject timeReObj = new JSONObject();
		
		//2. json데이터를 꺼내어 담을 배열 생성 
		JSONArray jArray = new JSONArray();
		
		//3. for문으로 arraylist에서 데이터 꺼내기
		for(int i=0;i<list.size();i++){
			
			JSONObject tempObj = new JSONObject(); // 배열내에 데이터를 가지고 들어갈 json오브젝트
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
			
			// ajax로 json 데이터를 리턴할때 데이터가 Timestamp타입이면 에러가 나므로 반드시 toString()해줄것!!!
			//tempObj.put("makingTime", list.get(i).getMakingTime().toString());	
		
			// tempObj에 저장된 1개의 번개 리스트 모든 정보를 json배열인 (jArray)에 저장
			jArray.add(tempObj);
			
		} // for문 종료
		
		// for문을 통해 배열에 저장된 모든 정보를 json객체에 담아서 string으로 변환 후 자바스크립트의 ajax의 호출에 data값에 응답한다.	
		// ajax로 리턴을 위해서는 string타입으로 변환 후 리턴해야 데이터 손실이 없다 
		timeReObj.put("replyList",jArray);	
		
		
		// ajax로 데이터 보내기
		out.print(timeReObj.toString());
		out.flush();		
		
		return null;
	}

}
