<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jstl 라이브러리 사용을 위한 선언  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>VANCO : 우리동네 반려견 커뮤니티</title>

<!-- pop-Icon에러 방지 연동 -->
<link rel="shortcut icon" href="data:image/x-icon;," type="image/x-icon">

<!-- CSS 연결 -->
<link href="${path}/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="${path}/css/index/index.css" type="text/css" rel="stylesheet">
<link href="${path}/css/index/common.css" type="text/css" rel="stylesheet">
<link href="${path}/css/comm/community.css" type="text/css" rel="stylesheet">
<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<!-- 헤더영역 -->
	<jsp:include page="../inc/header.jsp" />

	<div class="bodyWrap">

		<div class="commContentWrap">
			<div class="commContentMenu">
				<a href="${path}/list.bo"><span>게시판목록</span></a>
				<a href="${path}/form.bo"><span>게시판만들기</span></a> 
				<a href="board.bo?moimNum=${param.moimNum}"><span>${commName}게시판</span></a>
				
				<%-- 
				<c:if test="${not empty commName}">
					<a href="board.bo?moimNum=${param.moimNum}"><span>${commName}게시판</span></a>
				</c:if>
				<c:if test="${empty commName}">
					<a href="javascript:void(0);"><span>게시판</span></a>
				</c:if> --%>
				
			</div>
			
	<jsp:include page="${url}" />
	
		</div>

	</div>
</body>
</html>