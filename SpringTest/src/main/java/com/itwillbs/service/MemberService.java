package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	// 회원 가입 처리
	public void insertMember(MemberVO vo);
	
	// 로그인 처리
	public MemberVO loginCheck(MemberVO vo);
	
	
	
	
}
