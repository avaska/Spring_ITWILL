package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;

//@Repository : BoardDAO 역할을 할 수 있도록 지정
// -> 스프링이 BoardDAO 인터페이스를 찾아갔을 때 이 어노테이션이 붙은 subclass가 그 역할을 할 수 있도록 하게 만들겠다.
// -> 이게 있어야 의존주입 가능

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	// BoardDAO 인터페이스와 약한결합을 사용 
	// => DB와 연결 실행 동작을 처리
	// => DAO -> MyBatis(XML) -> Mysql
	
	// 정보를 로그 형태로 출력
	private static Logger logger 
		= LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// DB 연결 객체(SqlSession) 의존 주입(DI) 
	// root-context.xml 파일에서 생성된 객체(bean)을 주입
	// root-context.xml의 bean태그에 정의된 객체를 스프링이 대신 만들어주고 받아옴
	@Inject
	private SqlSession session;
	
	// 매퍼를 구분하는 값 (boardMapper.xml값 그대로 사용)
	// -> 실제 경로X, 임의의 값
	// -> 구분자의 역할일 뿐임
	private static final String NAMESPACE 
		= "com.itwillbs.mapper.BoardMapper";
	
	
	@Override
	public void create(BoardVO vo) throws Exception {
		// DAO 구현 -> Mapper에 접근해서 사용되는 SQL 구문 실행
		logger.info("DAO - create() 실행");
		logger.info("SqlSession 객체 사용 접근");
		logger.info("boardMapper.xml 이동해서 해당 SQL 구문 실행");
		
		//namespace 안의 create라는 id를 가진 쿼리를 실행
		session.insert(NAMESPACE + ".create", vo);
		
		logger.info("SQL 구문 실행 완료! ");
		
		// 서비스 객체로 이동
		logger.info("테스트 객체로 이동 (서비스 계층 역할)");
		
	}


	@Override
	public BoardVO read(Integer bno) throws Exception {
		logger.info("DAO - read() 호출");
		logger.info("SqlSession 객체를 사용 메서드 호출");
		
		//mapper에서 resultType으로 지정한 타입의 객체를 반환해준다.
		BoardVO vo = session.selectOne(NAMESPACE + ".read", bno);
		
		logger.info("Mapper에서 SQL 구문 실행완료!");
		logger.info("결과를 BoardVO 타입으로 저장 리턴");	
		
		return vo;		
	}


	@Override
	public void update(BoardVO newVO) throws Exception {
		
		logger.info("DAO-update() 호출");
		logger.info("SqlSession 객체 사용 Mapper 접근");
		
		session.update(NAMESPACE+".update", newVO);
	
		logger.info("Mapper - 정보 수정 완료");
		logger.info("서비스 계층 이동(Test)");
		
	}


	@Override
	public void delete(Integer bno) throws Exception {
		
		// SqlSession 객체 사용해서 Mapper 이동
		// SQL 구문 실행(delete)
		logger.info("DAO-delete() 호출");
		
		session.delete(NAMESPACE+".delete", bno);
		
	    logger.info("DAO-mapper 처리 완료,SQL 구문실행완료");
		
	}	


	@Override
	public List<BoardVO> listAll() throws Exception {
		
		logger.info("DAO-listAll() 호출");
		// SqlSession 객체 사용 - selectList()
		// session.selectList(statement, parameter)
		List<BoardVO> boardList 
		  = session.selectList(NAMESPACE+".listAll");
		//프레임워크가 알아서 List 객체에 BoardVO들을 넣어서 가져옴

		logger.info("DAO-SQL 실행완료");
		
		return boardList;
	}


	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		
		System.out.println("DAO : listPage(page) 호출");
		System.out.println("Test를 통해서 sql 구문 확인");
		
		//잘못된 페이지 처리의 경우
		if(page <= 0) {
			page = 1;
		}
		
		// 0, 10, 20, 30,.....
		//페이지별로 보이는 개수 미리 지정
		page = (page -1) * 10; // 테이블에서 가져올 글 리스트의 첫번째 글 번호 설정(pageStart)
		
		return session.selectList(NAMESPACE+".listPage", page);
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {

		System.out.println("DAO : listPage(Criteria cri)");		
		
		// mapper로 Criteria 객체(cri 변수)를 넘겨주면
		// Criteria의 getPageStart(), getPageSize() 함수가 자동으로 호출되어 
		// 쿼리의 #{pageStart},#{pageSize}  부분에 값을 넘겨준다.
		return session.selectList(NAMESPACE+".listCri", cri);
	}
	
	
	
}
