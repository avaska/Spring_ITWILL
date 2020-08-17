package dm;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dmSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward=null;

		// 쪽지 정보와 함께 hidden으로 보낸 정보들 가져 오기
		request.setCharacterEncoding("utf-8");
		String dmContent=request.getParameter("dmContent");
		String sendUserId=request.getParameter("sendUserId");
		String sendNickname=request.getParameter("sendNickname");
		String receiveUserId = request.getParameter("receiveUserId");
		String receiveNickname = request.getParameter("receiveNickname");

		//리다이렉트를 위한 url 변수 선언
			// yourPage에 쪽지 보내면 url을 보내지 않음(null)
			// 쪽지함에서 쪽지 보내면 url을 보냄
		String url = request.getParameter("url");
		// null이연 인덱스값이 -1로 반환되어 if문에서 구별 가능해져서 강제 url 생성 가능해짐
		int urlChk = url.indexOf("h");
		System.out.println("인덱스"+urlChk);		
		
		dmDTO ddto = new dmDTO();
		ddto.setDmContent(dmContent);
		ddto.setReceiveUserId(receiveUserId);
		ddto.setReceiveNickname(receiveNickname);
		ddto.setSendUserId(sendUserId);
		ddto.setSendNickname(sendNickname);
		
		// dto에 저장되지 않는 시스템 데이터 추가 저장 
		ddto.setWriteTime(new Timestamp(System.currentTimeMillis()));
		ddto.setIp(request.getRemoteAddr());
		
		dmDAO ddao= new dmDAO();
		int sendCheck=ddao.sendDm(ddto); 
			
			if(urlChk==0){	
				url="yourPage.me?yourId="+receiveUserId;
			}else{url="dmbox.dm";}
			
			if(sendCheck==1){
				out.println("<script>");
				out.println("alert('쪽지를 보냈습니다.');");
				out.println("location.href='"+url+"';");
				out.println("</script>");
				
				/*forward = new ActionForward();
				forward.setRedirect(false);//리다이렉트 방식으로 포워딩				
				forward.setPath(url);*/
				
			}else{
				out.println("<script>");
				out.println("alert('쪽지 보내기에 실패하였습니다. 다시한번 시도해 주세요.');");
				out.println("location.href='"+url+"';");				
				out.println("</script>");
			}
		return null;
	}

}
