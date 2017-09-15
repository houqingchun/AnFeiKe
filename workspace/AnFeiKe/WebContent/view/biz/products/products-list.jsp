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
	var brand = '${data.brand}';
	var name = '${data.name}';
	var types = '${data.types}';
	var pageIdx = ${data.page};
	var sortField = '${data.sort}';
	var sortOrder = '${data.order}';

	pageIdx = 1;
	if (fieldType == 'BRAND'){
		brand = v;
	}else if (fieldType == 'NAME'){
		name = v;
	}else if (fieldType == 'PRODUCTTYPES'){
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
	
	
	location.href='${basePath}/products/list.htm?brand=' + brand + '&name=' + name + '&types=' + types
			+ '&page=' + pageIdx + '&sort=' + sortField + '&order=' + sortOrder;
}


function removeFilterForProducts(fieldType){
	var brand = '${data.brand}';
	var name = '${data.name}';
	var types = '${data.types}';
	var pageIdx = ${data.page};
	var sortField = '${data.sort}';
	var sortOrder = '${data.order}';

	pageIdx = 1;
	if (fieldType == 'BRAND'){
		brand = '';
	}else if (fieldType == 'NAME'){
		name = '';
	}else if (fieldType == 'PRODUCTTYPES'){
		types = '';
	}else if (fieldType == 'PAGE'){
		pageIdx = '';
	}
	
	location.href='${basePath}/products/list.htm?brand=' + brand + '&name=' + name + '&types=' + types
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
							机型 <small>根据条件查询</small>
						</h1>
						<ol class="breadcrumb">
							<li><a href="index.html">主页</a></li>
							<li class="active">机型查询</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<!-- Content Row -->
				<div class="row">
					<div class="col-lg-12">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-2 control-label">机型类别</label>
								<div class="col-sm-10">
									<a href="#" onclick="addFilterForProducts('PRODUCTTYPES','')"
										class="btn btn-sm btn-default <c:if test="${param.types == null || param.types == ''}">btn-info</c:if>">全部</a>
									<c:forEach items="${DICT_TYPE_PRODUCTTYPE}" var="item"
										varStatus="idx">
										<a href="#"
											onclick="addFilterForProducts('PRODUCTTYPES','${item.code}')"
											class="btn btn-sm btn-default <c:if test="${param.types == item.code}">btn-info</c:if>">${item.name}</a>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">品牌</label>
								<div class="col-sm-10">
									<a href="#" onclick="addFilterForProducts('BRAND','')"
										class="btn btn-sm btn-default <c:if test="${param.brand == null || param.brand == ''}">btn-info</c:if>">全部</a>
									<c:forEach items="${DICT_TYPE_BRANDS}" var="item"
										varStatus="idx">
										<a href="#"
											onclick="addFilterForProducts('BRAND','${item.code}')"
											class="btn btn-sm btn-default <c:if test="${param.brand == item.code}">btn-info</c:if>">${item.name}</a>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">模糊查询</label>
								<div class="col-sm-4">
									<div class="input-group text-center">
										<input type="text" class="form-control" name="searchName"
											id="searchName" placeholder="输入型号..." value="${param.name }"><span
											class="input-group-btn">
											<button class="btn btn-primary btn" type="button"
												onclick="addFilterForProducts('NAME',$('#searchName').val())">搜索</button>
										</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">排序条件</label>
								<div class="col-lg-10">
									<a href="#" onclick="addFilterForProducts('SORT','name')"
										class="btn btn-sm btn-default <c:if test="${param.sort == 'name'}">btn-warning</c:if>"><span
										class="glyphicon glyphicon-uploads"></span>型号</a> <a href="#"
										onclick="addFilterForProducts('SORT','price')"
										class="btn btn-sm btn-default <c:if test="${param.sort == 'price'}">btn-warning</c:if>">价格</a>
								</div>
							</div>
							<div class="form-group" id="filterContainer">
								<label class="col-sm-2 control-label">当前筛选条件</label>
								<div class="col-sm-10">
									<c:set var="hasFilter" value="false"></c:set>
									<c:if test="${param.name != null && param.name != ''}">
										<c:set var="hasFilter" value="true"></c:set>
										<a class="btn btn-sm btn-default" href="#"
											onclick="removeFilterForProducts('NAME');"><span>${param.name}
												<label class="glyphicon glyphicon-remove"></label>
										</span> </a>
									</c:if>
									<c:if test="${param.types != null && param.types != ''}">
										<c:set var="hasFilter" value="true"></c:set>
										<a class="btn btn-sm btn-default" href="#"
											onclick="removeFilterForProducts('PRODUCTTYPES');"><span>${DICT_TYPE_PRODUCTTYPE_MAP[param.types]}
												<label class="glyphicon glyphicon-remove"></label>
										</span></a>
									</c:if>
									<c:if test="${param.brand != null && param.brand != ''}">
										<c:set var="hasFilter" value="true"></c:set>
										<a href="#" class="btn btn-sm btn-default"
											onclick="removeFilterForProducts('BRAND');"><span>${DICT_TYPE_BRANDS_MAP[param.brand]}
												<label class="glyphicon glyphicon-remove"></label>
										</span></a>
									</c:if>
									<c:if test="${hasFilter}">
										<a href="${basePath}/products/list.htm"
											class="btn btn-sm btn-primary">重置</a>
									</c:if>
									<span></span>
								</div>
							</div>
							<c:if test="${hasFilter == false }">
								<script>
						$('#filterContainer').remove();
						</script>
							</c:if>
						</form>
					</div>
				</div>
				<hr></hr>
				<div class="row">
					<!-- 分页 -->
					<c:if test="${total == 0}">
						<div class="col-lg-12">
							<div class="alert alert-info" role="alert">很抱歉，没有您要查询的记录！</div>
						</div>
					</c:if>
					<!-- 分页-上部 -->
				</div>
				<div class="row">
					<c:forEach items="${rows}" var="item" varStatus="idx">
						<div class="col-md-3 img-portfolio" style="height: 280px;">
							<a href="${basePath}/products/view.htm?id=${item.id}"
								target="_blank"> <img class="img-responsive img-hover"
								src="${basePath}${fn:replace(item.photoPath,'_O','_S')}" alt=""></a>
							<h4>
								<a href="${basePath}/products/view.htm?id=${item.id}"
									title="${item.name}" target="_blank"> <c:choose>
										<c:when test="${fn:length(item.name) > 15}">
								${fn:substring(item.name,0,15)}...	
							</c:when>
										<c:otherwise>
								${item.name}
							</c:otherwise>
									</c:choose>
								</a>
							</h4>
							<p title="${item.shortDesc}">
								<c:choose>
									<c:when test="${fn:length(item.shortDesc) > 18}">
								${fn:substring(item.shortDesc,0,18)}...	
							</c:when>
									<c:otherwise>
								${item.shortDesc}
							</c:otherwise>
								</c:choose>
							</p>
						</div>
					</c:forEach>
				</div>
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
											href="#" onclick="addFilterForProducts('PAGE',${currentVal})">${currentVal}</a></li>
									</c:forEach>
									<li><a href="#"
										onclick="addFilterForProducts('PAGE',${data.nextPage})"
										aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
								</ul>
							</div>
						</div>
					</c:if>

			</div>
		</section>
		<%@include file="../../footer.jsp"%>
	</div>
</body>
</html>
