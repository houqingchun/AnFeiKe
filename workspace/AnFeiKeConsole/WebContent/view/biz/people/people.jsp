<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../top.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/favicon.ico">
<title>安飞客通用航空</title>

<!-- Bootstrap core CSS -->
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">

	<!-- Custom styles for this template -->
<style type="text/css">
body {
	min-height: 2000px;
	padding-top: 70px;
}
</style>


</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">安飞客通用航空</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">首页</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">乐队 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li class="dropdown-header">风格</li>
							<li><a href="#">摇滚</a></li>
							<li><a href="#">爵士</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">人员组成</li>
							<li><a href="#">小编</a></li>
							<li><a href="#">大编</a></li>
						</ul></li>
					<li><a href="#">组合</a></li>
					<li><a href="#">歌手</a></li>
					<li><a href="#">乐手</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#" data-toggle="modal" data-target="#logonDialog">登录/注册</a></li>
					<li><a href="#">App下载</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container">

<div class="col-xs-10">
          <h2>用户注册</h2>
		<form id="peopleRegistForm" class="form-horizontal" action="${basePath}/people/save.shtml">

			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-6">
					<div class="radio">
						<label> <input checked="checked" value="BAND" name="type"
							type="radio"> 我来自一只乐队
						</label> </label>
					</div>
					<div class="radio">
						<label> <input value="我来自一个组合" name="type" value="GROUP"
							type="radio"> 我来自一个组合
						</label> </label>
					</div>
					<div class="radio">
						<label> <input value="我是一名歌手" name="type" value="SIGNER"
							type="radio"> 我是一名歌手
						</label> </label>
					</div>
					<div class="radio">
						<label> <input value="我是一名乐手" name="type" value="BANDSMAN"
							type="radio"> 我是一名乐手
						</label> </label>
					</div>
				</div>
			</div>



			<div class="form-group">

				<!-- Text input-->
				<label class="col-xs-2 control-label" for="input01"><span class="requiredmark">*</span>登陆账号</label>
				<div class="col-xs-6">
					<input placeholder="登陆账号" class="form-control" type="text"
						name="account" required="required">
					<p class="help-block"></p>
				</div>
			</div>

			<div class="form-group">

				<!-- Text input-->
				<label class="col-xs-2 control-label" for="input01"><span class="requiredmark">*</span>登陆密码</label>
				<div class="col-xs-6">
					<input placeholder="登陆密码" class="form-control" type="password"
						name="pwd" required="required">
					<p class="help-block"></p>
				</div>
			</div>

			<div class="form-group">

				<!-- Text input-->
				<label class="col-xs-2 control-label" for="input01"><span class="requiredmark">*</span>重新输入密码</label>
				<div class="col-xs-6">
					<input placeholder="请重新输入密码" class="form-control" type="password"
						name="pwdRepeat" required="required">
				</div>
			</div>

			<div class="form-group">
				<!-- Text input-->
				<label class="col-xs-2 control-label" for="input01">昵称</label>
				<div class="col-xs-6">
					<input placeholder="昵称" class="form-control" type="text"
						name="name">
					<p class="help-block">昵称将显示于你所发布的内容中</p>
				</div>
			</div>
			<div class="form-group">

				<!-- Text input-->
				<label class="col-xs-2 control-label" for="input01">邮箱</label>
				<div class="col-xs-6">
					<input placeholder="邮箱" class="form-control" type="email"
						name="email">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 control-label"></label>

				<!-- Button -->
				<div class="col-xs-6">
					<button type="button" id="fromSaveBtn" class="btn btn-primary">注册</button>
				</div>
			</div>
		</form>
</div>
	</div>
	<!-- /container -->
	
	
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.jsdelivr.net/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script src="${basePath}/js/custom/form-validate.js" type="text/javascript" charset="utf-8"></script>
<script src="${basePath}/js/commons/package.js" type="text/javascript" charset="utf-8"></script>
<script src="${basePath}/js/commons/base.js" type="text/javascript" charset="utf-8"></script>
<script src="${basePath}/js/commons/jquery.form.js" type="text/javascript" charset="utf-8"></script>  
<script type="text/javascript" charset="utf-8">

$(function(){
	
$('#fromSaveBtn').click(function(){
	jeecg.progress("请等待","数据保存中…");
	if ($("#peopleRegistForm").validate().form()){
		var option =
		{
		 	type:'post',
		 	dataType: 'json',
		 	success:function(data){
		 		if($.isFunction(callback)){
		 			callback(data);
		 		}
		 	},
		 	error:function(response, textStatus, errorThrown){
		 		try{
		 			//jeecg.closeProgress();
		 			var data = $.parseJSON(response.responseText);
			 		//alert("保存成功");
		 		}catch(e){
		 			alert("保存失败");
		 		}
		 	},
		 	complete:function(){
		 	
		 	}
		 }
		
		$("#peopleRegistForm").ajaxSubmit(option);
	}	
});

function loadForm(){
	var data ={};
	data["name"] = "Boris";
	data["account"] = "Login";
	data["pwd"] = "123";
	$("#peopleRegistForm").form('load',data);
}

loadForm();
});
</script>  

</body>
</html>
