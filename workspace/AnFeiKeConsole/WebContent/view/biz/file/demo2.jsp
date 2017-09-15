<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>plupload示例</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/plupload-1.5.8/js/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/html5/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plupload-1.5.8/js/plupload.full.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plupload-1.5.8/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>

<script type="text/javascript">
    $(function() {
        $("#uploader").pluploadQueue({
            runtimes : 'html5,flash,silverlight',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
            flash_swf_url : '${pageContext.request.contextPath}/js/plupload-1.5.8/js/plupload.flash.swf',// Flash环境路径设置
            silverlight_xap_url : '${pageContext.request.contextPath}/js/plupload-1.5.8/js/plupload.silverlight.xap',//silverlight环境路径设置
            url : '${pageContext.request.contextPath}/file/plupload.shtml',//上传文件路径
            max_file_size : '20mb',//100b, 10kb, 10mb, 1gb
    		// User can upload no more then 20 files in one go (sets multiple_queues to false)
    		max_file_count: 1,
    		multiple_queues: false,
            chunk_size : '1mb',//分块大小，小于这个大小的不分块
            unique_names : true,//生成唯一文件名
            // 如果可能的话，压缩图片大小
            //resize : { width : 320, height : 240, quality : 90 },
            // 指定要浏览的文件类型
            filters : [ {
                title : 'Image files',
                extensions : 'jpg,gif,png,jpeg'
            }, {
                title : 'Zip files',
                extensions : 'zip,7z,rar'
            } ],

    		// Sort files
    		sortable: false,

    		// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
    		dragdrop: true,
    		
            init : {
                FileUploaded : function(up, file, info) {//文件上传完毕触发
                    console.info(up);
                    console.info(file);
                    console.info(info);
                    var response = $.parseJSON(info.response);
                    if (response.success) {
                        $('#f1').append('<input type="text" name="fileUrl" value="'+ response.files[0].ctxFilePath +'"/>');
                        $('#f1').append('<input type="text" name="fileName" value="'+file.name+'"/><br/>');
                    }
                }
            }
        });
 
        // 客户端表单验证
        $('form').submit(function(e) {
            var uploader = $('#uploader').pluploadQueue();
            if (uploader.files.length > 0) {// 判断队列中是否有文件需要上传
                uploader.bind('StateChanged', function() {// 在所有的文件上传完毕时，提交表单
                    if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
                        $('form')[0].submit();
                    }
                });
                uploader.start();
            } else {
                alert('请选择至少一个文件进行上传！');
            }
            return false;
        });
 
    });
</script>
</head>
<body>
    <form id="f1" method="post">
        <table border="1">
            <tr>
                <td colspan="2">
                    <div id="uploader">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
                    <!-- 
                    <button onclick="$('#uploader').pluploadQueue().start();">开始上传</button>
                    <button onclick="$('#uploader').pluploadQueue().stop();">停止上传</button>
                     -->
                </td>
            </tr>
            <tr>
                <th>姓名</th>
                <td><input name="name" value="Irving" /></td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input name="pwd" value="123456" /></td>
            </tr>
        </table>
        <button type="button">提交表单</button>
    </form>
</body>
</html>