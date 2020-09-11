package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	// http://localhost:8070/sample/sendList
	// 컬렉션 정보 전달하기(List, Map)
	@RequestMapping(value = "/sendList")
	public List<SampleVO> sendList() {
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			vo.setAge(i * 5);
			vo.setName("사용자" + i);
			vo.setTel("010-1234-456" + i);
			
			list.add(vo);
		}		
		return list;		
	}
	
	// http://localhost:8070/sample/sendMap
	//map
	@RequestMapping(value = "/sendMap")
	public Map<Integer, SampleVO> sendMap(){
		
		Map<Integer, SampleVO> map = new HashMap<Integer, SampleVO>();
		
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			vo.setAge(i * 5);
			vo.setName("사용자" + i);
			vo.setTel("010-1234-456" + i);
			
			map.put(i, vo);
		}	
		
		return map;
	}
	
	
	
	// RestController 사용해서 데이터만 전달 
	// => 문제발생을 관리 X (어떤 문제가 발생하는지 HTTP상태코드가 없어 확인할 수 없음)
	// => ResponseEntity 객체 : 결과 데이터 + Http 상태 코드  제어 클래스
	
	// http 상태 정보를 전달
	// http://localhost:8070/sample/sendError
	@RequestMapping(value="/sendError")
	public ResponseEntity<Void> sendError(){		
		
		// 크롬 ARC 앱으로 확인(REST 처리 동작 확인)
		
		//return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	// 데이터 + Http 상태 정보 전달
	// ResponseEntity<전달할 데이터 타입>
	@RequestMapping("/sendErrorList")
	public ResponseEntity<List<SampleVO>> sendErrorList(){
		
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=0;i<10;i++) {
			SampleVO vo = new SampleVO();
			vo.setAge(i * 5);
			vo.setName("사용자" + i);
			vo.setTel("010-1234-456" + i);
			
			list.add(vo);
		}		
		
//		ResponseEntity<List<SampleVO>> dataError = 
//				//new ResponseEntity<List<SampleVO>>(list, HttpStatus.OK);
//				new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
//		return dataError;
		
//		ResponseEntity<List<SampleVO>> dataError = null;
//		if(dataError == null) {
//			List<SampleVO> errorList = new ArrayList<SampleVO>();
//			new ResponseEntity<List<SampleVO>>(errorList,HttpStatus.NOT_FOUND);
//		}else {
//			new ResponseEntity<List<SampleVO>>(list,HttpStatus.OK);
//		}
//    	 return dataError;
		
		
		return new ResponseEntity<List<SampleVO>>(list,HttpStatus.OK);
	}	
	
	
	// 데이터 전달 방법 (method)
	
	// get/post 방식 사용 데이터 처리
	
	
	// https://docs.microsoft.com/ko-kr/azure/architecture/best-practices/api-design
	
	// GET/POST/DELETE/PUT/PATCH 방식 사용 데이터 처리 (REST)
	
	//	GET은 지정된 URI에서 리소스의 표현을 검색합니다. 응답 메시지의 본문은 요청된 리소스의 세부 정보를 포함하고 있습니다.
	//	POST는 지정된 URI에 새 리소스를 만듭니다. 요청 메시지의 본문은 새 리소스의 세부 정보를 제공합니다. 참고로 POST를 사용하여 실제로 리소스를 만들지 않는 작업을 트리거할 수도 있습니다.
	//	PUT은 지정된 URI에 리소스를 만들거나 대체합니다. 요청 메시지의 본문은 만들 또는 업데이트할 리소스를 지정합니다.
	//	PATCH는 리소스의 부분 업데이트를 수행합니다. 요청 본문은 리소스에 적용할 변경 내용을 지정합니다.
	//	DELETE는 지정된 URI의 리소스를 제거합니다.
	
	
	// * REST방식 주소 처리 원칙
	// 1) URI는 사용자가 원하는 리소스를 의미.
	// 2) 명사를 사용해서 주소 작성 (복수형)
	// 3) URI에 확인할 데이터값을 같이 가지고 이동
	
	// 주소는 같지만 전달되는 상태에 따라서 다르게 처리된다
	// URI : /boards/100 => 100번 글 조회  (HttpMethod -GET/DELETE)
	// URI : /boards/100/replies/10 => 100번글 댓글 10번을 조회
	// URI : /boards/100/10 => 100번글 댓글 10번을 조회
	// URI : /boards/ => 신규 입력페이지 
	// (HttpMethod -POST/PUT(신규자료 수정 또는 등록,PATCH))
	
	//@RequestMapping(value = "/boards/100",method = RequestMethod.GET )
	@RequestMapping(value = "/boards/100",method = RequestMethod.DELETE)
	public void Test1() {
		
	}
	
     
	
	
	
}
