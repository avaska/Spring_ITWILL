<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- prefix에 다른 약자가 와도 상관없지만, core library라는 의미에서 보편적으로'c'를 사용함 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/member/memberList.jsp</h1>
	
	<%
		 //세션 아이디 처리
	    String id = (String)session.getAttribute("userid");
	  
	    if(id==null || !id.equals("admin")){
	    	response.sendRedirect("/member/login");
	    }
	
	%>
	
	<!-- 전달 받은 회원 목록을 출력(JSTL - 반복문)
		 JSTL 라이브러리는 Maven에 들어있다.
	 -->
	
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>비밀번호</td>
			<td>이름</td>
			<td>이메일</td>
			<td>가입일</td>
			<td>정보수정일</td>			
		</tr>
		
		<!-- var : 반복문이 돌 때마다 items의 데이터를 하나씩 꺼내온다 -->
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td><c:out value="${member.userid}" /></td>
				<td><c:out value="${member.userpw}" /></td>
				<td>${member.username }</td>
				<td>${member.email }</td>
				<td>${member.regdate }</td>
				<td>${member.updatedate }</td>
			</tr>
		</c:forEach>		
	</table>
	
	<a href="/member/main"> main 페이지로 이동 </a>
	
</body>
</html>