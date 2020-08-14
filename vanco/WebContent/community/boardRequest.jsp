<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="commSec">	
	<form action="request.bo" method="post" enctype="multipart/form-data">
		<div class="insertBtn">
			<label for="name">게시판명</label>
			<input type="text" name="name" id="name" class="insertBox" placeholder="게시판명을 입력해 주세요." required autofocus>
		</div>
		<div class="insertBtn">
			<label for="category">카테고리</label>
			<select name="category" id="category">
				<option value="game">게임</option>
				<option value="sport">스포츠</option>
				<option value="issue">이슈</option>
				<option value="love">연예</option>
			</select>
			
			<div class="picUploadedWrap">
				<ul>
				<li><span>&nbsp;&nbsp;메인 사진</span></li>
				<li class="imgBtnWrap">
					<input id="moimPhoto" class="imageInput mouseHand" type="file" name="commPhoto" onchange="readURL(this)">
					<span class="moimPicUploaded" id="picUploaded1"></span>		
				</li>
			</ul>
		</div>
		</div>
		<button class="clickBtn requestClick" type="submit">게시판 만들기</button>	
	</form>
</div>

<script type="text/javascript">
	//제이쿼리를 이용해 아래쪽의 input type="file" 태그에서 이미지 파일 첨부 미리보기 기능을 구현하는 함수
	function readURL(input){		

		if(input.files && input.files[0]){
		$("#picUploaded1").html("<img id='preview1' src='#' />");
		
		// 업로드할 이미지 파일 내용을 읽어올 객체 생성
		var reader=new FileReader();
		
		// 지정한 img 태그에 첫번째 파일 input에 첨부한 파일에 대한 File객체를 읽어 온다.
		reader.readAsDataURL(input.files[0]);
		
		// 파일명을 모두 읽어들였다면(모두 로드했을때)
		reader.onload=function(ProgressEvent){				
			console.debug(ProgressEvent)				
			$("#preview1").attr('src',ProgressEvent.target.result);}}}

</script>