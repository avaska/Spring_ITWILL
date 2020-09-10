package com.itwillbs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.SampleVO;

//1. pom.xml에서 자바버전 1.8, 스프링프레임워크 4.3.8, junit 4.12로 버전 변경
//2. 프로젝트 속성 - java build path - library에서 runtime server 추가, jre 1.8으로 변경
//3. project facets - dynamic web module 3.1버전, java 1.8버전으로 변경
//4. java compiler - 자바 1.8으로 변경
//5. deployment assembly - add - java build path entries - maven dependencies 추가
//6. 톰캣 서버 - modules - 프로젝트 경로를 root("/")로 변경

//7. jackson-databind 라이브러리 추가
//https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.11.0

//8. Advanced REST client 앱 추가(크롬 앱스토어)
//https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo/related?hl=ko


//스프링 4 버전부터 RestController를 별도로 지원
//(스프링 3 버전에서는 @RequestBody, @ResponseBody 어노테이션을 사용하면 컨트롤러에서 JSON 데이터를 주고받을 수 있음)

// @RestController : 해당 클래스(컨트롤러)가 Rest 데이터 처리 전용 컨트롤러
// => 클래스 안에 있는 모든 메서드에는 @ResponseBody가 추가되어 있음(생략 가능)
// @RequestMapping : 이 컨트롤러에 접근할 수 있는 주소를 지정

@RestController
@RequestMapping("/sample")
public class RestControllerSample {

	// * 일반 Controller와 달리 페이지 이동 X,
	// RestController는 데이터를 전송(전달)하는 목적
	
	// http://localhost:8070/controller/sample/hello (X)
	//http://localhost:8070/sample/hello
	// 문자데이터 처리
	@RequestMapping(value = "/hello")
	public String testHello() {
		
		//RestController는 아래 문자데이터를 전송 -> 이 데이터를 받는 페이지에서 가공해서 표현
		//일반 컨트롤러의 경우 Itwill Hello.jsp라는 이름의 view 페이지로 접근하게 함
		return "Itwill Hello";
	}
	
	// http://localhost:8070/sample/sendVO
	// JSON 타입 데이터 처리
	@RequestMapping("/sendVO")	
	public /* @ResponseBody */ SampleVO testSendVO() {
		//@ResponseBody 생략 가능
		
		SampleVO vo = new SampleVO();
		vo.setName("ITWILL");
		vo.setAge(20);
		vo.setTel("010-1234-5678");
		
		//Jackson Databind 라이브러리 추가하면 JSON 데이터를 확인 가능
		return vo;
	}
	

	
	
}
