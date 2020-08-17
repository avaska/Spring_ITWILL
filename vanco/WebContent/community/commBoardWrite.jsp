<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div class="writeBackground">
	<form action="insert.bo" method="post" id="fr" onsubmit='return filesListing();'>
	<input type="hidden" name="moimNum" id="moimNum" value="${param.moimNum}"> 
	<input type="hidden" name="writer" id="writer" value="<%=(String)session.getAttribute("userId")%>"> 
	
		<div class="writeTop">
			<div class="picUpBtnWrap">
				<input class="imageInput mouseHand" type="file" name="filename" multiple accept="image/*" id="fileElem" onchange="handleFiles(this.files)">
				<a href="#" id="fileSelect"><span class="picUploaded" id="picUploaded1"></span></a>					
			</div>
			<div class="noitceWrap">	
				<input type="checkbox" id="noticePin" name="pin">
				<label for="noticePin"></label>
			</div>									
		</div>
		<div class="writeBody">	
								<!-- 파일 목록 시작(js 연결요소) -->
									<div id="fileList">
										<p id="filemsg"></p>
									</div> 
								<!-- 파일 목록 종료 -->																		
			<input class="titleInput" spellcheck="false" type="text" name="title" placeholder="제목" required autofocus >			
			<iframe id="content2" src="about:blank"></iframe>
			<textarea spellcheck="false" rows="40" cols="100" name="content" placeholder="내용을 입력해 주세요"></textarea>									
			<button class="summitBtn btn80_40b mouseHand" type="submit">글쓰기</button>
		</div>
			
	</form>
</div>
<script type="text/javascript" src="${path}/js/community/commBoardWrite.js"></script>

