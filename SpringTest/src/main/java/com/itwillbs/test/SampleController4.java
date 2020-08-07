package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {
	// 해당 컨트롤러의 정보를 로그로 출력하기 위한 준비
	private static final Logger logger
	  = LoggerFactory.getLogger(SampleController4.class);
	
	// 페이지 이동
	//  Redirect 이동
	
	//http://localhost:8090/test/doE	
//	@RequestMapping("/doE") 
//	public String doE() {  
//	  logger.info(" /doE 주소를 사용해서 doE() 호출");
//	  
//	  //상대경로로만 주소 요청 -> 나중에 페이지 꼬일 수 있다. 
//	  //return "/doA"; //doE 메서드만 콘솔 출력 -> 제대로 페이지 이동 X
//	  
//	  //redirect 방식
//	  //return "redirect:/doA"; 
//	  	// doE, doA 메서드 모두 콘솔 출력 -> 페이지 이동이 되었다! 
//	  	// 컨트롤러를 거치지 않고 보여줌 -> 주소창이 doA로 바뀜
//	  
//	  //forward 방식 
//	  return "forward:/doA"; //컨트롤러를 거치고 보여줌 -> 주소창이 doE 그대로  
//	}
	 
	//////////////////////////////////////////////////////////////
	
	
	//서버 실행중인 상태에서 아래 주소 컨트롤키누르면 링크 걸림
	//http://localhost:8090/test/doE?abc=1234
//	@RequestMapping("/doE")
//	//public String doE() {	
//	public String doE( @ModelAttribute("abc") String tmp ) {		
//		
//		logger.info(" /doE 주소를 사용해서 doE() 메서드 호출");
//		
//		//return "/doA";
//		//return "redirect:/doA";
//		//return "forward:/doA";
//		return "redirect:/doF";
//	}
	
	
	//////////////////////////////////////////////////////////////
	
	//http://localhost:8090/test/doE
	@RequestMapping("/doE")	
	public String doE(RedirectAttributes rttr ) {		
		//RedirectAttributes => 주소줄에 URI에 보이지 않게 데이터 전달(파라미터값 입력하지 않아도 전달)
		
		logger.info(" /doE 주소를 사용해서 doE() 메서드 호출");
		
		//주소줄에 넘기는 파라미터값이 보여짐
		//새로고침해도 파라미터값 남아 있음
		//rttr.addAttribute("abc", "안녕하세요1234"); //한글데이터 깨짐(doE -> doF 인코딩방식이 달라서)
		rttr.addAttribute("abc", "abc1234");
		
		//주소줄에 파라미터값이 안 보임
		//휘발성(1회성)
		//화면 출력되고 난 후 새로고침(F5)하면 데이터 사라짐
		//게시판 본문 새로고침할 때 조회수 올라가는 것 방지할 때 사용
		//rttr.addFlashAttribute("abc", "Test1234");
		
		return "redirect:/doF";
	}
	
	
	
	///////////////////////////////////////////////////////////////////////
	// /doF 주소를 처리하는 메서드 doF()
	// /doE 페이지를 호출시 파라미터값 abc=1234 가지고 이동해오는 페이지
	
	// 주소 /doE?abc=1234 ->리다이렉트-> /doF (abc값 출력)
	
	// 1) 주소 /doE 입력시 파라미터값abc(1234) 가지고 호출
	// 2) 메서드에서 처리후 /doF 페이지로 리다이렉트 이동
	// 3) /doF에서 리다이렉트 이동후 전달받은 정보 출력
	
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("abc") String tmp) {	
					//데이터를 보내는 쪽, 받는 쪽 모두 데이터를 바인딩해줘야 함
		//리턴값이 void 타입이라서 doF.jsp로 연결된다.
		
		logger.info("/doF 주소를 사용해서 doF() 메서드 실행");	
		logger.info("전달값 : "+tmp);		
	}
	
	
	// 페이지 이동 (리다이렉트:주소도 변경, 페이지도 변경) + 데이터 가지고 이동
	
	// 1) doE -> doF
	// 주소 요청 : .../doE?abc=1234
	// 해당 컨트롤러가 반응 : doE(@ModelAttribute("abc") String tmp) 
	// redirect:/doF => .../doF?abc=1234 
	// 해당 컨트롤러가 반응 : doF(@ModelAttribute("abc") String tmp)
	// view 페이지에서 출력
	
	// 2) doE -> doF
	// 주소 요청 : .../doE
	// 컨트롤러 : doE(RedirectAttributes rttr)
	// rttr.addAttribute("abc",1234);
	// redirect:/doF  =>  .../doF?abc=1234
	// 컨트롤러 : doF(@ModelAttribute("abc") String tmp)
	// view 페이지 에서 출력
	
	
	// 3) doE -> doF
	// 주소요청 : .../doE
	// 컨트롤러 : doE(RedirectAttributes rttr)
	// rttr.addFlashAttribute("abc",1234);
	// redirect:/doF  =>  .../doF
	// 컨트롤러 : doF(@ModelAttribute("abc") String tmp)
	// view 페이지 에서 출력
	// + F5 (새로고침) 했을땐 데이터가 사라진다(1회성 속성값)

	// 컨트롤러에서 처리하는 메서드 전달인자로 RedirectAttributes 사용시
	// 1) addAttribute(이름,값) - 주소(URI) 표시 O , F5(새로고침) O(값이 유지)
	// 2) addFlashAttribute(이름,값) - 주소(URI) 표시X, F5 X(1회성 값->새로고침 시 값 사라짐)

	
	
}
