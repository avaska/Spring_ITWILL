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

/* // id로 사용자 정보 가져오기
MemberDTO mdto = new MemberDTO();
MemberDAO mdao = new MemberDAO();
mdto = mdao.getUserInfo(mdto.getUserId()); */
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
<c:set var="timeLinelist" value="${requestScope.timeLinelist}"/>

<!-- 타임라인 사진정보 가져온 리스트 -->
<c:set var="photoList" value="${requestScope.photoList}"/>

<!-- 타임라인 사진 갯수 가져오기 -->
<c:set var="photoCount" value="${requestScope.photoCount}"/>

<!-- 사용자 정보, 댓글 정보 가져온 리스트 -->
<c:set var="replyList" value="${requestScope.replyList}"/>

<div class="bodyWrap">	
<div class="timeLineWrap">

	
	
<%-- <c:forEach var="timeLinePrint" items="${timeLinelist}">
	<div class="timeContentWrap"> 
		<div class="profileWrap">
			<span class="profilePhoto50 mouseHand" style="background-image: url('${timeLinePrint.userPhoto}');"></span>
			<span class="mouseHand" >${timeLinePrint.userNickname}</span>
			<div>
				<span>${timeLinePrint.userCity} ${timeLinePrint.userDistrict}</span> · <span>${timeLinePrint.writeTimeStr}</span>			
			</div>
							
			<div class="dotBtnWrap">
				<span class="mouseHand dot3Btn" id="dotMenuToggle" onclick="dotMenuToggle(${timeLinePrint.timeLineNum});">...</span>
				<div class="dotMenu closed" id="dotMenu${timeLinePrint.timeLineNum}">
					<a>수정하기</a>
					<a>삭제하기</a>
					<a>관리자에 신고하기</a>					
				</div>
			</div>	
			
			<c:set var="timeLineNum" value="${timeLinePrint.timeLineNum}"/>
											
		</div>
		<div class="txtWrap">
			<p> ${timeLinePrint.title}</p>
			<p> ${timeLinePrint.content}</p>				
		</div>
				
		<div class="imgWrap">									
			<c:forEach  var="timePhotoPrint" items="${photoList}" varStatus="status">			
				<c:if test="${ timeLinePrint.timeLineNum ==  timePhotoPrint.timeLineNum }">
					<span id="timePhoto${status.index}" class="imgCenter tpClosed timePhoto${timeLinePrint.timeLineNum}" style="background-image: url('${timePhotoPrint.photoUrl}');"></span>						
				</c:if>									
			</c:forEach>
	
		 	<c:forEach  var="photoCountPrint" items="${photoCount}" varStatus="status">			
		 		<c:choose>	
		 			<c:when test="${photoCountPrint.photoCount<=1}"></c:when>
		
					<c:when test="${photoCountPrint.photoCount>1}">
						<c:if test="${ timeLinePrint.timeLineNum ==  photoCountPrint.timeLineNum }">
							<p id="photoToggle${timeLinePrint.timeLineNum}" onclick="photoToggle(${timeLinePrint.timeLineNum}, ${photoCountPrint.photoCount});"><span>+${photoCountPrint.photoCount}</span></p>						
						</c:if>
					</c:when>
				</c:choose>									
			</c:forEach>
		</div>	
			
	 
<!-- 댓글달기 창 -->	
		<div class="reWriteWrap">
			<span class="profilePhoto40" style="background-image: url('<%=userPhoto%>');"></span>
			<div class="reInputWrap">
				<input id="replyContent${timeLinePrint.timeLineNum}" type="text" placeholder="댓글을 입력해 보세요.">
				<span class="btn70_35r_r mouseHand" onclick="writeReply(${timeLinePrint.timeLineNum});">게시</span>
			</div>
		</div>

		<!-- 댓글 insert용 Wrap -->
		<div class="allReWrap" id="allReWrap${timeLinePrint.timeLineNum}">		
			<!-- 댓글 부분 자바스크립트로 불러오기 -->
			<script type="text/javascript">
				printReplyList(${timeLinePrint.timeLineNum});
				
				$(document).ready(function(){/* 댓글리스트 3개만 빼고 강제 닫기 */
					replyClose(${timeLinePrint.timeLineNum});
				});
			</script>
		</div> <!-- class allReWrap 끝 -->
	
		<!-- 좋아요 insert용 Wrap -->
		<div id="favoriteWrap${timeLinePrint.timeLineNum}" class="favoriteWrap">
			<!-- 좋아요 부분 자바스크립트로 불러오기 -->
			<script type="text/javascript">printFavorite(${timeLinePrint.timeLineNum},'${timeLinePrint.userId}');</script>						
		</div>	
	</div>
</c:forEach> --%>
	
<a class="upPhoto colorCamera45" href="timeLineWrite.ti"></a>


<script type="text/javascript">
 printTimeInfo(0, 4);  // 무한스크롤 호출을 위한 시작페이지, 회당 추가 페이지 수 전송
</script>

</div>
</div>
</body>
</html>