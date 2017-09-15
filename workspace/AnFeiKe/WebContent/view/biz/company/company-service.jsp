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
							服务介绍 <small>信息查询</small>
						</h1>
						<ol class="breadcrumb">
							<li><a href="${basePath}/index.htm">主页</a></li>
							<li>服务介绍</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<!-- Content Row -->
				<div class="row">
					<div class="col-lg-12 p-bottom-40">
						<ul id="myTab" class="nav nav-tabs nav-justified">
							<li class="<c:if test='${param.service=="training" }'>active</c:if>"><a href="#service-one" data-toggle="tab"><i
									class="fa fa-thumbs-o-up"></i> 飞行培训</a></li>
							<li class="<c:if test='${param.service=="trusteeship" }'>active</c:if>"><a href="#service-two" data-toggle="tab"><i
									class="fa fa-support"></i> 飞机托管</a></li>
							<li class="<c:if test='${param.service=="rent" }'>active</c:if>"><a href="#service-three" data-toggle="tab"><i
									class="fa fa-clock-o"></i> 租赁服务</a></li>
							<li class="<c:if test='${param.service=="sale" }'>active</c:if>"><a href="#service-four" data-toggle="tab"><i
									class="fa fa-plane"></i> 销售服务</a></li>
						</ul>

						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in" id="service-one">
								<h3>&nbsp;</h3>
								<p>${data.training}</p>
							</div>
							<div class="tab-pane fade" id="service-two">
								<h3>&nbsp;</h3>
								<p>${data.trusteeship}</p>
							</div>
							<div class="tab-pane fade" id="service-three">
								<h3>&nbsp;</h3>
								<p>${data.rent}</p>
							</div>
							<div class="tab-pane fade" id="service-four">
								<h3>&nbsp;</h3>
								<p>${data.sale}</p>
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
