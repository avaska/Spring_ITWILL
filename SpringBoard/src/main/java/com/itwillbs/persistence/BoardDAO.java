package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	// 게시판 처리동작 선언 -> BoardDAOImpl 객체를 통한 구현
	
	// 글 쓰기(Create)
	public void create(BoardVO vo) throws Exception;
	
	// 글 읽기(Read)
	// 프레임워크를 쓰다보면 자바버전이 바뀔 수 있기 때문에 auto boxing,unboxing 유무를 알 수 없어 Integer 사용
	public BoardVO read(Integer bno) throws Exception;	
	
	// 글 수정(Update)
	public void update(BoardVO newVO) throws Exception;
	
	// 글 삭제(Delete)
	public void delete(Integer bno) throws Exception;	
	
	// 글 목록 가져오기(List)
	public List<BoardVO> listAll() throws Exception;
	
	
}
