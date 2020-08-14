<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/userJoin.css" type="text/css" rel="stylesheet">
<link href="css/common.css" type="text/css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.0.js"></script>

<script type="text/javascript">
/* check박스 전체선택 jQuery */
$(document).ready(function(){
	$("#checkAll").on("click", function(){
        if($("#checkAll").prop("checked")){
            $("input[class=agree]").prop("checked",true);
        }else{
            $("input[class=agree]").prop("checked",false);
        }
    });
  $("input[class=agree]").on("click", function(){
  if($(this).is(":checked") == false){
   $("#checkAll").prop("checked",false)
  }
  });
});

</script>

</head>
<body>

  	<div class="agreeBox">
	  	<div class="checkBox">
        	<span class="agreeAll">약관 동의</span>
        	<input type="checkbox" class="agreeAll" id="checkAll">
        	<label class="agreeAll" for="agreeAll">전체 동의</label>
        </div>
        <div class="checkBox">
        	<a href="">이용약관</a>
        	<input type="checkbox" class="agree" name="agree1" id="userAgreement">
        	<label class="" for="userAgreement">동의(필수)</label>
        </div>
        <div class="checkBox">
        	<a href="">개인정보 취급방침</a>
        	<input type="checkbox" class="agree" name="agree2"  id="privacy">
        	<label class="" for="privacy">동의(필수)</label>
        </div>    
 	 </div>
 	 
</body>
</html>