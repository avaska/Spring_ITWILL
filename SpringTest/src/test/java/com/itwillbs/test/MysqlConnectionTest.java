package com.itwillbs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	//@Test 어노테이션 : JUnit 실행 -> 서버에서 작동하는것처럼 테스트 가능	
	@Test
	public void testConnection() {		
		try {
			// 드라이버 로드
			Class.forName(DRIVER);
			// 디비연결
			Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);			

			System.out.println("디비 연결 객체 : " + con);
			
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// ->try-with구문 사용
		// finally 블럭 없이 자동으로 자원 해제 처리 가능하도록 만들어진 구문
		// jdk7 이상 사용 가능(jdk7 미만 버전에서는 컴파일 에러)
		/*
		 * try(AutoCloseable 객체) {
		 * 
		 * }catch (Exception e) {
		 * 
		 * }
		 */
		// 드라이버 로드
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		// 디비연결
		try (Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);) {
			// Connection : AutoCloseable 인터페이스를 상속
			// try( ) 안에 넣어주면 finally블럭 없이 자동으로 자원해제 처리 가능
			
			// Hierarchy 보기 : 
			// Connection에 마우스 대고 F4키 누르면 좌측에 Type Hierarchy 생김
			// 2번째 버튼인 Supertype hierarchy로 보기

			System.out.println("디비 연결 객체 : " + con);
			
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
