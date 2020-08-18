package com.itwillbs.test;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

//@RequestMapping : 컨트롤러, 메서드에 모두 사용 가능

//http://localhost:8090/test/member/~~
// -> 해당 주소로 접근하는 파일은 모두 이 컨트롤러로 관리하겠다
@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	// 해당 컨트롤러의 정보를 로그로 출력하기 위한 준비
	private static final Logger logger
	  = LoggerFactory.getLogger(MemberController.class);
	
	//servle-context.xml이 root-context.xml과 연결되어 있기 때문에 서비스 계층의 정보를 가져올 수 있다
	// 서비스 계층 필요함 -> 의존 주입
	@Inject
	private MemberService service; //부모타입 인터페이스 타입의 레퍼런스여야 의존주입됨
	// 스프링이 서비스 객체를 대신 만들어서 주입해줌
	
	
	// 동작 구현
	// http://localhost:8090/test/testCon (x)
	// http://localhost:8090/test/member/testCon (o)
	@RequestMapping("/testCon")
	public void TestController() {
		logger.info("컨트롤러 테스트 중");
		logger.info("@@@@ Controller : /testCon 주소일 때 처리(테스트용 회원가입)");
		
		//회원 정보 생성(나중에는 View페이지에서 전달 받음 -> form태그 submit)
		MemberVO vo = new MemberVO();
		vo.setUserid("itwill4");
		vo.setUserpw("1234");
		vo.setUsername("사용자3");
		vo.setEmail("user3@google.com");		
		
		//서비스 계층에 있는 동작 중에서 회원가입 처리 호출
		//결과 확인
		service.insertMember(vo);
		
		logger.info("@@@@ Controller : 회원 정보 저장 완료!");		
		
	}
	
	// 회원 가입 정보를 입력 처리(GET) -> 화면(view)
	// value(속성) = 이동할 주소
	// http://localhost:8090/test/member/insert
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insertGET() throws Exception{
		
		logger.info(" 회원 가입 정보 입력창 (view) 호출");
		logger.info("/member/insert (get) -> views/member/insertForm.jsp 이동");
		//컨트롤러에 RequestMapping 어노테이션으로 /member/* 경로를 잡아놓았기 때문에 test/member/insert로 바로 이동 가능함
		
		
		return "/member/insertForm"; //이동할 주소
		//servlet-context.xml에 문자 앞뒤로 경로와 .jsp 붙도록 설정해놨기 때문에 위와 같이 입력하면 자동으로 적용
	}
	
	
	// 회원 가입 처리 동작(POST)	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) throws Exception{
		// 메서드 전달인자를 사용해서 페이지 이동 시 정보를 가져올 수 있음
		// 입력 받는 페이지(view - jsp)에서 전달되는 데이터의 이름을 
		// vo 객체의 변수명(DB의 컬럼명)으로 통일시켜서 이동시킨다.		
		
		
		// /member/insert (post) 방식 처리 - submit 동작
		logger.info("컨트롤러에서 /insert주소에 POST 방식");
		logger.info(vo + "");		
		
		
		// 회원가입 처리 메서드 호출(서비스 계층)
		service.insertMember(vo);		
		
		logger.info("회원 가입 완료!");
		
		
		// 페이지 이동(로그인 페이지로 이동 : 컨트롤러 -> view)
		//WEB-INF/views/member/login.jsp 로 이동
		return "redirect:/member/login"; 	
		//redirect -> 서버에 주소 직접 입력하는 것과 같은 결과
		
	}
	
	
	// 로그인 처리 (GET) method
	//http://localhost:8080/test/member/login(x)
	//http://localhost:8080/member/login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() throws Exception {
		
		logger.info("로그인 메서드 loginGET() 실행");
		logger.info("view 페이지로 연결(/member/login.jsp)");
		
		logger.info("/member/login 주소 입력(get) ->  /member/login.jsp로 이동");
		
		
		//WEB-INF/views/member/login.jsp
		return "/member/login";
	}
	//서버 다시 실행 : 페이지 컴파일 목적
	
	// 405 error : 'http 상태 코드' 검색
	// https://developer.mozilla.org/ko/docs/Web/HTTP/Status
	
	
	
	// login처리(POST)
	// http://localhost:8090/test/member/login (x)
	//http://localhost:8080/member/login (o)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(MemberVO vo, HttpSession session) throws Exception{
		// method에 매개변수를 정의만 했는데 값이 넘어오는 것은 스프링 프레임워크가 알아서 해주도록 구현이 되어있다고 함
		// 레퍼런스 봐야할듯?
		// login.jsp의 form태그에서 submit할 때 전달되는 parameter 값들의 name value와
		// MemberVO에 정의되어 있는 클래스변수의 값이 동일하면, 
		// MemberVO의 setter method를 통해서 
		// loginPOST() 함수의 매개변수로 받아오는 MemberVO 객체에 자동적으로 값을 넣어주도록 프레임워크에 구현이 되어있다고 함.
		
		
		
		logger.info("/member/login.jsp -> loginPOST() 호출(아이디, 비밀번호) ");
		// 1) 아이디,비밀번호 저장
		logger.info("전달 정보 : "+vo);
		// 2) 로그인여부 판별 -> service -> DB
		MemberVO DBvo = service.loginCheck(vo);
		logger.info("확인 결과 : "+DBvo);
		
		// 3) 로그인시 - 아이디값 (세션) -> main.jsp 이동
		//    로그인 실패 - /test/member/login 이동
		// DBvo객체가 null인지 아닌지 판별 
		if(DBvo == null) {
			logger.info("DBvo 값이 없습니다");
			return "redirect:/member/login";
		}
		
		// 로그인 성공		
		// 세션객체 사용
		// (login.jsp(get방식) 페이지에서 post방식으로 넘어올 때 JSP 내장객체를 가지고 온다.)
		session.setAttribute("userid", DBvo.getUserid());
		//session 내장객체 : jsp 페이지에서 쓸 수 있다		
		
		// 페이지 이동
		// /member/main		
		return "redirect:/member/main"; 
	}	
	
	
	// 메인페이지 (/member/main (get))
	// http://localhost:8080/test/member/main(x)
	// http://localhost:8080/member/main (o)
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainGET() throws Exception {
		logger.info("/member/main (get) -> /member/main.jsp");
		
		// 파라미터값 저장
		
		
		
	}


	// 로그아웃(/member/logout)
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception{
		
		logger.info("/member/logout  get  -> /member/main 이동 ");

		// 세션값을 초기화
		session.invalidate();
		// main 페이지로 이동	
		return "redirect:/member/main";
	}
	
	// 회원 정보 보기 /member/info
	// http://localhost:8080/member/info
	@RequestMapping(value="/info",method = RequestMethod.GET)
	public String infoGET(HttpSession session, Model model) throws Exception {
		
		logger.info("/member/info  get  ->  /member/info.jsp 이동");

		// 세션값 (id) 가져오기 
		String id = (String)session.getAttribute("userid");
		
		// 회원 정보를 가져오기 (service -> DAO -> Mysql)
		MemberVO vo = service.getMember(id);
		logger.info("찾아온 회원 정보 : " + vo);
		
		// 가져온 회원정보를 저장해서 -> view(.jsp) 이동
		// Model 객체 사용(컨트롤러->뷰 이동시 정보저장 공간)
		model.addAttribute("memberVO", vo);

		// 해당 jsp 페이지로 이동(/member/info.jsp)
		return "/member/info";
	}
	
	// 회원 정보 수정(/member/update)
	// http://localhost:8080/member/update
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateGET(HttpSession session, Model model) throws Exception{
							// Session 내장객체(jsp) 전달받음
		
		logger.info("updateGET() 호출");
		logger.info("/member/update  get -> /member/update.jsp 이동");
		
		// 세션 ID값 처리
		String id = (String) session.getAttribute("userid");
		
		// 서비스 -> DAO -> DB 		
		// 회원정보 모두를 가져오는 동작
		MemberVO vo = service.getMember(id);
		
		// 회원정보를 Model 객체에 담아서 view 페이지로 전달		
		model.addAttribute("memberVO", vo);
		
		// 페이지이동(void)
		// /member/update.jsp 페이지이동			
		
	}
	// 회원 정보 수정 처리    /member/update
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	//@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(MemberVO vo) {
		
		// /member/update.jsp 에서 입력받은 수정할 정보를 가져와서 
		// DB로 이동(service -> DAO -> Mapper -> MySQL)
		logger.info("/member/update.jsp  submit -> /member/update POST ");
		logger.info("vo(수정할 정보) : "+vo);
		
		// 서비스 객체에 있는 정보 수정 메서드 호출
		service.updateMember(vo);
		
		logger.info("service 처리 완료 (회원정보 수정 완료)");		
		
		// 수정완료 후 페이지 이동(main)		
		return "redirect:/member/main";		
	}
	
	// /member/delete
	// http://localhost:8080/member/delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET) //컨트롤러에 접근하는 주소
	public String deleteGET() throws Exception{  //만약에 발생할 예외를 대비하여 던지기		
		
		// /member/delete -> delete.jsp로 이동
		// deleteForm.jsp처럼 mapping정보와 다른 페이지로 이동하려면 String을 만들어 반환
		
		logger.info("/member/delete  get  -> /member/deleteForm.jsp");
		
		// /WEB-INF/views/member/deleteForm.jsp 페이지 이동
		//  -> servlet-context.xml에 접두사,접미사로 경로 및 파일명 설정정보가 연결되어 있다
		return "/member/deleteForm";
	}
	
	// /member/delete   post
	// 회원 탈퇴 처리 
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(MemberVO vo, HttpSession session, Model model) throws Exception {
		// RequestMapping된 함수의 매개변수에 parameter 타입을 넣으면 
		// request.getParameter() 역할을 스프링 프레임워크가 대신해준다.
		// 단, 받아올 수 있는 타입이어야한다.
		
		logger.info("/member/deleteForm.jsp -> /member/delete POST");
		
		// jsp 페이지에서 전달한(submit) 정보를 저장 (userid,userpw)
		// + session 내장객체의 정보를 저장
		logger.info("view에서 전달받은 정보 : "+vo);
		
		// DAO 이동해서 삭제  (service -> DAO -> mapper -> Mysql)
		
		// 의존 주입 받은 서비스 객체를 사용
		service.deleteMember(vo);		
		
        // 세션값 초기화 	
		session.invalidate();
		
		// 삭제 완료 시 메시지 역할
		// -> view 페이지로 데이터 전달(parameter)
		// -> http://localhost:8090/member/main?result=delOK 
		model.addAttribute("result", "delOK");	
		
		// 페이지 이동(main)		
		return "redirect:/member/main";
	}
	
	
	// /member/list
	// 회원 목록 처리 
	// http://localhost:8080/member/list
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String listGET(HttpSession session, Model model) throws Exception {
		
		logger.info(" /member/list  get -> /member/memberList.jsp 이동");
		
		// 세션값 (id) 사용해서 관리자 판별
		String id = (String)session.getAttribute("userid");
		
		if(id == null || !id.equals("admin")) {
			return "redirect:/member/login";
		}
		
		// 관리자일 때 서비스를 통해서 회원 목록을 전부 가져오기
		List<MemberVO> memberList = service.getMemberList();
		logger.info(memberList+""); //공백문자 이용해서 객체정보 출력
		
		// Model 객체 사용(컨트롤러 -> 뷰 데이터 이동 컨테이너)
		// 회원 정보를 모두 저장 후 /member/memberList.jsp 페이지에서 출력		
		model.addAttribute("memberList", memberList);		
		
		return "/member/memberList";
	}
	
	
	
	// 서버 동기화 도중에 페이지 요청 
	//  -> 동기화되는 시점에 문법이 완벽하지 않으면 exception 메시지가 발생
	//  -> 동기화 끝나고 요청하거나, 서버 꺼놓고 작업하면 문제X
	
	
	
	
	
}
