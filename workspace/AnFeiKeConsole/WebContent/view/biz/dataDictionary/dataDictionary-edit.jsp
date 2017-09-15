<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../head.jsp"%>

<link href="${basePath}/js/plugins/summernote/summernote.css"
	rel="stylesheet" />
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css"
	rel="stylesheet" />
</head>
<body>
	<%@include file="../../menu.jsp"%>


	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					数据字典 <small>信息维护</small>
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<form id="editForm" name="editForm" method="post" role="form"
				class="form-horizontal"
				action="${basePath}/dataDictionary/save.shtml">
				<input type="hidden" name="id" value="${data.id}"> 
				<div class="col-lg-8">
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>数据字典类型</label>
						<div class="col-lg-8">
							<select class="form-control" id="type" name="type">
								<option value="DICT_TYPE_PRODUCTTYPE"
									<c:if test="${data.type=='DICT_TYPE_PRODUCTTYPE'}">selected</c:if>>机型类别</option>
								<option value="DICT_TYPE_BRANDS"
									<c:if test="${data.type=='DICT_TYPE_BRANDS'}">selected</c:if>>机型品牌</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>编码</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="code" name="code"
								placeholder="对象值" required="required" value="${data.code}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>描述</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="name" name="name"
								placeholder="对象值描述" required="required" value="${data.name}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>排列顺序</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="nameNumber"
								name="nameNumber" placeholder="对象序号" required="required"
								value="${data.nameNumber}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-offset-2 col-lg-10">
							<button type="button" class="btn btn-primary"
								onclick="submitFormWithBtn()">
								<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>保存
							</button>
						</label>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /.row -->

	<%@include file="../../footer.jsp"%>

	<script>
		function submitFormWithBtn() {
			$('#editForm').submit();
		}

		$(function() {

			portal.init("editForm", function(result) {
				if (result.success) {
					$('#editForm #id').val(result.data.id);
				}
				bootbox.alert(result.msg)
			});
		});
	</script>
</body>

</html>
