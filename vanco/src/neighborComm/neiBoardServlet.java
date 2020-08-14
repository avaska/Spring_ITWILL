package neighborComm;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.editMyPhotoAction;


public class neiBoardServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// 요청한 가상주소값 얻기(컨텍스트 패스포함주소-컨텍스트길이 = 순수 경로)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		int contextPathLength= contextPath.length();
		String command = RequestURI.substring(contextPathLength);// 전체주소에서 요청주소만 잘라낸 결과
		
		System.out.println("command최종형태:"+command);
		
		//doProcess 전역변수 선언
		ActionForward forward = null;
		Action action=null;		
		
		// 요청주소(command)에 따라 작업 처리 시작하기
		
// 글 내용 연결(/neiBoardContent.ne)////////////////////////////////////////////////////////////////////
	if(command.equals("/neiBoardContent.ne")){		
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");

		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("/neighborComm/neiBoardContent.jsp?num="+num+"&pageNum="+pageNum);  
	
	}else if(command.equals("/neighborComm.ne")){	
	// 우리동네 연결
		forward=new ActionForward();
		forward.setRedirect(false);			
		forward.setPath("/neighborComm/neighborComm.jsp"); 
	
	}else if(command.equals("/neighborCommAction.ne")){	
		// 우리동네 연결 액션
		action=new neighborCommAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
			
			
	
		
		//Prev 넥스트,페이지숫자 버튼	
	}else if(command.equals("/neighborCommMove.ne")){
		String pageNum=request.getParameter("pageNum");
		forward=new ActionForward();
		forward.setRedirect(false);			
		forward.setPath("/neighborComm/neighborComm.jsp?pageNum="+pageNum);

// 글쓰기 버튼
	}else if(command.equals("/write.ne")){
		forward=new ActionForward();
		forward.setRedirect(false);			
		forward.setPath("./neighborComm/neiBoardWrite.jsp");
	
//글쓰기 액션 neiBoardWriteAction.
	}else if(command.equals("/neiBoardWriteAction.ne")){
		action=new neiBoardWriteAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}

//글삭제 액션 neiBoardDelete.ne
	}else if(command.equals("/neiBoardDelete.ne")){
		action=new neiBoardDeleteAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
//글 수정페이지 이동 neiBoardModify.ne
	}else if(command.equals("/neiBoardModify.ne")){
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");

		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/neiBoardModify.jsp?num="+num+"&pageNum="+pageNum); 
	//글 수정 액션페이지
	}else if(command.equals("/neiBoardModifyAction.ne")){
		action=new neiBoardModifyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}		
	
//리플삭제 액션 neiReplyDelete.ne
	}else if(command.equals("/neiReplyDelete.ne")){
		action=new neiReplyDeleteAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	
//댓글달기 액션 neiReplyAction.ne
	}else if(command.equals("/neiReplyAction.ne")){
		action=new neiReplyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
//대댓글달기 액션 neiReReplyAction.ne
	}else if(command.equals("/neiReReplyAction.ne")){
		action=new neiReReplyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	
//////////////////////여기서 부터는 소모임 관련 내용////////////////////////		
/////////////////// 소모임 콘텐츠 페이지 이동 		
	}else if(command.equals("/moimContent.ne")){
		String moimNum = request.getParameter("moimNum");
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimContent.jsp?moimNum="+moimNum); 
		
	// 소모임 게시판페이지 이동
	}else if(command.equals("/moimBoard.ne")){
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimContent.jsp?pageCall=moimBoard.jsp"); 
	
	// 소모임 info페이지 이동 	
	}else if(command.equals("/moimInfo.ne")){
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimContent.jsp?pageCall=moimInfo.jsp"); 

	}else if(command.equals("/moimMemberInfo.ne")){
	// 모임 회원정보 페이지
		String moimNum = request.getParameter("moimNum");
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("/neighborComm/moimContent.jsp?pageCall=moimMemberInfo.jsp");	
		
	}else if(command.equals("/moimMaking.ne")){
	// 모임 만들기 페이지 이동			
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimMaking.jsp"); 

		
	}else if(command.equals("/moimMakingAction.ne")){
	// 모임만들기 액션 페이지
		action=new moimMakingAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		
	}else if(command.equals("/insertThunderAction.ne")){
	// 모임내 벙개 만들기 액션
		action=new insertThunderAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		
	}else if(command.equals("/getThunderListAction.ne")){
	// 벙개 리스트 가져오기 액션
		action=new getThunderListAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/insertThunderUserAction.ne")){
	// 모임내 번개 참여 신청 액션(토글)
		action=new insertThunderUserAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/deleteThunderUserAction.ne")){
	// 모임내 번개 참여 삭제 액션(토글)
		action=new deleteThunderUserAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getThunderUserAction.ne")){
	// 번개 참여/삭제자 프로필사진 출력하는 액션
		action=new getThunderUserAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/moimModify.ne")){
	// 모임 정보 수정 페이지
		String moimNum = request.getParameter("moimNum");
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimModify.jsp?moimNum="+moimNum); 
		System.out.println(moimNum);
		
	}else if(command.equals("/moimModifyAction.ne")){
	// 번개 참여/삭제자 프로필사진 출력하는 액션
		action=new moimModifyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/thunderDelAction.ne")){
	// 번개 삭제하기
		action=new thunderDelAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/moimJoinAction.ne")){
	// 모임 가입하기
		action=new moimJoinAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/updateLevelAction.ne")){
	// 모임 가입하기
		action=new updateLevelAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/joinWaitDelAction.ne")){
	// 모임 승인 대기 삭제 액션
		action=new joinWaitDelAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/joinConfirmAction.ne")){
	// 모임 가입 승인 액션
		action=new joinConfirmAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/updateCautionAction.ne")){
	// 경고누적 액션
		action=new updateCautionAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/forcedMoimMemberDelAction.ne")){
	// 강제퇴장 액션
		action=new forcedMoimMemberDelAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/moimJoinCancelAction.ne")){
	// 강제퇴장 액션
		action=new moimJoinCancelAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/moimBoardContent.ne")){
	// 모임게시판 글 읽기 이동
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimContent.jsp?pageCall=moimBoardContent.jsp"); 
	
	}else if(command.equals("/moimBoardWrite.ne")){
	// 모임게시판 글 쓰기 이동
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./neighborComm/moimContent.jsp?pageCall=moimBoardWrite.jsp"); 
	}else if(command.equals("/getThunderJoinCountAction.ne")){
	//
		action=new getThunderJoinCountAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	// 공통사항 처리 (포워딩)////////////////////////////////////////////////////////////////////
	if(forward!=null){
		if(forward.isRedirect()){
			response.sendRedirect(forward.getPath());
		}else{//디스패치 방식으로 페이지 경로 노출 없이 포워딩
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);}
		
	}// 공통사항 if 종료		
} // doProcess() 종료	
} // 서블릿 종료