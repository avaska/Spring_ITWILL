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

<%@ include file="../include/footer.jsp" %>
