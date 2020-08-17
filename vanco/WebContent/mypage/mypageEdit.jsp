<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="/vc/css/mypage/mypage.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>

<!-- 정보수정 유효성 체크 -->
<script type="text/javascript">
	function editCheck(){
		var vancoUserEdit = document.vancoUserEdit;
		var pwdChk=$("#userPwd");
		var pwdChk2=$("#userPwdChk");
		var nicknameChk=$("#userNickname");
		var gender1=$("input[id=userGenderM]");
		var gender2=$("input[id=userGenderF]");
		
		var idReg = /^[A-Za-z0-9]+$/;			 
		var pwdReg= /^[a-zA-Z0-9]{6,20}$/;		
		var nickReg= /^[가-힣0-9]{2,7}$/;
		
		// 미입력 시 입력요청
		if(pwdChk.val()==""){	
			alert("비밀번호를 입력해주세요.");
			pwdChk.focus();
			return false;
		}else if(!pwdReg.test(pwdChk.val())){
			alert("비밀번호는 숫자와 영문자 조합으로 6~20자리를 사용해야 합니다.");
			pwdChk.focus();
			return false;
			
		}else if(pwdChk.val()!=pwdChk2.val()){
			alert("입력하신 비밀번호와 일치하지 않습니다.");
			pwdChk2.focus();
			return false;
			
		}else if(nicknameChk.val()==""){	
			alert("닉네임을 입력해 주세요.");
			nicknameChk.focus();
			return false;
					
		}else if(!nickReg.test(nicknameChk.val())){
			alert("닉네임은 2~7자리 한글,숫자만 입력 가능합니다.");
			pwdChk.focus();
			return false;
			
		}else{//모두 입력 했다면
			vancoUserEdit.submit();
		}	
	}  // joinCheck() 끝
</script>



