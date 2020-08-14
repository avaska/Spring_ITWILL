<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VANCO : 우리동네 반려견 커뮤니티</title>
<link href="/vc/css/index/common.css" type="text/css" rel="stylesheet">
<link href="/vc/css/inc/header.css" type="text/css" rel="stylesheet">
<link href="/vc/css/store/store.css" type="text/css" rel="stylesheet">


<!-- 모바일 버전 CSS 미디어쿼리 -->
<link href="/vanco/index_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/css/common_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/css/plugin/jquery.bxslider_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">
<link href="/vanco/header_m.css" type="text/css" rel="stylesheet" media="(min-width:0px) and (max-width:576px)">

<!-- 모바일용 뷰포트 메타태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">

<!-- jQuery 연동 -->
<script src="https://code.jquery.com/jquery-latest.min.js"></script>

<!-- bxSlider 플러그인 연동 -->
<link href="/vanco/css/plugin/jquery.bxslider.css" type="text/css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
</head>

<body>

<!-- 헤더영역 -->	
	<jsp:include page="../inc/header.jsp" />	

<!-- 서브메뉴 -->		
	<div class="storeSubMenu">	
		<div class="storeSubMenuWrap">
			<a href="#">카테고리</a>
			<a href="#"> 잘나가요</a>
			<a href="#">깜짝세일</a>
			<a href="#">필수에디션</a>
			<a href="#">나의기부순위</a>		
		</div>	
	</div>

	<div class="productWrap">
		<div class="productInfoBox">
			<div class="productPicBox">
				<img src="/vc/images/default.jpg">
			</div>
			<div class="productSelectBox">
				<span>때수건, 다 때가 있다블라블라블라블라</span>
				<table>
					<tr>
						<td>판매가격</td>
						<td>13,000원</td>
					</tr>
					<tr>
						<td>원산지</td>
						<td>한국</td>
					</tr>
					<tr>
						<td>판매자</td>
						<td>바른간식</td>
					</tr>
					<tr>
						<td>구매수량</td>
						<td>
							<select>
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
								<option>5</option>
							</select>
						</td>
					</tr>
				</table>
				
				<a href="#">구매하기</a>
				<a href="#">장바구니</a>
				<a href="#">문의하기</a>				
			</div>
			<span class="donationInfo">본 상품을 구매하시면 <b>130원</b>의 기부금이 적립됩니다.<br>
			VANCO#에서 구매하시는 모든 제품 수익금의 일부는 유기견의 생명을 살리는데 쓰입니다.
				
			</span>
		</div>
		
		<div class="productContentWrap">
			<img alt="반코상품" src="/vc/images/mallcontent.jpg">
		</div>
		
		<div class="deliveryInfo">
			 <span class="infoTitle">배송 관련 안내</span>
			 <span> - 당일 오후 2시 결제건에 한해 당일발송됩니다.</span>
			 <span> - 배송비는 2,500원입니다. (3만원 이상 구매 시 무료)</span>
			 <span> - CJ대한통운 택배로 발송됩니다.</span>
			 
			 <span class="infoTitle">교환 및 환불 안내</span>
			 <span> - 상품수령일로부터 3일 이내에 반품/교환/취소가 가능합니다.</span>
			 <span> - 상품의 하자 및 오배송 등의 사유로 교환, 환불진행시 배송비용은 판매자에서 부담합니다.</span>
			 <span> - 단순변심으로 인한 교환, 반품을 하실 경우 반송비용은 고객님께서 부담하셔야 합니다.</span>
			 <span> - 포장이 훼손되어 상품가치가 상실된 경우에는 교환/반품 불가합니다.</span>
			 <span> - 교환, 환불 신청은 아래의 문의하기 또는 판매자 연락처로 직접 문의해 주세요. </span>			 	 
		</div>
		
		<div class="userComment">
			<span class="userCommTitle">후기(1,283)</span>
			<div class="starBox">
					<span class="starIconOn"></span>
					<span class="starIconOn"></span>
					<span class="starIconOn"></span>
					<span class="starIconOn"></span>
					<span class="starIconOff"></span>
			</div>		
			<div class="replyContWrap">				
				<span class="span2">정말 필요할 떄 잘 사용 하였습니다. 정말 필요할 떄 하였습니다</span>
				<span  class="span3">믿음이엄마</span>
				<span  class="span4">2019.03.01</span>												
			</div>
			<div class="replyContWrap">	
				<span class="replyAngle span1"></span>	
				<span class="span2">정말 필요할 떄 잘 사용 하였습니다. 정말 필요할 떄 하였습니다</span>
				<span  class="span3">판매자답변</span>
				<span  class="span4">2019.03.01</span>										
			</div>
			<div class="replyContWrap">	
				<span class="span2">정말 필요할 떄 잘 사용 하였습니다. 정말 필요할 떄 하였습니다</span>
				<span  class="span3">믿음이엄마</span>
				<span  class="span4">2019.03.01</span>										
			</div>
			<div class="replyContWrap">	
				<span class="replyAngle span1"></span>	
				<span class="span2">정말 필요할 떄 잘 사용 하였습니다.정말 필요할 떄 하였습니다</span>
				<span  class="span3">판매자답변</span>
				<span  class="span4">2019.03.01</span>										
			</div>
		</div>
		
		
		
		<div class="userQuestion">
			<span class="userCommTitle">제품문의</span>
			<a href=# class="questionBtn">문의하기</a>			
			<div class="replyContWrap">				
				<span class="span2">대량구매 문의 드립니다</span>
				<span  class="span3">믿음이엄마</span>
				<span  class="span4">2019.03.01</span>												
			</div>
			<div class="replyContWrap">	
				<span class="replyAngle span1"></span>	
				<span class="span2">안녕하세요. 답변드립니다.</span>
				<span  class="span3">판매자답변</span>
				<span  class="span4">2019.03.01</span>										
			</div>
			<div class="replyContWrap">	
				<span class="span2">정말 필요할 떄 잘 사용 하였습니다.정말 필요할 떄 하였습니다</span>
				<span  class="span3">믿음이엄마</span>
				<span  class="span4">2019.03.01</span>										
			</div>
			<div class="replyContWrap">	
				<span class="replyAngle span1"></span>	
				<span class="span2">정말 필요할 떄 잘 사용 하였습니다.정말 필요할 떄 하였습니다</span>
				<span  class="span3">판매자답변</span>
				<span  class="span4">2019.03.01</span>										
			</div>
		</div>
			
		
		
		
		
		
		
	</div>

</body>
</html>