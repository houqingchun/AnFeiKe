<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../head.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>门户登录</title>

<!-- Custom styles for this template -->
<link href="${basePath}/css/signin.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<form class="form-signin" id="editForm" action="${basePath}/people/logon.shtml"  method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<label for="account" class="sr-only">用户名</label> <input type="text"
				id="account" class="form-control" placeholder="用户名" name="account" required autofocus>
			<label for="pwd" class="sr-only">密码</label> <input type="password"
				id="pwd" name="pwd" class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住密码
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>

			<div>
				<div class="alert alert-success" role="alert"
					id="notification" style="display: none;">...</div>
			</div>
		</form>
		
	<%@include file="../../footer.jsp"%>
	</div>
	<!-- /container -->

<script>

$(function(){
	portal.init("editForm",function(result){
		if (result.success){
			window.location="${basePath}/people/myHome.shtml";
		}else{
			$('#notification').html(result.msg).removeClass().addClass('alert alert-danger').show();	
		}
	});
});
</script>
</body>

</html>
