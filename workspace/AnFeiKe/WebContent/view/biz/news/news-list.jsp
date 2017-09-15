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
<link rel="stylesheet" href="${basePath}/assets/css/bootstrap.css">
<script>
function addFilterForProducts(fieldType, v, v2, v3){
	var types = '${data.types}';
	var pageIdx = ${data.page};
	var sortField = '${data.sort}';
	var sortOrder = '${data.order}';

	pageIdx = 1;
	if (fieldType == 'NEWSTYPE'){
		types = v;
	}else if (fieldType == 'PAGE'){
		pageIdx = v;
	}else if (fieldType == 'SORT'){
		if (sortField == v){
			if (sortOrder == 'asc'){
				sortOrder = 'desc'
			}else{
				sortOrder = 'asc'
			}
		}else{
			sortField = v;
			sortOrder = 'asc'
		}				
	}
	
	
	location.href='${basePath}/news/list.htm?types=' + types
			+ '&page=' + pageIdx + '&sort=' + sortField + '&order=' + sortOrder;
}


function removeFilterForProducts(fieldType){
	var types = '${data.types}';
	var pageIdx = ${data.page};
	var sortField = '${data.sort}';
	var sortOrder = '${data.order}';

	pageIdx = 1;
	if (fieldType == 'NEWSTYPE'){
		types = '';
	}else if (fieldType == 'PAGE'){
		pageIdx = '';
	}
	
	location.href='${basePath}/news/list.htm?types=' + types
			+ '&page=' + pageIdx + '&sort=' + sortField + '&order=' + sortOrder;
}


</script>
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
							新闻 <small></small>
						</h1>
						<ol class="breadcrumb">
							<li><a href="${basePath}/index.htm">主页</a></li>
							<li><a href="${basePath }/news/list.htm"></a>新闻</li>
							<li class="active">${DICT_TYPE_NEWSTYPE_MAP[data.types]}</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->
				<!-- Content Row -->
				<div class="row">
					<div class="col-md-3">
						<div class="list-group">
							<c:forEach items="${DICT_TYPE_NEWSTYPE}" var="item"
								varStatus="idx">
								<a href="#"
									onclick="addFilterForProducts('NEWSTYPE','${item.code}')"
									class="list-group-item <c:if test="${param.types == item.code}">active</c:if>">${item.name}</a>
							</c:forEach>
						</div>
					</div>
					<!-- Content Column -->
					<div class="col-md-9">
						<div class="row">
							<!-- 分页 -->
							<c:if test="${total == 0}">
								<div class="col-lg-12">
									<div class="alert alert-info" role="alert">很抱歉，没有您要查询的记录！</div>
								</div>
							</c:if>
							<!-- 分页-上部 -->
						</div>

						<c:forEach items="${rows}" var="item" varStatus="idx">
							<div class="row">
								<div class="col-md-4">
									<a href="${basePath}/news/view.htm?id=${item.id}"
										target="_blank""> <img class="img-responsive img-hover"
										src="${basePath}${fn:replace(item.photoPath,'_O','_S')}"
										alt=""></a>
								</div>
								<div class="col-md-8">
									<h4>${item.subject}</h4>
									<p>
										<i class="fa fa-clock-o"></i> 发布于
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.createTime }" />
										、
									</p>
									<a class="btn btn-default"
										href="${basePath}/news/view.htm?id=${item.id}">详情</i>
									</a>
								</div>
							</div>
						</c:forEach>
						<!-- 分页-下部 -->
						<hr>
							<c:if test="${total > 0}">
								<div class="row text-center">
									<div class="col-lg-12">
										<ul class="pagination">
											<li><a href="#"
												onclick="addFilterForProducts('PAGE',${data.previousPage})"
												aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
											<c:forEach begin="${data.paginationStart}"
												end="${data.paginationEnd}" var="currentVal" varStatus="idx">
												<li
													<c:if test="${data.page == currentVal}"> class="active"</c:if>><a
													href="#"
													onclick="addFilterForProducts('PAGE',${currentVal})">${currentVal}</a></li>
											</c:forEach>
											<li><a href="#"
												onclick="addFilterForProducts('PAGE',${data.nextPage})"
												aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
										</ul>
									</div>
								</div>
							</c:if>
					</div>
				</div>
			</div>
		</section>
		<%@include file="../../footer.jsp"%>
	</div>
</body>
</html>
