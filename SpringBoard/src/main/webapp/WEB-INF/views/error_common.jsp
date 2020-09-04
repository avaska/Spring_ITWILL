<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WEB-INF/views/error_common.jsp</h1>
   <h2> 예외처리 페이지 입니다. </h2>
   
   <h3> 관리자에게 문의하세요. </h3>
   
   <a href="/board/listAll"> 리스트로 돌아가기 </a>
   <hr>
   
   ${e.getMessage() }
   <hr>
   
   <c:forEach items="${e.getStackTrace() }" var="err">
     <h3>${err.toString() }</h3>      
   </c:forEach>
   
   
   


</body>
</html>