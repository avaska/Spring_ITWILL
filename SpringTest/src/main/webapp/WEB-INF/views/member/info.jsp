<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WEB-INF/views/member</h1>
	<h2> 회원 정보 출력 </h2>
	
	<!-- http://localhost:8090/member/info  로 접속-->
	<%
		// 세션값처리(ID가 없을경우 로그인 페이지로 이동)
		String id = (String) session.getAttribute("userid");
	
		if(id == null){
			response.sendRedirect("/member/login");
			//response.sendRedirect(request.getContextPath()+"/member/login");						
		}	
	%>
	
   <!-- 컨트롤러에서 정보를 저장해서 jsp페이지까지 이동완료(Model객체) -->
   <h3>아이디 : ${memberVO.userid }</h3>
   <h3>비밀번호 : ${memberVO.userpw }</h3>
   <h3>이름 : ${memberVO.username }</h3>
   <h3>이메일 : ${memberVO.email }</h3>
   <h3>회원 가입일 : ${memberVO.regdate }</h3>
   
   <h2><a href="/member/main">Main 페이지 이동</a></h2>

</body>
</html>