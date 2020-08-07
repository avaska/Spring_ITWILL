package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// (일반클래스가) 컨트롤러 사용할 수 있도록 준비
@Controller
public class SampleController2 {

	//해당 컨트롤러의 정보를 로그로 출력하기 위한 준비(== sysout 콘솔출력)
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController2.class); //static 메서드 호출
	
	// [/doC] 주소를 통해서 처리하는 메서드 doC() 구현
	// 도착 정보를 로그로 출력	
	@RequestMapping("/doC")
	public String doC(@ModelAttribute("msg") String msg, @ModelAttribute("age") int age) {
		// @ModelAttribute("이름") : 자동으로 해당 데이터를 뷰페이지까지 이동
		// 넘길 값이 2개 이상이면 콤마로 구분
		//전달되는 정보를 String변수에 담아서 key값으로 전달
		
		//컨트롤러에서 넘긴 데이터의 타입을 그대로 전달할 수 있다.
		
		// 주소창에 주소 요청 (http://localhost:8090/test/doC?msg=hello)
		// -> 요청한 컨트롤러로(doC) 연결됨
		// -> 전달할 정보(msg)가 @ModelAttribute 어노테이션에 의해 key값으로 저장
		// -> 주소창에 요청된 쿼리스트링을 request에 바인딩하여 view 페이지로 전달
		// -> view 페이지에서 전달된 값 출력 : ${msg}
		
		
		logger.info(" /doC 주소를 통해서 doC() 메서드 호출");
		
		return "itwill"; //리턴타입이 있으면,  '리턴하는 문자열.jsp'로 이동
	}
	// * 메서드의 리턴타입이 String일 경우 '리턴값.jsp' view 호출
	//	  컨트롤러를 거쳐서 view로 가야 페이지가 출력된다.
	//	 http://localhost:8090/test/doC  	   (출력O)
	//	 http://localhost:8090/test/itwill.jsp (출력X)
	
	
	//   ' /doC1 ' 주소를 통해서 itwill.jsp (view페이지) 이동	
	@RequestMapping("/doC1")
	public String doC1() {
		
		logger.info(" /doC1 주소 호출 -> itwill.jsp 이동");
		
		return "itwill"; //servlet-context.xml에서 .jsp로 매핑해줌
	}
	//코드를 작성하는 중에 서버에서 실시간 동기화를 하기 때문에 콘솔창에 에러메세지가 뜬다.
	//테스트하기 전에 서버를 강제로 재시작하는 습관을 가지는 것이 좋다.
	

	
	
	
}