<script type="text/javascript">
	// 지역 선택 select-option 자바스크립트
	function cityChoice(){
		var userCitySel=document.getElementById("userCitySel");
		var userDistrictSel=document.getElementById("userDistrictSel");
			
		// 지역 매치 시키기
		var seoul =["종로구",	"중구","용산구","성동구","광진구","동대문구","중랑구","성북구","강북구","도봉구",	"노원구",	"은평구",	"서대문구","마포구","양천구",
			"강서구",	"구로구",	"금천구",	"영등포구","동작구","관악구","서초구",	"강남구",	"송파구",	"강동구"];
		var busan=["중구","서구",	"동구","영도구","부산진구","동래구","남구","북구","강서구","해운대구","사하구","금정구","연제구","수영구","사상구","기장군"];
		var daegu = ["중구",	"동구","서구","남구","북구","수성구",	"달서구",	"달성군"];
		var incheon =["중구","동구","미추홀구","연수구","남동구","부평구","계양구","서구","강화군","옹진군"];
		var kwangju =["동구","서구","남구","북구","광산구"];
		var daejeon =["동구 ","중구","서구","유성구","대덕구"];
		var ulsan =["중구","남구","동구","북구","울주군"];
		var sejong =["세종특별자치구"];
		var jeju =[];
		var kyeongki =[];
		var kangwon =[];
		var chungbuk =[];
		var chungnam =[];
		var jeonbuk =[];
		var jeonnam =[];
		var kyeongbuk =[];
		var kyeongnam =[];
		
		// city값 선택때 마다 District에 해당 변수에 값이 쌓이지 않도록 설정
		while(userDistrictSel.length>0){
			userDistrictSel.remove(0);
			userDistrictSel.length--;
		}  // while 끝
		
		// 선택된 지역1(광역)과 지역2(시,구)를 동적으로 매칭
		switch(userCitySel.value){
				 
		case "서울":
			for(var i=0;i<seoul.length;i++){
				// 새로운 2차 옵션 태그 생성하기
				var option = document.createElement("option");
				option.innerHTML=seoul[i]; 
				// 2차 옵션태그를 #userDistrictSel <select>태그 안에 넣기
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "부산":
			for(var i=0;i<busan.length;i++){		
				var option = $("<option >"+busan[i]+"</option>");			
				$("#userDistrictSel").append(option);				
			} // for문 끝
			break;
		case "대구":
			for(var i=0;i<daegu.length;i++){				
				var option = $("<option >"+daegu[i]+"</option>");								
				$("#userDistrictSel").append(option);				
			} // for문 끝
			break;
		case "인천":
			for(var i=0;i<incheon.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=incheon[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "광주":
			for(var i=0;i<kwangju.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=kwangju[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "대전":
			for(var i=0;i<daejeon.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=daejeon[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "울산":
			for(var i=0;i<ulsan.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=ulsan[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "세종":
			for(var i=0;i<sejong.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=sejong[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "제주":
			for(var i=0;i<jeju.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=jeju[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "경기":
			for(var i=0;i<kyeongki.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=kyeongki[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "강원":
			for(var i=0;i<kangwon.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=kangwon[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "충북":
			for(var i=0;i<chungbuk.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=chungbuk[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "충남":
			for(var i=0;i<chungnam.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=chungnam[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "전북":
			for(var i=0;i<jeonbuk.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=jeonbuk[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "전남":
			for(var i=0;i<jeonnam.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=jeonnam[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "경북":
			for(var i=0;i<kyeongbuk.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=kyeongbuk[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		case "경남":
			for(var i=0;i<kyeongnam.length;i++){				
				var option = document.createElement("option");
				option.innerHTML=kyeongnam[i]; 				
				userDistrictSel.append(option);				
			} // for문 끝
			break;
		} //switch 끝
		
		
	
	} //cityChoice() 함수 끝
</script>




<%	//글 작성 시 input태그에 히든값으로 처리할 정보 세션에서 가져오기
	String userId=(String)session.getAttribute("userId");
	String userNickname=(String)session.getAttribute("userNickname");
		
	if(userId==null || userId==""){out.println("<script>alert('로그인을 해 주세요.'); location.href='./userLogin.me'; </script>");}	


	// DB접속하여 user 정보 가져오기.
	MemberDAO mdao = new MemberDAO();
	MemberDTO mdto = new MemberDTO();
	
	mdto = mdao.getUserInfo(userId);
	
	// 필요 정보 저장
	String dogPhoto=mdto.getDogPhoto();
	String userPhoto=mdto.getUserPhoto();
	String userPwd =mdto.getUserPwd();
	String userCity =mdto.getUserCity();
	String userDistrict = mdto.getUserDistrict();
	int userBirth =mdto.getUserBirth();
	String userGender =mdto.getUserGender();
	String userPosition = mdto.getUserPosition();
	String userComment =mdto.getUserComment();

%>

<body>

	<!-- 헤더영역 -->	
		<jsp:include page="../inc/header.jsp" />	

<div class="bodyWrap">	

	<div class="myPageEditWrap">
	
	<div class="editNameBox">
		<span class="editTit">회원정보수정</span>
		<span class="editUserId"><%=mdto.getUserId() %>님</span>
	</div>
	
	<form onsubmit="return editCheck()" action="editMypageAction.me" method="post" name="vancoUserEdit">		
		<table class="myEditTable">
			<tr>
				<td><span>닉네임</span></td>
				<td><input type="text" name = "userNickname" id="userNickname" value="<%=mdto.getUserNickname()%>"></td>					
			</tr>
			<tr>
				<td><span>비밀번호</span></td>
				<td><input type="password" name = "userPwd" id="userPwd"  value="<%=mdto.getUserPwd()%>"></td>
			</tr>
			<tr>
				<td><span>비밀번호확인</span></td>
				<td><input type="password" id="userPwdChk" value="<%=mdto.getUserPwd()%>"></td>
			</tr>
						
			<tr>
				<td><span>지역1(도/광역시)</span></td>
				<td>
					<select name = "userCity" id="userCitySel" onchange="cityChoice();">
						<option>::: 광역시/도 선택 :::</option>
						<option value="서울" <%if(userCity.equals("서울")){%>selected<%}%>>서울</option>
						<option value="부산" <%if(userCity.equals("부산")){%>selected<%}%>>부산</option>	
						<option value="대구" <%if(userCity.equals("대구")){%>selected<%}%>>대구</option>
						<option value="인천" <%if(userCity.equals("인천")){%>selected<%}%>>인천</option>
						<option value="광주" <%if(userCity.equals("광주")){%>selected<%}%>>광주</option>	
						<option value="대전" <%if(userCity.equals("대전")){%>selected<%}%>>대전</option>	
						<option value="울산" <%if(userCity.equals("울산")){%>selected<%}%>>울산</option>
						<option value="세종" <%if(userCity.equals("세종")){%>selected<%}%>>세종</option>	
						<option value="제주" <%if(userCity.equals("제주")){%>selected<%}%>>제주</option>	
						<option value="경기" <%if(userCity.equals("경기")){%>selected<%}%>>경기</option>	
						<option value="강원" <%if(userCity.equals("강원")){%>selected<%}%>>강원</option>	
						<option value="충북" <%if(userCity.equals("충북")){%>selected<%}%>>충북</option>	
						<option value="충남" <%if(userCity.equals("충남")){%>selected<%}%>>충남</option>	
						<option value="전북" <%if(userCity.equals("전북")){%>selected<%}%>>전북</option>	
						<option value="전남" <%if(userCity.equals("전남")){%>selected<%}%>>전남</option>	
						<option value="경북" <%if(userCity.equals("경북")){%>selected<%}%>>경북</option>	
						<option value="경남" <%if(userCity.equals("경남")){%>selected<%}%>>경남</option>								
					</select>
				</td>
			</tr>
			<tr>
				<td><span>지역2(시/구)</span></td>
				<td>			
					<select name="userDistrict" id="userDistrictSel">
						<option>::: 지역1을 선택해 주세요 :::</option>
					</select>
				</td>
			</tr>
			
		<%-- 	<tr>
				<td><span>지역(시/구)</span></td>
				<td><input type="text" name = "userDistrict"  value="<%=mdto.getUserDistrict()%>"></td>
			</tr> --%>
			<tr>
				<td><span>회원종류</span></td>
				<td>
					<select name = "userPosition">
						<option value="예비반려인" <%if(userPosition.equals("예비반려인")){%>selected<%}%>>예비반려인</option>
						<option value="반려인" <%if(userPosition.equals("반려인")){%>selected<%}%>>반려인</option>
						<option value="행동교정사" <%if(userPosition.equals("행동교정사")){%>selected<%}%>>행동교정사</option>
						<option value="수의사" <%if(userPosition.equals("수의사")){%>selected<%}%>>수의사</option>						
					</select>
				</td>
			</tr>
			<tr>
				<td><span>한줄소개</span></td>
				<td><input class="myIntro" name="userComment" type="text" value="<%=mdto.getUserComment()%>" maxlength=50></td>
			</tr>
		</table>
		
		<input type="hidden" name ="userId" value="<%=mdto.getUserId() %>">
		<input type="hidden" name ="userBirth" value="<%=mdto.getUserBirth() %>">
		<input type="submit" class="editSubmit mouseHand" value="완료">	
	</form>		
	
		</div>
	
	
	</div>

</body>
</html>