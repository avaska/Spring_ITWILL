# Spring_ITWILL
스프링 수업


git 연동

STS에서는 git commit 따로, push 따로 


https://m.blog.naver.com/sim4858/220924984480
이거 보고 하기


----------------------------------------------------------------------------------------------------------------

스프링 웹 프로젝트

1) Spring Boot 
    별도의 설정이 필요없음.
  WAS가 필요없음.
    로딩시간이 짧아서 테스트하기 편함.
  / 
   기존의 설정과 다른방식(독립적 방식)
   jsp 페이지 설정이 조금 복잡함. 


2) Spring Legacy Project => STS4에서는 기본 제공X(마켓플레이스에서 sts 검색추가)
  가장 일반적으로 사용되는 형태
  다양한 정보 제공
  이전의 프로젝트 구조를 이해하기 쉬움.
  모든 버전의 스프링을 사용할수 있음.
  /
  초반 설정이 매우 어려움(반복숙달)
  WAS를 사용할경우 많은 리소스가 필요함.
  최신 트렌드에는 조금 느릴수 있음.


* 빌드도구 Maven : 필요한 라이브러리를 쉽게 추가할수있게 함

  C:\Users\ITWILL\.m2\repository
  => 로컬에 저장됨 
   1) 프로젝트 우클릭 > MAVEN > update project  사용 재시작
   2) 필요에따라서 폴더 안에 파일을 직접 삭제후 재시작(STS 종료)

* 프로젝트 처음 만들때 사용한 패키지명이 프로젝트 실행시 주소로 표기
   패키지 : com.itwillbs.test
   시작주소 : http://localhost:8080/test/~
   
   
   * 프레임워크 : 뼈대, 틀처럼 프로젝트를 구성하는 코드의 묶음
   => 일반적인 프로젝트 진행보다 평균적인 결과를 제공하는 형태를 만들기위해서 
   => 경량 프레임워크 (서버 중심 개발-> 클라이언트 중심 개발)
   => 프로젝트 전체 설계가 쉽다.
   => 다양한 프레임워크 지원, 개발도구 지원
   
   * POJO기반의 구성(Plain Old Java Object) 
   => 자바코드 그대로 스프링에 적용가능(관계 구성시 별도의 API를 사용하지 않아도된다)
   
   * 제어의 역행(IoC-Inversion Of Control) 
   -> 메서드,객체 호출을 개발자가 직접하는게 아니라 외부(스프링)에서 결정 
   => 대부분의 프레임워크에서 사용 => 끼워넣기식 개발
    
   * 의존성 주입(DI-Dependency Injection)
   => 제어의 역행이 발생할때 스프링 내부에 있는 객체(Bean)가 전달되는 형태를 관리
   => A는 B를 필요로 한다.( A-B는 의존 관계에 있다 )
   
   * set메서드 / 생성자 사용 => java 프로젝트 참고
   
   * AOP 지원(Aspect Oriented Programming)
   => 보안처리,로그출력,트랜잭션등과 같은 작업은 반드시 필요하지만 내가 구현하기엔
      부담되기 때문에 스프링에서 처리함(횡단 관심사-cross concern)
   => 횡단 관심사를 처리할수있도록 모듈로 분리해서 제공해주는 프로그래밍 방법   
   
   
   
   * 스프링 + MyBatis + MySQL
      
   MyBatis -> SQL 쿼리를 효율적으로 처리하는 방법( SQL Mapper Library) // 코드량을 줄여줘서 익숙해지면 더 효율이 좋음
   (구버전 :  IBatis)   
   * SQL 구문을 분리해서 처리( XML코드, 어노테이션 )
   * 스프링과의 연동이 매우 우수해서 데이터 처리가 편리함
   * 동적 SQL 구문    // https://mybatis.org/mybatis-3/ko/dynamic-sql.html
   
   * MyBatis 다운로드 & pom.xml에 dependency 추가하기
   1. MyBatis : https://mvnrepository.com/artifact/org.mybatis/mybatis/3.4.1
   2. MyBatis Spring : https://mvnrepository.com/artifact/org.mybatis/mybatis-spring/1.3.0
   3. Spring JDBC : https://mvnrepository.com/artifact/org.springframework/spring-jdbc/5.2.8.RELEASE
   4. Spring Test : https://mvnrepository.com/artifact/org.springframework/spring-test/5.2.8.RELEASE   
   - Spring JDBC,Spring Test는 dependency 추가할 때 프레임워크에 맞는 버전 다운받도록 설정하기
   - <version>${org.springframework-version}</version>
   
   
   
   * 스프링 웹 프로젝트(MVC)
   
   Presentation Layer : UI 영역을 처리하는 계층(view) + Controller
   
   Business Layer : 서비스 계층(model) 처리 작업
   
   Data Access Layer : 데이터 처리 계층(Persistence Layer) -> DB 관련
   
   
   View - Controller - Service - DAO - MyBatis - DB
      여러 단계를 거치는 이유 : DB와의 결합을 약화시켜서 대상과 종속적이지 않게 하기 위함      
      
      
      
   * pom.xml 설정
   1. properties
   - 자바 버전 1.8으로 : <java-version>1.8</java-version>
   - 프레임워크 버전 : <org.springframework-version>4.3.8.RELEASE</org.springframework-version>
   
   2. dependencies
   - JUnit
   - mysql-connector-java
   - MyBatis
   - MyBatis Spring
   - Spring JDBC
   - Spring Test
   
   3. Maven - Update Project 후 jdk 버전 1.8으로 재설정
    - Java compiler
    - Project Facets
    
    
    * root-context.xml : 웹과 관련된 설정을 제외한 모든 설정을 처리하는 파일(전체 프로젝트에 영향을 준다)
    * servlet-context.xml : 웹과 관련된 설정을 지정하는 파일
    
    
    
    
