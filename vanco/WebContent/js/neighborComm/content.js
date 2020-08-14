/**
 *	content.js
 */

// parameter값 받아오는 함수
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

//전역변수 선언
var moimNum = getParameterByName('moimNum');
var idx = getParameterByName('idx');
var writerName = document.getElementById('writerName').value;

/*다운로드 기능 함수
function onDownload(idx) {
	var o = document.getElementById("ifrm_filedown");
	o.src = "download.do?moimNum="+moimNum+"&idx="+idx;
}
*/

/**
 *	코멘트 삽입 ajax 함수
 */
function insertComment(idx) {
	var content = document.getElementById("comment").value;
	$.ajax({
		url: "insert.co",
		type: "post",
		datatype : "json",
		data : {"content":content,
				"idx":idx,
				"moimNum":moimNum,
				"writer":writerName},
		success : function(data) {
			
			printComment(idx);
			$("#comment").val("");
		}
	});
}

/**
 *	코멘트 출력 함수
 */
function printComment(idx) {
	
	$.ajax({
		url: "print.co?idx="+idx+"&moimNum="+moimNum,
		cache: false,
		success: function(data) {
			var obj = JSON.parse(data);
			var comList = $(".replyWrap");
			
			var i;
			var html = '';
			for(i in obj.comments) {
				
				// <!-- 답글 @이름 구현
				if(obj.comments[i].reOdr == 0) {
					
				html+='<div class="replyContent">'
					+'<div class="seeReply">';

				
				} else {
					
					html+='<div class="replyContent2">'
						+'<span class="replyAngle"></span>'
						+'<div class="seeReply">';
				}
				
				html+='<img class="profilePhoto40 mouseHand" src="" onerror="this.src=\'/vc/images/man.jpg\';">'
				+'<span>'+obj.comments[i].writer+'</span>'
				+'<span>'
				+obj.comments[i].content
				+'<a class="deleteBin" onclick="return deleteComment('+obj.comments[i].num+');"></a>'			
				+'</span>'
				+'</div>'
				+'<div class="replyInfo">'
				+'<span>'+obj.comments[i].regdate+'</span>'
				+'<span class="mouseHand" onclick="reComment(this, '+obj.comments[i].num+', '+obj.comments[i].reDep+')">댓글</span>'
				+'<span><a href="javascript:void(0);" onclick="modifyComment(this, '+obj.comments[i].num+')">수정</a></span>'
				+'</div>'
				+'</div>';

			}
			comList.html(html);
			
		}
	});
}

/**
 *	코멘트 답글 창 생성 함수
 */
function reComment(meee,reNum,reDep) {
	
	
	// 2단 코멘트인지 3단 이상 코멘트인지 검사
	var comhtml = '';
	if(reDep > 0){
		comhtml = '<div class="writeReply2">'
		+'<span class="reNick">닉네임 님</span>'
		+'<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>'
		+'<span>0/100</span>'
		+'<span class="xBox1 mouseHand" onclick="modalClose()"></span>'
		+'<input class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength="100" required>'
		+'<input class="btn60_35" type="button" value="등록" onclick="reReCommentInsert('+reNum+');" id="reNickSubmit">'
		+'</div>'
		+'</div>';
	
	} else {
		comhtml = '<div class="writeReply2">'
			+'<span class="reNick">닉네임 님</span>'
			+'<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>'
			+'<span>0/100</span>'
			+'<span class="xBox1 mouseHand" onclick="modalClose()"></span>'
			+'<input class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength="100" required>'
			+'<input class="btn60_35" type="button" value="등록" onclick="reCommentInsert('+reNum+');" id="reNickSubmit">'
			+'</div>';
	}

	// 댓글 수정 창이 열려있으면 닫아주는 기능
	if($("#modifyComment"+reNum).length > 0){	
		$("#modifyComment"+reNum).find('td > div').stop().slideUp("slow", function(){
    		$("#modifyComment"+reNum).remove();
    	});
	}
	
	// 이미 현재 코멘트 답글 창이 열려있을 때 닫는 기능
	if($(".writeReply2").length > 0){
		
        $(".writeReply2").stop().slideUp("slow", function(){
        	$(".writeReply2").remove();
        });
        
    // 모든 경우의 수 가 아닐 경우 코멘트 답글 창 생성
	} else {
		
        // this ($(meee)값)로 잡아온 요소의 아래에 코멘트 답글 창 넣기위한 기능
        $(meee).parent().parent().after(comhtml);
			
	}
}

/**
 *	코멘트 수정 창 생성 함수
 */
