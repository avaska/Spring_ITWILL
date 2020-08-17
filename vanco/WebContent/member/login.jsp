<%@page import="java.math.BigInteger"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.security.SecureRandom"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>  
<!DOCTYPE html>
<html>
<head>

<link href="/vc/css/member/login.css" type="text/css" rel="stylesheet">
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="/vc/js/member/login_userJoin.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>	
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>
	

	<div class ="loginSec" >	
		<form class="idLogin loginSec" action="userLoginAction.me" method="post">
				<div class="loginHead">					
			    	<a href="vanco.me" class="vcLogo center">VANCO</a>
			    	<p class="loginIntro">우리동네 반려견 커뮤니티</p>
			  	</div>
			
			  	<div class="insertBtn">
			    	<input type="text" id="userId" name="userId" class="insertBox" placeholder="아이디" required autofocus>
			 	   <label for="userId"></label>
			  	</div>
			
			  	<div class="insertBtn">
				    <input type="password" id="userPwd" name="userPwd" class="insertBox" placeholder="비밀번호" required>
			   	 	<label for="userPwd"></label>
				</div>
			
			 	 <div class="checkbox">
			   		 <label>
			     	 <input type="checkbox" value="remembeMe"> 자동 로그인
			   		 </label>
			 	 </div>
			 	 <button class="clickBtn mouseHand" type="submit">로그인</button>
			 	
			 	 <hr>   <!-- 줄긋기 --> 
			 	 
			    <p class="snsLogTit">SNS 계정으로 로그인</p>
			</form>	 	 
			<a  href="javascript:loginWithKakao()"  class="clickBtn kakao mouseHand">카카오 로그인</a>			 
			
			<div class="clickBtn naver mouseHand">네이버 로그인 <div id="naverIdLogin"> </div></div>
			
			<!-- <div  id="naverIdLogin"></div> -->
			 	 
			<p class="copyR">&copy; VANCO :: since 2019</p>			
	 		<p class="joinLink">아이디가 없으신가요?&nbsp; <a href="./userJoin.me"> 회원가입</a></p>	
	 		
	 		
 		 <script type="text/javascript">
               var naverLogin = new naver.LoginWithNaverId({
                   clientId: "57kOwsMTMqXuRHCgpMpb",
                   callbackUrl: "http://localhost:8090/vc/member/naverCallback.jsp",
                   isPopup: false,   
                   loginButton: {color: "green", type:4, height: 60} /* 로그인 버튼의 타입을 지정 */
               });
               naverLogin.init();
         </script>
	 		
	 		<style>
	 			#naverIdLogin{position: absolute;top:0;width:280px; height:42px !important; opacity:0}
	 		</style>
	 		
	 		
	</div>
</body>
</html>