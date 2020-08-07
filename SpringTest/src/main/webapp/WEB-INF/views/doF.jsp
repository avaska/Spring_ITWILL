<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/doF.jsp</h1>
	

	<h2>전달된 값 : ${abc }</h2>
	
	<!-- 위와 같은 방식 -->
	<%=request.getAttribute("abc") %>
</body>
</html>