<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 메타태그1. 모바일 뷰포트용 메타태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">

<!-- CSS 연결 -->
<link href="<%=path%>/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/index.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/neighborComm/moim.css" type="text/css" rel="stylesheet">
</head>

<!-- 자바스크립트파일 연동 -->

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- jstl 라이브러리 사용을 위한 선언  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>


<title>VANCO : 우리동네 반려견 커뮤니티</title>


<!-- 정보수정 유효성 체크 -->
<script type="text/javascript">
	function moimMakingCheck(){
		var moimMakingForm = document.moimMaking;
		
		var moimCate1=$("input[id=moimCate1]");
		var moimCate2=$("input[id=moimCate2]");
		var moimCate3=$("input[id=moimCate3]");
		
		var moimTitle=$("#moimTitle");
		var moimPhoto=$("#moimPhoto");
		var moimIntro=$("#moimIntro");
		
		var imgReg = /([^\s]+(?=\.(jpg|gif|png|JPG|GIF|PNG))\.\2)/;			 
			
		var moimTitleReg= /^[a-zA-Z가-힣0-9]{5,7}$/;
		
		
		// 미입력 시 입력요청
		if(!(moimCate1.is(":checked")) && !(moimCate2.is(":checked")) && !(moimCate3.is(":checked"))){
			alert("카테고리를 선택해 주세요.");
			return false;			
		}else if(moimTitle.val()==""){	
			alert("모임주제를 입력해주세요.");
			moimTitle.focus();
			return false;
		}else if(moimTitle.val().length<=6 || moimTitle.val().length>21){			
			alert("모임주제는 6자 이상 20자 이하로 입력해 주세요.");
			moimTitle.focus();
			return false;			
		}else if(moimIntro.val()==""){			
			alert("모임소개를 입력해 주세요.");
			moimTitle.focus();
			return false;			
		}else if(moimIntro.val().length<10 || moimIntro.val().length>501){			
			alert("모임소개는 10자 이상, 500자 이하로 입력해 주세요.");
			moimTitle.focus();
			return false;			
		
		}else if(!imgReg.test(moimPhoto.val())){			
			alert("사진은 jpg, png, gif파일만 사용가능 합니다.");
			moimTitle.focus();
			return false;			
		
		}else{//모두 입력 했다면
			moimMakingForm.submit();
		}	
	}  // moimMakingCheck() 끝
</script>



<script type="text/javascript">
	//제이쿼리를 이용해 아래쪽의 input type="file" 태그에서 이미지 파일 첨부 미리보기 기능을 구현하는 함수
	function readURL(input){		

		if(input.files && input.files[0]){
		$("#picUploaded1").html("<img id='preview1' src='#' />");
		
		// 업로드할 이미지 파일 내용을 읽어올 객체 생성
		var reader=new FileReader();
		
		// 지정한 img 태그에 첫번째 파일 input에 첨부한 파일에 대한 File객체를 읽어 온다.
		reader.readAsDataURL(input.files[0]);
		
		// 파일명을 모두 읽어들였다면(모두 로드했을때)
		reader.onload=function(ProgressEvent){				
			console.debug(ProgressEvent)				
			$("#preview1").attr('src',ProgressEvent.target.result);}}}

</script>


		

<%	//글 작성 시 input태그에 히든값으로 처리할 정보 세션에서 가져오기
	String userId=(String)session.getAttribute("userId");		
	if(userId==null || userId==""){out.println("<script>alert('로그인을 해 주세요.'); location.href='./userLogin.me'; </script>");}	

	// DB접속하여 user 정보 가져오기.
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		mdto = mdao.getUserInfo(userId);
		
		// 필요 정보 저장	
		String userCity = mdto.getUserCity();
		String userDistrict = mdto.getUserDistrict();		
		String userPosition = mdto.getUserPosition();

%>

<body>

	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />	

<div class="bodyWrap">	

	<div class="moimMakingWrap">
	
	<div class="makingNameBox">
		<span class="makingTit">소모임 만들기</span>
		<span class="userDistrict"><%=userCity%> · <%=userDistrict%></span>
	</div>
	
	<form onsubmit="return moimMakingCheck()" action="moimMakingAction.ne" method="post" name="moimMaking" enctype="multipart/form-data" >		
		<table class="moimTable">
			<tr>
				<td><span>* 카테고리</span></td>
				<td>
				<div class="cateWrap">
				
				    <input type="radio" id="moimCate1" name="moimCategory" value="덕질" class="insertBox">
				    <label for="moimCate1" id="label1"></label>
				    
				    <input type="radio" id="moimCate2" name="moimCategory" value="덕품교환" class="insertBox">
				    <label for="moimCate2" id="label2"></label>
				    
				   <input type="radio" id="moimCate3" name="moimCategory" value="친목" class="insertBox">
				   <label for="moimCate3" id="label3"></label>
				    		   	 	
				</div>
				</td>
			</tr>
		
			<tr>
				<td><span>* 소모임 주제</span></td>
				<td><input spellcheck="false" type="text" name = "moimTitle" id="moimTitle"  maxlength=20 required placeholder="20자 이내로 주제를 적어 주세요."></td>					
			</tr>
			
			<tr class="picUploadedWrap">
				<td><span>&nbsp;&nbsp;메인 사진</span></td>
				<td class="imgBtnWrap">
					<input id="moimPhoto" class="imageInput mouseHand" type="file" name="moimPhoto" onchange="readURL(this)">
					<span class="moimPicUploaded" id="picUploaded1"></span>
				</td>					
			</tr>
						
			<tr class="moimIntroWrap">
				<td><span>* 소모임 소개</span></td>
				<td><textarea spellcheck="false" class="moimIntro" name="moimIntro" id="moimIntro" maxlength=500 required placeholder="500자 이내로 소개를 적어 주세요."></textarea></td>
			</tr>
		</table>
		
		<input type="hidden" name ="userId" value="<%=mdto.getUserId() %>">
		<input type="hidden" name ="userCity" value="<%=mdto.getUserCity() %>">
		<input type="hidden" name ="userDistrict" value="<%=mdto.getUserDistrict() %>">
		<input type="submit" class="makingSubmit mouseHand btn300_50" value="만들기">	
	</form>		
	
		</div>
	
	
	</div>

</body>
</html>