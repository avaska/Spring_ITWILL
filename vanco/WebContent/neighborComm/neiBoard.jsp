 
<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.File"%>
<%@page import="java.awt.Image"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="java.util.Date"%>
<%@page import="neighborComm.neiBoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="neighborComm.neiBoardDTO"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/vc/js/plugin/jquery.exif.js"></script>

<script type="text/javascript">
	function boardLogChk(){
		
		var idChk= "<%=(String)session.getAttribute("userId")%>"
		if(idChk=="null" || idChk== null ||idChk== ""){
			alert("로그인후 이용 가능합니다.");
		}else{			
			location.href="write.ne";	
		}
	}
</script>

 
<%	// 글쓰기 버튼 클릭 시 로그인 여부 확인을 위한 세션 id값 저장
	String userId = (String)session.getAttribute("userId");
	
	//사용자 지역정보 개인정보 가져오기
	MemberDAO mdao = new MemberDAO();
	MemberDTO mdto = mdao.getUserInfo(userId);
	
	String userCity = mdto.getUserCity();
	String userDistrict = mdto.getUserDistrict();
	
	
	%>  

<%	//날짜 시간 포맷 변환용 변수 생성
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm");

	//DAO 객체 생성
	neiBoardDAO ndao = new neiBoardDAO();

	//DB에 저장된 전체 글 갯수를 검색하기 위해 DAO의 getBoardCount()메소드 호출
	int count = ndao.getBoardCount();
	
	// 페이지 마다 보여줄 글의 갯수를 지정(7개)
	int pageSize=5;
	
	// 페이지 번호 클릭 시 번호 얻기
	String pageNum = request.getParameter("pageNum");
	int pageColor=1;
	
	
	//페이지 클릭 시 CSS 처리 위한 조치
	if(pageNum==null){
		pageColor=1;
	}else{
		if((Integer.parseInt(pageNum))<=8){
			pageColor=(Integer.parseInt(pageNum))%9;
			
		}else if((Integer.parseInt(pageNum))%8==0){
			pageColor=9;
		}else{
			pageColor=(Integer.parseInt(pageNum))%8+1;
		}
	}
	
	
		
	if(pageNum==null){
		pageNum="1";
	}
	int currentPage=Integer.parseInt(pageNum);
	//---------------------------------------------
	//각 페이지마다 가장 위에 시작할 글번호 구하기 로직
	int startRow=(currentPage-1)*pageSize;
	
	// DAO에서 리턴받을 결과값 저장할 변수 선언
	ArrayList<neiBoardDTO> boardList=null;
	
	if(count>0){
		// DAO의 검색 메소드에 (매개변수로 페이지마다 보여질첫글번호, 페이지사이즈를 전달함)하려 리턴받음
		boardList=ndao.getBoardList(startRow, pageSize);
	}	
%>



<div class="h2Title">
	<h2><%=userCity%> <%=userDistrict%> theKoo광장</h2>
	<!-- <a href="#">전체보기</a> -->
	<button class= "neiBoardWrite btn80_40b" id="neiBoardWrite" onclick="boardLogChk();">글쓰기</button>
