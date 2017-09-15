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
<script type="text/javascript"
	src="${basePath}/js/plugins/plupload-2.1.8/js/plupload.full.min.js?randomId=<%=Math.random()%>"></script>
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
				class="form-horizontal" action="${basePath}/slide/save.shtml">
				<input type="hidden" name="id" id="mainId" value="${data.id}"><input
					type="hidden" name="photoPath" id="photoPath"
					value="${data.photoPath}">
				<div class="col-lg-8">
					<div class="form-group">
						<label class="col-lg-2 control-label">幻灯片类别</label>
						<div class="col-lg-10">
							<select class="form-control" id="slideType" name="slideType">
								<c:forEach items="${DICT_TYPE_SLIDETYPE}" var="item"
									varStatus="idx">
									<option value="${item.code}"
										<c:if test="${data.slideType==item.code}">selected</c:if>>${item.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">大标题</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="themeTitle"
								name="themeTitle" placeholder="大标题" required="required"
								value="${data.themeTitle}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">简要</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="themeDesc"
								name="themeDesc" placeholder="简要" value="${data.themeDesc}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">注释</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="themeComments"
								name="themeComments" placeholder="注释"
								value="${data.themeComments}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-2 control-label">显示顺序</label>
						<div class="col-lg-10">
							<input class="form-control" type="text" id="displayOrder"
								name="displayOrder" placeholder="显示顺序"
								value="${data.displayOrder}" />
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
						<div class="col-lg-12">
							<div id="filelist"></div>
							<div id="container">
								<a id="pickfiles" href="javascript:;">[选择文件]</a> <a
									id="uploadfiles" href="javascript:;">[开始上传]</a>
								<p>首页幻灯片建议1920*600; 公司事迹幻灯片建议550*368</p>
							</div>
							<div id="console"></div>
						</div>
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
		$('#editForm').submit();
	}
	
	$(function() {

		portal.init("editForm", function(result) {
			if (result.success) {
				$('#editForm #id').val(result.data.id);
			}
			bootbox.alert(result.msg)
		});

		portal.cropperUploadCallback = function(fileObj, basePath) {
			$('#photoPathDisplay').attr('src', basePath + fileObj.ctxFilePath.replace('_O.', '_M.'));
			$('#photoPath').val(fileObj.ctxFilePath);
		};
		
		// Custom example logic
		/**
		function $(id) {
			return document.getElementById(id);	
		}
		*/

		var uploader = new plupload.Uploader({
			runtimes : 'html5,flash,silverlight,html4',
			browse_button : 'pickfiles', // you can pass in id...
			container: document.getElementById('container'), // ... or DOM Element itself
			max_file_size : '10mb',

			// Fake server response here 
			url: "${basePath}/file/upload.shtml?targetFld=SLIDE",
			
			// Flash settings
	        flash_swf_url : '${basePath}/js/plugins/plupload-2.1.8/js/Moxie.swf',
	     
	        // Silverlight settings
	        silverlight_xap_url : '${basePath}/js/plugins/plupload-2.1.8/js/Moxie.xap',
						filters : [ {
							title : "Image files",
							extensions : "jpg,gif,png"
						}, {
							title : "Zip files",
							extensions : "zip"
						} ],

						init : {
							PostInit : function() {
								document.getElementById('filelist').innerHTML = '';
								document.getElementById('uploadfiles').onclick = function() {
									uploader.start();
									return false;
								};
							},

							FilesAdded : function(up, files) {
								plupload
										.each(
												files,
												function(file) {
													document
															.getElementById('filelist').innerHTML += '<div id="' + file.id + '">'
															+ file.name
															+ ' ('
															+ plupload
																	.formatSize(file.size)
															+ ') <b></b></div>';
												});
							},

							UploadProgress : function(up, file) {
								document.getElementById(file.id)
										.getElementsByTagName('b')[0].innerHTML = '<span>'
										+ file.percent + "%</span>";
							},

							Error : function(up, err) {
								document.getElementById('console').innerHTML += "\nError #"
										+ err.code + ": " + err.message;
							},

							FileUploaded : function(up, file, info) {//文件上传完毕触发
								//console.info(up);
								//console.info(file);
								//console.info(info);
								var response = jQuery.parseJSON(info.response);
								if (response.success) {
									document.getElementById('photoPath').value = response.files[0].ctxFilePath;
									$('#photoPathDisplay').attr('src','${basePath}' + response.files[0].ctxFilePath.replace('_O.','_M.'));
									//document.getElementById('console').innerHTML='<input type="text" name="fileUrl" value="'+ response.files[0].ctxFilePath +'"/>';
									//$('console').innerHTML('<input type="text" name="fileName" value="'+file.name+'"/><br/>');
								}
							}
						}
					});

			uploader.init();

		});
	</script>
</body>

</html>
