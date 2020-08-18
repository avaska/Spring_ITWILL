package com.itwillbs.service;

import java.util.List;

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

	@Override
	public MemberVO loginCheck(MemberVO vo) {
		
		System.out.println("@@@@ Service: controller에서 로그인 체크 호출");
		System.out.println("@@@@ Service: 호출시 로그인 체크 정보를 가지고옴");
		System.out.println("@@@@ Service: "+vo);
		
		// 로그인 체크 기능이 있는 DAO 객체로 이동 		
		MemberVO DBvo = 
				mdao.getMemberWithIdPw(vo.getUserid(), vo.getUserpw());
		
		System.out.println("@@@@ Service: DAO 처리 완료 후 정보 저장 완료");
		System.out.println("@@@@ Service: " + DBvo);
		System.out.println("@@@@ Service: + controller 페이지로 이동");		

		return DBvo;
	}

	@Override
	public MemberVO getMember(String id) {
		
		System.out.println("@@@@ service : controller -> service 객체 호출");
		System.out.println("@@@@ service : 회원 아이디 정보를 가지고와서 DAO 이동");
        // DAO 객체 안에 있는 회원 정보를 가져오는 메서드 호출
		
		MemberVO vo = mdao.getMember(id);

		System.out.println("@@@@ service : DAO 처리완료, 결과 리턴완료 ");
		System.out.println("@@@@ service : controller (/member/info) 이동");
		
		return vo;
	}

	@Override
	public void updateMember(MemberVO vo) {
		
		System.out.println("@@@@ service : 컨트롤러에서 수정동작 처리 호출");
		System.out.println("@@@@ service : 수정할 정보 가져옴");
		System.out.println("@@@@ service : vo ->"+vo);
		System.out.println("@@@@ service : DAO 객체를 호출해서 동작 처리");
		
		mdao.updateMember(vo);		
		
		System.out.println("@@@@ service : DAO 처리 완료");
		System.out.println("@@@@ service : Controller 페이지로 이동");		
		
	}

	@Override
	public void deleteMember(MemberVO vo) {		
		System.out.println("@@@@ service : controller -> service 호출 ");
		System.out.println("@@@@ service : 삭제할 회원정보 전달받음 ");
		System.out.println("@@@@ service : vo ->"+vo);
		System.out.println("@@@@ service : DAO 회원 삭제 처리 동작 메서드 호출");
		
		int num = mdao.deleteMember(vo);
		// 정상처리
		if(num == 1) {
			System.out.println("@@@@ service : 정상 처리 완료!");		
		}		
		// 비밀번호 오류
		else {
			System.out.println("@@@@ service : 정상 처리 불가(오류)!");
		}
		
		System.out.println("@@@@ service : DAO 처리완료 -> Controller 이동");
		
	}

	@Override
	public List<MemberVO> getMemberList() {
		
		System.out.println("@@@@service : controller -> service 호출");
		System.out.println("@@@@service : DAO 이동해서 회원 목록 전부를 가져오기");
		
		List<MemberVO> memberList = mdao.getMemberList();
		
		System.out.println("@@@@service : DAO 처리 완료! ");
		System.out.println("@@@@service : 전달 받은 목록 확인 -> " + memberList);
		System.out.println("@@@@service : 결과를 리턴해서 controller 페이지로 이동");	
		
		return memberList;
	}
	
	
	
	
	
	
	
	
	
	
	
}
