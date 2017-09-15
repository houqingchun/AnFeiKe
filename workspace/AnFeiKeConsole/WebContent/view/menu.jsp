<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="height: 40px;">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#" style="margin-top: -8px;"> <img
				src="${basePath}/assets/images/logo.gif" class="logo" alt="">
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${SESSION_USER != null && SESSION_USER.account != null}">
							<li><a href="${basePath}/people/logout.shtml">退出</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${basePath}/people/logon-page.htm">管理员登录</a></li>
						</c:otherwise>
					</c:choose>
				
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>