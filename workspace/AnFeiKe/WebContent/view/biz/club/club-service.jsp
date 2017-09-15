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
							俱乐部 <small>信息查询</small>
						</h1>
						<ol class="breadcrumb">
							<li><a href="${basePath}/index.htm">主页</a></li>
							<li>俱乐部</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<!-- Content Row -->
				<div class="row">
					<div class="col-lg-9 p-bottom-40">
						<ul id="myTab" class="nav nav-tabs nav-justified">
							<li
								class="<c:if test='${param.service=="background" }'>active</c:if>"><a
								href="#service-one" data-toggle="tab"><i
									class="fa fa-history"></i> 背景</a></li>
							<li
								class="<c:if test='${param.service=="qualification" }'>active</c:if>"><a
								href="#service-two" data-toggle="tab"><i
									class="fa fa-certificate"></i> 资质</a></li>
							<li
								class="<c:if test='${param.service=="assurance" }'>active</c:if>"><a
								href="#service-three" data-toggle="tab"><i
									class="fa fa-asterisk"></i> 保险内容</a></li>
							<li
								class="<c:if test='${param.service=="responsibility" }'>active</c:if>"><a
								href="#service-four" data-toggle="tab"><i
									class="fa fa-heart"></i> 社会责任</a></li>
						</ul>

						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in" id="service-one">
								<h3>&nbsp;</h3>
								<p>${data.background}</p>
							</div>
							<div class="tab-pane fade" id="service-two">
								<h3>&nbsp;</h3>
								<p>${data.qualification}</p>
							</div>
							<div class="tab-pane fade" id="service-three">
								<h3>&nbsp;</h3>
								<p>${data.assurance}</p>
							</div>
							<div class="tab-pane fade" id="service-four">
								<h3>&nbsp;</h3>
								<p>${data.responsibility}</p>
							</div>
						</div>
					</div>

					<div class="col-lg-3 p-bottom-40">
						<div class="panel panel-info">
							<div class="panel-heading">
								<h3 class="panel-title">俱乐部招聘</h3>
							</div>
							<div class="panel-body">
								<p>${data.recruit}</p>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">联系我们</h3>
							</div>
							<div class="panel-body">
								<p>${data.contactUs}</p>
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
