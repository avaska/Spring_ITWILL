package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice : 해당클래스 컨트롤러에서 발생하는 모든 예외를 처리하겠다
// -> 그 동안 컨트롤러에 구현한 모든 메서드에서 예외를 던졌었는데(throws Exception) 그걸 이 곳에서 받아서 처리

@ControllerAdvice
public class CommonExceptionAdvice {

	// AOP - advice : 구현하고자 하는 동작의 실체(클래스)
	
	private static final Logger logger = 
			LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	// 예외 처리하는 동작 구현 => 메서드를 만들겠다
	//@ExceptionHandler(Exception.class)
	// 아래 메서드와 이 메서드 중 하나만 @ExceptionHandler 어노테이션 걸어서 실행해야 오류X
	public String common(Exception e) {
		
		logger.info(e.toString());
		
		//http://localhost:8070/board/read?bno=500
		// 현재 DB에 없는 글 번호를 입력하여 글 읽기 페이지로 이동하여 
		// 글 삭제 버튼 클릭 (http://localhost:8070/board/remove)
		// -> 예외처리 구현되어 있으면 빈 화면 출력
		// -> 에러 view 페이지 연결
		
		//특정 화면으로 페이지 전달
		// -> WEB-INF/views/error_common.jsp로 이동
		return "error_common";
		
	}
	
	// 예외 처리 시 정보 가지고 페이지 이동
	// * 주로 개발자가 테스트용	
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception e) { 
		//예외를 e라는 변수로 받아옴
		
		// ModelAndView : @ControllerAdvice 사용하는 클래스는
		// 전달 인자로 Exception 계열의 객체들만 사용 가능(view 페이지에 정보 전달 X)
		// 그래서 하나의 객체 안에 Model 데이터와 view 페이지 설정을 한 번에 처리하는 객체
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error_common"); // view 페이지 정보 저장 (root로 접근)
		            
		
		mav.addObject("e", e);
		
		return mav;		
	}
	
	
}
