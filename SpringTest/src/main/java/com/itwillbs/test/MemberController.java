package com.itwillbs.test;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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
	
	
	
	// 동작 구현
	// http://localhost:8090/test/testCon (x)
	// http://localhost:8090/test/member/testCon (o)
	@RequestMapping("/testCon")
	public void TestController() {
		logger.info("컨트롤러 테스트 중");
		logger.info("@@@@ Controller : /testCon 주소일 때 처리(테스트용 회원가입)");
		
		//회원 정보 생성(나중에는 View페이지에서 전달 받음 -> form태그 submit)
		MemberVO vo = new MemberVO();
		vo.setUserid("itwill3");
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
	public void insertPOST(MemberVO vo) throws Exception{
		// 메서드 전달인자를 사용해서 페이지 이동 시 정보를 가져올 수 있음
		// 입력 받는 페이지(view - jsp)에서 전달되는 데이터의 이름을 
		// vo 객체의 변수명(DB의 컬럼명)으로 통일시켜서 이동시킨다.		
		
		
		// /member/insert (post) 방식 처리 - submit 동작
		logger.info("컨트롤러에서 /insert주소에 POST 방식");
		logger.info(vo + "");		
		
		
		// 회원가입 처리 메서드 호출(서비스 계층)
		service.insertMember(vo);		
		
		logger.info("회원 가입 완료!");
		
		
	}
	
	
	
}
