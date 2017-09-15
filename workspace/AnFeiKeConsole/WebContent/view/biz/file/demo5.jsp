<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<c:set value="<%=basePath%>" var="basePath"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
 <!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link href="${basePath}/css/style.css" rel="stylesheet" type="text/css"	media="all"/>

<link  href="${basePath}/css/image-cropper/cropper.css" rel="stylesheet">

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="${basePath}/js/plugins/jquery-validate/jquery.validate.js"></script>
<script src="${basePath}/js/plugins/jquery-validate/messages_zh.js"></script>
<script src="${basePath}/js/plugins/bootbox.min.js"></script>
<script src="${basePath}/js/urls.js"></script>
<script src="${basePath}/js/package.js"></script>
<script src="${basePath}/js/base.js?id=2"></script>
<script src="${pageContext.request.contextPath}/js/plugins/plupload-2.1.8/js/plupload.full.min.js?randomId=<%=Math.random()%>"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${basePath}/js/plugins/image-cropper/cropper.js"></script>
</head>
<body>

<script type="text/javascript">
function isImageFile(file) {
	alert(file.type)
	if (file.type) {
		return /^image\/\w+$/.test(file.type);
	} else {
		return /\.(jpg|jpeg|png|gif)$/.test(file);
	}
}

function initCropper(){
	$('#cropperSrc').cropper({
		  aspectRatio: 1 / 1,
		  crop: function(e) {
		    // Output the result data for cropping image.
		    console.log(e.x);
		    console.log(e.y);
		    console.log(e.width);
		    console.log(e.height);
		    console.log(e.rotate);
		    console.log(e.scaleX);
		    console.log(e.scaleY);
		  }
		});
}

</script>

<form id="confirmUploadForm" action="${basePath}/file/cropperUpload.shtml" method="post">
      <input type="text" name="cropperFilePath" id="cropperFilePath">      
	  <!-- Wrap the image or canvas element with a block element -->
	  <div class="container">
		<img id="cropperSrc" src="#" alt="your image" />
	  </div>
      <button type="submit" class="btn btn-primary btn-block avatar-save">Done</button>
</form>
<h1>jQuery UI Widget</h1>

	<form id="form" method="post" action="/echo/json">
		<div id="filelist"></div>
		<div id="container">
			<a id="pickfiles" href="javascript:;">[Select files]</a> <a
				id="uploadfiles" href="javascript:;">[Upload files]</a>
		</div>
		<div id="console"></div>
	</form>
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
				container: $('container'), // ... or DOM Element itself
				max_file_size : '10mb',

				// Fake server response here 
				url: "<%=basePath%>/file/upload.shtml",
				
				// Flash settings
		        flash_swf_url : '<%=basePath%>/js/plupload-2.1.8/js/Moxie.swf',
		     
		        // Silverlight settings
		        silverlight_xap_url : '<%=basePath%>/js/plupload-2.1.8/js/Moxie.xap',
				filters : [		
					{title : "Image files", extensions : "jpg,gif,png"},
					{title : "Zip files", extensions : "zip"}
				],

				init: {
					PostInit: function() {
						$('filelist').innerHTML = '';

						$('uploadfiles').onclick = function() {
							uploader.start();
							return false;
						};
					},

					FilesAdded: function(up, files) {
						plupload.each(files, function(file) {
							$('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
						});
					},

					UploadProgress: function(up, file) {
						$(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
					},

					Error: function(up, err) {
						$('console').innerHTML += "\nError #" + err.code + ": " + err.message;
					}, 
					
					FileUploaded : function(up, file, info) {//文件上传完毕触发
		                    console.info(up);
		                    console.info(file);
		                    console.info(info);
		                    var response = jQuery.parseJSON(info.response);
		                    if (response.success) {
		                    	alert(urls['msUrl']);
			    				alert(response.files[0].ctxFilePath);		             
		                        $('console').innerHTML='<input type="text" name="fileUrl" value="'+ response.files[0].ctxFilePath +'"/>';
		                        //$('console').innerHTML('<input type="text" name="fileName" value="'+file.name+'"/><br/>');
		                        $('cropperFilePath').value=response.files[0].ctxFilePath;
			    				//显示小围标
			    				$('cropperSrc').src= urls['msUrl'] + '/' + response.files[0].ctxFilePath;
			    				
			    				initCropper();
		                    }
		                }
				}
			});

			uploader.init();
		});
	</script>
</body>
</html>