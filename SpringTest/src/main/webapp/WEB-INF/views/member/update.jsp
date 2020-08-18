<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/member/update.jsp</h1>
	
	<h1>회원 정보 수정</h1>
	<!-- http://localhost:8090/member/update -->
	<%
		//세션 데이터 처리
		String id = (String) session.getAttribute("userid");
	
		if(id == null){
			response.sendRedirect("/member/login");
		}
	%>
	
	<!-- 비밀번호 입력해야하고, DB랑 일치해야 수정 가능한 상태 -->
	<fieldset>
		<legend>회원 정보 수정</legend>		
		<form action="/member/update" method="post">
			아이디 : <input type="text" name="userid" value=${memberVO.userid } readonly><Br>
			비밀번호 : <input type="text" name="userpw"><Br>
			이름 : <input type="text" name="username" value=${memberVO.username }><Br>
			이메일 : <input type="text" name="email" value=${memberVO.email }><Br>			
			<input type="submit" value="회원 정보 수정">
		</form>	
	</fieldset>
	
	
</body>
</html>