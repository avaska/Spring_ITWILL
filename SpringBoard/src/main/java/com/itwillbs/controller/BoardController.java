package com.itwillbs.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

// @RequestMapping("/board/*")
// board로 시작되는 모든 주소에대한 처리 
// -> 컨트롤러 클래스에  사용되는 RequestMapping의미는 컨트롤러 구분목적
// jsp model2 프로젝트에서 web.xml에 서블릿주소 맵핑하는 것과 같은 의미인 듯?

//일반클래스 -> 컨트롤러 역할 하게 하는 어노테이션
@Controller
@RequestMapping("/board/*")
public class BoardController {
	// 서비스 호출 -> DAO
	
	// 서비스 객체 주입(DI)
	@Inject
	private BoardService service;
	
    // 로그객체 
	private static final Logger logger
	  = LoggerFactory.getLogger(BoardController.class);
	
	
	// 글쓰기 동작 - 입력 -> view 페이지 이동
	// http://localhost:8080/board/register
	// method = {RequestMethod.GET,RequestMethod.POST}
	// -> 한번에 두가지 방식의 처리 가능
	@RequestMapping(value = "/register",method = RequestMethod.GET )
	public void registerGET() throws Exception {
		logger.info(" /board/register GET 호출 -> /board/register.jsp 페이지 이동"); 
		logger.info(" submit() 사용해서 정보를 전달 ");
		
	}
	
	// 글쓰기 동작 - 처리
	@RequestMapping(value = "/register",method = RequestMethod.POST )
	//public String registerPOST(BoardVO vo, Model model) throws Exception{
	public String registerPOST(BoardVO vo, Model model, RedirectAttributes rttr) throws Exception{
		// 메서드 전달인자는 상황에 따라서 get()/set() 역활을 처리함
		// jsp -> 컨트롤러 (정보전달) 스프링이 해당 객체를 생성후 정보를 자동 저장
		
		// Model 객체는 스프링에서 제공하는 데이터 전달 객체 
		// (키,값) 쌍으로 데이터 저장 => 전달받는 jsp페이지에서는 el표현식으로 출력
		
		logger.info("/register post 요청 ");
		logger.info("전달 데이터 : "+vo);
		
		// DB 글쓰기 동작 (서비스를 통해서 DAO이동)
		// DI 주입된 서비스 객체를 사용해서 글쓰기 동작
		
		service.regist(vo);
		
		logger.info("서비스 처리 완료(글 등록완료)");
		
		
		// 1. model 객체에 정보를 저장해서 페이지 이동
		//model.addAttribute("result", "SUCCESS!!");
        // get 방식(redirect) : 정보를 주소(URL)에 표시
		// post 방식 : 정보를 Body에 저장
		
		
		// 2. RedirectAttribute 객체 사용 페이지 이동
		rttr.addFlashAttribute("result", "SUCCESS@@"); 
		//addFlashAttribute : 1회성 데이터 전달 -> 페이지 새로고침하면 소멸
		
		/* 
		 * [데이터를 저장해서 View 이동객체]
		 * 
		 * Model 객체 - addAttribute()
		 * - (key,value)쌍으로 데이터 저장
		 * - get : 주소에 보임, post : 주소에 안보임
		 * - F5(새로고침) : 정보 유지
		 * 
		 * RedirectAttributes 객체 - addFlashAttribute()
		 * - (key,value)쌍으로 데이터 저장
		 * - get : 주소에 안보임, post : 주소에 안보임
		 * - F5(새로고침) : 정보를 1회만 사용 후 사라짐
		 * 
		 * 
		 * */
		
		
		//return "/board/success";
		// 페이지 이동 (글 목록페이지)
		// return "/board/listAll";
		// 페이지 이동(화면 전환-> 다른동작)
		
		 return "redirect:/board/listAll";
	}
	
	
	// 글목록 동작 (/board/listAll)
	// http://localhost:8080/board/listAll?result=SUCCESS%21%21
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public void listAllGET(@ModelAttribute("result") String result) throws Exception{

		// @ModelAttribute("result") String result
		// => model 객체에 있는 속성값을 가져오는 어노테이션
		// 변수에 저장 후  (변수값은 컨트롤러에서 사용)   <-- String result
		// -> 연결되어있는 view(jsp)에 전달 (el표현식 -> model 저장한 키값 호출)
		
		//@ModelAttribute("result") : 변수명이 영향O
		//String result : 어떤 변수명으로 하든 영향 X
		
		// http://localhost:8080/board/listAll?result=SUCCESS%21%21
		if(result.equals("SUCCESS!!")) {
			logger.info("글쓰기 성공후 -> 리스트 페이지 이동");
			logger.info(" /register POST -> /listAll GET (리다이렉트) ");
		}
		// http://localhost:8080/board/listAll
		else {
			logger.info(" 리스트 페이지로 이동 ");
			logger.info(" /listALL GET 호출 ");
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
