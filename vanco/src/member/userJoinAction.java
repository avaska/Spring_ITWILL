package member;

import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		
		// 1. 회원가입 페이지에서 입력한 정보 처리
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userNickname = request.getParameter("userNickname");	
		
		
		//액션 포워드 객체 생성
		ActionForward forward = new ActionForward();
		
		
		MemberDAO mdao =  new MemberDAO();
		int chk=mdao.idCheck(userId);
		
		System.out.println("체크값 : "+chk);
		
		if(chk==0){
			// HTML 태그 사용을 위한 printwriter객체 선언
			response.setContentType("text/html;charset=utf-8"); 
			PrintWriter out = response.getWriter();
			
						
			out.println("<script type='text/javascript'>");
			out.println("alert('이미 가입된 아이디입니다. 새로운 아이디를 이용해 주세요');");
			out.println("history.back();");
			out.println("</script>");
			return null;
			
		}else{
		//2. request에 담아온 정보 DTO저장
			MemberDTO mdto=new MemberDTO();
			mdto.setUserId(userId);
			mdto.setUserPwd(request.getParameter("userPwd"));
			mdto.setUserNickname(userNickname);
			mdto.setUserGender(request.getParameter("userGender"));			

		// 3. request에 담기지 않은 시간정보는 DTO의 setter 객체 메소드를 통해 추가 저장한다		
			mdto.setJoinDate(new Timestamp(System.currentTimeMillis()));		
		
		// 6.idUserJoin() 메소드 호출(매개변수에 mdto 정보 전달) 및 로그인 결과값 리턴 받기	(사진경로로 리턴받음)	
			String userPhoto = mdao.idUserJoin(mdto);		
		 
		// 7. 리턴받은 로그인 결과값으로 세션 처리
			HttpSession session = request.getSession();
			session.setAttribute("userId",userId);
			session.setAttribute("userNickname",userNickname);
			session.setAttribute("userPhoto",userPhoto);
		} // id중복체크 if-else문 종료%>
		
		// 8. 회원가입 성공 시 페이지 이동방식여부(T/F),이동페이지주소값을 서블릿으로 리다이렉트 방식으로 반환시킨다.		
			//페이지 이동방식 선택(true=리다이렉트, false=디스패치(경로 노툴안함))
	
		forward.setRedirect(false);			
		forward.setPath("vanco.me");	
		return forward;
		
	} //execute 메소드 종료
}// 클래스 종료
