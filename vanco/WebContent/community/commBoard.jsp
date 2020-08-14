<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div class="commBoardWrap">
			
		<span class="boardTitle">${commName}</span>
		<a href="write.bo?moimNum=${param.moimNum}" class="writeBtn">글쓰기</a>
		<c:if test="${commPhoto != null}">
		<span class ="commTitPic imgCenter" style="background-image: url('commUpload/title/bg_${commPhoto}');"></span>
		</c:if>
		<!--  <img style="width:auto; height:100px;" src="commUpload/title/bg_${commPhoto}" onerror="this.src='/vc/images/man.jpg';"> -->
		
		<c:forEach items="${array}" var="arr">	
			
		<div class="boardContWrap">		
		<c:if test="${arr.pin > 0}">	
			<p class="gongjiIcon">공지</p>	
			</c:if>	
			<span class="span1 mouseHand" onclick="location.href='count.bo?moimNum=${param.moimNum}&idx=${arr.idx}'">${arr.title} [${arr.countcomm}]</span>
			<c:if test="${arr.countfile > 0}">
			<span class="span2 camera1"></span>
			</c:if>
			<span  class="span3">${arr.writer}</span>
			<span  class="span4">
				<fmt:formatDate value="${arr.regdate}" pattern="MM/dd HH:mm"/>
			</span>											
		</div>

		</c:forEach>
				
	</div>