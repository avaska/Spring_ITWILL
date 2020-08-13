package com.itwillbs.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링이 해당파일을 MemberService객체로 인식하게 처리

@Service
public class MemberServiceImpl implements MemberService{
	
	// DB에 접근이 가능해야 함
	// MemberDAO 객체 생성 -> 객체 의존 주입
	@Inject
	private MemberDAO mdao;	
	
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("@@@@ Service : DAO 연결을 위해서 객체 주입");
		System.out.println("@@@@ Service : 회원 가입을 하기 위해서 DAO 처리 메서드 호출");
		
		mdao.insertMember(vo);		
		
		System.out.println("@@@@ Service : DAO 처리 완료 서비스 처리 끝");
		System.out.println("@@@@ Service : 컨트롤러 페이지 이동");		
	}
	
	
	
	
}
