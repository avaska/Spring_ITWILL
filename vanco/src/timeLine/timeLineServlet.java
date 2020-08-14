package timeLine;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import neighborComm.neiBoardDeleteAction;


public class timeLineServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// 요청한 가상주소값 얻기(컨텍스트 패스포함주소-컨텍스트길이 = 순수 경로)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		int contextPathLength= contextPath.length();
		String command = RequestURI.substring(contextPathLength);// 전체주소에서 요청주소만 잘라낸 결과		
		
		System.out.println(command + "  연결주소 요청옴");
		
		//doProcess 전역변수 선언
		ActionForward forward = null;
		Action action=null;		
		
		// 요청주소(command)에 따라 작업 처리 시작하기
		
// 타임라인 연결(/timeLine.ti)////////////////////////////////////////////////////////////////////
	if(command.equals("/timeLine.ti")){
		//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
		forward=new ActionForward();
		forward.setRedirect(false);	
		forward.setPath("/timeLineAction.ti");  //이동할 페이지 경로 주소값 저장(회원가입 입력 페이지)
	
	}else if(command.equals("/timeLineAction.ti")){
	// 타임라인 요청시 바로 정보 가져오는 액션
		action=new timeLineAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/addLikeAction.ti")){
		// 좋아요 더하기 액션
			action=new addLikeAction();		
			try {
				forward=action.execute(request, response);			
			} catch (Exception e) {
				e.printStackTrace();	
			}
			
	}else if(command.equals("/delLikeAction.ti")){
		// 좋아요 삭제 액션
		action=new delLikeAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getLikeAction.ti")){
		// 좋아요 카운팅
		action=new getLikeAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/likeClickCheckAction.ti")){
		// 좋아요 클릭 여부 체크
		action=new likeClickCheckAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}if(command.equals("/timeLineWrite.ti")){		
		// 타임라인 사진 올리기 페이지 이동
		forward=new ActionForward();
		forward.setRedirect(false);	
		forward.setPath("./timeLine/timeLineWrite.jsp"); 
	
	}else if(command.equals("/timeLineWriteAction.ti")){
		// 타임라인 글쓰기(사진 올리기)
		action=new timeLineWriteAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/insertTimeReplyAction.ti")){
		// 타임라인 댓글 쓰기
		action=new insertTimeReplyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/insertTimeReReplyAction.ti")){
		// 타임라인 대댓글 쓰기
		action=new insertTimeReReplyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getReplyAction.ti")){
		// 타임라인 댓글 리스트 받기
		action=new getReplyAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getReplyCountAction.ti")){
		// 타임라인 댓글수 세기
		action=new getReplyCountAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/timeLineInAction.ti")){
		// 타임라인 인기사진 클릭 시 들어가는 1회용 페이지
		action=new timeLineInAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getReplyInAction.ti")){
		// 타임라인 인기사진 클릭 시 들어가는 1회용 페이지 댓글 불러오기
		action=new getReplyInAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getTimeLineInfoAction.ti")){
		// 타임라인 기본 info 불러오기(자바스크립트)
		action=new getTimeLineInfoAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getTimeImgAction.ti")){
		// 타임라인 기본 사진 1장 불러오기(자바스크립트)
		action=new getTimeImgAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
	}else if(command.equals("/getMoreImgAction.ti")){
		// 타임라인 나머지 사진 불러오기(자바스크립트)
		action=new getMoreImgAction();		
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