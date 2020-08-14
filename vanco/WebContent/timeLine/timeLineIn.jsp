<%@page import="timeLine.timeLineDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<%String path=request.getContextPath();%>
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/timeLine/timeLine.css" type="text/css" rel="stylesheet">
<!-- <script src="../js/plugin/load-image.all.min.js"></script> -->

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JNDI 함수 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!-- jstl 라이브러리 사용을 위한 선언  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<%	
//세션값으로 id//사진/닉넴 받아오기
String userId=(String)session.getAttribute("userId");		
String userPhoto=(String)session.getAttribute("userPhoto");	
String userNickname=(String)session.getAttribute("userNickname");	

String timeLineNum = request.getParameter("timeLineNum");
%>
</head>
<!-- 자바스크립트 파일에 가져갈 정보 저장하기 -->
<input type="hidden" id="path" value="<%=path%>">
<input type="hidden" id="userId" value="<%=userId%>">
<input type="hidden" id="userPhoto" value="<%=userPhoto%>">

<!-- 자바스크립트파일 연동(반드시 히든태그 밑으로 넣어야 함!!!) -->
<script type="text/javascript" src="<%=path%>/js/timeLine/timeLine.js"></script>



<%-- <%	//글 작성 시 input태그에 히든값으로 처리할 정보 세션에서 가져오기
	String userId=(String)session.getAttribute("userId");
	String userNickname=(String)session.getAttribute("userNickname");
		
	if(userId==null || userId==""){out.println("<script>alert('로그인을 해 주세요.'); location.href='./userLogin.me'; </script>");}	

%>
 --%>
<body>

<!-- 헤더영역 -->	
	<jsp:include page="../inc/header.jsp" />		

<!-- 사용자 정보, 타임라인 기본정보 가져온 리스트 -->
<c:set var="printDto" value="${tdto}"/>


<div class="bodyWrap">	
<div class="timeLineWrap">


	<div class="timeContentWrap"> 
		<div class="profileWrap">
			<span class="profilePhoto50 mouseHand" style="background-image: url('${printDto.userPhoto}');" onclick="location.href='yourPage.me?yourId=${printDto.userId}'"></span>
			<span class="mouseHand" >${printDto.userNickname}</span>
			<div>
				<span>${printDto.userCity} ${printDto.userDistrict}</span> · <span>${printDto.writeTimeStr}</span>			
			</div>
							
			<div class="dotBtnWrap">
				<span class="mouseHand dot3Btn" id="dotMenuToggle" onclick="dotMenuToggle(${printDto.timeLineNum});">...</span>
				<div class="dotMenu closed" id="dotMenu${printDto.timeLineNum}">
					<a>수정하기</a>
					<a>삭제하기</a>
					<a>관리자에 신고하기</a>					
				</div>
			</div>	
			
			<c:set var="timeLineNum" value="${printDto.timeLineNum}"/>
											
		</div>
		<div class="txtWrap">
			<p> ${printDto.title}</p>
			<p> ${printDto.content}</p>				
		</div>
				
		<div class="imgWrapIn">									
			<c:forEach  var="photoPrint" items="${photoList}" varStatus="status">			
				<span class="imgCenter" style="background-image: url('${photoPrint.photoUrl}');"></span>						
			</c:forEach>
		</div>	
			
	 
<!-- 댓글달기 창 -->	
		<div class="reWriteWrap">
		<%if(userId==null){%>
			<span class="profilePhoto40" style="background-image: url('<%=userPhoto%>');"></span>
			<div class="reInputWrap">
				<input id="replyContent<%=timeLineNum%>" type="text" placeholder="로그인을 해주세요" disabled>
				<span class="btn70_35r_r mouseHand" onclick="">게시</span>
			</div>
		<%}else{%>
			<span class="profilePhoto40" style="background-image: url('<%=userPhoto%>');"></span>
			<div class="reInputWrap">
				<input id="replyContent<%=timeLineNum%>" type="text" placeholder="댓글을 입력해 보세요.">
				<span class="btn70_35r_r mouseHand" onclick="writeReply('<%=timeLineNum%>', 1);">게시</span>
			</div>
		<%}%>
		</div>

		<!-- 댓글 insert용 Wrap -->
		<div class="allReWrap" id="allReWrapIn">		
			<!-- 댓글 부분 자바스크립트로 불러오기 -->
			<script type="text/javascript">
				printReplyListIn('<%=timeLineNum%>');				
			</script>
		</div> <!-- class allReWrap 끝 -->
	
		<!-- 좋아요 insert용 Wrap -->
		<div id="favoriteWrap<%=timeLineNum%>" class="favoriteWrap">
			<!-- 좋아요 부분 자바스크립트로 불러오기 -->
			<script type="text/javascript">printFavorite('<%=timeLineNum%>','${timeLinePrint.userId}');</script>						
		</div>	
	</div>
	
<a class="upPhoto colorCamera45" href="timeLineWrite.ti"></a>
</div>
</div>
</body>
</html>