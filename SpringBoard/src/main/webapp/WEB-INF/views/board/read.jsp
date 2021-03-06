<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">게시글 읽기</h3>
				</div>
				<!-- /.box-header -->
				
				<form role="form" action="" method="post">
					<input type="hidden" name="bno" value="${boardVO.bno}">
				</form>
				
				<div class="box-body">
					<div class="form-group">
						<label for="boardNum">글 번호</label> 
						<input type="text" name='boardNum' 
						       class="form-control" 
							   value="${boardVO.bno}" 
							   readonly>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">제목</label> 
						<input type="text" name='title' 
						       class="form-control" 
							   value="${boardVO.title}"
							   readonly>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">내용</label>
						<textarea class="form-control" 
								  name="content" rows="3"
								  placeholder="Enter ..." 
								  readonly>${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">글쓴이</label> 
						<input type="text" name="writer"
							   class="form-control" 
							   value="${boardVO.writer}"
							   readonly>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="button" class="btn btn-warning">수정하기</button>
					<button type="button" class="btn btn-danger">삭제하기</button>
					<button type="button" class="btn btn-primary">목록으로</button>
				</div>


			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert(" 확인 ! ");

		//목록으로 버튼 클릭
		$(".btn-primary").on("click", function(){
			// 목록 페이지로 이동
			// "./" 상위폴더에 접근 -> 컨트롤러에 "board"가 맵핑되어 있어 상위폴더로 접근시 board를 또 접근하게됨
			//window.location.href="./board/listAll"; (X)
			window.location.href="/board/listAll";
			});

		// 본문에 글 번호 정보를 가지고 있는 form 태그 정보를 가져오기		
		
		var formObj = $("form[role='form']"); // 태그속성 선택자로 가져옴	
		alert("form : " + formObj);
		console.log(formObj);
		
		
		// 삭제하기 클릭
		$(".btn-danger").on("click", function(){
			// 삭제 페이지로 이동
			// 이동 시 삭제할 글 번호를 가지고 이동

			//form이 post방식이어서 body에 정보가 들어 있음
			formObj.attr("action", "/board/remove");
			formObj.submit();
			});		

		// 수정하기 클릭
		$(".btn-warning").click(function(){
			//alert("수정하기");
			
			// 수정하는 페이지로 이동( + 수정할 글 번호 가지고 이동)
			// /board/modify 주소로 bno 저장해서 get 방식 submit()
			// => 수정할 정보를 입력 받는 페이지로 이동 
			formObj.attr("action", "/board/modify");
			formObj.attr("method", "get"); //get방식으로 변경
			formObj.submit();
			
			});


		
		}); //jQuery 끝		
		
</script>

<%@ include file="../include/footer.jsp" %>
