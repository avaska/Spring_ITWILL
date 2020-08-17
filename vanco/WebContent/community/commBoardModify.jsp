<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<c:set var="path" value="${pageContext.request.contextPath}"/>

<div class="writeBackground">
<form action="modifyFinish.bo" method="post" id="fr" onsubmit='return filesListing();'>
	<input type="hidden" name="moimNum" id="moimNum" value="${param.moimNum}"> 
	<input type="hidden" name="idx" id="idx" value="${param.idx}"> 
	<input type="hidden" name="writer" id="writer" value="<%=(String)session.getAttribute("userId")%>">
		<div class="writeTop">
			<div class="picUpBtnWrap">
				<input class="imageInput mouseHand" type="file" name="filename" multiple accept="image/*" id="fileElem" onchange="handleFiles(this.files)">
				<a href="#" id="fileSelect"><span class="picUploaded" id="picUploaded1"></span></a>					
			</div>
			<div class="noitceWrap">	
				<c:if test="${article.pin > 0}">
				<input type="checkbox" id="noticePin" name="pin" checked>
				</c:if>
				<c:if test="${article.pin == 0}">
				<input type="checkbox" id="noticePin" name="pin">
				</c:if>
				<label for="noticePin"></label>
			</div>									
		</div>
		<div class="writeBody">	
								<!-- 파일 목록 시작(js 연결요소) -->
									<div id="fileList">
										<p id="filemsg"></p>
										<ul>
										<c:forEach items="${filesList}" var="fl">
											<li id="file${fl.num}">
											${fl.filename} 
											<a href="javascript:void(0);" onclick="insertMyImageAction('${fl.filename}')">삽입</a>  
											<a href="javascript:void(0);" onclick="deleteMyImageAction(${fl.num})">삭제</a>
											</li>
										</c:forEach>
										</ul>
									</div> 
								<!-- 파일 목록 종료 -->																		
			<input class="titleInput" spellcheck="false" type="text" name="title" value="${article.title}" placeholder="제목" required autofocus >			
			<iframe id="content2" src="about:blank"></iframe>
			<textarea spellcheck="false" rows="40" cols="100" name="content" placeholder="내용을 입력해 주세요">${article.content}</textarea>									
			<button class="summitBtn btn80_40b mouseHand" type="submit">수정</button>
		</div>
			
	</form>
	<a href="board.bo?moimNum=${article.moimNum}">목록으로</a>
</div>
<script type="text/javascript" src="${path}/js/community/commBoardModify.js"></script>

