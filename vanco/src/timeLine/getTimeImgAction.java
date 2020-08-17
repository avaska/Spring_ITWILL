package timeLine;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getTimeImgAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String timeLineNum=request.getParameter("timeLineNum");
		
		System.out.println(timeLineNum+"fffffffff");
		
		// ajax리턴을 위한 데이터 타입 설정 및 변수 준비
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();		
		
		
		//0. DB에서 데이터 가져오기.
		timeLineDAO tdao = new timeLineDAO();		
		//ArrayList<timeLineDTO> list = tdao.getTimeImg(timeLineNum);
		
		//ArrayList<timeLineDTO> list = tdao.getTimeImg(timeLineNum);
		
		timeLineDTO tdto = tdao.getTimeImg(timeLineNum);
		
		
		
		//1. 최종 js파일에 리턴할 json오브젝트 생성(모든 번개 정보를 한곳에 담아 마지막에 out.print로 리턴시켜주기 위한 객체)
		JSONObject timeImgObj = new JSONObject();
		
		//2. json데이터를 꺼내어 담을 배열 생성 
		JSONArray jArray = new JSONArray();
		
		//3. for문으로 arraylist에서 데이터 꺼내기
		//for(int i=0;i<list.size();i++){
			
		JSONObject tempObj = new JSONObject(); // 배열내에 데이터를 가지고 들어갈 json오브젝트
		tempObj.put("photoCount", tdto.getPhotoCount());			
		tempObj.put("photoUrl", tdto.getPhotoUrl());
						
		// ajax로 json 데이터를 리턴할때 데이터가 Timestamp타입이면 에러가 나므로 반드시 toString()해줄것!!!
		//tempObj.put("makingTime", list.get(i).getMakingTime().toString());	
	
		// tempObj에 저장된 1개의 번개 리스트 모든 정보를 json배열인 (jArray)에 저장
		jArray.add(tempObj);
			
		//} // for문 종료
		
		// for문을 통해 배열에 저장된 모든 정보를 json객체에 담아서 string으로 변환 후 자바스크립트의 ajax의 호출에 data값에 응답한다.	
		// ajax로 리턴을 위해서는 string타입으로 변환 후 리턴해야 데이터 손실이 없다 
		timeImgObj.put("timeImgList",jArray);	
		
		
		// ajax로 데이터 보내기
		out.print(timeImgObj.toString());
		out.flush();		
		
		return null;
	}

}
