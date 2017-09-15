<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.portal.com/sac" prefix="sac"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<c:set value="<%=basePath%>" var="basePath"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${basePath}/js/plupload-2.1.8/js/jquery.ui.plupload/css/jquery.ui.plupload.css?id=3">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="${basePath}/js/plupload-2.1.8/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${basePath}/js/plupload-2.1.8/js/jquery.ui.plupload/jquery.ui.plupload.js"></script>
<script type="text/javascript" src="${basePath}/js/plupload-2.1.8/js/i18n/zh_CN.js"></script>

<style>
.plupload_filelist,
.plupload_file_status,
.plupload_file_size,
.plupload_file_action,
.plupload_cell{
background-color:white;
color:gray;
border:1 solid white;
}
.plupload_logo{
display: none;
}

.plupload_header_text,
.plupload_header_title{
margin-left: -40px;
}

.plupload_wrapper{
min-width: 480px;
}

.plupload_container {
	_height: 280px;
	min-height: 280px;
}

</style>



</head>
<body>
<div id="uploadedFiles" style="display: none;"></div>
<div class="container-fluid">
	<div id="uploader" class="col-xs-12"></div>
    <div class="col-xs-12" style="display: none;">
		<button type="button" class="btn btn-primary btn-block" id="submitBtn"  disabled="disabled"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>保存</button>
	</div>
</div>
<script>

$(document).ready(function() {
	function enableSubmitBtn(){
		$("#submitBtn").removeAttr('disabled');
	}
	
	$("#uploader").plupload({
		// General settings
		runtimes: 'html5,flash,silverlight,html4',
		
		// Fake server response here 
		// url : '../upload.php',
		url: "${basePath}/file/plupload.shtml?targetFld=${param.targetFld}&targetFileType=${param.targetFileType}",

		// Maximum file size
		max_file_size: '10mb',

		// User can upload no more then 20 files in one go (sets multiple_queues to false)
		<c:choose>
			<c:when test="${param.multiple == true}">
				max_file_count: 10,	
			</c:when>
			<c:otherwise>
				max_file_count: 1,
			</c:otherwise>
		</c:choose>
		
		multiple_queues: false,
		
		chunk_size: '1mb',

		// Resize images on clientside if we can
		//resize : {width: 1200,	height: 1200,quality: 90, crop: false	},

		// Specify what files to browse for
		filters: {
			 mime_types : [
				<c:choose>
					<c:when test="${param.targetFileType == 'IMAGE'}">
						{title : "Image files", extensions : "jpg,gif,png,jpeg"}	
					</c:when>
					<c:when test="${param.targetFileType == 'AUDIO'}">
						{title : "Audio files", extensions : "audio,mp3,mpeg,wav"}	
					</c:when>
					<c:otherwise>
						{title : "All files", extensions : "*"}
					</c:otherwise>
				</c:choose>
			    
			 ],
        	max_file_size: "10mb",
        	prevent_duplicates: true
		},

		// Rename files by clicking on their titles
		rename: false,
		
		// Sort files
		sortable: false,

		// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
		dragdrop: true,

		// Views to activate
		views: {
			list: false,
			thumbs: false, // Show thumbs
			active: 'thumbs'
		},
		//上传前在客户端压缩
		resize: {
			  width: 800,
			  height: 800,
			  crop: false,
			  quality: 70
		},
		 // Flash settings
        flash_swf_url : '${basePath}/js/plupload-2.1.8/js/Moxie.swf',
     
        // Silverlight settings
        silverlight_xap_url : '${basePath}/js/plupload-2.1.8/js/Moxie.xap',
        
        init :{
        	 FileUploaded : function(up, file, info) {//文件上传完毕触发
                 console.info(up);
                 console.info(file);
                 console.info(info);
                 var response = $.parseJSON(info.response);
                 if (response.success) {
                	 var filePathV = response.files[0].ctxFilePath;
                     $('#uploadedFiles').append('<input type="hidden" name="imageFilePaths" value="'+ filePathV +'"/>');
                     //针对上传图片文件，增加显示缩略图元素
                     if ('${param.targetFileType}' == 'IMAGE'){
                     	$('#uploadedFiles').append('<div class="col-xs-4 col-xs-2"><img src="${basePath}/'+ filePathV.replace('_O.','_S.') +'"  class="thumbnail"/></div>');
                     }
                 }

             }
        }
	});
	
	// When all files are uploaded submit form
	$('#uploader').on('complete', function() {
		//调用父窗口更新相关属性信息
		window.parent.imgBatchUploadCallback($('#uploadedFiles').html());
		
        //激活保存按扭
        enableSubmitBtn();
	});


	// Handle the case when form was submitted before uploading has finished
	$('#submitBtn').click(function(e) {
		// Files in queue upload them first
		if ($('#uploader').plupload('getFiles').length > 0) {

			// When all files are uploaded submit form
			$('#uploader').on('complete', function() {
				//调用父窗口更新相关属性信息
				window.parent.imgBatchUploadCallback($('#uploadedFiles').html());
			});

			$('#uploader').plupload('start');
		} else {
			alert("至少选择一个文件上传");
		}
		return false; // Keep the form from submitting
	});
});
</script>
</body>
</html>