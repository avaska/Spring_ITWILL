<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/member/insertForm.jsp</h1>
	
	<h1>회원 가입</h1>
	
	<fieldset>
		<legend>회원가입</legend>
		<!-- <form action="/test/member/insert" method="post"> -->
		<form action="/member/insert" method="post">
			아이디 : <input type="text" name="userid"><Br>
			비밀번호 : <input type="text" name="userpw"><Br>
			이름 : <input type="text" name="username"><Br>
			이메일 : <input type="text" name="email"><Br>
			<input type="submit" value="회원가입">
		</form>	
	</fieldset>
	
	
</body>
</html>