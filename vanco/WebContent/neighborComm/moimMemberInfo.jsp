<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="neighborComm.moimMemberDTO"%>
<%@page import="neighborComm.moimDAO"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@page import="neighborComm.moimDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="neighborComm.neiBoardDAO"%>
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

<%	
//세션값으로 id받아오기
String userId=(String)session.getAttribute("userId");		


// 우리동네 소모임 데이터 받아오기
request.setCharacterEncoding("utf-8");
String moimNum = request.getParameter("moimNum");  // 모임번호 받아오기

//모임장 정보 가져오기
neiBoardDAO ndao = new neiBoardDAO(); 	
moimDTO mdto = ndao.getMoimInfo(moimNum);
String moimLeader = mdto.getUserId(); 

//운영진 및 멤버 레벨 체크
moimDAO moimdao = new moimDAO();
int myLevel = moimdao.getMyLevel(userId, moimNum); 


//모임멤버 정보 가져오기
ArrayList<moimMemberDTO> list= moimdao.getMoimMember(moimNum);
moimMemberDTO mmdto = new moimMemberDTO();


//승인대기멤버 정보 가져오기
ArrayList<moimMemberDTO> wList= moimdao.getWaitingMember(moimNum);
moimMemberDTO wdto = new moimMemberDTO();



//나이계산 위한 오늘 년도 가져오기
Date now = new Date();
SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
int nowTime=Integer.parseInt(sdf.format(now));

%>

<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>
	<div class="moimMemberInfo">	
		<div class="moimMember">
			<span class="midTitle">멤버현황</span>	
			
			<%for(int i=0;i<list.size();i++){
				mmdto = list.get(i);
				int lv=mmdto.getUserLevel();
				String level="";
				if(lv==2){level="운영진";}else if(lv==3){level="모임장";}				
				%>
			
			<div class="moimMemberInfoWrap">
				<p class="profilePhoto50" style="background-image: url('<%=mmdto.getUserPhoto()%>');"></p>
				<p><%=mmdto.getUserNickname()%></p>
				<p><%=mmdto.getUserCity()%> <%=mmdto.getUserDistrict()%>  · <%=nowTime-mmdto.getUserBirth()%>세  ·  <%=mmdto.getUserGender()%></p>
				<p><%=level%></p>
				
				<%if(myLevel>1){// 모임리더에게만 버튼 보이기				
					if(lv==3){	//모임리더칸에는 경고 강퇴버튼등 안나오게 하기
					%>
					<%}else{%>
						<div class="memberCtrlBtn">
						<%if(lv==2){%>
							<%if(myLevel==3){%><a class="btn60_30bh lvCancel" href="updateLevelAction.ne?userId=<%=mmdto.getUserId()%>&level=<%=lv%>&moimNum=<%=moimNum%>">운영진취소</a><%}%>
						<%}else{%>
							<a class="btn60_30bh" href="updateLevelAction.ne?userId=<%=mmdto.getUserId()%>&level=<%=lv%>&moimNum=<%=moimNum%>" onclick="return confirm('운영진 등록 시 번개모집, 경고 및 강퇴, 가입승인 권한이 주어집니다. 운영진으로 등록 하시겠습니까?')">운영진등록</a>	
						<%}%>						
						<a class="btn60_30bh" href="updateCautionAction.ne?userId=<%=mmdto.getUserId()%>&moimNum=<%=moimNum%>&caution=<%=mmdto.getCaution()%>" onclick="return confirm('3회 경고 누적 시 강제퇴장 됩니다. 경고를 진행하시겠습니까?')">경고 <%=mmdto.getCaution()%></a>
						<a class="btn60_30bh" href="forcedMoimMemberDelAction.ne?userId=<%=mmdto.getUserId()%>&moimNum=<%=moimNum%>" onclick="return confirm('강제 퇴장 후 재가입이 불가합니다. 강퇴를 진행하시겠습니까?')">강퇴</a>
					</div>
				<%}}%>
			</div>		
			<%} //for문 종료%>
		</div>
		
		
	<%if(myLevel>1){// 운영진에게만 승인대기멤버 정보 보이기%>	
			
		<div class="moimMember">		
		
			<span class="midTitle">승인 대기 멤버</span>	
						
			<%if(wList.size()==0){%>
				<p class="noMemComment">승인 대기 멤버가 없습니다.</p>
			<%}			
			for(int i=0;i<wList.size();i++){
				wdto = wList.get(i);					
			%>
			
			<div class="moimMemberInfoWrap">
				<p class="profilePhoto50" style="background-image: url('<%=wdto.getUserPhoto()%>');"></p>
				<p><%=wdto.getUserNickname()%></p>
				<p><%=wdto.getUserCity()%> <%=wdto.getUserDistrict()%>  · <%=nowTime-wdto.getUserBirth()%>세  ·  <%=wdto.getUserGender()%></p>
				<div class="memberCtrlBtn2">
					<a class="btn60_30bh" href="joinWaitDelAction.ne?userId=<%=wdto.getUserId()%>&moimNum=<%=moimNum%>" onclick="return confirm('정말로 삭제 하시겠습니까?')">삭제</a>
					<a class="btn60_30bh" href="joinConfirmAction.ne?userId=<%=wdto.getUserId()%>&moimNum=<%=moimNum%>">가입승인</a>
				</div>
			</div>
			<%}%>			
		</div>
		<%}%>
		
		
	</div>
	

</body>
</html>