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
				<li><a href="${basePath}/about.htm">简介</a></li>
				<li><a href="${basePath}/products/list.htm">在售机型</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">服务流程 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${basePath}/services.htm">购买流程</a></li>
						<li><a href="${basePath}/services.htm">包机流程</a></li>
						<li><a href="${basePath}/services.htm">代管服务</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">新闻 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="${basePath}/news/list.htm?types=TYPE1">公司新闻</a></li>
						<li><a href="${basePath}/news/list.htm?types=TYPE2">行业新闻</a></li>
						<li><a href="${basePath}/news/list.htm?types=TYPE3">政策法规</a></li>
					</ul></li>
				<li><a href="${basePath}/contact.htm">联系我们</a></li>
				<li>
					<c:choose>
						<c:when test="${SESSION_USER.account != null}">
							<a href="${basePath}/people/logout.shtml">退出</a>
						</c:when>
						<c:otherwise>
							<a href="${basePath}/people/logon-page.htm">登录</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>