package neighborComm;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class getThunderListAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String moimNum = request.getParameter("moimNum");
		
		// ajax리턴을 위한 데이터 타입 설정 및 변수 준비
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
				
		
		neiBoardDAO ndao = new neiBoardDAO();
		ArrayList<thunderDTO> list = ndao.getThunderList(moimNum);
		
		// list에 받아온 번개 정보를 ajax 호출한 js파일로 리턴하기 위해 jaon배열객체에 저장하기
		
		//1. 최종 js파일에 리턴할 json오브젝트 생성(모든 번개 정보를 한곳에 담아 마지막에 out.print로 리턴시켜주기 위한 객체)
		JSONObject thunderFinalObj = new JSONObject();
		
		//2. json데이터를 꺼내어 담을 배열 생성 
		JSONArray jArray = new JSONArray();
		
		//3. for문으로 arraylist에서 데이터 꺼내기
		for(int i=0;i<list.size();i++){
			JSONObject tempObj = new JSONObject(); // �迭���� �����͸� ������ �� json������Ʈ
			tempObj.put("thunderNum", list.get(i).getThunderNum());
			tempObj.put("thunderName", list.get(i).getThunderName());
			tempObj.put("thunderPlace", list.get(i).getThunderPlace());
			tempObj.put("thunderPerson", list.get(i).getThunderPerson());
			tempObj.put("userId", list.get(i).getUserId());
			tempObj.put("userPhoto", list.get(i).getUserPhoto());	
			tempObj.put("thunderJoinCount", list.get(i).getThunderJoinCount());
			
			// ajax로 json 데이터를 리턴할때 데이터가 Timestamp타입이면 에러가 나므로 반드시 toString()해줄것!!!
			tempObj.put("makingTime", list.get(i).getMakingTime().toString());	
			tempObj.put("thunderDate", list.get(i).getThunderDate().toString());
			
			// 날짜 정보는 별도로 파싱하여 여러개의 필요한 정보로 변환
			Timestamp tempDate = list.get(i).getThunderDate();
			int parsingDate=tempDate.getDate(); // 파싱하여 날자 저장
			String parsingTime=""; // 파싱하여 시간 저장
			String parsingDay="";  // 파싱하여 요일 저장
			
			// 시간 쪼개기, 시간이 1자리 단위이면 앞에 강제로 0 붙임
			if(tempDate.getHours()<10){
				parsingTime = "0"+tempDate.getHours()+":";
			}else{
				parsingTime = tempDate.getHours()+":";
			}
			
			// 분 쪼개기, 분이 1자리 단위이면 앞에 강제로 0 붙임
			if(tempDate.getMinutes()<10){
				parsingTime += "0"+tempDate.getMinutes();
			}else{
				parsingTime += tempDate.getMinutes();
			}
			
			
			switch (tempDate.getDay()) {
			case 0:
				parsingDay = "일요일";
				break;
			case 1:
				parsingDay = "월요일";
				break;
			case 2:
				parsingDay = "화요일";
				break;
			case 3:
				parsingDay = "수요일";
				break;
			case 4:
				parsingDay = "목요일";
				break;
			case 5:
				parsingDay = "금요일";
				break;
			case 6:
				parsingDay = "토요일";
				break;
			default:
				break;
			} // ����ġ�� ����
			
			// 파싱한 날자정보를 tempObj에 추가 저장			
			tempObj.put("parsingDate", parsingDate);
			tempObj.put("parsingTime", parsingTime);
			tempObj.put("parsingDay", parsingDay);					
			
			// tempObj에 저장된 1개의 번개 리스트 모든 정보를 json배열인 (jArray)에 저장
			jArray.add(tempObj);
			
		} // for문 종료
		
		// for문을 통해 배열에 저장된 모든 정보를 json객체에 담아서 string으로 변환 후 자바스크립트의 ajax의 호출에 data값에 응답한다.	
		// ajax로 리턴을 위해서는 string타입으로 변환 후 리턴해야 데이터 손실이 없다 
		thunderFinalObj.put("allThunderList",jArray);					
		
		
		out.print(thunderFinalObj.toString());
		out.flush();
		
		// 서블릿으로 forward와 함께 리턴 되지 않음!(ajax로 리턴됨)
		return null;
	}

}
