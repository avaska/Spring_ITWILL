package neighborComm;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class neiReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
	 	int contentNum=Integer.parseInt(request.getParameter("contentNum"));
		int contentPageNum=Integer.parseInt(request.getParameter("contentPageNum"));
	 	
	 	String content=request.getParameter("content");
	 	String userId=request.getParameter("userId");
	 	String userNickname=request.getParameter("userNickname");
	 	String userPhoto=request.getParameter("userPhoto");
	 	int re_ref=Integer.parseInt(request.getParameter("re_ref"));
	 	int re_lev=Integer.parseInt(request.getParameter("re_lev"));
	 	int re_seq=Integer.parseInt(request.getParameter("re_seq"));


	 	neiBoardDTO ndto = new neiBoardDTO();
	 	ndto.setContentNum(contentNum);
	 	ndto.setContent(content);
	 	ndto.setUserId(userId);
	 	ndto.setUserNickname(userNickname);
	 	ndto.setUserPhoto(userPhoto);
	 	ndto.setRe_ref(re_ref);
	 	ndto.setRe_lev(re_lev);
	 	ndto.setRe_seq(re_seq);	
		
		// dto에 저장되지 않는 시스템 데이터 추가 저장 
		ndto.setWriteTime(new Timestamp(System.currentTimeMillis()));
		ndto.setIp(request.getRemoteAddr()); 	//request 객체 영역에는 ip, 사용자 브라우저 정보등이 다 들어있다
		
		// 입력받은 글 DB에 삽입
		neiBoardDAO ndao = new neiBoardDAO();
		ndao.insertNeiReply(ndto,contentNum);	
		
		//댓글 수 업데이트
		int num=contentNum;  //(댓글의 contentNum과, 원글의 num은 일치함.)
		ndao.updateReplyCount(num);
	
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//리다이렉트 방식으로 포워딩
		forward.setPath("./neighborComm/neiBoardContent.jsp?num="+contentNum+"&pageNum="+contentPageNum);
		
		return forward;
	}

}
