<%@page import="dm.dmDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="v"%>   
   
<%
	//세션값으로 필요한 정보 가져오기
	String userId=(String)session.getAttribute("userId");
	String userNickname=(String)session.getAttribute("userNickname");
	String userPhoto=(String)session.getAttribute("userPhoto");
	

	
	//DM 신규 메세지 존재 여부 체크
	dmDAO ddao = new dmDAO();
	int dmReadChk =ddao.countNewDm(userId);
	
%>

<script>
	function noId(){
		alert("로그인후 이용가능 합니다");
	}
</script>


 <!-- 로그인 페이지 url 설정 -->		

<header class="header">		
	<div class="headerWrap">
		<h1>
			<a href="./vanco.me">우리동네 반려견 커뮤니티</a>
		</h1>
		
		<nav class="headLeft">
			<ul>	
				<li><a href="timeLine.ti?"><span id="seeAll">타임라인</span></a></li>				
				<li><a href="list.bo"><span>커뮤니티</span></a></li>
				<%if(userId==null){%>
				<li><a onclick="noId();"><span>우리동네</span></a></li>
				 	
				<%}else{%>
				<li><a href="neighborCommAction.ne?userId=<%=userId%>"><span>우리동네</span></a></li> 
				<%}%>
				<!-- <li><a href="store.st"><span>스토어</span></a></li> -->
			</ul>
		</nav>
		
		<div class="searchBar">
			<div>
				<span class="redPoint"></span>
				<form action="#" method="post">
					<input action = "#" type="text" method="post" placeholder="검색을 원하시나요?">
				</form>
			</div>
		</div>
		
	<!-- 세션값으로 로그인 처리 -->
		
<%	if(userId==null){// 세션값이 없으면 %>
		
	<nav class="headRight">
		<ul>
			<li class="userJoin"><a href="./userJoin.me">회원가입</a></li>			
			<li><a href="userLogin.me">로그인</a></li>				
		</ul>
	</nav>			
		
	<%}else{%>
	<nav class="headRight">
			<ul>
				<li class="dm"><a href="./dmbox.dm"></a>
				<%if(dmReadChk>0){%>
					<span class="redPoint"></span>
				<%}%>					
				</li>				
				<li class="alarm"><a href="#a"></a></li> 
				<!-- <li class="shoppingBasket"><a href="#a"></a></li>	 -->			
				<li class="myPageName"><%=userNickname%></li>		
			</ul>
			<a href="./mypage.me?userId=<%=userId%>" style="background-image: url('<%=userPhoto%>');" class="profilePhoto35"></a>
				
	</nav>
	<%}%>		
	
	</div>	
</header>
	
	
<!--//////////모바일 용 헤더//////////////-->
		
	
<div class="header_m">		
	<div class="headerWrap">
		<a href="index.jsp" class="logo_m"></a>
		<a class="hamBtn_m" href="#"></a>
	 	<a class="alarmBtn_m" href="#"></a>
	</div>	
	
	<div class="mainMenu_m">
		<ul>
			<li><a href="#a"><span>커뮤니티</span></a></li>				
			<li><a href="neighborComm.ne"><span>우리동네</span></a></li>
			<li><a href="#a"><span>갤러리</span></a></li>
			<li><a href="#a"><span>용품후기</span></a></li>
			<li><a href="#a"><span>착한쇼핑</span></a></li>
		</ul>
	</div>
				
		
</div>