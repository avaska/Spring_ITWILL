package com.itwillbs.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

// @Service 어노테이션 추가
// BoardService 역할을 처리

@Service
public class BoardServiceImpl implements BoardService {
	
	// BoardDAO 객체 의존 주입(DI)
	@Inject
	private BoardDAO bdao;
	
	// 정보를 로그형태로 출력
	private static Logger logger 
	  = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	
	@Override
	public void regist(BoardVO board) throws Exception {
		// 컨트롤러 <-> 서비스 <-> DAO <-> MyBatis(Mysql)
		logger.info("컨트롤러에서 서비스 요청(regist)");
		logger.info("DAO 정보 전달해서 DB저장");
		
		bdao.create(board);
		
		logger.info(" 글쓰기 완료! 컨트롤러로 페이지 이동 ");		
		
	}

	
}
