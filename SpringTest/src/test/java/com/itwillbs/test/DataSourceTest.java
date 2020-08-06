package com.itwillbs.test;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(
//		//속성 지정
//		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
//		)
// => 일반 클래스를 테스트할 때 스프링으로 테스트할 수 있도록 세팅
//	  (자바클래스 1개가 스프링이 됨)


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		//속성 지정
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class DataSourceTest {
	
	// 스프링 테스트를 사용해서 DB연결 확인
	
	// root-context.xml에 있는 bean 객체를 가져와서 의존 주입
	// DB연결 정보를 가져오기	
	@Inject
	private DataSource ds;
	//직접 객체를 생성하지 않고, 이미 만들어져 있는(스프링이 만들어주는) 객체를 받아서 쓰겠다
	//관련 파일이 있어야만 데이터를 가져와 쓸 수 있다.	
	
	
	// 테스트 메서드 생성
	//@Test 어노테이션 붙여야 JUnit으로 테스트 가능
	@Test
	public void testCon() throws Exception{
		//try-with 구문
		try(Connection con = ds.getConnection()){
			
			System.out.println("스프링 의존 주입을 사용한 DB 연결");
			System.out.println(con);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//콘솔 결과
	//Loading XML bean definitions from URL : 해당 경로에 있는 xml파일로부터 bean 데이터를 읽어옴
	//AutowiredAnnotationBeanPostProcessor : Inject 어노테이션을 붙인 객체를 xml 설정파일에서 찾아서 자동으로 연결?
	//Loaded JDBC driver: com.mysql.jdbc.Driver : mysql jdbc 드라이버 로드
	//Closing org.springframework.context.support.GenericApplicationContext@4883b407: 자원 해제
	
	
}