</div>
<div class="neiBoard">
<%
	if(count>0){
		for(int i=0; i<boardList.size();i++){
		neiBoardDTO ndto = boardList.get(i);
			
		/* // 글쓴 날자를 불러와 원하는 양식으로 치환 (yy.MM.dd HH:MM)
		String wTime=sdf.format(ndto.getWriteTime()); */
						
		%>
			

		<div class="neiBoardWrap">
			<div class="neiBoardTit"><a href="neiBoardContent.ne?num=<%=ndto.getNum()%>&pageNum=<%=pageNum%>"><%=ndto.getSubject()%></a></div>
			<div class="neiBoardProf">
				<a class="profilePhoto35" style="background-image: url('<%=ndto.getUserPhoto()%>');" href="yourPage.me?yourId=<%=ndto.getUserId()%>" ></a>
				<a class="neiBoardNick" href="yourPage.me?yourId=<%=ndto.getUserId()%>"><%=ndto.getUserNickname() %></a>
				
				<%
				//날짜 시간 *분전으로 표시하기
				sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
				// 글쓴 날자를 불러와 원하는 양식으로 치환 (yyMMddHHMM)
				String writeTime=sdf.format(ndto.getWriteTime());

				Date now = new Date();
				
				Date wTime = sdf.parse(writeTime);
				long writetime = wTime.getTime();
				long nowTime=now.getTime();
		
				long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산
				
				if(timeGap<1){%>
					<span class="neiBoardInfo">조회수<%=ndto.getReadCount() %> &nbsp;· &nbsp;댓글<%=ndto.getReplyCount()%></span> <span class="writeTime">지금</span>
				<%}else if(timeGap>=1 && timeGap<60){%>
					<span class="neiBoardInfo">조회수<%=ndto.getReadCount() %> &nbsp;· &nbsp;댓글<%=ndto.getReplyCount()%></span> <span class="writeTime"><%=timeGap%>분전</span>
				<%}else if(timeGap>=60 && timeGap<1440){%>
					<span class="neiBoardInfo">조회수<%=ndto.getReadCount() %> &nbsp;· &nbsp;댓글<%=ndto.getReplyCount()%></span>  <span class="writeTime"><%=timeGap/60 %>시간전</span>
				<%}else if(timeGap>=1440 && timeGap<43200){%>
					<span class="neiBoardInfo">조회수<%=ndto.getReadCount() %> &nbsp;· &nbsp;댓글<%=ndto.getReplyCount()%></span>  <span class="writeTime"><%=timeGap/60/24 %>일전</span>
				<%}else if(timeGap>=43200){%>
					<span class="neiBoardInfo">조회수<%=ndto.getReadCount() %> &nbsp;· &nbsp;댓글<%=ndto.getReplyCount()%></span>  <span class="writeTime"><%=timeGap/60/24/30 %>개월전</span>
				<%}%>		
				
				<span class="neiBoardPic mouseHand imgCenter" onclick="location.href='neiBoardContent.ne?num=<%=ndto.getNum()%>&pageNum=<%=pageNum%>'"
				style="background-image: url('<%=ndto.getUpPhoto1()%>');">
					
				</span>
							
				<%-- <span class="neiBoardPic mouseHand" onclick="location.href='neiBoardContent.ne?num=<%=ndto.getNum()%>&pageNum=<%=pageNum%>'">
					<img id="neiPic1" alt="" src="<%=ndto.getUpPhoto1()%>" onerror="this.style.display='none'">
				</span> --%>
			
			</div>			
		</div>
		
	
			
<%		}
	}
%>


<div class="neiBoardPaging">
<%	///////////////// 보드 하단 페이징 처리///////////////////
	if(count>0){//글이 1개 이상 존재 할때, 전체 글수를 가져와서 뿌려줄 페이지수를 계산한다.
		// 페이지수 = 전체 글 갯수/1페이지당 보여줄 글 갯수 + 전체글수를 페이지당 보여줄 글수로 나눈 나머지 값  	// 조건 3항 연산자를 이용 (조건식?참;거짓)
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
	
		// 아래에 보여질 페이지 번호 블럭 갯수 설정
		int pageBlock=8;
		
		// 시작 페이지 번호 구하기 (1~10 => 1, 11~20=>11, 21~30 => 21페이지를 보여줌)
		// 식:((현재 보여질 페이지번호/한블럭의 페이지갯수)-(현재보여질 페이지번호%한블럭의 페이지갯수의 나머지값))* 한블럭의 페이지갯수+1
		int startPage=((currentPage/pageBlock)-(currentPage%pageBlock==0?1:0))*pageBlock + 1;
		
		// 끝페이지 번호 구하기
		// 식 : 시작페이지번호 + 한블럭의 페이지 갯수 -1
		int endPage=startPage+pageBlock-1;
		
		//끝페이지 번호가 전체페이지수 보다 클때
		if(endPage>pageCount){
			endPage=pageCount;
		}
		
		//[이전] 시작페이지 번호가 한 화면에 보여줄 페이지 수 보다 클때
		if(startPage>pageBlock){%>
							
		<a href = "neighborCommMove.ne?pageNum=<%=startPage-pageBlock%>"><</a>
						
		<%}
		// 페이지 번호 나타내기 [1] [2] [3]....[10]
		for(int i=startPage;i<=endPage;i++){%>
			<a id="pageSelected" class="pageSelected" href="neighborCommMove.ne?pageNum=<%=i%>"><%=i%></a>			
		<%
		}		
		
		// [다음] 끝 페이지 번호가 전체 페이지 수 보다 작을때..
		if(endPage<pageCount){%>
		<a href="neighborCommMove.ne?pageNum=<%=startPage+pageBlock%>">></a>
		<%}		
		///////////보드 하단 페이징 처리 끝/////////////////////////	
	}%>
		
		<style>
			.neiBoardPaging .pageSelected:NTH-CHILD(<%=pageColor%>){background:#64B9FF;color:#fff;}
		</style>
		
</div>			
		
	
	
		
</div>
