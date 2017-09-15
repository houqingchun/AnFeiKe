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
					机型 <small>明细信息查询</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${basePath}/index.htm">主页</a></li>
					<li><a href="${basePath}/products/list.htm">机型查询</a></li>
					<li class="active">${data.name}</li>
				</ol>
			</div>
		</div>
		<!-- /.row -->
		<!-- Content Row -->
		<div class="row">
			<!-- Sidebar Column -->
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
			<!-- Content Column -->
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-6">
						<div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel">
							<!-- Indicators -->
							<ol class="carousel-indicators">
								<li data-target="#carousel-example-generic" data-slide-to="0"
									class="active"></li>
								<c:forEach items="${data.photos}" var="item" varStatus="idx">
									<li data-target="#carousel-example-generic"
										data-slide-to="${idx.index + 1}"></li>
								</c:forEach>
							</ol>

							<!-- Wrapper for slides -->
							<div class="carousel-inner">
								<div class="item active">
									<img class="img-responsive"
										src="${basePath}${fn:replace(data.photoPath,'_O','_M')}">
								</div>
								<c:forEach items="${data.photos}" var="item" varStatus="idx">
									<div class="item">
										<img class="img-responsive"
											src="${basePath}${fn:replace(item.photoPath,'_O','_M')}">
									</div>
								</c:forEach>
							</div>

							<!-- Controls -->
							<a class="left carousel-control" href="#carousel-example-generic"
								data-slide="prev"> <span
								class="glyphicon glyphicon-chevron-left"></span>
							</a> <a class="right carousel-control"
								href="#carousel-example-generic" data-slide="next"> <span
								class="glyphicon glyphicon-chevron-right"></span>
							</a>
						</div>
					</div>

					<div class="col-md-6">
						<h3>${data.name}</h3>
						<div class="well">${data.shortDesc}</div>
						<p>${data.types}&nbsp;</p>
						<p>${data.price}万元</p>
						<p>${data.brand}</p>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header">详细信息</h3>
					</div>
					<div class="col-lg-12">
						<p>${data.description}</p>
					</div>
					、
				</div>
			</div>
		</div>
		<!-- /.row -->

		<%@include file="../../footer.jsp"%>

	</div>
	<!-- /.container -->

</body>

</html>
