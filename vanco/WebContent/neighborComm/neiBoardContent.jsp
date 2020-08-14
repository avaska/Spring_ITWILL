<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="neighborComm.neiBoardDTO"%>
<%@page import="neighborComm.neiBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%String path=request.getContextPath();%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="<%=path%>/css/index/common.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/css/neighborComm/neighborComm.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<style type="text/css">
 	 .bodyWrapLeft{padding:0; width:720px;} 	 
</style>

<script type="text/javascript">
// 댓글 입력 후 submit

	function forSubmit(){
		var neiReply = document.neiReply;
		neiReply.submit();
	}  // forSubmit() 끝
	
</script>


<%
	// 글제목 클릭 시 글번호와 page번호를 get방식으로 넘겨준것을 받아오기.
	request.setCharacterEncoding("UTF-8");

	int num = Integer.parseInt(request.getParameter("num")); 
	
	String pageNum = request.getParameter("pageNum");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
	
	neiBoardDAO ndao = new neiBoardDAO();	
	
	
/////////////////////// 쿠키를 이용한 조회수 중복 증가 방지  ///////////////////////
		
		// 글 번호를 String으로 가져 온다.
	String bbsno = request.getParameter("num");	
		
	Cookie[] cookies = request.getCookies();
	if(cookies==null || cookies.length==0){
		System.out.println("쿠키없음");
		return;	//실행하지 않고 반환
	}
	
	for(Cookie ck:cookies){
		/* System.out.println("쿠키명"+ck.getName()+" & 쿠키값 :"+ck.getValue());	 */	
	}
	
		// 실제 작업 : 글번호를 받고, 쿠키를 받아와서 쿠키의 name에 VIEWCOOKIE가 있는지 검색 후
			// 없으면 VIEWCOOKIE를 만들어 주고, 있으면 글번호를 추가해준다.
	
	Cookie viewCookie=null;
	
	if(cookies!=null && cookies.length>0){//viewCookie가 있다면
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("VIEWCOOKIE")){//Cookie의 name이 VIEWCOOKIE와 일치하는 쿠키를 viewCookie에 넣어준다.
				viewCookie=cookies[i];				
			}	
		}
	}
	
	if(viewCookie==null){ //viewCookie가 없다면
		Cookie newCookie = new Cookie("VIEWCOOKIE","|"+bbsno+"|");
		response.addCookie(newCookie);
		/* System.out.println("VIEWCOOKIE 없음"); */
	}else{
		String value=viewCookie.getValue();
		/* System.out.println("VIEWCOOKIE 있음"); */
		
			// 쿠키에 해당 글번호가 있는지 없는지 여부흘 확인하여 해당 글이 없다면 쿠키에 글 번호 추가 시키기
			// 글번호가 있다면 if가 실행 안되므로, 조회수 안올라감.
			if(value.indexOf("|"+bbsno+"|")<0){
				value=value+"|"+bbsno+"|";	// 쿠키에 글 번호 추가하기.						
				viewCookie.setValue(value);
				response.addCookie(viewCookie);				
								
				//페이지 진입 시 조회수 1증가 시키기
				ndao.updateReadCount(num);
				/* System.out.println("VIEWCOOKIE에 글번호 저장추가하고 조회수 1 증가 시킴"); */	
			}		
	}
