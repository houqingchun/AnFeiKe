<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		<nav class="navbar navbar-default bootsnav navbar-fixed">
			<div class="container">
				<!-- Start Header Navigation -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navbar-menu">
						<i class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand" href="#"> <img
						src="${basePath}/assets/images/logo.gif" class="logo" alt="">
					</a>
				</div>
				<!-- End Header Navigation -->

				<!-- navbar menu -->
				<div class="collapse navbar-collapse" id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${basePath}/index.htm">主页</a></li>
						<li><a href="${basePath}/company/service.htm?service=training">服务介绍</a></li>
						<li><a href="${basePath}/index.htm#business">关于我们</a></li>
						<li><a href="${basePath}/index.htm#product">机型介绍</a></li>
						<li><a href="${basePath}/club/service.htm?service=background">俱乐部</a></li>
						<li><a href="${basePath}/index.htm#news">新闻中心</a></li>
						<li><a href="${basePath}/index.htm#contact">联系我们</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
		</nav>
