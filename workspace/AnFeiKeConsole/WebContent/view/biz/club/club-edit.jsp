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
					公司 <small>信息维护</small>
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<form id="editForm" name="editForm" method="post" role="form"
				class="form-horizontal" action="${basePath}/club/save.shtml">
				<input type="hidden" name="id" id="mainId" value="${data.id}">
				<input type="hidden" name="background" id="background"></input> <input
					type="hidden" name="qualification" id="qualification"></input> <input
					type="hidden" name="assurance" id="assurance"></input> <input
					type="hidden" name="responsibility" id="responsibility"></input> <input
					type="hidden" name="contactUs" id="contactUs"></input> <input
					type="hidden" name="recruit" id="recruit" />
				<div class="col-lg-12">
					<div class="form-group">
						<label class="col-lg-2 control-label">俱乐部背景</label>
						<div class="col-lg-10">
							<div id="backgroundEditor">${data.background}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">俱乐部资质</label>
						<div class="col-lg-10">
							<div id="qualificationEditor">${data.qualification}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">保险内容</label>
						<div class="col-lg-10">
							<div id="assuranceEditor">${data.assurance}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">社会责任</label>
						<div class="col-lg-10">
							<div id="responsibilityEditor">${data.responsibility}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">联系我们</label>
						<div class="col-lg-10">
							<div id="contactUsEditor">${data.contactUs}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">俱乐部招聘</label>
						<div class="col-lg-10">
							<div id="recruitEditor">${data.recruit}</div>
						</div>
					</div>
				</div>
				<div class="col-lg-12">
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

	<script>
	
		function submitFormWithBtn() {
			$('#qualification').val($('#qualificationEditor').summernote('code'));
			$('#background').val($('#backgroundEditor').summernote('code'));
			$('#assurance').val($('#assuranceEditor').summernote('code'));
			$('#responsibility').val($('#responsibilityEditor').summernote('code'));
			$('#contactUs').val($('#contactUsEditor').summernote('code'));
			$('#recruit').val($('#recruitEditor').summernote('code'));
			$('#editForm').submit();
		}

		$(function() {
			portal.initRichText('backgroundEditor', '${basePath}', '..');
			portal.initRichText('qualificationEditor', '${basePath}', '..');
			portal.initRichText('assuranceEditor', '${basePath}', '..');
			portal.initRichText('responsibilityEditor', '${basePath}', '..');
			portal.initRichText('contactUsEditor', '${basePath}', '..');
			portal.initRichText('recruitEditor', '${basePath}', '..');

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
