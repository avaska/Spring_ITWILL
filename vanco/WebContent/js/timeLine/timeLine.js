/* 전역변수로 사용할 필요 정보 받아오기  */
var userId = document.getElementById("userId").value;  //세션에서 받아온 사용자 id
var userPhoto = $("#userPhoto").val();
var userCity = $("#userCity").val();
var userDistrict = $("#userDistrict").val();
var userNickname = $("#userNickname").val();

//////////////////무한스크롤 구현//////////////////////////////////////////////////
var startPage = 0;  // printTimeInfo의 pageCount 변수와 맞춰 주어야 함.
function iScroll(){
	var timeLineWrap = $(".timeLineWrap");	
	var timeInfoHtml = "";
	
	var pageCount = 4; // 스크롤 작동 시 3개씩 불러 옴
	
	var docHeight = $(document).height();
	var scrollTop = $(window).scrollTop();
	var winHeight = $(window).height();
	var scrollHeight = scrollTop + winHeight;
	//var callHeight = scrollHeight +500;
	
	console.log("docHeight  :"+ docHeight);
	//console.log("scrollTop  :"+ scrollTop);
	//console.log("winHeight  :"+ winHeight);
	console.log("scrollHeight  :"+ scrollHeight);
	//console.log("callHeight  :"+ callHeight);
	
	if(docHeight==scrollHeight){
		startPage +=4;
		//console.log("iScrollStart  :"+ startPage);
		
	
		printTimeInfo(startPage, pageCount);
		
	}		
} // 무한스크롤 함수 종료



//////타임라인 기본정보 + 추가정보 join한 리스트 Print///////////////////////////////////////////////////////////////
	
function printTimeInfo(startPage, pageCount){

	var timeLineWrap = $(".timeLineWrap");	
	var timeInfoHtml = "";
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getTimeLineInfoAction.ti?startPage="+startPage+"&pageCount="+pageCount,	
	})
	
	.done(function(timeListObj){	
		
		var allData = JSON.parse(timeListObj);
		
		for(var i=0;i<allData.timeInfoList.length;i++){

			// 배열로 받아온 값을 우선 변수에 저장(태그 코딩시 보기 쉽게)
			var timeLineNum = allData.timeInfoList[i].timeLineNum;
			var title = allData.timeInfoList[i].title;
			var content = allData.timeInfoList[i].content;
			var writeTimeStr = allData.timeInfoList[i].writeTimeStr;
			var userId = allData.timeInfoList[i].userId;
			var userPhoto = allData.timeInfoList[i].userPhoto;
			var userCity = allData.timeInfoList[i].userCity;
			var userDistrict = allData.timeInfoList[i].userDistrict;
			var userNickname = allData.timeInfoList[i].userNickname;
			var replyCount = allData.timeInfoList[i].replyCount;
			
			timeInfoHtml =  "<div class='timeContentWrap' id='timeContentWrap"+timeLineNum+"'> " 
							+"	<div class='profileWrap'> "
							+"		<span class='profilePhoto50 mouseHand' style=\"background-image: url('"+userPhoto+"');\" onclick=\" location.href='yourPage.me?yourId="+userId+"' \"></span>"
							+"		<span class='mouseHand' >"+userNickname+"</span>"
							+"		<div>"
							+"			<span>"+userCity+ " "+userDistrict+"</span> · <span>"+writeTimeStr+"</span>	"		
							+"		</div>"
							+"		<div class='dotBtnWrap'>"
							+"			<span class='mouseHand dot3Btn' id='dotMenuToggle' onclick='dotMenuToggle("+timeLineNum+");'>...</span>"
							+"			<div class='dotMenu closed' id='dotMenu"+timeLineNum+"'>"
							+"				<a>수정하기</a>"
							+"				<a>삭제하기</a>"
							+"				<a>관리자에 신고하기</a>"					
							+"			</div>"
							+"		</div>	"
							+"	</div>"
							+"	<div class='txtWrap'>"
							+"		<p> </p>"
							+"		<p>"+content+"ㄹㄹㄹㄹㄹㄹㄹ</p>	"			
							+"	</div>"
							+"	<div class='imgWrap' id='imgWrap"+timeLineNum+"'>"
										//이미지 들어갈 부분
							+"	</div>"
							+"	<div class='reWriteWrap' id='reWriteWrap"+timeLineNum+"'>"
										//댓글 달기 고정칸
							+"	</div>"
							+"	<div class='allReWrap' id='allReWrap"+timeLineNum+"'>"
										//댓글달기 껍데기
							+"	</div>"
							+"	<div  class='favoriteWrap' id='favoriteWrap"+timeLineNum+"'>"
										//좋아요 껍데기
							+"	</div>"
							
							
						+"</div>";
			
			timeLineWrap.append(timeInfoHtml);		
			
			printTimeImg(timeLineNum); // 이미지 함수 호출
			printReplyWrite(timeLineNum) // 댓글창 호출
			printReplyList(timeLineNum)  // 댓글 호출
			printFavorite(timeLineNum, userId, replyCount)  // 좋아요 호출
			
		}  // 전체 for문 끝
				
		if(startPage==0){ //무한 스크롤 함수 호출
			$(window).bind("scroll", iScroll);	//scroll 일어날때 isScroll()함수 호출하는 bind 기능 사용	
		} //
		
	}); //ajax done()끝
}// 함수 끝


