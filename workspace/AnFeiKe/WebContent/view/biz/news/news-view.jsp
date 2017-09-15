<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../top.jsp"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<title>安飞客通用航空</title>
<%@include file="../../head.jsp"%>
</head>
<body data-spy="scroll" data-target=".navbar-collapse">


	<!-- Preloader -->
	<div id="loading">
		<div id="loading-center">
			<div id="loading-center-absolute">
				<div class="object" id="object_one"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_three"></div>
				<div class="object" id="object_four"></div>
			</div>
		</div>
	</div>
	<!--End off Preloader -->


	<div class="culmn">
		<!--Home page style-->
		<%@include file="../../menu.jsp"%>

		<!-- Page Content -->
		<section id="products-view" class="p-top-30">
			<div class="container p-top-30">

				<!-- Page Heading/Breadcrumbs -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							新闻 <small>明细信息查询</small>
						</h1>
						<ol class="breadcrumb">
							<li><a href="${basePath}/index.htm">主页</a></li>
							<li>新闻</li>
							<li><a href="${basePath}/news/list.htm?types=${data.types}">${DICT_TYPE_NEWSTYPE_MAP[data.types]}</a></li>
							<li class="active">${data.subject}</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<!-- Content Row -->
				<div class="row">
					<!-- Content Column -->
					<div class="col-md-9">
						<div class="row">
							<div class="col-lg-12 text-center">
								<h3>${data.subject}</h3>
								<p>
									<i class="fa fa-clock-o"></i> 发布于
									<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
										value="${data.createTime }" />
								</p>
								<hr>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<p>${data.description}</p>
							</div>
							、
						</div>
					</div>
					<div class="col-md-3">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">联系我们</h3>
							</div>
							<div class="panel-body">
								<%@include file="../../contact-content.jsp"%>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</section>
		<%@include file="../../footer.jsp"%>
	</div>
</body>
</html>
