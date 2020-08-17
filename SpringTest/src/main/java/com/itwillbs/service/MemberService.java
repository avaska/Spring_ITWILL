package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	// 회원 가입 처리
	public void insertMember(MemberVO vo);
	
	// 로그인 처리
	public MemberVO loginCheck(MemberVO vo);
	
	// 회원정보를 가져오는 처리 (본인)
	public MemberVO getMember(String id);
	
	// 회원 정보 수정
	public void updateMember(MemberVO vo);
	
	
	
}
