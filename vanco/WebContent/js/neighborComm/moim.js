/* 전역변수로 사용할 필요 정보 받아오기  */
var moimNum = $("#moimNum").val();
var userId = $("#userId").val();
var path = $("#path").val();
var userPhoto = $("#userPhoto").val();


/* 번개 내용 입력 토글  */

function thunderMakingToggle(){
	var thunderToggle= $("#thunderToggle");
		thunderToggle.show(500);
		$("#thunderOpen").hide();		
}

	
/* 번개 만들기 버튼 토글  */	
function thunderCancel(){
	$("#thunderToggle").hide(300);
	$("#thunderOpen").show();
}
	
	
/* 번개 만들기 insert  */	
function insertThunder(){
	var thunderName= $("#thunderName").val();
	var thunderPlace= $("#thunderPlace").val();
	var thunderDate= $("#thunderDate").val();
	var thunderHour= $("#thunderHour").val();
	var thunderMinute= $("#thunderMinute").val();
	var thunderPerson= $("#thunderPerson").val();
	
	if(thunderName.length== 0) {
		alert("제목을 입력해 주세요");
		return;
	} 
	
	/*받아온 정보를 json오브젝트 형태로 저장*/
	var thunderInfoObj={
		"thunderName" :  thunderName,	
		"thunderPlace" :  thunderPlace,
		"thunderDate" :  thunderDate,
		"thunderHour" :  thunderHour,
		"thunderMinute" :  thunderMinute,
		"thunderPerson" :  thunderPerson,
		"userId" : userId,
		"userPhoto" : userPhoto,
		"moimNum" : moimNum,
	}
	
	/*json 오브젝트를 스트링 형태로변환*/
	var thunderInfoParam = JSON.stringify(thunderInfoObj);
	$.ajaxSetup( {cache:false} );
	$.ajax({
		method:"POST",
		cache:false,
		url:"insertThunderAction.ne",
		data:{"thunderInfoParam":thunderInfoParam}
	})
	
	.done(function(data){
		printThunderList();
		$("#thunderToggle").hide(300);			
	});
} /*insertThunder()끝*/
	
	
	
//모임 접속시 또는 모임 생성 후 번개리스트 출력하기	
	
function printThunderList(){	
	var xhttp;
	
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();			
	} else {
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");			
	}

	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200){ 
	//////////////
			
		var allData = JSON.parse(this.responseText);
		var thunderMoim = document.getElementById("thunderMoim");
					
		thunderMoim.innerHTML = "";
		var i;
		for (i = 0; i < allData.allThunderList.length; i++){
			
			var thunderNum = allData.allThunderList[i].thunderNum;
			var thOwnerPhoto = allData.allThunderList[i].userPhoto;
			var thOwnerId = allData.allThunderList[i].userId;
			var thunderJoinCount = allData.allThunderList[i].thunderJoinCount;
			var thunderPerson = allData.allThunderList[i].thunderPerson;
								
			thunderMoim.innerHTML += "<div class='thunderMoim'>"
				+	"<div class='calendarBox'>"
				+		"<span>"+allData.allThunderList[i].parsingDay+"</span>"
				+		"<span>"+allData.allThunderList[i].parsingDate+"</span>"
				+	"</div>"
				+	"<span>"+allData.allThunderList[i].thunderName
				+		"<a class='replyIcon'></a>"
				+		"<a class='reCount'>"+allData.allThunderList[i].thunderPerson+"</a>"
				+	"</span>"								
				+	"<span class='positionIcon'></span>"
				+	"<span>"+allData.allThunderList[i].thunderPlace+"</span>"								
				+	"<span class='clockIcon'></span>"
				+	"<span>"+allData.allThunderList[i].thunderDate.substring(0,16)+"</span>"							
				+	"<span class='personIcon'></span>"
				+	"<span id='joinCount"+thunderNum+"'>"
				+		"<b id='joinPerson"+thunderNum+"'>"+thunderJoinCount+"</b>/<b>"+thunderPerson+"</b>"
				+ 	"</span>"	/*참여인수/모집인원 들어오는 곳*/	 						
				+"</div>"
				
				+"<div class='thunderUser' id='thunderUser"+thunderNum+"'>"	
				+ "<div class='profileWrap'>"
				+	"<div id='profileWrap"+thunderNum+"'>"
						/*번개 신청자 이미지 들오오는 곳*/
				+	"</div>"
				+ "</div>"
				+"</div>"
				
				+"<div class='buttons clear'>"									
				+	"<span class='thunderDel mouseHand DpNone2' id='thunderDel"+thunderNum+"' onclick= thunderDel("+thunderNum+"); >정모삭제</span>"
				+	"<span id='joinWrap"+thunderNum+"'>"
				
				+	"</span>"
				+"</div>";
			printThunderJoinUser(thunderNum, thOwnerId);	
			}  // for문 종료
		}		
	};	
	////////////////////		
	xhttp.open("POST", "getThunderListAction.ne?t="+ new Date().getTime(), true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
	xhttp.send("moimNum="+moimNum);		
	//////////////
}	

	
//모임 접속시 또는 모임 생성 후 번개리스트 출력하기	
	
