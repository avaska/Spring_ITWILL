<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="/vc/css/dm/dm.css" type="text/css" rel="stylesheet">
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">


<!-- 모바일 버전 CSS 미디어쿼리 -->
<link href="/vanco/index_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/css/common_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/header_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">

<!-- 모바일용 뷰포트 메타태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">

<!-- jQuery 연동 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<%
// 쪽지 쓸때 함께 hidden으로 보낼 정보들 가져 오기
request.setCharacterEncoding("utf-8");
String sendUserId=(String)session.getAttribute("userId");
String sendNickname=(String)session.getAttribute("userNickname");
String receiveUserId = request.getParameter("receiveUserId");
String receiveNickname = request.getParameter("receiveNickname");
String url = request.getParameter("url");
System.out.println("dmSend.jsp페이지임, 얻은 주소"+url);
%>


<body>
	<!-- 헤더영역 -->	
	<jsp:include page="../inc/header.jsp" />	

	<div class="dmWrap">
		<div class="h2Title dmWrapTitle">
			<h2>쪽지 보내기</h2>
			<!-- <a>전체보기</a> -->
		</div>
		<div class="dmSendWrap">
			<div class="dmSendBox">
				<span>받는사람 : <%=receiveNickname%></span>
				<span>광고, 비방글, 욕설은 신고 및 강제탈퇴 될 수 있습니다.</span>
				<span>0/100</span>
				
				<form action="dmSendAction.dm" method="post" >			
					<textarea class="dmTyping mouseHand" spellcheck="false" name="dmContent" placeholder="내용을 입력해 주세요" maxlength="100" required></textarea>
					<input class="btn60_35" type="submit" value="보내기"> 
					
					<!-- 히든태그로 사용자 정보를 함께 보냄 -->
					<input type="hidden" name="sendUserId" value=<%=sendUserId%>>
					<input type="hidden" name="sendNickname" value=<%=sendNickname%>>	
					<input type="hidden" name="receiveUserId" value=<%=receiveUserId%>>	
					<input type="hidden" name="receiveNickname" value=<%=receiveNickname%>>		
					<input type="hidden" name="url" value=<%=url%>>						
				</form>
			</div>		
		</div>
		
		
		
	
	</div>

</body>
</html>