/////////////////////// 쿠키를 이용한 조회수 중복 증가 방지 끝 ///////////////////////	
	
	
	
	// DB로 부터 글 정보 가져오기
	neiBoardDTO ndto = ndao.getContent(num);
	
	int DBnum=ndto.getNum();
	String userId=ndto.getUserId();
	String userNickname=ndto.getUserNickname();
	String userPhoto=ndto.getUserPhoto();
	String upPhoto1=ndto.getUpPhoto1();
	String upPhoto2=ndto.getUpPhoto2();
	String upPhoto3=ndto.getUpPhoto3();
	String upPhoto4=ndto.getUpPhoto4();
	String subject=ndto.getSubject();
	String content=ndto.getContent();
	int re_ref=ndto.getRe_ref();
	int re_lev=ndto.getRe_lev();
	int re_seq=ndto.getRe_seq();
	int readCount=ndto.getReadCount();
	int replyCount=ndto.getReplyCount();
	
	String writeTime=sdf.format(ndto.getWriteTime());
	String ip=ndto.getIp();
	
		
	// DTO 내장메소드를 이용해 그래용 여부를 판별하고,
	// 글내용이 있을때, 사용자가 입력한 엔터키를 DTO내장메소드인 replace()를 이용해 <br>태그로 치환해줌
	String nieBoardContent="";
	if(ndto.getContent()!=null){
		nieBoardContent=ndto.getContent().replace("\r\n","<br>").replace("\t","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	}		
	
%>

</head>

<body>
	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />		
		
<div class="bodyWrap">

<!------------------------------- 왼쪽 사이드 부분 ------------------------------------>
	
<div class="bodyWrapLeft">
	<div class="contentHead">
		<p class=neiContentTit><%=subject%></p>
		<div class="neiContentProf">
				<a href="yourPage.me?yourId=<%=ndto.getUserId()%>"><img class="profilePhoto40" alt="프로필사진" src="<%=userPhoto%>" onerror="this.src='/vc/images/man.jpg';"></a>
				<a class="neiContentNick" href="yourPage.me?yourId=<%=ndto.getUserId()%>"><%=userNickname%></a>
		<%
		//날짜 시간 *분전으로 표시하기
		sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
		Date now = new Date();
		
		Date wTime = sdf.parse(writeTime);
		long writetime = wTime.getTime();
		long nowTime=now.getTime();

		long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산
		
		if(timeGap<1){%>
			<span class="neiContentInfo">지금 · 조회수<%=readCount%></span>
		<%}else if(timeGap>=1 && timeGap<60){%>
			<span class="neiContentInfo"><%=timeGap%>분전 · 조회수<%=readCount%></span>
		<%}else if(timeGap>=60 && timeGap<1440){%>
			<span class="neiContentInfo"><%=timeGap/60 %>시간전 &nbsp; · &nbsp; 조회수<%=readCount%></span>
		<%}else if(timeGap>=1440 && timeGap<43200){%>
			<span class="neiContentInfo"><%=timeGap/60/24 %>일전 &nbsp; · &nbsp; 조회수<%=readCount%></span>
		<%}else if(timeGap>=43200){%>
			<span class="neiContentInfo"><%=timeGap/60/24/30 %>개월전 &nbsp; · &nbsp; 조회수<%=readCount%></span>
		<%}%>		
				
				
		</div>
		<div class="neiContenBtn">
		<% // 글 삭제를 위한 프로세스
			// 글 삭제 권한을 위해 세션값 얻기 : 세션값의 userId와 글 정보의 id비교1!
	
			String idChk=(String)session.getAttribute("userId");
			
			if(idChk!=null){
				if(idChk.equals((userId))){%>
				<a href="neiBoardDelete.ne?num=<%=DBnum%>&pageNum=<%=pageNum%>" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</a>
				<a href="neiBoardModify.ne?num=<%=DBnum%>&pageNum=<%=pageNum%>">수정</a>
				<a href="">신고하기</a>
				<%}else{%>
				<a href="">신고하기</a>
				<%}
			}else{%>
			<a href="">신고하기</a>
			<%}%>
		</div>
	</div>
	
	<div class="contentBody">
		<span><%=nieBoardContent%></span>	
		<div>		
			<img class="neiContentPic1" alt="" src="<%=upPhoto1%>" onerror="this.style.display='none'">
			<img class="neiContentPic1" alt="" src="<%=upPhoto2%>" onerror="this.style.display='none'">
			<img class="neiContentPic1" alt="" src="<%=upPhoto3%>" onerror="this.style.display='none'">
			<img class="neiContentPic1" alt="" src="<%=upPhoto4%>" onerror="this.style.display='none'">		
		</div>	
	</div>
	
	
<!---------------------------------------------------------------------->	
<!------------------------- 댓글 작성 및 보여주기 부분 ------------------------->
<!---------------------------------------------------------------------->
<%
//DB에 저장된 댓글 갯수를 검색하기 위한 getReplyCount()호출
int count = ndao.getReplyCount(DBnum);

//DB에 저장된 댓글 가져와 저장할 변수 선언
ArrayList<neiBoardDTO> list = null;
if(count>0){
	list=ndao.getNeiReply(DBnum);
}

%>


	
<%	//글 작성 시 input태그에 히든값으로 처리할 정보 세션에서 가져오기
String ssUserId=(String)session.getAttribute("userId");
String ssUserNickname=(String)session.getAttribute("userNickname");
String ssUserPhoto=(String)session.getAttribute("userPhoto");

%>
	<!-- 원댓글 입력창 -->
	<div class="writeReplyWrap">
		<span></span>
		<div class="writeReply">
		<% if(ssUserId==null){%>
			<span class="reNick">로그인 후 작성이 가능합니다.</span>
			<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>
			<span>0/100</span>
						
				<input class="reTyping mouseHand" name="reTyping" type="text" placeholder="로그인을 해주세요." readonly>
				<a href="userLogin.me?" class="btn60_35">로그인</a>
					
		<%}else{%>
			<span class="reNick"><%=ssUserNickname%> 님</span>
			<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>
			<span>0/100</span>
			<form onsubmit="return forSubmit()" action="neiReplyAction.ne" name="neiReply" method="post" >
		
				<input id="neiReplyInput" class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength=100 required>
				<input class="btn60_35" type="submit" value="등록" onClick="window.location.reload(true)"> <!-- onClick="window.location.reload()"  -->
		<%}%>			
				<!-- 히든태그로 세션 정보를 함께 보냄 -->
				<input type="hidden" name="userId" value="<%=ssUserId%>">
				<input type="hidden" name="userNickname" value="<%=ssUserNickname%>">
				<input type="hidden" name="userPhoto" value="<%=ssUserPhoto%>">
				
				
				<input type="hidden" name="contentNum" value="<%=DBnum%>">
				<input type="hidden" name="contentPageNum" value="<%=pageNum%>">
			

				<input type="hidden" name="re_ref" value="<%=re_ref%>">
				<input type="hidden" name="re_lev" value="<%=re_lev%>">
				<input type="hidden" name="re_seq" value="<%=re_seq%>">	
				
				
			</form>
		</div>		
	</div>		
	
	<div class="replyWrap">
	
	<%	// 아래에 댓글 보여주기!!
		//댓글 정보가 있다면, for문으로 뿌려줌
		if(count>0){
			for(int i=0;i<list.size();i++){
				ndto=list.get(i);%>
				
				
				<script type="text/javascript">
						
				
				//////////////제이쿼리 댓글창 움직임 이벤트들!!//////////////////
					$(function(){
						
						// 우선 모든 대댓글 창 숨기기
						$("#writeReply2_<%=ndto.getNum()%>").hide();						
						
						// 클릭한 댓글창은 보이기.
						$("#neiReReply<%=ndto.getNum()%>").on("click",function(){
							$("#writeReply2_<%=ndto.getNum()%>").show(500);	
						})
							
						// 포커스 아웃되면 댓글창 숨기기
						<%-- $("#writeReply2_<%=ndto.getNum()%>").focusout(function() {
							$(this).hide(1000);
							}); --%>
						
						// x박스 누르면 댓글창 숨기기
						$("#closeRe<%=ndto.getNum()%>").on("click",function(){
							$("#writeReply2_<%=ndto.getNum()%>").hide(800);						
						});	
					});
									
				</script>	
				
		<!-- 댓글 게시 부분 (댓글 삭제 시 id일치 여부 확인) -->
		
				<%if(ndto.getRe_seq()==0){%>					 
				<div class="replyContent">
					<div class="seeReply">
						<img class="profilePhoto40 mouseHand" src="<%=ndto.getUserPhoto()%>" onerror="this.src='/vc/images/man.jpg';">
						<span><%=ndto.getUserNickname()%></span>
						<span><%=ndto.getContent()%>
							<% // 댓글 삭제 권한 부여(id값과 글쓴이 일치 시 삭제!)
							if(idChk!=null){ 						
								if(idChk.equals(ndto.getUserId())){%>
								<a class="deleteBin" href="neiReplyDelete.ne?num=<%=DBnum%>&pageNum=<%=pageNum%>&replyNum=<%=ndto.getNum()%>
										&re_ref=<%=ndto.getRe_ref()%>&re_lev=<%=ndto.getRe_lev()%>"							
										onclick="return confirm('정말 삭제하시겠습니까?')"></a>
								<%}else{}%>
							<%}else{}%>							
						</span>
					</div>					
					<div class="replyInfo">
						<span><%=sdf1.format(ndto.getWriteTime())%></span>							
						<span class="mouseHand" id="neiReReply<%=ndto.getNum()%>">댓글</span>				
						<span><a href="">신고</a></span>												
					</div>
				</div>
				<%}else{%>
				<div class="replyContent2">
					<span class="replyAngle"></span>
					<div class="seeReply">
						<img class="profilePhoto40 mouseHand" src="<%=ndto.getUserPhoto()%>" onerror="this.src='/vc/images/man.jpg';">
						<span><%=ndto.getUserNickname()%></span>
						<span><b>@<%=ndto.getReOwnerNick()%></b>&nbsp;&nbsp;<%=ndto.getContent()%>
							<% // 댓글 삭제 권한 부여(id값과 글쓴이 일치 시 삭제!)
							if(idChk!=null){ 						
								if(idChk.equals(ndto.getUserId())){%>
								<a class="deleteBin" href="neiReplyDelete.ne?num=<%=DBnum%>&pageNum=<%=pageNum%>&replyNum=<%=ndto.getNum()%>
										&re_ref=<%=ndto.getRe_ref()%>&re_lev=<%=ndto.getRe_lev()%>"						
										onclick="return confirm('정말 삭제하시겠습니까?')"></a>
								<%}else{}%>
							<%}else{}%>								
						</span>
					</div>					
					<div class="replyInfo">						
						<span><%=sdf1.format(ndto.getWriteTime())%></span>
						<span class="mouseHand" id="neiReReply<%=ndto.getNum()%>">댓글</span>
						<span><a href="">신고</a></span>										
					</div>
				</div>	
				<%}%>	
			
	<!-- 대댓글 코딩용---------------->
		
	
		<div class="writeReply2" id="writeReply2_<%=ndto.getNum()%>">
			<% if(ssUserId==null){%>
				<span class="reNick">로그인 후 작성이 가능합니다.</span>
				<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>
				<span>0/100</span>
				<span class="xBox1 mouseHand" id="closeRe<%=ndto.getNum()%>"></span>
				<!-- <form action="" name="" method="" > -->
				
					<input class="reTyping mouseHand" name="reTyping" type="text" placeholder="로그인을 해주세요." readonly>
					<a href="userLogin.me?" class="btn60_35">로그인</a>
						
			<%}else{%>
				<span class="reNick"><%=ssUserNickname%> 님</span>
				<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>
				<span>0/100</span>
				<span class="xBox1 mouseHand" id="closeRe<%=ndto.getNum()%>"></span>
				<form action="neiReReplyAction.ne" name="neiReply2" method="post" >
			
					<input class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength="100" required>
					<input class="btn60_35" type="submit" value="등록"  onClick="window.location.reload(true)" id="reNickSubmit"> 
			<%}%>			
					<!-- 히든태그로 세션 정보를 함께 보냄 -->
					<input type="hidden" name="userId" value="<%=ssUserId%>">
					<input type="hidden" name="userNickname" value="<%=ssUserNickname%>">
					<input type="hidden" name="userPhoto" value="<%=ssUserPhoto%>">
					
					
					<input type="hidden" name="contentNum" value="<%=DBnum%>">
					<input type="hidden" name="contentPageNum" value="<%=pageNum%>">
				
					<!-- re_ref값은 댓글달기 클릭한 댓글의 번호를 동적으로 가져와야 함!! -->
					<input type="hidden" name="re_ref" value="<%=ndto.getRe_ref()%>">	 <!-- ndto.getNum() -->
					<input type="hidden" name="re_lev" value="<%=re_lev%>">
					<input type="hidden" name="re_seq" value="<%=re_seq%>">	
					
					<!-- 댓글 원작자에게 대댓글 달때 @원작자 명 제공하기 위한 데이터 처리 -->
					<input type="hidden" name="reOwnerNick" value="<%=ndto.getUserNickname()%>">
					
				
					
				</form>
			</div>

		
		<!-- 대댓글 창 : 대댓글 코딩용 끝---------------->
	
			<%}
		}
	%>
			
				
	</div>				
</div>
	
	
<!------------------------------- 오른쪽 사이드 부분 ------------------------------------>
	
<div class="bodyWrapRight">


	
</div>	

	<!-- footer구역-->
		<jsp:include page="../inc/footer.jsp" />

</div>

</body>
</html>