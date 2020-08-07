<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> WEB-INF/views/itwill.jsp </h1>
	
	<h2> /doC 주소 호출로 연결된 view 페이지입니다. </h2>	
	
	<h1>전달값 msg: ${msg }</h1>
	<h1>전달값 age: ${age +10 }</h1>	
	<!-- SampleController2에서 get방식/내장객체(request/session,...) scope로 데이터 넘어옴 -->
	<!-- http://localhost:8090/test/doC?msg=hello&age=50 -->
	<!-- 파라미터값 msg, age 모두 요청해야 오류가 발생하지 않고 view 페이지가 출력된다. -->
	
	<!-- 컨트롤러를 거쳐서 view로 가야 페이지가 출력된다.
		 http://localhost:8090/test/doC  	   (출력O)
		 http://localhost:8090/test/itwill.jsp (출력X)
	 -->
</body>
</html>