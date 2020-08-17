<%@page import="neighborComm.moimDAO"%>
<%@page import="neighborComm.moimDTO"%>
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

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!-- jstl 라이브러리 사용을 위한 선언  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!-- JQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<%
//세션값으로 id받아오기
String userId=(String)session.getAttribute("userId");	

String pageCall=request.getParameter("pageCall");
if(pageCall == null){pageCall = "moimInfo.jsp";}
	
//모임정보 중 사진 경로 가져오기
request.setCharacterEncoding("utf-8");
String moimNum = request.getParameter("moimNum");  // 모임번호 받아오기
neiBoardDAO ndao = new neiBoardDAO(); 	
moimDTO mdto = ndao.getMoimInfo(moimNum);	

// 모임멤버 몇명인지 가져오기
moimDAO moimdao = new moimDAO();
int memberCount = moimdao.getMoimMemberCount(moimNum);

//운영진 및 멤버 레벨 체크
int myLevel = moimdao.getMyLevel(userId, moimNum); 
%> 

<script type="text/javascript">
	
	function memberCheck2(){		
		var myLevel = $("#myLevel").val();				
		if(myLevel<1){
			alert("모임에 가입해 주세요.");
			return;
		}		
		location.href="moimMemberInfo.ne?moimNum=<%=moimNum%>"
	}
	
	
	function memberCheck1(){		
		var myLevel = $("#myLevel").val();				
		if(myLevel<1){
			alert("모임에 가입해 주세요.");
			return;
		}		
		location.href="list.do?moimNum=<%=moimNum%>"
	}
	
	
</script>
<!-- 자바스크립트 호출 시 전달값 설정 -->
<input type="hidden" id ="myLevel" value="<%=myLevel%>">


<title>VANCO : 우리동네 반려견 커뮤니티</title>
</head>
<body>
	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />	
<div class="bodyWrap">
	
	<div class="moimContentWrap">
		<span class ="moimTitPic imgCenter" style="background-image: url('<%=mdto.getMoimPhoto()%>');"></span>
		<div class="moimContentMenu">
			<a href="moimInfo.ne?moimNum=<%=moimNum%>"><span>모임정보</span></a>
			<a href ="javascript:void(0);" onclick="memberCheck1();"><span>게시판</span></a>
			<a href ="javascript:void(0);" onclick="memberCheck2();"><span>회원정보(<%=memberCount%>)</span></a>
			
		</div>
	
	<!-- 클릭 시 페이지 정보 얻어 오기 -->
	<jsp:include page="<%=pageCall%>"/>
	
	<%
	int pageCallNo=1; 
	if(pageCall.equals("moimBoard.jsp")){pageCallNo=2;}
	else if(pageCall.equals("moimBoardContent.jsp")){pageCallNo=2;}
	else if(pageCall.equals("moimBoardWrite.jsp")){pageCallNo=2;}
	else if(pageCall.equals("moimMemberInfo.jsp")){pageCallNo=3;}
	%>
	
<style>
	.moimContentMenu a:NTH-CHILD(<%=pageCallNo%>) span{color:#64B9FF; font-weight:bold; border-bottom: 4px solid #64b9ff;}

</style>
	</div>
	
	
	
	
	
</div></body>
</html>