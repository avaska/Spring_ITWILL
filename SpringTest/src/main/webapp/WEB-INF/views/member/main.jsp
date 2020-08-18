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
			//response.sendRedirect(request.getContextPath() + "/member/login");
			
			//contextPath : 프로젝트 최상위경로(프로젝트명)
			//여기서는 패키지명
			
			id="TEST";
		}
		
		// ★★★페이지 실행 순서★★★
		// java(jsp) -> JSTL,EL -> html -> | javascript -> jquery |
		// (JS와 jquery는 상황에 따라 실행되는 우선순위가 바뀔 수 있음)
		// 자바소스 컴파일이 가장 우선적으로 실행!!
		
		// 자바스크립트보다 JSTL(EL)이 우선적으로 실행되기 때문에 내장객체를 자바스크립트로 받아올 수 있다.
		// 컨트롤러로부터 데이터가 넘어온 것이므로 자바->자바스크립트로 데이터가 전달되는 것과 같다.
		
		
		// 자바 코드가 먼저 실행되기 때문에 sendRedirect를 하면 아래의 html태그가 실행되기 전에 페이지 이동이 되어버린다.
		// (main.jsp 화면이 뜨기도 전에 다른 페이지로 바뀜)
		
	
	%>
	
	<h2>result : ${param.result}</h2>
	
	<script type="text/javascript">
		//자바스크립트로 EL표현식 데이터를 쓰려면?
		
		//alert(${param.result}); // (X) 문자열로 데이터를 넣어야 실행됨	
		
		//자바 소스 컴파일이 우선적으로 실행되기 때문에 자바스크립트에서 내장객체(EL)를 받아올 수 있다. 
		var stmp = '${ sessionScope.userid }';
        alert(stmp);			
		
		//EL 표현식(파라미터값) 데이터를 자바스크립트 변수에 저장
		var tmp = '${param.result}'; //문자열으로 표현된 EL표현식 데이터
		
		//alert("TEST!! " + tmp); 
		if(tmp == 'delOK'){
				alert("삭제 완료!!!");
			}
		
	</script>
	
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

	<hr>
    <h3><a href="/member/delete">회원 정보 삭제 (delete)</a></h3>
    
    <!-- 회원 정보리스트는 관리자(admin)만 사용가능 -->
    
    <%
    	//null검사 먼저 수행 -> 우선순위가 뒤이면 NullPointerException 예외 발생
    	if(id != null && id.equals("admin")){
    %>
     
    <hr>
    <h3><a href="/member/list">회원 정보 리스트 (select - list)</a></h3>
    
    <%} %>
    
	
</body>
</html>