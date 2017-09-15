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
					新闻 <small>信息维护</small>
				</h1>
			</div>
		</div>
		<!-- /.row -->
		<div class="row">
			<form id="editForm" name="editForm" method="post" role="form"
				class="form-horizontal" action="${basePath}/news/save.shtml">
				<input type="hidden" name="id" id="mainId" value="${data.id}">
				<input type="hidden" name="status" value="1"> <input
					type="hidden" name="photoPath" id="photoPath"
					value="${data.photoPath}"> <input type="hidden"
					name="description" id="description"></input>
				<div class="col-lg-8">
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>新闻标题</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" id="subject"
								name="subject" placeholder="新闻标题" required="required"
								value="${data.subject}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label"><span
							class="requiredmark">*</span>类别</label>
						<div class="col-lg-8">
							<select class="form-control" id="types" name="types">
								<c:forEach items="${DICT_TYPE_NEWSTYPE}" var="item"
									varStatus="idx">
									<option value="${item.code}"
										<c:if test="${data.types==item.code}">selected</c:if>>${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">是否置顶</label>
						<div class="col-lg-8">
							<label class="checkbox-inline"> <input type="checkbox"
								id="onTop" name="onTop"
								<c:if test="${data.onTop=='on'}">checked</c:if> />勾选置顶
							</label>
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
								onclick="submitFormWithBtn()">
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
							onclick="portal.openCropperModal('NEWS/${data.id}','${basePath}');"
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
		src="${basePath}/view/biz/news/page-news-edit.js"></script>
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

			portal.cropperUploadCallback = function(fileObj, basePath) {
				var imgPath = fileObj.ctxFilePath.replace('_O.', '_M.')

				var eleHtml = '<div class="col-lg-3 img-portfolio" id="photo_' + fileObj.id + '"><input type="hidden" name="imageFilePaths"';
				eleHtml = eleHtml + 'value="' + fileObj.ctxFilePath + '" /><img class="img-responsive img-rounded"	src="' + basePath + '/' + imgPath + '">';
				eleHtml = eleHtml
						+ '<h4><a class="btn btn-xs btn-link" href="javascript:$(\'#photo_'
						+ fileObj.id + '\').remove();">删除</a>&nbsp;';
				eleHtml = eleHtml
						+ '<a class="btn btn-xs btn-link" href="javascript:setCurrentImgAsMaster(\''
						+ basePath + '\',\'' + fileObj.ctxFilePath
						+ '\');">设为封面</a></h4>';
				eleHtml = eleHtml + '</div>'
				$('#photosContainer').append(eleHtml)
			};
		});
	</script>
</body>

</html>