function printTimeImg(timeLineNum){

	var imgWrap = $("#imgWrap"+timeLineNum);	
	var timeImgHtml = "";
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getTimeImgAction.ti?timeLineNum="+timeLineNum,		
	})
	
	
	.done(function(timeImgObj){	
		
		var allData = JSON.parse(timeImgObj);		
		
		var photoUrl = allData.timeImgList[0].photoUrl;
		var photoCount = allData.timeImgList[0].photoCount;
		
		if(photoCount>1){ // 사진 1장 이상일때는 더보기 버튼 만들기
		
			timeImgHtml = "<span id='timePhoto"+timeLineNum+"' class='imgCenter tpClosed timePhoto"+timeLineNum+"' style=\"background-image: url('"+photoUrl+"');\"></span>"
						+ "<div id='moreImgWrap"+timeLineNum+"'></div>"
						+ "	<p id='photoToggle"+timeLineNum+"' onclick='morePhoto("+timeLineNum+", "+photoCount+");'><span>+"+photoCount+"</span></p> ";
			imgWrap.html(timeImgHtml);
		}else{
			timeImgHtml = "<span id='timePhoto"+timeLineNum+"' class='imgCenter tpClosed timePhoto"+timeLineNum+"' style=\"background-image: url('"+photoUrl+"');\"></span>"
			
			imgWrap.html(timeImgHtml);
		}
		
		
	}); //ajax done()끝
}// 함수 끝



function morePhoto(timeLineNum, photoCount){
	
	var timePhoto = $(".timePhoto"+timeLineNum);
	var timePhotoHide = $(".timePhoto"+timeLineNum+":nth-child(n+2)");
	var photoToggle = $("#photoToggle"+timeLineNum);
	
	var closedClass = "imgCenter tpClosed timePhoto"+timeLineNum;
	var openedClass = "imgCenter tpOpened timePhoto"+timeLineNum;
	
	var moreImgWrap = $("#moreImgWrap"+timeLineNum);
	var moreImgHtml = ""; 
	 
	
	if(timePhoto.attr("class")===closedClass){
		timePhoto.css('display','block');
		timePhoto.attr('class',openedClass);
		
		$.ajax({
			method:"POST",
			cache:false,
			url:"getMoreImgAction.ti?timeLineNum="+timeLineNum+"&photoCount="+photoCount,		
		})
		
		
		.done(function(timeImgObj){	
			var allData = JSON.parse(timeImgObj);
			
			for(var i=0;i<allData.timeImgList.length;i++){

				// 배열로 받아온 값을 우선 변수에 저장(태그 코딩시 보기 쉽게)
				var photoUrl = allData.timeImgList[i].photoUrl;
				
				moreImgHtml += "<span id='timePhoto"+timeLineNum+"' class='imgCenter tpClosed timePhoto"+timeLineNum+"' style=\"background-image: url('"+photoUrl+"');\"></span>"; 
			}
			
			moreImgWrap.html(moreImgHtml);
				
			photoToggle.css('display', 'none');
			//photoToggle.attr('class', 'tpHide');
			//photoToggle.html("<span>다시접기</span>");
		
		});
		
		
	}else{
		timePhotoHide.css('display','none');
		timePhoto.attr('class',closedClass);
		photoToggle.attr('class', '1');
		photoToggle.html("<span>+"+photoCount+"</span>");
	}
	
}


