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

<link rel="stylesheet" type="text/css" href="${basePath}/js/plupload-2.1.8/js/jquery.ui.plupload/css/jquery.ui.plupload.css?id=3">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript" src="${basePath}/js/plupload-2.1.8/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${basePath}/js/plupload-2.1.8/js/jquery.ui.plupload/jquery.ui.plupload.js"></script>
<script type="text/javascript" src="${basePath}/js/plupload-2.1.8/js/i18n/zh_CN.js"></script>





</head>
<body>
<h1>jQuery UI Widget</h1>

<p>You can see this example with different themes on the <a href="http://plupload.com/example_jquery_ui.php">www.plupload.com</a> website.</p>

<form id="form" method="post" action="/echo/json">
	<div id="uploader">
		<p>Your browser doesn't have Flash, Silverlight or HTML5 support.</p>
	</div>
	<br />
	<input type="button" value="Submit" onclick="alert($('#uploader').html());"/>
	</div>
</form>
<script>

$(document).ready(function() {
	$("#uploader").plupload({
		// General settings
		runtimes: 'html5,flash,silverlight,html4',
		
		// Fake server response here 
		// url : '../upload.php',
		url: "<%=basePath%>/file/plupload.shtml",

		// Maximum file size
		max_file_size: '20mb',

		// User can upload no more then 20 files in one go (sets multiple_queues to false)
		max_file_count: 10,
		
		multiple_queues: false,
		
		chunk_size: '1mb',

		// Resize images on clientside if we can
		resize : {width: 1200,	height: 1200,quality: 90, crop: false	},

		// Specify what files to browse for
		filters: [
			{ title: "Image files", extensions: "jpg,gif,png,jpeg" },
			{ title: "Zip files", extensions:  "zip,avi" }
		],

		// Rename files by clicking on their titles
		rename: false,
		
		// Sort files
		sortable: false,

		// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
		dragdrop: true,

		// Views to activate
		views: {
			list: true,
			thumbs: true, // Show thumbs
			active: 'thumbs'
		},
        
		 // Flash settings
        flash_swf_url : '<%=basePath%>/js/plupload-2.1.8/js/Moxie.swf',
     
        // Silverlight settings
        silverlight_xap_url : '<%=basePath%>/js/plupload-2.1.8/js/Moxie.xap',
        
        init :{
        	 FileUploaded : function(up, file, info) {//文件上传完毕触发
                 console.info(up);
                 console.info(file);
                 console.info(info);
                 var response = $.parseJSON(info.response);
                 if (response.success) {
                     $('#form').append('<input type="text" name="fileUrl" value="'+ response.files[0].ctxFilePath +'"/>');
                     $('#form').append('<input type="text" name="fileName" value="'+file.name+'"/><br/>');
                 }

             }
        }
	});


	// Handle the case when form was submitted before uploading has finished
	$('#form').submit(function(e) {
		// Files in queue upload them first
		if ($('#uploader').plupload('getFiles').length > 0) {

			// When all files are uploaded submit form
			$('#uploader').on('complete', function() {
				$('#form')[0].submit();
			});

			$('#uploader').plupload('start');
		} else {
			alert("You must have at least one file in the queue.");
		}
		return false; // Keep the form from submitting
	});
});
</script>
</body>
</html>