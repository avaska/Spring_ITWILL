<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/vc/css/member/userJoin.css" type="text/css" rel="stylesheet">
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- 회원가입 유효성 체크 -->
<script type="text/javascript">
	function joinCheck(){
		var vancoUserJoin = document.vancoUserJoin;
		var idChk=$("#userId");
		var pwdChk=$("#userPwd");
		var pwdChk2=$("#userPwdChk");
		var nicknameChk=$("#userNickname");
		var gender1=$("input[id=userGenderM]");
		var gender2=$("input[id=userGenderF]");
		
		var idReg = /^[A-Za-z0-9]+$/;			 
		var pwdReg= /^[a-zA-Z0-9]{6,20}$/;		
		var nickReg= /^[가-힣0-9]{2,7}$/;
		
		// 미입력 시 입력요청
		
		if(!(gender1.is(":checked")) && !(gender2.is(":checked"))){
			alert("성별을 선택해 주세요.");
			return false;			
		}
		
		if(idChk.val()==""){
			alert("아이디를 입력해 주세요.");
			idChk.focus(); 
			return false;			
		}
		
		if(idChk.val().length<5){
			alert("아이디는 5자리 이상 입력해 주세요.");		
			idChk.focus(); 
			return false;
		}
		
		if(!idReg.test(idChk.val())){
			alert("아이디는 영문자와 숫자만 사용 가능합니다");
			idChk.focus(); 
			return false;
			
		}else if(pwdChk.val()==""){	
			alert("비밀번호를 입력해주세요.");
			pwdChk.focus();
			return false;
		}else if(!pwdReg.test(pwdChk.val())){
			alert("비밀번호는 숫자와 영문자 조합으로 6~20자리를 사용해야 합니다.");
			pwdChk.focus();
			return false;
			
		}else if(pwdChk.val()!=pwdChk2.val()){
			alert("입력하신 비밀번호와 일치하지 않습니다.");
			pwdChk2.focus();
			return false;
			
		}else if(nicknameChk.val()==""){	
			alert("닉네임을 입력해 주세요.");
			nicknameChk.focus();
			return false;
					
		}else if(!nickReg.test(nicknameChk.val())){
			alert("닉네임은 2~7자리 한글,숫자만 입력 가능합니다.");
			pwdChk.focus();
			return false;
			
		}else{//모두 입력 했다면
			vancoUserJoin.submit();
		}	
	}  // joinCheck() 끝
</script>


</head>
<body>



<div class ="loginSec" >	
	<form onsubmit="return joinCheck()" class="idLogin loginSec" action="userJoinAction.me" method="post" name="vancoUserJoin"> <!--  userJoinProc.jsp -->
		<div class="loginHead">					
	    	<a href="./vanco.me" class="vcLogo center">VANCO</a>
	    	<p class="loginIntro">우리동네 반려견 커뮤니티</p>
	  	</div>
	  	
		<p class="snsLogTit">아이디로 회원가입</p>  
	
	  	<div class="insertBtn">
	  		<label for="userId">아이디</label>
	    	<input type="text" id="userId"   name="userId" class="insertBox" placeholder="아이디를 입력해 주세요." required autofocus>			 	   
	  	</div>
	
	  	<div class="insertBtn">
		  	<label for="userPwd">비밀번호</label>
		    <input type="password" id="userPwd" name="userPwd" class="insertBox" placeholder="숫자와 영문자 조합으로  6~20자리" required>			   	 	
		</div>
		<div class="insertBtn">
			<label for="userPwdChk">비밀번호 확인</label>
		    <input type="password" id="userPwdChk" name="userPwdChk" class="insertBox" placeholder="비밀번호를 한번 더 입력해 주세요." required>			   	 	
		</div>
		<div class="insertBtn">
			<label for="userNickname">닉네임</label>
		    <input type="text" id="userNickname" name="userNickname" class="insertBox" placeholder="한글로 2~7자 이내로 입력해 주세요." required>			   	 	
		</div>
		<div class="genderWrap">
		
		    <input type="radio" id="userGenderM" name="userGender" value="남" class="insertBox userGender">
		    <label for="userGenderM" id="labelM"></label>
		    
		    <input type="radio" id="userGenderF" name="userGender" value="여" class="insertBox userGender">
		    <label for="userGenderF" id="labelF"></label>		   	 	
		</div>
		
								
	 	<button class="clickBtn joinClick" type="submit">회원 가입하기</button>	
		 
		<p class="copyR">&copy; VANCO :: since 2019</p>
	</form>		
</div>


   
</body>
</html>