function modifyComment(meee,num) {
	
	var comhtml = '';
	comhtml = '<div id="myModal" class="modal">'
		+'<div class="writeReply2 modal-content modifyComment">'
		+'<span class="reNick">닉네임 님</span>'
		+'<span>비방글, 악플은 삭제 및 신고될 수 있습니다.</span>'
		+'<span>0/100</span>'
		+'<span class="xBox1 mouseHand" onclick="modalClose()"></span>'
		+'<input class="reTyping mouseHand" name="content" type="text" placeholder="댓글을 입력해 주세요." maxlength="100" required>'
		+'<input class="btn60_35" type="button" value="수정" onclick="commentModify('+num+');" id="reNickSubmit">'
		+'</div>'
		+'</div>';
	

	
	// 기존의 댓글 수정 창이 열려있으면 닫아주는 기능
	if($("#myModal").length > 0){
		
		$("#myModal").stop().slideUp("slow", function(){

			$("#myModal").remove();

        	 });
        
    // 모든 경우의 수 가 아닐 경우 코멘트 수정 창 생성
	} else {
		
		/* 브라우저 슬라이드
		var offset = $(meee).offset();
        $("html").animate({scrollHeight : offset.top}, 200);
        */
		
     // this($(meee)값)로 잡아온 요소의 아래에 코멘트 수정 창 넣기위한 기능
        $(meee).parent().parent().after(comhtml);
			
			$.ajax({
				url: "bring.co",
				type: "post",
				datatype : "json",
				data : {"moimNum":moimNum,
						"idx":idx,
						"num":num,
						"writer":writerName},
				success : function(data) {
					$(".modifyComment > input")[0].value = data;
			        var modal = document.getElementById("myModal");
			        modal.style.display = "block";
				}
			});
	}
}

/**
 *	코멘트 답글 삽입 함수
 */
function reCommentInsert(reNum) {
	
	// 내용 가져올 변수 선언
	var content = $(".writeReply2 > input")[0].value;
	
	// ajax 호출
	$.ajax({
		url: "reInsert.co",
		type: "post",
		datatype : "json",
		data : {"content":content,
				"idx":idx,
				"reNum":reNum,
				"moimNum":moimNum,
				"writer":writerName},
		success : function(data) {
			printComment(idx);
			// 기존 코멘트 창 닫기
			modalClose();
		}	
	});
}

/**
 *	코멘트 답의 답글(대댓글) 삽입 함수
 */
function reReCommentInsert(reNum) {
	
	// 내용 가져올 변수 선언
	var content = $(".writeReply2 > input")[0].value;
	
	// ajax 호출
	$.ajax({
		url: "reReInsert.co",
		type: "post",
		datatype : "json",
		data : {"content":content,
				"idx":idx,
				"reNum":reNum,
				"moimNum":moimNum,
				"writer":writerName},
		success : function(data) {
			printComment(idx);
			// 기존 코멘트 창 닫기
			modalClose();
		}
	});
}

/**
 *	코멘트 수정 함수
 */
function commentModify(num) {
	
	// 내용 가져올 변수 선언
	var content = $(".modifyComment > input")[0].value;
	
	// ajax 호출
	$.ajax({
		url: "modify.co",
		type: "post",
		datatype : "json",
		data : {"content":content,
				"idx":idx,
				"num":num,
				"moimNum":moimNum,
				"writer":writerName},
		success : function(data) {
			// 코멘트 재출력
			printComment(idx);
			
			// 기존 코멘트 창 닫기
			modalClose();
		
		}	
	});
}

/**
 *	코멘트 삭제 함수
 */
function deleteComment(num) {
	
	if(!confirm('정말 삭제하시겠습니까?')){
		return;
	}
	
	// ajax 호출
	$.ajax({
		url: "delete.co",
		type: "post",
		datatype : "json",
		data : {"moimNum":moimNum,
				"idx":idx,
				"num":num,
				"writer":writerName},
		success : function(data) {
			
			// 코멘트 재출력
			printComment(idx);
			
			// 기존 코멘트 창 닫기
			modalClose();
		}	
	});
}

function modalClose() {
		// 이미 현재 코멘트 답글 창이 열려있을 때 닫는 기능
		if($(".writeReply2").length > 0){

	        $(".writeReply2").stop().slideUp("slow", function(){
	        	$(".writeReply2").remove();
	        });
		}
		if(document.getElementById("myModal")){
			var modal = document.getElementById("myModal");
			modal.remove();
		}
	}

printComment(idx);