///////////////////////////////////////////댓글창 부분


function printReplyWrite(timeLineNum){

	var reWriteWrap = $("#reWriteWrap"+timeLineNum);	
	var reWriteHtml = "";
	if(userId=="null"){
		reWriteHtml = "<span class='profilePhoto40' style=\"background-image: url('"+userPhoto+"');\"></span> "
					+ "<div class='reInputWrap'> "
					+ " 	<input id='replyContent"+timeLineNum+"' type='text' placeholder='로그인을 해주세요.' disabled>"
					+ " 	<span class='btn70_35r_r mouseHand' onclick=''>게시</span>"
					+ "</div> ";
	
	}else{
		reWriteHtml = "<span class='profilePhoto40' style=\"background-image: url('"+userPhoto+"');\"></span> "
		+ "<div class='reInputWrap'> "
		+ " 	<input id='replyContent"+timeLineNum+"' type='text' placeholder='댓글을 입력해 보세요.'>"
		+ " 	<span class='btn70_35r_r mouseHand' onclick=\"writeReply("+timeLineNum+", 0);\">게시</span>"
		+ "</div> ";
	}
		
	reWriteWrap.html(reWriteHtml);
		
	
}// 함수 끝










/*
function photoToggle(timeLineNum, photoCount){
	
	var timePhoto = $(".timePhoto"+timeLineNum);
	var timePhotoHide = $(".timePhoto"+timeLineNum+":nth-child(n+2)");
	var photoToggle = $("#photoToggle"+timeLineNum);
	
	var closedClass = "imgCenter tpClosed timePhoto"+timeLineNum;
	var openedClass = "imgCenter tpOpened timePhoto"+timeLineNum;
	 
	
	if(timePhoto.attr("class")===closedClass){
		timePhoto.css('display','block');
		timePhoto.attr('class',openedClass);
		photoToggle.attr('class', 'tpHide');
		photoToggle.html("<span>다시접기</span>");
	}else{
		timePhotoHide.css('display','none');
		timePhoto.attr('class',closedClass);
		photoToggle.attr('class', '1');
		photoToggle.html("<span>+"+photoCount+"</span>");
	}
	
}*/



/* 점3개메뉴 누르면 메뉴 펴지고 닫히기  */
function dotMenuToggle(timeLinuNum){
	
	var dotMenu= $("#dotMenu"+timeLinuNum);
	if(dotMenu.attr("class")==="dotMenu closed"){
		dotMenu.show(300);
		dotMenu.attr("class","dotMenu opened");
		
	}else{
		dotMenu.hide(300);
		dotMenu.attr("class","dotMenu closed");
	}

}



///////////////////////////////////////좋아요 부분 JS///////////////////////////////
/*좋아요 부분 출력하기*/
function printFavorite(timeLineNum, tlUserId, replyCount){
	var favoriteWrap = $("#favoriteWrap"+timeLineNum);

	if(replyCount>2){ // 댓글2개를 초과하면, 댓글 펼치기 열기
		// 좋아요 부분 html 틀 넣기
		favoriteWrap.html(
				  "<span id='heartWrap"+timeLineNum+"'>"
				+ 	"<span id='heart"+timeLineNum+"' class='heartOff2' onclick=\"likeToggle("+timeLineNum+",'"+tlUserId+"');\"></span>"
				+ "</span>"																			
				+ "<span id='likesCountWrap"+timeLineNum+"' class='countNums'></span>"	
				+ "<span class='replyIcon2'></span>"		
				+ "<span id='replyCountWrap"+timeLineNum+"' class='countNums'></span>"
				+ "<span id='replyToggle"+timeLineNum+"' class='replyToggle mouseHand' onclick='replyToggle("+timeLineNum+");'>댓글더보기</span>"
		); //html 더하기 끝	
	}else{
		// 좋아요 부분 html 틀 넣기
		favoriteWrap.html(
				  "<span id='heartWrap"+timeLineNum+"'>"
				+ 	"<span id='heart"+timeLineNum+"' class='heartOff2' onclick=\"likeToggle("+timeLineNum+",'"+tlUserId+"');\"></span>"
				+ "</span>"																			
				+ "<span id='likesCountWrap"+timeLineNum+"' class='countNums'></span>"	
				+ "<span class='replyIcon2'></span>"		
				+ "<span id='replyCountWrap"+timeLineNum+"' class='countNums'></span>"
		); //html 더하기 끝	
		
	}
	
	printLikeCount(timeLineNum);
	printReplyCount(timeLineNum);
}


