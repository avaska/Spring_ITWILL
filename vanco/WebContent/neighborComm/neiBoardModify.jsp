<%@page import="neighborComm.neiBoardDTO"%>
<%@page import="neighborComm.neiBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="/vc/css/neighborComm/neighborComm.css" type="text/css" rel="stylesheet">
<script src="/vc/css/neighborComm/neighborComm.js"></script>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>


<script type="text/javascript">
	//제이쿼리를 이용해 아래쪽의 input type="file" 태그에서 이미지 파일 첨부 미리보기 기능을 구현하는 함수
	function readURL(input){	//<input type="file>인 태그 전체를 매개변수로 전달 받음"
		
			if(input.files && input.files[0]){
			$("#picUploaded1").html("<img id='preview1' src='#' width=75 height=75 />");			
			var reader=new FileReader();			
			reader.readAsDataURL(input.files[0]);			
			reader.onload=function(ProgressEvent){		
			$("#preview1").attr('src',ProgressEvent.target.result);}}}
	
	
	function readURL2(input){			
			if(input.files && input.files[0]){
			$("#picUploaded2").html("<img id='preview2' src='#' width=75 height=75 />");
			var reader=new FileReader();			
			reader.readAsDataURL(input.files[0]);
			reader.onload=function(ProgressEvent){
				$("#preview2").attr('src',ProgressEvent.target.result);}}}
	
	function readURL3(input){			
		if(input.files && input.files[0]){
		$("#picUploaded3").html("<img id='preview3' src='#' width=75 height=75 />");
		var reader=new FileReader();			
		reader.readAsDataURL(input.files[0]);
		reader.onload=function(ProgressEvent){
			$("#preview3").attr('src',ProgressEvent.target.result);}}}
	
	function readURL4(input){			
		if(input.files && input.files[0]){
		$("#picUploaded4").html("<img id='preview4' src='#' width=75 height=75 />");
		var reader=new FileReader();			
		reader.readAsDataURL(input.files[0]);
		reader.onload=function(ProgressEvent){
			$("#preview4").attr('src',ProgressEvent.target.result);}}}
	
</script>


<%	// 수정하기 클릭 시 전달된 num, pageNum을 받아온다
	request.setCharacterEncoding("utf-8");
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	// 글번호 num으로 글정보 가져와 DTO에 저장하기
	neiBoardDAO ndao = new neiBoardDAO();
	neiBoardDTO ndto = ndao.getContent(num);
	
	// 주소 강제 입력 시 로그인 안한 사람은 수정 불가 처리
	String userId=(String)session.getAttribute("userId");
	if(userId==null || userId==""){out.println("<script>alert('로그인후 수정 가능합니다.'); location.href='neighborComm.ne'; </script>");}	

%>

</head>


<body>

	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />		

<div class="bodyWrap">		
	<div class="modifyBackground">
	<form action="neiBoardModifyAction.ne" method="post" enctype="multipart/form-data" >
		<div class="modifyTop">
			<div class="picUploadedWrap">
				<input class="imageInput mouseHand" type="file" name="upPhoto1" onchange="readURL(this)">
				<span class="picUploaded" id="picUploaded1"></span>
				<img src="<%=ndto.getUpPhoto1()%>" class="picUploaded" onerror="this.style.display='none'">
				
				<input class="imageInput mouseHand" type="file" name="upPhoto2" onchange="readURL2(this)">
				<span class="picUploaded" id="picUploaded2"></span>
				<img src="<%=ndto.getUpPhoto2()%>" class="picUploaded" onerror="this.style.display='none'">
				
				<input class="imageInput mouseHand" type="file" name="upPhoto3" onchange="readURL3(this)">
				<span class="picUploaded" id="picUploaded3"></span>
				<img src="<%=ndto.getUpPhoto3()%>" class="picUploaded" onerror="this.style.display='none'">
				
				<input class="imageInput mouseHand" type="file" name="upPhoto4" onchange="readURL4(this)">
				<span class="picUploaded" id="picUploaded4"></span>
				<img src="<%=ndto.getUpPhoto4()%>" class="picUploaded" onerror="this.style.display='none'">
				
				<!-- 히든태그로 글번호와 페이지, 기존 파일경로 전달 -->
				<input type="hidden" name="num" value="<%=num%>">
				<input type="hidden" name="pageNum" value="<%=pageNum%>">
				<input type="hidden" name="existPic1" value="<%=ndto.getUpPhoto1()%>">
				<input type="hidden" name="existPic2" value="<%=ndto.getUpPhoto2()%>">
				<input type="hidden" name="existPic3" value="<%=ndto.getUpPhoto3()%>">
				<input type="hidden" name="existPic4" value="<%=ndto.getUpPhoto4()%>">
				
			</div>
			<button type="submit">수정완료</button>
										
		</div>
		<div class="modifyBody">														
				<input type="text" name="subject" value="<%=ndto.getSubject()%>" spellcheck="false">
				<textarea name="content" spellcheck="false"><%=ndto.getContent()%></textarea>					
		</div>
	</form>
	</div>
</div>
</body>
</html>