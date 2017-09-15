<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<c:set value="<%=basePath%>" var="basePath"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<title>图片编辑</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap Core CSS -->
<link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="${basePath}/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="${basePath}/css/modern-business.css" rel="stylesheet">
<link href="${basePath}/css/image-cropper/cropper.css" rel="stylesheet">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="${basePath}/js/jquery.js"></script>
<script src="${basePath}/js/urls.js?id=<%=Math.random() %>"></script>
<script src="${basePath}/js/plugins/plupload-2.1.8/js/plupload.full.min.js?randomId=<%=Math.random()%>"></script>
<script src="${basePath}/js/plugins/image-cropper/cropper.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>

<!-- Morris Charts JavaScript -->
<script src="${basePath}/js/plugins/bootbox.min.js"></script>
<script src="${basePath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${basePath}/js/base.js?id=<%=Math.random() %>"></script>

<style>
.avatar-wrapper {
	height: 250px;
	width: 100%;
	margin-top: 5px;
	margin-bottom: 5px;
	box-shadow: inset 0 0 5px rgba(0, 0, 0, .25);
	background-color: #fcfcfc;
	overflow: hidden;
}

.avatar-wrapper img {
	display: block;
	height: auto;
	max-width: 100%;
}
</style>
</head>

<body>
	<form id="confirmUploadForm"
		action="${basePath}/file/cropperUpload.shtml" method="post">
		<div class="container-fluid">
			<div class="col-xs-6">
				<div id="fileContainer">
					<a id="pickfiles" href="javascript:;"
						class="btn btn-default btn-block">浏览…</a> <input type="hidden"
						id="uploadfiles">
				</div>
				<div id="filelist"></div>
				<div id="console"></div>
			</div>
			
			<div class="col-xs-6">
				<button type="submit" class="btn btn-primary btn-block"
					id="submitBtn" disabled="disabled">
					<span class="glyphicon glyphicon-save" aria-hidden="true"></span>保存
				</button>
			</div>
			<div class="col-xs-12">
				<input type="hidden" name="ctxFilePath" id="ctxFilePath"
					value="${param.oldImgCtxPath}"> <input type="hidden"
					name="cropX" id="cropX" /> <input type="hidden" name="cropY"
					id="cropY" /> <input type="hidden" name="cropWidth" id="cropWidth" />
				<input type="hidden" name="cropHeight" id="cropHeight" /> <input
					type="hidden" name="cropRotate" id="cropRotate" /> <input
					type="hidden" name="cropScaleX" id="cropScaleX" /> <input
					type="hidden" name="cropScaleY" id="cropScaleY" /> <input
					type="hidden" name="targetFld" id="targetFld"
					value="${param.targetFld}" />
				<!-- Wrap the image or canvas element with a block element -->
				<div class="avatar-wrapper">
					<c:choose>
						<c:when test="${param.oldImgCtxPath != null}">
							<img id="cropperSrc" src="${basePath}${param.oldImgCtxPath}" alt=""/>
						</c:when>
						<c:otherwise>
							<img id="cropperSrc" src="http://placehold.it/800x800" alt=""/>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="alert alert-success" role="alert" id="notification"
					style="display: none;">...</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
		// jQuery not really required, it's here to overcome an inability to pass configuration options to the fiddle remotely
		$(function() {
			// Custom example logic
			function $(id) {
				return document.getElementById(id);	
			}

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'pickfiles', // you can pass in id...
				container: $('fileContainer'), // ... or DOM Element itself
				//max_file_size : '5mb',

				// Fake server response here 
				url: "${basePath}/file/uploadTmp.shtml",
				
				// Flash settings
		        flash_swf_url : '${basePath}/js/plugins/plupload-2.1.8/js/Moxie.swf',
		     
		        // Silverlight settings
		        silverlight_xap_url : '${basePath}/js/plugins/plupload-2.1.8/js/Moxie.xap',

					multi_selection : false,

					filters : {
						mime_types : [ {
							title : "Image files",
							extensions : "jpg,gif,png,jpeg"
						} ],
						max_file_size : "10mb",
						prevent_duplicates : true
					},
					//上传前在客户端压缩
					resize : {
						width : 800,
						height : 800,
						crop : false,
						quality : 70
					},

					init : {
						PostInit : function() {
							$('filelist').innerHTML = '';

							$('uploadfiles').onclick = function() {
								uploader.start();
								return false;
							};
						},

						FilesAdded : function(up, files) {
							var maxfiles = 1;
							if (up.files.length > maxfiles) {
								up.splice(maxfiles);
								alert('只能选择 ' + maxfiles + ' 个文件上传！');
							} else {
								plupload.each(files,function(file) {
													$('filelist').innerHTML += '<div id="' + file.id + '">'+ file.name+ ' ('+ plupload.formatSize(file.size)+ ') <b></b></div>';
												});
								//触发上传事件
								$('uploadfiles').click();
							}
						},

						UploadProgress : function(up, file) {
							$(file.id).getElementsByTagName('b')[0].innerHTML = '<span>'+ file.percent + "%</span>";
						},

						Error : function(up, err) {
							var errMsg = err.message;
							if (err.code == '-600') {
								errMsg = '对不起，不能上传大于10M的文件！'
							}
							$('console').innerHTML += "\nError #" + err.code + ": " + errMsg;
						},

						FileUploaded : function(up, file, info) {//文件上传完毕触发
							//console.info(up);
							//console.info(file);
							//console.info(info);
							var response = jQuery.parseJSON(info.response);
							if (response.success) {
								//stopLocalCropper();

								//$('console').innerHTML='<input type="text" name="fileUrl" value="'+ response.files[0].ctxFilePath +'"/>';
								//$('console').innerHTML('<input type="text" name="fileName" value="'+file.name+'"/><br/>');
								$('ctxFilePath').value = response.files[0].ctxFilePath;
								//清空文件列表
								$('filelist').innerHTML = '';

								//更新图片显示
								replaceCropperImg(response.files[0].ctxFilePath);

								//重新初始化，以便重新上传图片
								uploader.splice();
							}
						}
					}
				});

		uploader.init();
	});

	function startLocalCropper() {
		$('#cropperSrc').cropper({
			aspectRatio : 4 / 3,
			dragCrop : false,

			crop : function(e) {
				// Output the result data for cropping image.
				//console.log('******************');
				//console.log('x:' + e.x);
				//console.log('y:' + e.y);
				//console.log('width:' + e.width);
				//console.log('height:' + e.height);
				//console.log('rotate:' + e.rotate);
				//console.log('scaleX:' + e.scaleX);
				//console.log('scaleY:' + e.scaleY);

				$('#cropX').val(e.x);
				$('#cropY').val(e.y);
				$('#cropWidth').val(e.width);
				$('#cropHeight').val(e.height);
				$('#cropRotate').val(e.rotate);
				$('#cropScaleX').val(e.scaleX);
				$('#cropScaleY').val(e.scaleY);

				//激活提交按扭
				enableSubmitBtn();
			}
		});
	}

	function enableSubmitBtn() {
		$("#submitBtn").removeAttr('disabled');
	}

	function stopLocalCropper() {
		//$('#cropperSrc').cropper("reset")
		$('#cropperSrc').cropper('destroy');
	}

	function replaceCropperImg(newImgCtxPath) {
		//$('#cropperSrc').cropper("reset")
		$('#cropperSrc').cropper('replace', '${basePath}' + newImgCtxPath);
	}

	$(function() {
		//图片裁剪FORM提交保存
		portal.init("confirmUploadForm", function(result) {
			if (result.success) {
				var filePath = result.data.ctxFilePath;
				$('#notification').html(result.msg).removeClass().addClass(
						'alert alert-success').show();
				//调用父窗口更新相关属性信息
				window.parent.portal.cropperUploadCallback(result.data, '${basePath}');
				window.parent.portal.closeCropperModal();
			} else {
				$('#notification').html(result.msg).removeClass().addClass(
						'alert alert-danger').show();
			}
		});

		startLocalCropper();
	});
</script>
</html>