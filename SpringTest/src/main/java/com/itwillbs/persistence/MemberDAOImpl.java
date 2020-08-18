package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 해당 클래스를 DAO로 사용하겠다. 스프링에 등록(root-context.xml 연결) 
// -> @Inject 어노테이션이 있는 코드를 실행하면 스프링이  @Repository 키워드가 있는 객체를 찾아서 injection

@Repository
public class MemberDAOImpl implements MemberDAO{
	// 인터페이스를 구현한 implement 객체

	// DAO(Data Access Object) 처리
	
	// DB 연결 (root-context.xml의 sqlSession bean객체를 의존 주입)
	@Inject
	private SqlSession sqlSession;
	// -> 디비 연결, 자원 해제, 쿼리 실행
	
	// mapper 주소 (바뀌지 않게 final로)
	private static final String namespace
		= "com.itwillbs.mapper.MemberMapper";
	// mapper주소는 내가 지정하는 이름이다.
	// memberMapper.xml에 있는 namespace와 같아야 한다.		
	
	// 실무에서 시스템콜(sysout 등) 사용하지 말 것 -> 속도 저하 원인
	// 대신  logger 사용	
	
	//추상메서드 구현
	@Override
	public String getTime() {		
		// sqlSession 객체를 사용해서 select 구문을 호출
		// memberMapper.xml 파일에 구문을 호출(namespace+".id값")
		
		System.out.println("MemberDAOImpl_getTime() 호출! ");
		
		System.out.println("sqlSession 객체를 주입 받는다! ");
		//sqlSession : DB연결정보 + mapper정보 가짐
		
		System.out.println("SQL 구문 실행하기 위해서 mapper로 이동");
		
		
		//sqlSession.selectOne() : 추상메서드, 제네릭타입(T) 리턴 -> 어떤 타입이든 가능		
		String tmp = sqlSession.selectOne(namespace + ".getTime");
		
		System.out.println("mapper에서 해당 sql구문 실행완료");
		System.out.println("호출했던 페이지 MemberDAOTest로 이동");
		return tmp;
		
		//return sqlSession.selectOne(namespace + ".getTime"); //select 쿼리의 결과물 return
	}

	@Override
	public void insertMember(MemberVO vo) {
		// 회원 가입 처리 - MemberMapper.xml 파일과 연결 처리
		
		System.out.println("DAOImpl : 회원 가입메서드 호출(회원정보가져옴)" + vo); //VO의 toString() 호출
		System.out.println(" MemberMapper.xml 호출 "); //sqlSession을 통해서 
		
		//sqlSession
		//mapper로 값 전달해서 쿼리구문 실행
		//sqlSession.insert(namespace + ".id", 가지고 갈 값)		
		sqlSession.insert(namespace + ".insertMember", vo);
		
		System.out.println("DAOImpl : 회원 저장 완료! Test 파일로 이동");
		
	}

	@Override
	public MemberVO getMember(String id) {
		
		//매개변수  id값을 가지고  mapper로 이동
		
		System.out.println("@@@@ DAO : TEST 파일에서 메서드 호출 @@@@");
		
		System.out.println("@@@@ DAO : MyBatis 사용 memberMapper로 이동 @@@@");
		
		//selectOne() : 가지고 올 데이터가 하나일 때 사용
		//com.itwillbs.mapper.MemberMapper.getMember
		MemberVO vo 
			= sqlSession.selectOne(namespace + ".getMember", id);
		
		System.out.println("@@@@ DAO : Mapper에서 SQL 구문 실행 완료");
		System.out.println("@@@@ DAO : 결과를 저장해서 TEST 페이지로 이동 ");		
		
		return vo;
	}

	@Override
	public MemberVO getMemberWithIdPw(String id, String pw) {
		// 회원 정보(ID/PW)에 해당하는 회원 정보 가져오기 메서드
		
		System.out.println("@@@@ DAO : TEST에서 메서드 호출 !");
		System.out.println("@@@@ DAO : DB 연결 준비(의존 주입 - sqlSession)");
		System.out.println("@@@@ DAO : MyBatis 사용 Mapper 이동해서 SQL 호출");
		
		System.out.println("@@@@ DAO : 파라미터값 2개 전달하기위해서 Map 객체 생성");
		Map<String, Object> paramMap
		 = new HashMap<String, Object>();
		
		//저장할 때 키값을 테이블의 컬럼명으로 저장
		// -> mapper에서 바로 전달될 수 있도록 처리
		// -> 스프링이 알아서 map객체로부터 데이터를 꺼내서 처리해줌
		paramMap.put("userid", id);
		paramMap.put("userpw", pw);		
		
		//com.itwillbs.mapper.MemberMapper.getMemberIdPw
		MemberVO vo = sqlSession.selectOne(namespace + ".getMemberIdPw", paramMap);
				
				
		System.out.println("@@@@ DAO : SQL 구문 실행 완료!");
		System.out.println("@@@@ DAO : 결과 저장해서 다시 테스트 페이지 이동");		
		
		return vo;
	}

	@Override
	public void updateMember(MemberVO vo) {
		System.out.println("@@@@ DAO : service에서 해당 동작을 호출");
		System.out.println("@@@@ DAO : 수정할 정보를 받아옴");
		System.out.println("@@@@ DAO : vo -> " + vo);
		System.out.println("@@@@ DAO : mapper 이동해서 쿼리 작동");			
		
		// sqlSession.update(statement, parameter) 
		//   -> 매개변수 : 쿼리 + mapper로 가지고 갈 데이터
		
		// sqlSession 객체(의존 주입) 사용
		//namespace + mapper id
		sqlSession.update(namespace + ".updateMember", vo);
		
		System.out.println("@@@@ DAO : mapper 사용 쿼리 실행 완료");
		System.out.println("@@@@ DAO : service 객체로 이동");
		
	}

	@Override
	public int deleteMember(MemberVO vo) {
		//DB에서 처리하고 올때 결과를 정수값으로 가져와서 서비스로 전달
		
		System.out.println("@@@@ DAO : service -> DAO 호출");
		System.out.println("@@@@ DAO : 삭제할 정보를 가지고옴 ");
		System.out.println("@@@@ DAO : vo -> "+vo);
		System.out.println("@@@@ DAO : mapper이동후 sql 실행");
		
		//mapper의 delete : pstmt.executeUpdate(); 와 같다
		// -> 쿼리가 실행된 rows 반환
		
		int values = sqlSession.delete(namespace + ".deleteMember", vo);
		
		System.out.println("@@@@ DAO : 삭제 완료. 삭제된 회원 수 -> " + values);
		System.out.println("@@@@ DAO : DAO -> service 이동(삭제된 회원수 가지고 이동)");
		
		return values;
		
	}

	@Override
	public List<MemberVO> getMemberList() {
		
		System.out.println("@@@@ DAO : service -> DAO ");
		System.out.println("@@@@ DAO : DAO -> mapper ");
		
		
		List<MemberVO> memberList = 
				sqlSession.selectList(namespace + ".getMemberList");
		// selectList() : DB의 SELECT 결과를 리스트로 저장하는 메서드
		// mapper에서는 List를 리턴x, List에 저장되는 타입을 리턴해야 한다.
		// => 스프링이 알아서 리스트에 저장
		
		System.out.println("@@@@ DAO : mapper의 결과를 List에 저장");
		System.out.println("@@@@ DAO : List -> "+memberList);
		System.out.println("@@@@ DAO : list 리턴해서 service 페이지로 이동");		
		
		return memberList;
	}
	
	
}