function printThunderJoinUser(thunderNum, thOwnerId){	
	var xhttp;
	
	if (window.XMLHttpRequest) {
		xhttp = new XMLHttpRequest();			
	} else {
		xhttp = new ActiveXObject("Microsoft.XMLHTTP");			
	}

	xhttp.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200){ 
	//////////////
		
		var allData = JSON.parse(this.responseText);			
		var profileWrap = document.getElementById("profileWrap"+thunderNum);
		var joinWrap = document.getElementById("joinWrap"+thunderNum);
			
		profileWrap.innerHTML = "";
		var i;
		
				
		// 프로파일 사진 불러오기용 for문
		for (i = 0; i < allData.allUser.length; i++){					
			//신청자 이미지 프로파일 넣기
			profileWrap.innerHTML += "<p class='profilePhoto35' style='background-image:url("+allData.allUser[i].userPhoto+")'></p>";
		}//for문 종료
		
		
		// 버튼 불러오기용 for문
		for (i = 0; i < allData.allUser.length; i++){					
														
			// 상황별 신청버튼 바꾸기(번개장, 신청시, 미신청시)
			var checkId = allData.allUser[i].userId;
			
			if(userId==thOwnerId){ //번개장은 신청안됨	
				joinWrap.innerHTML= "<span class='btn80_40b thunderJoin' id='thunderJoin"+thunderNum+"' onclick= alert('개설자입니다'); >참가하기</span>"
				return;
			}else if(userId==checkId){// 신청상태
				joinWrap.innerHTML= "<span class='btn80_40b thunderJoin checked' id='thunderJoin"+thunderNum+"' onclick=\"thunderJoin("+thunderNum+",'"+checkId+"');\">신청됨</span>"
				return;
			}else{//미신청 상태						
				joinWrap.innerHTML= "<span class='btn80_40b thunderJoin DpNone1' id='thunderJoin"+thunderNum+"' onclick=\" thunderJoin("+thunderNum+"); \">참가하기</span>"				
			}					
		} // 버튼용 for문 종료		
	} // onreadystatechange()함수 종료		
};	
////////////////////		
xhttp.open("POST", "getThunderUserAction.ne?thunderNum="+thunderNum+"&t="+new Date().getTime(), true);
xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
xhttp.send("moimNum="+moimNum);		
//////////////
}		

	
/*번개 참여 신청 및 취소 토글 */		

function thunderJoin(thunderNum, checkId){		
	var thunderJoin = $("#thunderJoin"+thunderNum);
	var joinPerson = $("#joinPerson"+thunderNum);
	
	var joinCount = $("#joinCount"+thunderNum);
	
		$.ajax({
			method:"POST",
			cache:false,
			url:"getThunderJoinCountAction.ne?thunderNum="+thunderNum+"&moimNum="+moimNum,			
		})
		
		.done(function(dataObj){	
			var allData = JSON.parse(dataObj);
			
			var thunderJoinCount = allData.count[0].thunderJoinCount;
			var thunderPerson = allData.count[0].thunderPerson;
			
			if(thunderJoinCount<thunderPerson || userId==checkId){
				
				//버튼 클릭 시 chekced 클래스명 토글(있게, 없게 하기)
				if(thunderJoin.attr('class')==="btn80_40b thunderJoin checked"){
									
					$.ajax({
						method:"POST",
						cache:false,
						url:"deleteThunderUserAction.ne?userId="+userId+"&thunderNum="+thunderNum+"&moimNum="+moimNum,				
					})
					
					.done(function(data){
						printThunderJoinUser(thunderNum);
						
						// 신청/취소 인원수 조정 태그 추가
						var count = thunderJoinCount-1;
						joinCount.html("<b id='joinPerson"+thunderNum+"'>"+count+"</b>/<b>"+thunderPerson+"</b>");
					});
							
							
				}else{ // 클래스 토글 if-else
									
					$.ajax({
						method:"POST",
						cache:false,
						url:"insertThunderUserAction.ne?userId="+userId+"&thunderNum="+thunderNum+"&moimNum="+moimNum,				
					})
					
					.done(function(data){
						printThunderJoinUser(thunderNum);
						
						// 신청/취소 인원수 조정 태그 추가
						var count = thunderJoinCount+1;
						joinCount.html("<b id='joinPerson"+thunderNum+"'>"+count+"</b>/<b>"+thunderPerson+"</b>");
					});
					
				} //// 클래스 토글 if-else 종료	
			}else{			
				alert("모집이 마감되었습니다");
				return;
			} // 인원초과 체크 if-else 종료
						
					
		}); // 2번째 ajax done 긑
	
	
	
}









function printThunderJoinCount(thunderNum){		
	var joinCount = $("#joinCount"+thunderNum);
	
	$.ajax({
		method:"POST",
		cache:false,
		url:"getThunderJoinCountAction.ne?thunderNum="+thunderNum+"&moimNum="+moimNum,			
	})
	
	.done(function(dataObj){	
		var allData = JSON.parse(dataObj);
		
		var thunderJoinCount = allData.count[0].thunderJoinCount;
		var thunderPerson = allData.count[0].thunderPerson;
		
		
		
		joinCount.html("<b id='joinPerson"+thunderNum+"'>"+thunderJoinCount+"</b>/<b>"+thunderPerson+"</b>");		
	});
	
	
}	







/* 번개 삭제*/
function thunderDel(thunderNum){
	
	if(confirm("정말로 삭제 하시겠습니까?")){		
		location.href="thunderDelAction.ne?thunderNum="+thunderNum+"&moimNum="+moimNum;
	}else{
		return;
	}
}	
	
	
	
	
	
	
	
	
	
	