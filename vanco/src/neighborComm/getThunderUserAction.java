package neighborComm;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getThunderUserAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		request.setCharacterEncoding("UTF-8");
		String moimNum = request.getParameter("moimNum");
		String thunderNum = request.getParameter("thunderNum");
		
		
		moimDAO mdao = new moimDAO();
		ArrayList<thunderUserDTO> list = mdao.getThunderUser(moimNum, thunderNum);
		
		// list에 받아온 번개 정보를 ajax 호출한 js파일로 리턴하기 위해 jaon배열객체에 저장하기
		
		//1. 최종 js파일에 리턴할 json오브젝트 생성(모든 번개 정보를 한곳에 담아 마지막에 out.print로 리턴시켜주기 위한 객체)
		JSONObject finalObj = new JSONObject();
		
		//2. json데이터를 꺼내어 담을 배열 생성 
		JSONArray jArray = new JSONArray();
		
		//3. for문으로 arraylist에서 데이터 꺼내어 json배열에 넣기
		for(int i=0;i<list.size();i++){
			
			JSONObject tempObj = new JSONObject(); // 배열내에 데이터를 가지고 들어갈 json오브젝트
			tempObj.put("userId", list.get(i).getUserId());
			tempObj.put("userPhoto", list.get(i).getUserPhoto());
			tempObj.put("userNickname", list.get(i).getUserNickname());		
			tempObj.put("thunderNum", list.get(i).getThunderNum());	
			
			// tempObj에 저장된 1개의 번개 리스트 모든 정보를 json배열인 (jArray)에 저장
			jArray.add(tempObj);
						
		} // for문 종료
		
		// for문을 통해 배열에 저장된 모든 정보를 json객체에 담아서 string으로 변환 후 자바스크립트의 ajax의 호출에 data값에 응답한다.	
		// ajax로 리턴을 위해서는 string타입으로 변환 후 리턴해야 데이터 손실이 없다 
		finalObj.put("allUser",jArray);				
		
		// ajax리턴을 위한 데이터 타입 설정 및 변수 준비
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(finalObj.toString());
		out.flush();
		
		// 서블릿으로 forward와 함께 리턴 되지 않음!(ajax로 리턴됨)
		return null;
	}

}
