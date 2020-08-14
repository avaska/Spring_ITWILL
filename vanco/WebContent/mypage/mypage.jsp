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
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="/vc/css/mypage/mypage.css" type="text/css" rel="stylesheet">
<!-- <script src="../js/plugin/load-image.all.min.js"></script> -->

<!-- JNDI 사용을 위한 태그 선언 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  


<script type="text/javascript">
	
	function readURL(input){	//<input type="file>인 태그 전체를 매개변수로 전달 받음	
		var formtag = document.dogPicUpload;
		formtag.submit();
	}	

	function readURL2(input){	
		var formtag = document.userPicUpload;
		formtag.submit();
		}
</script>
</head>

<%	//글 작성 시 input태그에 히든값으로 처리할 정보 세션에서 가져오기
	String userId=(String)session.getAttribute("userId");
	String userNickname=(String)session.getAttribute("userNickname");
		
	if(userId==null || userId==""){out.println("<script>alert('로그인을 해 주세요.'); location.href='./userLogin.me'; </script>");}	


	// DB접속하여 user 정보 가져오기.
	MemberDAO mdao = new MemberDAO();
	MemberDTO mdto = new MemberDTO();
	
	mdto = mdao.getUserInfo(userId);
	
	// 필요 정보 저장
	String dogPhoto=mdto.getDogPhoto();
	String userPhoto=mdto.getUserPhoto();	
	
	// 나이계산 위한 오늘 년도 가져오기
	Date now = new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	int nowTime=Integer.parseInt(sdf.format(now));
	
		
%>

<body>

	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />		

<div class="bodyWrap">	
<div class="myPageWrap">

	<div class="profileWrap">
	
		<div class="dogPicWrap">
			<form action="editMyPhotoAction.me" method="post" enctype="multipart/form-data" name="dogPicUpload">		
				<input class="dogImageInput mouseHand" type="file" name="upDogPhoto" onchange="readURL(this)" id="upDogPhoto">
				<span style="background-image: url('<%=dogPhoto%>');"  class="imgCenter existDogPic" ></span>
				<span class="upDogPicWrap" id="upDogPicWrap"></span>			
				
				<button type="submit"></button>					
				
				<input type="hidden" name="userId" value="<%=userId%>">
				<input type="hidden" name="existDogPic" value="<%=dogPhoto%>">
				<input type="hidden" name="existUserPic" value="<%=userPhoto%>">				
			</form>
		</div>	
		
		<div class="userPicWrap">
				<form action="editMyPhotoAction.me" method="post" enctype="multipart/form-data" name="userPicUpload">		
					<input class="userImageInput mouseHand" type="file" name="upUserPhoto" onchange="readURL2(this)">
					<span style="background-image: url('<%=userPhoto%>');" class="imgFace existUserPic "></span>
					<span class="upUserPicWrap" id="upUserPicWrap"></span>
					
					<button type="submit"></button>	
					
					<input type="hidden" name="userId" value="<%=userId%>">
					<input type="hidden" name="existDogPic" value="<%=dogPhoto%>">
					<input type="hidden" name="existUserPic" value="<%=userPhoto%>">	
				</form>
		</div>	
		<span class="colorCamera30 dogCamera"></span>
		<span class="colorCamera25 userCamera"></span>
	
		<div class="nameBox">
			<span><%=mdto.getUserNickname() %> 님</span>
			
			<% String userPosition=mdto.getUserPosition();
			if(userPosition==null || userPosition==""){
				out.println("<script>alert('로그인을 해 주세요.'); location.href='./userLogin.me'; </script>");
			}else if(userPosition.equals("미등록")){%>
			<a href="./mandatoryInfo.me" class="profileEdit">정보등록</a>	
			<%}else{%>
			<a href="./mypageEdit.me" class="profileEdit">정보수정</a>
			<%}%>			
			<a href="logout.me" class="logout" onclick="return confirm('로그아웃 하시겠습니까?')">로그아웃</a>
		</div>
	
		<div class="userInfoWrap">
			<table>
				<tr>
					<td>프로필</td>
					<td>지역</td>
					<td>등급</td>
				</tr>
				<tr>
				
				<%if(userPosition==null || userPosition==""){
					out.println("<script>alert('로그인을 해 주세요.'); location.href='./userLogin.me'; </script>");
				}else if(userPosition.equals("미등록")){%>
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
	</div>

	
<!-- --------------------마이쇼핑 부분 ----------------
	<div class="myShoppingWrap">
	
		<div class="h2Title myShoppingTitle">
			<h2>My 쇼핑</h2>
			
		</div>
		<table>
				<tr>
					<th>주문번호</th>
					<th>주문일시</th>
					<th>상품명</th>
					<th>배송상태</th>
					<th>수령확인</th>
				</tr>
				<tr>
					<td>2019032801001</td>
					<td>2019.03.28</td>
					<td>일이삼사오륙칠팔구...</td>
					<td>배송완료</td>
					<td>
						<a href="#">리뷰쓰기</a>
						<a href="#">환불요청</a>   
						<a href="#">교환요청</a>
					</td>
				</tr>
				<tr>
					<td>2019032801001</td>
					<td>2019.03.28</td>
					<td>일이삼사오륙칠팔구...</td>
					<td>상품준비중</td>
					<td> 
						<a href="#">주문취소</a>
					</td>
				</tr>
				<tr>
					<td>2019032801001</td>
					<td>2019.03.28</td>
					<td>일이삼사오륙칠팔구...</td>
					<td>배송중</td>
					<td> 
						<a href="#">배송조회</a>
						<a href="#">환불요청</a>
						<a href="#">교환요청</a>
					</td>
				</tr>
				<tr>
					<td>2019032801001</td>
					<td>2019.03.28</td>
					<td>일이삼사오륙칠팔구...</td>
					<td>배송완료</td>
					<td>
						<a href="#">리뷰쓰기</a>
						<a href="#">환불요청</a>   
						<a href="#">교환요청</a>
					</td>
				</tr>
				<tr>
					<td>2019032801001</td>
					<td>2019.03.28</td>
					<td>일이삼사오륙칠팔구...</td>
					<td>배송완료</td>
					<td>
						<a href="#">리뷰쓰기</a>
						<a href="#">환불요청</a>   
						<a href="#">교환요청</a>
					</td>
				</tr>
		</table>	
	</div>
	
		------>


	<!-- --------------------마이갤러리 부분 ---------------------->	
	<div class="myGalWrap">
	
		<div class="h2Title myGalTitle">
			<h2>My 갤러리</h2>
			<!-- <a>전체보기</a> -->
		</div>
		
		<c:forEach var="myPicPrint" items="${myPic}" varStatus="idx">	
		<div class="myGal mouseHand" onclick="location.href='#'">			
			<p class="imgCenter" style="background-image: url('${myPicPrint.photoUrl}');" ></p>
			<span>비공개</span>
		</div>
		</c:forEach>
	</div>
	




</div>
</div>
</body>
</html>