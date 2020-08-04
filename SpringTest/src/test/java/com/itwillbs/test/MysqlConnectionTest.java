package com.itwillbs.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MysqlConnectionTest {

	// mysql workbench에서 springdb schema 생성하기
	
	// DB 연결정보	
	// 드라이버,URL,ID,PW
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/springdb?userSSL=false";
	//8.0버전
	//private static final String DBURL = "jdbc:mysql://localhost:3306/springdb?userSSL=false&serverTimezone=Asia/Seoul";
	private static final String DBID = "root";
	private static final String DBPW = "1234";
	
	// Junit => 프레임워크 테스트 도구
	// main 메서드가 없어도 테스트할 수 있게 도와줌
	// 테스트 동작을 메서드로 구현 후 실행
	
	// 디비연결 테스트
	//@Test 어노테이션 : JUnit 실행
	@Test
	public void testConnection() throws Exception {
		// ->try-with구문 사용
		
		//드라이버 로드
		Class.forName(DRIVER);
		//디비 연결
		Connection con = 
				DriverManager.getConnection(DBURL, DBID, DBPW);
		
		System.out.println("디비 연결 객체 : " + con);
		
	}

}
