package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	// 사용자의 요구사항을 처리하는 비지니스 계층
	// => 컨트롤러 - DAO 연결
	// => 컨트롤러의 영속계층(DAO/persistence) 종속적인 상황을 방지 
	
	// 글 쓰기(regist)
	public void regist(BoardVO board) throws Exception;
	
	// 글 목록 가져오기
	public List<BoardVO> listAll() throws Exception;
	
	// 글 본문 열기(특정글 열기 - read)
	// Java 버전에 따라 int 타입으로 autoboxing되지 않는 문제가 생길 수 있으므로 Integer타입으로 지정
	public BoardVO read(Integer bno) throws Exception;
	
	// 글 삭제하기
	public void remove(Integer bno) throws Exception;
	
	// 글 수정하기
	public void modify(BoardVO vo) throws Exception;
	
	
}
