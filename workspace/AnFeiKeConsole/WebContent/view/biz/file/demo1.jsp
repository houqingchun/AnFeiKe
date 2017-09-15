<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/plupload-2.1.8/js/plupload.full.min.js?randomId=<%=Math.random()%>"></script>



</head>
<body>
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
		        flash_swf_url : '<%=basePath%>/js/plugins/plupload-2.1.8/js/Moxie.swf',
		     
		        // Silverlight settings
		        silverlight_xap_url : '<%=basePath%>/js/plugins/plupload-2.1.8/js/Moxie.xap',
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
		                        $('console').innerHTML='<input type="text" name="fileUrl" value="'+ response.files[0].ctxFilePath +'"/>';
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