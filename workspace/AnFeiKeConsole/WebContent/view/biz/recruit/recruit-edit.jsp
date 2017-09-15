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
					招聘 <small>信息维护</small>
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<form id="editForm" name="editForm" method="post" role="form"
				class="form-horizontal" action="${basePath}/recruit/save.shtml">
				<input type="hidden" name="id" id="mainId" value="${data.id}">
				<input type="hidden" name="jobReq" id="jobReq" >
				<div class="col-lg-12">
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>职位名称</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="jobTitle"
								name="jobTitle" placeholder="职位名称" required="required"
								value="${data.jobTitle}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>工作地</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="jobBase"
								name="jobBase" placeholder="工作地" required="required"
								value="${data.jobBase}" />
						</div>
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>招聘人数</label>
						<div class="col-lg-4">
							<input class="form-control" type="number" id="headCount"
								name="headCount" placeholder="招聘人数" required="required"
								value="${data.headCount}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>联络人</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="contact"
								name="contact" placeholder="联系人" required="required"
								value="${data.contact}" />
						</div>
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>手机</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="mobile"
								name="mobile" placeholder="手机" required="required"
								value="${data.mobile}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">职位要求</label>
						<div class="col-lg-10">
							<div id="jobReqEditor">${data.jobReq}</div>
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
	
	
	<script type="text/javascript"
		src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js">
		
	</script>

	<script type="text/javascript"
		src="${basePath}/js/plugins/summernote/summernote-zh-CN.js"></script>
	
	
	<script>
		$(function() {
			portal.initRichText('jobReqEditor', '${basePath}', '..');
			
			portal.init("editForm", function(result) {
				if (result.success) {
					$('#editForm #id').val(result.data.id);
				}
				bootbox.alert(result.msg)
			});
		});
		
		function submitFormWithBtn() {
			$('#jobReq').val($('#jobReqEditor').summernote('code'));
			$('#editForm').submit();
		}

	</script>
</body>

</html>