모델2(MVC 패턴)
=> 화면과 데이터를 분리해서 처리(코드의 재활용)
=> 위임(FC-Front Controller)받은 컨트롤러가 동작 제어(view 이동 처리)


	FC  -  Controller  - Service - DAO - MyBatis(Mapper) - MySql
	 |
    View
    
    * 스프링 MVC가 처리하는 작업
     - URI(맵핑할 주소) 분석해서 적절한 컨트롤러 이동
     - 컨트롤러에 필요한 메서드 호출
     - 컨트롤러에 결과를 데이터로 저장해서 view로 전달
     - 적절한 view 페이지 이동    
    
    * 개발자가 처리해야하는 작업
     - 특정 URI에 동작하는 컨트롤러 설계
     - 서비스 객체 생성 
     - DAO 객체 생성
     - 컨트롤러에서 필요한 사용자 정의 기능 구현
     - view에서 전달 받은 결과 출력/사용
    
    * 스프링 MVC 컨트롤러
     - 파라미터값 수집 ( DTO/VO(Value Object) 자동으로 처리) 
        -> request의 parameter,attribute 바인딩 값
     - @Controller 어노테이션 사용 -> 쉽게 설정 가능 : request, response 처리 가능
     - 각각의 메서드마다 특정 주소를 매핑해서 처리가능(처리 로직에 집중)
     - 스프링 사용 테스트가 쉽다.
     => servlet-context.xml 파일에 설정값들이 지정됨
     
     
     * 스프링  + MyBatis
     1) XML파일을 사용해서 SQL 구문 작성, DAO에서 xml을 읽어서 사용하게 하는 방식
     - SQL 구문(xml 파일)을 수정, 유지보수 처리가 쉽다. (별도로 만들어진 파일이므로)     
     - 개발 시 코드의 양 증가, 복잡도 증가(파일의 양이 많아지므로)
     - 관리자 입장에서 가장 좋은 방식.(코드의 양이 증가하는 것은 개발자의 몫이고 관리자와 상관 없기 때문)
     
     2) 어노테이션, 인터페이스 객체를 사용해서 SQL 구문을 사용하는 방법
     - 별도의 DAO 없이 개발이 가능 => 생산성 증가
     - 어노테이션으로 SQL구문 작성, 수정 시 매번 컴파일이 필요함.
     
     3) 인터페이스와 XML파일을 사용해서 SQL 구문을 사용하는 방법
     - 간단한 쿼리는 인터페이스(어노테이션), 복잡한 구문은 XML 처리 가능 => 유연한 대처
     - 개발자의 능력에 따른 차이가 발생하기 때문에, 팀플에 부적합.
     
     
   * MyBatis -> XML(SQL) -> DAO처리
  
   - 테이블 생성 및 디비 생성
   - 도메인 객체를 생성(자바빈)
   - DAO 인터페이스를 구현
   - 실제로 처리할 동작을 추상 메서드로 구현
   - XML 파일을 사용해서 Mapper생성, SQL 구문작성
   - MyBatis에서 XML파일을 인식 
   - DAO 인터페이스를 구현한 객체 생성
   - 스프링 테스트를 통해서 실제 동작을 구현 
   
   Java Resources : domain(VO) - persistence(DAO) - web(Controller) 3가지로 구분할 것
   
   Mabatis mapper를 만드는 방법
   https://mybatis.org/mybatis-3/ko/getting-started.html
   -> 매핑된 SQL 구문 살펴보기
   
   root-context.xml : 스프링에서 웹에관련된 설정을 제외한 모든 설정을 하는 파일
   
   CREATE TABLE `springdb`.`tbl_member` (
  `userid` VARCHAR(50) NOT NULL,
  `userpw` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(200) NULL,
  `regdate` TIMESTAMP NULL DEFAULT now(),
  `updatedate` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`userid`));
  
  
  * 도메인 : 개발프로젝트에서 가장 중요한 용어(단어)를 도메인(명사)
 	             물리적인 기준으로 분리 가능한 단위(DB 테이블로 나누어지는 상태)
  	ex) 쇼핑몰  		
  	  1차 도메인- 회원, 결제, 주문, 상품, 리뷰, 게시판,....
   	  2차 도메인- 회원 -> 회원 가입, 수정,삭제	
	  ....  
	  
