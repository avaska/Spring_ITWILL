package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.ITWILLBean;


@Controller
public class SampleController5 {

	// 해당 컨트롤러의 정보를 로그로 출력하기 위한 준비
	private static final Logger logger
	 = LoggerFactory.getLogger(SampleController5.class);	
	
	
	// 스프링의 특징 : JSON 데이터 처리가 쉬움 
	//jackson -> dependency 추가
	//https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.8.4
		
	//http://localhost:8090/test/doJson
	@RequestMapping("/doJson")
	public @ResponseBody ITWILLBean doJson() {
		
		// @ResponseBody : 일반 객체를 Json 타입으로 변경해서 리턴
		// * 상용 브라우저들은 Json 파서를 포함하고 있음.		
		
		logger.info("/doJson 페이지 요청으로 doJson() 호출");
		
		//Json 객체를 만들 데이터 생성
		ITWILLBean member = 
				new ITWILLBean("홍길동", "010-1234-1233", 11);
		
		return member; //객체 리턴
		
	}
	//외부 브라우저로 출력 시
	//크롬은 JSON Parser가 포함되어 있어서 화면에 json데이터가 바로 출력된다.	
	//JSON Parser : json을 해당페이지에서 잘라서 읽을 수 있는 프로그램
	
	//하지만 이클립스 내장브라우저로 실행하면 json parser가 포함되어 있지 않아서
	//최대한 할 수 있는 방법으로 json데이터를 다운로드할 수 있게 한다.
	
	//F12(개발자도구) - network탭 - f5(새로고침) - 머리글(headers) - 응답헤더에서 json인 것을 확인
	//Content-Type: application/json;charset=UTF-8
}
