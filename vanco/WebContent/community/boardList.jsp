<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:forEach items="${array}" var="a">
<!-- 		<div class="commColumn" > -->
			<div class="commCard" onclick="location.href='board.bo?moimNum=${a.num}';">
				<c:if test="${a.photo != null}">
				<span class="commTnPic imgCenter" style="background-image: url('commUpload/title/tn_${a.photo}');"></span>
				</c:if>
				<c:if test="${a.photo == null}">
				<span class="commTnPic imgCenter" style="background-image: url('/vc/images/man.jpg');"></span>
				</c:if>
				<!-- <img style="width:auto; height:70px;" src="commUpload/title/tn_${a.photo}" onerror="this.src='/vc/images/man.jpg';"> --> 
				<div class="commContainer">				
					<h4><b>${a.name}</b></h4>	
					<p>${a.category}</p>
				</div>
			</div>
<!-- 		</div> -->
	</c:forEach>
