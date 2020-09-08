package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.persistence.BoardDAO;

//스프링 테스트용 설정
//@RunWith : 테스트용 파일을 만들겠다
//@ContextConfiguration : 테스트에 필요한 파일을 준비해서 설정하겠다
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class BoardDAOTest {
	// JUnit 사용
	// BoardDAO 연결상태 테스트
	
	// 정보를 로그형태로 출력
	private static Logger logger
	 	= LoggerFactory.getLogger(BoardDAOTest.class);
	
	// BoardDAO 객체 생성(X) -> 의존 주입(DI)
	// 스프링이 BoardDAO 클래스를 찾아가고,  
	// @Repository 어노테이션이 붙은 인터페이스를 상속한 subclass인 impl객체를 의존 주입
	@Inject
	private BoardDAO bdao;
	
//	@Test
	public void TestDAO() throws Exception {
		logger.info("BoardDAO 객체 : " + bdao);
	}
	
	//글쓰기 테스트
//	@Test
	public void CreateTest() throws Exception {
		logger.info("Test-CreateTest() 실행");
		
		logger.info("글 정보 생성");
		
		// 글정보 저장하는 객체 생성
		BoardVO vo = new BoardVO();
		
		// 데이터 추가
//		vo.setTitle("1번 테스트 글");
//		vo.setContent(" 안녕하세요 테스트입니다.");
//		vo.setWriter("관리자");
		
		vo.setTitle("2번 테스트 글");
		vo.setContent(" 안녕하세요 테스트입니다.");
		vo.setWriter("사용자 1");
		
		// DAO 객체 - 메서드 호출
		logger.info("DI 처리된 객체를 사용해서 메서드 호출");
		bdao.create(vo);
		logger.info("글쓰기 완료!");	
				
	}
	
	// 글 정보를 확인 동작 처리(글 번호)
	// -> 1번 글의 정보를 가져와서 확인
//	@Test
	public void ReadTest() throws Exception{
		
		// Test->DAOImpl->Mapper->DAOImpl->Test
		
		logger.info("DI 객체 (bdao) 사용 글읽어오는 메서드를 실행");

		// 1번 글의 정보를 가져오는 동작
		BoardVO vo = bdao.read(1);
		
		logger.info(vo.toString());
		//logger.info(vo+"");	
		
	}
	
	// 글 정보 수정하기
//	@Test
	public void UpdateTest() throws Exception{
		
		logger.info("Test-UpdateTest() 호출");
		logger.info("bdao 객체를 사용해서 update() 호출");
		
		BoardVO newVO = new BoardVO();
		newVO.setBno(3);
		newVO.setTitle("글 수정!");
		newVO.setContent("수정된 내용입니다.");
		newVO.setWriter("수정된 사용자3");
		
		bdao.update(newVO);
		
		logger.info(" 글정보 수정완료  ");
	}
	
	
	// 글 삭제하기
	//@Test
	public void DeleteTest() throws Exception{
		
		logger.info("Test-DeleteTest() 호출");
		
		bdao.delete(2);
		
		logger.info("Test-글 삭제 완료");		
	}
	
	// 글 목록 가져오기
	@Test
	public void ListAllTest() throws Exception{	
		
		logger.info("Test-ListAllTest() 호출");		
		
		//컬렉션은 toString() 메서드가 오버라이딩 되어 있음 ->요소 바로 확인 가능	
		List<BoardVO> boardList = bdao.listAll();	
		logger.info(boardList + ""); //toString() 출력	
		
		//content 컬럼이 text타입으로 지정되어 있어서
		//logger로 출력할 때 unread라고 표시됨
		
//		for(BoardVO vo : boardList) {			
//			logger.info(vo.toString() + "");
//		}
		
	}
	
	// 글목록 10개씩 처리 동작
	//@Test
	public void TestListPage() throws Exception{
		// test 파일 -> DAO -> Mapper -> DB
		
		// DI를 사용해서 DAO객체 호출
		List<BoardVO> boardList = bdao.listPage(2);
		
		for(BoardVO vo :boardList) {
			System.out.println(vo.getBno()+"----"+vo.getTitle());
		}
		
	}
	
	// 객체를 사용해서 페이징 처리 
	@Test
	public void TestListCri() throws Exception{
		// 페이징처리 객체 생성 초기화
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPageSize(5);
		
		// DAO이동후 정보를 처리 
		List<BoardVO> list = bdao.listPage(cri);
				
		// 정보 출력	
		for(BoardVO vo : list) {
			System.out.println(vo.getBno()+"-----"+vo.getTitle());
		}
		
	}

	
}
