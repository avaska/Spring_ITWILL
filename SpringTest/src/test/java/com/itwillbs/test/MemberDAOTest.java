package com.itwillbs.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;


// 스프링 테스트로 사용할 수 있도록 설정
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {

	// 컨트롤러/뷰에서 호출 역할
	
	//MemberDAO mdao = new MemberDAOImpl(); //강한 결합
	
	//DB 처리 객체 - 의존 주입(DI)
	@Inject
	private MemberDAO mdao; 
	
	// 테스트 동작
	// DB 시간 정보 호출 메서드	
	// @Test : 있어야지만 반드시 JUnit을 실행 가능!!	
	@Test
	public void testTime() throws Exception{
		// @Test 어노테이션이 붙었다는 것은 주입을 받은 단계
		System.out.println("DI를 사용해서 MemberDAO 생성 완료! ");
		
		System.out.println("DAO 동작 호출");
		String value = mdao.getTime();
		
		System.out.println("결과 : " + value);
		
		System.out.println("DAO 처리 완료!");
	}
	
	// 회원 가입 처리하는 동작(테스트)
	//@Test
	public void testInsertMember() throws Exception {
		// MemberDAO 회원가입 메서드 호출
		
		// 회원 객체 생성
		MemberVO kim = new MemberVO();
		
		//MySQL 테이블의 컬럼명과 VO클래스 변수명이 동일한지 확인!! 이거 틀려서 오류 났었음 
		
		//ID 컬럼에 PK 걸려 있으므로 매번 다른 ID로 만들어줘야함!!
		//kim.setUserid("admin");
		//kim.setUserid("itwillbs1");
		kim.setUserid("itwillbs2");
		kim.setUserpw("1234");
		kim.setUsername("관리자");
		kim.setEmail("admin@itwill.co.kr");
		
		System.out.println("-------------------------------------");
		
		System.out.println("회원 객체 생성 완료");
		
		System.out.println("DAO 객체 사용해서 회원가입 메서드 호출");
		
		//mdao.insertMember(kim);	
		//id pk 걸려있어서 중복이면 에러남 
		
		System.out.println("회원가입 테스트 완료!");
		
		
	}
	
	
	//@Test
	public void getMember() throws Exception {
		
		System.out.println("----------------------------------------------------");
		System.out.println("@@@@ TEST : 회원 정보 호출 실행 @@@@@");
		
		MemberVO vo = mdao.getMember("admin");
		
		System.out.println("@@@@ TEST : DAO 호출  완료 결과 확인");
		System.out.println(vo);
	}
	
	@Test
	public void getMemberWithIDPW() throws Exception{
		
		System.out.println("------------------------------------------------------");
		System.out.println("@@@@ TEST : ID, PW 사용 본인정보 호출");
		System.out.println("@@@@ TEST : DAO 객체 사용 접근(의존 주입)");
		System.out.println("@@@@ TEST : DAO 안에 있는 처리 메서드  호출");
		
		//MemberVO vo = mdao.getMemberWithIdPw("admin", "1234");
		MemberVO vo = mdao.getMemberWithIdPw("admin", "12345678");
		
	
		System.out.println("@@@@ TEST : DAO에서 처리 완료 ! ");
		System.out.println("@@@@ TEST : 결과 확인 ");
		System.out.println(vo);
		
		if(vo == null)
			System.out.println("@@@@ TEST : ID 혹은 PW가 잘못되었습니다.");
		
		
	}
		
	
	
}
