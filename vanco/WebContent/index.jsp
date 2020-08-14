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
<link href="<%=path%>/css/index/index.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/inc/header.css" type="text/css" rel="stylesheet">

<!-- 모바일 버전 CSS 미디어쿼리 -->
<link href="/vanco/index_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/css/common_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/css/plugin/jquery.bxslider_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/header_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">

<!-- 자바스크립트파일 연동 -->
<script type="text/javascript" src="<%=path%>/js/index/index.js"></script>

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- bxSlider 플러그인 연동 -->
<link href="<%=path%>/css/plugin/jquery.bxslider.css" type="text/css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<script type="text/javascript">
/* bx슬라이더 */
$(document).ready(function(){
	$('.slider').bxSlider({
		speed:500, //0.5초
		pager:true, //페이지 숫자 표시를 노출을 제어(false:숨김, true:노출)
		moveSlides:1, //슬라이드 이동 갯수 설정
		slideWidth:660, // 슬라이드 폭
		minSlides:1,	//최소 노출 슬라이드
		maxSlides:4, // 최대 노출 슬라이드
		slideMargin:30, // 슬라이드간 간격
		auto:true,	// 자동으로 넘어감
		autoHover:true, // 마우스가 올라오면 자동정지 시키는 설정
		controls:true, // 이전/다음 텍스트 숨김(false), 노출(true)
		/* mode:'fade', */
	});
});
</script>

<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>


<!-- 헤더영역 -->
	<jsp:include page="inc/header.jsp"/>
	
<div class="bodyWrap">

<!------------------------------- 왼쪽 사이드 부분 ------------------------------------>
	
<div class="bodyWrapLeft">
	
	<!-- 메인 슬라이드 -->	
		<jsp:include page="inc/mainSlide.jsp"/>	
	<!-- 위클리 인기 갤러리 -->	
	<div class="h2Title">
		<h2>이번주의 인기 Pic.</h2>
		<a>전체보기</a>
	</div>
	
	<div class="weeklyPop">
	<c:forEach var="weeklyPicPrint" items="${weeklyPic}" varStatus="rank">
		<div class="popDogPic">
			<a href="timeLineInAction.ti?timeLineNum=${weeklyPicPrint.timeLineNum}" class="weeklyImgWrap imgCenter" style="background-image: url('${weeklyPicPrint.photoUrl}');" ></a>
			<span class="weeklyTit">${weeklyPicPrint.content}</span>
			<a href="#" class="weeklyNick">${weeklyPicPrint.userNickname}</a>
			<span class="heartIcon weeklyHeart"></span>
			<span class="weeklyHeartNum">${weeklyPicPrint.likesCount}</span>
			<span class="replyIcon"></span>
			<span class="weeklyHeartNum">${weeklyPicPrint.replyCount}</span>
			<div><span>${rank.count}</span></div> 					
		</div>
	</c:forEach>	
		<span>최근 일주일간 조회수, 좋아요, 댓글수를 종합한 알고리즘으로 추천됩니다.</span>							
	</div>	





	<!-- 훈련과 행동교정 매거진 -->	
	<%-- 	<jsp:include page="inc/mainMagazine.jsp"/>		 --%>
	
	<!-- 오늘의 조회수 베스트 -->		
	<div class="h2Title">
		<h2>투데이 댓글 베스트</h2>
		<a>전체보기</a>
	</div>
	<div class="bestClick">
		<c:forEach var="todayBoardPrint" items="${todayBoard}" varStatus="rank">	
		<div class="bestClickWrap"><div class="bestClickLine">
			<div class="bestClickRank"><span>${rank.count}</span></div>
			<div class="bestClicktTit"><a href="content.bo?moimNum=${todayBoardPrint.moimNum}&idx=${todayBoardPrint.idx}">${todayBoardPrint.title}</a></div>
			<span class="cameraIcon"></span>
			<div class="bestClicktNick"><a href="">${todayBoardPrint.boardName} 게시판</a></div>
			<span class="bestClicktInfo">${todayBoardPrint.userNickname} · 댓글수${todayBoardPrint.replyCount}</span>
		</div></div>
		</c:forEach>	
	</div>	
	

	<!-- 견종별 커뮤니티 -->		
	<div class="h2Title">
		<h2>theKoo커뮤니티 Top6</h2>
		<a href="">전체보기</a>
	</div>
	
	<div class="dogKindBoard">
		<c:forEach var="top6Print" items="${commTop6}" varStatus="rank">
		<div class="dogKindPic">
			<a href="board.bo?moimNum=${top6Print.moimNum}" >
				<span class="commImg imgCenter" style="background-image: url('images/woman.jpg');" ></span>
				<span class="kindBoardTit">${top6Print.boardName}</span>
				<span class="kindBoardRank">${rank.count}위</span>
				<span class="kindBoardNum">게시글 ${top6Print.contentCount}개</span>							
			</a>	
			<button class="starIconOn"></button>	
		</div>
		</c:forEach>
	</div>

	<!-- 반커스토어 스테디 셀러 -->
		<%-- <jsp:include page="inc/steadySeller.jsp"/>		 --%>
</div>
	
	
<!------------------------------- 오른쪽 사이드 부분 ------------------------------------>	
<div class="bodyWrapRight">

	<!-- 오른쪽 구역 1. 최신글 -->	
		
	<div class="h2TitleLeft">
		<h2>최신글</h2>
		<a>전체보기</a>
	</div>
	
	<c:forEach var="latestBoardPrint" items="${latestBoard}" >
	<div class="todayPost">
		<div class="todayPostTit"><a href="content.bo?moimNum=${latestBoardPrint.moimNum}&idx=${latestBoardPrint.idx}">${latestBoardPrint.title}</a></div>
		<div class="todayPostNick"><a href="">${latestBoardPrint.boardName} 게시판</a></div>
		<span class="todayPostInfo">${latestBoardPrint.writeTimeStr}</span>
	</div>
	</c:forEach>
	
	
	<!-- 오른쪽 구역 2. 오늘의 인기 사진 -->	
	<%-- <jsp:include page="inc/todayPic.jsp"/>	 --%>	
	
	<div class="h2TitleLeft">
		<h2>오늘의 인기 사진</h2>
		<!-- <a href="">전체보기</a> -->
	</div>

	<div class="todayPicWrap">			
	<c:forEach var="todayPicPrint" items="${todayPic}" varStatus="idx">				
		<div class="todayPic">	
			<div class="todayPicWrap1">
				<a href="timeLineInAction.ti?timeLineNum=${todayPicPrint.timeLineNum}" class="imgCenter" style="background-image: url('${todayPicPrint.photoUrl}');" ></a>
				<div class="todayRankWrap"><span class="todayRankNum">${idx.count}</span></div>
			</div>		
			<div class="todayPicWrap2">
				<div class="todayTitle"><a href="timeLineInAction.ti?timeLineNum=${todayPicPrint.timeLineNum}">${todayPicPrint.content}</a></div>
				<div class="todayNick"><a href="#">${todayPicPrint.userNickname}</a></div>
				<span class="todayFavo"><span class="heartIcon"></span> ${todayPicPrint.likesCount} · 댓글 ${todayPicPrint.replyCount}</span>
			</div>
		</div>
	</c:forEach>
	</div>	

<!-- ------------------------------------푸터영역---------------------------------- -->
	<%-- 	<jsp:include page="inc/footer.jsp"/> --%>
		
</div>
</body>
</html>