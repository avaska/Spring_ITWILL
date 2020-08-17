<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>

<link href="/vc/css/member/userJoin.css" type="text/css" rel="stylesheet">
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="/vc/js/member/login_userJoin.js"></script>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

</head>
<body>
	<div class ="loginSec" >	
		<form class="idLogin loginSec">
				<div class="loginHead">					
			    	<a class="vcLogo center" href="./vanco.me">VANCO</a>
			    	<p class="loginIntro">우리동네 반려견 커뮤니티</p>
			  	</div>
			  	
			  	<p class="snsLogTit">신규 회원가입</p>
			  	
				<%@ include file="agreeBox.jsp" %>
			 	 
			 	<a class="clickBtn kakao mouseHand" href="javascript:agreeCheckKakao();">카카오톡으로 가입하기</a>
			 	<a class="clickBtn naver mouseHand" onclick="agreeCheck()">네이버로 가입하기</a>
			 	<a class="clickBtn idJoin mouseHand" onclick="agreeCheckId()">아이디로 가입하기</a>
			 	
			</form>	
	 		<p class="joinLink">아이디가 있으신가요?&nbsp; <a href="./userLogin.me"> 로그인</a></p>	
	 		<p class="copyR">&copy; VANCO :: since 2019</p>
	</div>
	
</body>
</html>