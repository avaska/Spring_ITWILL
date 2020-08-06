package com.itwillbs.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}
			// ** : 어떤 폴더가 들어 있던(깊어지더라도) 해당 파일을 찾아서 쓰겠다				
		)
public class MyBatisTest {
	
	//Mybatis 연결 테스트
	// -> SqlSessionFactory 객체 생성(bean 의존 주입)	
	@Inject
	private SqlSessionFactory sqlFactory; 
	
	//root-context.xml에서 만들어지는 bean 객체(의존 주입할 객체) 
	// => SqlSessionFactoryBean : SqlSessionFactory 인터페이스를 상속하는 하위 객체
	//    상위 인터페이스인 SqlSessionFactory 레퍼런스로 의존 주입을 받아서 upcasting
	
	
	
	
	//JUnit은 바로 적용이 되지 않는 오류가 발생할때가 종종 있으니
	//안 되면 pom.xml에서 버전 내렸다가 올리는 방식을 해보자
	
	// 테스트 메서드 생성
	@Test
	public void testFactory() {
		System.out.println(" bean 객체 의존 주입!(객체 생성)");
		System.out.println("@@@@@@@ sqlFactory : " + sqlFactory);
	}
	//org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@2eae8e6e
	//DefaultSqlSessionFactory@2eae8e6e : 객체@해쉬코드 -> 객체 생성되었다는 의미.
	//Mybatis에서 구버전(ibatis) 패키지를 이용하는 경우도 있어서 ibatis라고 나오는 것
	
	
	//sqlFactory 객체를 사용해서 연결
	@Test
	public void testSessionFactory() {
		//try-With 구문(자동 자원해제)
		try(SqlSession session = sqlFactory.openSession()) {
			//Server.xml은 이미 톰캣서버가 실행되면 오라클과의 사이에서 세션을 생성하고 커넥션을 만든다.
			//이 중 세션을 만드는 것을 sessionfactory라 하며, 
			//세션을 생성하라고 sessionfactory에게 요청하는 것이 sqlsession이다.
			//https://m.blog.naver.com/PostView.nhn?blogId=soorte1&logNo=220821928062&proxyReferer=https:%2F%2Fwww.google.com%2F
			
			System.out.println(" sqlFactory 사용 DB 연결");
			System.out.println("@@@@@@@ 연결된 객체 정보 : " + session);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
