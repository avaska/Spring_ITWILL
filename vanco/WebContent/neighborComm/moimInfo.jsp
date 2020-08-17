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

//모임정보 가져오기
neiBoardDAO ndao = new neiBoardDAO(); 	
moimDTO mdto = ndao.getMoimInfo(moimNum);
String moimIntro = mdto.getMoimIntro().replace("\r\n","<br>").replace("\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
int maxQuota =  mdto.getMaxQuota();

// 모임장 id로 모임장 정보 가져오기
MemberDTO memdto = new MemberDTO();
MemberDAO memdao = new MemberDAO();
memdto = memdao.getUserInfo(mdto.getUserId());

String moimLeaderId =  memdto.getUserId();
String moimLeaderPhoto = memdto.getUserPhoto();
String moimLeaderNickname = memdto.getUserNickname();

//모임멤버 정보 가져오기
moimDAO moimdao = new moimDAO();
ArrayList<moimMemberDTO> list= moimdao.getMoimMember(moimNum);
moimMemberDTO mmdto = new moimMemberDTO();

// 모임멤버 몇명인지 count
int memberCount = moimdao.getMoimMemberCount(moimNum);

//운영진 레벨 체크
int myLevel = moimdao.getMyLevel(userId, moimNum); 
%>

<style>	
	/* 회원(Lv1) 이상만 보이게 하기 */
	<%if(myLevel<=0){%>
		.DpNone1{display:none !important;}
	<%}%>
	
	/* 운영진(Lv2) 이상만 보이게 하기 */
	<%if(myLevel<=1){%>
		.DpNone2{display:none !important;}
	<%}%>
	
</style>



<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>
<!-- 자바스크립트 데이터 전송용 데이터 불러오기 -->

<!-- 자바스크립트 데이터 전송용 히든태그 -->
<input type="hidden" id="moimNum" value="<%=moimNum%>">
<input type="hidden" id="userId" value="<%=userId%>">
<input type="hidden" id="path" value="<%=path%>">
<input type="hidden" id="userPhoto" value="<%=session.getAttribute("userPhoto")%>">

	<div class="moimInfo">
		<span class="moimTitle"><%=mdto.getMoimTitle()%></span>
		<div class="leaderProf">
			<span class="profilePhoto40" style="background-image: url('<%=moimLeaderPhoto%>');"></span>
			<p>모임장</p>
			<p><%=moimLeaderNickname%></p>
			
			<%if(userId==null||userId==""){
				out.println("<script>alert('로그인을 해주세요.'); location.href='vanco.me'; </script>");
			}else{			
				if(myLevel==3){%>
				<a class="btn80_40b joinBtn" href="moimModify.ne?moimNum=<%=moimNum%>">모임수정</a>		
				<%}else if(myLevel==0){%>
				<a class="btn80_40b joinBtn" href="moimJoinAction.ne?moimNum=<%=moimNum%>&userId=<%=userId%>&maxQuota=<%=maxQuota%>" onclick="return confirm('가입을 하시겠습니까?')">모임가입</a>	
				<%}else if(myLevel==-1){%>
				<a class="btn80_40b joinBtn joinWait" href="moimJoinCancelAction.ne?moimNum=<%=moimNum%>&userId=<%=userId%>" onclick="return confirm('가입을 취소 하시겠습니까?')">승인대기중</a>	
				<%}
			}%>			
			
		</div>	
		<span class="midTitle forPadd">모임 소개</span>	
		<span class="moimIntroduce"><%=moimIntro%></span>
		
		<div class="joinMember">
			<span class="midTitle">모임 멤버(<%=memberCount%>)</span>
			<%for(int i=0;i<list.size();i++){
				mmdto = list.get(i);
			%>
				<p class="profilePhoto40" style="background-image: url('<%=mmdto.getUserPhoto()%>');"></p>	
			<%}%>						
		</div>
		
	</div>
	
	
	
	<div class="thunderMaking">
		<span class="midTitle">정모/번개</span>
		
		<%if(myLevel>1){%>
		<span class="btn80_40b mouseHand" onclick="thunderMakingToggle();" id="thunderOpen">정모만들기</span>	
		<%}%>
		
		<div class="thunderToggle" id="thunderToggle">			
			<span>번개명</span>
			<input type="text" name="thunderName" id="thunderName" placeholder="20자 이내">
			<span>장소</span>
			<input type="text" name="thunderPlace" id="thunderPlace" placeholder="10자 이내">
			<span>인원</span>
			<select id="thunderPerson">
			<%for(int i = 2; i <= 20; i++){%>
			<option value="<%=i%>"><%=i%></option>
			<%}%>
			</select>	
			<span>날짜</span>	
			<input type="date" name="thunderDate" id="thunderDate">								
		
			<span>시</span>
			<select id="thunderHour">
				<%for (int i = 0; i < 24; i++) {
						if(i < 10){%>
							<option value="0<%=i%>">0<%=i%></option>
				<%} else {%>
				<option value="<%=i%>"><%=i%></option>
				<%}
				}%>
			</select>
			<span>분</span>
			<select id="thunderMinute">
				<%for(int i = 0; i < 60; i=i+10) {
					if(i < 10){%>
						<option value="0<%=i%>">0<%=i%></option>
				<%}else{%>
			<option value="<%=i%>"><%=i%></option>
			<%}
			}%>
			</select>
			
			<div class="thunderConfirm">
				<button id="insertThunder" class="btn80_40b mouseHand" onclick="insertThunder();">만들기</button>
				<button id="thunderCancel" class="btn80_40b mouseHand" onclick="thunderCancel();">취소</button>
			</div>	
		</div>
	</div>
	
	<div id="thunderMoim" >
	<div class="thunderMoim">
		
	</div>
	</div>

	
	
		
<!-- 자바스크립트파일 연동 -->
<script type="text/javascript" src="<%=path%>/js/neighborComm/moim.js"></script>

<!-- 번개리스트 불러오기 -->
<script type="text/javascript">
	printThunderList();	
</script>


</body>
</html>