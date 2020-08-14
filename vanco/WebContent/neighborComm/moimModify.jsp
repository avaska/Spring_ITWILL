<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="neighborComm.moimDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neighborComm.neiBoardDAO"%>
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

<!-- 자바스크립트파일 연동 -->
<script type="text/javascript" src="<%=path%>/js/neighborComm/moim.js"></script>

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<%	
//세션값으로 id받아오기
String userId=(String)session.getAttribute("userId");		


// 우리동네 소모임 데이터 받아오기
request.setCharacterEncoding("utf-8");
String moimNum = request.getParameter("moimNum");  // 모임번호 받아오기

//모임정보 가져오기
neiBoardDAO ndao = new neiBoardDAO(); 	
moimDTO mdto = ndao.getMoimInfo(moimNum);
String moimIntro = mdto.getMoimIntro();/* .replace("\r\n","<br>").replace("\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"); */
String moimCategory = mdto.getMoimCategory();

// 모임장 id로 모임장 정보 가져오기
MemberDTO memdto = new MemberDTO();
MemberDAO memdao = new MemberDAO();
memdto = memdao.getUserInfo(mdto.getUserId());

String moimLeaderId =  memdto.getUserId();
String moimLeaderPhoto = memdto.getUserPhoto();
String moimLeaderNickname = memdto.getUserNickname();
%>

<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>

<script type="text/javascript">
	//제이쿼리를 이용해 아래쪽의 input type="file" 태그에서 이미지 파일 첨부 미리보기 기능을 구현하는 함수
	function readURL(input){		

		if(input.files && input.files[0]){
		$("#picUploaded1").html("<img class='imgCenter' id='preview1' src='#' />");
		
		/* $("#picUploaded1").html("<img class='imgCenter' id='preview1' src='#' />"); */
		
		// 업로드할 이미지 파일 내용을 읽어올 객체 생성
		var reader=new FileReader();
		
		// 지정한 img 태그에 첫번째 파일 input에 첨부한 파일에 대한 File객체를 읽어 온다.
		reader.readAsDataURL(input.files[0]);
		
		// 파일명을 모두 읽어들였다면(모두 로드했을때)
		reader.onload=function(ProgressEvent){								
			$("#preview1").attr('src',ProgressEvent.target.result);}}}
			
	
/* 	style='background-image:url("+ProgressEvent.target.result+")	 */	
	

</script>





<!-- 헤더영역 -->	
	<jsp:include page="../inc/header.jsp" />	
	
		
<div class="bodyWrap">	
	
	<div class="moimContentWrap">
		<form action="moimModifyAction.ne" method="post" enctype="multipart/form-data" >
			<div class="moimModifyPicWrap">
				<input class="upMoimModifyPic mouseHand" type="file" name="moimPhoto" onchange="readURL(this)" id="upMoimModifyPic">
				<div class ="moimModifyPic imgCenter" style="background-image: url('<%=mdto.getMoimPhoto()%>');"></div>
				<div class="upMoimModifyPicWrap imgCenter" id="picUploaded1"></div>
				<span class="colorCamera45"></span>
			</div>
							
			<div class="moimModify">		
				<span class="midTitle">카테고리</span>
				<div class="modifyCateWrap">
					<input type="radio" id="moimCate1" name="moimCategory" value="산책" class="insertBox" <%if(moimCategory.equals("산책")){%>checked<%}%>>
					<label for="moimCate1" id="label1"></label>
					<input type="radio" id="moimCate2" name="moimCategory" value="교육훈련" class="insertBox" <%if(moimCategory.equals("교육훈련")){%>checked<%}%>>
					<label for="moimCate2" id="label2"></label>
					<input type="radio" id="moimCate3" name="moimCategory" value="친목" class="insertBox" <%if(moimCategory.equals("친목")){%>checked<%}%>>
					<label for="moimCate3" id="label3"></label>
				</div>
						
				<span class="midTitle">모임주제</span>				
				<input type="text" value="<%=mdto.getMoimTitle()%>" name="moimTitle" class="moimTitle" spellcheck="false">
				
				<span class="midTitle">모임소개</span>	
				<textarea class="moimIntroduce" spellcheck="false" name="moimIntro"><%=moimIntro%></textarea>
			</div>
			
			<input type="hidden" value=<%=moimNum%> name="moimNum">
			<input type="hidden" value=<%=mdto.getMoimPhoto()%> name="existMoimPhoto">			
			
			<div class="moimModifyConfirm">
					<input type="submit" class ="btn80_40b mouseHand" value="수정하기">
					<input type="reset" class ="btn80_40b mouseHand" value="다시입력">					
			</div>
		</form>
	</div>
</div>
</body>
</html>