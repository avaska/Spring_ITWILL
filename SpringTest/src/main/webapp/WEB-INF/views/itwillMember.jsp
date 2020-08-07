<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/Views/itwillMember.jsp</h1>	
	
	<h2>전달 객체값 확인</h2>
	<hr>   
	
	<h2>1. key값 없이 데이터를 넘겼을 경우 : 
		호출할 객체명의 첫 글자를 소문자로...</h2>
	<h1> addAttribute 실행시 이름 없이 저장 </h1>		
	
	<!-- 클래스 타입이 대문자로 이루어진 이름일때 첫글자 소문자변경 X -->	
	<h2>이름 : ${ITWILLBean.name}</h2>
	<h2>전화번호 : ${ITWILLBean.tel }</h2>
	
	<!-- 원래라면 카멜표기법으로 대소문자가 구분되어야 클래스 레퍼런스로 데이터 호출 가능 -->
	<!-- ITWILLBean으로 대문자가 너무 많아서 대소문자가 구분되지 않아서 정상적으로 되지 않는다고 함 -->
	<h2>나이 : ${iTWILLBean.age }</h2>
		
	<hr>
	    
	<h2>2. key값으로 데이터 호출할 경우</h2>
	<h1> addAttribute 실행시 이름 포함(itwill) 저장 </h1>
	<h2>이름 : ${itwill.name}</h2>
	<h2>전화번호 : ${itwill.tel }</h2>
	<h2>나이 : ${itwill.age }</h2>	
	
	<!-- 카멜표기법으로 대소문자가 구분 가능한 경우, 
	    클래스 레퍼런스로 데이터를 호출하려면 첫 글자가 소문자여야 한다. -->
	test1:  ${testBean.name }
 	test2:  ${TestBean.name }
</body>
</html>