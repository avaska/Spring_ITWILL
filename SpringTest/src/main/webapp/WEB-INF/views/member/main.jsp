<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/main.jsp</h1>
	
	<%
		// 세션값 체크
		String id = (String) session.getAttribute("userid");
	
		// 세션값이 없을 경우
		if(id == null){
			response.sendRedirect(request.getContextPath() + "/member/login");
			//contextPath : 프로젝트 최상위경로(프로젝트명)
			//여기서는 패키지명
		}
	
	%>
	
	<!-- jsp 표현식(자바코드의 변수에 접근해서 화면에 출력) -->
	<h3><%=id %>님이 로그인 하셨습니다</h3>
	
	
	<!--  scope의 종류 :  
		  - page : 현재 페이지에서만 유지되는 공간
		  - request : 
		  - session : 서버가 실행되고 있는 동안 유지되는 공간
		  - application : 프로그램이 실행되고 있는 동안 유지되는 공간
	-->	
	
	<!-- EL 표현식(영역(scope)의 데이터를 접근해서 화면에 출력) -->	
	<!-- 
		 EL 표현식 사용 가능한 내장객체(JSP 교재 p517~) 
	     
	     - pageScope
	     - requestScope
	     - sessionScope
	     - applicationScope
	     
	     - param    :  $(param.파라미터 이름)
	     - paramValues 		     
	     
	     *** parameter가 저장되는 공간과, attribute가 저장되는 공간이 다르다.
	     -> Scope 내장객체에는 attribute값이 바인딩된다.
	     -> param 내장객체에는 parameter 값이 바인딩된다.
	     
	     
	     * form태그에서 name속성이 같은 태그들 ex)checkbox 
	       ->  paramValues로 배열을 받아와서 JSTL foreach 반복문으로 화면에 출력
	     
	     //https://dololak.tistory.com/742
	-->
	
	
	
	<!-- 내장객체 영역.속성명 -->
	<h3>${ sessionScope.userid } 님이 로그인 하셨습니다</h3>

	<!-- 내장객체 영역.속성명 (내장객체의 영역이름을 생략 가능) -->
	<!-- 
	        내장객체 영역(pageScope > requestScope > sessionScope > applicationScope)을 모두 순차적으로 검색 
	        만약에 먼저 사용되는 속성이 있을 경우 다음 영역의 검색은 하지 않는다.
	-->
	<!-- 내장객체 전부(page,request,session,application)를 접근하여 대상의 attribute를 찾음 -->
	<h3>${ userid } 님이 로그인 하셨습니다</h3>
	
	
	<!-- EL표현식에서 null값은 빈 공백으로 출력된다. -->
	<h3>${ id } 님이 로그인 하셨습니다</h3>
	
	<hr>
    
    <input type="button" value="로그아웃" 
           onclick="location.href='/member/logout'" 
    >
    
    <hr>
    <h3><a href="/member/info">회원 정보 보기 (select)</a></h3>
    
    <hr>
    <h3><a href="/member/update">회원 정보 수정 (update)</a></h3>


	
</body>
</html>