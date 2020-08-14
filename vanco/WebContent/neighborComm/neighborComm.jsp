<%@page import="neighborComm.moimDAO"%>
<%@page import="neighborComm.moimDTO"%>
<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="neighborComm.neiBoardDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neighborComm.neiBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/neighborComm/neighborComm.css" type="text/css" rel="stylesheet">


<!-- 모바일 버전 CSS 미디어쿼리 -->
<link href="../index_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="../css/common_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="../css/plugin/jquery.bxslider_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="../header_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">

<!-- 모바일용 뷰포트 메타태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- jstl 라이브러리 사용을 위한 선언  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


<script src="neighborComm.js"></script>
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>


<%// 세션값으로 id 받아오기
String userId=(String)session.getAttribute("userId");

// 우리동네 소모임 데이터 받아오기
request.setCharacterEncoding("utf-8");		
	
MemberDAO mdao = new MemberDAO();	 	
MemberDTO mdto = mdao.getUserInfo(userId);

String userCity = mdto.getUserCity();
String userDistrict = mdto.getUserDistrict();

neiBoardDAO nedao = new neiBoardDAO();
ArrayList<moimDTO> moimList = nedao.getMoimListByDisgtrict(userId, userCity, userDistrict);
%>


</head>

<body>
	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />	
<div class="bodyWrap">

<!------------------------------- 왼쪽 사이드 부분 ------------------------------------>
	
<div class="bodyWrapLeft">
	
	<!-- 우리동네 소통광장 -->	
		<jsp:include page="../neighborComm/neiBoard.jsp"/>	
		
		
	<!-- 우리동네 개설된 소모임 -->	
	
	<div class="h2Title">
		<h2><%=userDistrict%> theKoo 소모임</h2>
		<!-- <a href="#">전체보기</a> -->
	</div>
	
	<div class="neiGatherWrap">
	<%
			 	
	 	for(int i=0;i<moimList.size();i++){
	 		moimDTO moDTO = moimList.get(i);
	 		String moimCategory = moDTO.getMoimCategory();%>
	 		
	 		<div class="neiGather">
			<div class="neiGatherLeft">
				<img class="neiGatherPic" alt="" src=""/>
				<a href="moimContent.ne?moimNum=<%=moDTO.getMoimNum()%>"><%=moDTO.getMoimTitle()%></a>				
				<span></span>
				<span>회원수 : <%=moDTO.getMemberCount()%>/<%=moDTO.getMaxQuota()%></span>
				<span></span>
				<img class="profilePhoto40 mouseHand"alt="" src="" onclick="location.href='#'"/>
		<%if(moimCategory.equals("산책")){%> 
				<span class="btn70_70R"><b><%=moimCategory%></b></span>
		<%}else if(moimCategory.equals("교육훈련")){%> 
				<span class="btn70_70Y"><b><%=moimCategory%></b></span>
		<%}else if(moimCategory.equals("친목")){%> 
				<span class="btn70_70G"><b><%=moimCategory%></b></span>
		<%}%>					
				<span><%=moDTO.getMoimIntro()%></span>	
			</div>			
		</div>	
	 	<%}%>					
	</div>		
	
			
</div>
	
	
<!------------------------------- 오른쪽 사이드 부분 ------------------------------------>
	
<div class="bodyWrapRight">

	<!-- 오른쪽 구역 1. 조회수 top5 -->	
		
	<div class="h2TitleLeft">
		<h2><%=userDistrict%>  투데이 Best</h2>
	</div>
	<div class="neiBestClickWrap">
<%
// 필요한 변수 준비
	neiBoardDAO ndao = new neiBoardDAO();
	ArrayList<neiBoardDTO> list = new ArrayList<neiBoardDTO>();
	
// DB검색하여 결과 리턴 받기
	list = ndao.getNeiBestList();

	for(int i=0; i<list.size();i++){
		neiBoardDTO ndto = list.get(i);	%>			
		
		<div class="neiBestClick">	
			<div class="neiBestWrap1">
				<a class="imgCenter mouseHand" style="background-image: url('<%=ndto.getUpPhoto1()%>');" href="neiBoardContent.ne?num=<%=ndto.getNum()%>&pageNum=<%=ndto.getContentPageNum()%>" ></a>
			</div>		
				
			<div class="neiBestWrap2">
				<div class="neiBestRankWrap"><span class=""><%=i+1%></span></div>
				<a class="neiBestTitle" href="neiBoardContent.ne?num=<%=ndto.getNum()%>&pageNum=<%=ndto.getContentPageNum()%>"><%=ndto.getSubject()%></a>
				<a class="profilePhoto35" style="background-image: url('<%=ndto.getUserPhoto()%>');" href="yourPage.me?yourId=<%=ndto.getUserId()%>" ></a>
				<a class="neiBestNick" href="yourPage.me?yourId=<%=ndto.getUserId()%>"><%=ndto.getUserNickname()%></a>
				<span class="neiBestFavo">조회수 <%=ndto.getReadCount()%> · 댓글 <%=ndto.getReplyCount()%></span>				
			</div>
		</div>		
		<%}%>	
		<span class="algoMent">인기글 알고리즘에 의해 추천되며, 매일 자정 갱신됩니다.</span>	
	</div>	
	
		
	<div class="h2TitleLeft myGatherTit">
		<h2>나의 theKoo모임</h2>
	</div>
	
	<div class="myGatherWrap">
		<%ArrayList<moimDTO> myList = ndao.getMyMoimList(userId);	
		for(int i=0;i<myList.size();i++){
			moimDTO modto = myList.get(i);%>		
		
		<div class="myGather">
			<span class="mouseHand" onclick="location.href='#'"><%=modto.getMoimTitle()%></span>
			<span>다음모임 : <%=modto.getMakingTime()%></span>
			<span>3개의 새글이 올라 왔습니다.</span>
		</div>
		<%}%>
	</div>
	
	
	<div>
		<a href="moimMaking.ne" class="btn320_55 gatherMake">더쿠모임 만들기</a>
	</div>
	
	
</div>


	
	
<!-- footer구역-->		
		<jsp:include page="../inc/footer.jsp" />
		
		
		
</div>
	
	
</body>
</html>