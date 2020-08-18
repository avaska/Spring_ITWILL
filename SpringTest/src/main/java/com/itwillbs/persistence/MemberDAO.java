package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.MemberVO;

//persistence(영속) 계층 : DAO를 포괄하는 계층

// 인터페이스에서는 추상메서드로 기능만 정의
// -> 인터페이스를 구현하는 서브클래스를 만들어서 객체 간의 결합을 약화시켜 의존 주입
public interface MemberDAO {
	
	// 디비를 사용해서 현재 시간 정보 출력
	public String getTime(); //추상메서드 -> 인터페이스는 abstract 키워드 생략 가능
	
	
	// 회원 가입 처리 메서드
	public void insertMember(MemberVO VO);
	
	// ID에 해당하는 회원 정보 모두를 가져오는 메서드
	public MemberVO getMember(String id);
	
	// ID/PW를 사용해서 본인 회원 정보를 가져오는 메서드
	public MemberVO getMemberWithIdPw(String id, String pw);
	
	// 회원 정보 수정 처리 메서드
	public void updateMember(MemberVO vo);
	
	// 회원 정보 삭제 처리 메서드 (서비스로 처리결과를 리턴)
	public int deleteMember(MemberVO vo);
	
	// 회원 목록 처리 메서드
	public List<MemberVO> getMemberList();
	
	
	
	
	
}
