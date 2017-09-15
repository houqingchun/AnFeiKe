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
				class="form-horizontal" action="${basePath}/company/save.shtml">
				<input type="hidden" name="id" id="mainId" value="${data.id}">
				<input type="hidden" name="longDesc" id="longDesc"></input> <input
					type="hidden" name="training" id="training"></input> <input
					type="hidden" name="trusteeship" id="trusteeship"></input> <input
					type="hidden" name="rent" id="rent"></input> <input type="hidden"
					name="sale" id="sale"></input> <input type="hidden"
					name="photoPath" id="photoPath" value="${data.photoPath}">
				<div class="col-lg-12">
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>公司名称</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="name"
								name="name" placeholder="公司名称" required="required"
								value="${data.name}" />
						</div>
						<label class="col-lg-2 control-label">服务宗旨</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="serviceAim"
								name="serviceAim" placeholder="服务宗旨" required="required"
								value="${data.serviceAim}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">备案号</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="icp" name="icp"
								placeholder="备案号" required="required" value="${data.icp}" />
						</div>
						<label class="col-lg-2 control-label">口号</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="slogan"
								name="slogan" placeholder="口号" required="required"
								value="${data.slogan}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">公司简介</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="shortDesc" name="shortDesc"
								placeholder="公司简介" required="required" value="${data.shortDesc}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">公司详细描述</label>
						<div class="col-lg-10">
							<div id="longDescEditor">${data.longDesc}</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-2 control-label">联系地址</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="address"
								name="address" placeholder="联系地址" required="required"
								value="${data.address}" />
						</div>
						<label class="col-lg-2 control-label">邮编</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="zip" name="zip"
								placeholder="邮编" required="required" value="${data.zip}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">座机</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="tel" name="tel"
								placeholder="座机" required="required" value="${data.tel}" />
						</div>
						<label class="col-lg-2 control-label">移动电话</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="mobile"
								name="mobile" placeholder="移动电话" required="required"
								value="${data.mobile}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">联系人</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="contact"
								name="contact" placeholder="联系人" required="required"
								value="${data.contact}" />
						</div>
						<label class="col-lg-2 control-label">邮件</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" id="email" name="email"
								placeholder="邮件" required="required" value="${data.email}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">咨询热线</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="hotlines"
								name="hotlines" placeholder="咨询热线" required="required"
								value="${data.hotlines}" /><span>多人用半角';'分隔</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">飞行培训</label>
						<div class="col-lg-10">
							<div id="trainingEditor">${data.training}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">飞机托管</label>
						<div class="col-lg-10">
							<div id="trusteeshipEditor">${data.trusteeship}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">飞机租赁</label>
						<div class="col-lg-10">
							<div id="rentEditor">${data.rent}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">飞机销售</label>
						<div class="col-lg-10">
							<div id="saleEditor">${data.sale}</div>
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
			$('#longDesc').val($('#longDescEditor').summernote('code'));
			$('#training').val($('#trainingEditor').summernote('code'));
			$('#trusteeship').val($('#trusteeshipEditor').summernote('code'));
			$('#rent').val($('#rentEditor').summernote('code'));
			$('#sale').val($('#saleEditor').summernote('code'));
			$('#editForm').submit();
		}

		$(function() {
			portal.initRichText('longDescEditor', '${basePath}', '..');
			portal.initRichText('trainingEditor', '${basePath}', '..');
			portal.initRichText('trusteeshipEditor', '${basePath}', '..');
			portal.initRichText('rentEditor', '${basePath}', '..');
			portal.initRichText('saleEditor', '${basePath}', '..');

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
