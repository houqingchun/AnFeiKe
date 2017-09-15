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
							招聘 <small>明细信息查询</small>
						</h1>
						<ol class="breadcrumb">
							<li><a href="${basePath}/index.htm">主页</a></li>
							<li>招聘</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<!-- Content Row -->
				<div class="row">
					<div class="col-md-9">
						<!-- Content Column -->
						<div class="col-lg-2 m-top-10"><strong>职位名称</strong></div>
						<div class="col-lg-10 m-top-10">${data.jobTitle}</div>
						<div class="col-lg-2 m-top-10"><strong>工作地</strong></div>
						<div class="col-lg-10 m-top-10">${data.jobBase}</div>
						<div class="col-lg-2 m-top-10"><strong>招聘人数</strong></div>
						<div class="col-lg-10 m-top-10">${data.headCount}</div>
						<div class="col-lg-2 m-top-10"><strong>联络人</strong></div>
						<div class="col-lg-10 m-top-10">${data.contact}</div>
						<div class="col-lg-2 m-top-10"><strong>手机</strong></div>
						<div class="col-lg-10 m-top-10">${data.mobile}</div>
						<div class="col-lg-2 m-top-10"><strong>职位要求</strong></div>
						<div class="col-lg-10 m-top-10">${data.jobReq}</div>
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
			</div>
			<!-- /.row -->
		</section>

		<%@include file="../../footer.jsp"%>
	</div>
	</div>
</body>
</html>
