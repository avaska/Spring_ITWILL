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
		// 크롬 콘솔에 담아온 태그 출력하여 주소 확인
		console.debug(input);
		console.debug(input.files);
		console.debug(input.length);
					
		//참고 : <input type="file>인 태그 객체의 files속성을 호출하면
		// FileList 배열이 생성되어 FileList 배열 내부의 0번째 인덱스 위치에
		// 선택한 이미지 파일 정보들을 key:value 값으로 저장한 file 객체가 저장되어 있음.
		
		
		//FileList 배열이 존재하고 && FileList배열의 0번째 인데스 위치에 파일 객체가 저장되어 있다면(즉, 인풋태그에서 파일을 선택 했다면)
			
			// 동적으로 <img>태그를 만들어서 id속성값이 tdImg인 <td>태그 영역에 추가 함.
			if(input.files && input.files[0]){
			$("#picUploaded1").html("<img id='preview1' src='#' width=75 height=75 />");
			
			// 업로드할 이미지 파일 내용을 읽어올 객체 생성
			var reader=new FileReader();
			
			// 지정한 img 태그에 첫번째 파일 input에 첨부한 파일에 대한 File객체를 읽어 온다.
			reader.readAsDataURL(input.files[0]);
			
			// 파일명을 모두 읽어들였다면(모두 로드했을때)
			reader.onload=function(ProgressEvent){
				// 읽어들인 file객체에 매개변수로 넘어로는 ProgressEvent 객체 내부의 target속성에 대응되는 객체로 저장되어 있다.
				// 또한 JSON객체 데이터 내부에는 result 속성에 읽어들인 file객체 정보가 저장되어 있다. 
				console.debug(ProgressEvent)
				
				//id가 preview인 <img>태그에 attr 메소드를 이용해 파일 첨부시 미리보기 이미지를 나타내기 위해 src속성값을
				// newFileReader()객체가 읽어들인 첨부할 차일 객체 정보를 지정하여 추가하여 미리보기가 가능해진다.
				$("#preview1").attr('src',ProgressEvent.target.result);}}}
	
	
	function readURL2(input){			
			if(input.files && input.files[0]){
			$("#picUploaded2").html("<img id='preview2' src='#' width=75 height=75 />");
			var reader=new FileReader();			
			reader.readAsDataURL(input.files[0]);
			reader.onload=function(ProgressEvent){
				console.debug(ProgressEvent)
				$("#preview2").attr('src',ProgressEvent.target.result);}}}
	
	function readURL3(input){			
		if(input.files && input.files[0]){
		$("#picUploaded3").html("<img id='preview3' src='#' width=75 height=75 />");
		var reader=new FileReader();			
		reader.readAsDataURL(input.files[0]);
		reader.onload=function(ProgressEvent){
			console.debug(ProgressEvent)
			$("#preview3").attr('src',ProgressEvent.target.result);}}}
	
	
	function readURL4(input){			
		if(input.files && input.files[0]){
		$("#picUploaded4").html("<img id='preview4' src='#' width=75 height=75 />");
		var reader=new FileReader();			
		reader.readAsDataURL(input.files[0]);
		reader.onload=function(ProgressEvent){
			console.debug(ProgressEvent)
			$("#preview4").attr('src',ProgressEvent.target.result);}}}
	
	
	
</script>




</head>

<%	//글 작성 시 input태그에 히든값으로 처리할 정보 세션에서 가져오기
	String userId=(String)session.getAttribute("userId");
	String userNickname=(String)session.getAttribute("userNickname");
	String userPhoto=(String)session.getAttribute("userPhoto");

	
	if(userId==null || userId==""){out.println("<script>alert('로그인후 작성 가능합니다.'); history.back(); </script>");}	
%>

<body>

	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />		

<div class="bodyWrap">		
	<div class="writeBackground">
	<form action="neiBoardWriteAction.ne" method="post" enctype="multipart/form-data" >
		<div class="writeTop">
			<div class="picUploadedWrap">
				<input class="imageInput mouseHand" type="file" name="upPhoto1" onchange="readURL(this)">
				<span class="picUploaded" id="picUploaded1"></span>
				
				<input class="imageInput mouseHand" type="file" name="upPhoto2" onchange="readURL2(this)">
				<span class="picUploaded" id="picUploaded2"></span>
				
				<input class="imageInput mouseHand" type="file" name="upPhoto3" onchange="readURL3(this)">
				<span class="picUploaded" id="picUploaded3"></span>
				
				<input class="imageInput mouseHand" type="file" name="upPhoto4" onchange="readURL4(this)">
				<span class="picUploaded" id="picUploaded4"></span>
				
				
			</div>
			<button type="submit">글쓰기</button>
											
		</div>
		<div class="writeBody">
														
				<input spellcheck="false" type="text" name="subject" placeholder="제목" required autofocus >
				<textarea spellcheck="false" rows="40" cols="100" name="content" placeholder="내용을 입력해 주세요" required></textarea>
				
				<input type="hidden" name="userId" value="<%=userId%>">
				<input type="hidden" name="userNickname" value="<%=userNickname%>">
				<input type="hidden" name="userPhoto" value="<%=userPhoto%>">
					
		</div>
	</form>
	</div>
</div>
</body>
</html>