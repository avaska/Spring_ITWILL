package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller : 일반 클래스가 어노테이션을 사용해서 컨트롤러 역할으로 사용

@Controller
public class SampleController1 {
	
	//System.out.print() : 리소스를 적지 않게 소모하므로 logger로 대체해서 테스트
	
	// 해당 파일에 로그를 작성하는 객체를 준비
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleController1.class);
	
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA() 메서드 호출 !!!!! "); //콘솔창 로그 확인
	}
	//views에 doA.jsp 파일 만들어야 함.
	//컨트롤러는 서버 필요(JUnit으로 테스트X)
	//주소창에 뒷부분 지우고 http://localhost:8090/test/doA  로 바꿔서 요청
	
	// doB() 생성해서 페이지 호출
	//호출하는 이름에 해당되는 jsp 페이지를 찾는다.
	@RequestMapping("/doBB")
	public void doB() {
		logger.info("doB() 메서드 호출 !!!!! ");
	}
	//주소창에 http://localhost:8090/test/doB   로 요청
	
	
	//ctrl + s로 저장하면 서버 동기화 -> 완료되면 페이지 요청해보기
	
	// * 컨트롤러 안에서 동작을 메서드로 구현
	// 메서드가 리턴타입이 void일 경우 해당 경로에 해당하는 jsp 페이지 호출
	// ex) http://localhost:8090/test/doBB
	//	   => doBB.jsp 페이지 호출
	
	// 맵핑되지않은 주소를 입력할 경우 아래 경고 메시지 출력
	//WARN : org.springframework.web.servlet.PageNotFound - No mapping found for HTTP request with URI [/test/doC] in DispatcherServlet with name 'appServlet'
	
	// doB1 주소 호출을 통해서
	// doB1() 메서드 구현 => doB1.jsp 페이지 호출
	
	
	
	
	
	
}