/*좋아요 누를때 하트 채우고 끄기 토글*/
function likeToggle(timeLineNum, tlUserId){
	
	var heartWrap = $("#heartWrap"+timeLineNum);	
	var heart = $("#heart"+timeLineNum);	
	
	//하트 클릭했는지 여부 체크
	$.ajax({
		method:"POST",
		cache:false,
		url:"likeClickCheckAction.ti?timeLineNum="+timeLineNum+"&likeId="+userId,				
	})
	
	.done(function(clickCheck){				
		// clickCheck = 0  : 좋아요 안누름  // 1=누름
		if(clickCheck==0){ 	// 기존에 안 누른 경우(add)
			
			heart.attr("class", "heartOn2");
			
			$.ajax({
				method:"POST",
				cache:false,
				url:"addLikeAction.ti?userId="+tlUserId+"&timeLineNum="+timeLineNum+"&likeId="+userId,				
			})
			
			.done(function(data){				
				printLikeCount(timeLineNum);
				//printReplyCount(timeLineNum);
			});
			
			
		}else{	// 기존에 누른 경우(del)
			
			heart.attr("class", "heartOff2");
			
			$.ajax({
				method:"POST",
				cache:false,
				url:"delLikeAction.ti?timeLineNum="+timeLineNum+"&likeId="+userId,				
			})
			
			.done(function(data){				
				printLikeCount(timeLineNum);
				//printReplyCount(timeLineNum);
			});
		}  // if-else종료
	});  //done종료
	
	
} //likeToggle 종료


function printLikeCount(timeLineNum){
	
	var heart = $("#heart"+timeLineNum);	
	var likesCountWrap=$("#likesCountWrap"+timeLineNum);
	
	//하트 클릭했는지 여부 체크
	$.ajax({
		method:"POST",
		cache:false,
		url:"likeClickCheckAction.ti?timeLineNum="+timeLineNum+"&likeId="+userId,				
	})
	
	.done(function(clickCheck){
		
		// clickCheck = 0  : 좋아요 안누름  // 1=누름
		if(clickCheck==0){ 	// 기존에 안 누른 경우(add)			
			heart.attr("class", "heartOff2");			
		}else{
			heart.attr("class", "heartOn2");			
		}		
	}); //done 끝
	
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getLikeAction.ti?timeLineNum="+timeLineNum,				
	})
	
	.done(function(likesCount){		
		likesCountWrap.html(likesCount);			
	});
} //printLikeStatus 종료



// 댓글 수 세기 함수
function printReplyCount(timeLineNum){
	
	var replyCountWrap=$("#replyCountWrap"+timeLineNum);
	
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getReplyCountAction.ti?timeLineNum="+timeLineNum,				
	})
	
	.done(function(replyCount){		
		replyCountWrap.html(replyCount);			
	});
} //printLikeStatus 종료




/////////////////////////////////////댓글쓰기 JS///////////////////////////////

/* 댓글 달기 누르면 메뉴 펴지고 닫히기  */
function reToggle(timeLineNum, replyNum, reOwnerNick, printKey){
	var re_ref = replyNum;
	var replyWrap = $("#replyWrap"+replyNum);
	var reReWriteWrap = $("#reReWriteWrap"+replyNum);
	
	var closedClass = "closed reToggle replyWrap togClass"+timeLineNum;
	var openedClass = "opened reToggle replyWrap togClass"+timeLineNum;
	
	if(replyWrap.attr("class")==closedClass){
		replyWrap.after(
				"<div id='reReWriteWrap"+replyNum+"' class='reReWriteWrap opened'>"
				+ "<span class='profilePhoto40' style='background-image: url(\""+userPhoto+"\");'></span>"
				+ 	"<div class='reReInputWrap'>"
				+		 "	<input id='reReplyContent"+replyNum+"' type='text' placeholder='댓글을 입력해 보세요.'>"
				+		 "	<span class='btn70_35r_r mouseHand' onclick=\writeReReply("+timeLineNum+","+replyNum+","+re_ref+",'"+reOwnerNick+"',"+printKey+");\>게시</span>"
				+ 	"</div>"
				+ "</div>"
		);
		replyWrap.attr("class",openedClass);
	}else{
		reReWriteWrap.hide(200);
		replyWrap.attr("class",closedClass);
	}	
	
}



