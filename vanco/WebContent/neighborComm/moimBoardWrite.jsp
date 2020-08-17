<%@page import="java.text.SimpleDateFormat"%>
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
<script type="text/javascript" src="<%=path%>/js/neighborComm/moimBoard.js"></script>


<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>VANCO : 우리동네 반려견 커뮤니티</title>

</head>
<body>
	
<div class="writeBackground">
	<form action="insert.do" method="post" id="fr" onsubmit='return filesListing();'>
	<input type="hidden" name="moimNum" id="moimNum" value="${param.moimNum}"> 
	<input type="hidden" name="writer" id="writer" value="<%=(String)session.getAttribute("userNickname")%>"> 
	
		<div class="writeTop">
			<div class="picUpBtnWrap">
				<input class="imageInput mouseHand" type="file" name="filename" multiple accept="image/*" id="fileElem" onchange="handleFiles(this.files)">
				<a href="#" id="fileSelect"><span class="picUploaded" id="picUploaded1"></span></a>					
			</div>
			<div class="noitceWrap">	
				<input type="checkbox" id="noticePin" name="pin">
				<label for="noticePin"></label>
			</div>									
		</div>
		<div class="writeBody">	
								<!-- 파일 목록 시작(js 연결요소) -->
									<div id="fileList">
										<p id="filemsg"></p>
									</div> 
								<!-- 파일 목록 종료 -->																		
			<input class="titleInput" spellcheck="false" type="text" name="title" placeholder="제목" required autofocus >			
			<iframe id="content2" src="about:blank"></iframe>
			<textarea spellcheck="false" rows="40" cols="100" name="content" placeholder="내용을 입력해 주세요"></textarea>									
			<button class="summitBtn btn80_40b mouseHand" type="submit">글쓰기</button>
		</div>
			
	</form>
</div>
	
	
</body>
</html>