package com.itwillbs.test;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// @RunWith
// @ContextConfiguration
// => 스프링 테스트 처리

// + @WebAppConfiguration
// => 스프링 MVC 테스트 처리


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		//locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		//이 폴더 아래쪽에 있는 .xml파일을 모두 추가하겠다.
		// => servlet-context.xml도 가져다 쓰겠다.
		)
public class SampleControllerTest {

	// 테스트를 사용해서 컨트롤러를 구현 (was 없이 컨트롤러 실행)
	// 서버를 이용하지 않고 JUnit만으로 테스트
	// -> 현업에서 서버를 재부팅하면서 테스트할 수 없기 때문에 이런 방식을 이용함	
	
	private static final Logger logger =
			LoggerFactory.getLogger(SampleControllerTest.class);	
	
	// 객체 의존 주입
	// 사용중인 프로젝트 정보를 주입 받는다.
	@Inject	
	private WebApplicationContext wac; //웹프로젝트에 대한 정보를 처리하는 객체를 xml 설정파일로부터 받아와서 주입
	
	// MVC 구조 테스트 객체 : 가상의 응답, 요청을 처리하는 객체
	private MockMvc mockMvc;
	
	// @Test : 테스트할 것을 담아놓는 것
	// @Before : 테스트 전에 실행될 동작	
	
	@Before
	public void setUp() {		
		// 테스트 하기 전에 미리 필요한 객체/정보들을 준비하는 단계
		
		//테스트 전용 객체를 메서드를 통해 생성
		//MockMvcBuilders : 필요한 정보를 받아서 MockMvc 객체를 만드는 객체
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info(" MockMvc 객체가 생성 완료 ! MVC 테스트 준비 끝 ");		
	}	
	
	@Test
	public void testDoA() throws Exception{
		
		logger.info(" 테스트 시작 (MVC패턴 구조를 테스트) ");		
		
		// 해당 객체를 사용해서 GET방식으로 페이지 호출
		//mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
		mockMvc.perform(MockMvcRequestBuilders.get("/doBB"));
		
		logger.info(" 테스트 완료, 페이지 호출 완료");
		
		
	}
	
	
	
}
