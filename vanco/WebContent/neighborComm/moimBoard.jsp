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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- pop-Icon에러 방지 연동 -->
<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>

	
	<div class="moimBoardWrap">
	
			
		<span class="boardTitle">모임게시판</span>
		<a href="write.do?moimNum=${param.moimNum}" class="writeBtn">글쓰기</a>
		
		<c:forEach items="${array}" var="arr">	
		<c:if test="${arr.pin > 0}">		
		<div class="boardContWrap">		
			<p class="gongjiIcon">공지</p>		
			<span class="span1 mouseHand" onclick="location.href='count.do?moimNum=${param.moimNum}&idx=${arr.idx}'">${arr.title}</span>
			<span class="span2 camera1"></span>
			<span  class="span3">${arr.writer}</span>
			<span  class="span4"><fmt:formatDate value="${arr.regdate}" pattern="MM.dd HH:mm"/></span>											
		</div>
		</c:if>
		
		<c:if test="${arr.pin == 0}">		
		<div class="boardContWrap">		
			<span class="span1 mouseHand" onclick="location.href='count.do?moimNum=${param.moimNum}&idx=${arr.idx}'">${arr.title}   [${arr.countcomm}]</span>
			<span class="span2 camera1"></span>
			<span  class="span3">${arr.writer}</span>
			<span  class="span4"><fmt:formatDate value="${arr.regdate}" pattern="MM.dd HH:mm"/></span>											
		</div>
		</c:if>
		</c:forEach>
				
	</div>
	
	
</body>
</html>