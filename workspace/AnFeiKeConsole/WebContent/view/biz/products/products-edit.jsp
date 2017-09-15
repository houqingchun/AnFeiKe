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
					在售机型 <small>信息维护</small>
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<form id="editForm" name="editForm" method="post" role="form"
				class="form-horizontal" action="${basePath}/products/save.shtml">
				<input type="hidden" name="id" id="mainId" value="${data.id}">
				<input type="hidden" name="rangesUnit" value="公里"> <input
					type="hidden" name="speedsUnit" value="公里/小时"> <input
					type="hidden" name="loadsUnit" value="吨"> <input
					type="hidden" name="priceUnit" value="万"> <input
					type="hidden" name="prePayUnit" value="万"> <input
					type="hidden" name="photoPath" id="photoPath"
					value="${data.photoPath}"> <input type="hidden"
					name="description" id="description"></input> <input type="hidden"
					name="historyDesc" id="historyDesc"></input>
				<div class="col-lg-8">
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>机型编号</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="name" name="name"
								placeholder="机型名称" required="required" value="${data.name}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>类别</label>
						<div class="col-lg-8">
							<select class="form-control" id="types" name="types">
								<c:forEach items="${DICT_TYPE_PRODUCTTYPE}" var="item"
									varStatus="idx">
									<option value="${item.code}"
										<c:if test="${data.types==item.code}">selected</c:if>>${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>品牌</label>
						<div class="col-lg-8">
							<select class="form-control" id="brand" name="brand">
								<c:forEach items="${DICT_TYPE_BRANDS}" var="item"
									varStatus="idx">
									<option value="${item.code}"
										<c:if test="${data.types==item.code}">selected</c:if>>${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>座位数</label>
						<div class="col-lg-4">
							<div class="input-group">
								<input class="form-control" type="number" name="seatsNo"
									required="required" value="${data.seatsNo}"> <span
									class="input-group-addon">个</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>续航里程</label>
						<div class="col-lg-4">
							<div class="input-group">
								<input class="form-control" type="number" name="ranges"
									required="required" value="${data.ranges}"> <span
									class="input-group-addon">公里</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">速度</label>
						<div class="col-lg-4">
							<div class="input-group">
								<input class="form-control" type="number" name="speeds"
									value="${data.speeds}"> <span class="input-group-addon">公里/小时</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>最大载重</label>
						<div class="col-lg-4">
							<div class="input-group">
								<input class="form-control" type="number" name="loads"
									required="required" value="${data.loads}"> <span
									class="input-group-addon">吨</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>指导价</label>
						<div class="col-lg-4">
							<div class="input-group">
								<input class="form-control" type="number" name="price"
									value="${data.price}" required="required"> <span
									class="input-group-addon">万元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>预付价</label>
						<div class="col-lg-4">
							<div class="input-group">
								<input class="form-control" type="number" name="prePay"
									value="${data.prePay}" required="required"> <span
									class="input-group-addon">万元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">制造商</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" maxlength="100"
								name="manufacture" placeholder="制造商" required="required"
								value="${data.manufacture}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>简述</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="shortDesc"
								name="shortDesc" placeholder="简述" required="required"
								maxlength="500" value="${data.shortDesc}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">详细描述</label>
						<div class="col-lg-10">
							<div id="descriptionEditor">${data.description}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-offset-2 col-lg-10">
							<button type="button" class="btn btn-primary"
								onclick="submitFormWithBtn('PUBLISHED')">
								<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>保存
							</button>
						</label>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="form-group">
						<div class="col-lg-12">
							<c:choose>
								<c:when
									test="${data.photoPath == null || fn:trim(data.photoPath) == ''}">
									<img id="photoPathDisplay" class="img-responsive img-rounded"
										src="http://placehold.it/800x600">
								</c:when>
								<c:otherwise>
									<img id="photoPathDisplay" class="img-responsive img-rounded"
										src="${basePath}${fn:replace(data.photoPath,'_O','_M')}">
								</c:otherwise>
							</c:choose>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-12"> <a href="#"
							onclick="portal.openCropperModal('PRODUCTS/${data.id}','${basePath}');"
							class="btn btn-default"><span
								class="glyphicon glyphicon-uploads" aria-hidden="true"></span>上传图片</a>
							上传后需要保存！
						</label>
					</div>
					<div class="form-group" id="photosContainer">
						<c:forEach items="${data.photos}" var="item" varStatus="idx">
							<div class="col-lg-3 img-portfolio" id="photo_${item.id}">
								<input type="hidden" name="imageFilePaths"
									value="${item.photoPath}" /><img
									class="img-responsive img-rounded"
									src="${basePath}${fn:replace(item.photoPath,'_O','_M')}">
								<h4>
									<a class="btn btn-xs btn-link" href="#"
										onclick="javascript:$('#photo_${item.id}').remove();">删除</a>&nbsp;
									<a class="btn btn-xs btn-link"
										href="javascript:setCurrentImgAsMaster('${basePath}','${item.photoPath}');">设为封面</a>
								</h4>
							</div>
						</c:forEach>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- /.row -->

	<script type="text/javascript"
		src="${basePath}/view/biz/products/page-products-edit.js"></script>
	<%@include file="../../footer.jsp"%>

	<script type="text/javascript"
		src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js">
		
	</script>

	<script type="text/javascript"
		src="${basePath}/js/plugins/summernote/summernote-zh-CN.js"></script>
		
		<script>
		$(function() {
			portal.initRichText('descriptionEditor', '${basePath}', '..');
			
			portal.init("editForm", function(result) {
				if (result.success) {
					$('#editForm #id').val(result.data.id);
				}
				bootbox.alert(result.msg)
			});
			
			portal.cropperUploadCallback =  function(fileObj, basePath) {
				var imgPath = fileObj.ctxFilePath.replace('_O.', '_M.')
				
				var eleHtml = '<div class="col-lg-3 img-portfolio" id="photo_' + fileObj.id + '"><input type="hidden" name="imageFilePaths"';
				eleHtml = eleHtml + 'value="' + fileObj.ctxFilePath + '" /><img class="img-responsive img-rounded"	src="' + basePath + imgPath + '">';
				eleHtml = eleHtml + '<h4><a class="btn btn-xs btn-link" href="javascript:$(\'#photo_' + fileObj.id + '\').remove();">删除</a>&nbsp;';
				eleHtml = eleHtml + '<a class="btn btn-xs btn-link" href="javascript:setCurrentImgAsMaster(\'' + basePath + '\',\'' + fileObj.ctxFilePath + '\');">设为封面</a></h4>';
				eleHtml = eleHtml + '</div>'
				$('#photosContainer').append(eleHtml)
			};
		});
		</script>
</body>

</html>
