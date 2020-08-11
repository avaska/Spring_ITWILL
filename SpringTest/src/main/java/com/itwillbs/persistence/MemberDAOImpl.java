package com.itwillbs.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : 해당 클래스를 DAO로 사용하겠다. 스프링에 등록(root-context.xml 연결) 
// -> @Inject 어노테이션이 있는 코드를 실행하면 스프링이  @Repository 키워드가 있는 객체를 찾아서 injection

@Repository
public class MemberDAOImpl implements MemberDAO{

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
	
	
	

}