/* 대댓글 달기 누르면 메뉴 펴지고 닫히기  */
function reReToggle(timeLineNum, replyNum, re_ref, reOwnerNick, printKey){
	var reReplyWrap = $("#reReplyWrap"+replyNum);
	var reReWriteWrap = $("#reReWriteWrap"+replyNum);
	
	var closedClass = "closed reToggle reReplyWrap togClass"+timeLineNum;
	var openedClass = "opened reToggle reReplyWrap togClass"+timeLineNum;
	
	if(reReplyWrap.attr("class")==closedClass){
		
		reReplyWrap.after(
				"<div id='reReWriteWrap"+replyNum+"' class='reReWriteWrap opened'>"
				+ "<span class='profilePhoto40' style='background-image: url(\""+userPhoto+"\");'></span>"
				+ 	"<div class='reReInputWrap'>"
				+		 "	<input id='reReplyContent"+replyNum+"' type='text' placeholder='댓글을 입력해 보세요.'>"
				+		 "	<span class='btn70_35r_r mouseHand' onclick=\writeReReply("+timeLineNum+","+replyNum+","+re_ref+",'"+reOwnerNick+"',"+printKey+");\>게시</span>"
				+ 	"</div>"
				+ "</div>"
		);
		reReplyWrap.attr("class",openedClass);
	}else{
		reReWriteWrap.hide(200);
		reReplyWrap.attr("class",closedClass);
	}	
	
}


//타임라인 개별 페이지에서 댓글쓰기 부분

function writeReply(timeLineNum, printKey){
		
	var replyInput = $("#replyContent"+timeLineNum);
	var replyContent = replyInput.val();
	var replyWrap = $("#replyWrap"+timeLineNum);
	var openKey = 1;
		
	$.ajax({
		method:"POST",
		cache:false,
		url:"insertTimeReplyAction.ti?timeLineNum="+timeLineNum+"&content="+replyContent+"&userId="+userId,				
	})
	
	.done(function(data){
		
		if(printKey==1){ // 타임라인 클릭 후 개별 페이지에서 댓글 쓴 후 뿌리기
			printReplyListIn(timeLineNum);
		}else{ // 타임라인 본페이지에서 댓글 쓴 후 뿌리기
			printReplyList(timeLineNum, openKey);
			
		}		
		replyInput.val('');	
		
		printReplyCount(timeLineNum); // 댓글 수 업데이트
		//replyOpen(timeLineNum); // 댓글 강제로 열기
	});	
}


// 대댓글 쓰기 부분

function writeReReply(timeLineNum, replyNum, re_ref, reOwnerNick, printKey){

	var reReplyInput = $("#reReplyContent"+replyNum)
	var reReplyContent = reReplyInput.val();
	var reReplyWrap = $("#reReplyWrap"+replyNum);
	var reReWriteWrap = $("#reReWriteWrap"+replyNum);
	var openKey = 1;
	
	var closedClass = "closed reToggle reReplyWrap togClass"+timeLineNum;
	var openedClass = "opened reToggle reReplyWrap togClass"+timeLineNum;
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"insertTimeReReplyAction.ti?timeLineNum="+timeLineNum+"&re_ref="+re_ref+"&content="+reReplyContent+"&userId="+userId+"&reOwnerNick="+reOwnerNick,			
	})
	
	.done(function(data){
		
		if(printKey==1){
			printReplyListIn(timeLineNum); //댓글 출력
			reReWriteWrap.hide(); // 대댓글창 숨기기
			reReplyInput.val(''); //대댓글창 지우기
			printReplyCount(timeLineNum); // 댓글 수 업데이트
		}else{
			printReplyList(timeLineNum, openKey); //댓글 출력
			reReWriteWrap.hide(); // 대댓글창 숨기기
			reReWriteWrap.attr("class",closedClass); //대댓글창 클래스 변경		
			reReplyInput.val(''); //대댓글창 지우기			
			printReplyCount(timeLineNum); // 댓글 수 업데이트
			//replyOpen(timeLineNum) // 댓글 강제로 열기
		}
	});
		
}

////////////////////댓글과 대댓글 뿌리기(Print)//////////////////////////////////////////

