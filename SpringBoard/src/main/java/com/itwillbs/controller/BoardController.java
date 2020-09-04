package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void listAllGET(@ModelAttribute("result") String result, Model model) throws Exception{

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
		
		// DB 글 목록을 가져와서 -> view(jsp) 전달 출력
		// model 객체 사용해서 전달
		// /board/listAll.jsp (표에 데이터 채우기)
		
		// 서비스에서 전달된 글 목록 확인(출력)
		List<BoardVO> boardList = service.listAll();
		
		//logger.info(boardList + "");
		
		// model 객체 사용 데이터 저장
		model.addAttribute("boardList", boardList);		
		
	}
	
	// 글 본문 보기(/board/read)
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void readGET(@RequestParam("bno") int bno, Model model) throws Exception{
		
		// @RequestParam("bno")
		// => request.getParameter() (문자열 타입) 처럼 작동하는 어노테이션
		// => 문자열, 숫자, 날짜 등의 형변환 가능 (view 페이지의 parameter type과 같은 타입으로 받아온다)
		
		//@RequestParam("bno") int bno
		// -> 받아온 parameter 값을 변수 bno에 담아줌
		
		
		logger.info(" /listAll -> /read GET 페이지 호출 ");
		logger.info("전달된 글 번호 : " + bno);
		
		// 글 번호에 해당하는 글 정보 모두를 가져오기
		// DB에 접근하기 위해 서비스 객체를 통한 접근 시도
		BoardVO vo = service.read(bno);
		logger.info(bno+"번 글 정보 :"+ vo);
		
		// 글 정보를 전달 받아서 view 페이지(/board/read.jsp)로 이동
		// Model 객체 사용 전달 
		model.addAttribute("boardVO", vo);		
		
	}
	
	// http://localhost:8090/board/remove
	// 글 삭제 동작
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception{
	//public String removePOST(int bno) throws Exception{
	//@RequestParam("bno") 어노테이션을 생략해도 가능하다.
		
		logger.info("/remove 호출(삭제 처리)" );
		
		// 삭제 -> 서비스 -> DAO -> DB 삭제
		// 삭제할 글 번호
		logger.info("삭제할 글 번호 : " + bno );
		
		// 서비스에 삭제 처리하는 메서드 호출
		service.remove(bno);
		
		// 삭제처리 확인값 사용(1회성)
		rttr.addFlashAttribute("result", "delOK");
		
		//페이지 이동
		return "redirect:/board/listAll";
	}
	
	
	// 글 수정하기 (modify) => (수정정보 입력+정보수정)
	// http://localhost:8090/board/modify?bno=54
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
		// @RequestParam("bno") : Request로부터 bno라는 이름의 파라미터 값을 받아옴
		// int bno : 받아온 파라미터를 bno라는 이름의 변수에 저장해서 사용하겠다
		
		// 메서드의 리턴 타입이 void : mapping값과 동일한 이름의 jsp 페이지로 이동 
		logger.info(" /board/modify -> /board/modify.jsp 이동 ");
		// 전달 받은 파라미터값 저장 bno
		logger.info(" 수정할 글 번호 : " + bno + " 번글 ");
		
		// DB에서 수정할 정보를 가지고 와야 함(Model 객체 사용)
		// 정보 저장 -> View 페이지로 이동	
		
		// DB에서 글 정보를 가져오기(글 번호) -> 서비스 기능 호출
		BoardVO vo = service.read(bno);
		logger.info("수정할 글 정보 : " + vo);
		
		// DB -> 컨트롤러 정보 전달 완료
		// 저장된 정보 가지고 View 페이지 이동
		model.addAttribute("boardVO", vo);
		
		//컨트롤러 메서드의 리턴 타입에 따라 어떤방식으로, 어디로 이동될지 결정됨
	}
	
	
	// http://localhost:8070/board/modify?bno=54
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(/* @RequestParam("boardVO") */ BoardVO vo, RedirectAttributes rttr) throws Exception{
		//객체타입을 지정해주면 프레임워크가 SET메서드를 통하여 자동적으로 받아온다
		
		logger.info("/modify (get) -> /modify (post) 호출");
		logger.info("vo : "+vo);
		
		// 수정할 정보를 받아서 (저장) -> 서비스 -> DAO -> Mapper
		service.modify(vo);
		
		rttr.addFlashAttribute("result", "modifyOK");
		
		//가상 주소로 redirect -> 실제 주소 list.jsp로 이동
		return "redirect:/board/listAll";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
