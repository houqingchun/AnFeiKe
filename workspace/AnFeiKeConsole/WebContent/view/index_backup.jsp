<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="top.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>安飞客通用航空::机型信息</title>
<%@include file="head.jsp"%>
</head>

<body>
	<%@include file="menu.jsp"%>


	<!-- Header Carousel -->
	<header id="myCarousel" class="carousel slide"> <!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner">
		<div class="item active">
			<div class="fill"
				style="background-image: url('images/slide_1.png');"></div>
			<div class="carousel-caption">
				<h2></h2>
			</div>
		</div>
		<div class="item">
			<div class="fill"
				style="background-image: url('images/slide_2.png');"></div>
			<div class="carousel-caption">
				<h2></h2>
			</div>
		</div>
		<div class="item">
			<div class="fill"
				style="background-image: url('images/slide_3.png');"></div>
			<div class="carousel-caption">
				<h2></h2>
			</div>
		</div>
	</div>

	<!-- Controls --> <a class="left carousel-control" href="#myCarousel"
		data-slide="prev"> <span class="icon-prev"></span>
	</a> <a class="right carousel-control" href="#myCarousel" data-slide="next">
		<span class="icon-next"></span>
	</a> </header>

	<!-- Page Content -->
	<div class="container">

		<!-- Marketing Icons Section -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">欢迎访问安飞客</h1>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-check"></i> 服务流程
						</h4>
					</div>
					<div class="panel-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Itaque, optio corporis quae nulla aspernatur in alias at numquam
							rerum ea excepturi expedita tenetur assumenda voluptatibus
							eveniet incidunt dicta nostrum quod?</p>
						<a href="#" class="btn btn-default">Learn More</a>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-gift"></i> 我们的宗旨
						</h4>
					</div>
					<div class="panel-body">
						<p>追梦蓝天，安全飞翔</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4>
							<i class="fa fa-fw fa-compass"></i>关于售后
						</h4>
					</div>
					<div class="panel-body">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Itaque, optio corporis quae nulla aspernatur in alias at numquam
							rerum ea excepturi expedita tenetur assumenda voluptatibus
							eveniet incidunt dicta nostrum quod?</p>
						<a href="#" class="btn btn-default">Learn More</a>
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

		<!-- Portfolio Section -->
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">热销机型</h2>
			</div>
			<div class="col-md-4 col-sm-6">
				<a href="portfolio-item.html"> <img
					class="img-responsive img-portfolio img-hover"
					src="http://placehold.it/700x450" alt=""></a>
			</div>
			<div class="col-md-4 col-sm-6">
				<a href="portfolio-item.html"> <img
					class="img-responsive img-portfolio img-hover"
					src="http://placehold.it/700x450" alt=""></a>
			</div>
			<div class="col-md-4 col-sm-6">
				<a href="portfolio-item.html"> <img
					class="img-responsive img-portfolio img-hover"
					src="http://placehold.it/700x450" alt=""></a>
			</div>
			<div class="col-md-4 col-sm-6">
				<a href="portfolio-item.html"> <img
					class="img-responsive img-portfolio img-hover"
					src="http://placehold.it/700x450" alt=""></a>
			</div>
			<div class="col-md-4 col-sm-6">
				<a href="portfolio-item.html"> <img
					class="img-responsive img-portfolio img-hover"
					src="http://placehold.it/700x450" alt=""></a>
			</div>
			<div class="col-md-4 col-sm-6">
				<a href="portfolio-item.html"> <img
					class="img-responsive img-portfolio img-hover"
					src="http://placehold.it/700x450" alt=""></a>
			</div>
		</div>
		<!-- /.row -->

		<!--end-footer-->
		<%@include file="footer.jsp"%>
		<!--end-footer-->


	</div>
	<!-- /.container -->
	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>

</body>

</html