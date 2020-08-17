<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dm.dmDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dm.dmDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="/vc/css/dm/dm.css" type="text/css" rel="stylesheet">
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">


<!-- 모바일 버전 CSS 미디어쿼리 -->
<link href="/vanco/index_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/css/common_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/header_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">

<!-- 모바일용 뷰포트 메타태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">


<%	//미로그인 / 로그아웃 시 이용 안되게 튕김
	String userId=(String)session.getAttribute("userId");
	if(userId==null || userId==""){out.println("<script>alert('로그인을 해 주세요.'); location.href='/vanco/index.jsp'; </script>");}	

	//날자 포맷 설정
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy.MM.dd");
	
	//DB에 저장된 전체 dm갯수를 검색
	String receiveUserId = userId;
	dmDAO ddao = new dmDAO();
	int count = ddao.getDmCount(receiveUserId);
	
	System.out.println("count :"+count);
	
	// 페이지 마다 보여줄 글의 갯수를 지정(7개)
	int pageSize=7;
	
	// 페이지 번호 클릭 시 번호 얻기
	String pageNum = request.getParameter("pageNum");
	
	
	int pageColor=1;

	//페이지 클릭 시 CSS 처리 위한 조치
	if(pageNum==null){
		pageColor=1;
	}else{
		if((Integer.parseInt(pageNum))<=3){
			pageColor=(Integer.parseInt(pageNum))%4;			
		}else if((Integer.parseInt(pageNum))%3==0){
			pageColor=4;
		}else{
			pageColor=(Integer.parseInt(pageNum))%3+1;
		}
	}
	
	System.out.println("페이지컬러 : "+pageColor);
	
			
	if(pageNum==null){
		pageNum="1";
	}
	int currentPage=Integer.parseInt(pageNum);
	//---------------------------------------------
	//각 페이지마다 가장 위에 시작할 글번호 구하기 로직
	int startRow=(currentPage-1)*pageSize;
	
	// DAO에서 리턴받을 결과값 저장할 변수 선언
	ArrayList<dmDTO> dmList = new ArrayList<dmDTO>();
	
	//쪽지 정보 불러오기
	dmDTO ddto = new dmDTO();
	
	if(count>0){
		// DAO의 검색 메소드에 (매개변수로 페이지마다 보여질첫글번호, 페이지사이즈를 전달함)하려 리턴받음
		dmList=ddao.getDmList(startRow, pageSize, receiveUserId);
	}	
	
	//readCheck 올리기
	ddao.updateReadCheck(userId);
%>



</head>
<body>

	<!-- 헤더영역 -->	
	<jsp:include page="../inc/header.jsp" />		

	<div class="dmWrap">
		<div class="h2Title dmWrapTitle">
			<h2>받은 쪽지함</h2>
			<!-- <a>전체보기</a> -->
		</div>

<%	if(count>0){
		for(int i=0; i<dmList.size();i++){
			ddto = dmList.get(i);
			
			//날짜 시간 오늘은 시간, 어제부터는 날자로 표시하기
			sdf = new SimpleDateFormat("yyyyMMddHHmm"); 
			// 글쓴 날자를 불러와 원하는 양식으로 치환 (yyMMddHHMM)
			String writeTime=sdf.format(ddto.getWriteTime());
	
			Date now = new Date();		
			Date wTime = sdf.parse(writeTime);
			long writetime = wTime.getTime();
			long nowTime=now.getTime();
			long timeGap = (nowTime-writetime)/1000/60;  // 밀리세컨드를 분으로 환산		
			%>	
					
			<div class="dmBoxWrap">			
				<div class="dmBox">			
					<span class="mouseHand" onclick="location.href='yourPage.me?yourId=<%=ddto.getSendUserId()%>'">From 
						<b><%=ddto.getSendNickname()%>
							<%if(ddto.getReadCheck()<1){%>
							<p class="redPoint"></p>
							<%}%>
						</b>
					</span>
					<span><%=ddto.getDmContent()%></span>
					<%if(timeGap<1440){%>
						<span><%=sdfTime.format(ddto.getWriteTime())%></span>
					<%}else{%>
						<span><%=sdfDate.format(ddto.getWriteTime())%></span>
					<%}%>
					
					<%// 답장 보내기 위한url 저장하기
					String url = request.getRequestURI().toString(); %>									
					<div>
						<a href="dmSend.dm?receiveUserId=<%=ddto.getSendUserId()%>&receiveNickname=<%=ddto.getSendNickname()%>&url=<%=url%>">답장</a>
						<a href="#">삭제</a>
						<a href="#">신고</a>					
					</div>
				</div>
			</div>
			<%}
		} //if(count)끝%>
		
		<div class="dmPaging">
			<%	///////////////// dm 하단 페이징 처리///////////////////
			if(count>0){//글이 1개 이상 존재 할때, 전체 글수를 가져와서 뿌려줄 페이지수를 계산한다.
				// 페이지수 = 전체 글 갯수/1페이지당 보여줄 글 갯수 + 전체글수를 페이지당 보여줄 글수로 나눈 나머지 값  	// 조건 3항 연산자를 이용 (조건식?참;거짓)
				int pageCount=count/pageSize+(count%pageSize==0?0:1);
	
				// 아래에 보여질 페이지 번호 블럭 갯수 설정
				int pageBlock=3;
		
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
				<a href = "dmbox.dm?pageNum=<%=startPage-pageBlock%>">이전</a>								
				<%}
				// 페이지 번호 나타내기 [1] [2] [3]....[10]
				for(int i=startPage;i<=endPage;i++){%>
					<a id="pageSelected" class="pageSelected" href="dmbox.dm?pageNum=<%=i%>"><%=i%></a>			
				<%}		
	
				// [다음] 끝 페이지 번호가 전체 페이지 수 보다 작을때..
				if(endPage<pageCount){
				
				%>
				<a href="dmbox.dm?pageNum=<%=startPage+pageBlock%>">다음</a>
				<%}		
			
				}//if문 끝 %>
			
			</div>
				
				
	</div>  <!-- dmWrap끝 -->
	
		<style>
			.dmPaging .pageSelected:NTH-CHILD(<%=pageColor%>){font-weight:bold;color:#222;}
		</style>

</body>
</html>