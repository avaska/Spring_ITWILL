<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
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

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<title>VANCO : 우리동네 반려견 커뮤니티</title>

</head>
<body>
<div class="moimBoardContentWrap">
	<div class="contentHead">
		<p class=moimContentTit>${article.title}</p>
		<div class="moimContentProf">
			<a href="yourPage.me?yourId=<%="아이디"%>"></a>
			<a class="moimContentNick" href="yourPage.me?yourId=<%=""%>">${article.writer}</a>
			<span class="moimContentInfo">${article.regdate} <%-- · 조회수<%=3%> --%></span>
		</div>
		<div class="moimContentBtn">
		
				<a href="#" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</a>
				<a href="#">수정</a>
				<a href="">신고하기</a>				
		</div>
	</div>
	
	<div class="contentBody">
		<span>${article.content}</span>			
	</div>
	
	
<!---------------------------------------------------------------------->	
<!------------------------- 댓글 작성 및 보여주기 부분 ------------------------->
<!---------------------------------------------------------------------->

	<!-- 원댓글 입력창 -->
	<div class="writeReplyWrap">
		<span></span><img class="profilePhoto40" alt="프로필사진" src='<%=""%>' onerror="this.src='/vc/images/man.jpg';">
		<div class="writeReply">
		
			<span class="reNick">닉네임 님</span>
			<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>
			<span>0/100</span>
			<form onsubmit="return forSubmit()" action="neiReplyAction.ne" name="neiReply" method="post" >
		
				<input id="neiReplyInput" class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength=100 required>
				<input class="btn60_35" type="submit" value="등록" onClick="window.location.reload(true)"> <!-- onClick="window.location.reload()"  -->
					
			</form>
		</div>		
	</div>		
	
	<div class="replyWrap">				 
		<div class="replyContent">
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>닉네임</span>
				<span>댓글내용 댓글내용댓글내용댓글내용댓글내용댓글내용
						<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>										
				</span>
			</div>					
			<div class="replyInfo">
				<span>2019.08.09 18:00</span>							
				<span class="mouseHand" >댓글</span>				
				<span><a href="">신고</a></span>												
			</div>
		</div>
		<div class="replyContent">
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>닉네임</span>
				<span>댓글내용 댓글내용댓글내용댓글내용댓글내용댓글내용본문글본문글 본문글  본문글본문글본문글본문글본문글문글본문글본문글본문
						<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>										
				</span>
			</div>					
			<div class="replyInfo">
				<span>2019.08.09 18:00</span>							
				<span class="mouseHand" >댓글</span>				
				<span><a href="">신고</a></span>												
			</div>
		</div>
		<div class="replyContent">
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>닉네임</span>
				<span>댓글내용 댓글내용댓글내용댓글내용댓글내용댓글내용
						<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>										
				</span>
			</div>					
			<div class="replyInfo">
				<span>2019.08.09 18:00</span>							
				<span class="mouseHand" >댓글</span>				
				<span><a href="">신고</a></span>												
			</div>
		</div>
		<div class="replyContent">
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>닉네임</span>
				<span>댓글내용 댓글내용댓글내용댓글내용댓글내용댓글내용
						<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>										
				</span>
			</div>					
			<div class="replyInfo">
				<span>2019.08.09 18:00</span>							
				<span class="mouseHand" >댓글</span>				
				<span><a href="">신고</a></span>												
			</div>
		</div>
		<div class="replyContent">
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>닉네임</span>
				<span>댓글내용 댓글내용댓글내용댓글내용댓글내용댓글내용
						<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>										
				</span>
			</div>					
			<div class="replyInfo">
				<span>2019.08.09 18:00</span>							
				<span class="mouseHand" >댓글</span>				
				<span><a href="">신고</a></span>												
			</div>
		</div>
		
		
		<div class="replyContent2">
			<span class="replyAngle"></span>
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>넥네임2</span>
				<span><b>@너님</b>&nbsp;&nbsp; 대댓글을 달아 드립니다%>
					<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>
				</span>
			</div>					
			<div class="replyInfo">						
				<span>2019.03.11 10:22</span>
				<span class="mouseHand">댓글</span>
				<span><a href="">신고</a></span>										
			</div>
		</div>
		<div class="replyContent2">
			<span class="replyAngle"></span>
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>넥네임2</span>
				<span><b>@너님</b>&nbsp;&nbsp; 대댓글을 본문글본문글 본문글  본문글본문글본문글본문글본문글문글본문글본문글본문달아 드립니다%>
					<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>
				</span>
			</div>	
			
			
			<!-- 대댓글 코딩용---------------->
	
			<div class="writeReply2" >
				
				<span class="reNick">닉네임 님</span>
				<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>
				<span>0/100</span>
				<span class="xBox1 mouseHand" ></span>
				
				<form action="neiReReplyAction.ne" name="neiReply2" method="post" >		
					<input class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength="100" required>
					<input class="btn60_35" type="submit" value="등록"  onClick="window.location.reload(true)" id="reNickSubmit"> 
						
					
				</form>
			</div>
		<!-- 대댓글 창 : 대댓글 코딩용 끝---------------->
			
			
							
			<div class="replyInfo">						
				<span>2019.03.11 10:22</span>
				<span class="mouseHand">댓글</span>
				<span><a href="">신고</a></span>										
			</div>
		</div>
		<div class="replyContent2">
			<span class="replyAngle"></span>
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>넥네임2</span>
				<span><b>@너님</b>&nbsp;&nbsp; 대댓글을 달아 드립니다%>
					<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>
				</span>
			</div>					
			<div class="replyInfo">						
				<span>2019.03.11 10:22</span>
				<span class="mouseHand">댓글</span>
				<span><a href="">신고</a></span>										
			</div>
		</div>
		<div class="replyContent2">
			<span class="replyAngle"></span>
			<div class="seeReply">
				<img class="profilePhoto40 mouseHand" src="" onerror="this.src='/vc/images/man.jpg';">
				<span>넥네임2</span>
				<span><b>@너님</b>&nbsp;&nbsp; 대댓글을 달아 드립니다%>
					<a class="deleteBin" onclick="return confirm('정말 삭제하시겠습니까?')"></a>
				</span>
			</div>					
			<div class="replyInfo">						
				<span>2019.03.11 10:22</span>
				<span class="mouseHand">댓글</span>
				<span><a href="">신고</a></span>										
			</div>
		</div>
	
	</div>	
	
			
	
				

	<!-- footer구역-->
		<jsp:include page="../inc/footer.jsp" />


</div>
</body>
</html>