package neighborComm;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class insertThunderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String thunderInfoStr=request.getParameter("thunderInfoParam");
		
		JSONParser parser = new JSONParser();
		JSONObject thunderInfoObj=(JSONObject)parser.parse(thunderInfoStr);
		
		String thunderName =(String)thunderInfoObj.get("thunderName");
		String thunderPlace =thunderInfoObj.get("thunderPlace").toString();
		String thunderDateStr = (String)thunderInfoObj.get("thunderDate") + " " + (String)thunderInfoObj.get("thunderHour") + ":" + (String)thunderInfoObj.get("thunderMinute")+":00";
		String thunderPerson = (String)thunderInfoObj.get("thunderPerson");
		String userId =(String)thunderInfoObj.get("userId");
		String moimNum = (String)thunderInfoObj.get("moimNum");
		Timestamp makingTime=new Timestamp(System.currentTimeMillis());
		String userPhoto =(String)thunderInfoObj.get("userPhoto");
				
		//String형태의 모임일자를 date타입으로 바꾸기
		Timestamp thunderDate = Timestamp.valueOf(thunderDateStr);
			
		thunderDTO tdto = new thunderDTO();
		tdto.setThunderName(thunderName);
		tdto.setThunderPlace(thunderPlace);
		tdto.setThunderDate(thunderDate);
		tdto.setThunderPerson(thunderPerson);		
		tdto.setUserId(userId);
		tdto.setMoimNum(moimNum);
		tdto.setMakingTime(makingTime);
		tdto.setUserPhoto(userPhoto);
		
		
		neiBoardDAO ndao = new neiBoardDAO();
		ndao.insertThunder(tdto);
		
		// ajax�� ȣ���߱⶧���� forward��� ���� ���� �ٷ� ajax������ ���ϵ�.
		return null;
	}

}
