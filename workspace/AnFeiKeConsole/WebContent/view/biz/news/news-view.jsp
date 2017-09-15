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

		<%@include file="../../footer.jsp"%>
	</div>
	<!-- /.container -->
</body>

</html>
