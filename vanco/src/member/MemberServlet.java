package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@SuppressWarnings("serial")
public class MemberServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("두겟");}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doProcess(request,response);System.out.println("두포스트");}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("서블릿 작동");
		
		// 요청한 가상주소값 얻기(컨텍스트 패스포함주소-컨텍스트길이 = 순수 경로)
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		int contextPathLength= contextPath.length();
		String command = RequestURI.substring(contextPathLength);// 전체주소에서 요청주소만 잘라낸 결과
		System.out.println("1:"+RequestURI);
		System.out.println("2:"+contextPath);
		System.out.println("3:"+contextPathLength);
		System.out.println("command최종형태:"+command);
		
		//doProcess 전역변수 선언
		ActionForward forward = null;
		Action action=null;		
		
		// 요청주소(command)에 따라 작업 처리 시작하기
		
// 회원가입 메인 연결(userJoin.me)////////////////////////////////////////////////////////////////////
	if(command.equals("/userJoin.me")){
		//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./member/userJoin.jsp");  //이동할 페이지 경로 주소값 저장(회원가입 입력 페이지)
	
// 아이디 회원가입 연결////////////////////////////////////////////////////////////////////	
	}else if(command.equals("/idUserJoin.me")){
		forward=new ActionForward();
		forward.setRedirect(false);			
		forward.setPath("./member/idUserJoin.jsp");  //이동할 페이지 경로 주소값 저장(id회원가입 입력 페이지)
		
	}else if(command.equals("/userJoinAction.me")){
		// DB회원가입 처리를 위한  Action관련 객체 생성(비즈니스로직) : DB작업은 userJoinAction에서 실행
		action=new userJoinAction();
		
		try {
			forward=action.execute(request, response);
		} catch (Exception e) {
			System.out.println("userJoinAction.me서블릿 에러"+e);
		}	
		
// 메인페이지 연결(vacno.me)////////////////////////////////////////////////////////////////////	
	}else if(command.equals("/vanco.me")){		
		// 인덱스 접속 시 필요한 정보들 읽어오기
		action=new getIndexInfoAction();
		
		try {
			forward=action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

// 로그인페이지 연결(userLogin.me)////////////	
	}else if(command.equals("/userLogin.me")){
		String url = request.getParameter("url");
			
		forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/login.jsp?url="+url);
		
		// 로그인 작업 페이지 연결()////////////		
	}else if(command.equals("/userLoginAction.me")){
		// DB에 로그인 처리를 위한 Action관련 객체 생성(비즈니스 로직) : DB작업은 MemberLoginAction클래스를 호출하여 실행
		action=new userLoginAction();
		
		try {
			forward=action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	// 로그인 완료 시 url 연결 ////////////		
		}else if(command.equals("/loginOK.me")){
			
			
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("vanco.me");

	}else if(command.equals("/mypage.me")){
// 마이페이지 연결(./mypage.me)////////////	
		action=new getMypageInfoAction();
		
		try {
			forward=action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 로그아웃 연결
	else if(command.equals("/logout.me")){
		action=new logoutAction();
		try {
			forward=action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

// 신규가입자 정보등록 연결(./mandatoryInfo.me)////////////	
	}else if(command.equals("/mandatoryInfo.me")){
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./mypage/mandatoryInfo.jsp");
	
	// 신규가입자 정보등록 액션(./mandatoryInfoAction.me)////////////	
	}else if(command.equals("/mandatoryInfoAction.me")){
		action=new mandatoryInfoAction();		
		try {
			forward=action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

// 가입자 정보수정 액션(./mypageEdit.me)////////////			
	}else if(command.equals("/mypageEdit.me")){
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./mypage/mypageEdit.jsp");
		
	// 가입자 정보수정 액션(./editMypageAction.me)////////////	
	}else if(command.equals("/editMypageAction.me")){
		action=new editMypageAction();		
		try {
			forward=action.execute(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
// 마이페이지 사진 정보수정 액션(./editMyPhotoAction.me)////////////					
	}else if(command.equals("/editMyPhotoAction.me")){
		action=new editMyPhotoAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
		}

// 사용자 프로필 확인 페이지 이동(
	
	}else if(command.equals("/yourPage.me")){
		String yourId = request.getParameter("yourId");
		System.out.println(yourId);
		System.out.println(request.getContextPath());
		
		forward=new ActionForward();
		forward.setRedirect(false);		
		forward.setPath("./mypage/yourPage.jsp?yourId="+yourId);
		
// 카카오톡 회원가입 아이디 존재 여부 확인 및 id존재 시 로그인 처리. idChk		
	}else if(command.equals("/snsIdChk.me")){
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html, charset=utf-8");
						
		System.out.println("sns아이디체크 호출 됨.");	
		
		// 전송된 id로 DB에 ID존재 여부 확인 후 ajax기술을 이용하여 결과값을 리턴 한다.
		String userId = request.getParameter("userId");	
		System.out.println("서블릿으로 넘어온 Id값 :"+userId);
		
		
		action=new snsIdChkAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	//카카오톡 회원가입 액션 	
	}else if(command.equals("/snsUserJoinAction.me")){
		System.out.println("sns로그인 호출됨");
		/*
		String userId = request.getParameter("userId");			
		System.out.println("snsUserJoin 호출 id :"+userId);*/
		
		action=new snsUserJoinAction();		
		try {
			forward=action.execute(request, response);			
		} catch (Exception e) {
			System.out.println("snsUserJoinAction에러"+e);
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
