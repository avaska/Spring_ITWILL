<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<div class="h2TitleLeft">
	<h2>오늘의 인기 사진</h2>
	<a href="">전체보기</a>
</div>

<div class="todayPicWrap">


<c:forEach var="todayPicPrint" items="${todayPic}">				
	<div class="todayPic">	
		<div class="todayPicWrap1">
			<a href="#"><img alt="오늘의 인기 갤러리 사진" src="images/galary4.jpg"></a>
			<div class="todayRankWrap"><span class="todayRankNum">1</span></div>
		</div>		
		<div class="todayPicWrap2">
			<div class="todayTitle"><a href="#">${todayPicPrint.content}</a></div>
			<div class="todayNick"><a href="#">${todayPicPrint.userNickname}</a></div>
			<span class="todayFavo">좋아요 ${todayPicPrint.likesCount} · 댓글 ${todayPicPrint.replyCount}</span>
		</div>
	</div>
</c:forEach>

</div>
