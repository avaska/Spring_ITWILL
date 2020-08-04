package com.itwillbs.ex;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcTest {

	public static void main(String[] args) {
		// 계산기 실행 사용(전, 후 AOP 사용 로그 출력)
		
		// 계산기 객체가 필요 => 계산기 객체에 의존한다.
		// 약한 결합을 통한 의존 주입(DI)
		// => 스프링 파일에서 미리 생성된 객체를 불러서 사용
		
		//new Calculator
		//new LoggingAdvice
		// => 직접 코드 생성X
		
		// xml 파일 호출
		ApplicationContext ctx
		  = new ClassPathXmlApplicationContext("AOPTest.xml");
		
		//xml 파일을 통해서 해당 객체(bean) 주입
		Calculator cal = (Calculator)ctx.getBean("proxyCal");
		
		// 계산기 실행
		cal.add(100, 200);
		System.out.println("----------------------------");
		cal.sub(100, 200);
		System.out.println("----------------------------");
		cal.mul(100, 200);
		System.out.println("----------------------------");
		cal.div(100, 200);
		
		

	}

}