function printReplyList(timeLineNum, openKey){
	
	var allReWrap = $("#allReWrap"+timeLineNum);	
	var replyHtml = "";
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getReplyAction.ti?timeLineNum="+timeLineNum,			
	})
	
	.done(function(timeReObj){	
		
		var allData = JSON.parse(timeReObj);
		
		for(var i=0;i<allData.replyList.length;i++){

			// 배열로 받아온 값을 우선 변수에 저장(태그 코딩시 보기 쉽게)
			var timeLineReplyNum = allData.replyList[i].timeLineReplyNum;
			var timeLineNumRe = allData.replyList[i].timeLineNum;
			var yourId = allData.replyList[i].userId;
			var content = allData.replyList[i].content;
			var writeTime = allData.replyList[i].writeTimeStr;
			var re_ref = allData.replyList[i].re_ref;
			var re_lev = allData.replyList[i].re_lev;
			var reOwnerNick = allData.replyList[i].reOwnerNick;
			var userPhoto = allData.replyList[i].userPhoto;
			var userNickname = allData.replyList[i].userNickname;	
			
			var reWrite = "답글달기";					
			if(userId=="null"){reWrite = "";}
				
			if(re_lev==0){ // 댓글 뿌리기					
				replyHtml+= "<div id='replyWrap"+timeLineReplyNum+"' class='closed reToggle replyWrap togClass"+timeLineNumRe+"' name='hideRe"+timeLineNumRe+"'> "
				+"				<div class='reply'>"
				+"					<span class='profilePhoto35 mouseHand' style='background-image: url(\""+userPhoto+"\");'  onclick=\" location.href='yourPage.me?yourId="+yourId+"' \"></span>"
				+"					<div class='replyProWrap'>"
				+"						<span>"+userNickname+"</span>	"
				+"						<span>"+writeTime+"</span>"
				+"					</div>	"
				+"					<span>"+content	
				+"						<span class='writeReBtn mouseHand' onclick=\" reToggle("+timeLineNum+","+timeLineReplyNum+",'"+userNickname+"')\"> "+reWrite+"</span>"
				+"					</span>	"
				+"				</div>"
				+"			</div>"	
				
			}else{// 대댓글 뿌리기
					
				replyHtml+= "<div id='reReplyWrap"+timeLineReplyNum+"' class='closed reToggle reReplyWrap togClass"+timeLineNumRe+"' name='hideRe'> "
				+"				<span class='replyAngle'></span>"
				+"				<div class='reReply'>"
				+"					<span class='profilePhoto35 mouseHand' style='background-image: url(\""+userPhoto+"\");'  onclick=\" location.href='yourPage.me?yourId="+yourId+"' \"></span>"
				+"					<div class='reReplyProWrap'>"
				+"						<span>"+userNickname+"</span>"	
				+"						<span>"+writeTime+"</span>"
				+"					</div>	"
				+"					<span><b class='toId'>@"+reOwnerNick+" &nbsp; </b>"+content				
				+"						<span class='writeReBtn mouseHand' onclick = \" reReToggle("+timeLineNum+", "+timeLineReplyNum+", "+re_ref+",'"+userNickname+"')\"> "+reWrite+"</span>"	
				+"					</span>"
				+"				</div>"
				+"			</div>"		
					
			}			
			allReWrap.html(replyHtml);		
			
			if(openKey==1){ // 댓글이나 대댓글 달면 강제로 숨은 댓글 열어주기
				replyOpen(timeLineNum) // 댓글 강제로 열기
			}
			
		}  // 전체 for문 끝
	}); //ajax done()끝
}// 함수 끝



function replyToggle(timeLineNum){
		
	var timeReply = $(".togClass"+timeLineNum);
	var timeReplyHide = $(".togClass"+timeLineNum+":nth-child(n+3)");	
	var replyToggle = $("#replyToggle"+timeLineNum);	
		
	if(replyToggle.text()=="댓글더보기"){
		
		timeReply.css('display','block');		
		replyToggle.text("댓글접기");
		replyToggle.attr('class', 'replyToggle mouseHand opened');
		
	}else{
		
		timeReplyHide.css('display','none');
		replyToggle.text("댓글더보기");
		replyToggle.attr('class', 'replyToggle mouseHand');
	}	
}


function replyClose(timeLineNum){
		
	var timeReply = $(".togClass"+timeLineNum);
	var timeReplyHide = $(".togClass"+timeLineNum+":nth-child(n+3)");	
	var replyToggle = $("#replyToggle"+timeLineNum);	
			
	timeReplyHide.css('display','none');
	replyToggle.text("댓글더보기");
	replyToggle.attr('class', 'replyToggle mouseHand');
}