* 비즈니스 계층 (Service)
-> 사용자의 요구사항을 구현한 계층 (인터페이스 -> 클래스)
-> DAO 와 Controller의 연결 
-> 코드의 분량이 증가 (결합도 감소,응집도 감소)  
-> 요구사항 마다 구현할수있도록 준비되어 있기 때문에 완충작용
(디비에 처리되는 동작을 완충시키는 작업)
-> 컨트롤러와 같은 외부 호출에서 영속계층(DAO) 호출을 막아서 
종속적인 상황을 제거함 
- 컨트롤러가 DAO에 직접 접근할 경우 : 컨트롤러에 모든 동작의 구현이 집중됨
(컨트롤러에서 DAO 객체 직접 생성해서 사용)
      

 * 서버 동기화 도중에 페이지 요청 
	-> 동기화되는 시점에 문법이 완벽하지 않으면 exception 메시지가 발생
	-> 동기화 끝나고 요청하거나, 서버 꺼놓고 작업하면 문제X    
      
  *Server - Path 지정
   -> "/"  : 프로젝트가 1개만 있을 때 편하게 쓰기 위해 root를 기본 경로로 지정
   -> "/test" : 프로젝트가 여러개 있을 때 패키지명을 붙여서 path를 구분
   
   
 * 404 에러
  - HTTP Status 404 -   : 맵핑된 정보가 없다
  - HTTP Status 404 - ~~~.jsp  : 맵핑은 되어있으나 연결되는 view페이지가 없다 
    ex) HTTP Status 404 - /WEB-INF/views/member/deleteForm.jsp 

  
 * 페이지 실행 순서
 
	- java(jsp) -> JSTL,EL -> html -> [ javascript -> jquery ]
	  -> JS와 jquery는 상황에 따라 실행되는 우선순위가 바뀔 수 있음
	  -> 자바소스 컴파일이 가장 우선적으로 실행!!
		
	- 자바스크립트보다 JSTL(EL)이 우선적으로 실행되기 때문에 내장객체를 자바스크립트로 받아올 수 있다.
	- 컨트롤러로부터 데이터가 넘어온 것이므로 자바->자바스크립트로 데이터가 전달되는 것과 같다.		
		
	- 자바 코드가 먼저 실행되기 때문에 view 페이지에서 sendRedirect를 하면 자바코드 아래의 html태그가 실행되기 전에 페이지 이동이 되어버린다.
	  (main.jsp 화면이 뜨기도 전에 다른 페이지로 바뀜)       
   
   
-------------------------------------------------------------------
   
 * 실무에서 시스템콜(sysout 등) 사용하지 말 것 -> 속도 저하 원인
  -> 대신  logger 사용     

--------------------------------------------------------------------------------------------------------
**팀프로젝트 소스 DB 오류 발생할 때
HTTP Status 500 - org.apache.commons.dbcp.SQLNestedException
 :Cannot create PoolableConnectionFactory 
 (Access denied for user 'itwillbs8'@'localhost' (using password: YES))
- 발표 시 사용했던 웹서버 계정과 로컬 서버와의 차이 때문일 수 있다고 함
- MySQL에 해당 계정을 만들어주거나
- META-INF/context.xml, DAO 설정 바꿔보라고 함


-----------------------------------------------------------------------------------------------------------

**팀프로젝트
1. 버전 통일
2. 테이블 짜기(각자 구현하고자 하는)
3. 소스 관리(git,카톡 등)
4. 저작권 주의(폰트,이미지)
5. 파트 나눠서 2명씩 분담













