<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="../../head.jsp"%>
</head>
<body>
	<%@include file="../../menu.jsp"%>


	<!-- Page Content -->
	<div class="container">

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
				<div class="col-md-2 ">
					<label class="control-label">职位名称</label>
				</div>
				<div class="col-md-10">
					<p>${data.jobTitle}</p>
				</div>
				<div class="col-lg-2 ">
					<label class="control-label"> 工作地</label>
				</div>
				<div class="col-lg-10">
					<p>${data.jobBase}</p>
				</div>
				<div class="col-lg-2 ">
					<label class="control-label">招聘人数</label>
				</div>
				<div class="col-lg-10">
					<p>${data.headCount}</p>
				</div>
				<div class="col-lg-2 ">
					<label class="control-label">联络人</label>
				</div>
				<div class="col-lg-10">
					<p>${data.contact}</p>
				</div>
				<div class="col-lg-2 ">
					<label class="control-label">手机</label>
				</div>
				<div class="col-lg-10">
					<p>${data.mobile}</p>
				</div>
				<div class="col-lg-2 ">
					<label class="control-label">职位要求</label>
				</div>
				<div class="col-lg-10">
					<p>${data.jobReq}</p>
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
	</div>
	<!-- /.row -->

	<%@include file="../../footer.jsp"%>
	</div>
	<!-- /.container -->
</body>

</html>