function replyOpen(timeLineNum){
	
	// 댓글이나 대댓글 달면 댓글창 강제로 열어주기
	var timeReply = $(".togClass"+timeLineNum);
	var timeReplyHide = $(".togClass"+timeLineNum+":nth-child(n+3)");	
	var replyToggle = $("#replyToggle"+timeLineNum);	
	
	timeReply.css('display','block');		
	replyToggle.text("댓글접기");			
	replyToggle.attr('class', 'replyToggle mouseHand opened');
			
}


/////////////////////////////////////////////타임라인 개별사진 페이지 설정

// 개별 사진 들어갔을때 댓글 뿌기기
function printReplyListIn(timeLineNum){
	
	var allReWrap = $("#allReWrapIn");	
	var replyHtml = "";
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getReplyInAction.ti?timeLineNum="+timeLineNum,			
	})
	
	.done(function(timeReObj){	
		
		var allData = JSON.parse(timeReObj);
		
		for(var i=0;i<allData.replyList.length;i++){

			// 배열로 받아온 값을 우선 변수에 저장(태그 코딩시 보기 쉽게)
			var timeLineReplyNum = allData.replyList[i].timeLineReplyNum;
			var timeLineNumRe = allData.replyList[i].timeLineNum;
			var yourId = allData.replyList[i].userId;
			var content = allData.replyList[i].content;
			var writeTime = allData.replyList[i].writeTimeStr;
			var re_ref = allData.replyList[i].re_ref;
			var re_lev = allData.replyList[i].re_lev;
			var reOwnerNick = allData.replyList[i].reOwnerNick;
			var userPhoto = allData.replyList[i].userPhoto;
			var userNickname = allData.replyList[i].userNickname;	
			
			
			// 로그인 안하면 대댓글 답글달기 없애기
			var reWrite = "답글달기";					
			if(userId=="null"){reWrite = "";}
				
			if(re_lev==0){ // 댓글 뿌리기					
			replyHtml+= "<div id='replyWrap"+timeLineReplyNum+"' class='closed reToggle replyWrap togClass"+timeLineNumRe+"' name='hideRe"+timeLineNumRe+"'> "
			+"				<div class='reply'>"
			+"					<span class='profilePhoto35 mouseHand' style='background-image: url(\""+userPhoto+"\");'  onclick=\" location.href='yourPage.me?yourId="+yourId+"' \"></span>"
			+"					<div class='replyProWrap'>"
			+"						<span>"+userNickname+"</span>	"
			+"						<span>"+writeTime+"</span>"
			+"					</div>	"
			+"					<span>"+content	
			+"						<span class='writeReBtn mouseHand' onclick=\" reToggle("+timeLineNum+","+timeLineReplyNum+",'"+userNickname+"',1)\"> "+reWrite+"</span>	"
			+"					</span>	"
			+"				</div>"
			+"			</div>"	
			
			}else{// 대댓글 뿌리기
					
			replyHtml+= "<div id='reReplyWrap"+timeLineReplyNum+"' class='closed reToggle reReplyWrap togClass"+timeLineNumRe+"' name='hideRe"+timeLineNumRe+"'> "
			+"				<span class='replyAngle'></span>"
			+"				<div class='reReply'>"
			+"					<span class='profilePhoto35 mouseHand' style='background-image: url(\""+userPhoto+"\");'   onclick=\" location.href='yourPage.me?yourId="+yourId+"' \"></span>"
			+"					<div class='reReplyProWrap'>"
			+"						<span>"+userNickname+"</span>"	
			+"						<span>"+writeTime+"</span>"
			+"					</div>	"
			+"					<span><b class='toId'>@"+reOwnerNick+" &nbsp; </b>"+content				
			+"						<span class='writeReBtn mouseHand' onclick = \"reReToggle("+timeLineNum+", "+timeLineReplyNum+", "+re_ref+",'"+userNickname+"',1)\"> "+reWrite+"</span>"	
			+"					</span>"
			+"				</div>"
			+"			</div>"		
					
			}	
		
			allReWrap.html(replyHtml);	
			$(".reToggle").css('display','block');
			
		}  // 전체 for문 끝
	}); //ajax done()끝 
}// 함수 끝

