<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="/vc/css/mypage/mypage.css" type="text/css" rel="stylesheet">
<title>VANCO : 우리동네 반려견 커뮤니티</title>



<%	// 미 로그인 시 접근 불가 처리
	String userId=(String)session.getAttribute("userId");	
	if(userId==null || userId==""){out.println("<script>alert('로그인을 해 주세요.'); location.href='./vanco.me'; </script>");}	
	
	//프로필사진 클릭 시 함께 넘어온 Id정보 가져오기.
	String yourId = request.getParameter("yourId");
	System.out.println(yourId);

	// DB접속하여 user 정보 가져오기.
	MemberDAO mdao = new MemberDAO();
	MemberDTO mdto = new MemberDTO();
	
	
	mdto = mdao.getUserInfo(yourId);
	
	// 필요 정보 저장
	String dogPhoto=mdto.getDogPhoto();
	String userPhoto=mdto.getUserPhoto();	
	
	// 나이계산 위한 오늘 년도 가져오기
	Date now = new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	int nowTime=Integer.parseInt(sdf.format(now));
	
	// 클릭대상자 탈퇴 시 탈퇴 내용 안내
	String userPosition=mdto.getUserPosition();
	
	//현재 페이지 URL
	String url = request.getRequestURL().toString();
	
	if(userPosition==null || userPosition==""){
		out.println("<script>alert('탈퇴한 회원입니다'); history.back();  </script>");
	}else{%>

<body>

	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />
	

<div class="bodyWrap">	
<div class="myPageWrap">

	<div class="profileWrap">
	
		<div class="dogPicWrap">			
			<span style="background-image: url('<%=dogPhoto%>');"  class="imgCenter existDogPic" ></span>				
		</div>	
		
		<div class="userPicWrap">
			<span style="background-image: url('<%=userPhoto%>');" class="imgFace existUserPic "></span>		
		</div>	
		
		<div class="nameBox">
			<span><%=mdto.getUserNickname() %></span>
			<a href="dmSend.dm?receiveUserId=<%=yourId%>&receiveNickname=<%=mdto.getUserNickname()%>&url=<%=url%>" class="profileEdit">쪽지쓰기</a>			
		</div>
	
		<div class="userInfoWrap">
			<table>
				<tr>
					<td>프로필</td>
					<td>지역</td>
					<td>등급</td>
				</tr>
				<tr>
				<%if(mdto.getUserPosition().equals("미등록")){%>
					<td><%=mdto.getUserGender() %> · 미등록</td>	
				<%}else{%>
					<td><%=mdto.getUserGender() %> · <%=nowTime - mdto.getUserBirth()%>세</td>	
				<%}%>				
					<td><%=mdto.getUserCity()%> <%=mdto.getUserDistrict()%></td>
					<td><%=mdto.getUserPosition()%></td> 
				</tr>
				<tr >
					<td colspan="3">한줄 소개</td>
				</tr>
				<tr>
					<td colspan="3"><%=mdto.getUserComment() %></td>
				</tr>
			</table>
		</div>	
		
		
		<!-- --------------------your갤러리 부분 ---------------------->	
	<div class="myGalWrap">
	
		<div class="h2Title myGalTitle">
			<h2><%=mdto.getUserNickname() %>님 갤러리</h2>
			<!-- <a>전체보기</a> -->
		</div>
		
		<div class="myGal mouseHand" onclick="location.href='#'">			
			<img src="/vanco/images/galary3.jpg">
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary4.jpg">
			<span>비공개</span>
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary5.jpg">
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary6.jpg">
			<span>비공개</span>
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary3.jpg">
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary3.jpg">
			<span>비공개</span>
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary3.jpg">
		</div>
		<div class="myGal">
			<img src="/vanco/images/galary3.jpg">		
		</div>		
	</div>
		
		
		
	</div>

<%} // 최상단 탈퇴회원 if-else끝%>
</div>
</div>
</body>
</html>