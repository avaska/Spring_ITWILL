package com.itwillbs.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//JUnit 라이브러리 가져오지 못한다면 프로젝트 우클릭 - maven - update project
// -> 프레임워크 기본버전으로 내려가므로 Java Compiler, Project Facets에서 Java version 1.8으로 변경하여 적용


// 스프링 테스트용으로 사용
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
	)
public class DataSourceTest {
	// root-context.xml 파일과 연결해서 DB연결 테스트
	// +(의존 주입 확인)	
	
	// DB 연결 객체를 의존 주입 (DI)
	@Inject
	private DataSource ds;
	
	//테스트 작업 -> 메서드
	@Test
	public void testCon() throws Exception{
		
		//Try-With 구문 (자원해제 자동으로 수행)
		try(Connection con = ds.getConnection()){
			
			System.out.println("DB 연결 테스트 성공!");
			System.out.println("con : " + con);
			
		}catch (Exception e) {
			System.out.println("DB 연결 테스트 실패!");
			e.printStackTrace();
		}
	}
	
}
