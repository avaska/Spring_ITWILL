// 회원가입 동의하기 필수체크 js //
	function agreeCheckId(){ // id 로그인 시 약관에 동의하지 않으면 다음으로 진행 안되도록.
		var vancoUserJoin = document.vancoUserJoin;
		var agreeChk1=$("input[name=agree1]");
		var agreeChk2=$("input[name=agree2]");
		
		// 미입력 시 입력요청
		if(agreeChk1.is(":checked") && agreeChk2.is(":checked")){
			location.href="idUserJoin.me";					
		}else{
			alert("이용약관과 개인정보 취급방침에 동의해 주세요.")
			return false;
		}	
	}  // joinCheck() 끝
	

	
// 카카오톡 로그인 시 약관 동의 않으면 다음 진행 안되도록!

	function agreeCheckKakao(){ // 카카오톡 로그인 시 약관에 동의하지 않으면 다음으로 진행 안되도록.
		var vancoUserJoin = document.vancoUserJoin;
		var agreeChk1=$("input[name=agree1]");
		var agreeChk2=$("input[name=agree2]");
		
		// 미입력 시 입력요청
		if(agreeChk1.is(":checked") && agreeChk2.is(":checked")){
			loginWithKakao();	
		}else{
			alert("이용약관과 개인정보 취급방침에 동의해 주세요.")
			return false;
		}	
	}  // joinCheck() 끝



// 카카오톡 로그인 & 회원가입 //  자바스크립트	
	function loginWithKakao() {
	Kakao.init('01a0724ffc60f8d7348f7606d2de58b3');
	  // 로그인 창을 띄웁니다.
	  Kakao.Auth.login({
	    success: function(authObj) {
	         //alert(JSON.stringify(authObj)); 
	         
	         //로그인 정보 확인 성공 시 API를 호출 시킨다.
	         Kakao.API.request({
	        	 url:'/v2/user/me',
	        	 success:function(res){
	        		// res.id+="@k"; 
	        		// alert(res.id);
	        		// alert(JSON.stringify(res));
	        		
	        		$.ajax({
	        			url:"snsIdChk.me?userId="+res.id,
	        			headers : {
	                        "Accept" : "application/json",
	                        "Content-Type" : "application/json"},
	                        
	                    success:function(idChk){
	                	   if(idChk=='noId'){//DB에 아이디가 없으면 회원가입 
	                		    			                    	   
	                	   		var userId=res.id;
	                	   		var userPwd=res.id+"@k";
	                	   		var userNickname=res.properties.nickname;
	                	   		var userPhoto=res.properties.profile_image;
	                	   		
	                	   				                    	   					                    	   		
	                	   		//ajax를 이용해 서블릿을 호출하고, 데이터를 보내어 회원가입 시킴
	                	   		 $.ajax({
	                	   			url:"snsUserJoinAction.me",
	                	   			async:true,
	                	   			dataType:"text",
	                	   			type:"post",
	                	   			data:{
	                	   				userId:userId,
	                	   				userPwd:userPwd,
	                	   				userNickname:userNickname,
	                	   				userPhoto:userPhoto			                    	   				
	                	   			},
	                	   			success:function(idChk){
	                	   				location.href="vanco.me";
	                	   			}
	                	   		}); 
	                	   }else{
	                		   location.href="vanco.me";
	                	   }
	                	   
	                	   
	                   }, //idchk success 종료
	        		}); //ajax 종료
	        	 } //res success 종료
	         });
	    },
	    fail: function(err) {
	      alert(JSON.stringify(err));
	    }
	  });
	};
	
	
//// 네이버 아이디 로그인  영역
	var naver_id_login = new naver_id_login("YOUR_CLIENT_ID", "YOUR_CALLBACK_URL");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("YOUR_SERVICE_URL");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
  	
    
  	// 접근 토큰 값 출력
    alert(naver_id_login.oauthParams.access_token);
    // 네이버 사용자 프로필 조회
    naver_id_login.get_naver_userprofile("naverSignInCallback()");
    // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
    function naverSignInCallback() {
      alert(naver_id_login.getProfileData('email'));
      alert(naver_id_login.getProfileData('nickname'));
      alert(naver_id_login.getProfileData('age'));
    }
  	
	